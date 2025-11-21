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
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "CRM", length = 20, nullable = false, unique = true)
    private String crm;

    @Column(name = "nome_completo", length = 100, nullable = false)
    private String nomeCompleto;

    @Column(name = "especialidade", length = 100, nullable = false)
    private String especialidade;

    @Column(name = "endereco_medico", length = 255, nullable = false)
    private String enderecoMedico;

    @Column(name = "foto_medico", length = 255)
    private String fotoMedico;

    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @Column(name = "senha", length = 64, nullable = false)
    private String senha;
}
