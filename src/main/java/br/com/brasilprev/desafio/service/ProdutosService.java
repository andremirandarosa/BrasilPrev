package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Produtos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {
    
    @Autowired
    AppService appService;
    
    @Autowired
    CategoriasService categoriasService;
    
    //==========================================================================
    
    public Optional<Produtos> cria(Long idCategoria, String produto, BigDecimal preco, int quantidade, String descricao, String foto){

        Optional<Categorias> op_categorias = categoriasService.busca(idCategoria);
        
        if(op_categorias.isPresent()){
        
            Produtos p = appService.salvaProduto(op_categorias.get(), produto, preco, quantidade, descricao, foto);
            
            if(p != null)
                return Optional.of(p);
        }
        
        return Optional.empty();
    }
  
    public Optional<Produtos> altera(Long idProduto, Long idCategoria, String produto, BigDecimal preco, int quantidade, String descricao, String foto){

        Optional<Produtos> op_produtos = appService.getProdutosRepository().findById(idProduto);

        if(op_produtos.isPresent()){

            Optional<Categorias> op_categorias = categoriasService.busca(idCategoria);

            if(op_categorias.isPresent()){

                Produtos p = op_produtos.get();
                p.setIdCategoria(op_categorias.get());
                p.setProduto(produto);
                p.setPreco(preco);
                p.setQuantidade(quantidade);
                p.setDescricao(descricao);
                p.setFoto(foto);
                
                p = appService.getProdutosRepository().save(p);

                if(p != null)
                    return Optional.of(p);
            }
        }
        
        return Optional.empty();
    }
    
    public Optional<Produtos> busca(Long idProduto){

        return appService.getProdutosRepository().findById(idProduto);
    }
    
    public Optional<List<Produtos>> lista(){

        Iterable<Produtos> it = appService.getProdutosRepository().findAll();
        
        if(it != null){
            
            List<Produtos> list = new ArrayList();
            it.forEach(list::add);
    
            return Optional.of(list);
        }
        
        return Optional.empty();
    }

    public void remove(Long idProduto){

        appService.getProdutosRepository().deleteById(idProduto);
    }
}
