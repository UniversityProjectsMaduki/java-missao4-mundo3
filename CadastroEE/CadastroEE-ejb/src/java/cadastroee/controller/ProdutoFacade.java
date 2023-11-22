package cadastroee.controller;

import cadastroee.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Classe de fachada para lidar com operações na entidade Produto.
 */
@Stateless
public class ProdutoFacade implements ProdutoFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    /**
     * Método para obter o gerenciador de entidade.
     *
     * @return o gerenciador de entidade
     */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Método para encontrar todos os produtos.
     *
     * @return uma lista de produtos
     */
    @Override
    public List<Produto> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Produto.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    // Outros métodos de negócios podem ser implementados aqui.
}
