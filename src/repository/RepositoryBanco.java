package repository;

import java.util.List;

public interface RepositoryBanco<T, I> {
	void salvar(T entidade);

	T buscarPorId(Integer id);

	List<T> listar();

	void atualizar(T entidade);

	void deletar(Integer id);
}
