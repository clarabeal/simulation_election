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

    //Fonction qui retourne nbElecteur
    public int getNbElecteur(){
        return this.nbElecteur;
    }

    //Fonction qui retourne nbCandidat
    public int getNbCandidat(){
        return this.nbCandidat;
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

    //Affiche les candidats avec leur representation et leur id 
    public void affTabCandidat(){
        for(int i=0;i<this.nbCandidat;i++){
            System.out.println("Id : Candidat "+this.tabCandidat[i].getIdCandidat());
            System.out.println("Age : "+this.tabCandidat[i].getAgeCandidat()+" ans");
            System.out.println("Représentation : ["+this.tabCandidat[i].getRepresentation(0)+","+this.tabCandidat[i].getRepresentation(1)+"]");
        }
    }

    //Affiche les électeurs avec leur représentation, leur id et leurs votes
    public void affTabElecteur(){
        for(int i=0;i<this.nbElecteur;i++){
            System.out.println("Id : Electeur "+this.tabElecteur[i].getIdElecteur());
            System.out.println("Représentation : ["+this.tabElecteur[i].getRepresentation(0)+","+this.tabElecteur[i].getRepresentation(1)+"]");
            System.out.print("Vote : [ ");
            this.tabElecteur[i].affTabVote();
            System.out.println("]");
        }
    }

    //Procédure qui permet de remplir tabVote de chaque électeur
    public abstract void voter();

    //Fonction qui retourne un tab contenant le nombre de voix pour chaque Candidat(indice 0 => candidat 1, indice 1 => candidat 2 ...)
    public int[] getNbVoix(){

        int tabVoix[] = new int[this.nbCandidat];
        for(int i=0;i<this.nbCandidat;i++){
            tabVoix[i]=0;
        }

        for(int i=0;i<this.nbElecteur;i++){
            //On parcourt tabVote de tous les électeurs
            for(int j=0;j<this.nbCandidat;j++){

                if((getElecteur(i).getVote(j)!=-1)&&(getElecteur(i).getVote(j)!=0)){ //On enlève le cas de l'abstention et le cas où il n'y a pas de vote
                    tabVoix[getElecteur(i).getVote(j)-1]=tabVoix[getElecteur(i).getVote(j)-1]+1; //-1 car id commence à 1
                }
            }
        }
        return tabVoix;
    }

    //Fonction qui retourne un tab du rang des indices de tabVoix
    public int[] getRangIndice(){
        int tabVoix[]=new int[this.nbCandidat];
        tabVoix=getNbVoix();

        int tabRang[]=new int[this.nbCandidat];

        for(int i=0;i<this.nbCandidat;i++){
            int compt = 0;
            for(int j=0;j<this.nbCandidat;j++){
                if(tabVoix[j]>tabVoix[i]){
                    compt++;
                }
            }
            tabRang[i]=compt+1;
        }
        return tabRang;
    }

    //Fonction qui retourne un tab 
    public int[] getResultat(){
        int tabRang[]=new int[this.nbCandidat];
        tabRang=getRangIndice();

        //On modifie tabRang pour enlever les égalités possibles
        changeEgalite(tabRang);

        int tabResult[]=new int[this.nbCandidat];

        for(int i=1;i<this.nbCandidat+1;i++){
            for(int j=0;j<this.nbCandidat;j++){
                if(tabRang[j]==i){
                    System.out.println("Candidat "+(j+1)+" est "+i);
                    tabResult[i-1]=j+1;
                }
            }
        }
        return tabResult;
    }

    //Modifie tabRang en enlevant les égalités en regardant l'âge
    public void changeEgalite(int[] tabRang){
        for(int i=0;i<this.nbCandidat;i++){
            for(int j=0;j<this.nbCandidat;j++){
                if((i!=j)&&(tabRang[i]==tabRang[j])){ //On regarde que ça ne soit pas le même indice et qu'on ait la même valeur
                    if(getCandidat(i).getAgeCandidat()>getCandidat(j).getAgeCandidat()){
                        tabRang[j]++;
                    }
                    else{
                        tabRang[i]++;
                    }
                }
            }
        }
    }
}

