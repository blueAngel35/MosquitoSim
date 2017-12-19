package simulator;

public class Mosquito {

	static Boolean infected;
	static double deathrate;
	static double probaTransmission;
	static double probaMorsure;

	public  Mosquito() { //genere un moustique "basique" ne sert que pour les tests
		infected=true;
		deathrate=0.5;
		probaTransmission=0.9;
		probaMorsure=0.9;
	}
	
	public  Mosquito(Boolean infected, double deathrate, double probaTransmission, double probaMorsure) { 
		//genere un moustique "paramétré"
		Mosquito.infected=infected;
		Mosquito.deathrate=deathrate;
		Mosquito.probaTransmission=probaTransmission;
		Mosquito.probaMorsure=probaMorsure;
	}

	public Boolean testMorsure()  { // dit si oui ou non le moustique va mordre e
		double n = Math.random();  // tire un nombre au hasard entre 0 et 1
		return n <= probaMorsure; // compare le nombre avec le seuil de probabilité
	}

	public Boolean testMort() {
		double n = Math.random();
		return n <= deathrate;
	}

	public Boolean isInfected() {return infected;}
	
	public void morsure(Vertebrate vert) {
		//Morsure d'un vertébré précis, calcule toutes les probabilités et effectue les contaminations
		double n = Math.random(); // nombre aléatoire pour les probabilités
		if (infected && !vert.infected) { //moustique infecté vertébré sain
			if (n <= probaTransmission) {
				vert.infected=true;
				//System.out.println("Transmission Moustique Vertébré");
			} else {
				//System.out.println("Pas de Transmission 1");
			}
		} else if (!infected && vert.infected) { //moustique sain vertébré infecté
			if (n <= probaTransmission) {
				infected=true;
				//System.out.println("Transmission Vertébré Moustique");
			} else {
				//System.out.println("Pas de Transmission 2");
			}
		} else {
			//System.out.println("Pas de Transmission 3");
		}
	}

}
