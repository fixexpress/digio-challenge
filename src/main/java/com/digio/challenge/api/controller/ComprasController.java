package com.digio.challenge.api.controller;

import com.digio.challenge.api.model.Cliente;
import com.digio.challenge.api.model.Compra;
import com.digio.challenge.api.model.Produto;
import com.digio.challenge.api.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComprasController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CompraService compraService;

    @GetMapping("/compras")
    @Operation(summary = "Obtém a lista de compras ordenada")
    public List<Compra> getCompras() {
        @SuppressWarnings("unchecked")
		List<Compra> compras = (List<Compra>) redisTemplate.opsForValue().get("compras");
        if (compras == null) {
            throw new IllegalStateException("As compras não foram carregadas no cache.");
        }
        return compraService.ordenarCompras(compras);
    }

    @GetMapping("/maior-compra/{ano}")
    @Operation(summary = "Obtém a maior compra do ano")
    public Compra getMaiorCompra(@PathVariable @Parameter(description = "Ano da compra") int ano) {
        @SuppressWarnings("unchecked")
		List<Compra> compras = (List<Compra>) redisTemplate.opsForValue().get("compras");
        if (compras == null) {
            throw new IllegalStateException("As compras não foram carregadas no cache.");
        }
        return compraService.buscarMaiorCompra(compras, ano);
    }

    @GetMapping("/clientes-fieis")
    @Operation(summary = "Obtém a lista de clientes fiéis")
    public List<Cliente> getClientesFieis() {
        @SuppressWarnings("unchecked")
		List<Compra> compras = (List<Compra>) redisTemplate.opsForValue().get("compras");
        if (compras == null) {
            throw new IllegalStateException("As compras não foram carregadas no cache.");
        }
        return compraService.calcularClientesFieis(compras);
    }

    @GetMapping("/recomendacao/{cpf}")
    @Operation(summary = "Recomenda um produto com base no CPF do cliente")
    public Produto getRecomendacao(@PathVariable @Parameter(description = "CPF do cliente") String cpf) {
        @SuppressWarnings("unchecked")
		List<Compra> compras = (List<Compra>) redisTemplate.opsForValue().get("compras");
        if (compras == null) {
            throw new IllegalStateException("As compras não foram carregadas no cache.");
        }
        return compraService.recomendarProduto(compras, cpf);
    }
}
