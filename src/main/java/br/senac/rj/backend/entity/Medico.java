package br.senac.rj.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * 
 * @author luccas.bentim
 * Entidade que representa a tabela MEDICO.
 */
@Entity
@Table(name = "MEDICO")
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

    @ManyToMany
    @JoinTable(
        name = "MEDICO_CONVENIO",
        joinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id_medico"),
        inverseJoinColumns = @JoinColumn(name = "id_convenio", referencedColumnName = "id_convenio")
    )
    private Set<Convenio> convenios = new HashSet<>();
}
