package br.com.cotiinformatica.models;

//Modelo de dados que deverá ser enviado para a API
//no serviço de cadastro de cliente
public class ClientePostRequest {

	private String nome;
	private String email;
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
