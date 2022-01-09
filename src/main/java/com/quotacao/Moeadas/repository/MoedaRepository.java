package com.quotacao.Moeadas.repository;


import com.quotacao.Moeadas.model.Moeda;


import org.springframework.data.mongodb.repository.MongoRepository;



public interface MoedaRepository extends MongoRepository<Moeda, String> {
	

}