package br.com.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.store.model.Categoria;
import br.com.store.model.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Produto produto) {
		this.em.persist(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> findAll(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> findByName(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.nome= :nome ";
		return em.createQuery(jpql, Produto.class)
		.setParameter("nome", nome)
		.getResultList();
	}
	
	public List<Produto> findByCategoriaName(String nome){
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
		.setParameter("nome", nome)
		.getResultList();
	}
	
	public BigDecimal findPriceByProductName(String nome){
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome= :nome ";
		return em.createQuery(jpql, BigDecimal.class)
		.setParameter("nome", nome)
		.getSingleResult();
	}
	
	

}
