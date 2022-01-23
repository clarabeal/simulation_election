package personnes;

public class Candidat extends Personne {
    private static int stidCandidat=1; //Ne se remettra jamais à 0 grâce au static
    private int idCandidat;


    public Candidat(){
        super();

        this.idCandidat=this.stidCandidat;
        this.stidCandidat++;
    }

    //Retourne l'identifiant du candidat
    public int getIdCandidat(){
        return this.idCandidat;
    }
}