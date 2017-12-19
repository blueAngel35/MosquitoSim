package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulator.Simulation;

public class MosquitoSimulator extends JFrame {

	//Paramètres par défauts (modifiable par l'interface graphique)
	static int nMosquito = 20;
	static int nVert = 50;
	static double deathrate=0.5;
	static double pTransmission=0.9;
	static double pBiting=0.9;


	//Objets de l'interface
	private JButton bouton = new JButton("Débuter Simulation");
	private JButton boutonSave = new JButton("Sauvegarder Résultats de la simulation");
	private JPanel pan = new JPanel();

	private JFormattedTextField champsNMosquito = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel labelNMosquito = new JLabel("Nombre de moustiques");

	private JFormattedTextField champsNVert = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel labelNVert = new JLabel("Nombre de vertébrés");

	private JFormattedTextField champsDR = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel labelDR = new JLabel("Taux de mortalité");

	private JFormattedTextField champsPT = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel labelPT = new JLabel("Probabilité de transmission");

	private JFormattedTextField champsPM = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel labelPM = new JLabel("Probabilité de morsure");
	
	Simulation sim;

	public MosquitoSimulator(){                
		this.setTitle("MosquitoSimulator Beta V1");
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);

		bouton.addActionListener(new BoutonListener()); 
		boutonSave.addActionListener(new BoutonListener2());
		boutonSave.setEnabled(false);

		Font police = new Font("Arial", Font.BOLD, 14);
		champsNMosquito.setFont(police);
		champsNMosquito.setPreferredSize(new Dimension(150, 30));
		champsNMosquito.setText(Integer.toString(nMosquito));

		pan.add(labelNMosquito);
		pan.add(champsNMosquito);

		champsNVert.setFont(police);
		champsNVert.setPreferredSize(new Dimension(150, 30));
		champsNVert.setText(Integer.toString(nVert));

		pan.add(labelNVert);
		pan.add(champsNVert);

		champsDR.setFont(police);
		champsDR.setPreferredSize(new Dimension(150, 30));
		champsDR.setText(Double.toString(deathrate));

		pan.add(labelDR);
		pan.add(champsDR);

		champsPT.setFont(police);
		champsPT.setPreferredSize(new Dimension(150, 30));
		champsPT.setText(Double.toString(pTransmission));

		pan.add(labelPT);
		pan.add(champsPT);

		champsPM.setFont(police);
		champsPM.setPreferredSize(new Dimension(150, 30));
		champsPM.setText(Double.toString(pBiting));

		pan.add(labelPM);
		pan.add(champsPM);

		pan.add(bouton);
		pan.add(boutonSave);

		this.setContentPane(pan);

		this.setVisible(true);
	}


	public static void main(String[] args) {
		MosquitoSimulator fenetre = new MosquitoSimulator();
	}

	public class BoutonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nMosquito = Integer.parseInt(champsNMosquito.getText());
			nVert = Integer.parseInt(champsNVert.getText());
			deathrate = Double.parseDouble(champsDR.getText());
			pTransmission = Double.parseDouble(champsPT.getText());
			pBiting = Double.parseDouble(champsPM.getText());;
			sim = new Simulation(nMosquito, nVert,deathrate,pTransmission,pBiting);	
			if (boutonSave.isEnabled()==false) {boutonSave.setEnabled(true);}
		} 
	}

	public class BoutonListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e)  {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(null);
			
			DataOutputStream dos;
			try {
				dos = new DataOutputStream(
				          new BufferedOutputStream(
				            new FileOutputStream(fileChooser.getSelectedFile())));
				dos.writeChars("Tour\tNombre de moustiques inféctés\tNombre de mostiques\tNombre de vértébrés infectés\ttNombre de vértébrés\n");
				for (int i=0; i<sim.getTab().getNumberLine();i++) {
					dos.writeChars(sim.getTab().getLine(i));
					dos.writeChars("\n");
				}
				
				dos.close();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			
			
			

		} 
	}

}
