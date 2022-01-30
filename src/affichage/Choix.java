package Affichage;

import java.awt.Container;
import java.awt.event.ItemListener;
/**
 * Classe qui permet de créer un menu déroulant.
 */
public abstract class Choix implements ItemListener {
	
	// méthodes :
	/**
	 * Remet les paramètres de base (ceux qu'on a à l'initialisation).
	 */
	public abstract void reinitialiser();
        
	/**
	 * Renvoie le choix sélectionné par le menu déroulant.
	 * @return Le choix sélectionné par le menu déroulant.
	 */
	public abstract Object getSelection();
	
    /**
	 * Ajoute le menu déroulant à un objet de classe Container.
	 * @param c
	 * 		L'objet de classe Container auquel on veut ajouter le menu déroulant.
	 */
	public abstract void addComponentToPane(Container c);
	
}