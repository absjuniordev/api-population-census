package com.absjunior.domain.service;

import com.absjunior.domain.model.Client;

public interface ClientService {
	
	Iterable<Client> findAll();
	
	Client findById(Long id);
	
	void insert(Client client);
	
	void update(Long id, Client client);
	
	void delete(Long id);

}
