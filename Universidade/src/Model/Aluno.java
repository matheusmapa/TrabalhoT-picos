
package Model;

import java.util.Date;

/**
 *
 * @author Samuel
 */
public class Aluno {

    private int numeroMatricula;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private Date dataNascimento;
    private char sexo;
    private String turma;
    private Departamento departamentoPrincipal;
    private String programa;

    public Aluno(int numeroMatricula, String nome, String cpf, String endereco, String telefone, Date dataNascimento, char sexo, String turma, Departamento departamentoPrincipal, String programa) {
        this.numeroMatricula = numeroMatricula;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.turma = turma;
        this.departamentoPrincipal = departamentoPrincipal;
        this.programa = programa;
    }

    public Aluno(String nome, String cpf, String endereco, String telefone, Date dataNascimento, char sexo, String turma, Departamento departamentoPrincipal, String programa) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.turma = turma;
        this.departamentoPrincipal = departamentoPrincipal;
        this.programa = programa;
    }

    public Aluno() {
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Departamento getDepartamentoPrincipal() {
        return departamentoPrincipal;
    }

    public void setDepartamentoPrincipal(Departamento departamentoPrincipal) {
        this.departamentoPrincipal = departamentoPrincipal;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    @Override
    public String toString() {
        return "numeroMatricula = " + numeroMatricula
                + "\nnome = " + nome
                + "\ncpf = " + cpf
                + "\nendereco = " + endereco
                + "\ntelefone = " + telefone
                + "\ndataNascimento = " + dataNascimento
                + "\nsexo = " + sexo
                + "\nturma = " + turma
                + "\ndepartamentoPrincipal = " + departamentoPrincipal
                + "\nprograma = " + programa;
    }

}
