import java.util.ArrayList;

/**
 * Unterklasse von Spielfigur, die die Bewegungslogik des Königs implementiert.
 * 
 * @author lmey
 *
 */
public class Koenig extends Spielfigur {

	/**
	 * Konstruktor, der neben dem geerbten Konstruktor nur das zur Farbe passende
	 * Königsymbol ergänzt
	 * 
	 * @param farbe Spielfarbe
	 */
	public Koenig(Spielfarbe farbe) {
		super(farbe);
		if (farbe==Spielfarbe.WEISS) {
			this.setSymbol("K");
		}
		if (farbe==Spielfarbe.SCHWARZ) {
			this.setSymbol("k");
		}
	}

	/**
	 * Copy Konstruktor
	 * 
	 * @param könig
	 */
	public Koenig(Koenig koenig) {
		super(koenig);
	}

	/**
	 * Erstellt eine Liste mit allen möglichen Zügen dieser Figur unter Einsatz der
	 * geerbten Methode addZugAtPosition. Es werden alle Positionen im Einser-Abstand um den König getestet.
	 * 
	 * @return zuege ArrayList<Zug> , die alle möglichen Züge abspeichert
	 */
	public ArrayList<Zug> getZuege(Schachspiel spiel) {
		ArrayList<Zug> zuege = new ArrayList<Zug>();
		addZugAtPosition(spiel.getSpielbrett(), zuege, 1,0);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -1,0);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 1,1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -1,1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, -1,-1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 0,-1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 0,1);
		addZugAtPosition(spiel.getSpielbrett(), zuege, 1,-1);

		return zuege;
	}
}

//int[] richtung = new int[] { 1, -1 }; //1-> rechts bzw. oben; -1-> links bzw. unten
//
//// horizontal
//int offsetX = 0;
//int offsetY = 0;
//for (int r : richtung) {
//	offsetX = r;
//	while (Math.abs(offsetX)  <=1 && Math.abs(offsetY) <=1) { //beschränke gehen auf 1 Stelle
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
//	while (Math.abs(offsetX)  <=1 && Math.abs(offsetY) <=1) { //beschränke gehen auf 1 Stelle
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
//	while (Math.abs(offsetX)  <=1 && Math.abs(offsetY) <=1) { //beschränke gehen auf 1 Stelle
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
//	while (Math.abs(offsetX)  <=1 && Math.abs(offsetY) <=1) { //beschränke gehen auf 1 Stelle
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


////oben
//if (getX()-1>=0) {
//	Position oben = new Position (getX()-1, getY());
//	if(schachstellung.isFrei(oben)) {
//		zuege.add(new Zug(this, this.getPosition(), oben, schachstellung, false));
//	}
//	else if(this.isGegner(schachstellung, oben)) {
//		zuege.add(new Zug(this, this.getPosition(), oben, schachstellung, true));
//	}
//	
//	//obenlinks
//	if (getY()-1>=0) {
//		Position o_l = new Position (getX()-1, getY()-1);
//		if(schachstellung.isFrei(o_l)) {
//			zuege.add(new Zug(this, this.getPosition(), o_l, schachstellung, false));
//		}
//		else if(this.isGegner(schachstellung, o_l)) {
//			zuege.add(new Zug(this, this.getPosition(), o_l, schachstellung, true));
//		}
//	}
//	//oben rechts
//	if(getY()+1<schachstellung.getBreite()) {
//		Position o_r = new Position (getX()-1, getY()+1);
//		if(schachstellung.isFrei(o_r)) {
//			zuege.add(new Zug(this, this.getPosition(), o_r, schachstellung, false));
//		}
//		else if(this.isGegner(schachstellung, o_r)) {
//			zuege.add(new Zug(this, this.getPosition(), o_r, schachstellung, true));
//		}
//	}
//}
//
////unten
//if (getX()+1<schachstellung.getHoehe()) {
//	Position unten = new Position (getX()+1, getY());
//	if(schachstellung.isFrei(unten)) {
//		zuege.add(new Zug(this, this.getPosition(), unten, schachstellung, false));
//	}
//	else if(this.isGegner(schachstellung, unten)) {
//		zuege.add(new Zug(this, this.getPosition(), unten, schachstellung, true));
//	}
//	//unten links
//	if (getY()-1>=0) {
//		Position u_l = new Position (getX()+1, getY()-1);
//		if(schachstellung.isFrei(u_l)) {
//			zuege.add(new Zug(this, this.getPosition(), u_l, schachstellung, false));
//		}
//		else if(this.isGegner(schachstellung, u_l)) {
//			zuege.add(new Zug(this, this.getPosition(), u_l, schachstellung, true));
//		}
//	}
//	//unten rechts
//	if(getY()+1<schachstellung.getBreite()) {
//		Position u_r = new Position (getX()+1, getY()+1);
//		if(schachstellung.isFrei(u_r)) {
//			zuege.add(new Zug(this, this.getPosition(), u_r, schachstellung, false));
//		}
//		else if(this.isGegner(schachstellung, u_r)) {
//			zuege.add(new Zug(this, this.getPosition(), u_r, schachstellung, true));
//		}
//	}
//}
//
////rechts
//if(getY()+1<schachstellung.getBreite()) {
//	Position rechts = new Position (getX(), getY()+1);
//	if(schachstellung.isFrei(rechts)) {
//		zuege.add(new Zug(this, this.getPosition(), rechts, schachstellung, false));
//	}
//	else if(this.isGegner(schachstellung, rechts)) {
//		zuege.add(new Zug(this, this.getPosition(), rechts, schachstellung, true));
//	}
//}
//
////links
//if(getY()-1>=0) {
//	Position links = new Position (getX(), getY()-1);
//	if(schachstellung.isFrei(links)) {
//		zuege.add(new Zug(this, this.getPosition(), links, schachstellung, false));
//	}
//	else if(this.isGegner(schachstellung, links)) {
//		zuege.add(new Zug(this, this.getPosition(), links, schachstellung, true));
//	}
//}
		