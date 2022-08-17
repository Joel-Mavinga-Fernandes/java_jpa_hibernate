package br.com.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.store.model.Categoria;
import br.com.store.model.Pedido;
import br.com.store.model.Produto;

public class PedidoDao {
	
	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Pedido pedido) {
		this.em.persist(pedido);
	}

}
