package View;

import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MenuPrincipal {

    private final Scanner scanner;

    public MenuPrincipal() {
        scanner = new Scanner(System.in);
        inicio();
    }

    private void inicio() {
        System.out.println("-- MENU PRINCIPAL --");
        System.out.println("Opções: ");
        System.out.println("1 - Cadastrar Disciplina");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Matricular Aluno");
        System.out.println("4 - Lançar Notas");
        System.out.println("5 - Listar Notas");

        int opcao = Integer.valueOf(scanner.nextLine());

        switch (opcao) {
            case 1: {
                MenuDisciplina menu = new MenuDisciplina();
                menu.cadastrar();
                inicio();
                break;
            }
            case 2: {
                MenuAluno menu = new MenuAluno();
                menu.cadastrar();
                inicio();
                break;
            }
            case 3: {
                MenuHistorico menu = new MenuHistorico();
                menu.cadastrar();
                inicio();
                break;
            }
            case 4: {
                MenuHistorico menu = new MenuHistorico();
                menu.lançarNota();
                inicio();
                break;
            }
            case 5: {
                MenuHistorico menu = new MenuHistorico();
                menu.listar();
                inicio();
                break;
            }
            default: {
                System.out.flush();
                System.err.println("Opção Inválida! Tente Novamente");
                inicio();
            }
        }
    }

}
