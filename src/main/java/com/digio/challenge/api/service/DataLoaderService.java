package com.digio.challenge.api.service;

import com.digio.challenge.api.model.Cliente;
import com.digio.challenge.api.model.Compra;
import com.digio.challenge.api.model.Produto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoaderService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    private final String produtosUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";
    private final String clientesUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";

    public DataLoaderService(RestTemplate restTemplate, ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void carregarDados() {
        carregarProdutos();
        carregarClientesECompras();
    }

    private void carregarProdutos() {
        try {
            String json = restTemplate.getForObject(produtosUrl, String.class);
            JsonNode rootNode = objectMapper.readTree(json);

            List<Produto> produtos = new ArrayList<>();
            for (JsonNode node : rootNode) {
                Produto produto = objectMapper.treeToValue(node, Produto.class);
                produtos.add(produto);
            }

            // Cacheando os produtos no Redis
            redisTemplate.opsForValue().set("produtos", produtos);
            System.out.println("Produtos carregados com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void carregarClientesECompras() {
        try {
            String json = restTemplate.getForObject(clientesUrl, String.class);
            JsonNode rootNode = objectMapper.readTree(json);

            List<Cliente> clientes = new ArrayList<>();
            List<Compra> compras = new ArrayList<>();

            for (JsonNode node : rootNode) {
                Cliente cliente = objectMapper.treeToValue(node, Cliente.class);

                // Extraindo as compras do cliente
                if (node.has("compras")) {
                    for (JsonNode compraNode : node.get("compras")) {
                        Compra compra = objectMapper.treeToValue(compraNode, Compra.class);
                        compra.setCliente(cliente);
                        compras.add(compra);
                    }
                }

                clientes.add(cliente);
            }

            // Cacheando os clientes e compras no Redis
            redisTemplate.opsForValue().set("clientes", clientes);
            redisTemplate.opsForValue().set("compras", compras);

            System.out.println("Clientes e compras carregados com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao carregar clientes e compras: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Produto> getProdutos() {
        return (List<Produto>) redisTemplate.opsForValue().get("produtos");
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> getClientes() {
        return (List<Cliente>) redisTemplate.opsForValue().get("clientes");
    }

    @SuppressWarnings("unchecked")
    public List<Compra> getCompras() {
        return (List<Compra>) redisTemplate.opsForValue().get("compras");
    }
}
