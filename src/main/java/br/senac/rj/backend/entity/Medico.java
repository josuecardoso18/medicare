package br.senac.rj.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 * @author luccas.bentim
 * Entidade que representa a tabela medico.
 */
@Entity
@Table(name = "medico")
@Data
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name = "especialidade", length = 100, nullable = false)
    private String especialidade;

    @Column(name = "endereco", length = 255, nullable = false)
    private String endereco;

    @Column(name = "foto_perfil", length = 255)
    private String foto_perfil;
}
