package br.com.fiap.dao;

import br.com.fiap.to.MedicacaoTO;
import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicacaoDAO {

    public MedicacaoTO save(MedicacaoTO medicacao) {
        String sql = "INSERT INTO T_MEDICACAO(id_paciente, nome, dosagem, frequencias_horas, horario_inicio, ativo) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, medicacao.getPaciente().getId());
            preparedStatement.setString(2, medicacao.getNome());
            preparedStatement.setString(3, medicacao.getDosagem());
            preparedStatement.setInt(4, medicacao.getFrequenciaHoras());
            preparedStatement.setDate(5, Date.valueOf(medicacao.getData()));
            preparedStatement.setString(6, String.valueOf(medicacao.getAtivo()));

            if (preparedStatement.executeUpdate() > 0) {
                return medicacao;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar medicacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public MedicacaoTO update(MedicacaoTO medicacao) {
        String sql = "UPDATE T_MEDICACAO SET id_paciente = ?, nome = ?, dosagem = ?, frequencias_horas = ?, horario_inicio = ?, ativo = ? WHERE id_medicacao = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, medicacao.getPaciente().getId());
            preparedStatement.setString(2, medicacao.getNome());
            preparedStatement.setString(3, medicacao.getDosagem());
            preparedStatement.setInt(4, medicacao.getFrequenciaHoras());
            preparedStatement.setDate(5, Date.valueOf(medicacao.getData()));
            preparedStatement.setString(6, String.valueOf(medicacao.getAtivo()));
            preparedStatement.setLong(7, medicacao.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return medicacao;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar medicacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_MEDICACAO WHERE id_medicacao = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir medicacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public MedicacaoTO findById(Long id) {
        String sql = "SELECT * FROM T_MEDICACAO WHERE id_medicacao = ?";
        MedicacaoTO medicacao = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medicacao = new MedicacaoTO();
                medicacao.setId(resultSet.getLong("id_medicacao"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                medicacao.setPaciente(paciente);

                medicacao.setNome(resultSet.getString("nome"));
                medicacao.setDosagem(resultSet.getString("dosagem"));
                medicacao.setFrequenciaHoras(resultSet.getInt("frequencias_horas"));
                medicacao.setData(resultSet.getDate("horario_inicio").toLocalDate());
                medicacao.setAtivo(resultSet.getString("ativo").charAt(0));

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar medicacao: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return medicacao;
    }

    public ArrayList<MedicacaoTO> findAll() {
        String sql = "SELECT * FROM T_MEDICACAO ORDER BY id_medicacao";
        ArrayList<MedicacaoTO> medicacoes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    MedicacaoTO medicacao = new MedicacaoTO();
                    medicacao.setId(resultSet.getLong("id_medicacao"));

                    PacienteDAO pacienteDAO = new PacienteDAO();
                    PacienteTO paciente = pacienteDAO.findById(resultSet.getLong("id_paciente"));
                    medicacao.setPaciente(paciente);

                    medicacao.setNome(resultSet.getString("nome"));
                    medicacao.setDosagem(resultSet.getString("dosagem"));
                    medicacao.setFrequenciaHoras(resultSet.getInt("frequencias_horas"));
                    medicacao.setData(resultSet.getDate("horario_inicio").toLocalDate());
                    medicacao.setAtivo(resultSet.getString("ativo").charAt(0));

                    medicacoes.add(medicacao);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar medicacoes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return medicacoes;
    }
}
