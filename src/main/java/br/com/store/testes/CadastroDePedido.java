package br.com.store.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.store.dao.CategoriaDao;
import br.com.store.dao.ClienteDao;
import br.com.store.dao.PedidoDao;
import br.com.store.dao.ProdutoDao;
import br.com.store.model.Categoria;
import br.com.store.model.Cliente;
import br.com.store.model.ItemPedido;
import br.com.store.model.Pedido;
import br.com.store.model.Produto;
import br.com.store.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		BigDecimal valor = pedidoDao.valorTotalVendido();
		System.out.println(valor);
		
		

	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("IPhone", "Pro Max", new BigDecimal(1220), celulares);
		
		Cliente cliente = new Cliente("Jay", "123456");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		em.flush();
		em.clear();
	}


}
