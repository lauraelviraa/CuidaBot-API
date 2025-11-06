package br.com.fiap.bo;

import br.com.fiap.dao.MedicacaoDAO;
import br.com.fiap.to.MedicacaoTO;

import java.util.ArrayList;

public class MedicacaoBO {
    private MedicacaoDAO medicacaoDAO;

    public MedicacaoTO save(MedicacaoTO medicacao) {
        medicacaoDAO = new MedicacaoDAO();

        return medicacaoDAO.save(medicacao);
    }

    public MedicacaoTO update(MedicacaoTO medicacao) {
        medicacaoDAO = new MedicacaoDAO();

        // Se id for vazio
        if (medicacao.getId() == null) {
            return null;
        }

        return medicacaoDAO.update(medicacao);
    }

    public boolean delete(Long id) {
        medicacaoDAO = new MedicacaoDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return medicacaoDAO.delete(id);
    }

    public MedicacaoTO findById(Long id) {
        medicacaoDAO = new MedicacaoDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return medicacaoDAO.findById(id);
    }

    public ArrayList<MedicacaoTO> findAll() {
        medicacaoDAO = new MedicacaoDAO();

        return medicacaoDAO.findAll();
    }
}
