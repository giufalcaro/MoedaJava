package com.quotacao.Moeadas.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Moeda")
public class Moeda {
    @MongoId
    private String id;

    private String nome;
    private Double valor;
    

    public Moeda() {

    }
    

    public Moeda(String nome,Double valor) {
        this.nome = nome;
        this.valor = valor;
    }


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	@Override
	public String toString() {
		return "Moeda [id=" + id + ", nome=" + nome + ", valor=" + valor + "]";
	}
    

}
