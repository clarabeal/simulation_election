package personnes;
import java.util.Random;

/**
 * Candidat est la classe qui hérite de Personne et qui désigne un candidat
 */
public class Candidat extends Personne {
    private static int stidCandidat=1; //Ne se remettra jamais à 0 grâce au static
    private int idCandidat;
    private int age=0; //Pour gérer les égalités

    /**
	 * Le construteur de la classe
	 */
    public Candidat(){
        super();

        this.idCandidat=this.stidCandidat;
        this.stidCandidat++;

        Random ran = new Random();
        this.age=ran.nextInt((70-18)+1)+18;
    }

    /**
     * Fonction qui retourne l'identifiant du candidat
     * @return idCandidat, l'id du candidat
     */
    public int getIdCandidat(){
        return this.idCandidat;
    }

    /**
     * Fonction qui retourne l'âge du candidat
     * @return age, l'âge du candidat
     */
    public int getAgeCandidat(){
        return this.age;
    }
}