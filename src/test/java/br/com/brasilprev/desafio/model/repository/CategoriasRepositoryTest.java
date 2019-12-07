package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Categorias;
import org.junit.Assert;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoriasRepositoryTest {
    
    @Mock
    CategoriasRepository categoriasRepository;
    
    //==========================================================================
        
    @Test
    public void test() {
    
        Categorias c = new Categorias();
        c.setIdCategoria(0L);
        assertNotNull(c.toString());
        
        Categorias c_mock = Categorias.builder()
                                .idCategoria(0L)
                                .categoria("Teste de Categoria")
                              .build();
        
        Mockito.when(categoriasRepository.save(Mockito.any(Categorias.class)))
               .thenReturn(c_mock);
                
        Categorias c_saved = categoriasRepository.save(new Categorias());
        
        assertNotNull(c_saved);
        assertNotNull(c_saved.getIdCategoria());
        
        Assert.assertTrue(c_saved.getIdCategoria() == 0L);
        Assert.assertEquals("Teste de Categoria", c_saved.getCategoria());
    }
    
}
