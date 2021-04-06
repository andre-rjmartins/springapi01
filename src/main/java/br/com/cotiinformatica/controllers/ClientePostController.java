package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.models.ClientePostRequest;
import br.com.cotiinformatica.models.ClientePostResponse;
import br.com.cotiinformatica.services.ClienteService;

@Controller
public class ClientePostController {
	
	@Autowired
	private ClienteService service;
	
	/*
	 * Método de serviço para cadastro do cliente (POST)
	 */
	@CrossOrigin //Habilitando a política de CORS para este serviço
	@RequestMapping(value = "/api/clientes", method = RequestMethod.POST)
	@ResponseBody
	public ClientePostResponse postCliente(@RequestBody ClientePostRequest request) {
		
		ClientePostResponse response = new ClientePostResponse();
		
		try {
			
			//verificar se o email do cliente já encontra-se cadastrado
			if(service.findByEmail(request.getEmail()) != null) {
				throw new Exception("O email informado já encontra-se cadastrado. Tente outro.");
			}
			
			//verificar se o cpf do cliente já encontra-se cadastrado
			if(service.findByCpf(request.getCpf()) != null) {
				throw new Exception("O CPF informado já encontra-se cadastrado. Tente outro.");
			}
			
			Cliente cliente = new Cliente();
			
			cliente.setNome(request.getNome());
			cliente.setEmail(request.getEmail());
			cliente.setCpf(request.getCpf());
			
			service.saveOrUpdate(cliente);
			
			response.setMensagem("Cliente cadastrado com sucesso.");
		}
		catch(Exception e) {
			response.setMensagem("Erro: " + e.getMessage());
		}
		
		return response;
	}
	
}
