package service;

import java.util.List;

import entetiesDao.RelatorioCompraDTO;
import entetiesDao.RelatorioVendaDTO;
import repository.RepositoryBancoRelatorio;

public class RelatorioService {

	private RepositoryBancoRelatorio<RelatorioCompraDTO , RelatorioVendaDTO> repository;

	public RelatorioService(RepositoryBancoRelatorio<RelatorioCompraDTO, RelatorioVendaDTO> repository) {
		this.repository = repository;
	}
	
	public void listarCompras() {

        List<RelatorioCompraDTO> compras = repository.listarCompra();

        for (RelatorioCompraDTO compra : compras) {
            System.out.println(compra);
        }
    }

    public void listarVendas() {

        List<RelatorioVendaDTO> vendas = repository.listarVenda();

        for (RelatorioVendaDTO venda : vendas) {
            System.out.println(venda);
        }
    }
    
    public void listarVendaDetalhada() {
    	
    }
    
    public void faturamento() {
    	System.out.println("Faturamento da Vendas = R$"+repository.listarFaturamento());
    }
	public void valorEstoque() {
		System.out.println("Valor do estoque = R$"+repository.listarValorEstoque());
	}
	
	
	
}
