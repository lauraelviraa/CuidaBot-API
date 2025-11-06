package br.com.fiap.dao;

import br.com.fiap.to.NotificacaoTO;
import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NotificacaoDAO {

    public NotificacaoTO save(NotificacaoTO notificacao) {
        String sql = "INSERT INTO T_NOTIFICACAO(id_paciente, tipo, mensagem, titulo, data_envio, lida) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, notificacao.getPaciente().getId());
            preparedStatement.setString(2, notificacao.getTipo());
            preparedStatement.setString(3, notificacao.getMensagem());
            preparedStatement.setString(4, notificacao.getTitulo());
            preparedStatement.setDate(5, Date.valueOf(notificacao.getDataEnvio()));
            preparedStatement.setString(6, String.valueOf(notificacao.getLida()));

            if (preparedStatement.executeUpdate() > 0) {
                return notificacao;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar notificacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public NotificacaoTO update(NotificacaoTO notificacao) {
        String sql = "UPDATE T_NOTIFICACAO SET id_paciente = ?, tipo = ?, mensagem = ?, titulo = ?, data_envio = ?, lida = ? WHERE id_notificacao = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, notificacao.getPaciente().getId());
            preparedStatement.setString(2, notificacao.getTipo());
            preparedStatement.setString(3, notificacao.getMensagem());
            preparedStatement.setString(4, notificacao.getTitulo());
            preparedStatement.setDate(5, Date.valueOf(notificacao.getDataEnvio()));
            preparedStatement.setString(6, String.valueOf(notificacao.getLida()));
            preparedStatement.setLong(7, notificacao.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return notificacao;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar notificacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_NOTIFICACAO WHERE id_notificacao = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir notificacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public NotificacaoTO findById(Long id) {
        String sql = "SELECT * FROM T_NOTIFICACAO WHERE id_notificacao = ?";
        NotificacaoTO notificacao = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                notificacao = new NotificacaoTO();
                notificacao.setId(resultSet.getLong("id_notificacao"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                notificacao.setPaciente(paciente);

                notificacao.setTipo(resultSet.getString("tipo"));
                notificacao.setMensagem(resultSet.getString("mensagem"));
                notificacao.setTitulo(resultSet.getString("titulo"));
                notificacao.setDataEnvio(resultSet.getDate("data_envio").toLocalDate());
                notificacao.setLida(resultSet.getString("lida").charAt(0));

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar notificacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return notificacao;
    }

    public ArrayList<NotificacaoTO> findAll() {
        String sql = "SELECT * FROM T_NOTIFICACAO ORDER BY id_notificacao";
        ArrayList<NotificacaoTO> notificacoes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    NotificacaoTO notificacao = new NotificacaoTO();
                    notificacao.setId(resultSet.getLong("id_notificacao"));

                    PacienteDAO pacienteDAO = new PacienteDAO();
                    PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                    notificacao.setPaciente(paciente);

                    notificacao.setTipo(resultSet.getString("tipo"));
                    notificacao.setMensagem(resultSet.getString("mensagem"));
                    notificacao.setTitulo(resultSet.getString("titulo"));
                    notificacao.setDataEnvio(resultSet.getDate("data_envio").toLocalDate());
                    notificacao.setLida(resultSet.getString("lida").charAt(0));

                    notificacoes.add(notificacao);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar notificacoes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return notificacoes;
    }
}
