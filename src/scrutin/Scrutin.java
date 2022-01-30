package scrutin;

import java.util.Arrays;
import java.util.Random;

import personnes.*;

public abstract class Scrutin {
    private Electeur tabElecteur[];
    private Electeur tabElecteurSauvegarde[];
    private Candidat tabCandidat[];
    private Candidat tabCandidatSauvegarde[];
    private int nbElecteur;
    private int nbElecteurSauvegarde;
    private int nbCandidat;
    private int nbCandidatSauvegarde;

    private double seuil;

    public Scrutin (int nbE, int nbC){
        this.nbElecteur=nbE;
        this.nbElecteurSauvegarde=nbE;
        this.nbCandidat=nbC;
        this.nbCandidatSauvegarde=nbC;
        this.seuil=0.3;

        this.tabElecteur = new Electeur[this.nbElecteur];
        this.tabElecteurSauvegarde=new Electeur[this.nbElecteur];
        for(int i=0;i<this.nbElecteur;i++){
			Electeur e = new Electeur(this.nbCandidat);
			this.tabElecteur[i]=e;
            this.tabElecteurSauvegarde[i]=e;
        }

        this.tabCandidat = new Candidat[this.nbCandidat];
        this.tabCandidatSauvegarde = new Candidat[this.nbCandidat];
        for(int i=0;i<this.nbCandidat;i++){
            Candidat c = new Candidat();
            this.tabCandidat[i]=c;
            this.tabCandidatSauvegarde[i]=c;
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

    //Fonction qui retourne un tab des résultats du scrutin
    public int[] getResultat(){
        int tabRang[]=new int[this.nbCandidat];
        tabRang=getRangIndice();

        //On modifie tabRang pour enlever les égalités possibles
        changeEgalite(tabRang);

        int tabResult[]=new int[this.nbCandidat];

        for(int i=1;i<this.nbCandidat+1;i++){
            for(int j=0;j<this.nbCandidat;j++){
                if(tabRang[j]==i){
                    tabResult[i-1]=j+1;
                }
            }
        }

        return tabResult;
    }

    //Créé un nouveau tabCandidat   
    public void setNewTabCandidat(Candidat[] newTabCandidat){
        this.nbCandidat = 2;
        this.tabCandidat[0]=newTabCandidat[0];
        this.tabCandidat[1]=newTabCandidat[1];
    }

    //Créé un nouveau tabElecteur
    public void setNewTabElecteur(Electeur[] tabElecteurSondage, int nbE){
        this.nbElecteur = nbE;
        for(int i=0;i<this.nbElecteur;i++){
            tabElecteur[i]=tabElecteurSondage[i];
        } 
    }

    //Modifie le seuil
    public void setSeuil(double seuil){
        this.seuil=seuil;
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

    //Procédure qui permet de remplir tabVote de chaque électeur et de retourner le nb d'abstention
    public abstract int voter();

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

    //Supprime le tabCandidat
    public void deleteTabCandidat(){
        for(int i=0;i<getNbCandidat();i++)
        {
            this.tabCandidat[i]=null;
        }
    }

    //Supprime le tabElecteur
    public void deleteTabElecteur(){
        for(int i=0;i<getNbElecteur();i++)
        {
            this.tabElecteur[i]=null;
        }
    }

    //Retourne vrai si l'utilisateur a rentré des paramètres correctes, faux sinon
    public boolean bonsParametres() {
        return (getNbElecteur() == (int) getNbElecteur()) && (getNbCandidat() == (int) getNbCandidat()) && (getNbElecteur() >= 0) && (getNbCandidat() >= 0);
    }

    //Modifie le tabElecteur pour le sondage
    public void modifTabElecteurSondage(){
        int nbE =(int)Math.round(nbElecteur*0.15);
        
        int[] tabIdElecteur = new int[nbE]; //Ce tableau va contenir les id des Electeurs du sondage
        for(int i=0;i<nbE;i++){
            tabIdElecteur[i]=0;
        }

        Electeur[] tabElecteurSondage = new Electeur[nbE];
        for(int i=0;i<nbE;i++)
        {
            tabElecteurSondage[i]=null;
        }
        Random ran = new Random();

        //On remplit tabElecteurSondage
        for(int i=0;i<nbE;i++){
            boolean disponible=false;
            int idE=-1;
            //On choisit un électeur parmis tous les électeurs et on vérifie qu'il n'a pas déjà été choisi
            while(!disponible){
                disponible=true;
                idE = ran.nextInt(getNbElecteur())+1; //de 1 à nbElecteur

                for(int j=0;j<nbE;j++){
                    if(tabIdElecteur[j]==idE){
                        disponible=false;
                    }
                }

            }

            //On remplit les tab
            tabIdElecteur[i]=idE;
            tabElecteurSondage[i]=getElecteur(idE-1); //-1 à cause du décalage dans tabElecteur

        }
        setNewTabElecteur(tabElecteurSondage,nbE);

    }
    
     public void interactions(){
        //On parcourt tabElecteur
        for(int i=0;i<this.nbElecteur;i++){

            //On cherche si il tombe sur un autre électeur ou un candidat
            Random ran = new Random();
            int choix = ran.nextInt(2);

            if(choix==0){//Choisir un autre électeur
                int idElecteur;
                do{
                    idElecteur=ran.nextInt(getNbElecteur())+1; //de 1 à nbElecteur
                }while(idElecteur==i+1);

                //On modifie les représentations
                double moy=0;
                for(int j=0;j<2;j++){
                    moy=moy+Math.abs(getElecteur(i).getRepresentation(j)-getElecteur(idElecteur-1).getRepresentation(j));
                }

                moy=moy/2;

                if(moy<getSeuil()){//On les rapproche
                    for(int j=0;j<2;j++){
                        double dist=getElecteur(i).getRepresentation(j)-getElecteur(idElecteur-1).getRepresentation(j);

                        //On va venir rapprocher les représentations de l'électeur avec celles de l'autre électeur
                        if(Math.abs(dist)>0.1&&dist<0){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)+0.1);
                        }
                        else if(Math.abs(dist)>0.1&&dist>0){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)-0.1);
                        }
                        else{//La valeur absolue de la distance est <0,1 donc on copie la valeur de l'autre électeur
                            getElecteur(i).setRepresentation(j, getElecteur(idElecteur-1).getRepresentation(j));
                        }
                    }
                }
                else{//On les éloigne
                    for(int j=0;j<2;j++){
                        double dist=getElecteur(i).getRepresentation(j)-getElecteur(idElecteur-1).getRepresentation(j);

                        //On va venir éloigner les représentations de l'électeur avec celles de l'autre électeur
                        if((dist<0) && (getElecteur(i).getRepresentation(j)-0.1>=0)){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)-0.1);
                        }
                        else if((dist>0) && (getElecteur(i).getRepresentation(j)+0.1<=1)){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)+0.1);
                        }
                        //Si la nouvelle valeur allait être supérieure à 1 ou inférieure à 0 on ne change rien
                    }
                }
            }
            else{//choix==1, choisir un candidat
                int idCandidat=ran.nextInt(getNbCandidat())+1; //de 1 à nbCandidat

                //On modifie les représentations
                double moy=0;
                for(int j=0;j<2;j++){
                    moy=moy+Math.abs(getElecteur(i).getRepresentation(j)-getCandidat(idCandidat-1).getRepresentation(j));
                }

                moy=moy/2;


                if(moy<getSeuil()){//On les rapproche
                    for(int j=0;j<2;j++){
                        double dist=getElecteur(i).getRepresentation(j)-getCandidat(idCandidat-1).getRepresentation(j);

                        //On va venir rapprocher les représentations de l'électeur avec celles du candidat
                        if(Math.abs(dist)>0.1&&dist<0){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)+0.1);
                        }
                        else if(Math.abs(dist)>0.1&&dist>0){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)-0.1);
                        }
                        else{//La valeur absolue de la distance est <0,1 donc on copie la valeur du candidat
                            getElecteur(i).setRepresentation(j, getCandidat(idCandidat-1).getRepresentation(j));
                        }
                    }

                }
                else{//On les éloigne
                    for(int j=0;j<2;j++){
                        double dist=getElecteur(i).getRepresentation(j)-getCandidat(idCandidat-1).getRepresentation(j);

                        //On va venir éloigner les représentations de l'électeur avec celles du candidat
                        if((dist<0) && (getElecteur(i).getRepresentation(j)-0.1>=0)){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)-0.1);
                        }
                        else if((dist>0) && (getElecteur(i).getRepresentation(j)+0.1<=1)){
                            getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)+0.1);
                        }
                        //Si la nouvelle valeur allait être supérieure à 1 ou inférieure à 0 on ne change rien
                    }
                }
            }
        }
    }

    //Remet la toute première version de TabCandidat (utile pour afficher dans le CSV les candidats issus d'un scrutin majoritaire à 2 tours
    public void resetTabCandidat(){
        deleteTabCandidat();
        this.nbCandidat=tabCandidatSauvegarde.length;
        this.tabCandidat=tabCandidatSauvegarde;
    }
     
    //Remet la toute première version de TabCandidat (utile pour afficher dans le CSV les candidats issus d'un scrutin majoritaire à 2 tours
    public void resetTabElecteur(){
        this.nbElecteur=nbElecteurSauvegarde;
        this.tabElecteur=tabElecteurSauvegarde;
    }

    
    public void CalculSondageUtilite(int[] countVote){ 
            
        int[] nbVote = new int[getNbCandidat()];
        nbVote = countVote;
        double[] PercentVote=new double[nbVote.length];
        int sommeVote=0;
        
        for(int i=0;i<getNbCandidat();i++)
        {
            sommeVote=sommeVote+nbVote[i];
        }
        for(int i=0;i<getNbCandidat();i++)
        {
            PercentVote[i]=nbVote[i]/sommeVote;
        }
             
        for(int i=0;i<this.getNbElecteur();i++){
        int idCandidat=-1;
        double utiliteMin=1;
        
        //On parcourt tabCandidat pour chaque électeur
        for(int j=0;j<this.getNbCandidat();j++){
            double SommeDistRep=0;
            double utilite=0;
            //On parcourt toutes les représentations
            for(int r=0;r<2;r++){ 
                SommeDistRep=SommeDistRep+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
            }
            SommeDistRep=SommeDistRep/2;
            utilite=(1-SommeDistRep)*PercentVote[j];
            //On cherche le candidat qui se rapproche le plus des opinions de l'électeur
            if(utilite<utiliteMin){
                utiliteMin=utilite;
                idCandidat=j;
            }
        }
            for(int j=0;j<2;j++){
                double dist=getElecteur(i).getRepresentation(j)-getCandidat(idCandidat).getRepresentation(j);

                //On va venir rapprocher les représentations de l'électeur avec celles du candidat
                if(Math.abs(dist)>0.005&&dist<0){
                    getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)+0.005);
                }
                else if(Math.abs(dist)>0.005&&dist>0){
                    getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)-0.005);
                }
                else{//La valeur absolue de la distance est <0,1 donc on copie la valeur du candidat
                    getElecteur(i).setRepresentation(j, getCandidat(idCandidat).getRepresentation(j));
                }
                if(getElecteur(i).getRepresentation(j)<0){
                    getElecteur(i).setRepresentation(j,0);
                }
                else if(getElecteur(i).getRepresentation(j)>1){
                    getElecteur(i).setRepresentation(j,1);
                }
            }
        }       
    } 
     
    public void CalculSondageUtilitePropor(int[] countVote){ 
            
        int[] nbVote = new int[getNbCandidat()];
        nbVote = countVote;
        double[] PercentVote=new double[nbVote.length];
        int sommeVote=0;
        
        for(int i=0;i<getNbCandidat();i++)
        {
            sommeVote=sommeVote+nbVote[i];
        }
        for(int i=0;i<getNbCandidat();i++)
        {
            PercentVote[i]=nbVote[i]/sommeVote;
        }
             
        for(int i=0;i<this.getNbElecteur();i++){
            int idCandidat=-1;
            double utiliteMin=1;
            
            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<this.getNbCandidat();j++){
                double SommeDistRep=0;
                double utilite=0;
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){ 
                    SommeDistRep=SommeDistRep+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
                }
                SommeDistRep=SommeDistRep/2;
                utilite=(1-SommeDistRep)*PercentVote[j];
                //On cherche le candidat qui se rapproche le plus des opinions de l'électeur
                if(utilite<utiliteMin){
                    utiliteMin=utilite;
                    idCandidat=j;
                }
            }
            for(int j=0;j<2;j++){
                double dist=getElecteur(i).getRepresentation(j)-getCandidat(idCandidat).getRepresentation(j);

                //On va venir rapprocher les représentations de l'électeur avec celles du candidat
                if(Math.abs(dist)>(0.01*utiliteMin)&&dist<0){
                    getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)+(0.01*utiliteMin));
                }
                else if(Math.abs(dist)>(0.01*utiliteMin)&&dist>0){
                    getElecteur(i).setRepresentation(j,getElecteur(i).getRepresentation(j)-(0.01*utiliteMin));
                }
                else{//La valeur absolue de la distance est <0,1 donc on copie la valeur du candidat
                    getElecteur(i).setRepresentation(j, getCandidat(idCandidat).getRepresentation(j));
                }
                if(getElecteur(i).getRepresentation(j)<0){
                    getElecteur(i).setRepresentation(j,0);
                }
                else if(getElecteur(i).getRepresentation(j)>1){
                    getElecteur(i).setRepresentation(j,1);
                }
            }
        }
            
    }
}

