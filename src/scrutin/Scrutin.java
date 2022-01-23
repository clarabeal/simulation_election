package scrutin;

import personnes.*;

public abstract class Scrutin {
    private Electeur tabElecteur[];
    private Candidat tabCandidat[];
    private int nbElecteur;
    private int nbCandidat;
    private double seuil;

    public Scrutin (int nbE, int nbC){
        this.nbElecteur=nbE;
        this.nbCandidat=nbC;
        this.seuil=0.3;

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

    //Affiche les candidats avec leur representation et leur id 
    public void affTabCandidat(){
        for(int i=0;i<this.nbCandidat;i++){
            System.out.println("Représentation : ["+this.tabCandidat[i].representation[0]+","+this.tabCandidat[i].representation[1]+"]");
            System.out.println("Id : Candidat "+this.tabCandidat[i].getIdCandidat());
        }
    }

    //Affiche les électeurs avec leur représentation et leur id (manque tabVote car au début il est vide)
    public void affTabElecteur(){
        for(int i=0;i<this.nbElecteur;i++){
            System.out.println("Représentation : ["+this.tabElecteur[i].representation[0]+","+this.tabElecteur[i].representation[1]+"]");
            System.out.println("Id : Electeur "+this.tabElecteur[i].getIdElecteur());
        }
    }

    //Fonction qui retourne nbElecteur
    public int getNbELecteur(){
        return this.nbElecteur;
    }

    //Fonction qui retourne le seuil
    public double getSeuil(){
        return this.seuil;
    }

    //Fonction qui retourne le Candidat présent à l'indice i de tabCandidat
    public Candidat getCandidat(int i){
        return this.tabCandidat[i];
    }

    //Fonction qui retourne l'Electeur présent à l'indice i de tabElecteur
    public Electeur getElecteur(int i){
        return this.tabElecteur[i];
    }

    public abstract void voter();
}

