package br.com.fiap.bo;

import br.com.fiap.dao.PacienteCuidadorDAO;
import br.com.fiap.to.PacienteCuidadorTO;

import java.util.ArrayList;

public class PacienteCuidadorBO {
    private PacienteCuidadorDAO pacienteCuidadorDAO;

    public PacienteCuidadorTO save(PacienteCuidadorTO pacienteCuidador) {
        pacienteCuidadorDAO = new PacienteCuidadorDAO();

        return pacienteCuidadorDAO.save(pacienteCuidador);
    }

    public PacienteCuidadorTO update(PacienteCuidadorTO pacienteCuidador) {
        pacienteCuidadorDAO = new PacienteCuidadorDAO();

        // Se id for vazio
        if (pacienteCuidador.getId() == null) {
            return null;
        }

        return pacienteCuidadorDAO.update(pacienteCuidador);
    }

    public boolean delete(Long id) {
        pacienteCuidadorDAO = new PacienteCuidadorDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return pacienteCuidadorDAO.delete(id);
    }

    public PacienteCuidadorTO findById(Long id) {
        pacienteCuidadorDAO = new PacienteCuidadorDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return pacienteCuidadorDAO.findById(id);
    }

    public ArrayList<PacienteCuidadorTO> findAll() {
        pacienteCuidadorDAO = new PacienteCuidadorDAO();

        return pacienteCuidadorDAO.findAll();
    }
}
