package personnes;
import java.util.Random;

public class Personne {
    private double[] representation;

    public Personne(){
        this.representation = new double[2];
        //On remplit aléatoirement la représentation
        Random ran = new Random();
        representation[0]=Math.round(ran.nextFloat()*10.0)/10.0; //de 0 à 1
        representation[1]=Math.round(ran.nextFloat()*10.0)/10.0;
    }

    //Retourne la représentation de l'indice i
    public double getRepresentation(int i){
        return this.representation[i];
    }
}

