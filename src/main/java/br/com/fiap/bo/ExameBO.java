package br.com.fiap.bo;

import br.com.fiap.dao.ExameDAO;
import br.com.fiap.to.ExameTO;

import java.util.ArrayList;

public class ExameBO {
    private ExameDAO exameDAO;

    public ExameTO save(ExameTO exame) {
        exameDAO = new ExameDAO();

        return exameDAO.save(exame);
    }

    public ExameTO update(ExameTO exame) {
        exameDAO = new ExameDAO();

        // Se id for vazio
        if (exame.getId() == null) {
            return null;
        }

        return exameDAO.update(exame);
    }

    public boolean delete(Long id) {
        exameDAO = new ExameDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return exameDAO.delete(id);
    }

    public ExameTO findById(Long id) {
        exameDAO = new ExameDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return exameDAO.findById(id);
    }

    public ArrayList<ExameTO> findAll() {
        exameDAO = new ExameDAO();

        return exameDAO.findAll();
    }
}
