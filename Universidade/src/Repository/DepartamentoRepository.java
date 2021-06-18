package Repository;

import ConnectionFactory.ConnectionFactory;
import Model.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class DepartamentoRepository {

    public static boolean inserir(Departamento departamento) {
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "INSERT INTO DEPARTAMENTO (NOME, NUMERO_SALA, TELEFONE_SALA) "
                + "VALUES(?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            stmt.setString(1, departamento.getNome());
            stmt.setInt(2, departamento.getNumeroSala());
            stmt.setString(3, departamento.getTelefoneSala());

            stmt.execute();

            connection.close();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static Departamento selecionarUltimoAdicionado() {
        Departamento departamento = new Departamento();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM DEPARTAMENTO WHERE CODIGO = ((SELECT MAX(CODIGO) FROM DEPARTAMENTO))";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                departamento = new Departamento(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getInt("NUMERO_SALA"),
                        rs.getString("TELEFONE_SALA")
                );
            }

            stmt.close();
            connection.close();
            return departamento;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static List<Departamento> selecionarTodos() {
        List<Departamento> departamentos = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM DEPARTAMENTO";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                departamentos.add(new Departamento(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getInt("NUMERO_SALA"),
                        rs.getString("TELEFONE_SALA")
                ));
            }

            stmt.close();
            connection.close();
            return departamentos;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static Departamento selecionarPorCodigo(int codigo) {
        Departamento departamento = new Departamento();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM DEPARTAMENTO WHERE CODIGO = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                departamento = new Departamento(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getInt("NUMERO_SALA"),
                        rs.getString("TELEFONE_SALA")
                );
            }

            stmt.close();
            connection.close();
            return departamento;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
