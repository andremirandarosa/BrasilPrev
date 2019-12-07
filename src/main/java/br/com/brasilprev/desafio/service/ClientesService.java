package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Clientes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {
    
    @Autowired
    AppService appService;
    
    //==========================================================================
    
    public Optional<Clientes> cria(String nome, String senha, String email, String rua, String bairro, String cidade, String estado, String cep){

        Clientes p = appService.salvaCliente(nome, senha, email, rua, bairro, cidade, estado, cep);

        if(p != null)
            return Optional.of(p);
        
        return Optional.empty();
    }
  
    public Optional<Clientes> altera(Long idCliente, String nome, String senha, String email, String rua, String bairro, String cidade, String estado, String cep){

        Optional<Clientes> op_cliente = appService.getClientesRepository().findById(idCliente);

        if(op_cliente.isPresent()){

            Clientes c = op_cliente.get();
            c.setNome(nome);
            c.setSenha(senha);
            c.setEmail(email);
            c.setRua(rua);
            c.setBairro(bairro);
            c.setCidade(cidade);
            c.setEstado(estado);
            c.setCep(cep);

            c = appService.getClientesRepository().save(c);

            if(c != null)
                return Optional.of(c);
        }
        
        return Optional.empty();
    }
    
    public Optional<Clientes> busca(Long idCliente){

        return appService.getClientesRepository().findById(idCliente);
    }
    
    public Optional<List<Clientes>> lista(){

        Iterable<Clientes> it = appService.getClientesRepository().findAll();
        
        if(it != null){
            
            List<Clientes> list = new ArrayList();
            it.forEach(list::add);
    
            return Optional.of(list);
        }
        
        return Optional.empty();
    }

    public void remove(Long idCliente){

        appService.getClientesRepository().deleteById(idCliente);
    }
}
