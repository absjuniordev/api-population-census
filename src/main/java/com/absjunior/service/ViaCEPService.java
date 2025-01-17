package com.absjunior.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.absjunior.domain.model.Address;
/**
 * Client HTTP, criado via <b>OpenFeign</b>, para o consumo da API do
 * <b>ViaCEP</b>.
 * 
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * @author absjuniordev
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCEPService {

	@GetMapping("/{cep}/json/")
	Address addressConsult(@PathVariable("cep") String cep);
}