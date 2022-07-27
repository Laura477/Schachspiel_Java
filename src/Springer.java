import java.util.ArrayList;

/**
 * Unterklasse von Spielfigur, die die Bewegungslogik des Springers (Pferd)
 * implementiert.
 * 
 * @author lmey
 *
 */
public class Springer extends Spielfigur {

	/**
	 * Konstruktor, der neben dem geerbten Konstruktor nur das zur Farbe passende
	 * Springersymbol ergänzt
	 * 
	 * @param farbe Spielfarbe
	 */
	public Springer(Spielfarbe farbe) {
		super(farbe);
		if (farbe==Spielfarbe.WEISS) {
			this.setSymbol("S");
		}
		if (farbe==Spielfarbe.SCHWARZ) {
			this.setSymbol("s");
		}
	}

	/**
	 * Copy Konstruktor
	 * 
	 * @param springer
	 */
	public Springer(Springer springer) {
		super(springer);
	}

	/**
	 * Erstellt eine Liste mit allen möglichen Zügen dieser Figur unter Einsatz der
	 * geerbten Methode addZugAtPosition. Es werden alle Positionen in 2er Abstand
	 * in eine Richtung und 1er Abstand in 90 Grad Richtung dazu getestet.
	 * 
	 * @return zuege ArrayList<Zug> , die alle möglichen Züge abspeichert
	 */
	public ArrayList<Zug> getZuege(Schachspiel spiel) { // TODO
		ArrayList<Zug> zuege = new ArrayList<Zug>();
		addZugAtPosition(spiel.getSpielbrett(), zuege, 1, 2);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -1, 2);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 1, -2);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -1, -2);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 2, 1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 2, -1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -2, 1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -2, -1);
		return zuege;
	}
}

//int[] weg1 = new int[] { 1, -1 }; // 1-> rechts bzw. oben; -1-> links bzw. unten
//int[] weg2 = new int[] { 2, -2 }; // 1-> rechts bzw. oben; -1-> links bzw. unten
//
//// horizontal
//int offsetX = 0;
//int offsetY = 0;
//for (int w1 : weg1) { //1, -1
//	for (int w2 : weg2) { //2, -2
//		offsetX = w1;
//		offsetY = w2;
//		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
//		if (pos == null) {
//			continue;
//		}
//		if (!(brett.isFrei(pos))) {
//			if (brett.isGegner(this, pos)) {
//				zuege.add(new Zug(getPosition(), pos, brett, Zugart.Schlag));
//			}
//		} else {
//			zuege.add(new Zug(getPosition(), pos, brett, Zugart.Standard));
//		}
//	}
//}
//
//for (int w1 : weg1) { //1, -1
//	for (int w2 : weg2) { //2, -2
//		offsetX = w2;
//		offsetY = w1;
//		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
//		if (pos == null) {
//			continue;
//		}
//		if (!(brett.isFrei(pos))) {
//			if (brett.isGegner(this, pos)) {
//				zuege.add(new Zug(getPosition(), pos, brett, Zugart.Schlag));
//			}
//		} else {
//			zuege.add(new Zug(getPosition(), pos, brett, Zugart.Standard));
//		}
//	}
//}

//// Option hoch links
//if (getX() - 2 >= 0 && getY() - 1 >= 0) {
//	Position pos_h_l = new Position(getX() - 2, getY() - 1);
//	if ((schachstellung.isFrei(pos_h_l))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_h_l, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_h_l)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_h_l, schachstellung, true));
//	}
//}
//
//// Option hoch rechts
//if (getX() - 2 >= 0 && getY() + 1 < schachstellung.getBreite()) {
//	Position pos_h_r = new Position(getX() - 2, getY() + 1);
//	if ((schachstellung.isFrei(pos_h_r))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_h_r, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_h_r)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_h_r, schachstellung, true));
//	}
//}
//
//// Option rechts hoch
//if (getX() - 1 >= 0 && getY() + 2 < schachstellung.getBreite()) {
//	Position pos_r_h = new Position(getX() - 1, getY() + 2);
//	if ((schachstellung.isFrei(pos_r_h))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_r_h, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_r_h)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_r_h, schachstellung, true));
//	}
//}
//
//// Option rechts unten
//if (getX() + 1 < schachstellung.getHoehe() && getY() + 2 < schachstellung.getBreite()) {
//	Position pos_r_u = new Position(getX() + 1, getY() + 2);
//	if ((schachstellung.isFrei(pos_r_u))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_r_u, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_r_u)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_r_u, schachstellung, true));
//	}
//}
//
//// Option unten links
//if (getX() + 2 < schachstellung.getHoehe() && getY() - 1 >= 0) {
//	Position pos_u_l = new Position(getX() + 2, getY() - 1);
//	if ((schachstellung.isFrei(pos_u_l))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_u_l, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_u_l)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_u_l, schachstellung, true));
//	}
//}
//
//// Option unten rechts
//if (getX() + 2 < schachstellung.getHoehe() && getY() + 1 < schachstellung.getBreite()) {
//	Position pos_u_r = new Position(getX() + 2, getY() + 1);
//	if ((schachstellung.isFrei(pos_u_r))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_u_r, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_u_r)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_u_r, schachstellung, true));
//	}
//}
//
//// Option links hoch
//if (getX() + 1 < schachstellung.getHoehe() && getY() - 2 >= 0) {
//	Position pos_l_h = new Position(getX() + 1, getY() - 2);
//	if ((schachstellung.isFrei(pos_l_h))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_l_h, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_l_h)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_l_h, schachstellung, true));
//	}
//}
//
//// Option links unten
//if (getX() - 1 >= 0 && getY() - 2 >= 0) {
//	Position pos_l_u = new Position(getX() - 1, getY() - 2);
//	if ((schachstellung.isFrei(pos_l_u))) {
//		zuege.add(new Zug(this, this.getPosition(), pos_l_u, schachstellung, false));
//	} else if (this.isGegner(schachstellung, pos_l_u)) {
//		zuege.add(new Zug(this, this.getPosition(), pos_l_u, schachstellung, true));
//	}
//}
