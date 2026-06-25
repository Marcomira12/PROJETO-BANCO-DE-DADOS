package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import entetiesDao.Nota_VendaDTO;
import model.Cliente;
import model.Item_Venda;
import model.Nota_Venda;
import model.Produto;
import reflexao.ClasseTipo;
import reflexao.Comando;
import reflexao.Service;
import repository.NotaRepository;
@ClasseTipo(descricao = "Realizar Venda",order = 4)
@Service(nome = "Venda")
public class VendaServiceCliente {
	private NotaRepository<Nota_Venda, Item_Venda, Integer> notaRepository;
	private ProdutoService produtoService;
	private ClienteService clienteService;
	/*
	 * Aplicando SOLID e recebendo os serviços de produto, cliente e a classe banco
	 * responsável.
	 */

	public VendaServiceCliente(NotaRepository<Nota_Venda, Item_Venda, Integer> notaVendaRepository,
			ProdutoService produtoService, ClienteService clienteService) {
		this.notaRepository = notaVendaRepository;
		this.produtoService = produtoService;
		this.clienteService = clienteService;
	}

	/*
	 * Metodo responsável pela adição de itens a uma DTO nota recebido por
	 * parâmetro, será informado o codigo do produto e quantidade a serem vendidos.
	 * Será realizado validações através de metodos injetados via construtor de
	 * produto.
	 */
	@Comando(descricao = "Adicionar item",order = 1)
	public Nota_VendaDTO adicionarItem(Nota_VendaDTO dto, Integer produtoId, Integer quantidade) {
		Produto produto = produtoService.buscarPorId(produtoId);
		if (produto == null) {
			System.out.println("Produto não encontrado");
			return dto;
		}

		if (quantidade == null || quantidade <= 0) {
			System.out.println("Quantidade inválida");
			return dto;
		}

		if (produto.getEstoque() < quantidade) {
			System.out.println("Quantidade insuficiente em estoque");
			return dto;
		}

		Item_Venda item = new Item_Venda();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setPrecoUnitario(produto.getValorAtualProduto());

		dto.getItens().add(item);

		return dto;

	}
	@Comando(descricao = "Executar Venda",order = 2)
	public Nota_Venda executarVenda(Nota_VendaDTO dto) {
		if (dto == null) {
			System.out.println("Dados insuficientes");
			return null;
		}

		if (dto.getCliente() == null) {
			System.out.println("Cliente não informado");
			return null;
		}

		if (dto.getCliente().getId() == null) {
			System.out.println("ID do cliente não informado");
			return null;
		}

		Cliente cliente = clienteService.buscarClienteId(dto.getCliente().getId());
		
		if (cliente == null) {
			System.out.println("Cliente não encontrado");
			return null;
		}
		if(dto.getItens().isEmpty()) {
			System.out.println("Lista de Itens vazia");
			return null;
		}
		
		dto.setCliente(cliente);
		return realizarVenda(dto);
	}

	/*
	 * Metodo que realiza a ação de vender e encaminhar os dados necessários e
	 * aplicação de regra de négocio para a classe banco para ser registrado a nota
	 * e os itens ligados a nota, juntamente realizando a operação de atualização do
	 * estoque.
	 */
	private Nota_Venda realizarVenda(Nota_VendaDTO dto) {

		Cliente cliente = dto.getCliente();

		BigDecimal valorTotal = BigDecimal.ZERO;

		Nota_Venda nota = new Nota_Venda();
		nota.setCliente(cliente);
		nota.setData(LocalDate.now());
		for (Item_Venda item : dto.getItens()) {
			if(item.getProduto().equals(null)) {
				return null;
			}
			BigDecimal subtotal = item.getProduto().getValorAtualProduto()
					.multiply(BigDecimal.valueOf(item.getQuantidade()));
			valorTotal = valorTotal.add(subtotal);
		}
		
		
		nota.setValor_cashback_utilizado(cliente.getCashback());
		nota.setValor_cashback_utilizado(nota.getValor_cashback_utilizado().min(valorTotal));
		nota.setValorTotal(valorTotal.subtract(nota.getValor_cashback_utilizado()));

		if (nota.getValor_cashback_utilizado().compareTo(BigDecimal.ZERO) == 0) {
			nota.setValor_cashbakc_gerado(calcularCashback(nota.getValorTotal()));
			cliente.setCashback(nota.getValor_cashbakc_gerado());
		} else {
			nota.setValor_cashbakc_gerado(BigDecimal.ZERO);
			cliente.setCashback(BigDecimal.ZERO);
		}

		nota.setId(notaRepository.criarNota(nota));
		nota.setItens(dto.getItens());
		// momento critico para ser tratado por Therds, pois a execução deverá ocorrer
		// simultaneamente

		for (Item_Venda item : dto.getItens()) {
			notaRepository.adicionarItem(nota.getId(), item);
			item.getProduto().setEstoque(item.getQuantidade());
			produtoService.baixarEstoque(item.getProduto());
		}

		clienteService.updateClienteCashback(cliente, cliente.getCashback());

		notaRepository.finalizar(nota.getId());
		return nota;
	}

	// metodo responsável pela aplicação do CashBack.
	private BigDecimal calcularCashback(BigDecimal valorVenda) {
		return valorVenda.multiply(new BigDecimal("0.05"));
	}
}
