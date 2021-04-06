package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.models.ClienteDeleteResponse;
import br.com.cotiinformatica.services.ClienteService;

@Controller
public class ClienteDeleteController {
	
	@Autowired
	private ClienteService service;
	
	@CrossOrigin //Habilitando a política de CORS para este serviço
	@RequestMapping(value = "/api/clientes/{idCliente}", method = RequestMethod.DELETE)
	@ResponseBody
	public ClienteDeleteResponse deleteCliente(@PathVariable("idCliente") Integer idCliente) {
		
		ClienteDeleteResponse response = new ClienteDeleteResponse();
		
		try {
			
			//buscar o cliente no banco de dados através do id
			Cliente cliente = service.findById(idCliente);
			
			if(cliente != null) {
				service.delete(cliente);
				response.setMensagem("Cliente excluído com sucesso.");
			}
			else {
				throw new Exception("Cliente não encontrado.");
			}
		}
		catch(Exception e) {
			response.setMensagem("Erro: " + e.getMessage());
		}
		
		return response;
	}
}
