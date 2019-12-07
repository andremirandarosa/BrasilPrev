package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.service.CategoriasService;
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
public class CategoriaAPITest {
    
    @InjectMocks
    CategoriaAPI categoriaAPI;
	
    @Mock
    CategoriasService categoriaService;
    
    //==========================================================================
   
    @Test
    public void testCria() throws Exception {
        
        Mockito.when(categoriaService.cria(Mockito.anyString()))
            .thenReturn(Optional.of(new Categorias()));
      
        ResponseEntity r = categoriaAPI.cria("");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(categoriaService.cria(Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = categoriaAPI.cria("");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testAltera() throws Exception {
     
        Mockito.when(categoriaService.altera(Mockito.anyLong(), Mockito.anyString()))
            .thenReturn(Optional.of(new Categorias()));
      
        ResponseEntity r = categoriaAPI.altera(0L, "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(categoriaService.altera(Mockito.anyLong(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = categoriaAPI.altera(0L, "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testBusca() throws Exception {
        
        Mockito.when(categoriaService.busca(Mockito.anyLong()))
            .thenReturn(Optional.of(new Categorias()));
      
        ResponseEntity r = categoriaAPI.busca(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(categoriaService.busca(Mockito.anyLong()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = categoriaAPI.busca(0L);
        
        assertEquals(HttpStatus.NO_CONTENT, r2.getStatusCode());
    }

    @Test
    public void testLista() throws Exception {
     
        Mockito.when(categoriaService.lista())
            .thenReturn(Optional.of(new LinkedList()));
      
        ResponseEntity r = categoriaAPI.lista();
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(categoriaService.lista())
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = categoriaAPI.lista();
        
        assertEquals(HttpStatus.OK, r2.getStatusCode());
    }

    @Test
    public void testRemove() throws Exception {
      
        ResponseEntity r = categoriaAPI.remove(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
    }
}
