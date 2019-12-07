package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.PedidoItens;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import br.com.brasilprev.desafio.model.entity.Produtos;
import br.com.brasilprev.desafio.model.repository.CategoriasRepository;
import br.com.brasilprev.desafio.model.repository.ClientesRepository;
import br.com.brasilprev.desafio.model.repository.PedidoItensRepository;
import br.com.brasilprev.desafio.model.repository.PedidosRepository;
import br.com.brasilprev.desafio.model.repository.ProdutosRepository;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AppServiceTest {
   
    @InjectMocks
    AppService appService;
    
    @Mock
    CategoriasRepository categoriasRepository;
    
    @Mock
    ProdutosRepository produtosRepository;
    
    @Mock
    ClientesRepository clientesRepository;
    
    @Mock
    PedidosRepository pedidosRepository;
    
    @Mock
    PedidoItensRepository pedidoItensRepository;
    
    //==========================================================================
    
    @Test
    public void testGets() {
        
        appService.getCategoriasRepository();
        
        appService.getClientesRepository();
        
        appService.getProdutosRepository();
        
        appService.getPedidosRepository();
        
        appService.getPedidoItensRepository();
    }
    
    @Test
    public void criaCategoriaOBJ(){
        
        Categorias c = appService.criaCategoriaOBJ("Teste de Categoria");
        
        assertNotNull(c);
        assertEquals("Teste de Categoria", c.getCategoria());
    }
    
    @Test
    public void criaProdutoOBJ(){
     
        Categorias c = appService.criaCategoriaOBJ("Teste de Categoria");
        
        Produtos p = appService.criaProdutoOBJ(c, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        assertNotNull(p);
        assertEquals("Teste de Produto", p.getProduto());
    }
    
     @Test
    public void criaClienteOBJ(){
        
        Clientes c = appService.criaClienteOBJ("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        assertNotNull(c);
        assertEquals("Teste de Cliente", c.getNome());
    }
    
    @Test
    public void criaPedidoOBJ(){
        
        Clientes c = appService.criaClienteOBJ("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        Pedidos p = appService.criaPedidoOBJ(c, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        assertNotNull(p);
        assertEquals("31/12/2019", p.getData());
        assertEquals("Teste de Cliente", p.getIdCliente().getNome());
    }
    
    @Test
    public void criaPedidoItemOBJ(){
    
        Categorias categoria = appService.criaCategoriaOBJ("Teste de Categoria");
        
        Produtos produto = appService.criaProdutoOBJ(categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        Clientes cliente = appService.criaClienteOBJ("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        Pedidos pedido = appService.criaPedidoOBJ(cliente, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        PedidoItens pedido_item = appService.criaPedidoItemOBJ(produto, pedido, "Teste de Produto Item", 3, new BigDecimal("14.95"), new BigDecimal("59.29"));
                  
        assertNotNull(pedido_item);
        assertEquals("Teste de Produto Item", pedido_item.getProduto());
        assertEquals(new BigDecimal("14.95"), pedido_item.getValor());
        assertEquals("31/12/2019", pedido_item.getIdPedido().getData());
        assertEquals("Teste de Descricao", pedido_item.getIdProduto().getDescricao());
    }
    
    //==========================================================================
    
    @Test
    public void salvaCategoria(){
        
        Categorias c = appService.criaCategoriaOBJ("Teste de Categoria");
        
        Mockito.when(categoriasRepository.save(Mockito.any(Categorias.class)))
               .thenReturn(c);
        
        Categorias obj = appService.getCategoriasRepository().save(c);
        assertNotNull(obj);
    }
    
    @Test
    public void salvaProduto(){
        
        Categorias c = appService.criaCategoriaOBJ("Teste de Categoria");
        
        Produtos p = appService.criaProdutoOBJ(c, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        Mockito.when(produtosRepository.save(Mockito.any(Produtos.class)))
            .thenReturn(p);
        
        Produtos obj = appService.getProdutosRepository().save(p);
        assertNotNull(obj);
    }
    
    @Test
    public void salvaCliente(){
        
        Clientes c = appService.criaClienteOBJ("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        Mockito.when(clientesRepository.save(Mockito.any(Clientes.class)))
            .thenReturn(c);
        
        Clientes obj = appService.getClientesRepository().save(c);
        assertNotNull(obj);
    }
    
    @Test
    public void salvaPedido(){
        
        Clientes c = appService.criaClienteOBJ("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        Pedidos p = appService.criaPedidoOBJ(c, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        Mockito.when(pedidosRepository.save(Mockito.any(Pedidos.class)))
            .thenReturn(p);
        
        Pedidos obj = appService.getPedidosRepository().save(p);
        assertNotNull(obj);
    }
    
    @Test
    public void salvaPedidoItem(){
    
        Categorias categoria = appService.criaCategoriaOBJ("Teste de Categoria");
        
        Produtos produto = appService.criaProdutoOBJ(categoria, "Teste de Produto", new BigDecimal("1.99"), 1, "Teste de Descricao", "Foto em Base 64");
        
        Clientes cliente = appService.criaClienteOBJ("Teste de Cliente", "123456", "cliente@teste.com", "Rua de teste", "Bairro de Teste", "São Paulo", "SP", "12345-678");
        
        Pedidos pedido = appService.criaPedidoOBJ(cliente, "31/12/2019", "Teste de Status", "Teste de Sessao");
        
        PedidoItens pedido_item = appService.criaPedidoItemOBJ(produto, pedido, "Teste de Produto Item", 3, new BigDecimal("14.95"), new BigDecimal("59.29"));
        
        Mockito.when(pedidoItensRepository.save(Mockito.any(PedidoItens.class)))
            .thenReturn(pedido_item);
        
        PedidoItens obj = appService.getPedidoItensRepository().save(pedido_item);
        assertNotNull(obj);
    }
}
