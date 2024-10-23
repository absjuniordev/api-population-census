package com.absjunior.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.absjunior.domain.model.Address;

@Repository
public interface AddressRepositiry  extends CrudRepository<Address, String>{

}
