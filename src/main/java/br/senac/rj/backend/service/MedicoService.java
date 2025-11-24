package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.MedicoDao;
import br.senac.rj.backend.entity.Medico;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Serviço com a lógica de negócio para Medico.
 */
public class MedicoService {

    private final MedicoDao dao = new MedicoDao();

    /**
     * Busca médicos com filtros opcionais.
     * Se todos os filtros forem nulos, retorna todos.
     */
    public List<Medico> buscar(String nome, String especialidade, String endereco, Integer convenioId) {
        // Prioriza filtro de convênio quando presente
        if (convenioId != null) {
            return dao.buscarPorConvenioId(convenioId);
        }
        // Caso haja outros filtros, aplica em ordem: nome, especialidade, endereco
        if (nome != null && !nome.isBlank()) {
            return dao.buscarPorNome(nome);
        }
        if (especialidade != null && !especialidade.isBlank()) {
            return dao.buscarPorEspecialidade(especialidade);
        }
        if (endereco != null && !endereco.isBlank()) {
            return dao.buscarPorEndereco(endereco);
        }
        // nenhum filtro: lista todos
        return dao.listarTodos();
    }

    public Medico buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    /**
     * Salva ou atualiza médico.
     */
    public Response salvar(Medico medico) {
        Medico salvo = dao.salvar(medico);
        if (salvo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível salvar o médico.\"}")
                    .build();
        }
        return Response.ok(salvo).build();
    }
}