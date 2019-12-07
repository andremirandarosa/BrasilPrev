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
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AppService {
    
    @Autowired
    CategoriasRepository categoriasRepository;
    
    @Autowired
    ProdutosRepository produtosRepository;
    
    @Autowired
    ClientesRepository clientesRepository;
    
    @Autowired
    PedidosRepository pedidosRepository;
    
    @Autowired
    PedidoItensRepository pedidoItensRepository;
    
    //==========================================================================
    
    public Categorias criaCategoriaOBJ(String categoria){
        
        return Categorias.builder()
                            .categoria(categoria)
                          .build();
    }
    
    public Produtos criaProdutoOBJ(Categorias categoria, String produto, BigDecimal preco, int quantidade, String descricao, String foto){
        
        return Produtos.builder()
                    .idCategoria(categoria)
                    .produto(produto)
                    .preco(preco)
                    .quantidade(quantidade)
                    .descricao(descricao)
                    .foto(foto)
                  .build();
    }
    
    public Clientes criaClienteOBJ(String nome, String senha, String email, String rua, String bairro, String cidade, String estado, String cep){
        
        return Clientes.builder()
                        .nome(nome)
                        .senha(senha)
                        .email(email)
                        .rua(rua)
                        .bairro(bairro)
                        .cidade(cidade)
                        .estado(estado)
                        .cep(cep)
                      .build();
    }
    
    public Pedidos criaPedidoOBJ(Clientes cliente, String data, String status, String sessao){
        
        return Pedidos.builder()
                        .idCliente(cliente)
                        .data(data)
                        .status(status)
                        .sessao(sessao)
                       .build();
    }
    
    public PedidoItens criaPedidoItemOBJ(Produtos idProduto, Pedidos pedido, String produto, int quantidade, BigDecimal valor, BigDecimal subtotal){
    
        return PedidoItens.builder()              
                            .idProduto(idProduto)
                            .idPedido(pedido)
                            .produto(produto)
                            .quantidade(quantidade)
                            .valor(valor)
                            .subtotal(subtotal)
                           .build();
    }
    
    //==========================================================================
    
    public Categorias salvaCategoria(String categoria){
        
        return this.getCategoriasRepository().save(this.criaCategoriaOBJ(categoria));
    }
    
    public Produtos salvaProduto(Categorias categoria, String produto, BigDecimal preco, int quantidade, String descricao, String foto){
        
        return this.getProdutosRepository().save(this.criaProdutoOBJ(categoria, produto, preco, quantidade, descricao, foto));
    }
    
    public Clientes salvaCliente(String nome, String senha, String email, String rua, String bairro, String cidade, String estado, String cep){
        
        return this.getClientesRepository().save(this.criaClienteOBJ(nome, senha, email, rua, bairro, cidade, estado, cep));
    }
    
    public Pedidos salvaPedido(Clientes cliente, String data, String status, String sessao){
        
        return this.getPedidosRepository().save(this.criaPedidoOBJ(cliente, data, status, sessao));
    }
    
    public PedidoItens salvaPedidoItem(Produtos idProduto, Pedidos pedido, String produto, int quantidade, BigDecimal valor, BigDecimal subtotal){
    
        return this.getPedidoItensRepository().save(this.criaPedidoItemOBJ(idProduto, pedido, produto, quantidade, valor, subtotal));
    }
}
