package br.senac.rj.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 * @author reinaldo.jose
 * Entidade que representa a tabela usuario.
 */
@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String nome;
    
    @Column(length = 64, nullable = false)
    private String senha;

}
