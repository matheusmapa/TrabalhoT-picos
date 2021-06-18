package Model;

/**
 *
 * @author Samuel
 */
public class Curso {

    private int codigo;
    private String nome;
    private String descricao;
    private int horasSemestrais;
    private int nivel;
    private Departamento departamentoResponsavel;

    public Curso(String nome, String descricao, int horasSemestrais, int nivel, Departamento departamentoResponsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.horasSemestrais = horasSemestrais;
        this.nivel = nivel;
        this.departamentoResponsavel = departamentoResponsavel;
    }

    public Curso(int codigo, String nome, String descricao, int horasSemestrais, int nivel, Departamento departamentoResponsavel) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.horasSemestrais = horasSemestrais;
        this.nivel = nivel;
        this.departamentoResponsavel = departamentoResponsavel;
    }

    public Curso() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getHorasSemestrais() {
        return horasSemestrais;
    }

    public void setHorasSemestrais(int horasSemestrais) {
        this.horasSemestrais = horasSemestrais;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Departamento getDepartamentoResponsavel() {
        return departamentoResponsavel;
    }

    public void setDepartamentoResponsavel(Departamento departamentoResponsavel) {
        this.departamentoResponsavel = departamentoResponsavel;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + "\n"
                + "nome=" + nome + "\n"
                + "descricao=" + descricao + "\n"
                + "horasSemestrais=" + horasSemestrais + "\n"
                + "nivel=" + nivel + "\n"
                + "departamentoResponsavel=" + departamentoResponsavel + '}';
    }

}
