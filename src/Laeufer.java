import java.util.ArrayList;
import java.util.List;

/**
 * Unterklasse von Spielfigur, die die Bewegungslogik des Läufers implementiert.
 * 
 * @author lmey
 *
 */
public class Laeufer extends Spielfigur {

	/**
	 * Konstruktor, der neben dem geerbten Konstruktor nur das zur Farbe passende
	 * Laeufersymbol ergänzt
	 * 
	 * @param farbe Spielfarbe
	 */
	public Laeufer(Spielfarbe farbe) {
		super(farbe);
		if (farbe.equals(Spielfarbe.WEISS)) {
			this.setSymbol("L");
		}
		if (farbe.equals(Spielfarbe.SCHWARZ)) {
			this.setSymbol("l");
		}
	}

	/**
	 * Copy Konstruktor
	 * 
	 * @param laeufer
	 */
	public Laeufer(Laeufer laeufer) {
		super(laeufer);
	}

	/**
	 * Erstellt eine Liste mit allen möglichen Zügen dieser Figur unter Einsatz der
	 * geerbten Methode addRunUntilObstacle. Der Läufer kann nur diagonal laufen,
	 * d.h. es muss eine Verschiebung in X und Y Richtung gleichzeitig erfolgen
	 * 
	 * @return zuege ArrayList<Zug> , die alle möglichen Züge abspeichert
	 */
	public List<Zug> getZuege(Schachspiel spiel) {

		ArrayList<Zug> zuege = new ArrayList<Zug>();
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, +1, +1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, -1, -1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, +1, -1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, -1, +1);

		return zuege;
	}
}

//
//int[] richtung = new int[] { 1, -1 }; //1-> rechts bzw. oben; -1-> links bzw. unten
//
//// diagonal links oben nach rechts unten
//int offsetX = 0;
//int offsetY = 0;
//for (int r : richtung) {
//	offsetX = r;
//	offsetY = r;
//	while (true) {
//		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
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
//		offsetY += r;
//	}
//}
//
//// diagonal rechts oben nach links unten
//for (int r : richtung) {
//	offsetX = r;
//	offsetY = -r;
//	while (true) {
//		Position pos = getPosition().getRelatedPosition(offsetX, offsetY);
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
//		offsetY += -r;
//	}
//}
//
////
////// horizontal
////int offsetX = 0;
////int offsetY = 0;
////for (int r : richtung) {
////	offsetX = r;
////	while (true) {
////		Position pos = getPosition().getRelatedPosition(offsetX, 0);
////		if (pos == null) {
////			break;
////		}
////		if (!(brett.isFrei(pos))) {
////			if (brett.isGegner(this, pos)) {
////				zuege.add(new Zug(getPosition(), pos, brett, true));
////			}
////			break;
////		}else {
////			zuege.add(new Zug(getPosition(), pos, brett, false));
////		}
////		offsetX += r;
////	}
////}
////
////// vertikal
////offsetX = 0;
////for (int r : richtung) {
////	offsetY = r;
////	while (true) {
////		Position pos = getPosition().getRelatedPosition(0, offsetY);
////		if (pos == null) {
////			break;
////		}
////		if (!(brett.isFrei(pos))) {
////			if (brett.isGegner(this, pos)) {
////				zuege.add(new Zug(getPosition(), pos, brett, true));
////			}
////			break;
////		}else {
////			zuege.add(new Zug(getPosition(), pos, brett, false));
////		}
////		offsetY += r;
////	}
////}
////
////return zuege;
////}
////}
////
//////// Bewegung nach rechts unten
//////int i;
//////for (i = 1; getY() + i < brett.getBreite() && getX() + i < brett.getHoehe(); i++) {
//////	if ((brett.isFrei(getX() + i, getY() + i))) {
//////		zuege.add(new Zug(this, this.getPosition(), new Position(getX()+i, getY()+i), brett, false));
//////	} else {
//////		break;
//////	}
//////}
//////if (getY() + i < brett.getBreite() && getX() + i < brett.getHoehe()
//////		&& this.isGegner(brett, getX() + i, getY() + i)) { // Schlag
//////	zuege.add(new Zug(this, this.getPosition(), new Position(getX()+i, getY()+i), brett, true));
//////
//////}
//////
//////// Bewegung nach links unten
//////for (i = 1; getY() - i >= 0 && getX() + i < brett.getHoehe(); i++) {
//////	if ((brett.isFrei(getX() + i, getY() - i))) {
//////		zuege.add(new Zug(this, this.getPosition(), new Position(getX()+i, getY()-i), brett, false));
//////
//////	} else {
//////		break;
//////	}
//////}
//////if (getY() - i >= 0 && getX() + i < brett.getHoehe()
//////		&& this.isGegner(brett, getX() + i, getY() - i)) { // Schlag
//////	zuege.add(new Zug(this, this.getPosition(), new Position(getX()+i, getY()-i), brett, true));
////
////}
////
////// Bewegung nach rechts oben
////for (i = 1; getX() - i >= 0 && getY() + i < brett.getBreite(); i++) {
////	if ((brett.isFrei(getX() - i, getY() + i))) {
////		zuege.add(new Zug(this, this.getPosition(), new Position(getX()-i, getY()+i), brett, false));
////
////	} else {
////		break;
////	}
////}
////if (getX() - i >= 0 && getY() + i < brett.getBreite()
////		&& this.isGegner(brett, getX() - i, getY() + i)) { // Schlag
////	zuege.add(new Zug(this, this.getPosition(), new Position(getX()-i, getY()+i), brett, true));
////
////}
////
////// Bewegung nach rechts oben
////for (i = 1; getX() - i >= 0 && getY() - i >= 0; i++) {
////	if ((brett.isFrei(getX() - i, getY() - i))) {
////		zuege.add(new Zug(this, this.getPosition(), new Position(getX()-i, getY()-i), brett, false));
////
////	} else {
////		break;
////	}
////}
////if (getX() - i >= 0 && getY() - i >= 0 
////		&& this.isGegner(brett, getX() - i, getY() - i)) { // Schlag
////	zuege.add(new Zug(this, this.getPosition(), new Position(getX()-i, getY()-i), brett, true));
////}
