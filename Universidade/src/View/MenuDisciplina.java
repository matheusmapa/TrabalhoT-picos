package View;

import Model.Aluno;
import Model.Curso;
import Model.Disciplina;
import Repository.DisciplinaRepository;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MenuDisciplina {

    private final Scanner scanner;

    public MenuDisciplina() {
        scanner = new Scanner(System.in, "ISO-8859-1");
    }

    public Disciplina cadastrar() {
        System.out.println("-- CADASTRAR DISCIPLINA --");

        System.out.println("Digite o nome do Professor");
        String nomeProfessor = scanner.next();

        int opcao = 0;
        Curso curso = null;
        MenuCurso menuCurso = new MenuCurso();
        while (opcao != 1 && opcao != 2) {
            System.out.println("Selecione a opção de curso: ");
            System.out.println("1 - Novo Curso");
            System.out.println("2 - Listar Cursos");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1: {
                    curso = menuCurso.cadastrar();
                }
                case 2: {
                    curso = menuCurso.selecionarPorCodigo();
                }
                default: {
                    System.out.println("Opção inválida");
                }
            }
        }

        Disciplina disciplina = new Disciplina(nomeProfessor, curso);
        if (DisciplinaRepository.insert(disciplina)) {
            System.out.println("Disciplina adicionada com sucesso!");
            return DisciplinaRepository.selecionarUltimoAdicionado();
        } else {
            System.out.println("Disciplina não adicionada");
            return null;
        }
    }

    public Disciplina selecionarOpcao() {
        int opcao = 0;
        while (opcao != 1 && opcao != 2) {
            System.out.println("Selecione a opção de Disciplina: ");
            System.out.println("1 - Nova Disciplina");
            System.out.println("2 - Listar Disciplinas");
            opcao = Integer.valueOf(scanner.nextLine());
            switch (opcao) {
                case 1: {
                    return this.cadastrar();
                }
                case 2: {
                    return this.selecionarPorCodigo();
                }
                default: {
                    System.out.println("Opção inválida");
                }
            }
        }
        return null;
    }

    public Disciplina selecionarPorCodigo() {
        System.out.println("-- LISTA DE DISCIPLINAS --");
        List alunos = DisciplinaRepository.selecionarTodos();
        if (alunos.size() > 0) {
            alunos.forEach(aluno -> {
                System.out.println(aluno.toString());
                System.out.println("");
            });
            System.out.print("Digite o codigo da disciplina que deseja utilizar: ");
            int codigo = Integer.valueOf(scanner.nextLine());
            return DisciplinaRepository.selecionarPorCodigo(codigo);
        } else {
            System.out.println("Não existem disciplinas no banco de dados");
            return null;
        }
    }
}
