package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.model.entity.PedidoItens;
import br.com.brasilprev.desafio.service.PedidoItensService;
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
public class PedidoItemAPITest {
    
    @InjectMocks
    PedidoItemAPI pedidoItemAPI;
	
    @Mock
    PedidoItensService pedidoItensService;
    
    //==========================================================================
   
    @Test
    public void testCria() throws Exception {
        
        Mockito.when(pedidoItensService.cria(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(BigDecimal.class)))
            .thenReturn(Optional.of(new PedidoItens()));
      
        ResponseEntity r = pedidoItemAPI.cria(0L, 0L, "", 0, new BigDecimal("0"));
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(pedidoItensService.cria(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(BigDecimal.class)))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoItemAPI.cria(0L, 0L, "", 0, new BigDecimal("0"));
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testAltera() throws Exception {
     
        Mockito.when(pedidoItensService.altera(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(BigDecimal.class)))
            .thenReturn(Optional.of(new PedidoItens()));
      
        ResponseEntity r = pedidoItemAPI.altera(0L, 0L, 0L, "", 0, new BigDecimal("0"));
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(pedidoItensService.altera(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(BigDecimal.class)))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoItemAPI.altera(0L, 0L, 0L, "", 0, new BigDecimal("0"));
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testBusca() throws Exception {
        
        Mockito.when(pedidoItensService.busca(Mockito.anyLong()))
            .thenReturn(Optional.of(new PedidoItens()));
      
        ResponseEntity r = pedidoItemAPI.busca(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(pedidoItensService.busca(Mockito.anyLong()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoItemAPI.busca(0L);
        
        assertEquals(HttpStatus.NO_CONTENT, r2.getStatusCode());
    }

    @Test
    public void testLista() throws Exception {
     
        Mockito.when(pedidoItensService.lista())
            .thenReturn(Optional.of(new LinkedList()));
      
        ResponseEntity r = pedidoItemAPI.lista();
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(pedidoItensService.lista())
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = pedidoItemAPI.lista();
        
        assertEquals(HttpStatus.OK, r2.getStatusCode());
    }

    @Test
    public void testRemove() throws Exception {
      
        ResponseEntity r = pedidoItemAPI.remove(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
    }
}
