package Repository;

import ConnectionFactory.ConnectionFactory;
import Model.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class DisciplinaRepository {

    public static boolean insert(Disciplina disciplina) {
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "INSERT INTO DISCIPLINA (CODIGO_CURSO, NOME_PROFESSOR, SEMESTRE, ANO) "
                + "VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            Date date = new Date();

            // Pega a data Local, utilizando a configuração padrão do sistema
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            stmt.setInt(1, disciplina.getCurso().getCodigo());
            stmt.setString(2, disciplina.getNomeProfessor());

            // Verifica se o mês faz parte do primeiro o do segundo semestre utilizando operação ternária
            stmt.setString(3, localDate.getMonthValue() >= 1 && localDate.getMonthValue() <= 6 ? "Semestre 1" : "Semestre 2");

            // Pega o ano pela data local
            stmt.setInt(4, localDate.getYear());

            stmt.execute();

            connection.close();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static Disciplina selecionarUltimoAdicionado() {
        Disciplina disciplina = new Disciplina();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM DISCIPLINA WHERE CODIGO = ((SELECT MAX(CODIGO) FROM DISCIPLINA))";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                disciplina = new Disciplina(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME_PROFESSOR"),
                        rs.getString("SEMESTRE"),
                        rs.getInt("ANO"),
                        CursoRepository.selecionarPorCodigo(rs.getInt("CODIGO_CURSO"))
                );
            }

            stmt.close();
            connection.close();
            return disciplina;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static List<Disciplina> selecionarTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM DISCIPLINA";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                disciplinas.add(new Disciplina(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME_PROFESSOR"),
                        rs.getString("SEMESTRE"),
                        rs.getInt("ANO"),
                        CursoRepository.selecionarPorCodigo(rs.getInt("CODIGO_CURSO"))
                ));
            }

            stmt.close();
            connection.close();
            return disciplinas;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static Disciplina selecionarPorCodigo(int codigo) {
        Disciplina disciplina = new Disciplina();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM DISCIPLINA WHERE CODIGO = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                disciplina = new Disciplina(
                        rs.getInt("CODIGO"),
                        rs.getString("NOME_PROFESSOR"),
                        rs.getString("SEMESTRE"),
                        rs.getInt("ANO"),
                        CursoRepository.selecionarPorCodigo(rs.getInt("CODIGO_CURSO"))
                );
            }

            stmt.close();
            connection.close();
            return disciplina;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

}
