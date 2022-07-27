import java.util.ArrayList;

/**
 * Unterklasse von Spielfigur, die die Bewegungslogik der Dame implementiert.
 * 
 * @author lmey
 *
 */
public class Dame extends Spielfigur {

	/**
	 * Konstruktor, der neben dem geerbten Konstruktor nur das zur Farbe passende
	 * Damensymbol ergänzt
	 * 
	 * @param farbe Spielfarbe
	 */
	public Dame(Spielfarbe farbe) {
		super(farbe);
		if (farbe==Spielfarbe.WEISS) {
			this.setSymbol("D");
		}
		if (farbe==Spielfarbe.SCHWARZ) {
			this.setSymbol("d");
		}
	}

	/**
	 * Copy Konstruktor
	 * 
	 * @param dame
	 */
	public Dame(Dame dame) {
		super(dame);
	}

	/**
	 * Erstellt eine Liste mit allen möglichen Zügen dieser Figur unter Einsatz der
	 * geerbten Methode addRunUntilObstacle
	 * 
	 * @return zuege ArrayList<Zug> , die alle möglichen Züge abspeichert
	 */
	public ArrayList<Zug> getZuege(Schachspiel spiel) {
		ArrayList<Zug> zuege = new ArrayList<Zug>();

		// horizontal
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, +1, 0);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, -1, 0);

		// vertikal
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, 0, +1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, 0, -1);

		// diagonal
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, +1, +1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, -1, -1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, +1, -1);
		addRunUntilObstacle(spiel.getSpielbrett(), zuege, -1, +1);

		return zuege;
	}
}

///int[] richtung = new int[] { 1, -1 }; //1-> rechts bzw. oben; -1-> links bzw. unten

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
//
//// diagonal links oben nach rechts unten
//offsetY = 0;
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
