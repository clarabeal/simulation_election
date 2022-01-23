import personnes.*;
import scrutin.*;

public class Main {
    public static void main(String [] args){

        //Affichage qd Scrutin n'existait pas encore 

        /*
        Personne p = new Personne();
        System.out.println(p.representation[0]);
        System.out.println(p.representation[1]);

        Candidat c1 = new Candidat();
        Candidat c2 = new Candidat();

        System.out.println(c1.representation[0]);
        System.out.println(c1.representation[1]);
        System.out.println(c1.idCandidat);
        System.out.println(c2.idCandidat);

        Electeur e = new Electeur(2);

        System.out.println(e.representation[0]);
        System.out.println(e.representation[1]);
        System.out.println(e.idElecteur);
        System.out.println(e.tabVote[0]);
        System.out.println(e.tabVote[1]);
        */

        ScrutinApprobation s = new ScrutinApprobation(2,2);

        s.affTabCandidat();
        s.affTabElecteur();
        s.voter();

        System.out.println("Electeur 1 :");
        s.getElecteur(0).affTabVote();

        System.out.println("Electeur 2 :");
        s.getElecteur(1).affTabVote();
    }
}