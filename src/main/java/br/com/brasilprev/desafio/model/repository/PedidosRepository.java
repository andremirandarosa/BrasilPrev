package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Pedidos;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public interface PedidosRepository extends CrudRepository<Pedidos, Long>{}
