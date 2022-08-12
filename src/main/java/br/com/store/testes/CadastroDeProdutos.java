package br.com.store.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.store.dao.CategoriaDao;
import br.com.store.dao.ProdutoDao;
import br.com.store.model.Categoria;
import br.com.store.model.Produto;
import br.com.store.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		 List<Produto> all = produtoDao.findAll();
		 all.forEach(p1 -> System.out.println(p.getNome()));
		 
		 List<Produto> findByName = produtoDao.findByName("IPhone");
		 findByName.forEach(p2 -> System.out.println(p.getNome()));
		 
		 List<Produto> findByCategoriaName = produtoDao.findByCategoriaName("CELULARES");
		 findByCategoriaName.forEach(p3 -> System.out.println(p.getNome()));
		 
		 BigDecimal precoDoProduto = produtoDao.findPriceByProductName("IPhone");
		 System.out.println("Preço do produto: " + precoDoProduto);
		
		

		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("IPhone", "Pro Max", new BigDecimal(1220), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.flush();
		em.clear();
	}

}
