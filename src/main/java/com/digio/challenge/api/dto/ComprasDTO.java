package com.digio.challenge.api.dto;

import com.digio.challenge.api.model.Produto;

public class ComprasDTO {
    private String nomeCliente;
    private String cpfCliente;
    private int quantidadeCompras;
    private double valorTotalCompras;
    private Produto produto;

	public ComprasDTO(String nomeCliente, String cpfCliente, Produto produto, int quantidadeCompras,
			double valorTotalCompras) {
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.setProduto(produto);
		this.quantidadeCompras = quantidadeCompras;
		this.valorTotalCompras = valorTotalCompras;
	}


	public ComprasDTO() {
		// TODO Auto-generated constructor stub
	}

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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


	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
