package personnes;
import java.util.Random;

/**
 * Personne est la classe qui contient les éléments communs à toutes les personnes
 */
public class Personne {
    private double[] representation;

    /**
	 * Le construteur de la classe
	 */
    public Personne(){
        this.representation = new double[2];
        //On remplit aléatoirement la représentation
        Random ran = new Random();
        representation[0]=Math.round(ran.nextFloat()*100.0)/100.0;//de 0 à 1
        representation[1]=Math.round(ran.nextFloat()*100.0)/100.0;
    }

    /**
     * Fonction qui retourne la représentation de l'indice i
     * @param i
     *      L'indice de l'axe qu'il faut retourner
     * @return representation[i], l'axe politique de l'indice i
     */
    public double getRepresentation(int i){
        return this.representation[i];
    }
    
    /** 
     * Modifie l'axe d'indice i
     * @param i
	 * 		L'indice de l'axe qu'il faut modifier
     * @param val 
     *      La valeur de l'axe
     */
    public void setRepresentation(int i,double val){
        this.representation[i]=val;
    }
}

