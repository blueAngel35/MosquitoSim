package simulator;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
	
	//paramètres de simulation
	static int nMosquito;
	static int nVert;
	static double deathrate;
	static double probaTransmission;
	static double probaMorsure;

	
	//objets
	static List<Mosquito> listeMosquito;
	static List<Vertebrate> listeVertebrate;
	static int nTour;
	private static Resultats tableau;
	

	
	public Simulation(int nMosquito, int nVert,  double deathrate, double probaTransmission,  double probaMorsure) {

		Simulation.nMosquito = 0;Simulation.nVert = 0;Simulation.deathrate = 0;
		Simulation.probaTransmission = 0;Simulation.probaMorsure = 0;Simulation.nTour = 0;
		listeMosquito = new ArrayList<Mosquito>();listeVertebrate = new ArrayList<Vertebrate>();
		
		Simulation.nMosquito=nMosquito;
		Simulation.nVert=nVert;
		
		Simulation.deathrate = deathrate;
		Simulation.probaTransmission = probaTransmission;
		Simulation.probaMorsure = probaMorsure;
				
		for (int i=0; i<nMosquito; i++) {
			Mosquito m = new Mosquito(true, deathrate, probaTransmission, probaTransmission) ;
			listeMosquito.add(m);
		}
		
		for (int i=0; i<nVert; i++) {
			Vertebrate v = new Vertebrate();
			listeVertebrate.add(v);
		}
		
		int nVertInf = 0;
		int nMosqInf = 0;
				
		for (Mosquito m : listeMosquito) {if (m.isInfected()) {nMosqInf++;}}
		
		for (Vertebrate v : listeVertebrate) {if (v.isInfected()) {nVertInf++;}}
				
		tableau = new Resultats(nMosqInf, nMosquito, nVertInf, nVert);			
		for (int i=0; i<15; i++) {tourSimulation();}
				
		tableau.sortie();
	}
	
	
	
	public static void tourSimulation() {
		nTour++;
		updateMosquito();
		tourContamination();
		resultats();
	}
	
	public static void resultats() {
		int nVertInf = 0;
		int nMosqInf = 0;
		
		for (Mosquito m : listeMosquito) {
			if (m.isInfected()) {nMosqInf++;}
		}
		
		for (Vertebrate v : listeVertebrate) {
			if (v.isInfected()) {nVertInf++;}
		}
		
		System.out.println("Moustiques infectés : "+nMosqInf+"/"+nMosquito);
		System.out.println("Vertébrés infectés : "+nVertInf+"/"+nVert);
		
		tableau.ajouterLigne(nTour, nMosqInf, nMosquito, nVertInf, nVert);
	}
	
	public static void updateMosquito() {
		for (Mosquito m : listeMosquito) {
			if (m.testMort()) {m = new Mosquito();}
		}
	}
	
	public Resultats getTab() {return tableau;}
	
	public static void tourContamination() {
		int i = 0;
		if (nMosquito>nVert) {
			for (Mosquito m : listeMosquito) {
				if (m.testMorsure()) {
					m.morsure(listeVertebrate.get(i)); i++;
					if (i == listeVertebrate.size()) {i=0;}
				}
			}
		} else {
			Mosquito m;
			for (Vertebrate v : listeVertebrate) {
				m = listeMosquito.get(i);
				if (m.testMorsure()) {
					m.morsure(v); i++;
					if (i == listeMosquito.size()) {i=0;}
				}
			}
		}

	}

}
