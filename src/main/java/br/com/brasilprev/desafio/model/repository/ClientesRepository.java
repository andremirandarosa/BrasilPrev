package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Clientes;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public interface ClientesRepository extends CrudRepository<Clientes, Long>{}
