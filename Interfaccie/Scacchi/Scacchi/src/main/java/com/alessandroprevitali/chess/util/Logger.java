package main.java.com.alessandroprevitali.chess.util;

public class Logger {

	private Logger() {
		// Utility class: no instances needed.
	}

	/**
	 * Stampa un messaggio di log prefissato nel formato dell'applicazione.
	 *
	 * @param message testo da inviare in output
	 */
	public static void log(String message) {
		System.out.println("[Chess] " + message);
	}
}
