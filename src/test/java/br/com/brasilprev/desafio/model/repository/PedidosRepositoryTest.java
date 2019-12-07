package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Clientes;
import br.com.brasilprev.desafio.model.entity.Pedidos;
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
public class PedidosRepositoryTest {
    
    @Mock
    PedidosRepository pedidosRepository;
    
    //==========================================================================
    
    @Test
    public void test() {
        
        Pedidos p = new Pedidos();
        p.setIdPedido(0L);
        assertNotNull(p.toString());
        
        Clientes c = Clientes.builder()
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
        
        
        Pedidos mock = Pedidos.builder()
                                .idPedido(0L)
                                .idCliente(c)
                                .data("31/12/2019")
                                .status("Teste de Status")
                                .sessao("Teste de Sessao")
                            .build();
        
        Mockito.when(pedidosRepository.save(Mockito.any(Pedidos.class)))
               .thenReturn(mock);
                
        Pedidos p_saved = pedidosRepository.save(new Pedidos());
        
        assertNotNull(p_saved);
        assertNotNull(p_saved.getIdPedido());
        
        assertNotNull(p_saved.getIdCliente());
        Assert.assertTrue(p_saved.getIdCliente().getIdCliente() == 0L);
        Assert.assertEquals("Teste de Cliente", p_saved.getIdCliente().getNome());
        
        Assert.assertEquals("31/12/2019", p_saved.getData());
        Assert.assertEquals("Teste de Status", p_saved.getStatus());
        Assert.assertEquals("Teste de Sessao", p_saved.getSessao());
    }
}
