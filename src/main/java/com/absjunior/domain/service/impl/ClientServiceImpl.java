package com.absjunior.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.absjunior.domain.model.Address;
import com.absjunior.domain.model.Client;
import com.absjunior.domain.repository.AddressRepositiry;
import com.absjunior.domain.repository.ClientRepository;
import com.absjunior.domain.service.ClientService;
import com.absjunior.domain.service.ViaCEPService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepositiry addressRepositiry;

	@Autowired
	private ViaCEPService viaCEPService;

	@Override
	public Iterable<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.get();
	}

	@Override
	public void insert(Client client) {
		saveCustomerWithZipCode(client);

	}

	@Override
	public void update(Long id, Client client) {
		Optional<Client> clientDB = clientRepository.findById(id);
		if (clientDB.isPresent()) {
			saveCustomerWithZipCode(client);

		}
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);

	}

	private void saveCustomerWithZipCode(Client client) {
		String cep = client.getAddress().getCep();
		Address address = addressRepositiry.findById(cep).orElseGet(() -> {
			Address newAddress = viaCEPService.addressConsult(cep);
			addressRepositiry.save(newAddress);
			return newAddress;
		});

		client.setAddress(address);
		clientRepository.save(client);
	}
}
