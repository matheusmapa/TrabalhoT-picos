package Repository;

import ConnectionFactory.ConnectionFactory;
import Model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class CursoRepository {

    public static boolean insert(Curso curso) {
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "INSERT INTO CURSO (COD_DEPARTAMENTO_REPONSAVEL, NOME, DESCRICAO, HORAS_SEMESTRAIS, NIVEL) "
                + "VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            stmt.setInt(1, curso.getDepartamentoResponsavel().getCodigo());
            stmt.setString(2, curso.getNome());
            stmt.setString(3, curso.getDescricao());
            stmt.setInt(4, curso.getHorasSemestrais());
            stmt.setInt(5, curso.getNivel());

            stmt.execute();

            connection.close();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static Curso selecionarUltimoAdicionado() {
        Curso curso = new Curso();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM CURSO WHERE CODIGO = ((SELECT MAX(CODIGO) FROM CURSO))";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                curso = new Curso(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRICAO"),
                        rs.getInt("HORAS_SEMESTRAIS"),
                        rs.getInt("NIVEL"),
                        DepartamentoRepository.selecionarPorCodigo(rs.getInt("COD_DEPARTAMENTO_REPONSAVEL"))
                );
            }

            stmt.close();
            connection.close();
            return curso;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static List<Curso> selecionarTodos() {
        List<Curso> cursos = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM CURSO";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRICAO"),
                        rs.getInt("HORAS_SEMESTRAIS"),
                        rs.getInt("NIVEL"),
                        DepartamentoRepository.selecionarPorCodigo(rs.getInt("COD_DEPARTAMENTO_REPONSAVEL"))
                ));
            }

            stmt.close();
            connection.close();
            return cursos;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static Curso selecionarPorCodigo(int codigo) {
        Curso curso = new Curso();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM CURSO WHERE CODIGO = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                curso = new Curso(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getString("DESCRICAO"),
                        rs.getInt("HORAS_SEMESTRAIS"),
                        rs.getInt("NIVEL"),
                        DepartamentoRepository.selecionarPorCodigo(rs.getInt("COD_DEPARTAMENTO_REPONSAVEL"))
                );
            }

            stmt.close();
            connection.close();
            return curso;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
