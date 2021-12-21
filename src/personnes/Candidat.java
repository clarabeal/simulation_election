package personnes;

public class Candidat extends Personne {
    private static int stidCandidat=0; // Ne se remettra jamais à 0 grâce au static
    private int idCandidat;

    public Candidat(){
        super();

        idCandidat=stidCandidat;
        stidCandidat++;
    }
}