/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Affichage;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * AffichageHist est la classe qui permet l'affichage d'un histogramme
 */
@SuppressWarnings("serial")
public class AffichageHist{

    /**
	 * Le construteur de la classe
	 */
    public AffichageHist(){}
    
    /** 
     * Fonction qui crée et affiche l'histogramme
     * @param array
	 * 		Le tableau contenant les résultats du vote
     */
    public static void createAndShowGui(int[] array)
    {
        JFrame frame = new JFrame();
        double[] dataArray= new double[array.length];
        
 
        for(int i = 0;i<array.length;i++)
        {
           dataArray[i] = array[i];
        }
        
        JFreeChart chart = getHistogramChart("Histogramme du nombre de vote par candidat", dataArray);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(3000);
        chartPanel.setMaximumDrawWidth(3000);
        frame.add(chartPanel);

        
        frame.setSize(450, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /** 
     * Fonction qui permet d'obtenir l'initialisation de l'histogramme
     * @param array
	 * 		Le tableau contenant les résultats du vote
     *@return chart           
     */
    private static JFreeChart getHistogramChart(String name, double[] dataArray)
    {
        String plotTitle = name;
        String xAxisLabel = "Candidat";
        String yAxis = "Nombre de vote";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (int i = 0; i < dataArray.length; i++)
        {
            dataSet.addValue(dataArray[i], "Candidat "+i, (Integer) (i+1));
        }
        boolean show = true;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createBarChart(plotTitle, xAxisLabel,
            yAxis, dataSet, orientation, show, toolTips, urls);
        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 8));
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        return chart;
    }
    
    /** 
     * Fonction qui crée et affiche l'histogramme
     * @param array
	 * 		Le tableau contenant les résultats du vote
     * @param ChartName
	 * 		Contient une chaine de caractère à afficher en titre (utile pour les votes à plusieurs scrutins
     */
    public static void createAndShowGuiName(int[] array, String ChartName)
    {
        JFrame frame = new JFrame();
        double[] dataArray= new double[array.length];
        
 
        for(int i = 0;i<array.length;i++)
        {
           dataArray[i] = array[i];
        }
        
        JFreeChart chart = getHistogramChart(ChartName, dataArray);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(3000);
        chartPanel.setMaximumDrawWidth(3000);
        frame.add(chartPanel);

        
        frame.setSize(450, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


