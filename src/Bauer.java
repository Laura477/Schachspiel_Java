import java.util.ArrayList;

/**
 * Unterklasse von Spielfigur, die die Bewegungslogik des Königs implementiert.
 * Test.....
 * @author lm.ey
 *
 */
public class Bauer extends Spielfigur {

	/**
	 * Konstruktor, der neben dem geerbten Konstruktor nur das zur Farbe passende
	 * Bauersymbol ergänzt
	 * 
	 * @param farbe Spielfarbe
	 */
	public Bauer(Spielfarbe farbe) {
		super(farbe);
		if (farbe==Spielfarbe.WEISS) {
			this.setSymbol("B");
		}
		if (farbe==Spielfarbe.SCHWARZ) {
			this.setSymbol("b");
		}
	}

	/**
	 * Copy Konstruktor
	 * 
	 * @param bauer
	 */
	public Bauer(Bauer bauer) {
		super(bauer);
	}

	/**
	 * Erstellt eine Liste mit allen möglichen Zügen dieser Figur. Dabei wird
	 * zwischen den unterschiedlichen Laufrichtungen der Farbe und Züge auf leere
	 * Felder bzw. Schläge nach links oder rechts unterschieden, sowie für die
	 * Startstellung Zweierschritte zugelassen.
	 * 
	 * @return zuege ArrayList<Zug> , die alle möglichen Züge abspeichert
	 */
	public ArrayList<Zug> getZuege(Schachspiel spiel) {
		ArrayList<Zug> zuege = new ArrayList<Zug>();

		// Schritte ohne Schlag
		if ((getFarbe()==Spielfarbe.SCHWARZ)) {
			addZug_ohneSchlag(spiel.getSpielbrett(), zuege, 1, 0);
			if (getX() == 1) { // Zweierschritte aus Startposition
				addZug_ohneSchlag(spiel.getSpielbrett(), zuege, 2, 0);
			}
		}
		if ((getFarbe()==Spielfarbe.WEISS)) {
			addZug_ohneSchlag(spiel.getSpielbrett(), zuege, -1, 0);
			if (getX() == 6) { // Zweierschritte aus Startposition
				addZug_ohneSchlag(spiel.getSpielbrett(), zuege, -2, 0);
			}
		}

		// Schritte mit Schlag
		if ((getFarbe()==Spielfarbe.SCHWARZ)) {
			addZug_mitSchlag(spiel.getSpielbrett(), zuege, 1, 1);
			addZug_mitSchlag(spiel.getSpielbrett(), zuege, 1, -1);

		}
		if ((getFarbe()==Spielfarbe.WEISS)) {
			addZug_mitSchlag(spiel.getSpielbrett(), zuege, -1, 1);
			addZug_mitSchlag(spiel.getSpielbrett(), zuege, -1, -1);
		}

		// *******EN PASSANT**********
		// en passant kann nur passieren, wenn ein Bauer zwei Felder vor der
		// Startstellung der gegnerischen Bauern steht und schon ein Zug passiert ist
		if (((getFarbe()==Spielfarbe.WEISS && getPosition().getRow() == 3)
				|| (getFarbe()==Spielfarbe.SCHWARZ && getPosition().getRow() == 4))
				&& spiel.getGespielteZuege().size() > 1) {

			// get letzten Zug des Spiels
			Zug vorzug = spiel.getLastZug();

			// prüfe, ob letzter Zug ein Zweierschritt des Gegners war
			if (vorzug.isZweischritt() && (isGegner(vorzug.getSpielfigur()))) {

				// berücksichtigt unterschiedliche Gehrichtungen
				int offsetX;
				if (getFarbe()==Spielfarbe.WEISS) {
					offsetX = -1;
				} else {
					offsetX = 1;
				}

				// prüfe, ob Gegner auf Nachbarfeld rechts gelandet ist, und füge
				// Schlagmöglichkeit hinzu
				if (getPosition().getRelatedPosition(0, 1) != null
						&& vorzug.getSpielfigur().getPosition().equals(getPosition().getRelatedPosition(0, 1))) {
					zuege.add(new Zug(getPosition(), getPosition().getRelatedPosition(offsetX, 1), spiel.getSpielbrett(),
							Zugart.ENPASSANT));
				}
				// prüfe, ob Gegner auf Nachbarfeld links gelandet ist, und füge
				// Schlagmöglichkeit hinzu
				if (getPosition().getRelatedPosition(0, -1) != null
						&& vorzug.getSpielfigur().getPosition().equals(getPosition().getRelatedPosition(0, -1))) {
					zuege.add(new Zug(getPosition(), getPosition().getRelatedPosition(offsetX, -1), spiel.getSpielbrett(),
							Zugart.ENPASSANT));
				}

			}
		}

		return zuege;

	}

}

//
//// Einerschritte schwarz
//if ((farbe.equals(Spielfarbe.SCHWARZ)) && (getX() != 7)) {
//	if (brett.isFrei(getX() + 1, getY())) { // kein Schlag
//		zuege.add(new Zug(this.position, new Position(getX() + 1, getY()), brett, Zugart.STANDARD));
//	}
//	if (getY() != 7 && brett.isGegner(this, new Position(getX() + 1, getY() + 1))) {// Schlag rechts
//		zuege.add(new Zug(this.position, new Position(getX() + 1, getY() + 1), brett, Zugart.SCHLAG));
//
//	}
//	// System.out.println(getX() + "y"+getY());
//	if (getY() != 0 && brett.isGegner(this, new Position(getX() + 1, getY() - 1))) {// Schlag links
//		zuege.add(new Zug(this.position, new Position(getX() + 1, getY() - 1), brett, Zugart.SCHLAG));
//	}
//}
//
//// Einerschritte weiss
//if ((farbe.equals(Spielfarbe.WEISS)) && (getX() != 0)) {
//	if (brett.isFrei(getX() - 1, getY())) { // kein Schlag
//		zuege.add(new Zug(this.position, new Position(getX() - 1, getY()), brett, Zugart.STANDARD));
//	}
//	if (getY() != 7 && brett.isGegner(this, new Position(getX() - 1, getY() + 1))) {// Schlag rechts
//		zuege.add(new Zug(this.position, new Position(getX() - 1, getY() + 1), brett, Zugart.SCHLAG));
//
//	}
//	if (getY() != 0 && brett.isGegner(this, new Position(getX() - 1, getY() - 1))) {// Schlag links
//		zuege.add(new Zug(this.position, new Position(getX() - 1, getY() - 1), brett, Zugart.SCHLAG));
//	}
//}

//// Einerschritte (ohne Schlagen)
//if ((farbe.equals(Spielfarbe.SCHWARZ)) && (getX() != 7) && schachstellung.isFrei(getX() + 1, getY())) {
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX() + 1, getY()), schachstellung, false));
//}
//if ((farbe.equals(Spielfarbe.WEISS)) && (getX() != 0) && schachstellung.isFrei(getX() - 1, getY())) {
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX() - 1, getY()), schachstellung, false));
//}
//
//// Zweierschritte
//if ((farbe.equals(Spielfarbe.SCHWARZ)) && (getX() == 1) && schachstellung.isFrei(getX() + 2, getY())) {
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX() + 2, getY()), schachstellung, false));
//}
//if ((farbe.equals(Spielfarbe.WEISS)) && (getX() == 6) && schachstellung.isFrei(getX() - 2, getY())) {
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX() - 2, getY()), schachstellung, false));
//}
//
//// TODO verhindern, dass Bauer an Schlagmöglichkeit vorbeigeht
//// Schritte mit Schlagen
//if (farbe.equals(Spielfarbe.SCHWARZ) && (getX() != 7)) {
//	if ((getY() != 7) && !(schachstellung.isFrei(getX() + 1, getY() + 1))
//			&& schachstellung.isWeiss(getX() + 1, getY() + 1)) { // TODO indexoutofbounds abfangen
//		zuege.add(
//				new Zug(this, this.getPosition(), new Position(getX() + 1, getY() + 1), schachstellung, true));
//	}
//	if ((getY() != 0) && !(schachstellung.isFrei(getX() + 1, getY() - 1))
//			&& schachstellung.isWeiss(getX() + 1, getY() - 1)) {
//		zuege.add(
//				new Zug(this, this.getPosition(), new Position(getX() + 1, getY() - 1), schachstellung, true));
//	}
//}
//if (farbe.equals(Spielfarbe.WEISS) && (getX() != 0)) {
//	if ((getY() != 7) && !(schachstellung.isFrei(getX() - 1, getY() + 1))
//			&& schachstellung.isSchwarz(getX() - 1, getY() + 1)) {
//		zuege.add(
//				new Zug(this, this.getPosition(), new Position(getX() - 1, getY() + 1), schachstellung, true));
//		if ((getY() != 0) && !(schachstellung.isFrei(getX() - 1, getY() - 1))
//				&& schachstellung.isSchwarz(getX() - 1, getY() - 1)) {
//			zuege.add(new Zug(this, this.getPosition(), new Position(getX() - 1, getY() - 1), schachstellung,
//					true));
//		}
//	}
//
//}
//public boolean checkSymbol() {
//return (symbol.equals("B") || symbol.equals("XXX") || symbol.equals("b") || symbol.equals("_")); // TODO
//																									// Character
//																									// encoding
//}
//
//public boolean checkSymbol(String test) {
//return (test.equals("B") || test.equals("XXX") || test.equals("b") || test.equals("_")); // TODO
//																									// Character
//																									// encoding
//}

//public List<Position> getZielpositionen(Spielbrett schachstellung) {
//List<Position> zielpositionen = new ArrayList<Position>();
//
//// Einerschritte (ohne Schlagen)
//if ((farbe.equals(farben.SCHWARZ)) && (getX() != 7) && schachstellung.isFrei(getX() + 1, getY())) {
//	zielpositionen.add(new Position(getX() + 1, getY()));
//}
//if ((farbe.equals(farben.WEISS)) && (getX() != 0) && schachstellung.isFrei(getX() - 1, getY())) {
//	zielpositionen.add(new Position(getX() - 1, getY()));
//}
//
//// Zweierschritte
//if ((farbe.equals(farben.SCHWARZ)) && (getX() == 1) && schachstellung.isFrei(getX() + 2, getY())) {
//	zielpositionen.add(new Position(getX() + 2, getY()));
//}
//if ((farbe.equals(farben.WEISS)) && (getX() == 6) && schachstellung.isFrei(getX() - 2, getY())) {
//	zielpositionen.add(new Position(getX() - 2, getY()));
//}
//
//// TODO verhindern, dass Bauer an Schlagmöglichkeit vorbeigeht
//// Schritte mit Schlagen
//if (farbe.equals(farben.SCHWARZ) && (getX() != 7)) {
//	if ((getY() != 7) && !(schachstellung.isFrei(getX() + 1, getY() + 1))
//			&& schachstellung.isWeiss(getX() + 1, getY() + 1)) { // TODO indexoutofbounds abfangen
//		zielpositionen.add(new Position(getX() + 1, getY() + 1));
//	}
//	if ((getY() != 0) && !(schachstellung.isFrei(getX() + 1, getY() - 1))
//			&& schachstellung.isWeiss(getX() + 1, getY() - 1)) {
//		zielpositionen.add(new Position(getX() + 1, getY() - 1));
//	}
//}
//if (farbe.equals(farben.WEISS) && (getX() != 0)) {
//	if ((getY() != 7) && !(schachstellung.isFrei(getX() - 1, getY() + 1))
//			&& schachstellung.isSchwarz(getX() - 1, getY() + 1)) {
//		zielpositionen.add(new Position(getX() - 1, getY() + 1));
//	}
//	if ((getY() != 0) && !(schachstellung.isFrei(getX() - 1, getY() - 1))
//			&& schachstellung.isSchwarz(getX() - 1, getY() - 1)) {
//		zielpositionen.add(new Position(getX() - 1, getY() - 1));
//	}
//}
//return zielpositionen;
//
//}
