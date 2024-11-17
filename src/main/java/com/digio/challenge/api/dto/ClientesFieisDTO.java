
package com.digio.challenge.api.dto;

import java.util.List;
import com.digio.challenge.api.model.Cliente;

public class ClientesFieisDTO {

	private List<Cliente> clientes;

	public ClientesFieisDTO() {
		// Construtor para Jackson
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
