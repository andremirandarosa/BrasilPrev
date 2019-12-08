package br.com.brasilprev.desafio.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "clientes", schema = "SCHEMA_BRASILPREV")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Clientes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE) 
    @Column(name = "idCliente", updatable = false, nullable = false)
    private Long idCliente;
            
    @Column(name = "nome")
    private String nome;
        
    @Column(name = "email")
    private String email;
        
    @JsonIgnore
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "rua")
    private String rua;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "cep")
    private String cep;
}
