package com.absjunior.domain.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositiry  extends CrudRepository<Address, String>{

}
