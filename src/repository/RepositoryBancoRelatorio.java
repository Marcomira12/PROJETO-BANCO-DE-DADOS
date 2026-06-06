package repository;

import java.math.BigDecimal;
import java.util.List;

public interface RepositoryBancoRelatorio<T, I> {
	List<T> listarCompra();
	List<I> listarVenda();
	BigDecimal listarValorEstoque();
	BigDecimal listarFaturamento();

}
