package scrutin;

import java.io.FileWriter;
import java.util.ArrayList;

public class ScrutinBorda extends Scrutin{

    public ScrutinBorda(int nbE, int nbC){
        super(nbE,nbC);
    }

    /**
    * @Override
    */
    //Remplit tabVote de chaque Electeur en attribuant n points pour le 1er Candidat, n-1 pour le 2ème... et retourne le nb d'abstention
    public int voter(){
        //On parcourt tabElecteur
        for(int i=0;i<getNbElecteur();i++){
            //Tab qui contiendra la moy de l'électeur avec les candidats
            double[] tabMoy = new double [getNbCandidat()];

            //On créé un tab qui va renseigner le rang des moyennes
            int tabRang[]=new int[getNbCandidat()];

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

            //On remplit tabRang
            for(int n=0;n<getNbCandidat();n++){
                int compt = 0;
                for(int m=0;m<getNbCandidat();m++){
                    if(tabMoy[m]<tabMoy[n]){
                        compt++;
                    }
                }
                tabRang[n]=compt+1;
            }

            //On remplit tabVote de chaque Electeur
            int points = getNbCandidat();

            for(int p=0;p<getNbCandidat();p++){
                getElecteur(i).setVote(p,points-tabRang[p]+1);
            }
        }

        return 0;
    }

    //Redéfinition
    //Fonction qui retourne un tab contenant le nombre de voix pour chaque Candidat(indice 0 => candidat 1, indice 1 => candidat 2 ...)
    public int[] getNbVoix(){

        int tabVoix[] = new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++){
            tabVoix[i]=0;
        }

        for(int i=0;i<this.getNbElecteur();i++){
            //On parcourt tabVote de tous les électeurs
            for(int j=0;j<this.getNbCandidat();j++){
                tabVoix[j]=tabVoix[j]+getElecteur(i).getVote(j); //-1 car id commence à 1
            }
        }
        return tabVoix;
    }
    
    
    public void ToString(){
        System.out.println("");
        System.out.println("");
        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();
        System.out.println("Nombre de votes :");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print("Candidat "+(i+1)+" :");
            System.out.println(tabVoix[i]+" ");
        }
        System.out.println("");

        int tabResult[] = new int[getNbCandidat()];
        tabResult=getResultat();

        System.out.print("Ordre de résultat : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabResult[i]+" ");
        }
        System.out.println("");
        System.out.println("Le gagnant est : Candidat "+tabResult[0]);
        System.out.println("");
        
        
    }
    
    /**
	 * Permet la création d'un fichier CSV.
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
			file = new FileWriter("RESULTAT VOTE BORDA.csv");
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