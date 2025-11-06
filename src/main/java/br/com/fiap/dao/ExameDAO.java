package br.com.fiap.dao;

import br.com.fiap.to.ExameTO;
import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExameDAO {

    public ExameTO save(ExameTO exame) {
        String sql = "INSERT INTO T_EXAME(id_paciente, nm_exame, status, dt_exame, resultado) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
                preparedStatement.setLong(1, exame.getPaciente().getId());
                preparedStatement.setString(2, exame.getNomeExame());
                preparedStatement.setString(3, exame.getStatus());
                preparedStatement.setDate(4, Date.valueOf(exame.getDataExame()));
                preparedStatement.setString(5, exame.getResultado());

            if (preparedStatement.executeUpdate() > 0) {
                return exame;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar exame: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ExameTO update(ExameTO exame) {
        String sql = "UPDATE T_EXAME SET id_paciente = ?, nm_exame = ?, status = ?, dt_exame = ?, resultado = ? WHERE id_exame = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, exame.getPaciente().getId());
            preparedStatement.setString(2, exame.getNomeExame());
            preparedStatement.setString(3, exame.getStatus());
            preparedStatement.setDate(4, Date.valueOf(exame.getDataExame()));
            preparedStatement.setString(5, exame.getResultado());
            preparedStatement.setLong(6, exame.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return exame;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar exame: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_EXAME WHERE id_exame = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir exame: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ExameTO findById(Long id) {
        String sql = "SELECT * FROM T_EXAME WHERE id_exame = ?";
        ExameTO exame = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exame = new ExameTO();
                exame.setId(resultSet.getLong("id_exame"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                exame.setPaciente(paciente);

                exame.setNomeExame(resultSet.getString("nm_exame"));
                exame.setStatus(resultSet.getString("status"));
                exame.setDataExame(resultSet.getDate("dt_exame").toLocalDate());
                exame.setResultado(resultSet.getString("resultado"));

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar exame: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return exame;
    }

    public ArrayList<ExameTO> findAll() {
        String sql = "SELECT * FROM T_EXAME ORDER BY id_exame";
        ArrayList<ExameTO> exames = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    ExameTO exame = new ExameTO();
                    exame.setId(resultSet.getLong("id_exame"));

                    PacienteDAO pacienteDAO = new PacienteDAO();
                    PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                    exame.setPaciente(paciente);

                    exame.setNomeExame(resultSet.getString("nm_exame"));
                    exame.setStatus(resultSet.getString("status"));
                    exame.setDataExame(resultSet.getDate("dt_exame").toLocalDate());
                    exame.setResultado(resultSet.getString("resultado"));

                    exames.add(exame);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar exames: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return exames;
    }
}
