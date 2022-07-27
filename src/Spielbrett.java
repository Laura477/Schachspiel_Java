
/**
 * Spielbrett Klasse erzeugt einen zweidimensionalen Array von Spielfiguren von
 * gegebener Gr��e. Kann graphisch ausgegeben werden und Figuren setzen und
 * bewegen
 * 
 * @version 1.2, 29.10.2021
 * @author lmey
 */
public class Spielbrett {
	private static final int BREITE = 8;
	private static final int HOEHE = 8;
	private Spielfigur[][] schachbrett;

	/**
	 * Konstruktor erzeugt ein leeres Brett der Gr��e Breite x H�he.
	 * 
	 * @param breite int Breite des Spielbretts
	 * @param hoehe  int H�he des Spielbretts
	 */
	public Spielbrett() {
		this.schachbrett = new Spielfigur[BREITE][HOEHE];
	}

//	/**
//	 * Copy Konstruktor
//	 * 
//	 * @param s Spielbrett, das kopiert werden soll
//	 */
//	public Spielbrett(Spielbrett s) {
//		this.schachbrett = s.schachbrett.clone();
//	}

	/**
	 * Gibt das Schachbrett zur�ck, damit es zum Beispiel von der Klasse Schachspiel
	 * genutzt werden kann
	 * 
	 * @return schachbrett Spielfigur[][]
	 */
	public Spielfigur[][] getBrett() {
		assert this.schachbrett != null;
		return this.schachbrett;
	}

	/**
	 * Setzt Spielfigur auf die gegebene Position
	 * 
	 * @param spielfigur Spielfigur, die gesetzt werden soll
	 * @param zielpos    Position, auf die die Spielfigur gesetzt werden soll
	 */
	public void setFigur(Spielfigur spielfigur, Position zielpos) {
		this.schachbrett[zielpos.getRow()][zielpos.getCol()] = spielfigur;
		spielfigur.setPosition(zielpos);
	}

	/**
	 * Setzt Spielfigur auf das Feld mit dem gegebenen Feldnamen
	 * 
	 * @param spielfigur Spielfigur, die gesetzt werden soll
	 * @param feldname   String Feldname der Position, auf die die Spielfigur
	 *                   gesetzt werden soll
	 */
	public void setFigur(Spielfigur spielfigur, String feldname) {
		Position pos = new Position(feldname);
		setFigur(spielfigur, pos);
	}

	/**
	 * Getter f�r die Spielfigur
	 * 
	 * @param row Reihe des Spielbretts
	 * @param col Spalte des Spielbretts
	 * @return Spielfigur
	 */
	public Spielfigur getFigur(int row, int col) {
		return schachbrett[row][col];
	}

	/**
	 * Getter f�r die Spielfigur
	 * 
	 * @param pos Position
	 * @return Spielfigur
	 */
	public Spielfigur getFigur(Position position) {
		return schachbrett[position.getRow()][position.getCol()];
	}

	/**
	 * Getter f�r die Spielfigur
	 * 
	 * @param feldname String Feldname der abgefragten Position
	 * @return Spielfigur
	 */
	public Spielfigur getFigur(String feldname) {
		Position pos = new Position(feldname);
		return schachbrett[pos.getRow()][pos.getCol()];
	}

	/**
	 * Pr�ft, ob das Brett an der gegebenen Position frei oder besetzt ist
	 * 
	 * @param position Position, die untersucht werden soll
	 * @return boolean (true, wenn das Feld frei ist, false wenn es besetzt ist)
	 */
	public boolean isFrei(Position position) {
		return schachbrett[position.getRow()][position.getCol()] == null;
	}

	/**
	 * Pr�ft, ob das Brett auf dem Feld in der Spalte und Reihe frei oder besetzt
	 * ist
	 * 
	 * @param row Reihenindex des Bretts
	 * @param col Spaltenindex des Bretts
	 * @return boolean (true, wenn das Feld frei ist, false wenn es besetzt ist)
	 */
	public boolean isFrei(int row, int col) { // TODO catch indexoutofbound exceptions
		return schachbrett[row][col] == null;
	}

	/**
	 * Pr�ft, ob f�r eine Figur auf einer Position die Figur auf einer anderen
	 * Position einen Gegner darstellt
	 * 
	 * @param spielfigur    Spielfigur, die am Zug ist
	 * @param gegenposition Position der Spielfigur, auf die die Spielfigur gezogen
	 *                      werden soll
	 * @return boolean isGegner True, wenn auf der zweiten Position ein Gegner steht
	 */
	public boolean isGegner(Spielfigur spielfigur, Position gegenposition) {
		assert spielfigur != null;
		assert gegenposition != null;
		if (isFrei(gegenposition)) {
			return false;
		}
		boolean isGegner = false;
		if (spielfigur.getFarbe() == Spielfarbe.WEISS) {
			isGegner = (getFigur(gegenposition).getFarbe() == Spielfarbe.SCHWARZ);
		}
		if (spielfigur.getFarbe() == Spielfarbe.SCHWARZ) {
			isGegner = (getFigur(gegenposition).getFarbe() == Spielfarbe.WEISS);
		}
		return isGegner;
	}

	/**
	 * Getter f�r die Breite des Spielbretts
	 * 
	 * @return breite
	 */
	public int getBreite() {
		return BREITE;
	}

	/**
	 * Getter f�r die H�he des Spielbretts
	 * 
	 * @return hoehe
	 */
	public int getHoehe() {
		return HOEHE;
	}

	/**
	 * Grafische Ausgabe des Spielbretts
	 */
	@Override
	public String toString() {
		StringBuilder oberrand = new StringBuilder("    ");
		for (int i = 0; i < 8; i++) {
			oberrand.append("+---");
		}
		oberrand.append("+\n");

		StringBuilder abc = new StringBuilder("    ");
		for (int i = 0; i < 8; i++) {
			abc.append("  " + (char) (i + 97) + " ");
		}
		
		StringBuilder output = new StringBuilder();
		output.append(abc);
		output.append("\n");
		for (int i = 0; i < 8; i++) {
			output.append(oberrand);
			output.append(  "  " + (9 - (i + 1)) + " ");
			for (int j = 0; j < 8; j++) {
				if (schachbrett[i][j] == null) {
					output.append( "| " + " " + " ");
				} else {
					output.append("| " + schachbrett[i][j].getSymbol() + " ");
				}
			}
			output.append( "| " + (9 - (i + 1)) + "\n");
		}
		output.append( oberrand);
		output.append(abc);
		output.append("\n");

		return output.toString();
	}

}

//
///**
// * Setzt Spielfigur auf die gegebene Position, l�scht die Spielfigur an ihrer
// * urspr�nglichen Position, und falls schon eine Figur auf der Zielposition
// * steht, wird diese als geschlagen markiert
// * 
// * @param spielfigur Spielfigur, die gesetzt werden soll
// * @param pos        Position, auf die die Spielfigur gesetzt werden soll
// * @return geschlagene Spielfigur (bei einfachem Schlag)
// */
//public void moveFigur(Spielfigur spielfigur, Position zielpos) {
//	this.schachbrett[spielfigur.getPosition().getRow()][spielfigur.getPosition().getCol()] = null;
//	if (getFigur(zielpos) != null) {
//		getFigur(zielpos).setIsGeschlagen(true);
//	}
//	setFigur(spielfigur, zielpos);
//}
//
///**
// * F�hrt den gegebenen Zug durch nach zus�tzlicher Pr�fung, ob Figur auf
// * Startfeld steht und im Schlagfall das Zielfeld besetzt ist bzw. im
// * Nicht-Schlag-Fall das Zielfeld frei ist
// * 
// * @param zug Zug, der ausgef�hrt werden soll
// * @return geschlagene Spielfigur 
// */
//public void moveFigur(Zug zug) {
//	assert zug != null;
//	if (zug.getZugart() == Zugart.SCHLAG && getFigur(zug.getZielposition()) == null) {
//		throw new NumberFormatException("Keine Figur zum Schlagen vorhanden!");
//	}
//	if (zug.getZugart() == Zugart.STANDARD && getFigur(zug.getZielposition()) != null) {
//		throw new NumberFormatException("Das Zielfeld ist nicht frei!");
//	}
//	if (getFigur(zug.getStartposition()) == null) {
//		throw new NumberFormatException("keine passende Figur auf dem Startfeld");
//	}
//	moveFigur(getFigur(zug.getStartposition()), zug.getZielposition());
//	
//	//Zusatz f�r en passant
//	if (zug.getZugart()==Zugart.ENPASSANT) {
//		this.schachbrett[zug.getGeschlagen().getPosition().getRow()][zug.getGeschlagen().getPosition().getCol()] = null;
//		zug.getGeschlagen().setIsGeschlagen(true);
//		
//	}
//}

//public Spielfigur[][] getSchachbrett() {
//	return schachbrett;
//}

///**
// * Macht den gegebenen Zug r�ckg�ngig, indem die bewegte Spielfigur
// * zur�ckgesetzt wird und falls ein Schlag ausgef�hrt wurde, die geschlagene
// * Figur zur�ck auf das Brett gesetzt wird
// * 
// * @param zug Zug, der r�ckg�ngig gemacht werden soll
// */
//public void rueckmoveFigur(Zug zug) {
//	moveFigur(getFigur(zug.getZielposition()), zug.getStartposition());
//	if (zug.getZugart() == Zugart.SCHLAG) {
//		setFigur(zug.getGeschlagen(), zug.getZielposition());
//		zug.getGeschlagen().setIsGeschlagen(false);
//	}
//	if (zug.getZugart() == Zugart.ENPASSANT) {
//		if (zug.getSpielfigur().getFarbe().equals(Spielfarbe.WEISS)) {
//			setFigur(zug.getGeschlagen(), zug.getZielposition().getRelatedPosition(1, 0));
//			zug.getGeschlagen().setIsGeschlagen(false);
//		}
//		if (zug.getSpielfigur().getFarbe().equals(Spielfarbe.SCHWARZ)) {
//			setFigur(zug.getGeschlagen(), zug.getZielposition().getRelatedPosition(-1, 0));
//			zug.getGeschlagen().setIsGeschlagen(false);
//		}
//	}
//}

//String oberrand = "    ";
//for (int i = 0; i < 8; i++) {
//	oberrand += "+---";
//}
//oberrand += "+\n";
//
//String abc = "    ";
//for (int i = 0; i < 8; i++) {
//	abc += "  " + (char) (i + 97) + " ";
//}
//
//String output = abc;
//output += "\n";
//for (int i = 0; i < 8; i++) {
//	output += oberrand;
//	output += "  " + (9 - (i + 1)) + " ";
//	for (int j = 0; j < 8; j++) {
//		if (schachbrett[i][j] == null) {
//			output += "| " + " " + " ";
//		} else {
//			output += "| " + schachbrett[i][j].symbol + " ";
//		}
//	}
//	output += "| " + (9 - (i + 1)) + "\n";
//}
//output += oberrand;
//output += abc + "\n";

//// TODO l�schen
//public boolean isSchwarz(int row, int col) {
//	return getFigur(row, col).farbe == Spielfarbe.SCHWARZ;
//}
//
//public boolean isWeiss(int row, int col) {
//	return getFigur(row, col).farbe == Spielfarbe.WEISS;
//}
////Konstruktor mit gegebener F�llung
//public Spielbrett(Spielfigur[][] stellung) {
//	this.breite = stellung.length;
//	this.hoehe = stellung[0].length;
//	this.brett = new Spielfigur[breite][hoehe];
//	for (int i = 0; i < breite; i++) {
//		for (int j = 0; j < hoehe; j++) {
//			this.brett[i][j] = stellung[i][j];
//		}
//	}
//}

////setze Gr��e des Bretts
//public void setBreiteHoehe(int breite, int hoehe) {
//	this.breite = breite;
//	this.hoehe = hoehe;
//}

//
//
