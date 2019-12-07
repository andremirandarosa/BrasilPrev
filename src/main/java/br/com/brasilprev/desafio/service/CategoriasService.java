package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Categorias;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {
    
    @Autowired
    AppService appService;
    
    //==========================================================================
    
    public Optional<Categorias> cria(String categoria){

        Categorias c = appService.salvaCategoria(categoria);
        
        if(c != null)
            return Optional.of(c);
        
        return Optional.empty();
    }
  
    public Optional<Categorias> altera(Long idCategoria, String categoria){

        Optional<Categorias> op = appService.getCategoriasRepository().findById(idCategoria);
        
        if(op.isPresent()){
            
            Categorias c = op.get();
            c.setCategoria(categoria);
                    
            c = appService.getCategoriasRepository().save(c);
        
            if(c != null)
                return Optional.of(c);
        }
        
        return Optional.empty();
    }
    
  
    public Optional<Categorias> busca(Long idCategoria){

        return appService.getCategoriasRepository().findById(idCategoria);
    }
    
    public Optional<List<Categorias>> lista(){

        Iterable<Categorias> it = appService.getCategoriasRepository().findAll();
        
        if(it != null){
            
            List<Categorias> list = new ArrayList();
            it.forEach(list::add);
    
            return Optional.of(list);
        }
        
        return Optional.empty();
    }

    public void remove(Long idCategoria){

        appService.getCategoriasRepository().deleteById(idCategoria);
    }
}
