import java.util.ArrayList;
import java.util.List;

/**
 * Spielfigur Klasse erzeugt eine Spielfigur mit farbe, position und symbol und
 * Marker daf�r, ob sie geschlagen wurde oder nicht. Diese Klasse wird an
 * verschiedene Spielfiguren vererbt, die ihre eigene Methode zum �berpr�fen von
 * Z�gen implmentieren. Die Klasse stellt Getter, Setter und Methoden zur
 * �berpr�fung von m�glichen Z�gen und zur �berpr�fung, ob ein Gegner auf einem
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
	 * Pr�fe den gegebenen Zug in Bezug auf das gegebenen Spielbrett darauf, ob der
	 * Zug f�r die aktuelle Spielfigur ein m�glicher Zug ist.
	 * 
	 * @param zug   Der Zug, der gepr�ft werden soll
	 * @param spiel Das aktuelle Spielbrett
	 * @return boolean True falls der Zug g�ltig ist, false falls nicht
	 */
	public boolean pruefeZug(Zug zug, Schachspiel spiel) {
		if (getZuege(spiel).contains(zug)) {
			return true;
		}
		return false;
	}

	/**
	 * Abstrakte Methode, die von den Unterklassen implmentiert werden muss, um die
	 * figurspezifischen Zugm�glichkeiten aufzulisten.
	 * 
	 * @param spiel Das Spielbrett, auf dem gespielt wird
	 * @return List<Zug> Liste mit allen m�glichen Zugm�glichkeiten f�r die aktuelle
	 *         Figur
	 */
	public abstract List<Zug> getZuege(Schachspiel spiel);

	/**
	 * F�gt alle Z�ge der Spielfigur in eine vorgegebene Richtung auf dem Spielbrett
	 * zu einer gegebenen Liste hinzu, solang das Feld frei ist oder ein Gegner
	 * geschlagen werden kann und die Figur nicht vom Spielbrett runterl�uft. Wird
	 * innerhalb der Unterklassen Dame, L�ufer, Turm genutzt, um m�gliche Z�ge zu
	 * sammeln.
	 * 
	 * @param brett     Spielbrett, auf dem die Spielfigur steht
	 * @param zuege     ArrayList<Zug>, die die m�glichen Z�ge sammelt
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
	 * F�gt alle Z�ge der Spielfigur auf der angegeben Position auf dem Spielbrett
	 * zu einer gegebenen Liste hinzu, solang das Feld frei ist oder ein Gegner
	 * geschlagen werden kann und die Figur nicht vom Spielbrett runterl�uft. Kann
	 * f�r alle Figuren ausser Bauern genutzt werden.
	 * 
	 * @param brett   Spielbrett, auf dem die Spielfigur steht
	 * @param zuege   ArrayList<Zug>, die die m�glichen Z�ge sammelt
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
	 * Variante f�r Z�ge, bei denen kein Schlag m�glich ist, zur Nutzung im Bauern
	 * 
	 * @param brett   Spielbrett, auf dem die Spielfigur steht
	 * @param zuege   ArrayList<Zug>, die die m�glichen Z�ge sammelt
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
	 * Variante f�r Z�ge, bei denen nur Schlag m�glich ist, zur Nutzung im Bauern
	 * 
	 * @param brett   Spielbrett, auf dem die Spielfigur steht
	 * @param zuege   ArrayList<Zug>, die die m�glichen Z�ge sammelt
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
	 * Methode gibt true zur�ck, wenn die gegebene Spielfigur ein Gegner der
	 * aktuellen Spielfigur ist, d.h. ob die Spielfarbe der anderen Spielfarbe als
	 * der eigenen entspricht.
	 * 
	 * @param spielfigur Spielfigur, bei der �berpr�ft werden soll, ob es sich um
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
	 * return true genau dann wenn das �bergebene Objekt ein Spielfigur-Objekt mit
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
	 * @param farbe Spielfarbe (Schwarz oder Wei�)
	 */
	protected void setFarbe(Spielfarbe farbe) {
		this.farbe = farbe;
	}

}

//while (true) {
//Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
//
//// in dem Fall, dass die n�chste Position nicht mehr auf dem Spielbrett ist,
//// Abbruch
//if (pos == null) {
//	break;
//}
//
//// in dem Fall, dass die n�chste Position besetzt ist, Abbruch, aber pr�fe
//// vorher, ob die Figur auf dem besetzten Feld geschlagen werden kann und f�ge
//// ggf. den Schlag-Zug hinzu
//if (!(brett.isFrei(pos))) {
//	if (brett.isGegner(this, pos)) {
//		zuege.add(new Zug(getPosition(), pos, brett, Zugart.SCHLAG));
//	}
//	break;
//} else {
//	// f�ge Bewegungszug auf das n�chste freie Feld hinzu
//	zuege.add(new Zug(getPosition(), pos, brett, Zugart.STANDARD));
//}
//
//// setzt die Indizes f�r die n�chste zu pr�fende Position
//offsetX += richtungX;
//offsetY += richtungY;
//}

//	public void setSymbol(String symbol) {
//		if (true) { //TODO check why symbol check does not work
//			this.symbol = symbol;
//		} else {
//			throw new NumberFormatException("Ung�ltiges Symbol!");
//		}
//	}
//
//	public String getSymbol() {
//		return this.symbol;
//	}

//	public void setFarbe(String farbe) {
//		if (farbe.equals("weiss") || farbe.equals("wei�") || farbe.equals("schwarz") || farbe.equals("neutral")) {
//			this.farbe = farbe;
//		} else {
//			throw new ArithmeticException("Schach muss schwarz & wei� sein!");
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

////�berpr�fe Endfeld
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
////TODO l�schen
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
