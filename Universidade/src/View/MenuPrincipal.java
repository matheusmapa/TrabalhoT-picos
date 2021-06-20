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
        
        //isso é o que o usúrio irá receber na tela
        
        System.out.println("-- MENU PRINCIPAL --");
        System.out.println("Opções: ");
        System.out.println("1 - Cadastrar Disciplina");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Matricular Aluno");
        System.out.println("4 - Lançar Notas");
        System.out.println("5 - Listar Notas");
        
        
        // uma variavel de acordo que o usurio faça sua escolha se liga a um novo menu

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
            
            // caso o usúario digite um numero que está fora das opções será retornado essa mensagem
            default: {
                System.out.flush();
                System.err.println("Opção Inválida! Tente Novamente");
                inicio();
            }
        }
    }

}
