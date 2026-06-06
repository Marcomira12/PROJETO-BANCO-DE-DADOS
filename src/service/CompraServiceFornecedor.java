package service;

import java.math.BigDecimal;
import java.time.LocalDate;

import entetiesDao.Nota_CompraDTO;
import model.Fornecedor;
import model.Item_Compra;
import model.Nota_Compra;
import model.Produto;
import repository.NotaRepository;

public class CompraServiceFornecedor {
	private NotaRepository<Nota_Compra, Item_Compra, Integer> notaRepository;
	private ProdutoService produtoService;
	private FornecedorService fornecedorService;

	
	public CompraServiceFornecedor(NotaRepository<Nota_Compra, Item_Compra, Integer> notaRepository,
			ProdutoService produtoService, FornecedorService fornecedorService) {
		super();
		this.notaRepository = notaRepository;
		this.produtoService = produtoService;
		this.fornecedorService = fornecedorService;
	}

	public Nota_CompraDTO adicionarItemAoLote(Nota_CompraDTO dto, Integer produtoId, Integer quantidade) {
		Produto produto = produtoService.buscarPorId(produtoId);
		if(produto == null){
			System.out.println("Produto não cadastrado");
			return dto;
		}
		if (quantidade == null || quantidade <= 0) {
			System.out.println("Quantidade inválida");
			
			return dto;
		}

		Item_Compra item = new Item_Compra();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setPrecoCusto(produto.getValorAtualProduto());

		dto.getItens().add(item);

		return dto;

	}

	public Nota_Compra executarCompraParaEstoque(Nota_CompraDTO dto) {
		if (dto == null) {
			System.out.println("Dados insuficientes");
			return null;
		}

		if (dto.getFornecedor() == null) {
			System.out.println("Fornecedor não informado");
			return null;
		}

		if (dto.getFornecedor().getId() == null) {
			System.out.println("ID do Fornecedor não informado");
			return null;
		}
		
		Fornecedor fornecedor = fornecedorService.buscarFornecedorID(dto.getFornecedor().getId());

		if (fornecedor == null) {
			System.out.println("Fornecedor não encontrado");
			return null;
		}
		dto.setFornecedor(fornecedor);
		return realizarRegistroCompraAoEstoque(dto);

	}

	private Nota_Compra realizarRegistroCompraAoEstoque(Nota_CompraDTO dto) {
		Fornecedor fornecedor = dto.getFornecedor();

		BigDecimal valorTotal = BigDecimal.ZERO;

		Nota_Compra nota = new Nota_Compra();
		nota.setFornecedor(fornecedor);
		nota.setData(LocalDate.now());
		for (Item_Compra item : dto.getItens()) {

			BigDecimal subtotal = item.getProduto().getValorAtualProduto()
					.multiply(BigDecimal.valueOf(item.getQuantidade()));
			valorTotal = valorTotal.add(subtotal);
		}
		nota.setValorTotal(valorTotal);
		nota.setId(notaRepository.criarNota(nota));
		nota.setItens(dto.getItens());
		
		// momento critico para ser tratado por Therds, pois a execução deverá ocorrer
		// simultaneamente

		for (Item_Compra item : dto.getItens()) {
			notaRepository.adicionarItem(nota.getId(), item);
			item.getProduto().setEstoque(item.getQuantidade());
			produtoService.aumentarEstoque(item.getProduto());
		}

		notaRepository.finalizar(nota.getId());
		return nota;

	
	}

}
