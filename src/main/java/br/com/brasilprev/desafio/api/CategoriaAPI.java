package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.config.Config;
import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.service.CategoriasService;
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

@Api(value = Config.PATH_API + "/categoria", tags = "Categoria")
@RestController
@RequestMapping(Config.PATH_API + "/categoria")
public class CategoriaAPI {

    @Autowired
    CategoriasService categoriaService;

    //==========================================================================
    
    @ApiOperation(value = "Cria uma Categoria", response = String.class)
    @RequestMapping(method = RequestMethod.POST, 
                    value = "/cria",
                    produces = "application/json")
    public ResponseEntity cria(
            @ApiParam(required = true, name="categoria", value = "Nome da Categoria") @RequestParam(required=true) String categoria) throws Exception{

        Optional<Categorias> op = categoriaService.cria(categoria);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Altera uma Categoria pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.PUT, 
                    value = "/altera",
                    produces = "application/json")
    public ResponseEntity altera(
            @ApiParam(required = true, name="idCategoria", value = "Id da Categoria") @RequestParam(required=true) Long idCategoria,
            @ApiParam(required = true, name="categoria", value = "Nome da Categoria") @RequestParam(required=true) String categoria) throws Exception{

        Optional<Categorias> op = categoriaService.altera(idCategoria, categoria);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity<Categorias>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ApiOperation(value = "Busca uma Categoria pelo ID", response = String.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/busca",
                    produces = "application/json")
    public ResponseEntity busca(
            @ApiParam(required = true, name="idCategoria", value = "Id da Categoria") @RequestParam(required=true) Long idCategoria) throws Exception{

        Optional<Categorias> op = categoriaService.busca(idCategoria);
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "Lista as Categorias", response = List.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = "/lista",
                    produces = "application/json")
    public ResponseEntity lista() throws Exception{

        Optional<List<Categorias>> op = categoriaService.lista();
        
        if(op.isPresent())
            return new ResponseEntity(op.get(), HttpStatus.OK);
        
        return new ResponseEntity(new LinkedList(), HttpStatus.OK);
    }

     @ApiOperation(value = "Remove uma Categoria pelo ID")
    @RequestMapping(method = RequestMethod.DELETE, 
                    value = "/remove")
    public ResponseEntity remove(
            @ApiParam(required = true, name="idCategoria", value = "Id da Categoria") @RequestParam(required=true) Long idCategoria) throws Exception{

        categoriaService.remove(idCategoria);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
