package personnes;

public class Candidat extends Personne {
    private static int stidCandidat=0; // Ne se remettra jamais à 0 grâce au static
    private int idCandidat;
    private int nbCandidat;

    public Candidat(int n){
        super();

        this.nbCandidat=n;
        this.idCandidat=stidCandidat;
        stidCandidat++;
    }
}