package br.com.brasilprev.desafio.api;

import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.service.ClientesService;
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
public class ClienteAPITest {
    
    @InjectMocks
    ClienteAPI clienteAPI;
	
    @Mock
    ClientesService clientesService;
    
    //==========================================================================
   
    @Test
    public void testCria() throws Exception {
        
        Mockito.when(clientesService.cria(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                                          Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.of(new Clientes()));
      
        ResponseEntity r = clienteAPI.cria("", "", "", "", "", "", "", "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(clientesService.cria(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                                          Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = clienteAPI.cria("", "", "", "", "", "", "", "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testAltera() throws Exception {
     
        Mockito.when(clientesService.altera(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                                            Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.of(new Clientes()));
      
        ResponseEntity r = clienteAPI.altera(0L, "", "", "", "", "", "", "", "");
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(clientesService.altera(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                                            Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = clienteAPI.altera(0L, "", "", "", "", "", "", "", "");
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r2.getStatusCode());
    }

    @Test
    public void testBusca() throws Exception {
        
        Mockito.when(clientesService.busca(Mockito.anyLong()))
            .thenReturn(Optional.of(new Clientes()));
      
        ResponseEntity r = clienteAPI.busca(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
        Mockito.when(clientesService.busca(Mockito.anyLong()))
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = clienteAPI.busca(0L);
        
        assertEquals(HttpStatus.NO_CONTENT, r2.getStatusCode());
    }

    @Test
    public void testLista() throws Exception {
     
        Mockito.when(clientesService.lista())
            .thenReturn(Optional.of(new LinkedList()));
      
        ResponseEntity r = clienteAPI.lista();
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
        
        //======================================================================
         
         Mockito.when(clientesService.lista())
            .thenReturn(Optional.empty());
      
        ResponseEntity r2 = clienteAPI.lista();
        
        assertEquals(HttpStatus.OK, r2.getStatusCode());
    }

    @Test
    public void testRemove() throws Exception {
      
        ResponseEntity r = clienteAPI.remove(0L);
        
        assertEquals(HttpStatus.OK, r.getStatusCode());
    }
}
