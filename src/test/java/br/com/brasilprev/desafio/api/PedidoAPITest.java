package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.model.entity.Pedidos;
import br.com.brasilprev.desafio.service.PedidosService;
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
public class PedidoAPITest {
    
    @InjectMocks
    PedidoAPI pedidoAPI;
	
    @Mock
    PedidosService pedidoService;
    
    //==========================================================================
   
    @Test
    public void testCria() throws Exception {
        
        Mockito.when(pedidoService.cria(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.of(new Pedidos()));
      
        ResponseEntity r = pedidoAPI.cria(0L, "", "", "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(pedidoService.cria(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoAPI.cria(0L, "", "", "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testAltera() throws Exception {
     
        Mockito.when(pedidoService.altera(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.of(new Pedidos()));
      
        ResponseEntity r = pedidoAPI.altera(0L, 0L, "", "", "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(pedidoService.altera(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoAPI.altera(0L, 0L, "", "", "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testBusca() throws Exception {
        
        Mockito.when(pedidoService.busca(Mockito.anyLong()))
            .thenReturn(Optional.of(new Pedidos()));
      
        ResponseEntity r = pedidoAPI.busca(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(pedidoService.busca(Mockito.anyLong()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoAPI.busca(0L);
        
        assertEquals(HttpStatus.NO_CONTENT, r2.getStatusCode());
    }

    @Test
    public void testLista() throws Exception {
     
        Mockito.when(pedidoService.lista())
            .thenReturn(Optional.of(new LinkedList()));
      
        ResponseEntity r = pedidoAPI.lista();
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(pedidoService.lista())
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoAPI.lista();
        
        assertEquals(HttpStatus.OK, r2.getStatusCode());
    }

    @Test
    public void testRemove() throws Exception {
      
        ResponseEntity r = pedidoAPI.remove(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
    }
}
