package cadastroee.controller;

import cadastroee.model.Produto;
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface ProdutoFacadeLocal {

    void create(Produto produto);

    Produto find(Object id);

    void edit(Produto produto);

    void remove(Produto produto);

    List<Produto> findAll();
}
