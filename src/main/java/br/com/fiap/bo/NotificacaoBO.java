package br.com.fiap.bo;

import br.com.fiap.dao.NotificacaoDAO;
import br.com.fiap.to.NotificacaoTO;

import java.util.ArrayList;

public class NotificacaoBO {
    private NotificacaoDAO notificacaoDAO;

    public NotificacaoTO save(NotificacaoTO notificacao) {
        notificacaoDAO = new NotificacaoDAO();

        return notificacaoDAO.save(notificacao);
    }

    public NotificacaoTO update(NotificacaoTO notificacao) {
        notificacaoDAO = new NotificacaoDAO();

        // Se id for vazio
        if (notificacao.getId() == null) {
            return null;
        }

        return notificacaoDAO.update(notificacao);
    }

    public boolean delete(Long id) {
        notificacaoDAO = new NotificacaoDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return notificacaoDAO.delete(id);
    }

    public NotificacaoTO findById(Long id) {
        notificacaoDAO = new NotificacaoDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return notificacaoDAO.findById(id);
    }

    public ArrayList<NotificacaoTO> findAll() {
        notificacaoDAO = new NotificacaoDAO();

        return notificacaoDAO.findAll();
    }
}
