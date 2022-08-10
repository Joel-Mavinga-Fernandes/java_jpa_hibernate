package br.com.store.dao;

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

}
