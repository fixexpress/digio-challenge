package com.digio.challenge.api.service;

import com.digio.challenge.api.model.Cliente;
import com.digio.challenge.api.model.Compra;
import com.digio.challenge.api.model.Produto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompraService {

    /**
     * Ordena todas as compras por quantidade em ordem decrescente.
     *
     * @param compras Lista de compras.
     * @return Lista de compras ordenadas.
     */
    public List<Compra> ordenarCompras(List<Compra> compras) {
        return compras.stream()
                .sorted(Comparator.comparingInt(Compra::getQuantidade).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Busca a maior compra em termos de quantidade feita no ano especificado.
     *
     * @param compras Lista de compras.
     * @param ano     Ano para buscar a maior compra.
     * @return A maior compra encontrada no ano.
     */
    public Compra buscarMaiorCompra(List<Compra> compras, int ano) {
        return compras.stream()
                .filter(compra -> compra.getCliente() != null) 
                .filter(compra -> compra.getCliente().getCpf() != null)
                .max(Comparator.comparingInt(Compra::getCodigoProduto)) 
                .orElse(null);
    }

    /**
     * Calcula os clientes fiéis, que são aqueles com mais compras registradas.
     *
     * @param compras Lista de compras.
     * @return Lista de clientes fiéis.
     */
    public List<Cliente> calcularClientesFieis(List<Compra> compras) {
        Map<Cliente, Long> clienteCompraCounts = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente, Collectors.counting()));

        long maxCompras = clienteCompraCounts.values().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);

        return clienteCompraCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCompras)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Recomenda um produto com base no histórico de compras do cliente identificado pelo CPF.
     *
     * @param compras Lista de compras.
     * @param cpf     CPF do cliente para gerar a recomendação
     * @return Produto recomendado, se disponível.
     */
    public Produto recomendarProduto(List<Compra> compras, String cpf) {
        return compras.stream()
                .filter(compra -> compra.getCliente() != null && compra.getCliente().getCpf().equals(cpf))
                .max(Comparator.comparingInt(Compra::getQuantidade))
                .map(compra -> new Produto(
                        compra.getCodigoProduto(),
                        "Recomendado",
                        0.0, 
                        "N/A", // Safra desconhecida
                        0 // Ano desconhecido
                ))
                .orElse(null);
    }
}
