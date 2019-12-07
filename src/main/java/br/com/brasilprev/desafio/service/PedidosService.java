package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidosService {
    
    @Autowired
    AppService appService;
    
    @Autowired
    ClientesService clientesService;
    
    //==========================================================================
    
    public Optional<Pedidos> cria(Long idCliente, String data, String status, String sessao){

        Optional<Clientes> op_cliente = clientesService.busca(idCliente);
        
        if(op_cliente.isPresent()){
        
            Pedidos c = appService.salvaPedido(op_cliente.get(), data, status, sessao);
            
            if(c != null)
                return Optional.of(c);
        }
        
        return Optional.empty();
    }
  
    public Optional<Pedidos> altera(Long idPedido, Long idCliente, String data, String status, String sessao){

        Optional<Pedidos> op_pedido = appService.getPedidosRepository().findById(idPedido);

        if(op_pedido.isPresent()){

            Optional<Clientes> op_clientes = clientesService.busca(idCliente);

            if(op_clientes.isPresent()){

                Pedidos p = op_pedido.get();
                p.setIdCliente(op_clientes.get());
                p.setData(data);
                p.setStatus(status);
                p.setSessao(sessao);
                        
                p = appService.getPedidosRepository().save(p);

                if(p != null)
                    return Optional.of(p);
            }
        }
        
        return Optional.empty();
    }
    
    public Optional<Pedidos> busca(Long idPedido){

        return appService.getPedidosRepository().findById(idPedido);
    }
    
    public Optional<List<Pedidos>> lista(){

        Iterable<Pedidos> it = appService.getPedidosRepository().findAll();
        
        if(it != null){
            
            List<Pedidos> list = new ArrayList();
            it.forEach(list::add);
    
            return Optional.of(list);
        }
        
        return Optional.empty();
    }

    public void remove(Long idPedido){

        appService.getPedidosRepository().deleteById(idPedido);
    }
}
