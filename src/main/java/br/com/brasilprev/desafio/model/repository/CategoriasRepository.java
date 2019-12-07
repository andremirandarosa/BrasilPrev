package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Categorias;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
public interface CategoriasRepository extends CrudRepository<Categorias, Long>{}
