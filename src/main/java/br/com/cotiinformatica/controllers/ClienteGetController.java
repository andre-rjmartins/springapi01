package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.models.ClienteGetResponse;
import br.com.cotiinformatica.services.ClienteService;

@Controller
public class ClienteGetController {
	
	@Autowired
	private ClienteService service;
	
	@CrossOrigin //Habilitando a política de CORS para este serviço
	@RequestMapping(value = "/api/clientes", method = RequestMethod.GET)
	@ResponseBody
	public List<ClienteGetResponse> getClientes(){
		
		List<ClienteGetResponse> result = new ArrayList<ClienteGetResponse>();
		
		try {
			
			for(Cliente cliente : service.findAll()) {
				
				ClienteGetResponse response = new ClienteGetResponse();
				response.setIdCliente(cliente.getIdCliente());
				response.setNome(cliente.getNome());
				response.setCpf(cliente.getCpf());
				response.setEmail(cliente.getEmail());
				
				result.add(response);
			}
		}
		catch(Exception e) {
			System.out.println("Erro: " + e.getMessage()); //PROVISORIO
		}
		
		return result;
	}
	
	@CrossOrigin //Habilitando a política de CORS para este serviço
	@RequestMapping(value = "/api/clientes/{idCliente}", method = RequestMethod.GET)
	@ResponseBody
	public ClienteGetResponse getCliente(@PathVariable("idCliente") Integer idCliente){
				
		try {
			
			//buscando o cliente no banco de dados atraves do ID..
			Cliente cliente = service.findById(idCliente);
			
			//verificando se o cliente foi encontrado
			if(cliente != null) {
				
				ClienteGetResponse response = new ClienteGetResponse();
				
				response.setIdCliente(cliente.getIdCliente());
				response.setNome(cliente.getNome());
				response.setCpf(cliente.getCpf());
				response.setEmail(cliente.getEmail());
				
				return response;
			}
		}
		catch(Exception e) {
			System.out.println("Erro: " + e.getMessage()); //PROVISORIO
		}
		
		return null;
	}
}
