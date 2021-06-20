package View;

import Model.Departamento;
import Repository.DepartamentoRepository;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MenuDepartamento {
    
    private final Scanner scanner;
    
    public MenuDepartamento() {
        scanner = new Scanner(System.in, "ISO-8859-1");
    }
    
    public Departamento cadastrar() {
        System.out.println("-- CADASTRAR DEPARTAMENTO --");
        
        System.out.print("Digite o nome do Departamento: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite o número da sala: ");
        int numeroSala = Integer.valueOf(scanner.nextLine());
        
        System.out.print("Digite o telefone do departamento: ");
        String telefoneSala = scanner.nextLine();
        
        Departamento departamento = new Departamento(nome, numeroSala, telefoneSala);
        
        if (DepartamentoRepository.inserir(departamento)) {
            System.out.println("Departamento adicionado com sucesso");
            Departamento departamentoAdicionado = DepartamentoRepository.selecionarUltimoAdicionado();
            System.out.println(departamentoAdicionado.toString());
            return departamentoAdicionado;
        } else {
            System.out.println("O Departamento não foi adicionado");
            return null;
        }
    }
    
    public Departamento selecionarPorCodigo() {
        System.out.println("-- LISTA DE DEPARTAMENTOS --");
        List departamentos = DepartamentoRepository.selecionarTodos();
        if (departamentos.size() > 0) {
            departamentos.forEach(departamento -> {
                System.out.println(departamento.toString());
                System.out.println("");
            });
            System.out.print("Digite o código do departamento que deseja utilizar: ");
            int codigo = Integer.valueOf(scanner.nextLine());
            return DepartamentoRepository.selecionarPorCodigo(codigo);
        } else {
            System.out.println("Não existem departamentos no banco de dados");
            return null;
        }
    }
    
    public Departamento selecionarOpcao() {
        int opcao = 0;
        while (opcao != 1 && opcao != 2) {
            System.out.println("Selecione a opção de Departamento: ");
            System.out.println("1 - Novo Departamento");
            System.out.println("2 - Listar Departamentos");
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
