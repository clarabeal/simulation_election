package scrutin;

import personnes.*;

public class Scrutin { //Remettre abstract pour méthode voter
    private Electeur tabElecteur[];
    private Candidat tabCandidat[];
    private int nbElecteur;
    private int nbCandidat;

    public Scrutin (int nbE, int nbC){
        this.nbElecteur=nbE;
        this.nbCandidat=nbC;

        this.tabElecteur = new Electeur[this.nbElecteur];
        for(int i=0;i<this.nbElecteur;i++){
			Electeur e = new Electeur(this.nbCandidat);
			this.tabElecteur[i]=e;
		}

        this.tabCandidat = new Candidat[this.nbCandidat];
        for(int i=0;i<this.nbCandidat;i++){
            Candidat c = new Candidat();
            this.tabCandidat[i]=c;
        }
    }

    // Affiche les candidats avec leur representation et leur id 
    public void affTabCandidat(){
        for(int i=0;i<this.nbCandidat;i++){
            System.out.println("Représentation : ["+this.tabCandidat[i].representation[0]+","+this.tabCandidat[i].representation[1]+"]");
            System.out.println("Id : "+this.tabCandidat[i].idCandidat);
        }
    }

    //Affiche les électeurs avec leur representation et leur id (manque tabVote car au début il est vide)
    public void affTabElecteur(){
        for(int i=0;i<this.nbElecteur;i++){
            System.out.println("Représentation : ["+this.tabElecteur[i].representation[0]+","+this.tabElecteur[i].representation[1]+"]");
            System.out.println("Id : "+this.tabElecteur[i].idElecteur);
        }
    }
}

