/**
 * Die Klasse Position erzeugt ein Positionsobjekt mit Reihenindex row und
 * Spaltenindex col. Die Position kann auch als Name wie im Schach üblich von
 * "a1" bis "h8" benannt werden und die Klasse rechnet zwischen Namen und Index
 * um. Außerdem wird der Vergleichsoperator überschrieben um Positionen mit
 * gleichen Indizes als gleich vergleichen zu können.
 * 
 * @version 1.1, 19.10.2021
 * @author lmey
 */
public class Position {
	private int row;
	private int col;

	/**
	 * Konstruktor, setzt Reihen- und Spaltenindex
	 * 
	 * @param row Reihenindex
	 * @param col Spaltenindex
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Copy Konstruktor - erstellt neue Position mit gleichem Reihen- und Spaltenindex
	 * 
	 * @param position Position, die kopiert werden soll
	 */
	public Position(Position position) {
		this.row = position.row;
		this.col = position.col;
	}

	/**
	 * Konstruktor aus dem Feldnamen - erzeugt neue Position aus der Bezeichnung eines Felds in der Schachkonvention ("Feldname")
	 * 
	 * @param feldname String Bezeichnung eines Felds in der Schachkonvention (z.B. "a1", "h8")
	 */
	public Position(String feldname) {
		this.row = 8 - ((int) feldname.charAt(1) - 48);
		this.col = (int) feldname.charAt(0) - 97;
	}

	/**
	 *toString-Methode, gibt den Feldnamen der Position zurück
	 *
	 *@return feldname String
	 */
	@Override
	public String toString() {
		return getFeldname();
	}

	/**
	 * Getter-Methode für den Reihenindex
	 * 
	 * @return row int Reihenindex
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Getter-Methode für den Spaltenindex
	 * 
	 * @return row int Spaltenindex
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Hilfsmethode, die den Feldnamen rausgibt (durch Umwandeln der Indizes)
	 * 
	 * @return String feldname
	 */
	public String getFeldname() {
		return "" + (char) (this.col + 97) + (char) (8 - this.row + 48);
	}
	
	/**
	 * Gibt eine neue Position zurück, die um die gegebene Verschiebung in X- und Y-Richtung 
	 * (X: horizontal, Y: vertikal) verschoben ist
	 * 
	 * @param offsetX Verschiebung in horizontaler Richtung
	 * @param offsetY Verschiebung in vertikaler Richtung
	 * @return
	 */
	public Position getRelatedPosition(int offsetX, int offsetY) {
		Position relatedPos = null;
		if (getRow()+offsetX >= 0 && getCol() + offsetY >= 0 && getRow()+offsetX < 8
				&& getCol() + offsetY < 8) { //Masse von Brett TODO
			relatedPos = new Position(this.row+offsetX, this.col+offsetY);
		}
		return relatedPos;
	}

	/**
	 *Wertevergleich
	 *
	 *@param Object obj
	 *@return true genau dann wenn das übergebene Objekt eine Position mit gleichem Reihen- und Spaltenindex ist
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Position))
			return false;
		if (obj == this)
			return true;
		return (this.row == ((Position) obj).row) && (this.col == ((Position) obj).col);
	}
}

//// set Position über Indizes
///**
// * 
// * 
// * @param row
// * @param col
// */
//public void setFeld(int row, int col) {
//	this.row = row;
//	this.col = col;
//}

//// set Position über Feldname
//public void setFeld(String feldname) {
//	this.row = 8 - ((int) feldname.charAt(1) - 48);
//	this.col = (int) feldname.charAt(0) - 97;
//}
