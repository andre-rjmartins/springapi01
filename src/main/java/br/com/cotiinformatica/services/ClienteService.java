package br.com.cotiinformatica.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.interfaces.IClienteRepository;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	private IClienteRepository repository;
	
	public void saveOrUpdate(Cliente cliente) throws Exception{
		repository.save(cliente);
	}
	
	public void delete(Cliente cliente) throws Exception{
		repository.delete(cliente);
	}
	
	public List<Cliente> findAll() throws Exception{
		return (List<Cliente>) repository.findAll();
	}
	
	public Cliente findById(Integer id) throws Exception{
		return repository.findById(id).get();
	}
	
	public Cliente findByCpf(String cpf) throws Exception{
		
		List<Cliente> result = repository.findByCpf(cpf);
		
		if(result != null && result.size() == 1)
			return result.get(0);
			
		return null;
	}
	
	public Cliente findByEmail(String email) throws Exception{
		
		List<Cliente> result = repository.findByEmail(email);
		
		if(result != null && result.size() == 1)
			return result.get(0);
		
		return null;
	}
}
