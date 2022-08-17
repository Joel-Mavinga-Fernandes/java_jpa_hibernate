package br.com.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.store.model.Categoria;
import br.com.store.model.Cliente;
import br.com.store.model.Pedido;
import br.com.store.model.Produto;

public class ClienteDao {
	
	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}

}
