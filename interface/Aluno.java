import java.util.*;

public class Aluno{

    private String matricula;

    public Aluno(){
        this.matricula = "2310100978";
    }

    public static Aluno verificarDiscente(String matricula){

        if(matricula.equals("2310100978")){

            return new Aluno();
        }
        return null;
    }

    public String getMatricula(){return matricula;}
}
