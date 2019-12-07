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
@Table(name = "produtos", schema = "SCHEMA_BRASILPREV")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produtos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE) 
    @Column(name = "idProduto", updatable = false, nullable = false)
    private Long idProduto;
            
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    @JoinColumn(name="idCategoria")
    @Fetch(FetchMode.SELECT)
    private Categorias idCategoria;
    
    @Column(name = "produto")
    private String produto;
    
    @Column(name = "preco")
    private BigDecimal preco;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "foto")
    private String foto; //Base64
}
