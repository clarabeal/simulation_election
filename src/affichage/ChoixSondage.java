package affichage;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class ChoixSondage extends Choix {

	// attributs :
	
	private JTextField tfLongueur;
	private JTextField tfLargeur;
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

    @Override
    public void reinitialiser() {
    	NBSONDAGEPREFERENCE.setText("");
        NBSONDAGEUTILITE.setText("");
        NBSONDAGEUTILITEPORPOR.setText("");
    	comboBox.setSelectedItem(SONDAGE_PREFERENCE);
    }
    
    public String getPREFERENCEValue() {
    	String tab;
    	tab = NBSONDAGEPREFERENCE.getText();

    	return tab;
    }
    
    public String getUTILITEValue() {
    	String tab;
    	tab = NBSONDAGEUTILITE.getText();

    	return tab;
    }
    
    public String getUTILITEPROPORValue() {
    	String tab;
    	tab = NBSONDAGEUTILITEPORPOR.getText();

    	return tab;
    }

    @Override
    public Object getSelection() {
    	return comboBox.getSelectedItem();
    }

    public void addComponentToPane(Container c) {
		c.add(panelComboBox, BorderLayout.PAGE_START);
                c.add(cards, BorderLayout.CENTER);
	}

    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
}