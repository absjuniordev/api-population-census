package com.absjunior.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.absjunior.domain.model.Client;
import com.absjunior.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;

import java.net.URI;

@RestController
@RequestMapping("clients")
public class ClientRestController {
	
	@Autowired
	private ClientService clientService;
	
	@Operation(summary = "Gets all users in the database")
	@GetMapping
	public ResponseEntity<Iterable<Client>> findAll(){
		return ResponseEntity.ok(clientService.findAll());
	}
	
	@Operation(summary = "Find a user by Id")
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable("id") Long id){
		return ResponseEntity.ok(clientService.findById(id));
	}
	
	@Operation(summary = "Insert a user into the databse")
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client client){
		var clientCreated = clientService.insert(client);	
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(clientCreated.getId())
				.toUri();
		return ResponseEntity.created(location).body(clientCreated);
	}
       
	
	@Operation(summary = "Update a user in the database")
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable("id") Long Id, @RequestBody Client client){
		clientService.update(Id, client);
		return ResponseEntity.ok(client);
	}
	
	@Operation(summary = "Delete a user in the database")
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> delete(@PathVariable("id") Long id){
		clientService.delete(id);
		return ResponseEntity.ok().build();		
	}
}
