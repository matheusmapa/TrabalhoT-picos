package Repository;

import ConnectionFactory.ConnectionFactory;
import Model.Disciplina;
import Model.Historico;
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
public class HistoricoRepository {

    public static boolean insert(Historico historico) {
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "INSERT INTO HISTORICO (CODIGO_ALUNO, CODIGO_DISCIPLINA, NOTA) "
                + "VALUES(?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            stmt.setInt(1, historico.getAluno().getNumeroMatricula());
            stmt.setInt(2, historico.getDisciplina().getCodigo());
            stmt.setDouble(3, historico.getNota());

            stmt.execute();

            connection.close();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static Historico selecionarUltimoAdicionado() {
        Historico historico = null;
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM HISTORICO WHERE CODIGO = ((SELECT MAX(CODIGO) FROM HISTORICO))";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                historico = new Historico(
                        rs.getInt("CODIGO"),
                        AlunoRepository.selecionarPorCodigo(rs.getInt("CODIGO_ALUNO")),
                        DisciplinaRepository.selecionarPorCodigo(rs.getInt("CODIGO_DISCIPLINA")),
                        rs.getDouble("NOTA")
                );
            }

            stmt.close();
            connection.close();
            return historico;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static List<Historico> selecionarPorDisciplina(Disciplina disciplina) {
        List<Historico> historicos = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM HISTORICO WHERE CODIGO_DISCIPLINA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, disciplina.getCodigo());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                historicos.add(new Historico(
                        rs.getInt("CODIGO"),
                        AlunoRepository.selecionarPorCodigo(rs.getInt("CODIGO_ALUNO")),
                        DisciplinaRepository.selecionarPorCodigo(rs.getInt("CODIGO_DISCIPLINA")),
                        rs.getDouble("NOTA")
                ));
            }

            stmt.close();
            connection.close();
            return historicos;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static Historico selecionarPorCodigo(int codigo) {
        Historico historico = null;
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM HISTORICO WHERE CODIGO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                historico = new Historico(
                        rs.getInt("CODIGO"),
                        AlunoRepository.selecionarPorCodigo(rs.getInt("CODIGO_ALUNO")),
                        DisciplinaRepository.selecionarPorCodigo(rs.getInt("CODIGO_DISCIPLINA")),
                        rs.getDouble("NOTA")
                );
            }

            stmt.close();
            connection.close();
            return historico;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static boolean atualizar(Historico historico) {
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "UPDATE HISTORICO SET CODIGO_ALUNO = ?, CODIGO_DISCIPLINA = ?, NOTA = ? WHERE CODIGO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            stmt.setInt(1, historico.getAluno().getNumeroMatricula());
            stmt.setInt(2, historico.getDisciplina().getCodigo());
            stmt.setDouble(3, historico.getNota());
            stmt.setInt(4, historico.getCodigo());

            stmt.execute();

            connection.close();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }
}
