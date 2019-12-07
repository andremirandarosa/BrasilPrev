package br.com.brasilprev.desafio.service;

import br.com.brasilprev.desafio.model.entity.Categorias;
import br.com.brasilprev.desafio.model.entity.Produtos;
import java.math.BigDecimal;
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
public class ProdutosServiceTest {
    
    @Autowired
    CategoriasService categoriasService;
    
    @Autowired
    ProdutosService produtosService;
    
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
        assertTrue(op_produtos.get().getIdProduto() >= 0);
        assertEquals("Teste de Produto", op_produtos.get().getProduto());
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
        
        //======================================================================
        
        Optional<Produtos> op_produtos_2 = produtosService.altera(id_produto, id_categoria, "Teste de Produto Alterado", new BigDecimal("4.25"), 8, "Teste de Descricao 2", "Foto em Base 64 33");
        
        assertTrue(op_produtos_2.isPresent());
        assertNotNull(op_produtos_2.get());
        Long id = op_produtos_2.get().getIdProduto();
        assertTrue(id >= 0);
        assertEquals("Teste de Produto Alterado", op_produtos_2.get().getProduto());
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
        
        //======================================================================
        
        Optional<Produtos> op_produtos_2 = produtosService.busca(id_produto);
        
        assertTrue(op_produtos_2.isPresent());
        assertNotNull(op_produtos_2.get());
        Long id = op_produtos_2.get().getIdProduto();
        assertTrue(id >= 0);
        assertEquals("Teste de Produto", op_produtos_2.get().getProduto());
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
        
        //======================================================================
        
        Optional<List<Produtos>> op2 = produtosService.lista();
        
        assertTrue(op2.isPresent());
        assertNotNull(op2.get());
        assertTrue(op2.get().size() > 0);
        assertEquals("Foto em Base 64", op2.get().get(0).getFoto());
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
        
        //======================================================================
        
        int size_antes = produtosService.lista().get().size();
        
        produtosService.remove(id_produto);
        
        int size_depois = produtosService.lista().get().size();
        
        assertEquals(1, size_antes - size_depois);
    }
}
