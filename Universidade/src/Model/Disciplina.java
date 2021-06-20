package Model;

/**
 *
 * @author Samuel
 */
public class Disciplina {

    private int codigo;
    private String nomeProfessor;
    private String semestre;
    private int ano;
    private Curso curso;

    public Disciplina() {
    }

    public Disciplina(String nomeProfessor, Curso curso) {
        this.nomeProfessor = nomeProfessor;
        this.curso = curso;
    }

    public Disciplina(String nomeProfessor, String semestre, int ano, Curso curso) {
        this.nomeProfessor = nomeProfessor;
        this.semestre = semestre;
        this.ano = ano;
        this.curso = curso;
    }

    public Disciplina(int codigo, String nomeProfessor, String semestre, int ano, Curso curso) {
        this.codigo = codigo;
        this.nomeProfessor = nomeProfessor;
        this.semestre = semestre;
        this.ano = ano;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "codigo=" + codigo + ", nomeProfessor=" + nomeProfessor + ", semestre=" + semestre + ", ano=" + ano + ", curso=" + curso + '}';
    }

}
