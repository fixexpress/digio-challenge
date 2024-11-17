package com.digio.challenge.api.model;

public class Compra {
    
	private Produto produto;
    private int quantidade;
    private int codigo;
    
    public Compra() {
    	
    }
    
    public Compra(int codigo, int quantidade) {
        this.setCodigo(codigo);
        this.quantidade = quantidade;
    }

    public Compra(Produto produto, int quantidade) {
        this.setProduto(produto);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



}


