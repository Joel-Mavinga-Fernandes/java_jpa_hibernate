package br.com.store.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.store.vo.RelatorioVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(42l);
		Produto produto3 = produtoDao.buscarPorId(47l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido, produto3));

		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		List<RelatorioVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
		
//		BigDecimal valor = pedidoDao.valorTotalVendido();
//		System.out.println(valor);
		
		//List<Produto> produto1 = produtoDao.findByName("PlayStation");
		
		
		

	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria games = new Categoria("VIDEO GAME");
		Categoria informatica = new Categoria("INFORMAICA");

		Produto celular = new Produto("IPhone", "Pro Max", new BigDecimal(1220), celulares);
		Produto game = new Produto("PlayStation", "PS5", new BigDecimal(500), games);
		Produto infor = new Produto("Mac", "Pro", new BigDecimal(3200), informatica);
		

		
		Cliente cliente = new Cliente("Jay", "123456");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(games);
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(game);
		produtoDao.cadastrar(infor);
		clienteDao.cadastrar(cliente);
		em.flush();
		em.clear();
	}


}
