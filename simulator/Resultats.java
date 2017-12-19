package simulator;

import java.util.ArrayList;
import java.util.List;

public class Resultats {
	
	static List<Number> listeTours = new ArrayList<Number>();
	static List<Number> listeMosquitoInf = new ArrayList<Number>();
	static List<Number> listeMosquito = new ArrayList<Number>();
	static List<Number> listeVertInf = new ArrayList<Number>();
	static List<Number> listeVert = new ArrayList<Number>();
	
	public Resultats(int nMosquitoInf, int nMosquito, int nVertInf, int nVert) {
		listeTours.add(0);
		listeMosquitoInf.add(nMosquitoInf);
		listeMosquito.add(nMosquito);
		listeVertInf.add(nVertInf);
		listeVert.add(nVert);
	}
	
	public void ajouterLigne(int tour, int nMosquitoInf, int nMosquito, int nVertInf, int nVert) {
		listeTours.add(tour);
		listeMosquitoInf.add(nMosquitoInf);
		listeMosquito.add(nMosquito);
		listeVertInf.add(nVertInf);
		listeVert.add(nVert);
	}
	
	public void sortie() {
		String str = "";
		for (int i=0; i<listeTours.size();i++) {
			str=str+=listeTours.get(i)+"\t"+listeMosquitoInf.get(i)+"\t"+listeMosquito.get(i)+"\t"+listeVertInf.get(i)+"\t"+listeVert.get(i)+"\n";
		}
		System.out.println(str);
	}
	
	public String getLine(int n) {
		String str = listeTours.get(n) + "\t";
		str = str+listeMosquitoInf.get(n) + "\t";
		str = str+listeMosquito.get(n) + "\t";
		str = str+listeVertInf.get(n) + "\t";
		str = str+listeVert.get(n) + "\t";
		return str;
	}
	
	
	public int getNumberLine() {
		return listeTours.size();
	}

}
