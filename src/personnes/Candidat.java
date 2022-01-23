package personnes;

public class Candidat extends Personne {
    private static int stidCandidat=1; // Ne se remettra jamais à 0 grâce au static
    public int idCandidat;


    public Candidat(){
        super();

        this.idCandidat=this.stidCandidat;
        this.stidCandidat++;
    }
}