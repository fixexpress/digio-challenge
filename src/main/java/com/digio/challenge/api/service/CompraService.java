package com.digio.challenge.api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digio.challenge.api.dto.ClientesFieisDTO;
import com.digio.challenge.api.dto.ComprasDTO;
import com.digio.challenge.api.dto.MaiorCompraDTO;
import com.digio.challenge.api.model.Cliente;
import com.digio.challenge.api.model.Compra;
import com.digio.challenge.api.model.Produto;

@Service
public class CompraService {

	/**
	 * Ordena todas as compras por quantidade em ordem decrescente.
	 * 
	 * instruções:
	 * 
	 * Retornar uma lista das compras ordenadas de forma crescente por valor, deve
	 * conter o nome dos clientes, cpf dos clientes, dado dos produtos, quantidade
	 * das compras e valores totais de cada compra.
	 *
	 * @param compras Lista de compras.
	 * @return Lista de compras ordenadas.
	 */

	public List<ComprasDTO> listComprasOrdenadas(List<Cliente> clientes) {
		List<ComprasDTO> listComprasDTO = new ArrayList<>();

		for (Cliente cliente : clientes) {
			for (Compra compra : cliente.getCompras()) {
				ComprasDTO comprasDTO = new ComprasDTO();
				comprasDTO.setCpfCliente(cliente.getCpf());
				comprasDTO.setNomeCliente(cliente.getNome());
				comprasDTO.setQuantidadeCompras(compra.getQuantidade());
				comprasDTO.setValorTotalCompras(compra.getQuantidade() * compra.getProduto().getPreco());
				comprasDTO.setProduto(compra.getProduto());

				listComprasDTO.add(comprasDTO);
			}
		}

		return listComprasDTO.stream().sorted(Comparator.comparingDouble(ComprasDTO::getValorTotalCompras))
				.collect(Collectors.toList());
	}

	/**
	 * Retorna a maior compra de um determinado ano.
	 * 
	 * instruções:
	 * 
	 * Retornar a maior compra do ano informando os dados da compra
	 * disponibilizados, deve ter o nome do cliente, cpf
	 * 
	 * @param clientes Lista de clientes.
	 * @param ano      Ano da compra.
	 * @return Maior compra do ano
	 * 
	 */
	public MaiorCompraDTO getMaiorCompra(List<Cliente> clientes, int ano) {
		MaiorCompraDTO maiorCompraDTO = null;
		double maxValorTotal = 0;

		for (Cliente cliente : clientes) {
			for (Compra compra : cliente.getCompras()) {
				Produto produto = compra.getProduto();
				if (produto != null && produto.getAno_compra() == ano) {
					double valorTotal = compra.getQuantidade() * produto.getPreco();
					if (valorTotal > maxValorTotal) {
						maxValorTotal = valorTotal;
						maiorCompraDTO = new MaiorCompraDTO();
						maiorCompraDTO.setCpfCliente(cliente.getCpf());
						maiorCompraDTO.setNomeCliente(cliente.getNome());
						maiorCompraDTO.setQuantidadeCompras(compra.getQuantidade());
						maiorCompraDTO.setValorTotalCompras(valorTotal);
						
						maiorCompraDTO.setProduto(produto);
					}
				}
			}
		}

		return maiorCompraDTO;
	}
	
	/**
	 * Retorna os 3 clientes mais fiéis.
	 * 
	 * instruções:
	 * 
	 * Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras
	 * recorrentes com maiores valores.
	 * 
	 * @param clientes Lista de clientes.
	 * @return Top 3 clientes mais fiéis.
	 */
	public ClientesFieisDTO calcularClientesFieis(List<Cliente> clientes) {
		ClientesFieisDTO clientesFieisDTO = new ClientesFieisDTO();
		List<Cliente> clientesFieis = new ArrayList<>();
		for (Cliente cliente : clientes) {
			double valorTotalCompras = 0;
			for (Compra compra : cliente.getCompras()) {
				Produto produto = compra.getProduto();
				if (produto != null) {
					valorTotalCompras += compra.getQuantidade() * produto.getPreco();
				}
			}
			cliente.setValorTotalCompras(valorTotalCompras);
		}
		clientesFieis = clientes.stream().sorted(Comparator.comparingDouble(Cliente::getValorTotalCompras).reversed())
				.limit(3).collect(Collectors.toList());
		
		clientesFieisDTO.setClientes(clientesFieis);
		return clientesFieisDTO;
	}
	
}
