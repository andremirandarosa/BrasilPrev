package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.config.Config;
import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.service.ClientesService;
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

@Api(value = Config.PATH_API + "/cliente", tags = "Cliente")
@RestController
@RequestMapping(Config.PATH_API + "/cliente")
public class ClienteAPI {

    @Autowired
    ClientesService clientesService;

    //==========================================================================
    
    @ApiOperation(value = "Cria um Cliente", response = String.class)
    @RequestMapping(method = RequestMethod.POST, 
                    value = "/cria",
                    produces = "application/json")
    public ResponseEntity cria(
            @ApiParam(required = true, name="nome", value = "Nome do Cliente") @RequestParam(required=true) String nome, 
            @ApiParam(required = true, name="senha", value = "Senha do Cliente") @RequestParam(required=true) String senha, 
            @ApiParam(required = true, name="email", value = "Email do Cliente") @RequestParam(required=true) String email, 
            @ApiParam(required = true, name="rua", value = "Rua do Cliente") @RequestParam(required=true) String rua, 
            @ApiParam(required = true, name="bairro", value = "Bairro do Cliente") @RequestParam(required=true) String bairro, 
            @ApiParam(required = true, name="cidade", value = "Cidade do Cliente") @RequestParam(required=true) String cidade, 
            @ApiParam(required = true, name="estado", value = "Estado do Cliente") @RequestParam(required=true) String estado, 
            @ApiParam(required = true, name="cep", value = "CEP do Cliente") @RequestParam(required=true) String cep) throws Exception{

        Optional<Clientes> op = clientesService.cria(nome, senha, email, rua, bairro, cidade, estado, cep);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Altera um Cliente pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.PUT, 
                    value = "/altera",
                    produces = "application/json")
    public ResponseEntity altera(
            @ApiParam(required = true, name="idCliente", value = "Id do produto") @RequestParam(required=true) Long idCliente,
            @ApiParam(required = true, name="nome", value = "Nome do Cliente") @RequestParam(required=true) String nome, 
            @ApiParam(required = true, name="senha", value = "Senha do Cliente") @RequestParam(required=true) String senha, 
            @ApiParam(required = true, name="email", value = "Email do Cliente") @RequestParam(required=true) String email, 
            @ApiParam(required = true, name="rua", value = "Rua do Cliente") @RequestParam(required=true) String rua, 
            @ApiParam(required = true, name="bairro", value = "Bairro do Cliente") @RequestParam(required=true) String bairro, 
            @ApiParam(required = true, name="cidade", value = "Cidade do Cliente") @RequestParam(required=true) String cidade, 
            @ApiParam(required = true, name="estado", value = "Estado do Cliente") @RequestParam(required=true) String estado, 
            @ApiParam(required = true, name="cep", value = "CEP do Cliente") @RequestParam(required=true) String cep) throws Exception{

        Optional<Clientes> op = clientesService.altera(idCliente, nome, senha, email, rua, bairro, cidade, estado, cep);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Busca um Cliente pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/busca",
                    produces = "application/json")
    public ResponseEntity busca(
            @ApiParam(required = true, name="idCliente", value = "Id do Cliente") @RequestParam(required=true) Long idCliente) throws Exception{

        Optional<Clientes> op = clientesService.busca(idCliente);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "Lista os Clientes", response = List.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/lista",
                    produces = "application/json")
    public ResponseEntity lista() throws Exception{

        Optional<List<Clientes>> op = clientesService.lista();
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(new LinkedList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um Cliente pelo ID")
    @RequestMapping(method = RequestMethod.DELETE, 
                    value = "/remove")
    public ResponseEntity remove(
            @ApiParam(required = true, name="idCliente", value = "Id do Cliente") @RequestParam(required=true) Long idCliente) throws Exception{

        clientesService.remove(idCliente);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
