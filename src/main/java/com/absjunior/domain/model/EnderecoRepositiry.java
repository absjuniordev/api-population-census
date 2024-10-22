package com.absjunior.domain.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositiry  extends CrudRepository<Endereco, String>{

}
