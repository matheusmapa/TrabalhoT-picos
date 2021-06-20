package Model;

/**
 *
 * @author Samuel
 */
public class Historico {

    private int codigo;
    private Aluno aluno;
    private Disciplina disciplina;
    private double nota;

    public Historico(Aluno aluno, Disciplina disciplina, double nota) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public Historico(int codigo, Aluno aluno, Disciplina disciplina, double nota) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "codigo = " + codigo
                + "\naluno = " + aluno
                + "\ndisciplina = " + disciplina
                + "\nnota = " + nota
                + "\nconceito = " + this.getConceito()
                + "\naprovação = " + this.getAprovacao();
    }

    private char getConceito() {
        if (nota > 0 && nota < 10) {
            return 'F';
        } else if (nota > 11 && nota < 20) {
            return 'E';
        } else if (nota > 21 && nota < 40) {
            return 'D';
        } else if (nota > 41 && nota < 60) {
            return 'C';
        } else if (nota > 61 && nota < 80) {
            return 'B';
        } else if (nota > 81 && nota < 100) {
            return 'A';
        }
        return 0;

    }

    private String getAprovacao() {
        return nota >= 60 ? "Aprovado" : "Reprovado";
    }

}
