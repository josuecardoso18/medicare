package br.senac.rj.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidade que representa a tabela CONVENIO.
 */
@Entity
@Table(name = "CONVENIO")
@Data
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convenio")
    private Long idConvenio;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "img_convenio", length = 255)
    private String imgConvenio;
}