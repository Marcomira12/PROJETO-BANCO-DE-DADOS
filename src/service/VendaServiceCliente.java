package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

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
	public Nota_VendaDTO adicionarItem(Nota_VendaDTO dto, Scanner sc) {
		produtoService.listar();
		System.out.println("Digite o ID do Produto: ");
		Produto produto = produtoService.buscarPorId(Integer.parseInt(sc.nextLine()));
		System.out.println("Digite a quantidade: ");
		Integer quantidade=Integer.parseInt(sc.nextLine());
		
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
	    Nota_Venda nota = new Nota_Venda();

	    nota.setCliente(cliente);
	    nota.setData(LocalDate.now());

	    BigDecimal valorBruto = calcularValorBruto(dto);
	    BigDecimal cashbackUtilizado = calcularCashbackUtilizado(dto, valorBruto);
	    BigDecimal valorFinal = valorBruto.subtract(cashbackUtilizado);

	    nota.setValor_cashback_utilizado(cashbackUtilizado);
	    nota.setValorTotal(valorFinal);

	    if (cashbackUtilizado.compareTo(BigDecimal.ZERO) == 0) {
	        nota.setValor_cashbakc_gerado(calcularCashback(valorFinal));
	        cliente.setCashback(nota.getValor_cashbakc_gerado());
	    } else {
	        nota.setValor_cashbakc_gerado(BigDecimal.ZERO);
	        cliente.setCashback(BigDecimal.ZERO);
	    }

	    nota.setId(notaRepository.criarNota(nota));
	    nota.setItens(dto.getItens());

	    for (Item_Venda item : dto.getItens()) {
	        notaRepository.adicionarItem(nota.getId(), item);

	        item.getProduto().setEstoque(item.getQuantidade());
	        produtoService.baixarEstoque(item.getProduto());
	    }

	    clienteService.updateClienteCashback(cliente, cliente.getCashback());

	    notaRepository.finalizar(nota.getId());

	    return nota;
	}
	@Comando(descricao = "Calcular Valor da venda",order = 3)
	public BigDecimal calcularValorBruto(Nota_VendaDTO dto) {
	    BigDecimal valorTotal = BigDecimal.ZERO;

	    for (Item_Venda item : dto.getItens()) {
	        if (item.getProduto() == null) {
	            throw new RuntimeException("Produto não encontrado no item da venda");
	        }

	        BigDecimal subtotal = item.getProduto().getValorAtualProduto()
	                .multiply(BigDecimal.valueOf(item.getQuantidade()));

	        valorTotal = valorTotal.add(subtotal);
	    }

	    return valorTotal;
	}

	public BigDecimal calcularCashbackUtilizado(Nota_VendaDTO dto, BigDecimal valorTotal) {
	    BigDecimal cashbackCliente = dto.getCliente().getCashback();

	    if (cashbackCliente == null) {
	        return BigDecimal.ZERO;
	    }

	    return cashbackCliente.min(valorTotal);
	}
	// metodo responsável pela aplicação do CashBack.
	private BigDecimal calcularCashback(BigDecimal valorVenda) {
		return valorVenda.multiply(new BigDecimal("0.05"));
	}
}
