package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Categorias;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriasServiceTest {
    
    @Autowired
    CategoriasService categoriasService;
    
    //==========================================================================

    @Test
    public void testCria() {
        
        Optional<Categorias> op = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        assertTrue(op.get().getIdCategoria() >= 0);
        assertEquals("Teste de Categoria", op.get().getCategoria());
    }

    @Test
    public void testAltera() {
     
        Optional<Categorias> op = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCategoria();
        assertTrue(id >= 0);
        assertEquals("Teste de Categoria", op.get().getCategoria());
        
        //======================================================================
        
        Optional<Categorias> op2 = categoriasService.altera(id, "Teste de Categoria Alterada");
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertEquals(id, op2.get().getIdCategoria());
        assertEquals("Teste de Categoria Alterada", op2.get().getCategoria());
    }
   
    @Test
    public void testBusca() {
        
        Optional<Categorias> op = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCategoria();
        assertTrue(id >= 0);
        assertEquals("Teste de Categoria", op.get().getCategoria());
        
        //======================================================================
        
        Optional<Categorias> op2 = categoriasService.busca(id);
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertEquals(id, op2.get().getIdCategoria());
        assertEquals("Teste de Categoria", op.get().getCategoria());
    }

    @Test
    public void testLista() {
        
        Optional<Categorias> op = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCategoria();
        assertTrue(id >= 0);
        assertEquals("Teste de Categoria", op.get().getCategoria());
        
        //======================================================================
        
        Optional<List<Categorias>> op2 = categoriasService.lista();
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertTrue(op2.get().size() > 0);
        assertEquals("Teste de Categoria", op2.get().get(0).getCategoria());
    }
  
    @Test
    public void testRemove() {
        
        Optional<Categorias> op = categoriasService.cria("Teste de Categoria");
        
        assertTrue(op.isPresent());
        assertNotNull(op.get());
        Long id = op.get().getIdCategoria();
        assertTrue(id >= 0);
        assertEquals("Teste de Categoria", op.get().getCategoria());
        
        //======================================================================
        
        int size_antes = categoriasService.lista().get().size();
        
        categoriasService.remove(id);
        
        int size_depois = categoriasService.lista().get().size();
        
        assertEquals(1, size_antes - size_depois);
    }
}
