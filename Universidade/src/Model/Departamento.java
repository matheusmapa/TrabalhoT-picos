package Model;

/**
 *
 * @author Samuel
 */
public class Departamento {

    private int codigo;
    private String nome;
    private int numeroSala;
    private String telefoneSala;

    public Departamento() {
    }

    public Departamento(String nome, int numeroSala, String telefoneSala) {
        this.nome = nome;
        this.numeroSala = numeroSala;
        this.telefoneSala = telefoneSala;
    }

    public Departamento(int codigo, String nome, int numeroSala, String telefoneSala) {
        this.codigo = codigo;
        this.nome = nome;
        this.numeroSala = numeroSala;
        this.telefoneSala = telefoneSala;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getTelefoneSala() {
        return telefoneSala;
    }

    public void setTelefoneSala(String telefoneSala) {
        this.telefoneSala = telefoneSala;
    }

    @Override
    public String toString() {
        return "codigo = " + codigo + "\n"
                + "nome = " + nome + "\n"
                + "numeroSala = " + numeroSala + "\n"
                + "telefoneSala = " + telefoneSala + '}';
    }
}
