
/**
 * Zug Klasse erzeugt einen Zug aus einer Spielfigur, Start- und Endposition auf
 * einem Schachbrett inklusive Marker dafür, ob der Zug ein Schlag ist oder
 * nicht, und Speicher für die ggf. geschlagene Spielfigur.
 * 
 * @version 1.2, 29.10.2021
 * @author lmey
 */
public class Zug {
	private Position startposition;
	private Position zielposition;
	private Zugart zugart;
	private Spielbrett brett;
	private Spielfigur geschlagen; // geschlagene Spielfigur, falls in dem Zug jemand geschlagen wird, sonst null
	private Spielfigur spielfigur;

	/**
	 * Konstruktor
	 * 
	 * @param startposition
	 * @param zielposition
	 * @param brett
	 * @param zugart        Schlag oder Standard (= Bewegung ohne Schlag) oder
	 *                      EnPassant/andere Specials
	 */
	public Zug(Position startposition, Position zielposition, Spielbrett brett, Zugart zugart) {
		this.startposition = startposition;
		this.zielposition = zielposition;
		this.brett = brett;
		this.zugart = zugart;
		this.spielfigur = brett.getFigur(startposition);

		// wenn der Zug ein Schlag ist, wird im Attribut geschlagen die geschlagene
		// Spielfigur gespeichert
		if (zugart == Zugart.SCHLAG) {
			this.geschlagen = brett.getFigur(zielposition);
		} else if (zugart == Zugart.ENPASSANT) {
			if (spielfigur.getFarbe() == Spielfarbe.SCHWARZ) {
				this.geschlagen = brett.getFigur(zielposition.getRelatedPosition(-1, 0));
			}
			if (spielfigur.getFarbe() == Spielfarbe.WEISS) {
				this.geschlagen = brett.getFigur(zielposition.getRelatedPosition(+1, 0));
			}
		} else {
			this.geschlagen = null;
		}
	}

	/**
	 * Copy Konstruktor, erstellt eine Zug mit gleichen Werten in den Attributen wie
	 * der übergebene Zug
	 * 
	 * @param zug der zu kopierende Zug
	 */
	public Zug(Zug zug) {
		this.startposition = new Position(zug.startposition);
		this.zielposition = new Position(zug.zielposition);
		this.brett = zug.brett;
		this.zugart = zug.zugart;
		this.geschlagen = zug.geschlagen;
		this.spielfigur = zug.spielfigur;
	}

	/**
	 * Konstruktor, der einen Zug aus einem String und einem Spielbrett erstellt
	 * 
	 * @param zug   String des Zuges, der durchgeführt werden soll (Format z.B.
	 *              be1-e2)
	 * @param brett Spielbrett, auf dem der Zug durchgeführt werden soll
	 * @throws ZugFormatException wenn das Format des Eingabestrings nicht passt
	 * 
	 */
	public Zug(String zug, Spielbrett brett) {

		// Formatprüfung des Eingabestrings
		if ("blstdkBLSTDK".indexOf(zug.charAt(0)) == -1 || !(Character.isLetter(zug.charAt(1)))
				|| !(Character.isDigit(zug.charAt(2)))
				|| !(zug.charAt(3) == '-' || zug.charAt(3) == 'x' || zug.charAt(3) == 'X')
				|| !(Character.isLetter(zug.charAt(4))) || !(Character.isDigit(zug.charAt(5)))) {
			throw new ZugFormatException("Eingabeformat kann nicht in einen Zug umgewandelt werden!");
		}

		// Übergabe von Spielbrett und Startposition
		this.brett = brett;
		this.startposition = new Position(zug.substring(1, 3));
		this.spielfigur = brett.getFigur(startposition);
		this.zielposition = new Position(zug.substring(4, 6));

		// Setzen von Zugart und Zielposition je nachdem, welches Zeichen bzw. ob ein
		// Zeichen zwischen den Positionen steht
		if (zug.charAt(3) == 'x') {
			this.zugart = Zugart.SCHLAG;
		} else if (zug.charAt(3) == 'X') {
			this.zugart = Zugart.ENPASSANT;
		} else if (zug.charAt(3) == '-') {
			this.zugart = Zugart.STANDARD;
		}

		// bei Schlag: speichere die geschlagene Figur
		if (zugart == Zugart.SCHLAG) {
			this.geschlagen = brett.getFigur(zielposition);
		} else if (zugart == Zugart.ENPASSANT) {
			if (spielfigur.getFarbe() == Spielfarbe.SCHWARZ) {
				this.geschlagen = brett.getFigur(zielposition.getRelatedPosition(-1, 0));
			}
			if (spielfigur.getFarbe() == Spielfarbe.WEISS) {
				this.geschlagen = brett.getFigur(zielposition.getRelatedPosition(+1, 0));
			}
		}

		else {
			this.geschlagen = null;
		}

	}

	/**
	 * Gibt String im Format z.B. "Be2-e4" zurück, bei Schlag mit "x" zwischen den
	 * Positionen
	 *
	 */
	@Override
	public String toString() {
		if (zugart == Zugart.KURZEROCHADE) {
			return "0-0";
		}
		if (zugart == Zugart.LANGEROCHADE) {
			return "0-0-0";
		}
		String schlag = "-";
		if (zugart == Zugart.SCHLAG) {
			schlag = "x";
		}
		if (zugart == Zugart.ENPASSANT) {
			schlag = "X";
		}
		return "" + spielfigur.getSymbol() + startposition.getFeldname() + schlag + zielposition.getFeldname();
	}

	/**
	 * Getter
	 * 
	 * @return startposition Position, von der losgezogen wird
	 */
	public Position getStartposition() {
		return this.startposition;
	}

	/**
	 * Getter
	 * 
	 * @return zielposition Position, zu der gezogen werden soll
	 */
	public Position getZielposition() {
		return this.zielposition;
	}

	/**
	 * Getter
	 * 
	 * @return zugart Zugart (Schlag oder Standard(=nicht-Schlag))
	 */
	public Zugart getZugart() {
		return zugart;
	}

	/**
	 * Getter
	 * 
	 * @return brett Spielbrett, auf dem gezogen wird
	 */
	public Spielbrett getBrett() {
		return this.brett;
	}

	/**
	 * Getter
	 * 
	 * @return Spielfigur, die geschlagen worden ist (null wenn der Zug kein Schlag
	 *         war)
	 */
	public Spielfigur getGeschlagen() {
		return this.geschlagen;
	}

	/**
	 * Getter
	 * 
	 * @return Spielfigur, die am Zug ist
	 */
	public Spielfigur getSpielfigur() {
		return this.spielfigur;
	}

	/**
	 * Prüft, ob Zug ein Zwei-Schritt-Zug eines Bauern ist
	 * 
	 * @return boolean true, wenn Zug ein Zwei-Schritt-Zug eines Bauern ist
	 */
	public boolean isZweischritt() {
		if (this.spielfigur.getSymbol() == "b") {
			if (this.startposition.getRow() == 1 && this.zielposition.getRow() == 3) {
				return true;
			}
		}
		if (this.spielfigur.getSymbol() == "B") {
			if (this.startposition.getRow() == 6 && this.zielposition.getRow() == 4) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Wertevergleich
	 *
	 * return true genau dann wenn das übergebene Objekt ein Zug-Objekt mit den
	 * gleichen Positionen auf gleichem Brett ist
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Zug))
			return false;
		if (obj == this)
			return true;
		return ((this.startposition.equals(((Zug) obj).startposition))
				&& (this.zielposition.equals(((Zug) obj).zielposition)) && (this.zugart == ((Zug) obj).zugart))
				&& (this.brett.equals(((Zug) obj).brett));
	}

	/**
	 * Führt einen Zug aus. Erst wird geprüft, ob der Zug zu den Feldern passt. Bei
	 * einem Zug mit Schlag wird die geschlagene Figur gespeichert im Attribut
	 * geschlagen. Anschließend wird die gezogene Figur umgesetzt und alte Figuren
	 * ggf. entfernt vom Brett.
	 * 
	 */
	public void moveFigur() {

		if (zugart == Zugart.SCHLAG) {
			assert brett.getFigur(zielposition) != null;
			geschlagen = brett.getFigur(zielposition);
		} else if (zugart == Zugart.ENPASSANT) {
			if (spielfigur.getFarbe() == Spielfarbe.SCHWARZ) {
				geschlagen = brett.getFigur(zielposition.getRelatedPosition(-1, 0));
			}
			if (spielfigur.getFarbe() == Spielfarbe.WEISS) {
				geschlagen = brett.getFigur(zielposition.getRelatedPosition(+1, 0));
			}
			brett.getBrett()[geschlagen.getPosition().getRow()][geschlagen.getPosition().getCol()] = null;
		}

		brett.getBrett()[startposition.getRow()][startposition.getCol()] = null;
		brett.setFigur(spielfigur, zielposition);

	}

	/**
	 * Macht einen Zug rückgängig, indem die gezogene Figur zurückgesetzt und bei
	 * Zügen mit Schlag der Zug rückgängig gemacht wird. Wird für die Untersuchung,
	 * ob eine SchachMatt-Situation vorliegt, benötigt.
	 */
	public void rueckmoveFigur() {

		brett.getBrett()[zielposition.getRow()][zielposition.getCol()] = null;

		brett.setFigur(spielfigur, startposition);

		if (zugart == Zugart.SCHLAG) {
			if (brett.getFigur(zielposition) != null) {
			}
			brett.setFigur(geschlagen, zielposition);
		}
		if (zugart == Zugart.ENPASSANT) {
			if (spielfigur.getFarbe() == Spielfarbe.WEISS) {
				brett.setFigur(geschlagen, zielposition.getRelatedPosition(1, 0));
			}
			if (spielfigur.getFarbe() == Spielfarbe.SCHWARZ) {
				brett.setFigur(geschlagen, zielposition.getRelatedPosition(-1, 0));
			}
		}
	}

}

//public Zug setZugart(Zug zug, Zugart zugart) {
//Zug neu_zug = new Zug(zug);
//neu_zug.zugart = zugart;
//return neu_zug;
//}

//public Zug(Position startposition, Position zielposition, Spielbrett schachstellung) {
//this.startposition = startposition;
//this.zielposition = zielposition;
//this.schachstellung = schachstellung;
//this.spielfigur = schachstellung.getFigur(startposition);
//this.geschlagen = null;
//}

//public Zug(Spielfigur spielfigur, Position zielposition, Spielbrett schachstellung, boolean isSchlag) {
//this.startposition = schachstellung.getFigur(startposition).getPosition();
//this.zielposition = zielposition;
//this.schachstellung = schachstellung;
//this.spielfigur = schachstellung.getFigur(startposition);
//this.isSchlag = isSchlag;
//if (isSchlag) {
//	this.geschlagen = schachstellung.getBrett()[zielposition.getRow()][zielposition.getCol()];
//}else {
//	this.geschlagen = null;
//}
//}
//public Zug getGegenzug(Zug zug) {
//Zug gegenzug = new Zug(zug);
//gegenzug.startposition = zielposition;
//gegenzug.zielposition = startposition;
//return gegenzug;
//}
//String figur = Character.toString(zug.charAt(0));
