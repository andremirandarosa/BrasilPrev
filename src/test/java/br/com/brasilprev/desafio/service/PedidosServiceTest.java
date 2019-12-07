package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidosServiceTest {
    
    @Autowired
    ClientesService clientesService;
    
    @Autowired
    PedidosService pedidosService;
    
    //==========================================================================
    
   
    @Test
    public void testCria() {
        
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id_cliente = op.get().getIdCliente();
        assertTrue(id_cliente >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
    
        Optional<Pedidos> op_pedidos = pedidosService.cria(id_cliente, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        assertTrue(op_pedidos.isPresent());
        assertNotNull(op_pedidos.get());
        Long id_pedido = op_pedidos.get().getIdPedido();
        assertTrue(id_pedido >= 0);
        assertEquals("31/12/2019", op_pedidos.get().getData());
        
    }

    @Test
    public void testAltera() {
      
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id_cliente = op.get().getIdCliente();
        assertTrue(id_cliente >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
    
        Optional<Pedidos> op_pedidos = pedidosService.cria(id_cliente, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        assertTrue(op_pedidos.isPresent());
        assertNotNull(op_pedidos.get());
        Long id_pedido = op_pedidos.get().getIdPedido();
        assertTrue(id_pedido >= 0);
        assertEquals("31/12/2019", op_pedidos.get().getData());
        
        //======================================================================
        
        Optional<Pedidos> op_pedido_2 = pedidosService.altera(id_pedido, id_cliente, "03/12/2019", "Teste de Status Alterado", "Teste de Sessao Alterado");
        
        assertTrue(op_pedido_2.isPresent());
        assertNotNull(op_pedido_2.get());
        Long id = op_pedido_2.get().getIdPedido();
        assertTrue(id >= 0);
        assertEquals("Teste de Sessao Alterado", op_pedido_2.get().getSessao());
    }
 
    @Test
    public void testBusca() {
      
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id_cliente = op.get().getIdCliente();
        assertTrue(id_cliente >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
    
        Optional<Pedidos> op_pedidos = pedidosService.cria(id_cliente, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        assertTrue(op_pedidos.isPresent());
        assertNotNull(op_pedidos.get());
        Long id_pedido = op_pedidos.get().getIdPedido();
        assertTrue(id_pedido >= 0);
        assertEquals("31/12/2019", op_pedidos.get().getData());
        
        //======================================================================
        
        Optional<Pedidos> op_pedido_2 = pedidosService.busca(id_pedido);
        
        assertTrue(op_pedido_2.isPresent());
        assertNotNull(op_pedido_2.get());
        Long id = op_pedido_2.get().getIdPedido();
        assertTrue(id >= 0);
        assertEquals("Teste de Status", op_pedido_2.get().getStatus());
    }

    @Test
    public void testLista() {
        
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id_cliente = op.get().getIdCliente();
        assertTrue(id_cliente >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
    
        Optional<Pedidos> op_pedidos = pedidosService.cria(id_cliente, "10/12/2019", "Teste de Status", "Teste de Sessao");
        
        assertTrue(op_pedidos.isPresent());
        assertNotNull(op_pedidos.get());
        Long id_pedido = op_pedidos.get().getIdPedido();
        assertTrue(id_pedido >= 0);
        assertEquals("10/12/2019", op_pedidos.get().getData());
        
        //======================================================================
        
        Optional<List<Pedidos>> op2 = pedidosService.lista();
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertTrue(op2.get().size() > 0);
    }

    @Test
    public void testRemove() {
        
        Optional<Clientes> op = clientesService.cria("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id_cliente = op.get().getIdCliente();
        assertTrue(id_cliente >= 0);
        assertEquals("Teste de Cliente", op.get().getNome());
    
        Optional<Pedidos> op_pedidos = pedidosService.cria(id_cliente, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        assertTrue(op_pedidos.isPresent());
        assertNotNull(op_pedidos.get());
        Long id_pedido = op_pedidos.get().getIdPedido();
        assertTrue(id_pedido >= 0);
        assertEquals("31/12/2019", op_pedidos.get().getData());
        
        //======================================================================
        
        int size_antes = pedidosService.lista().get().size();
        
        pedidosService.remove(id_pedido);
        
        int size_depois = pedidosService.lista().get().size();
        
        assertEquals(1, size_antes - size_depois);
    }
}
