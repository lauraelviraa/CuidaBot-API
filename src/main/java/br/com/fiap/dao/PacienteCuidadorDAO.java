package br.com.fiap.dao;

import br.com.fiap.to.CuidadorTO;
import br.com.fiap.to.PacienteCuidadorTO;
import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PacienteCuidadorDAO {

    public PacienteCuidadorTO save(PacienteCuidadorTO pacienteCuidador) {
        String sql = "INSERT INTO T_PACIENTE_CUIDADOR(id_paciente, id_cuidador, papel, data_inicio, data_fim) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, pacienteCuidador.getPaciente().getId());
            preparedStatement.setLong(2, pacienteCuidador.getCuidador().getId());
            preparedStatement.setString(3, pacienteCuidador.getPapel());
            preparedStatement.setDate(4, Date.valueOf(pacienteCuidador.getDataInicio()));
            preparedStatement.setDate(5, Date.valueOf(pacienteCuidador.getDataFim()));

            if (preparedStatement.executeUpdate() > 0) {
                return pacienteCuidador;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar paciente cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PacienteCuidadorTO update(PacienteCuidadorTO pacienteCuidador) {
        String sql = "UPDATE T_PACIENTE_CUIDADOR SET id_paciente = ?, id_cuidador = ?, papel = ?, data_inicio = ?, data_fim = ? WHERE id_paciente_cuidador = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, pacienteCuidador.getPaciente().getId());
            preparedStatement.setLong(2, pacienteCuidador.getCuidador().getId());
            preparedStatement.setString(3, pacienteCuidador.getPapel());
            preparedStatement.setDate(4, Date.valueOf(pacienteCuidador.getDataInicio()));
            preparedStatement.setDate(5, Date.valueOf(pacienteCuidador.getDataFim()));
            preparedStatement.setLong(6, pacienteCuidador.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return pacienteCuidador;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_PACIENTE_CUIDADOR WHERE id_paciente_cuidador = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PacienteCuidadorTO findById(Long id) {
        String sql = "SELECT * FROM T_PACIENTE_CUIDADOR WHERE id_paciente_cuidador = ?";
        PacienteCuidadorTO pacienteCuidador = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pacienteCuidador = new PacienteCuidadorTO();
                pacienteCuidador.setId(resultSet.getLong("id_paciente_cuidador"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                pacienteCuidador.setPaciente(paciente);

                CuidadorDAO cuidadorDAO = new CuidadorDAO();
                CuidadorTO cuidador = cuidadorDAO.findById(resultSet.getLong("id_cuidador"));
                pacienteCuidador.setCuidador(cuidador);

                pacienteCuidador.setPapel(resultSet.getString("papel"));
                pacienteCuidador.setDataInicio(resultSet.getDate("data_inicio").toLocalDate());
                pacienteCuidador.setDataFim(resultSet.getDate("data_fim").toLocalDate());

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacienteCuidador;
    }

    public ArrayList<PacienteCuidadorTO> findAll() {
        String sql = "SELECT * FROM T_PACIENTE_CUIDADOR ORDER BY id_paciente_cuidador";
        ArrayList<PacienteCuidadorTO> pacientesCuidadores = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    PacienteCuidadorTO pacienteCuidador = new PacienteCuidadorTO();
                    pacienteCuidador.setId(resultSet.getLong("id_paciente_cuidador"));

                    PacienteDAO pacienteDAO = new PacienteDAO();
                    PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                    pacienteCuidador.setPaciente(paciente);

                    CuidadorDAO cuidadorDAO = new CuidadorDAO();
                    CuidadorTO cuidador = cuidadorDAO.findById(resultSet.getLong("id_cuidador"));
                    pacienteCuidador.setCuidador(cuidador);

                    pacienteCuidador.setPapel(resultSet.getString("papel"));
                    pacienteCuidador.setDataInicio(resultSet.getDate("data_inicio").toLocalDate());
                    pacienteCuidador.setDataFim(resultSet.getDate("data_fim").toLocalDate());

                    pacientesCuidadores.add(pacienteCuidador);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar pacientes cuidadores: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacientesCuidadores;
    }
}
