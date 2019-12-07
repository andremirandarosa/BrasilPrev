package br.com.brasilprev.desafio.model.repository;

import br.com.brasilprev.desafio.model.entity.Clientes;
import org.junit.Assert;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClientesRepositoryTest {
   
    @Mock
    ClientesRepository clientesRepository;
    
    //==========================================================================
    
    @Test
    public void test() {
        
        Clientes c = new Clientes();
        c.setIdCliente(0L);
        assertNotNull(c.toString());
        
        Clientes c_mock = Clientes.builder()
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
        
        Mockito.when(clientesRepository.save(Mockito.any(Clientes.class)))
               .thenReturn(c_mock);
                
        Clientes c_saved = clientesRepository.save(new Clientes());
        
        assertNotNull(c_saved);
        assertNotNull(c_saved.getIdCliente());
        
        Assert.assertTrue(c_saved.getIdCliente() == 0L);
        Assert.assertEquals("Teste de Cliente", c_saved.getNome());
        Assert.assertEquals("123456", c_saved.getSenha());
        Assert.assertEquals("cliente@teste.com", c_saved.getEmail());
        Assert.assertEquals("Rua de teste", c_saved.getRua());
        Assert.assertEquals("Bairro de Teste", c_saved.getBairro());
        Assert.assertEquals("São Paulo", c_saved.getCidade());
        Assert.assertEquals("SP", c_saved.getEstado());
        Assert.assertEquals("12345-678", c_saved.getCep());
    }
}
