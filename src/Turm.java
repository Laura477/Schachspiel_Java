import java.util.ArrayList;
import java.util.List;

/**
 * Unterklasse von Spielfigur, die die Bewegungslogik des Turms implementiert.
 * 
 * @author lmey
 *
 */
public class Turm extends Spielfigur {

	/**
	 * Konstruktor, der neben dem geerbten Konstruktor nur das zur Farbe passende
	 * Turmsymbol ergänzt
	 * 
	 * @param farbe Spielfarbe
	 */
	public Turm(Spielfarbe farbe) {
		super(farbe);
		if (farbe.equals(Spielfarbe.WEISS)) {
			this.setSymbol("T");
		}
		if (farbe.equals(Spielfarbe.SCHWARZ)) {
			this.setSymbol("t");
		}
	}

	/**
	 * Copy Konstruktor
	 * 
	 * @param laeufer
	 */
	public Turm(Turm turm) {
		super(turm);
	}

	/**
	 * Erstellt eine Liste mit allen möglichen Zügen dieser Figur unter Einsatz der
	 * geerbten Methode addRunUntilObstacle. Der Turm kann nur in vertikale und horizontale Richtung laufen.
	 * 
	 * @return zuege ArrayList<Zug> , die alle möglichen Züge abspeichert
	 */
	public List<Zug> getZuege(Schachspiel spiel) {
		ArrayList<Zug> zuege = new ArrayList<Zug>();
		//horizontal
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, +1, 0);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, -1, 0);
		//vertikal
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, 0, +1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, 0, -1);
		return zuege;
	}
}

//int[] richtung = new int[] { 1, -1 }; //1-> rechts bzw. oben; -1-> links bzw. unten
////int[] offset = new int[] { 0,0 }; //offset[0] -> offsetX (horizontal), offset[1] -> offsetY (vertikal)
//
//// horizontal
//int offsetX = 0;
//int offsetY = 0;
//for (int r : richtung) {
//	offsetX = r;
//	while (true) {
//		Position pos = getPosition().getRelatedPosition(offsetX, 0);
//		if (pos == null) {
//			break;
//		}
//		if (!(brett.isFrei(pos))) {
//			if (brett.isGegner(this, pos)) {
//				zuege.add(new Zug(getPosition(), pos, brett, Zugart.Schlag));
//			}
//			break;
//		}else {
//			zuege.add(new Zug(getPosition(), pos, brett, Zugart.Standard));
//		}
//		offsetX += r;
//	}
//}
//
//// vertikal
//offsetX = 0;
//for (int r : richtung) {
//	offsetY = r;
//	while (true) {
//		Position pos = getPosition().getRelatedPosition(0, offsetY);
//		if (pos == null) {
//			break;
//		}
//		if (!(brett.isFrei(pos))) {
//			if (brett.isGegner(this, pos)) {
//				zuege.add(new Zug(getPosition(), pos, brett, Zugart.Schlag));
//			}
//			break;
//		}else {
//			zuege.add(new Zug(getPosition(), pos, brett, Zugart.Standard));
//		}
//		offsetY += r;
//	}
//}

//// horizontale Bewegung nach rechts
//int i;
//for (i = 1; getY() + i < brett.getBreite(); i++) {
//	if ((brett.isFrei(getX(), getY() + i))) {
//		zuege.add(new Zug(this.getPosition(), new Position(getX(), getY() + i), brett, false));
//	} else {
//		break;
//	}
//}
//if (getY() + i < brett.getBreite() && this.isGegner(brett, getX(), getY() + i)) { // Schlag
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX(), getY() + i), brett, true));
//}
//
//// horizontale Bewegung nach links
//for (i = 1; getY() - i >= 0; i++) {
//	if ((brett.isFrei(getX(), getY() - i))) {
//		zuege.add(new Zug(this, this.getPosition(), new Position(getX(), getY() - i), brett, false));
//	} else {
//		break;
//	}
//}
//if (getY() - i >= 0 && isGegner(brett.getFigur(getX(), getY() - i))) { // Schlag
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX(), getY() - i), brett, true));
//}
//
//// vertikale Bewegung nach unten
//for (i = 1; getX() + i < brett.getHoehe(); i++) {
//	if ((brett.isFrei(getX() + i, getY()))) {
//		zuege.add(new Zug(this, this.getPosition(), new Position(getX() + i, getY()), brett, false));
//	} else {
//		break;
//	}
//}
//if (getX() + i < brett.getHoehe() && this.isGegner(brett, getX() + i, getY())) { // Schlag
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX() + i, getY()), brett, true));
//}
//
//// vertikale Bewegung nach oben
//for (i = 1; getX() - i >= 0; i++) {
//	if ((brett.isFrei(getX() - i, getY()))) {
//		zuege.add(new Zug(this, this.getPosition(), new Position(getX() - i, getY()), brett, false));
//	} else {
//		break;
//	}
//}
//if (getX() - i >= 0 && this.isGegner(brett, getX() - i, getY())) { // Schlag
//	zuege.add(new Zug(this, this.getPosition(), new Position(getX() - i, getY()), brett, true));
//
//}