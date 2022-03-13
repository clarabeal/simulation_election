# Application de simulation d'élection

Projet réalisé par Hugo LIEVRE et Clara BEAL dans le cadre du Projet S5 d'Algorithmique Programmation Objet à Polytech Lyon.

## Présentation du projet

Cette application permet de simuler une élection tout en choisissant le type de scrutin. Il est aussi possible d'effectuer des interactions et des sondages afin de faire évoluer les opinions des électeurs. Nous avons mis en place une interface graphique qui permet de saisir les différents paramètres et aussi d'afficher les résultats sous forme d'histogramme.

## Différents types de scrutin

- Scrutin majoritaire à un tour

- Scrutin majoritaire à deux tours

- Vote par approbation

- Vote alternatif

- Méthode de Borda

## Dynamique

- Interactions socio-politiques

- Sondage basé sur les préférences

- Sondage basé sur l'utilité 

- Sondage basé sur l'utilité (Proportion)

## Extensions

### Sauvegarde

Grâce à la méthode getCSV(), il est possible de récupérer les valeurs de la simulation dans un fichier CSV qui peut être utilisé sur Excel. Ce fichier se crée s’il n’est pas déjà crée dans le dossier du projet, ou écrasera les données s’il y en a déjà un du même nom.

### Interface graphique

Nous avons mis en place une interface graphique qui permet de saisir les différents paramètres de l'élection. Nous avons aussi choisi d'afficher les résultats de l'élection sous la forme d'histogrammes grâce à la librairie JFreeChart.