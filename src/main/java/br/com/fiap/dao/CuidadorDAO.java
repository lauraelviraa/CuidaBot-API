package br.com.fiap.dao;

import br.com.fiap.to.CuidadorTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CuidadorDAO {

    public CuidadorTO save(CuidadorTO cuidador) {
        String sql = "INSERT INTO T_CUIDADOR(nome, parentesco, telefone, email) VALUES(?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, cuidador.getNome());
            preparedStatement.setString(2, cuidador.getParentesco());
            preparedStatement.setString(3, cuidador.getTelefone());
            preparedStatement.setString(4, cuidador.getEmail());

            if (preparedStatement.executeUpdate() > 0) {
                return cuidador;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public CuidadorTO update(CuidadorTO cuidador) {
        String sql = "UPDATE T_CUIDADOR SET nome = ?, parentesco = ?, telefone = ?, email = ? WHERE id_cuidador = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, cuidador.getNome());
            preparedStatement.setString(2, cuidador.getParentesco());
            preparedStatement.setString(3, cuidador.getTelefone());
            preparedStatement.setString(4, cuidador.getEmail());
            preparedStatement.setLong(5, cuidador.getId());

            if (preparedStatement.executeUpdate() > 0) {
                return cuidador;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_CUIDADOR WHERE id_cuidador = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public CuidadorTO findById(Long id) {
        String sql = "SELECT * FROM T_CUIDADOR WHERE id_cuidador = ?";
        CuidadorTO cuidador = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cuidador = new CuidadorTO();
                cuidador.setId(resultSet.getLong("id_cuidador"));
                cuidador.setNome(resultSet.getString("nome"));
                cuidador.setNome(resultSet.getString("parentesco"));
                cuidador.setNome(resultSet.getString("telefone"));
                cuidador.setNome(resultSet.getString("email"));

            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return cuidador;
    }

    public ArrayList<CuidadorTO> findAll() {
        String sql = "SELECT * FROM T_CUIDADOR ORDER BY id_cuidador";
        ArrayList<CuidadorTO> cuidadores = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    CuidadorTO cuidador = new CuidadorTO();
                    cuidador.setId(resultSet.getLong("id_cuidador"));
                    cuidador.setNome(resultSet.getString("nome"));
                    cuidador.setNome(resultSet.getString("parentesco"));
                    cuidador.setNome(resultSet.getString("telefone"));
                    cuidador.setNome(resultSet.getString("email"));

                    cuidadores.add(cuidador);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar cuidadores: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return cuidadores;
    }
}
