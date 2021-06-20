package View;

import Model.Aluno;
import Model.Departamento;
import Repository.AlunoRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MenuAluno {

    private final Scanner scanner;

    public MenuAluno() {
        scanner = new Scanner(System.in, "ISO-8859-1");
    }

    public Aluno cadastrar() {
        System.out.println("-- CADASTRAR ALUNO --");

        System.out.println("Digite o nome do Aluno: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do Aluno: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite o Endereço do Aluno: ");
        String endereco = scanner.nextLine();

        System.out.println("Digite o Telefone do Aluno: ");
        String telefone = scanner.nextLine();

        System.out.println("Digite a data de nascimento do Aluno (dd-MM-yyyy): ");
        Date data;
        try {
            data = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.err.println("Data digitada no formato errado");
            return null;
        }

        System.out.println("Digite o Sexo do Aluno (M ou F): ");
        char sexo = scanner.nextLine().charAt(0);

        System.out.println("Digite a Turma do Aluno: ");
        String turma = scanner.nextLine();

        System.out.println("Digite o Programa do Aluno: ");
        String programa = scanner.nextLine();

        MenuDepartamento menuDepartamento = new MenuDepartamento();
        Departamento departamentoPrincipal = menuDepartamento.selecionarOpcao();

        Aluno aluno = new Aluno(nome, cpf, endereco, telefone, data, sexo, turma, departamentoPrincipal, programa);
        if (AlunoRepository.insert(aluno)) {
            System.out.println("Aluno adicionado com sucesso!");
            return AlunoRepository.selecionarUltimoAdicionado();
        } else {
            System.out.println("Aluno não adicionado");
            return null;
        }
    }

    public Aluno selecionarPorCodigo() {
        System.out.println("-- LISTA DE ALUNOS --");
        List alunos = AlunoRepository.selecionarTodos();
        if (alunos.size() > 0) {
            alunos.forEach(aluno -> {
                System.out.println(aluno.toString());
                System.out.println("");
            });
            System.out.print("Digite o numero de matricula do aluno que deseja utilizar: ");
            int codigo = Integer.valueOf(scanner.nextLine());
            return AlunoRepository.selecionarPorCodigo(codigo);
        } else {
            System.out.println("Não existem alunos no banco de dados");
            return null;
        }
    }

    public Aluno selecionarOpcao() {
        int opcao = 0;
        while (opcao != 1 && opcao != 2) {
            System.out.println("Selecione a opção de Aluno: ");
            System.out.println("1 - Novo Aluno");
            System.out.println("2 - Listar Alunos");
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
}
