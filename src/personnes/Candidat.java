package personnes;
import java.util.Random;

public class Candidat extends Personne {
    private static int stidCandidat=1; //Ne se remettra jamais à 0 grâce au static
    private int idCandidat;
    private int age=0; //Pour gérer les égalités


    public Candidat(){
        super();

        this.idCandidat=this.stidCandidat;
        this.stidCandidat++;

        Random ran = new Random();
        this.age=ran.nextInt((70-18)+1)+18;
    }

    //Retourne l'identifiant du candidat
    public int getIdCandidat(){
        return this.idCandidat;
    }

    //Retourne l'âge
    public int getAgeCandidat(){
        return this.age;
    }
}