package br.com.store.testes;

import java.math.BigDecimal;

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
		
		celulares = em.merge(celulares);
		celulares.setNome("Iphone 13");
		em.flush();
		em.clear();
		
		em.remove(celulares);
		em.flush();
		
	}

}
