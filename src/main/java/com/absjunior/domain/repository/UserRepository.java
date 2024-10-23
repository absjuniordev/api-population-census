package com.absjunior.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.absjunior.domain.model.Client;

@Repository
public interface UserRepository extends CrudRepository<Client, Long> {

}
