package View;

import Model.Curso;
import Model.Departamento;
import Repository.CursoRepository;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MenuCurso {

    private final Scanner scanner;

    public MenuCurso() {
        scanner = new Scanner(System.in, "ISO-8859-1");
    }

    public Curso cadastrar() {
        System.out.println("-- CADASTRAR CURSO --");

        System.out.println("Digite o nome do Curso: ");
        String nome = scanner.nextLine();

        System.out.println("Digite a descrição: ");
        String descricao = scanner.nextLine();

        System.out.println("Digite as horas semestrais: ");
        int horasSemestrais = Integer.valueOf(scanner.nextLine());

        System.out.println("Digite o nível: ");
        int nivel = Integer.valueOf(scanner.nextLine());

        int opcao = 0;
        Departamento departamento = null;
        MenuDepartamento menuDepartamento = new MenuDepartamento();

        while (opcao != 1 && opcao != 2) {
            System.out.println("Selecione a opção de departamento: ");
            System.out.println("1 - Novo Departamento");
            System.out.println("2 - Listar Departamentos");
            opcao = Integer.valueOf(scanner.nextLine());
            switch (opcao) {
                case 1: {
                    departamento = menuDepartamento.cadastrar();
                    break;
                }
                case 2: {
                    departamento = menuDepartamento.selecionarPorCodigo();
                    break;
                }
                default: {
                    System.out.println("Opção inválida");
                }
            }
        }

        Curso curso = new Curso(nome, descricao, horasSemestrais, nivel, departamento);
        if (CursoRepository.insert(curso)) {
            System.out.println("Curso adicionado com sucesso!");
            return CursoRepository.selecionarUltimoAdicionado();
        } else {
            System.out.println("Curso não adicionado");
            return null;
        }
    }

    public Curso selecionarPorCodigo() {
        System.out.println("-- LISTA DE CURSOS --");
        List departamentos = CursoRepository.selecionarTodos();
        if (departamentos.size() > 0) {
            departamentos.forEach(departamento -> {
                System.out.println(departamento.toString());
                System.out.println("");
            });
            System.out.print("Digite o código do Curso que deseja utilizar: ");
            int codigo = Integer.valueOf(scanner.nextLine());
            return CursoRepository.selecionarPorCodigo(codigo);
        } else {
            System.out.println("Não existem departamentos no banco de dados");
            return null;
        }
    }
}
