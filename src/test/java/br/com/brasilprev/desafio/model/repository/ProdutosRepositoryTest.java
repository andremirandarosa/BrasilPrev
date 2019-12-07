package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Produtos;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProdutosRepositoryTest {
    
    @Mock
    ProdutosRepository produtosRepository;
    
    //==========================================================================

    @Test
    public void test() {
        
        Produtos p = new Produtos();
        p.setIdProduto(0L);
        assertNotNull(p.toString());
        
        Categorias categoria = Categorias.builder()
                                    .idCategoria(0L)
                                    .categoria("Teste de Categoria")
                              .build();    
            
            
        Produtos mock = Produtos.builder()
                                .idProduto(0L)
                                .idCategoria(categoria)
                                .produto("Teste de Produto")
                                .preco(new BigDecimal("1.99"))
                                .quantidade(1)
                                .descricao("Teste de Descricao")
                                .foto("Foto em Base 64")
                              .build();
        
        Mockito.when(produtosRepository.save(Mockito.any(Produtos.class)))
               .thenReturn(mock);
                
        Produtos p_saved = produtosRepository.save(new Produtos());
        
        assertNotNull(p_saved);
        assertNotNull(p_saved.getIdProduto());
        
        assertNotNull(p_saved.getIdCategoria());
        Assert.assertTrue(p_saved.getIdCategoria().getIdCategoria() == 0L);
        Assert.assertEquals("Teste de Categoria", p_saved.getIdCategoria().getCategoria());
        
        Assert.assertTrue(p_saved.getIdProduto() == 0L);
        Assert.assertEquals("Teste de Produto", p_saved.getProduto());
        Assert.assertEquals(new BigDecimal("1.99"), p_saved.getPreco());
        Assert.assertEquals(1, (int) p_saved.getQuantidade());
        Assert.assertEquals("Teste de Descricao", p_saved.getDescricao());
        Assert.assertEquals("Foto em Base 64", p_saved.getFoto());
    }
    
}
