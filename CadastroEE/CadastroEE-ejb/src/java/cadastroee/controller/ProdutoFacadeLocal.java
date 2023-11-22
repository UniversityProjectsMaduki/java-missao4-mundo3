package cadastroee.controller;

import cadastroee.model.Produto;
import java.util.List;
import jakarta.ejb.Local;

/**
 * Interface local para a fachada de Produtos.
 */
@Local
public interface ProdutoFacadeLocal {

    /**
     * Método para encontrar todos os produtos.
     *
     * @return uma lista de produtos.
     */
    List<Produto> findAll();
    // Outros métodos podem ser adicionados conforme necessário.
}
