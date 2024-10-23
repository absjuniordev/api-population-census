package com.absjunior.domain.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.absjunior.domain.model.Address;


@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCEPService {

	@GetMapping("/{cep}/json/")
	Address addressConsult (@PathVariable("cep") String cep);
}
