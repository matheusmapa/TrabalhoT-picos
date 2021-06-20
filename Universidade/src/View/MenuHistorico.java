package View;

import Model.Aluno;
import Model.Disciplina;
import Model.Historico;
import Repository.HistoricoRepository;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MenuHistorico {

    private final Scanner scanner;

    public MenuHistorico() {
        scanner = new Scanner(System.in, "ISO-8859-1");
    }

    public Historico cadastrar() {
        System.out.println("-- MATRICULAR ALUNO --");
        MenuAluno menuAluno = new MenuAluno();
        Aluno aluno = menuAluno.selecionarOpcao();

        MenuDisciplina menuDisciplina = new MenuDisciplina();
        Disciplina disciplina = menuDisciplina.selecionarOpcao();

        Historico historico = new Historico(aluno, disciplina, 0);
        if (HistoricoRepository.insert(historico)) {
            System.out.println("Historico adicionada com sucesso!");
            return HistoricoRepository.selecionarUltimoAdicionado();
        } else {
            System.out.println("Historico não adicionada");
            return null;
        }
    }

    public boolean lançarNota() {
        System.out.println("-- LANÇAR NOTA --");

        MenuDisciplina menu = new MenuDisciplina();
        Disciplina disciplina = menu.selecionarPorCodigo();

        List<Historico> historicos = HistoricoRepository.selecionarPorDisciplina(disciplina);

        System.out.println("-- LISTA HISTORICOS --");
        historicos.forEach(historico -> {
            System.out.println(historico.toString());
        });

        System.out.println("Digite o codigo do historico que deseja editar: ");
        int codigo = Integer.valueOf(scanner.nextLine());

        Historico historico = HistoricoRepository.selecionarPorCodigo(codigo);
        System.out.println(historico);
        System.out.println("Digite a nota final do aluno: ");
        historico.setNota(Double.valueOf(scanner.nextLine()));

        return HistoricoRepository.atualizar(historico);
    }

    public void listar() {
        MenuDisciplina menu = new MenuDisciplina();
        Disciplina disciplina = menu.selecionarPorCodigo();

        List<Historico> historicos = HistoricoRepository.selecionarPorDisciplina(disciplina);
        System.out.println("-- LISTA HISTORICOS --");
        historicos.forEach(historico -> {
            System.out.println(historico.toString());
        });
    }
}
