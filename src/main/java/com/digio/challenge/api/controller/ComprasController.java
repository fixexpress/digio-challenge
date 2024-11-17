package com.digio.challenge.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digio.challenge.api.dto.ClientesFieisDTO;
import com.digio.challenge.api.dto.ComprasDTO;
import com.digio.challenge.api.dto.MaiorCompraDTO;
import com.digio.challenge.api.model.Cliente;
import com.digio.challenge.api.model.Produto;
import com.digio.challenge.api.service.CompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api")
public class ComprasController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private CompraService compraService;

	/**
	 * GET: /compras - Retornar uma lista das compras ordenadas de forma crescente
	 * por valor, deve conter o nome dos clientes, cpf dos clientes, dado dos
	 * produtos, quantidade das compras e valores totais de cada compra.
	 * 
	 * @return
	 */
	@GetMapping("/compras")
	@Operation(summary = "Obt√©m a lista de compras ordenada")
	public List<ComprasDTO> getCompras() {
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = (List<Cliente>) redisTemplate.opsForValue().get("clientes");
		if (clientes == null) {
			throw new IllegalStateException("Os clientes e suas compras n√£o foram carregadas no cache.");
		}
		return compraService.listComprasOrdenadas(clientes);
	}

	/**
	 * GET: /maior-compra/ano - (Exemplo: /maior_compra/2016) - 
	 * Retornar a maior compra do ano informando os dados da compra disponibilizados, 
	 * deve ter o nome do cliente, cpf
	 * 
	 * @param ano
	 * @return
	 */
	@GetMapping("/maior-compra/{ano}")
	@Operation(summary = "Obt√©m a maior compra do ano")
	public MaiorCompraDTO getMaiorCompra(@PathVariable @Parameter(description = "Ano da compra") int ano) {

		@SuppressWarnings("unchecked")
		List<Cliente> clientes = (List<Cliente>) redisTemplate.opsForValue().get("clientes");
		if (clientes == null) {
			throw new IllegalStateException("As compras n√£o foram carregadas no cache.");
		}
		return compraService.getMaiorCompra(clientes, ano);
	}

	
	/*
	 * GET: /clientes-fieis - Retornar o Top 3 clientes mais fieis, 
	 * clientes que possuem mais compras recorrentes com maiores valores. 
	 */
	
	@GetMapping("/clientes-fieis")
	@Operation(summary = "Obt√©m o Top 3 clientes mais fieis")
	public ClientesFieisDTO getClientesFieis() {
        @SuppressWarnings("unchecked")
        List<Cliente> clientes = (List<Cliente>) redisTemplate.opsForValue().get("clientes");
        if (clientes == null) {
            throw new IllegalStateException("As compras n√£o foram carregadas no cache.");
        }
        return compraService.calcularClientesFieis(clientes);
    }
	
	
/*
 * GET: /recomendacao/cliente/tipo 
 * Retornar uma recomendaÁ„o de vinho baseado nos tipos de vinho que o cliente mais compra.
 * 
 *  @FIXME: O endpoint /recomendacao/cliente/tipo n„o foi implementado, pois o contexto da aplicaÁ„o n„o possui informaÁıes suficientes para tal.
 *  O contexto da aplicaÁ„o n„o possui informaÁıes suficientes para implementar a recomendaÁ„o de vinho baseado nos tipos de vinho que o cliente mais compra.
 *  
 */
	@GetMapping("/recomendacao/{cpfCliente}")
	@Operation(summary = "Obt√©m a recomenda√ß√£o de vinho baseado nos tipos de vinho que o cliente mais compra")
	public Produto getRecomendacao(@PathVariable @Parameter(description = "CPF do cliente") String cpfCliente) {
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = (List<Cliente>) redisTemplate.opsForValue().get("clientes");
		if (clientes == null) {
			throw new IllegalStateException("As compras n√£o foram carregadas no cache.");
		}
		return compraService.getRecomendacao(clientes, cpfCliente);
	}

}
