package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.PedidoItens;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import br.com.brasilprev.desafio.model.entity.Produtos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoItensService {
    
    @Autowired
    AppService appService;
    
    @Autowired
    ProdutosService produtosService;
    
    @Autowired
    PedidosService pedidosService;
    
    //==========================================================================
    
    public Optional<PedidoItens> cria(Long idProduto, Long idPedido, String produto, int quantidade, BigDecimal valor){

        Optional<Produtos> op_produto = produtosService.busca(idProduto);
        Optional<Pedidos> op_pedido = pedidosService.busca(idPedido);
        
        if((op_produto.isPresent()) && (op_pedido.isPresent())){
        
            PedidoItens pi = appService.salvaPedidoItem(op_produto.get(), op_pedido.get(), produto, quantidade, valor, valor.multiply(new BigDecimal(quantidade)));
            
            if(pi != null)
                return Optional.of(pi);
        }
        
        return Optional.empty();
    }
  
    public Optional<PedidoItens> altera(Long idPedidoItem, Long idProduto, Long idPedido, String produto, int quantidade, BigDecimal valor){

        Optional<PedidoItens> op_pedido_item = appService.getPedidoItensRepository().findById(idPedidoItem);

        if(op_pedido_item.isPresent()){

            Optional<Produtos> op_produto = produtosService.busca(idProduto);
            Optional<Pedidos> op_pedido = pedidosService.busca(idPedido);

            if((op_produto.isPresent()) && (op_pedido.isPresent())){

                PedidoItens pi = op_pedido_item.get();
                pi.setIdProduto(op_produto.get());
                pi.setIdPedido(op_pedido.get());
                pi.setProduto(produto);
                pi.setQuantidade(quantidade);
                pi.setValor(valor);
                pi.setSubtotal(valor.multiply(new BigDecimal(quantidade)));
                
                PedidoItens pi_saved = appService.getPedidoItensRepository().save(pi);

                if(pi_saved != null)
                    return Optional.of(pi_saved);
            }
        }
        
        return Optional.empty();
    }
    
    public Optional<PedidoItens> busca(Long idPedidoItem){

        return appService.getPedidoItensRepository().findById(idPedidoItem);
    }
    
    public Optional<List<PedidoItens>> lista(){

        Iterable<PedidoItens> it = appService.getPedidoItensRepository().findAll();
        
        if(it != null){
            
            List<PedidoItens> list = new ArrayList();
            it.forEach(list::add);
    
            return Optional.of(list);
        }
        
        return Optional.empty();
    }

    public void remove(Long idPedidoItem){

        appService.getPedidoItensRepository().deleteById(idPedidoItem);
    }
}
