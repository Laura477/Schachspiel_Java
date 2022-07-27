import java.util.Scanner;

/**
 * Testklasse für das Schachspiel und zugehörige Klassen; implementiert die
 * Spiellogik
 * 
 * @version 1.2, 29.10.2021
 * @author lmey
 *
 */
public class Tests {

	public static void main(String[] args) {

		// erstellt neues Schachspiel-Objekt, setzt die Startstellung und gibt sie aus
		Schachspiel s = new Schachspiel();
		s.setzeStartstellung();
		System.out.println(s);

		// Initialisiere Eingabestring und Scanner für die Usereingabe
		String input = "";
		Scanner sc = new Scanner(System.in);
		int zug_anzahl = 0; // zählt die Anzahl der Züge

		// hier startet das Spiel mit abwechselnden Zügen, solange weder schwarz noch
		// weiss Schachmatt steht
		while (!(s.isMatt(s.getSpielbrett(), Spielfarbe.SCHWARZ)) && !(s.isMatt(s.getSpielbrett(), Spielfarbe.WEISS))) {

			// Eingabeaufforderung je nachdem, wer am Zug ist
			if (zug_anzahl % 2 == 0) {
				System.out.println("Spieler Weiss, bitte Zug angeben: (Formatbeispiel: Ba2-a3)");
			}
			if (zug_anzahl % 2 == 1) {
				System.out.println("Spieler Schwarz, bitte Zug angeben: (Formatbeispiel: ba7-a6)");
			}

			// Prüfe, ob die Usereingabe von dem Spieler kommt, der am Zug ist, ansonsten
			// Fehlermeldung
			input = sc.nextLine();
			if (zug_anzahl % 2 == 0) {
				if (Character.isLowerCase(input.charAt(0))) {
					System.out.println("Spieler Weiss ist am Zug!");
					continue;
				}
			}
			if (zug_anzahl % 2 == 1) {
				if (Character.isUpperCase(input.charAt(0))) {
					System.out.println("Spieler Schwarz ist am Zug!");
					continue;
				}
			}

			// *****Zugausführung*******//
			Zug zug = null;

			// erstelle neuen Zug aus der Usereingabe und melde Fehler, wenn das Format
			// nicht passt
			try {
				zug = new Zug(input, s.getSpielbrett());
			} catch (ZugFormatException z) {
				System.out.println(z.getMessage());
				continue;
			}

			// führe den Zug durch und fange verschiedene Fehlerfälle ab
			try {
				s.addZug(zug);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}

			// gebe das Feld nach dem ausgeführten Zug aus
			System.out.println(s);

			// prüfe ob Schachsituation gegeben ist
			if (s.isSchach(s.getSpielbrett(), Spielfarbe.SCHWARZ) && zug_anzahl % 2 == 0) {
				System.out.println("Achtung! Schwarz steht im Schach");
			}
			if (s.isSchach(s.getSpielbrett(), Spielfarbe.WEISS) && zug_anzahl % 2 == 1) {
				System.out.println("Achtung! Weiss steht im Schach");
			}

			// Spielerwechsel
			zug_anzahl++;
		}

		// Spielende - Gewinner ausgeben
		if (s.isMatt(s.getSpielbrett(), Spielfarbe.SCHWARZ)) {
			System.out.println("Schachmatt - Weiss hat gewonnen!");
		}
		if (s.isMatt(s.getSpielbrett(), Spielfarbe.WEISS)) {
			System.out.println("Schachmatt - Schwarz hat gewonnen!");
		}

		sc.close();
	}
}

//} catch (ArrayIndexOutOfBoundsException i) {
//System.out.println("Bitte spiele auf dem Spielfeld!");
//continue;
//}

//catch (NumberFormatException n) {
//System.out.println(n.getMessage()); 
//continue;
//} catch (ArithmeticException x) {
//System.out.println("Schlag/nicht-Schlag passt nicht!");
//continue;
//} catch (NullPointerException e) {
//System.out.println("Keine passende Figur auf dem Startfeld!");
//continue;
//} catch (SchachException e) {
//System.out.println(e.getMessage());
//continue;
//}
//
////System.out.println("\nPosition von Zugliste der Figur");
////System.out.println(s.getSpielbrett().getFigur(new Position("b5")).getZuege(s.getSpielbrett()));
////Position testposition = s.getSpielbrett().getFigur(new Position("b5")).getZuege(s.getSpielbrett()).get(0);
////System.out.println(testposition);
////System.out.println(testposition.getIndex1());
////System.out.println(testposition.getIndex2());
////System.out.println(testposition.getFeldname());
////
////
////Zug zug = new Zug("Bb5-c5", s);
////System.out.println("\nPosition von Zug");
////System.out.println(zug.getZielposition());
////System.out.println(zug.getZielposition().getIndex1());
////System.out.println(zug.getZielposition().getIndex2());
////System.out.println(zug.getZielposition().getFeldname());
////
////
////System.out.println("\nequals");
////System.out.println(testposition.equals(zug.getZielposition()));
////
////System.out.println("\n==");
////System.out.println(testposition == (zug.getZielposition()));
////
////System.out.println("\ncontains");
////System.out.println(s.getSpielbrett().getFigur(new Position("b5")).getZuege(s.getSpielbrett()).contains(zug.getZielposition()));
////System.out.println(s.getSpielbrett().getFigur("g6").getPosition());
//Zug zug0 = new Zug(s.getSpielbrett().getFigur("h2").getZuege(s.getSpielbrett()).get(0));
////System.out.println(s.getSpielbrett().getFigur("h2").getZuege(s.getSpielbrett()));
//System.out.println("Figur:" +zug0.getFigur()+" Start:"+zug0.getStartposition() +"Ziel:"+zug0.getZielposition()+"Schlag:"+zug0.getIsSchlag()+zug0.getBrett());
//
//
//
//System.out.println(new Zug("Bh2-h3", s));
//System.out.println("Figur:" +zug0.getFigur()+" Start:"+zug0.getStartposition() +"Ziel:"+zug0.getZielposition()+"Schlag:"+zug0.getIsSchlag()+zug0.getBrett());
//
//**********zugvergleich test***************
///		Position p1 = new Position("a2");
//Position p2 = new Position(p1);
//System.out.println(p1.equals(p2));
//
//System.out.println(s.getSpielbrett().getFigur("a2").getZuege(s.getSpielbrett()));
//System.out.println(new Zug("Ba2-a3", s.getSpielbrett()));
//Zug z1 = new Zug("Ba2-a3", s.getSpielbrett());
//System.out.println("z1:"+z1);
////System.out.println(z1.getBrett());
//Zug z2 = new Zug("Ba2-a3", s.getSpielbrett());
//System.out.println("z2:"+z2);
////System.out.println(z2.getBrett());
//System.out.println(z1.equals(z2));
//System.out.println(s.getSpielbrett().getFigur(new Position("a2")).pruefeZug(new Zug("Ba2-a3", s.getSpielbrett()), s.getSpielbrett()));

//// System.out.println(s.getSpielbrett().getFigur("e4").getZuege(s.getSpielbrett()));
//System.out.println(s.getSpielbrett().getFigur("c5").getZuege(s.getSpielbrett()));
//System.out.println(s.getSpielbrett().getFigur("f1").getZuege(s.getSpielbrett()));

//System.out.println(s.getZuege(s.getSpielbrett()));
//System.out.println(s.isMattSchwarz(s.getSpielbrett()));
////System.out.println(s.isMattSchwarzAlt(s.getSpielbrett()));
//System.out.println(s.isSchachSchwarz(s.getSpielbrett()));
//
//
////System.out.println(s.getZuege(s.getSpielbrett()));

// System.out.println(s.getSpielbrett().getFigur(new Position("0,0")));

////System.out.println(s.getSpielbrett().getFigur(new Position("e5")).getZuege(s.getSpielbrett()));
////System.out.println(s.getSpielbrett().getFigur(new Position("g4")).getZuege(s.getSpielbrett()));
//String test = "Be2-e4";
//System.out.println(("blstdkBLSTDK".indexOf(test.charAt(0))==-1 )//
//|| !(Character.isLetter(test.charAt(1))) 
//|| !(Character.isDigit(test.charAt(2))) 
//	|| !(test.charAt(3)=='-' || test.charAt(3) == 'x' ) 
//|| !(Character.isLetter(test.charAt(4))));
//|| !(Character.isDigit(test.charAt(4)))	));
