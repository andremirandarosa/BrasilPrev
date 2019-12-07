package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.config.Config;
import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.PedidoItens;
import br.com.brasilprev.desafio.service.PedidoItensService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = Config.PATH_API + "/pedido_item", tags = "Pedido Item")
@RestController
@RequestMapping(Config.PATH_API + "/pedido_item")
public class PedidoItemAPI {

    @Autowired
    PedidoItensService pedidoItensService;

    //==========================================================================
    
    @ApiOperation(value = "Cria um Pedido Item", response = String.class)
    @RequestMapping(method = RequestMethod.POST, 
                    value = "/cria",
                    produces = "application/json")
    public ResponseEntity cria(
            @ApiParam(required = true, name="idProduto", value = "Id do Produto") @RequestParam(required=true) Long idProduto,
            @ApiParam(required = true, name="idPedido", value = "Id do Pedido") @RequestParam(required=true) Long idPedido,
            @ApiParam(required = true, name="produto", value = "Nome do Produto") @RequestParam(required=true) String produto, 
            @ApiParam(required = true, name="quantidade", value = "Quantidade") @RequestParam(required=true) int quantidade,
            @ApiParam(required = true, name="valor", value = "Valor") @RequestParam(required=true) BigDecimal valor) throws Exception{

        Optional<PedidoItens> op = pedidoItensService.cria(idProduto, idPedido, produto, quantidade, valor);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Altera um Pedido Item pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.PUT, 
                    value = "/altera",
                    produces = "application/json")
    public ResponseEntity altera(
            @ApiParam(required = true, name="idPedidoItem", value = "Id do Pedido") @RequestParam(required=true) Long idPedidoItem,
            @ApiParam(required = true, name="idProduto", value = "Id do Produto") @RequestParam(required=true) Long idProduto,
            @ApiParam(required = true, name="idPedido", value = "Id do Pedido") @RequestParam(required=true) Long idPedido,
            @ApiParam(required = true, name="produto", value = "Nome do Produto") @RequestParam(required=true) String produto, 
            @ApiParam(required = true, name="quantidade", value = "Quantidade") @RequestParam(required=true) int quantidade,
            @ApiParam(required = true, name="valor", value = "Valor") @RequestParam(required=true) BigDecimal valor) throws Exception{

        Optional<PedidoItens> op = pedidoItensService.altera(idPedidoItem, idProduto, idPedido, produto, quantidade, valor);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Busca um Pedido Item pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/busca",
                    produces = "application/json")
    public ResponseEntity busca(
            @ApiParam(required = true, name="idPedidoItem", value = "Id do Pedido") @RequestParam(required=true) Long idPedidoItem) throws Exception{

        Optional<PedidoItens> op = pedidoItensService.busca(idPedidoItem);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "Lista os Pedido Itens", response = List.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/lista",
                    produces = "application/json")
    public ResponseEntity lista() throws Exception{

        Optional<List<PedidoItens>> op = pedidoItensService.lista();
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(new LinkedList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um Pedido Item pelo ID")
    @RequestMapping(method = RequestMethod.DELETE, 
                    value = "/remove")
    public ResponseEntity remove(
            @ApiParam(required = true, name="idPedidoItem", value = "Id do Pedido Item") @RequestParam(required=true) Long idPedidoItem) throws Exception{

        pedidoItensService.remove(idPedidoItem);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
