package br.com.store.model;

import java.util.Date;

public class Relatorio {
	
	private String nome;
	private int qtd;
	private Date ultimaVenda;
	
	public Relatorio () {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Date getUltimaVenda() {
		return ultimaVenda;
	}

	public void setUltimaVenda(Date ultimaVenda) {
		this.ultimaVenda = ultimaVenda;
	}
	
	

}
