package Affichage;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

/**
 * Classe qui permet de créer un menu déroulant pour choisir quel sondage l'utilisateur veut chosir.
 */
public class ChoixSondage extends Choix {

    // attributs :

    public final static String SONDAGE_PREFERENCE = "Sondage basé sur les préférences";
    public final static String SONDAGE_UTILITE = "Sondage basé sur l'utilité";
    public final static String SONDAGE_UTILITE_PORPORTION = "Sondage basé sur l'utilité (Proportion)";
    private JTextField NBSONDAGEPREFERENCE, NBSONDAGEUTILITE, NBSONDAGEUTILITEPORPOR ;
    private String comboBoxItems[] = { SONDAGE_PREFERENCE, SONDAGE_UTILITE, SONDAGE_UTILITE_PORPORTION };
    private JPanel panelComboBox;
    private JComboBox<String> comboBox;
    private JPanel cards;
    private JPanel card1;
    private JPanel card2;
    private JPanel card3;


    
    // constructeur :
    
    /**
     * Le constructeur permet de créer un menu déroulant pour choisir le sondage
     */
    public ChoixSondage() {
        panelComboBox = new JPanel();
        comboBox = new JComboBox<String>(comboBoxItems);
        comboBox.setEditable(false);
        comboBox.addItemListener(this);
        panelComboBox.add(comboBox);


        card1 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelSONDAGEPREFERENCE = new JLabel("Nombre de sondage");
        card1.add(labelSONDAGEPREFERENCE);
        NBSONDAGEPREFERENCE = new JTextField();
        card1.add(NBSONDAGEPREFERENCE);


        card2 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelSONDAGEUTILITE = new JLabel("Nombre de sondage");
        card2.add(labelSONDAGEUTILITE);
        NBSONDAGEUTILITE = new JTextField();
        card2.add(NBSONDAGEUTILITE);

        card3 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelSONDAGEUTILITEPROPOR = new JLabel("Nombre de sondage");
        card3.add(labelSONDAGEUTILITEPROPOR);
        NBSONDAGEUTILITEPORPOR = new JTextField();
        card3.add(NBSONDAGEUTILITEPORPOR);


        cards = new JPanel(new CardLayout());
        cards.add(card1, SONDAGE_PREFERENCE);
        cards.add(card2, SONDAGE_UTILITE);
        cards.add(card3, SONDAGE_UTILITE_PORPORTION);
    }

    /**
	 * Remet les paramètres de base (ceux qu'on a à l'initialisation).<br>
	 * Cela vide donc toutes les zones de texte et remet le choix initial (scrutin PREFERENCE).
	 */
    @Override
    public void reinitialiser() {
    	NBSONDAGEPREFERENCE.setText("");
        NBSONDAGEUTILITE.setText("");
        NBSONDAGEUTILITEPORPOR.setText("");
    	comboBox.setSelectedItem(SONDAGE_PREFERENCE);
    }
    
    /**
	 * Renvoie le paramètre entré par l'utilisateur dans la zone de texte du sondage PREFERENCE 
	 * @return Une chaine de caractère contenant le paramètre entré par l'utilisateur dans la zones de texte du sondage PREFERENCE
	 */
    public String getPREFERENCEValue() {
    	String tab;
    	tab = NBSONDAGEPREFERENCE.getText();

    	return tab;
    }
    
    /**
	 * Renvoie le paramètre entré par l'utilisateur dans la zone de texte du sondage UTILITE 
	 * @return Une chaine de caractère contenant le paramètre entré par l'utilisateur dans la zones de texte du sondage UTILITE
	 */
    public String getUTILITEValue() {
    	String tab;
    	tab = NBSONDAGEUTILITE.getText();

    	return tab;
    }
    
    /**
	 * Renvoie le paramètre entré par l'utilisateur dans la zone de texte du sondage UTILITE PROPORTION
	 * @return Une chaine de caractère contenant le paramètre entré par l'utilisateur dans la zones de texte du sondage UTILITE PROPORTION
	 */
    public String getUTILITEPROPORValue() {
    	String tab;
    	tab = NBSONDAGEUTILITEPORPOR.getText();

    	return tab;
    }

    /**
	 * Renvoie le choix sélectionné par le menu déroulant (pour le modèle).
	 * @return Le choix sélectionné par le menu déroulant (pour le modèle).
	 */
        @Override
    public Object getSelection() {
    	return comboBox.getSelectedItem();
    }

    /**
	 * Ajoute le menu déroulant à un objet de classe Container.
	 * @param c
	 * 		L'objet de classe Container auquel on veut ajouter le menu déroulant.
	 */
    @Override
    public void addComponentToPane(Container c) {
        c.add(panelComboBox, BorderLayout.PAGE_START);
        c.add(cards, BorderLayout.CENTER);
    }

    /**
     * Change les zones de textes en fonction du choix dans le menu déroulant.
     */
        @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
	
}