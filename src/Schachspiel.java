import java.util.ArrayList;

/**
 * Die Klasse Schachspiel enthält ein Spielbrett und die benötigten Spielfiguren
 * und speichert eine aktuelle Liste der geschlagenen Figuren. Das Schachspiel
 * kann seine aktuelle möglichen Züge, insgesamt oder nach Farbe, ausgeben, und
 * Züge durchführen und rückgängig machen, sowie prüfen, ob eine Schach- oder
 * Schachmatt-Situation vorliegt.
 * 
 * @version 1.2, 29.10.2021
 * @author lmey
 *
 */
public class Schachspiel {

	// Spielbrett
	private Spielbrett brett;

	// Spielfiguren
	private Bauer B1 = new Bauer(Spielfarbe.WEISS);
	private Bauer B2 = new Bauer(Spielfarbe.WEISS);
	private Bauer B3 = new Bauer(Spielfarbe.WEISS);
	private Bauer B4 = new Bauer(Spielfarbe.WEISS);
	private Bauer B5 = new Bauer(Spielfarbe.WEISS);
	private Bauer B6 = new Bauer(Spielfarbe.WEISS);
	private Bauer B7 = new Bauer(Spielfarbe.WEISS);
	private Bauer B8 = new Bauer(Spielfarbe.WEISS);
	private Bauer b1 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b2 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b3 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b4 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b5 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b6 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b7 = new Bauer(Spielfarbe.SCHWARZ);
	private Bauer b8 = new Bauer(Spielfarbe.SCHWARZ);

	private Turm T1 = new Turm(Spielfarbe.WEISS);
	private Springer S1 = new Springer(Spielfarbe.WEISS);
	private Laeufer L1 = new Laeufer(Spielfarbe.WEISS);
	private Dame D = new Dame(Spielfarbe.WEISS);
	private Koenig K = new Koenig(Spielfarbe.WEISS);
	private Laeufer L2 = new Laeufer(Spielfarbe.WEISS);
	private Springer S2 = new Springer(Spielfarbe.WEISS);
	private Turm T2 = new Turm(Spielfarbe.WEISS);

	private Turm t1 = new Turm(Spielfarbe.SCHWARZ);
	private Springer s1 = new Springer(Spielfarbe.SCHWARZ);
	private Laeufer l1 = new Laeufer(Spielfarbe.SCHWARZ);
	private Dame d = new Dame(Spielfarbe.SCHWARZ);
	private Koenig k = new Koenig(Spielfarbe.SCHWARZ);
	private Laeufer l2 = new Laeufer(Spielfarbe.SCHWARZ);
	private Springer s2 = new Springer(Spielfarbe.SCHWARZ);
	private Turm t2 = new Turm(Spielfarbe.SCHWARZ);

	private ArrayList<Spielfigur> geschlagene_spielfiguren = new ArrayList<>();
	private ArrayList<Zug> gespielte_zuege = new ArrayList<>();

	/**
	 * Konstruktor für ein leeres Schachspiel
	 */
	public Schachspiel() {
		this.brett = new Spielbrett();
	}

	/**
	 * Copy Konstruktor, der alle Attribute des übergebenen Schachspiels kopiert
	 * 
	 * @param schachspiel
	 */
	public Schachspiel(Schachspiel schachspiel) {
		this.brett = schachspiel.brett;
		this.b1 = new Bauer(schachspiel.b1);
		this.b2 = new Bauer(schachspiel.b2);
		this.b3 = new Bauer(schachspiel.b3);
		this.b4 = new Bauer(schachspiel.b4);
		this.b5 = new Bauer(schachspiel.b5);
		this.b6 = new Bauer(schachspiel.b6);
		this.b7 = new Bauer(schachspiel.b7);
		this.b8 = new Bauer(schachspiel.b8);
		this.B1 = new Bauer(schachspiel.B1);
		this.B2 = new Bauer(schachspiel.B2);
		this.B3 = new Bauer(schachspiel.B3);
		this.B4 = new Bauer(schachspiel.B4);
		this.B5 = new Bauer(schachspiel.B5);
		this.B6 = new Bauer(schachspiel.B6);
		this.B7 = new Bauer(schachspiel.B7);
		this.B8 = new Bauer(schachspiel.B8);

		this.T1 = new Turm(schachspiel.T1);
		this.S1 = new Springer(schachspiel.S1);
		this.L1 = new Laeufer(schachspiel.L1);
		this.D = new Dame(schachspiel.D);
		this.K = new Koenig(schachspiel.K);
		this.T2 = new Turm(schachspiel.T2);
		this.S2 = new Springer(schachspiel.S2);
		this.L2 = new Laeufer(schachspiel.L2);

		this.t1 = new Turm(schachspiel.t1);
		this.s1 = new Springer(schachspiel.s1);
		this.l1 = new Laeufer(schachspiel.l1);
		this.d = new Dame(schachspiel.d);
		this.k = new Koenig(schachspiel.k);
		this.t2 = new Turm(schachspiel.t2);
		this.s2 = new Springer(schachspiel.s2);
		this.l2 = new Laeufer(schachspiel.l2);

	}

	/**
	 * Setzt die Figuren auf das Spielbrett
	 */
	public void setzeStartstellung() {
		// Bauern
		brett.setFigur(B1, "a2");
		brett.setFigur(B2, "b2");
		brett.setFigur(B3, "c2");
		brett.setFigur(B4, "d2");
		brett.setFigur(B5, "e2");
		brett.setFigur(B6, "f2");
		brett.setFigur(B7, "g2");
		brett.setFigur(B8, "h2");
		brett.setFigur(b1, "a7");
		brett.setFigur(b2, "b7");
		brett.setFigur(b3, "c7");
		brett.setFigur(b4, "d7");
		brett.setFigur(b5, "e7");
		brett.setFigur(b6, "f7");
		brett.setFigur(b7, "g7");
		brett.setFigur(b8, "h7");

		brett.setFigur(T1, "a1");
		brett.setFigur(S1, "b1");
		brett.setFigur(L1, "c1");
		brett.setFigur(D, "d1");
		brett.setFigur(K, "e1");
		brett.setFigur(L2, "f1");
		brett.setFigur(S2, "g1");
		brett.setFigur(T2, "h1");

		brett.setFigur(t1, "a8");
		brett.setFigur(s1, "b8");
		brett.setFigur(l1, "c8");
		brett.setFigur(d, "d8");
		brett.setFigur(k, "e8");
		brett.setFigur(l2, "f8");
		brett.setFigur(s2, "g8");
		brett.setFigur(t2, "h8");
	}

	/**
	 * Die Methode addZug führt den übergebenen Zug auf dem Spielbrett aus.
	 * 
	 * @param zug Zug, der ausgeführt werden soll
	 * @throws SchachException wird geworfen, wenn der Zug dafür sorgen würde, dass
	 *                         jemand die eigene Farbe ins Schach stellen würde
	 */
	public void addZug(Zug zug) {
		// prüfe, ob der Zug möglich ist

		if (brett.getFigur(zug.getStartposition()).pruefeZug(zug, this)) {

			// führe den Zug durch
			zug.moveFigur();
			gespielte_zuege.add(zug);

			/*
			 * prüfe, ob der Zug die Farbe, die aktuell am Zug ist, in eine Schachsituation
			 * bringen würde, und wenn ja, mache den Zug rückgängig und werfe Fehlermeldung
			 */
			if (isSchach(brett, Spielfarbe.SCHWARZ) && zug.getSpielfigur().getFarbe() == Spielfarbe.SCHWARZ) {
				undoZug(zug);
				throw new SchachException("Zug ist nicht möglich weil Schwarz ins Schach ziehen würde!");
			}
			if (isSchach(brett, Spielfarbe.WEISS) && zug.getSpielfigur().getFarbe() == Spielfarbe.WEISS) {
				undoZug(zug);
				throw new SchachException("Zug ist nicht möglich weil Weiss ins Schach ziehen würde!");
			}

			// bei einem Schlag speichere die geschlagene Figur in dem Listenattribut
			if (zug.getZugart() == Zugart.SCHLAG || zug.getZugart() == Zugart.ENPASSANT) {
				this.geschlagene_spielfiguren.add(zug.getGeschlagen());
			}

			// wenn der Zug nicht möglich ist, werfe Exception
		} else { //TODO nachprüfen & auffällige Formatierung schaffen
			if (zug.getZugart() == Zugart.SCHLAG && brett.getFigur(zug.getZielposition()) == null) {
				throw new ZugException("Keine Figur zum Schlagen vorhanden!");
			}
			if (zug.getZugart() == Zugart.STANDARD && brett.getFigur(zug.getZielposition()) != null) {
				throw new ZugException("Das Zielfeld ist nicht frei!");
			}
			if (brett.getFigur(zug.getStartposition()) != zug.getSpielfigur()) {
				throw new ZugException("Keine passende Figur auf dem Startfeld");
			} else {
				throw new ZugException("Kein gültiger Zug!");
			}
		}
	}

	/**
	 * Macht den übergebenen Zug rückgängig (wird z.B. benötigt, um Schachsituation
	 * zu prüfen)
	 * 
	 * @param zug Zug, der zurückgesetzt werden soll
	 */
	public void undoZug(Zug zug) {
		zug.rueckmoveFigur();
		if (zug.getZugart() == Zugart.SCHLAG || (zug.getZugart() == Zugart.ENPASSANT)) { // falls ein Schlag rückgängig
																							// gemacht werden soll,
																							// entferne die
			// zurückgesetzte Figur aus der Liste der geschlagenen Figuren
			this.geschlagene_spielfiguren.remove(zug.getGeschlagen());
		}
		gespielte_zuege.remove(zug);

	}

	/**
	 * Mögliche Hilfsmethode für die Rochade - durchläuft bisherige Züge und prüft,
	 * ob die übergebene Spielfigur im Spielverlauf bereits gezogen wurde
	 * 
	 * @param spielfigur Spielfigur
	 * @return isGezogen boolean, true genau dann wenn die Figur schon gezogen wurde
	 */
	public boolean isGezogen(Spielfigur spielfigur) {
		boolean isGezogen = false;
		for (Zug z : gespielte_zuege) {
			if (z.getSpielfigur().equals(spielfigur)) {
				isGezogen = true;
			}
		}
		return isGezogen;
	}

	/**
	 * Get Liste aller möglichen Züge aller Figuren, indem alle Positionen auf dem
	 * Brett durchgegangen werden und bei Positionen mit Figuren die möglichen Züge
	 * dieser Figuren in die Liste gepackt werden
	 * 
	 * @param brett Spielbrett, auf dem gespielt wird
	 * @return zuege ArrayList<Zug> Liste aller möglichen Züge
	 */
	public ArrayList<Zug> getZuege(Spielbrett brett) {
		ArrayList<Zug> zuege = new ArrayList<>();

		for (int i = 0; i < brett.getBrett().length; i++) {
			for (int j = 0; j < brett.getBrett()[0].length; j++) {
				if (brett.getFigur(i, j) != null) {
					for (Zug zug : brett.getFigur(i, j).getZuege(this)) {
						zuege.add(zug);
					}
				}
			}
		}
		return zuege;
	}

	/**
	 * Wie getZuege(Spielbrett brett), nur dass eine Spielfarbe übergeben werden
	 * kann, nach der die möglichen Züge gefiltert werden sollen
	 *
	 * @param brett Spielbrett, auf dem gespielt wird
	 * @param farbe Spielfarbe, deren Züge zurückgegeben werden sollen
	 * @return zuege ArrayList<Zug> Liste aller möglichen Züge der übergebenen
	 *         Spielfarbe
	 */
	public ArrayList<Zug> getZuege(Spielbrett brett, Spielfarbe farbe) {
		ArrayList<Zug> zuege = new ArrayList<>();

		for (int i = 0; i < brett.getBrett().length; i++) {
			for (int j = 0; j < brett.getBrett()[0].length; j++) {
				if (brett.getFigur(i, j) != null && brett.getFigur(i, j).getFarbe() == farbe) {
					for (Zug zug : brett.getFigur(i, j).getZuege(this)) {
						zuege.add(zug);
					}
				}
			}
		}
		return zuege;
	}

	/**
	 * Prüft, ob die angegebenen Spielfarbe Schachmatt ist. Dafür wird geprüft, ob
	 * die Farbe im Schach steht, und zusätzlich, dass es keinen einzelnen Zug
	 * dieser Farbe gibt, die zu einem Ende der Schachsituation führen würde, indem
	 * jeder Zug nacheinander durchgeführt und rückgängig gemacht wird.
	 * 
	 * @param brett Spielbrett, auf dem aktuell gespielt wird
	 * @param farbe Spielfarbe (schwarz oder weiss), die geprüft werden soll
	 * @return boolean true genau dann wenn Schachmatt ist
	 */
	public boolean isMatt(Spielbrett brett, Spielfarbe farbe) {
		boolean isMatt = false;
		if (isSchach(brett, farbe)) {
			isMatt = true;
			for (Zug zug : getZuege(brett, farbe)) {
				try {
					addZug(zug);
				} catch (SchachException e) {
					continue; // Exception kann hier ignoriert werden, da nur geprüft werden muss, ob Züge die
								// Schachposition auflösen, und Züge, die sie herbeiführen, dies nicht tun
				}
				if (isSchach(brett, farbe) == false) {
					isMatt = false;
				}
				undoZug(zug);
			}

		}
		return isMatt;
	}

	/**
	 * Prüft, ob die übergebene Farbe im Schach steht (d.h. ob der König der
	 * übergebenen Farbe vom Gegner bedroht wird)
	 * 
	 * @param brett Spielbrett, das geprüft werden soll
	 * @param farbe Spielfarbe (schwarz oder weiß), von der geprüft werden soll, ob
	 *              sie im Schach steht
	 * @return boolean true genau dann wenn die übergebene Farbe im Schach steht
	 */
	public boolean isSchach(Spielbrett brett, Spielfarbe farbe) {
		for (Zug zug : getZuege(brett)) {
			if (farbe == Spielfarbe.WEISS && zug.getZielposition().equals(K.getPosition())) {
				return true;
			}
			if (farbe == Spielfarbe.SCHWARZ && zug.getZielposition().equals(k.getPosition())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter
	 * 
	 * @return brett Spielbrett, das zu dem aktuellen Spiel gehört
	 */
	public Spielbrett getSpielbrett() {
		return this.brett;
	}

	/**
	 * Getter
	 * 
	 * @return gespielte_zuege ArrayList<Zug>, die alle gespielten Züge widergibt
	 */
	public ArrayList<Zug> getGespielteZuege() {
		return this.gespielte_zuege;
	}

	/**
	 * Getter
	 * 
	 * @return zug Der letzte Zug, der gespielt wurde.
	 */
	public Zug getLastZug() {
		return getGespielteZuege().get(getGespielteZuege().size() - 1);
	}

	/**
	 * toString()-Methode, die die graphische Spielsituation auf dem Spielbrett,
	 * sowie eine Liste der aktuell möglichen Züge und die geschlagenenen Figuren
	 * nach Farbe in einen String packt
	 * 
	 * @return Ausgabestring
	 *
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(System.getProperty("line.separator") + brett.toString() + System.getProperty("line.separator"));
		sb.append("Bisherige Züge: " + gespielte_zuege + System.getProperty("line.separator"));
		sb.append("Mögliche Züge: " + getZuege(this.getSpielbrett()) + System.getProperty("line.separator"));

		sb.append("Geschlagene Figuren: " + System.getProperty("line.separator") + " Weiss: ");
		for (Spielfigur s : this.geschlagene_spielfiguren) {
			if (s.getFarbe() == Spielfarbe.WEISS) {
				sb.append(s);
			}
		}
		sb.append(System.getProperty("line.separator") + " Schwarz: ");
		for (Spielfigur s : this.geschlagene_spielfiguren) {
			if (s.getFarbe() == Spielfarbe.SCHWARZ) {
				sb.append(s);
			}
		}

		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

}

//public boolean checkRochade(Spielfarbe farbe) {
//if (farbe == Spielfarbe.WEISS) {
//	for (Zug z : gespielte_zuege) {
//		if (z.getSpielfigur().equals(K)) {
//
//		}
//	}
//}
//}

//public Spielfigur[][] getSchachstellung() {
//return this.brett.getBrett();
//}
// OUTPUT += THIS.GESCHLAGENE_SPIELFIGUREN;
//FOR (SPIELFIGUR S: THIS.SPIELFIGUREN) {
//	IF (S.ISGESCHLAGEN == TRUE) {
//		OUTPUT += S;
//	}
//}

//	String oberrand = "    ";
//	for (int i = 0; i < 8; i++) {
//		oberrand += "+---";
//	}
//	oberrand += "+\n";
//
//	String abc = "    "; //abc
//	for (int i = 0; i < 8; i++) {
//		abc +=  "  " + (char) (i + 97) + " ";
//	}
//	
//	String output = abc;
//	output += "\n";
//	for (int i = 0; i < 8; i++) {
//		output += oberrand;
//		output += "  "+(9 - (i + 1))+" ";
//		for (int j = 0; j < 8; j++) {
//			if (schachstellung.getBrett()[i][j] == null) {
//				output +=  "| " + " " + " ";
//			} else {
//				output += "| " + schachstellung.getBrett()[i][j].symbol + " ";
//			}
//		}
//		output += "| " + (9 - (i + 1)) + "\n";
//	}
//	output += oberrand;
//	output +=abc+"\n";
//
//	return output;
//}

//public boolean isMattWeissAlt(Spielbrett schachstellung) {
//	boolean isMatt = false;
//	for (Zug zug : getZuege(schachstellung)) {
//		if ((K.getZuege(schachstellung).isEmpty()) && zug.getZielposition().equals(K.getPosition())) {
//			isMatt = true;
//			for (Zug gegenzug : getZuege(schachstellung)) {
//				if (gegenzug.getZielposition().equals(zug.getStartposition())) {
//					isMatt = false;
//				}
//			}
//		} else {
//			for (Zug k_zug : K.getZuege(schachstellung)) {
//				if (zug.getZielposition().equals(k_zug.getZielposition())) {
//					isMatt = true;
//					for (Zug gegenzug : getZuege(schachstellung)) {
//						if (gegenzug.getZielposition().equals(zug.getStartposition())) {
//							isMatt = false;
//						}
//					}
//				}
//			}
//		}
//	}
//	return isMatt;
//
//}
//
//public boolean isMattSchwarzAlt(Spielbrett schachstellung) {
//	boolean isMatt = false;
//	for (Zug zug : getZuege(schachstellung)) {
//		if ((k.getZuege(schachstellung).isEmpty()) && zug.getZielposition().equals(k.getPosition())) {
//			isMatt = true;
//			for (Zug gegenzug : getZuege(schachstellung)) {
//				if (gegenzug.getZielposition().equals(zug.getStartposition())) {
//					isMatt = false;
//				}
//			}
//		} else {
//			for (Zug k_zug : k.getZuege(schachstellung)) {
//				if (zug.getZielposition().equals(k_zug.getZielposition())) {
//					isMatt = true;
//					for (Zug gegenzug : getZuege(schachstellung)) {
//						if (gegenzug.getZielposition().equals(zug.getStartposition())) {
//							isMatt = false;
//						}
//					}
//				}
//			}
//		}
//	}
//
//	return isMatt;
//
//}

//public boolean isMattSchwarzAlt(Spielbrett schachstellung) {
//	boolean isMatt = false;
//	if (isSchachSchwarz(schachstellung)) {
//		isMatt = true;
//		for (Zug zug : getZuege(schachstellung, Spielfigur.farben.SCHWARZ)) {
//				Schachspiel alternative  = new Schachspiel(this);
//				System.out.println("vor Zug \n" + alternative.schachstellung);
//				alternative.addZug(zug, alternative.schachstellung);
//				System.out.println("nach Zug \n" +alternative.schachstellung);
//				System.out.println("nach Zug original \n" +this.schachstellung);
//
//				if (alternative.isSchachSchwarz(alternative.schachstellung)==false) {
//					isMatt=false;
//				}
//		}
//			
//	}
//	return isMatt;
//}

//public boolean isMattSchwarz(Spielbrett schachstellung) {
//boolean isMatt = false;
//if (isSchachSchwarz(schachstellung)) {
//	isMatt = true;
//	for (Zug zug : getZuege(schachstellung, Spielfarbe.SCHWARZ)) {
//		try {
//			addZug(zug);
//		} catch (SchachException s) {
//			continue; //
//		}
//		if (isSchachSchwarz(schachstellung) == false) {
//			isMatt = false;
//			// break;
//		}
//		undoZug(zug);
//	}
//
//}
//return isMatt;
//}
//
//public boolean isMattWeiss(Spielbrett schachstellung) {
//boolean isMatt = false;
//if (isSchachWeiss(schachstellung)) {
//	isMatt = true;
//	for (Zug zug : getZuege(schachstellung, Spielfarbe.WEISS)) {
//		addZug(zug);
//		if (isSchachWeiss(schachstellung) == false) {
//			isMatt = false;
//			// break;
//		}
//		undoZug(zug);
//	}
//
//}
//return isMatt;
//}

//public boolean isSchachSchwarz(Spielbrett brett) { 
//for (Zug zug : getZuege(brett)) {
//	if (zug.getZielposition().equals(k.getPosition())) {
//		return true;
//	}
//}
//return false;
//}
//
//public boolean isSchachWeiss(Spielbrett brett) { 
//for (Zug zug : getZuege(brett)) {
//	if (zug.getZielposition().equals(K.getPosition())) {
//		return true;
//	}
//}
//return false;
//}
//
