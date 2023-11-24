package cadastroee.servlets;


import cadastroee.controller.exceptions.NonexistentEntityException;
import cadastroee.controller.exceptions.PreexistingEntityException;
import cadastroee.model.Produto;
import cadastroee.model.MovimentoCompra;
import cadastroee.model.MovimentoVenda;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ProdutoJpaController implements Serializable {

    private final EntityManagerFactory emf;

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (EntityExistsException e) {
            throw new PreexistingEntityException("Produto " + produto + " already exists.", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            throw new NonexistentEntityException("The produto with id " + produto.getIdProduto() + " no longer exists.", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            if (produto == null) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
            cq.select(cq.from(Produto.class));
            TypedQuery<Produto> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(cb.count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
