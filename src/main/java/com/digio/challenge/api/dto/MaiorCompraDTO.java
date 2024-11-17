package com.digio.challenge.api.dto;

import com.digio.challenge.api.model.Produto;

public class MaiorCompraDTO {
	private String nomeCliente;
	private String cpfCliente;
	private int quantidadeCompras;
	private double valorTotalCompras;
	private Produto produto;

	public MaiorCompraDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public int getQuantidadeCompras() {
		return quantidadeCompras;
	}

	public void setQuantidadeCompras(int quantidadeCompras) {
		this.quantidadeCompras = quantidadeCompras;
	}

	public double getValorTotalCompras() {
		return valorTotalCompras;
	}

	public void setValorTotalCompras(double valorTotalCompras) {
		this.valorTotalCompras = valorTotalCompras;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
