package br.com.store.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.store.model.Categoria;
import br.com.store.model.Pedido;
import br.com.store.model.Produto;
import br.com.store.model.Relatorio;
import br.com.store.vo.RelatorioVendasVo;

public class PedidoDao {
	
	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<RelatorioVendasVo> relatorioDeVendas(){
		String jpql = "SELECT new br.com.store.vo.RelatorioVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioVendasVo.class)
				.getResultList();
	}
	

}
