package br.com.fiap.bo;

import br.com.fiap.dao.CuidadorDAO;
import br.com.fiap.to.CuidadorTO;

import java.util.ArrayList;

public class CuidadorBO {
    private CuidadorDAO cuidadorDAO;

    public CuidadorTO save(CuidadorTO cuidador) {
        cuidadorDAO = new CuidadorDAO();

        return cuidadorDAO.save(cuidador);
    }

    public CuidadorTO update(CuidadorTO cuidador) {
        cuidadorDAO = new CuidadorDAO();

        // Se id for vazio
        if (cuidador.getId() == null) {
            return null;
        }

        return cuidadorDAO.update(cuidador);
    }

    public boolean delete(Long id) {
        cuidadorDAO = new CuidadorDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return cuidadorDAO.delete(id);
    }

    public CuidadorTO findById(Long id) {
        cuidadorDAO = new CuidadorDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return cuidadorDAO.findById(id);
    }

    public ArrayList<CuidadorTO> findAll() {
        cuidadorDAO = new CuidadorDAO();

        return cuidadorDAO.findAll();
    }
}
