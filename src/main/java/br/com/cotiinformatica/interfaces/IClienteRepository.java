package br.com.cotiinformatica.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Integer>{
	
	@Query("select c from Cliente c where c.cpf = :param")
	public List<Cliente> findByCpf(@Param("param") String cpf);
	
	@Query("select c from Cliente c where c.email = :param")
	public List<Cliente> findByEmail(@Param("param")String email);
}
