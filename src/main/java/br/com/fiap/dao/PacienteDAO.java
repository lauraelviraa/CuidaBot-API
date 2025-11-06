package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PacienteDAO {

    public PacienteTO save(PacienteTO paciente) {
        String sql = "INSERT INTO T_PACIENTE(nome, cpf, data_nascimento, sexo, baixa_visao, leitor_tela) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setDate(3, Date.valueOf(paciente.getDataNascimento()));
            preparedStatement.setString(4, String.valueOf(paciente.getSexo()));
            preparedStatement.setString(5, String.valueOf(paciente.getBaixaVisao()));
            preparedStatement.setString(6, String.valueOf(paciente.getLeitorTela()));

            if (preparedStatement.executeUpdate() > 0) {
                return paciente;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PacienteTO update(PacienteTO paciente) {
        String sql = "UPDATE T_PACIENTE SET nome = ?, cpf = ?, data_nascimento = ?, sexo = ?, baixa_visao = ?, leitor_tela = ? WHERE id_paciente = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setDate(3, Date.valueOf(paciente.getDataNascimento()));
            preparedStatement.setString(4, String.valueOf(paciente.getSexo()));
            preparedStatement.setString(5, String.valueOf(paciente.getBaixaVisao()));
            preparedStatement.setString(6, String.valueOf(paciente.getLeitorTela()));
            preparedStatement.setLong(7, paciente.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return paciente;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_PACIENTE WHERE id_paciente = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PacienteTO findById(Long id) {
        String sql = "SELECT * FROM T_PACIENTE WHERE id_paciente = ?";
        PacienteTO paciente = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                paciente = new PacienteTO();
                paciente.setId(resultSet.getLong("id_paciente"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
                paciente.setSexo(resultSet.getString("sexo").charAt(0));
                paciente.setBaixaVisao(resultSet.getString("baixa_visao").charAt(0));
                paciente.setLeitorTela(resultSet.getString("leitor_tela").charAt(0));

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return paciente;
    }

    public ArrayList<PacienteTO> findAll() {
        String sql = "SELECT * FROM T_PACIENTE ORDER BY id_paciente";
        ArrayList<PacienteTO> pacientes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    PacienteTO paciente = new PacienteTO();
                    paciente.setId(resultSet.getLong("id_paciente"));
                    paciente.setId(resultSet.getLong("id_paciente"));
                    paciente.setNome(resultSet.getString("nome"));
                    paciente.setCpf(resultSet.getString("cpf"));
                    paciente.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
                    paciente.setSexo(resultSet.getString("sexo").charAt(0));
                    paciente.setBaixaVisao(resultSet.getString("baixa_visao").charAt(0));
                    paciente.setLeitorTela(resultSet.getString("leitor_tela").charAt(0));

                    pacientes.add(paciente);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacientes;
    }
}
