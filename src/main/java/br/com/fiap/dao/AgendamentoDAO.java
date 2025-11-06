package br.com.fiap.dao;

import br.com.fiap.to.AgendamentoTO;
import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AgendamentoDAO {

    public AgendamentoTO save(AgendamentoTO agendamento) {
        String sql = "INSERT INTO T_AGENDAMENTO(id_paciente, tipo, data_hora, status, canal) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, agendamento.getPaciente().getId());
            preparedStatement.setString(2, agendamento.getTipo());
            preparedStatement.setDate(3, Date.valueOf(agendamento.getData()));
            preparedStatement.setString(4, agendamento.getStatus());
            preparedStatement.setString(5, agendamento.getCanal());

            if (preparedStatement.executeUpdate() > 0) {
                return agendamento;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar agendamento: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public AgendamentoTO update(AgendamentoTO agendamento) {
        String sql = "UPDATE T_AGENDAMENTO SET id_paciente = ?, tipo = ?, data_hora = ?, status = ?, canal = ? WHERE id_agendamento = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, agendamento.getPaciente().getId());
            preparedStatement.setString(2, agendamento.getTipo());
            preparedStatement.setDate(3, Date.valueOf(agendamento.getData()));
            preparedStatement.setString(4, agendamento.getStatus());
            preparedStatement.setString(5, agendamento.getCanal());
            preparedStatement.setLong(6, agendamento.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return agendamento;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar agendamento: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_AGENDAMENTO WHERE id_agendamento = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir agendamento: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AgendamentoTO findById(Long id) {
        String sql = "SELECT * FROM T_AGENDAMENTO WHERE id_agendamento = ?";
        AgendamentoTO agendamento = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                agendamento = new AgendamentoTO();
                agendamento.setId(resultSet.getLong("id_agendamento"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                agendamento.setPaciente(paciente);

                agendamento.setTipo(resultSet.getString("tipo"));
                agendamento.setData(resultSet.getDate("data_hora").toLocalDate());
                agendamento.setStatus(resultSet.getString("status"));
                agendamento.setCanal(resultSet.getString("canal"));

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar agendamento: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return agendamento;
    }

    public ArrayList<AgendamentoTO> findAll() {
        String sql = "SELECT * FROM T_AGENDAMENTO ORDER BY id_agendamento";
        ArrayList<AgendamentoTO> agendamentos = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    AgendamentoTO agendamento = new AgendamentoTO();
                    agendamento.setId(resultSet.getLong("id_agendamento"));

                    PacienteDAO pacienteDAO = new PacienteDAO();
                    PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                    agendamento.setPaciente(paciente);

                    agendamento.setTipo(resultSet.getString("tipo"));
                    agendamento.setData(resultSet.getDate("data_hora").toLocalDate());
                    agendamento.setStatus(resultSet.getString("status"));
                    agendamento.setCanal(resultSet.getString("canal"));

                    agendamentos.add(agendamento);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar agendamentos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return agendamentos;
    }
}
