package br.com.brasilprev.desafio.model.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "pedidoItens", schema = "SCHEMA_BRASILPREV")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoItens implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE) 
    @Column(name = "idItem", updatable = false, nullable = false)
    private Long idItem;
            
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    @JoinColumn(name="idProduto")
    @Fetch(FetchMode.SELECT)
    private Produtos idProduto;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    @JoinColumn(name="idPedido")
    @Fetch(FetchMode.SELECT)
    private Pedidos idPedido;
    
    @Column(name = "produto")
    private String produto;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
    @Column(name = "valor")
    private BigDecimal valor;
    
    @Column(name = "subtotal")
    private BigDecimal subtotal;
}
