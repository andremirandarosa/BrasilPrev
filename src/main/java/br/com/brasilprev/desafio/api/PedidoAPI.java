package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.config.Config;
import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import br.com.brasilprev.desafio.service.PedidosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(value = Config.PATH_API + "/pedido", tags = "Pedido")
@RestController
@RequestMapping(Config.PATH_API + "/pedido")
public class PedidoAPI {

    @Autowired
    PedidosService pedidoService;

    //==========================================================================
    
    @ApiOperation(value = "Cria um Pedido", response = String.class)
    @RequestMapping(method = RequestMethod.POST, 
                    value = "/cria",
                    produces = "application/json")
    public ResponseEntity cria(
            @ApiParam(required = true, name="idCliente", value = "Id do Cliente") @RequestParam(required=true) Long idCliente,
            @ApiParam(required = true, name="data", value = "Data do Pedido") @RequestParam(required=true) String data, 
            @ApiParam(required = true, name="status", value = "Status do Pedido") @RequestParam(required=true) String status, 
            @ApiParam(required = true, name="sessao", value = "Sessão do Pedido") @RequestParam(required=true) String sessao) throws Exception{

        Optional<Pedidos> op = pedidoService.cria(idCliente, data, status, sessao);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Altera um Pedido pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.PUT, 
                    value = "/altera",
                    produces = "application/json")
    public ResponseEntity altera(
            @ApiParam(required = true, name="idPedido", value = "Id do Pedido") @RequestParam(required=true) Long idPedido,
            @ApiParam(required = true, name="idCliente", value = "Id do Cliente") @RequestParam(required=true) Long idCliente,
            @ApiParam(required = true, name="data", value = "Data do Pedido") @RequestParam(required=true) String data, 
            @ApiParam(required = true, name="status", value = "Status do Pedido") @RequestParam(required=true) String status, 
            @ApiParam(required = true, name="sessao", value = "Sessão do Pedido") @RequestParam(required=true) String sessao) throws Exception{

        Optional<Pedidos> op = pedidoService.altera(idPedido, idCliente, data, status, sessao);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Busca um Pedido pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/busca",
                    produces = "application/json")
    public ResponseEntity busca(
            @ApiParam(required = true, name="idPedido", value = "Id do Pedido") @RequestParam(required=true) Long idPedido) throws Exception{

        Optional<Pedidos> op = pedidoService.busca(idPedido);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "Lista os Pedidos", response = List.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/lista",
                    produces = "application/json")
    public ResponseEntity lista() throws Exception{

        Optional<List<Pedidos>> op = pedidoService.lista();
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(new LinkedList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um Pedido pelo ID")
    @RequestMapping(method = RequestMethod.DELETE, 
                    value = "/remove")
    public ResponseEntity remove(
            @ApiParam(required = true, name="idPedido", value = "Id do Pedido") @RequestParam(required=true) Long idPedido) throws Exception{

        pedidoService.remove(idPedido);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
