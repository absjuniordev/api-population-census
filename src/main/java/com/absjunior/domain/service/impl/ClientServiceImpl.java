package com.absjunior.domain.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.absjunior.domain.model.Address;
import com.absjunior.domain.model.Client;
import com.absjunior.domain.repository.AddressRepository;
import com.absjunior.domain.repository.ClientRepository;
import com.absjunior.domain.service.ClientService;
import com.absjunior.domain.service.ViaCEPService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author absjuniordev
 */
@Service
public class ClientServiceImpl implements ClientService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClientRepository clienteRepository;
	@Autowired
	private AddressRepository enderecoRepository;
	@Autowired
	private ViaCEPService viaCEPService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Client> findAll() {
		// Buscar todos os Clientes.
		return clienteRepository.findAll();
	}

	@Override
	public Client findById(Long id) {
		// Buscar Cliente por ID.
		Optional<Client> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void insert(Client cliente) {
		saveClientWitchZipCode(cliente);
	}

	@Override
	public void update(Long id, Client client) {
		// Buscar Cliente por ID, caso exista:
		Optional<Client> clientBd = clienteRepository.findById(id);
		if (clientBd.isPresent()) {
			saveClientWitchZipCode(client);
		}
	}

	@Override
	public void delete(Long id) {
		// Deletar Cliente por ID.
		clienteRepository.deleteById(id);
	}

	private void saveClientWitchZipCode(Client client) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = client.getAddress().getCep();
		Address endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Address newAddress = viaCEPService.addressConsult(cep);
			enderecoRepository.save(newAddress);
			return newAddress;
		});
		client.setAddress(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		clienteRepository.save(client);
	}

}