package com.quotacao.Moeadas.controller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.quotacao.Moeadas.model.Moeda;
import com.quotacao.Moeadas.model.QuotacaoMoedaResponse;
import com.quotacao.Moeadas.repository.MoedaRepository;

import reactor.core.publisher.Flux;
import services.JsonBodyHandler;

@CrossOrigin(origins = "http://localhost:5577") //@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MoedaController {
	
	@Autowired
	MoedaRepository moedaRepository;
	
	@PostMapping("/moeda")
	public ResponseEntity<Moeda> createMoeda(@RequestBody Moeda moeda) {
		try {
			Moeda _moeda = moedaRepository.save(new Moeda(moeda.getNome(), moeda.getValor()));
		     
			return new ResponseEntity<>(_moeda, HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	  @GetMapping("/quotacao")
	  public ResponseEntity<Flux<String>> getTutorialById() {
			try {
 
				WebClient webClient = WebClient.create("https://economia.awesomeapi.com.br");

				Flux<String> moedaResponse =  webClient.get()
						.uri("/last/EUR-BRL,BTC-BRL,BRL-CHF,BRL-DKK,BRL-JMD,BRL-LKR,BRL-MAD,BRL-NOK,BRL-RON,BRL-RUB,BRL-GBP")
						.retrieve()
						.bodyToFlux(String.class);
				
				return new ResponseEntity<>(moedaResponse, HttpStatus.CREATED);
				
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

}
