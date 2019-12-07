package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Clientes;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientesServiceTest {
         
    @Autowired
    ClientesService clientesService;
    
    //==========================================================================

    @Test
    public void testCria() {
      
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        assertTrue(op.get().getIdCliente() >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
        
    }

    @Test
    public void testAltera() {
      
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCliente();
        assertTrue(id >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
        
        //======================================================================
        
        Optional<Clientes> op2 = clientesService.altera(id, "Teste de Cliente Alterado", "1234567890", "cliente222@teste.com", "Rua de teste 2", "Bairro de Teste 2", "São Paulo 2", "SP 2", "12345-678 2");
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        Long id2 = op2.get().getIdCliente();
        assertTrue(id2 >= 0);
        assertEquals("Teste de Cliente Alterado", op2.get().getNome());
    }

    @Test
    public void testBusca() {
     
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCliente();
        assertTrue(id >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
        
        //======================================================================
        
        Optional<Clientes> op2 = clientesService.busca(id);
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertEquals(id, op2.get().getIdCliente());
        assertEquals("Teste de Cliente", op.get().getNome());
    }
    
    @Test
    public void testLista() {
     
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCliente();
        assertTrue(id >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
        
        //======================================================================
        
        Optional<List<Clientes>> op2 = clientesService.lista();
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertTrue(op2.get().size() > 0);
        assertEquals("São Paulo", op2.get().get(0).getCidade());
    }
    
    @Test
    public void testRemove() {
      
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCliente();
        assertTrue(id >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
        
        //======================================================================
        
        int size_antes = clientesService.lista().get().size();
        
        clientesService.remove(id);
        
        int size_depois = clientesService.lista().get().size();
        
        assertEquals(1, size_antes - size_depois);
    }
}
