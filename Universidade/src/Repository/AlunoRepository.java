
// como todas as classes anteriores, repository, model e connection são referidas ao banco de dados essa parte esta diretamente envolvivada com o banco usado, o MySQL é aqui que a conexão se dar pro completo
package Repository;

import ConnectionFactory.ConnectionFactory;
import Model.Aluno;
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
public class AlunoRepository {

    public static boolean insert(Aluno aluno) {
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "INSERT INTO ALUNO ("
                + "CODIGO_DEPARTAMENTO_PRINCIPAL, "
                + "NOME, "
                + "CPF, "
                + "ENDERECO, "
                + "TELEFONE, "
                + "DATA_NASCIMENTO, "
                + "SEXO, "
                + "TURMA, "
                + "PROGRAMA) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            stmt.setInt(1, aluno.getDepartamentoPrincipal().getCodigo());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getEndereco());
            stmt.setString(5, aluno.getTelefone());

            //Converte java.util.date para java.sql.date
            stmt.setDate(6, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setString(7, String.valueOf(aluno.getSexo()));
            stmt.setString(8, aluno.getTurma());
            stmt.setString(9, aluno.getPrograma());

            stmt.execute();

            connection.close();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static Aluno selecionarUltimoAdicionado() {
        Aluno aluno = null;
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM ALUNO WHERE NUMERO_MATRICULA = ((SELECT MAX(NUMERO_MATRICULA) FROM ALUNO))";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                        rs.getInt("NUMERO_MATRICULA"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("ENDERECO"),
                        rs.getString("TELEFONE"),
                        rs.getDate("DATA_NASCIMENTO"),
                        rs.getString("SEXO").charAt(0),
                        rs.getString("TURMA"),
                        DepartamentoRepository.selecionarPorCodigo(rs.getInt("CODIGO_DEPARTAMENTO_PRINCIPAL")),
                        rs.getString("PROGRAMA")
                );
            }

            stmt.close();
            connection.close();
            return aluno;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static List<Aluno> selecionarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM ALUNO";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("NUMERO_MATRICULA"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("ENDERECO"),
                        rs.getString("TELEFONE"),
                        rs.getDate("DATA_NASCIMENTO"),
                        rs.getString("SEXO").charAt(0),
                        rs.getString("TURMA"),
                        DepartamentoRepository.selecionarPorCodigo(rs.getInt("CODIGO_DEPARTAMENTO_PRINCIPAL")),
                        rs.getString("PROGRAMA")
                ));
            }

            stmt.close();
            connection.close();
            return alunos;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static Aluno selecionarPorCodigo(int numeroMatricula) {
        Aluno aluno = new Aluno();
        Connection connection = ConnectionFactory.getConnection();

        String sqlQuery = "SELECT * FROM ALUNO WHERE NUMERO_MATRICULA = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, numeroMatricula);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                        rs.getInt("NUMERO_MATRICULA"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("ENDERECO"),
                        rs.getString("TELEFONE"),
                        rs.getDate("DATA_NASCIMENTO"),
                        rs.getString("SEXO").charAt(0),
                        rs.getString("TURMA"),
                        DepartamentoRepository.selecionarPorCodigo(rs.getInt("CODIGO_DEPARTAMENTO_PRINCIPAL")),
                        rs.getString("PROGRAMA")
                );
            }

            stmt.close();
            connection.close();
            return aluno;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
