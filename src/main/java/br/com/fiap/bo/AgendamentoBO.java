package br.com.fiap.bo;

import br.com.fiap.dao.AgendamentoDAO;
import br.com.fiap.to.AgendamentoTO;

import java.util.ArrayList;

public class AgendamentoBO {
    private AgendamentoDAO agendamentoDAO;

    public AgendamentoTO save(AgendamentoTO agendamento) {
        agendamentoDAO = new AgendamentoDAO();

        return agendamentoDAO.save(agendamento);
    }

    public AgendamentoTO update(AgendamentoTO agendamento) {
        agendamentoDAO = new AgendamentoDAO();

        // Se id for vazio
        if (agendamento.getId() == null) {
            return null;
        }

        return agendamentoDAO.update(agendamento);
    }

    public boolean delete(Long id) {
        agendamentoDAO = new AgendamentoDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return agendamentoDAO.delete(id);
    }

    public AgendamentoTO findById(Long id) {
        agendamentoDAO = new AgendamentoDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return agendamentoDAO.findById(id);
    }

    public ArrayList<AgendamentoTO> findAll() {
        agendamentoDAO = new AgendamentoDAO();

        return agendamentoDAO.findAll();
    }
}
