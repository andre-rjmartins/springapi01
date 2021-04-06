package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.models.ClientePutRequest;
import br.com.cotiinformatica.models.ClientePutResponse;
import br.com.cotiinformatica.services.ClienteService;

@Controller
public class ClientePutController {
	
	@Autowired
	private ClienteService service;
	
	@CrossOrigin //Habilitando a política de CORS para este serviço
	@RequestMapping(value = "/api/clientes", method = RequestMethod.PUT)
	@ResponseBody
	public ClientePutResponse putCliente(@RequestBody ClientePutRequest request) {
		
		ClientePutResponse response = new ClientePutResponse();
		
		try {
			
			//buscar o cliente 
			Cliente cliente = service.findById(request.getIdCliente());
			
			//verificar se o cliente foi encontrado..
			if(cliente != null) {
				
				//modificando os dados do cliente..
				cliente.setNome(request.getNome());
				
				service.saveOrUpdate(cliente); //atualizando
				
				response.setMensagem("Cliente atualizado com sucesso.");
			}
			else {
				throw new Exception("Cliente não foi encontrado.");
			}
			
		}
		catch(Exception e) {
			response.setMensagem("Erro: " + e.getMessage());
		}
		
		return response;
	}
}
