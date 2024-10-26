package com.absjunior.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.absjunior.domain.model.Address;
import com.absjunior.domain.model.Client;
import com.absjunior.domain.repository.AddressRepository;
import com.absjunior.domain.repository.ClientRepository;
import com.absjunior.service.ClientService;
import com.absjunior.service.ViaCEPService;


@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	private ClientRepository clienteRepository;
	@Autowired
	private AddressRepository enderecoRepository;
	@Autowired
	private ViaCEPService viaCEPService;
	
	@Override
	public Iterable<Client> findAll() {
		
		return clienteRepository.findAll();
	}

	@Override
	public Client findById(Long id) {
		Optional<Client> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public Client insert(Client cliente) {
		 saveClientWitchZipCode(cliente);
		return cliente;
	}

	@Override
	public void update(Long id, Client client) {

		Optional<Client> clientBd = clienteRepository.findById(id);
		if (clientBd.isPresent()) {
			saveClientWitchZipCode(client);
		}
	}

	@Override
	public void delete(Long id) {
	
		clienteRepository.deleteById(id);
	}

	private void saveClientWitchZipCode(Client client) {
		
		String cep = client.getAddress().getCep();
		Address endereco = enderecoRepository.findById(cep).orElseGet(() -> {
	
			Address newAddress = viaCEPService.addressConsult(cep);
			enderecoRepository.save(newAddress);
			return newAddress;
		});
		client.setAddress(endereco);
		
		clienteRepository.save(client);
	}

}