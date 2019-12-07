package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.model.entity.Produtos;
import br.com.brasilprev.desafio.service.ProdutosService;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProdutoAPITest {
    
    @InjectMocks
    ProdutoAPI produtoAPI;
	
    @Mock
    ProdutosService produtosService;
    
    //==========================================================================
   
    @Test
    public void testCria() throws Exception {
        
        Mockito.when(produtosService.cria(Mockito.anyLong(), Mockito.anyString(), Mockito.any(BigDecimal.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.of(new Produtos()));
      
        ResponseEntity r = produtoAPI.cria(0L, "", new BigDecimal("0"), 0, "", "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(produtosService.cria(Mockito.anyLong(), Mockito.anyString(), Mockito.any(BigDecimal.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = produtoAPI.cria(0L, "", new BigDecimal("0"), 0, "", "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testAltera() throws Exception {
     
        Mockito.when(produtosService.altera(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.any(BigDecimal.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.of(new Produtos()));
      
        ResponseEntity r = produtoAPI.altera(0L, 0L, "", new BigDecimal("0"), 0, "", "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(produtosService.altera(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.any(BigDecimal.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = produtoAPI.altera(0L, 0L, "", new BigDecimal("0"), 0, "", "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testBusca() throws Exception {
        
        Mockito.when(produtosService.busca(Mockito.anyLong()))
            .thenReturn(Optional.of(new Produtos()));
      
        ResponseEntity r = produtoAPI.busca(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(produtosService.busca(Mockito.anyLong()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = produtoAPI.busca(0L);
        
        assertEquals(HttpStatus.NO_CONTENT, r2.getStatusCode());
    }

    @Test
    public void testLista() throws Exception {
     
        Mockito.when(produtosService.lista())
            .thenReturn(Optional.of(new LinkedList()));
      
        ResponseEntity r = produtoAPI.lista();
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(produtosService.lista())
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = produtoAPI.lista();
        
        assertEquals(HttpStatus.OK, r2.getStatusCode());
    }

    @Test
    public void testRemove() throws Exception {
      
        ResponseEntity r = produtoAPI.remove(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
    }
}
