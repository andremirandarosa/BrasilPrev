package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.PedidoItens;
import br.com.brasilprev.desafio.model.entity.Pedidos;
import br.com.brasilprev.desafio.model.entity.Produtos;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PedidoItensRepositoryTest {
        
    @Mock
    PedidoItensRepository pedidoItensRepository;
    
    //==========================================================================
    @Test
    public void test() {
      
        PedidoItens p = new PedidoItens();
        p.setIdItem(0L);
        assertNotNull(p.toString());
        
        Categorias categoria = Categorias.builder()
                                .idCategoria(0L)
                                .categoria("Teste de Categoria")
                              .build();
            
        Produtos produto = Produtos.builder()
                                .idProduto(1L)
                                .idCategoria(categoria)
                                .produto("Teste de Produto")
                                .preco(new BigDecimal("1.99"))
                                .quantidade(1)
                                .descricao("Teste de Descricao")
                                .foto("Foto em Base 64")
                              .build();
        
        Clientes cliente = Clientes.builder()
                                .idCliente(0L)
                                .nome("Teste de Cliente")
                                .senha("123456")
                                .email("cliente@teste.com")
                                .rua("Rua de teste")
                                .bairro("Bairro de Teste")
                                .cidade("São Paulo")
                                .estado("SP")
                                .cep("12345-678")
                              .build();
        
        Pedidos pedido = Pedidos.builder()
                                .idPedido(2L)
                                .idCliente(cliente)
                                .data("31/12/2019")
                                .status("Teste de Status")
                                .sessao("Teste de Sessao")
                            .build();
        
        PedidoItens mock = PedidoItens.builder()
                                        .idItem(0L)
                                        .idProduto(produto)
                                        .idPedido(pedido)
                                        .produto("Teste de Produto Item")
                                        .quantidade(2)
                                        .valor(new BigDecimal("4.95"))
                                        .subtotal(new BigDecimal("9.9"))
                                       .build();
        
        
         Mockito.when(pedidoItensRepository.save(Mockito.any(PedidoItens.class)))
               .thenReturn(mock);
                
        PedidoItens p_saved = pedidoItensRepository.save(new PedidoItens());
        
        assertNotNull(p_saved);
        assertNotNull(p_saved.getIdItem());
        
        assertNotNull(p_saved.getIdProduto());
        Assert.assertTrue(p_saved.getIdProduto().getIdProduto() == 1L);
        Assert.assertEquals("Teste de Descricao", p_saved.getIdProduto().getDescricao());
        
        assertNotNull(p_saved.getIdPedido());
        Assert.assertTrue(p_saved.getIdPedido().getIdPedido() == 2L);
        Assert.assertEquals("31/12/2019", p_saved.getIdPedido().getData());
        
        Assert.assertEquals("Teste de Produto Item", p_saved.getProduto());
        Assert.assertEquals(2, (int) p_saved.getQuantidade());
        Assert.assertEquals(new BigDecimal("4.95"), p_saved.getValor());
        Assert.assertEquals(new BigDecimal("9.9"), p_saved.getSubtotal());
    }
}
