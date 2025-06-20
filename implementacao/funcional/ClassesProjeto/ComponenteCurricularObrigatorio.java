package classes;

import java.util.List;

public class ComponenteCurricularObrigatorio implements ComponenteCurricular{
    private int id;
    private String nome;
    private int creditos;
    private List<Integer> prerequisitos;
    private List<Integer> corequisitos; // Alterado de int para List<Integer>
    private int cargaHoraria;
    private int semestre;

    public ComponenteCurricularObrigatorio (int id, String nome, int creditos, List<Integer> prerequisitos, List<Integer> corequisitos, int cargaHoraria,
                   int semestre) {
        super();
        this.id = id;
        this.nome = nome;
        this.creditos = creditos;
        this.prerequisitos = prerequisitos;
        this.corequisitos = corequisitos;
        this.cargaHoraria = cargaHoraria;
        this.semestre = semestre;
    }

    public ComponenteCurricularObrigatorio (int id, String nome, int creditos, List<Integer> prerequisitos) {
        super();
        this.id = id;
        this.nome = nome;
        this.creditos = creditos;
        this.prerequisitos = prerequisitos;
    }

    public ComponenteCurricularObrigatorio (int id) {
        super();
        this.id = id;

    }


    @Override
    public String toString() {
        return "Componente Curricular Obrigatorio [id=" + id + ", nome=" + nome + ", creditos=" + creditos + ", prerequisitos=" + prerequisitos
                + ", corequisitos=" + corequisitos + ", cargaHoraria=" + cargaHoraria + ", semestre=" + semestre + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Integer> getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(List<Integer> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    public List<Integer> getCorequisitos() {
        return corequisitos;
    }

    public void setCorequisitos(List<Integer> corequisitos) {
        this.corequisitos = corequisitos;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}
