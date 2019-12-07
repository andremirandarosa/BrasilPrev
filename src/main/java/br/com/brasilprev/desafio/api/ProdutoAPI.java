package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.config.Config;
import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Produtos;
import br.com.brasilprev.desafio.service.ProdutosService;
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

@Api(value = Config.PATH_API + "/produto", tags = "Produto")
@RestController
@RequestMapping(Config.PATH_API + "/produto")
public class ProdutoAPI {

    @Autowired
    ProdutosService produtosService;

    //==========================================================================
    
    @ApiOperation(value = "Cria um Produto", response = String.class)
    @RequestMapping(method = RequestMethod.POST, 
                    value = "/cria",
                    produces = "application/json")
    public ResponseEntity cria(
            @ApiParam(required = true, name="idCategoria", value = "Id da Categoria") @RequestParam(required=true) Long idCategoria, 
            @ApiParam(required = true, name="produto", value = "Nome do Produto") @RequestParam(required=true) String produto, 
            @ApiParam(required = true, name="preco", value = "Preço do Produto") @RequestParam(required=true) BigDecimal preco, 
            @ApiParam(required = true, name="quantidade", value = "Quantidade do Produto") @RequestParam(required=true) int quantidade, 
            @ApiParam(required = true, name="descricao", value = "Descrição do Produto") @RequestParam(required=true) String descricao, 
            @ApiParam(required = true, name="foto", value = "Foto do Produto") @RequestParam(required=true) String foto) throws Exception{

        Optional<Produtos> op = produtosService.cria(idCategoria, produto, preco, quantidade, descricao, foto);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Altera um Produto pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.PUT, 
                    value = "/altera",
                    produces = "application/json")
    public ResponseEntity altera(
            @ApiParam(required = true, name="idProduto", value = "Id do Produto") @RequestParam(required=true) Long idProduto,
            @ApiParam(required = true, name="idCategoria", value = "Id da Categoria") @RequestParam(required=true) Long idCategoria, 
            @ApiParam(required = true, name="produto", value = "Nome do Produto") @RequestParam(required=true) String produto, 
            @ApiParam(required = true, name="preco", value = "Preço do Produto") @RequestParam(required=true) BigDecimal preco, 
            @ApiParam(required = true, name="quantidade", value = "Quantidade do Produto") @RequestParam(required=true) int quantidade, 
            @ApiParam(required = true, name="descricao", value = "Descrição do Produto") @RequestParam(required=true) String descricao, 
            @ApiParam(required = true, name="foto", value = "Foto do Produto") @RequestParam(required=true) String foto) throws Exception{

        Optional<Produtos> op = produtosService.altera(idProduto, idCategoria, produto, preco, quantidade, descricao, foto);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Busca um Produto pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/busca",
                    produces = "application/json")
    public ResponseEntity busca(
            @ApiParam(required = true, name="idProduto", value = "Id do Produto") @RequestParam(required=true) Long idProduto) throws Exception{

        Optional<Produtos> op = produtosService.busca(idProduto);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "Lista os Produtos", response = List.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/lista",
                    produces = "application/json")
    public ResponseEntity lista() throws Exception{

        Optional<List<Produtos>> op = produtosService.lista();
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(new LinkedList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um Produto pelo ID")
    @RequestMapping(method = RequestMethod.DELETE, 
                    value = "/remove")
    public ResponseEntity remove(
            @ApiParam(required = true, name="idProduto", value = "Id do Produto") @RequestParam(required=true) Long idProduto) throws Exception{

        produtosService.remove(idProduto);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
