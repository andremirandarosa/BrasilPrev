package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.PedidoItens;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import br.com.brasilprev.desafio.model.entity.Produtos;
import java.math.BigDecimal;
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
public class PedidoItensServiceTest {
    
    @Autowired
    CategoriasService categoriasService;
     
    @Autowired
    ProdutosService produtosService;
    
    @Autowired
    ClientesService clientesService;
    
    @Autowired
    PedidosService pedidosService;
    
    @Autowired
    PedidoItensService pedidoItensService;
     
    //==========================================================================
    
    @Test
    public void testCria() {
        
        Optional<Categorias> op_categoria = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op_categoria.isPresent());
        assertNotNull(op_categoria.get());
        Long id_categoria = op_categoria.get().getIdCategoria();
        assertTrue(id_categoria >= 0);
        assertEquals("Teste de Categoria", op_categoria.get().getCategoria());
        
        Optional<Produtos> op_produtos = produtosService.cria(id_categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        assertTrue(op_produtos.isPresent());
        assertNotNull(op_produtos.get());
        Long id_produto = op_produtos.get().getIdProduto();
        assertTrue(id_produto >= 0);
        assertEquals("Teste de Produto", op_produtos.get().getProduto());
        
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
        
        Optional<PedidoItens> op_pedido_itens = pedidoItensService.cria(id_produto, id_pedido, "Teste de Produto Item", 2, new BigDecimal("14.95"));
        
        assertTrue(op_pedido_itens.isPresent());
        assertNotNull(op_pedido_itens.get());
        Long id_pedido_item = op_pedido_itens.get().getIdItem();
        assertTrue(id_pedido_item >= 0);
        assertEquals(new BigDecimal("29.90"), op_pedido_itens.get().getSubtotal());
    }
  
    @Test
    public void testAltera() {
     
        Optional<Categorias> op_categoria = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op_categoria.isPresent());
        assertNotNull(op_categoria.get());
        Long id_categoria = op_categoria.get().getIdCategoria();
        assertTrue(id_categoria >= 0);
        assertEquals("Teste de Categoria", op_categoria.get().getCategoria());
        
        Optional<Produtos> op_produtos = produtosService.cria(id_categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        assertTrue(op_produtos.isPresent());
        assertNotNull(op_produtos.get());
        Long id_produto = op_produtos.get().getIdProduto();
        assertTrue(id_produto >= 0);
        assertEquals("Teste de Produto", op_produtos.get().getProduto());
        
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
        
        Optional<PedidoItens> op_pedido_itens = pedidoItensService.cria(id_produto, id_pedido, "Teste de Produto Item", 2, new BigDecimal("2"));
        
        assertTrue(op_pedido_itens.isPresent());
        assertNotNull(op_pedido_itens.get());
        Long id_pedido_item = op_pedido_itens.get().getIdItem();
        assertTrue(id_pedido_item >= 0);
        assertEquals(new BigDecimal("4"), op_pedido_itens.get().getSubtotal());
        
        //======================================================================
        
        Optional<PedidoItens> op_pedido_item_2 = pedidoItensService.altera(id_pedido_item, id_produto, id_pedido, "Teste de Produto Item", 7, new BigDecimal("4.85"));
        
        assertTrue(op_pedido_item_2.isPresent());
        assertNotNull(op_pedido_item_2.get());
        Long id = op_pedido_item_2.get().getIdItem();
        assertTrue(id >= 0);
        assertEquals(7, (int) op_pedido_item_2.get().getQuantidade());
    }

    @Test
    public void testBusca() {
      
        Optional<Categorias> op_categoria = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op_categoria.isPresent());
        assertNotNull(op_categoria.get());
        Long id_categoria = op_categoria.get().getIdCategoria();
        assertTrue(id_categoria >= 0);
        assertEquals("Teste de Categoria", op_categoria.get().getCategoria());
        
        Optional<Produtos> op_produtos = produtosService.cria(id_categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        assertTrue(op_produtos.isPresent());
        assertNotNull(op_produtos.get());
        Long id_produto = op_produtos.get().getIdProduto();
        assertTrue(id_produto >= 0);
        assertEquals("Teste de Produto", op_produtos.get().getProduto());
        
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
        
        Optional<PedidoItens> op_pedido_itens = pedidoItensService.cria(id_produto, id_pedido, "Teste de Produto Item", 2, new BigDecimal("14.95"));
        
        assertTrue(op_pedido_itens.isPresent());
        assertNotNull(op_pedido_itens.get());
        Long id_pedido_item = op_pedido_itens.get().getIdItem();
        assertTrue(id_pedido_item >= 0);
        assertEquals(new BigDecimal("14.95"), op_pedido_itens.get().getValor());
        
        //======================================================================
        
        Optional<PedidoItens> op_pedido_item_2 = pedidoItensService.busca(id_pedido_item);
        
        assertTrue(op_pedido_item_2.isPresent());
        assertNotNull(op_pedido_item_2.get());
        Long id = op_pedido_item_2.get().getIdItem();
        assertTrue(id >= 0);
        assertEquals(new BigDecimal("29.90"), op_pedido_item_2.get().getSubtotal());
    }

    @Test
    public void testLista() {
      
        Optional<Categorias> op_categoria = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op_categoria.isPresent());
        assertNotNull(op_categoria.get());
        Long id_categoria = op_categoria.get().getIdCategoria();
        assertTrue(id_categoria >= 0);
        assertEquals("Teste de Categoria", op_categoria.get().getCategoria());
        
        Optional<Produtos> op_produtos = produtosService.cria(id_categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        assertTrue(op_produtos.isPresent());
        assertNotNull(op_produtos.get());
        Long id_produto = op_produtos.get().getIdProduto();
        assertTrue(id_produto >= 0);
        assertEquals("Teste de Produto", op_produtos.get().getProduto());
        
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
        
        Optional<PedidoItens> op_pedido_itens = pedidoItensService.cria(id_produto, id_pedido, "Teste de Produto Item", 2, new BigDecimal("14.95"));
        
        assertTrue(op_pedido_itens.isPresent());
        assertNotNull(op_pedido_itens.get());
        Long id_pedido_item = op_pedido_itens.get().getIdItem();
        assertTrue(id_pedido_item >= 0);
        assertEquals(new BigDecimal("14.95"), op_pedido_itens.get().getValor());
        
        //======================================================================
        
        Optional<List<PedidoItens>> op2 = pedidoItensService.lista();
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertTrue(op2.get().size() > 0);
    }

 
    @Test
    public void testRemove() {
        
        Optional<Categorias> op_categoria = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op_categoria.isPresent());
        assertNotNull(op_categoria.get());
        Long id_categoria = op_categoria.get().getIdCategoria();
        assertTrue(id_categoria >= 0);
        assertEquals("Teste de Categoria", op_categoria.get().getCategoria());
        
        Optional<Produtos> op_produtos = produtosService.cria(id_categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        assertTrue(op_produtos.isPresent());
        assertNotNull(op_produtos.get());
        Long id_produto = op_produtos.get().getIdProduto();
        assertTrue(id_produto >= 0);
        assertEquals("Teste de Produto", op_produtos.get().getProduto());
        
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
        
        Optional<PedidoItens> op_pedido_itens = pedidoItensService.cria(id_produto, id_pedido, "Teste de Produto Item", 2, new BigDecimal("14.95"));
        
        assertTrue(op_pedido_itens.isPresent());
        assertNotNull(op_pedido_itens.get());
        Long id_pedido_item = op_pedido_itens.get().getIdItem();
        assertTrue(id_pedido_item >= 0);
        assertEquals(new BigDecimal("14.95"), op_pedido_itens.get().getValor());
        
        //======================================================================
     
        int size_antes = pedidoItensService.lista().get().size();
        
        pedidoItensService.remove(id_pedido_item);
        
        int size_depois = pedidoItensService.lista().get().size();
        
        assertEquals(1, size_antes - size_depois);
    }
}
