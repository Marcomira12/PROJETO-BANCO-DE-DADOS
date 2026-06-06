package repository;

public interface NotaRepository<N, I, ID> {

    ID criarNota(N nota);

    void adicionarItem(ID notaId, I item);

    N buscarPorId(ID id);

    void finalizar(ID notaId);
}
