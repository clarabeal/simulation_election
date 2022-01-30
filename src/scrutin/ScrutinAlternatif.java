package scrutin;

import Affichage.AffichageHist;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * ScrutinAlternatif est la classe qui hérite de la classe Scrutin
 * Le système du vote alternatif est que chaque electeur classe l’ensemble des candidats. Celui qui a recu le moins de premier choix
 * est éliminé et ses votes sont transférés aux candidats restants (via le 2ème choix des bulletins) et ainsi de
 * suite jusqu’à ce qu’il ne reste qu’un seul candidat qui est élu
 */
public class ScrutinAlternatif extends Scrutin {

    private int tabRang[];
    private int CompteurTour=1;

    /**
	 * Le construteur de la classe
	 * @param nbE
	 * 		Le nombre d'électeurs
	 * @param nbC
	 * 		Le nombre de candidats
	 */
    public ScrutinAlternatif(int nbE, int nbC){
        super(nbE,nbC);
    
        this.tabRang = new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++){
			tabRang[i]=0;
        }
    }

    /**
    * Remplit tabVote de chaque Electeur en classant les Candidats
    * @return Le nombre d'abstentions
    */
    @Override
    public int voter(){

        //On parcourt tabElecteur
        for(int i=0;i<getNbElecteur();i++){
            //Tab qui contiendra la moy de l'électeur avec les candidats
            double[] tabMoy = new double [getNbCandidat()];

            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<getNbCandidat();j++){
                double moy=0;
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){ 
                    moy=moy+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
                }
                moy=moy/2;
                tabMoy[j]=moy; 
            }

            //On remplit tabVote en fonction du rang des moyennes
            for(int n=0;n<getNbCandidat();n++){
                int compt = 0;
                for(int m=0;m<getNbCandidat();m++){
                    if(tabMoy[m]<tabMoy[n]){
                        compt++;
                    }
                }
                getElecteur(i).setVote(n,compt+1);
            }
        }

        return 0;
    }

    /** 
     * Fonction qui retourne un tab contenant le nombre de fois qu'un Candidat a été placé 1er
     * @return Tableau tabVoix contenant le nombre de fois qu'un Candidat a été placé 1er (indice 0 => candidat 1, indice 1 => candidat 2 ...)
     */
    @Override
    public int[] getNbVoix(){

        int tabVoix[] = new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++){
            tabVoix[i]=0;
        }

        for(int i=0;i<this.getNbElecteur();i++){
            //On parcourt tabVote de tous les électeurs
            for(int j=0;j<this.getNbCandidat();j++){
                if(getElecteur(i).getVote(j)==1){ //Si égal à 1 on lui rajoute une voix
                    tabVoix[j]++;
                }
            }
        }
        return tabVoix;
    }

    /** 
     * Fonction qui retourne l'indice du Candidat qui a reçu le moins de 1er choix
     * @return idEliminé, l'identifiant du Candidat qui a été éliminé
     */
    public int getIdElimine(){
        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();
        
        //Affichage tabVoix
        System.out.println("");
        System.out.println("ELECTION TOUR "+CompteurTour);
        System.out.print("nbVoix : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println(" ");
        String ChartName = "Histogramme du nombre de vote par candidat (VOTE ALTERNATIF TOUR "+CompteurTour+")";
        AffichageHist.createAndShowGuiName(tabVoix,ChartName);
        CompteurTour=CompteurTour+1;
        int min=getNbElecteur(); //Il ne peut pas y avoir plus de voix que d'électeurs
        int idElimine=0;

        for(int i=0;i<getNbCandidat();i++){
            if((tabVoix[i]<min)&&(tabVoix[i]!=0)){
                min=tabVoix[i];
                idElimine=i;
            }
        }

        return idElimine;
    }

    /** 
     * Fonction qui remplit tabVote de chaque Electeur en prenant en compte le Candidat éliminé
     * @return Le nombre d'abstentions
     */
    public int voterElimine(){

        int compt=getNbCandidat();
        
        
        for(int k=0;k<getNbCandidat()-1;k++){
            //On récupère l'indice du Candidat éliminé
            int idElimine = getIdElimine();
            System.out.println("L'éliminé à ce tour est le candidat : "+(idElimine+1));

            //On remplit tabRang
            if(compt!=1){
                this.tabRang[compt-1]=(idElimine+1);
            }
            compt--;
            for(int i=0;i<getNbElecteur();i++){
                
                //On modifie le classement des candidats qui se situent après l'idElimine
                for(int p=0;p<getNbCandidat();p++){
                    if(getElecteur(i).getVote(p)>getElecteur(i).getVote(idElimine)){
                        getElecteur(i).setVote(p, getElecteur(i).getVote(p)-1);
                    }
                }
                //On met à 0 la case de l'idElimine pour chaque tabVote
                for(int l=0;l<getNbCandidat();l++){
                    if(l==idElimine){
                        getElecteur(i).setVote(l,0);
                    }
                }
            }  
        }

        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();

        //On cherche l'indice du gagnant pour remplir la dernière case de tabRang
        for(int i=0;i<getNbCandidat();i++){
            if(tabVoix[i]!=0){
                this.tabRang[i]=compt;
            }
        }
        String ChartName = "Histogramme du nombre de vote par candidat (VOTE ALTERNATIF TOUR "+5+")";
        AffichageHist.createAndShowGuiName(tabVoix,ChartName);
        //Affichage tabVoix
        System.out.print("");
        System.out.println("ELECTION TOUR "+getNbCandidat());
        System.out.print("nbVoix : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println(" ");
        if(compt==1){
            for(int i = 0;i<getNbCandidat();i++)
            {
                if(tabVoix[i]==getNbElecteur()){
                    this.tabRang[0]=(i+1);
                }
            }
        }  
        return 0;
    }

    /** 
     * Fonction qui retourne un tab du rang des indices de tabVoix
     * @return Tableau tabRang du rang des indices de tabVoix
     */
    @Override
    public int[] getRangIndice(){
        return this.tabRang;
    }
    
    /**
	 * Affichage du gagnant et du nombre de voix par candidat
	 */  
    public void ToString(){
        System.out.println("");
        System.out.println("");
        int tabRang5[]= new int [getNbCandidat()];
        tabRang5=getRangIndice();

        System.out.print("Classement : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabRang5[i]+" ");
        }
        System.out.println("");
        System.out.println("Le gagnant est : Candidat "+tabRang5[0]);
        System.out.println("");
    }
    
    /**
	 * Permet la création d'un fichier CSV
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getCSV() {
		ArrayList value1 = new ArrayList();
        ArrayList value2 = new ArrayList();
        ArrayList value3 = new ArrayList();
        ArrayList value4 = new ArrayList();
        ArrayList value5 = new ArrayList();
        ArrayList value6 = new ArrayList();
        ArrayList value7 = new ArrayList();
        ArrayList value8 = new ArrayList();

        
        int tabVoix2[] = new int[getNbCandidat()];
        tabVoix2=getNbVoix();
               
		int tabResult2[] = new int[getNbCandidat()];
        for(int i = 0;i<getNbCandidat();i++)
        {
            tabResult2[i]=i+1;
        }
        
        int tabElecteur[]=new int[getNbElecteur()];
        for(int i=0;i<getNbElecteur();i++)
        {
            tabElecteur[i]=getElecteur(i).getIdElecteur();
        }
        
        double[] tabRep1=new double[getNbElecteur()];
        for(int i=0;i<getNbElecteur();i++)
        {
            tabRep1[i]=getElecteur(i).getRepresentation(0);
        }
        
        
        double[] tabRep2=new double[getNbElecteur()];
        for(int i=0;i<getNbElecteur();i++)
        {
            tabRep2[i]=getElecteur(i).getRepresentation(1);
        }
        
        int tabCandidat[]=new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++)
        {
            tabCandidat[i]=getCandidat(i).getIdCandidat();
        }
        
        double[] tabCRep1=new double[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++)
        {
            tabCRep1[i]=getCandidat(i).getRepresentation(0);
        }
        
        
        double[] tabCRep2=new double[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++)
        {
            tabCRep2[i]=getCandidat(i).getRepresentation(1);
        }
                
        for(int i = 0; i < tabResult2.length; i++) {
			value1.add("Candidat "+tabResult2[i]);
		}
                
        for(int i = 0; i < tabVoix2.length; i++) {
			value2.add(tabVoix2[i]);
		}
                
        for(int i = 0; i < tabElecteur.length; i++) {
			value3.add("Electeur "+tabElecteur[i]);
		}
                
        for(int i = 0; i < tabRep1.length; i++) {
			value4.add(tabRep1[i]);
		}
                
        for(int i = 0; i < tabRep2.length; i++) {
			value5.add(tabRep2[i]);
		}
                
        for(int i = 0; i < tabCandidat.length; i++) {
			value6.add("Candidat "+tabCandidat[i]);
		}
                
        for(int i = 0; i < tabCRep1.length; i++) {
			value7.add(tabCRep1[i]);
		}
                
        for(int i = 0; i < tabCRep2.length; i++) {
			value8.add(tabCRep2[i]);
		}
                
                
               

		FileWriter file = null;
		try {
			file = new FileWriter("RESULTAT VOTE ALTERNATIF.csv");
            file.append("Résultat VOTE\n");
			for(int i = 0; i < value1.size(); i++) {
				file.append(String.valueOf(value1.get(i)));
				file.append(";");
			}
            file.append("\n");
			for(int i = 0; i < value2.size(); i++) {
				file.append(String.valueOf(value2.get(i)));
				file.append(";");
			}
            file.append("\n\n");
            file.append("Représentation Electeur\n");
            for(int i = 0; i < value3.size(); i++) {
				file.append(String.valueOf(value3.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value4.size(); i++) {
				file.append(String.valueOf(value4.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value5.size(); i++) {
				file.append(String.valueOf(value5.get(i)));
				file.append(";");
			}
                        
            file.append("\n\n");
            file.append("Représentation Candidat\n");
            for(int i = 0; i < value6.size(); i++) {
				file.append(String.valueOf(value6.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value7.size(); i++) {
				file.append(String.valueOf(value7.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value8.size(); i++) {
				file.append(String.valueOf(value8.get(i)));
				file.append(";");
			}
			file.append("\n");
            file.close();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    
}