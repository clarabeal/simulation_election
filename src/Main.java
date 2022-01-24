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

        int nbCandidat = 5;
        int nbElecteur = 5;

        ScrutinApprobation s = new ScrutinApprobation(nbElecteur,nbCandidat);

        s.voter();
        s.affTabCandidat();
        s.affTabElecteur();

        int tabVoix[] = new int [nbCandidat];
        tabVoix=s.nbVoix();

        for(int i=0;i<nbCandidat;i++){
            System.out.println(tabVoix[i]);
        }
    }
}