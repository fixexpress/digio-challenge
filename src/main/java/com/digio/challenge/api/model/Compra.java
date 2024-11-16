package com.digio.challenge.api.model;

public class Compra {
    private int codigoProduto;
    private int quantidade;
    private Cliente cliente;

    public Compra(int codigoProduto, int quantidade, Cliente cliente) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.cliente= cliente;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}


