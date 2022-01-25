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
        int nbElecteur = 100;

        //Vote par approbation
        /*
        ScrutinApprobation s1 = new ScrutinApprobation(nbElecteur,nbCandidat);

        s1.voter();
        s1.affTabCandidat();
        s1.affTabElecteur();

        int tabVoix[] = new int[nbCandidat];
        tabVoix=s1.getNbVoix();

        System.out.print("nbVoix : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println("");

        int tabRang[] = new int[nbCandidat];
        tabRang=s1.getRangIndice();

        System.out.print("Rang : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabRang[i]+" ");
        }
        System.out.println("");

        int tabResult[] = new int[nbCandidat];
        tabResult=s1.getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabResult[i]+" ");
        }
        */

        //Scrutin majoritaire à un tour        
        ScrutinMajoritaireUnTour s2 = new ScrutinMajoritaireUnTour(nbElecteur,nbCandidat);

        s2.voter();
        s2.affTabCandidat();
        s2.affTabElecteur();

        int tabVoix2[] = new int[nbCandidat];
        tabVoix2=s2.getNbVoix();

        System.out.print("nbVoix : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabVoix2[i]+" ");
        }
        System.out.println("");

        int tabRang2[] = new int[nbCandidat];
        tabRang2=s2.getRangIndice();

        System.out.print("Rang : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabRang2[i]+" ");
        }
        System.out.println("");

        int tabResult2[] = new int[nbCandidat];
        tabResult2=s2.getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabResult2[i]+" ");
        }
        
    }
}