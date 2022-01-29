import personnes.*;
import scrutin.*;
//import affichage.*;

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
        int nbAbstention = 0;

        //Vote par approbation
        
        
        ScrutinApprobation s1 = new ScrutinApprobation(nbElecteur,nbCandidat);

        s1.affTabCandidat();
        s1.affTabElecteur();

        s1.interactions();

        nbAbstention=s1.voter();
        s1.affTabCandidat();
        s1.affTabElecteur();

        System.out.print("Abstention : ");
        System.out.println(nbAbstention);

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
        

        //Scrutin majoritaire à un tour  
              
        /*
        ScrutinMajoritaireUnTour s2 = new ScrutinMajoritaireUnTour(nbElecteur,nbCandidat);

        nbAbstention=s2.voter();
        s2.affTabCandidat();
        s2.affTabElecteur();

        System.out.print("Abstention : ");
        System.out.println(nbAbstention);

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
        */

        //Scrutin majoritaire à deux tours        

        /*
        ScrutinMajoritaireDeuxTours s4 = new ScrutinMajoritaireDeuxTours(nbElecteur, nbCandidat);

        //1er tour
        nbAbstention=s4.voter();
        s4.affTabCandidat();
        s4.affTabElecteur();

        System.out.print("Abstention : ");
        System.out.println(nbAbstention);

        int tabResult4[] = new int[nbCandidat];
        tabResult4=s4.getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabResult4[i]+" ");
        }

        //2nd tour
        s4.modifTabCandidat(tabResult4);

        nbAbstention=s4.voter();
        s4.affTabCandidat();
        s4.affTabElecteur();

        System.out.print("Abstention : ");
        System.out.println(nbAbstention);

        int tabVoix4[] = new int[nbCandidat];
        tabVoix4=s4.getNbVoix();

        System.out.print("nbVoix : ");
        for(int i=0;i<2;i++){
            System.out.print(tabVoix4[i]+" ");
        }
        System.out.println("");

        int tabResult5[] = new int[nbCandidat];
        tabResult5=s4.getResultat2();

        System.out.print("Résultats : ");
        for(int i=0;i<2;i++){
            System.out.print(tabResult5[i]+" ");
        }
        */
        
        //Scrutin Borda

        /*
        ScrutinBorda s3 = new ScrutinBorda(nbElecteur, nbCandidat);
        s3.voter();
        s3.affTabCandidat();
        s3.affTabElecteur();

        int tabVoix3[] = new int[nbCandidat];
        tabVoix3=s3.getNbVoix();

        System.out.print("nbVoix : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabVoix3[i]+" ");
        }
        System.out.println(" ");

        int tabRang3[] = new int[nbCandidat];
        tabRang3=s3.getRangIndice();

        System.out.print("Rang : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabRang3[i]+" ");
        }
        System.out.println("");

        int tabResult3[] = new int[nbCandidat];
        tabResult3=s3.getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabResult3[i]+" ");
        }
        */
        
        //Vote alternatif

        /*
        ScrutinAlternatif s5 = new ScrutinAlternatif(nbElecteur, nbCandidat);
        s5.voter();
        s5.affTabCandidat();
        s5.affTabElecteur();
        s5.voterElimine();

        int tabRang5[]= new int [nbCandidat];
        tabRang5=s5.getRangIndice();

        System.out.print("Rang : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabRang5[i]+" ");
        }
        System.out.println("");

        int tabResult5[] = new int[nbCandidat];
        tabResult5=s5.getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<nbCandidat;i++){
            System.out.print(tabResult5[i]+" ");
        }
        */
    }
}