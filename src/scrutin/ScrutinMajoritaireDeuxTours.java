package scrutin;

import java.io.FileWriter;
import java.util.ArrayList;
import personnes.*;

public class ScrutinMajoritaireDeuxTours extends ScrutinMajoritaireUnTour{
    
    private int tabIdCandidats[];
    public ScrutinMajoritaireDeuxTours(int nbE, int nbC) {
        super(nbE,nbC);
        this.tabIdCandidats = new int[2];
        for(int i=0;i<2;i++){
			tabIdCandidats[i]=0;
        }

    }

    //Fonction qui modifie le tabCandidat en prenant les 2 gagnants du 1er tour
    public void modifTabCandidat(int[] tabResultat){

        Candidat[] tabCandidatTour2 = new Candidat[2];

        tabCandidatTour2[0]=getCandidat(tabResultat[0]-1);//-1 à cause du décalage dans tabCandidat
        tabCandidatTour2[1]=getCandidat(tabResultat[1]-1); 

        deleteTabCandidat();
        setNewTabCandidat(tabCandidatTour2);

        //On remplit tabIdCandidats
        tabIdCandidats[0]=getCandidat(0).getIdCandidat();
        tabIdCandidats[1]=getCandidat(1).getIdCandidat();
    }

    
    public int[] ToString1erTour(int abstention){
        System.out.println("");
        System.out.println("");
        System.out.println("Nombre de vote :");
        System.out.print("Abstention : ");
        System.out.println(abstention);

        int tabResult[] = new int[getNbCandidat()];
        tabResult=getNbVoix();

        for(int i=0;i<getNbCandidat();i++){
            System.out.print("Candidat "+(i+1)+" :");
            System.out.println(tabResult[i]+" ");
        }
        System.out.println("");
        tabResult=getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabResult[i]+" ");
        }
        System.out.println("");
        System.out.println("Le gagnant du premier tour est : Candidat "+tabResult[0]);
        System.out.println("");
        return tabResult;
        
    }

    public void ToString2eTour(int abstention){
        System.out.println("");
        System.out.println("Nombre de vote :");
        System.out.print("Abstention : ");
        System.out.println(abstention);

        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();

        for(int i=0;i<getNbCandidat();i++){
            System.out.print("Candidat "+(i+1)+" :");
            System.out.println(tabVoix[i]+" ");
        }
        System.out.println("");

        int tabResult[] = new int[getNbCandidat()];
        tabResult=getResultat2();

        System.out.print("Résultats : ");
        for(int i=0;i<2;i++){
            System.out.print(tabResult[i]+" ");
        }
        System.out.println("");
        System.out.println("Le gagnant de l'élection est : Candidat "+tabResult[0]+" !!!");
        System.out.println("");
    }

    
    public int[] getResultat2(){
        int tabRang[]=new int[getNbCandidat()];
        tabRang=getRangIndice();

        //On modifie tabRang pour enlever les égalités possibles
        changeEgalite(tabRang);

        int tabResult[]=new int[getNbCandidat()];

        for(int i=1;i<getNbCandidat()+1;i++){
            for(int j=0;j<getNbCandidat();j++){
                if(tabRang[j]==i){         
                    tabResult[i-1]=tabIdCandidats[j];
                }
            }
        }
        return tabResult;
    }

        
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void getCSV(int[] arrayVote) {
		ArrayList value1 = new ArrayList();
        ArrayList value2 = new ArrayList();
        ArrayList valuea = new ArrayList();
        ArrayList valueb = new ArrayList();
        ArrayList value3 = new ArrayList();
        ArrayList value4 = new ArrayList();
        ArrayList value5 = new ArrayList();
        ArrayList value6 = new ArrayList();
        ArrayList value7 = new ArrayList();
        ArrayList value8 = new ArrayList();
                
        int tabResult2[] = new int[arrayVote.length];
        for(int i = 0;i<arrayVote.length;i++)
        {
            tabResult2[i]=i+1;
        }
        int tabVoix2[] = new int[getNbCandidat()];
        tabVoix2=arrayVote;
        int tabResult5[] = new int[getNbCandidat()];
        tabResult5=getResultat2();
        int tabVoix4[] = new int[getNbCandidat()];
        tabVoix4=getNbVoix();
        
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
        resetTabCandidat();
        
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
                
        for(int i = 0; i < tabResult5.length; i++) {
			valuea.add("Candidat "+tabResult5[i]);
		}
                
        for(int i = 0; i < tabVoix4.length; i++) {
			valueb.add(tabVoix4[i]);
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
                    
			file = new FileWriter("RESULTAT VOTE MAJORITAIRE A 2 TOUR.csv");
            file.append("Résultat vote 1er tour");
            file.append("\n");
                        
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
            file.append("Résultat vote 2e tour");
            file.append("\n");
            for(int i = 0; i < valuea.size(); i++) {
				file.append(String.valueOf(valuea.get(i)));
				file.append(";");
			}
            file.append("\n");
			for(int i = 0; i < valueb.size(); i++) {
				file.append(String.valueOf(valueb.get(i)));
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