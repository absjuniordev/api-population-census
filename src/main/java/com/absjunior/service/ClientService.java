package com.absjunior.service;

import com.absjunior.domain.model.Client;

public interface ClientService {
	
	Iterable<Client> findAll();
	
	Client findById(Long id);
	
	Client insert(Client client);
	
	void update(Long id, Client client);
	
	void delete(Long id);

}
