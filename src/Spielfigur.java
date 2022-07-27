import java.util.ArrayList;
import java.util.List;

/**
 * Spielfigur Klasse erzeugt eine Spielfigur mit farbe, position und symbol und
 * Marker dafür, ob sie geschlagen wurde oder nicht. Diese Klasse wird an
 * verschiedene Spielfiguren vererbt, die ihre eigene Methode zum Überprüfen von
 * Zügen implmentieren. Die Klasse stellt Getter, Setter und Methoden zur
 * Überprüfung von möglichen Zügen und zur Überprüfung, ob ein Gegner auf einem
 * Feld steht, bereit.
 * 
 * @version 1.2, 29.10.2021
 * @author lmey
 */
public abstract class Spielfigur {
	private Spielfarbe farbe;
	private Position position;
	private String symbol;

	/**
	 * Konstruktor, der Spielfigur der gegebenen Farbe erzeugt.
	 * 
	 * @param farbe Farbe der Spielfigur
	 */
	public Spielfigur(Spielfarbe farbe) {
		this.farbe = farbe;
	}

	/**
	 * Copy Konstruktor, der ein neues Objekt durch Kopieren der Attribute der
	 * gegebenen Spielfigur erzeugt
	 * 
	 * @param spielfigur Spielfigur, die kopiert werden soll
	 */
	public Spielfigur(Spielfigur spielfigur) {
		this.farbe = spielfigur.farbe;
		this.position = new Position(spielfigur.position);
		this.symbol = spielfigur.symbol;
		this.setSymbol(spielfigur.getSymbol());
	}

	/**
	 * Prüfe den gegebenen Zug in Bezug auf das gegebenen Spielbrett darauf, ob der
	 * Zug für die aktuelle Spielfigur ein möglicher Zug ist.
	 * 
	 * @param zug   Der Zug, der geprüft werden soll
	 * @param spiel Das aktuelle Spielbrett
	 * @return boolean True falls der Zug gültig ist, false falls nicht
	 */
	public boolean pruefeZug(Zug zug, Schachspiel spiel) {
		if (getZuege(spiel).contains(zug)) {
			return true;
		}
		return false;
	}

	/**
	 * Abstrakte Methode, die von den Unterklassen implmentiert werden muss, um die
	 * figurspezifischen Zugmöglichkeiten aufzulisten.
	 * 
	 * @param spiel Das Spielbrett, auf dem gespielt wird
	 * @return List<Zug> Liste mit allen möglichen Zugmöglichkeiten für die aktuelle
	 *         Figur
	 */
	public abstract List<Zug> getZuege(Schachspiel spiel);

	/**
	 * Fügt alle Züge der Spielfigur in eine vorgegebene Richtung auf dem Spielbrett
	 * zu einer gegebenen Liste hinzu, solang das Feld frei ist oder ein Gegner
	 * geschlagen werden kann und die Figur nicht vom Spielbrett runterläuft. Wird
	 * innerhalb der Unterklassen Dame, Läufer, Turm genutzt, um mögliche Züge zu
	 * sammeln.
	 * 
	 * @param brett     Spielbrett, auf dem die Spielfigur steht
	 * @param zuege     ArrayList<Zug>, die die möglichen Züge sammelt
	 * @param richtungX int Anzahl der Felder in horizontaler Richtung, um die die
	 *                  Figur nach links (-) oder rechts (+) verschoben wird
	 * @param richtungY int Anzahl der Felder in vertikaler Richtung, um die die
	 *                  Figur nach oben (-) oder unten (+) verschoben wird
	 */
	public void addRunUntilObstacle(Spielbrett brett, ArrayList<Zug> zuege, int richtungX, int richtungY) {
		int offsetX = richtungX;
		int offsetY = richtungY;
		while (addZugAtPosition(brett, zuege, offsetX, offsetY) ) {
			offsetX += richtungX;
			offsetY += richtungY;
		}
	}

	/**
	 * Fügt alle Züge der Spielfigur auf der angegeben Position auf dem Spielbrett
	 * zu einer gegebenen Liste hinzu, solang das Feld frei ist oder ein Gegner
	 * geschlagen werden kann und die Figur nicht vom Spielbrett runterläuft. Kann
	 * für alle Figuren ausser Bauern genutzt werden.
	 * 
	 * @param brett   Spielbrett, auf dem die Spielfigur steht
	 * @param zuege   ArrayList<Zug>, die die möglichen Züge sammelt
	 * @param offsetX int Anzahl der Felder in horizontaler Richtung, um die die
	 *                Figur nach links (-) oder rechts (+) verschoben wird
	 * @param offset  Y int Anzahl der Felder in vertikaler Richtung, um die die
	 *                Figur nach oben (-) oder unten (+) verschoben wird
	 * @return isMove boolean ist True genau dann wenn auf das verschobene Feld
	 *         entweder durch einen Standardzug gezogen werden kann
	 */
	public boolean addZugAtPosition(Spielbrett brett, ArrayList<Zug> zuege, int offsetX, int offsetY) {
		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
		boolean isMove = false; 
		if (pos != null) {
			if (!(brett.isFrei(pos))) {
				if (brett.isGegner(this, pos)) {
					zuege.add(new Zug(getPosition(), pos, brett, Zugart.SCHLAG));
				}
			} else {
				zuege.add(new Zug(getPosition(), pos, brett, Zugart.STANDARD));
				isMove = true;
			}
		}
		return isMove;

	}

	/**
	 * Variante für Züge, bei denen kein Schlag möglich ist, zur Nutzung im Bauern
	 * 
	 * @param brett   Spielbrett, auf dem die Spielfigur steht
	 * @param zuege   ArrayList<Zug>, die die möglichen Züge sammelt
	 * @param offsetX int Anzahl der Felder in horizontaler Richtung, um die die
	 *                Figur nach links (-) oder rechts (+) verschoben wird
	 * @param offset  Y int Anzahl der Felder in vertikaler Richtung, um die die
	 *                Figur nach oben (-) oder unten (+) verschoben wird
	 */
	public void addZug_ohneSchlag(Spielbrett brett, ArrayList<Zug> zuege, int offsetX, int offsetY) {
		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
		if (pos != null) {
			if ((brett.isFrei(pos))) {
				zuege.add(new Zug(getPosition(), pos, brett, Zugart.STANDARD));
			}
		}

	}

	/**
	 * Variante für Züge, bei denen nur Schlag möglich ist, zur Nutzung im Bauern
	 * 
	 * @param brett   Spielbrett, auf dem die Spielfigur steht
	 * @param zuege   ArrayList<Zug>, die die möglichen Züge sammelt
	 * @param offsetX int Anzahl der Felder in horizontaler Richtung, um die die
	 *                Figur nach links (-) oder rechts (+) verschoben wird
	 * @param offset  Y int Anzahl der Felder in vertikaler Richtung, um die die
	 *                Figur nach oben (-) oder unten (+) verschoben wird
	 */
	public void addZug_mitSchlag(Spielbrett brett, ArrayList<Zug> zuege, int offsetX, int offsetY) {
		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
		if (pos != null) {
			if (!(brett.isFrei(pos))) {
				if (brett.isGegner(this, pos)) {
					zuege.add(new Zug(getPosition(), pos, brett, Zugart.SCHLAG));
				}
			}
		}

	}

	/**
	 * Getter
	 * 
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Getter
	 * 
	 * @return int Aktueller Reihenindex
	 */
	public int getX() {
		return getPosition().getRow();
	}

	/**
	 * Getter
	 * 
	 * @return int Aktueller Spaltenindex
	 */
	public int getY() {
		return getPosition().getCol();
	}

	/**
	 * Getter
	 * 
	 * @return Spielfarbe Schwarz oder Weiss
	 */
	public Spielfarbe getFarbe() {
		return this.farbe;
	}

	/**
	 * Getter
	 * 
	 * @return String symbol
	 */
	public String getSymbol() {
		return this.symbol; 
	}

	/**
	 * Setter
	 * 
	 * @param position Position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Setter
	 * 
	 * @param int row Reihenindex
	 * @param int col Spaltenindex
	 */
	public void setPosition(int row, int col) {
		this.position = new Position(row, col);
	}

	/**
	 * Methode gibt true zurück, wenn die gegebene Spielfigur ein Gegner der
	 * aktuellen Spielfigur ist, d.h. ob die Spielfarbe der anderen Spielfarbe als
	 * der eigenen entspricht.
	 * 
	 * @param spielfigur Spielfigur, bei der überprüft werden soll, ob es sich um
	 *                   einen Gegner handelt
	 * @return isGegner boolean
	 */
	public boolean isGegner(Spielfigur spielfigur) {
		assert spielfigur != null;
		boolean isGegner = false;
		if (this.getFarbe() == Spielfarbe.WEISS) {
			isGegner = spielfigur.getFarbe() == Spielfarbe.SCHWARZ;
		}
		if (this.getFarbe() == Spielfarbe.SCHWARZ) {
			isGegner = spielfigur.getFarbe() == Spielfarbe.WEISS;
		}
		return isGegner;
	}

	@Override
	public String toString() {
		return this.getSymbol();
	}

	/**
	 * Wertevergleich
	 *
	 * return true genau dann wenn das übergebene Objekt ein Spielfigur-Objekt mit
	 * den gleichem Symbol und Position ist
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Spielfigur))
			return false;
		if (obj == this)
			return true;
		return ((this.getPosition().equals(((Spielfigur) obj).getPosition())) && (this.getSymbol().equals(((Spielfigur) obj).getSymbol())));
	}

	/**
	 * Setter
	 * 
	 * @param symbol String
	 */
	protected void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Setter
	 * 
	 * @param farbe Spielfarbe (Schwarz oder Weiß)
	 */
	protected void setFarbe(Spielfarbe farbe) {
		this.farbe = farbe;
	}

}

//while (true) {
//Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
//
//// in dem Fall, dass die nächste Position nicht mehr auf dem Spielbrett ist,
//// Abbruch
//if (pos == null) {
//	break;
//}
//
//// in dem Fall, dass die nächste Position besetzt ist, Abbruch, aber prüfe
//// vorher, ob die Figur auf dem besetzten Feld geschlagen werden kann und füge
//// ggf. den Schlag-Zug hinzu
//if (!(brett.isFrei(pos))) {
//	if (brett.isGegner(this, pos)) {
//		zuege.add(new Zug(getPosition(), pos, brett, Zugart.SCHLAG));
//	}
//	break;
//} else {
//	// füge Bewegungszug auf das nächste freie Feld hinzu
//	zuege.add(new Zug(getPosition(), pos, brett, Zugart.STANDARD));
//}
//
//// setzt die Indizes für die nächste zu prüfende Position
//offsetX += richtungX;
//offsetY += richtungY;
//}

//	public void setSymbol(String symbol) {
//		if (true) { //TODO check why symbol check does not work
//			this.symbol = symbol;
//		} else {
//			throw new NumberFormatException("Ungültiges Symbol!");
//		}
//	}
//
//	public String getSymbol() {
//		return this.symbol;
//	}

//	public void setFarbe(String farbe) {
//		if (farbe.equals("weiss") || farbe.equals("weiß") || farbe.equals("schwarz") || farbe.equals("neutral")) {
//			this.farbe = farbe;
//		} else {
//			throw new ArithmeticException("Schach muss schwarz & weiß sein!");
//		}
//	}

//	// Copy Konstruktor
//	public Spielfigur(Spielfigur spielfigur) {
//		this.farbe = spielfigur.farbe;
//		this.symbol = spielfigur.symbol;
//		this.isGeschlagen = spielfigur.isGeschlagen;
//		this.feld1 = spielfigur.feld1;
//		this.feld2 = spielfigur.feld2;
//	}

////Überprüfe Endfeld
//Position ziel = testzug.getZielposition();
//if (testzug.isSchlag == false && !(getZuege(schachstellung).contains(testzug.getZielposition()))) { //TODO Unterscheidung Schlag vs. nichtSchlag
//	gueltigerZug = false;
//	throw new ArithmeticException("Zug passt nicht"); //TODO exceptions
//
//}
//if (testzug.isSchlag == true && !(getZuege(schachstellung).contains(testzug.getZielposition()))) {
//	gueltigerZug = false;
//	throw new ArithmeticException("Zug passt nicht (schlag) "); //TODO exceptions
//
//}
////TODO löschen
//public boolean isGegner(Spielbrett schachstellung, int index1, int index2) {
//	boolean isGegner = false;
//	if (this.farbe == Spielfarbe.WEISS) {
//		isGegner = schachstellung.getFigur(index1, index2).farbe == Spielfarbe.SCHWARZ;
//	}
//	if (this.farbe == Spielfarbe.SCHWARZ) {
//		isGegner= schachstellung.getFigur(index1, index2).farbe == Spielfarbe.WEISS;
//	}
//	return isGegner;
//	}
