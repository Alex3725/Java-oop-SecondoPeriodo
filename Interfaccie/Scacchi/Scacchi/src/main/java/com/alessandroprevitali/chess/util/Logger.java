package main.java.com.alessandroprevitali.chess.util;

public class Logger {

	private Logger() {
		// Utility class: no instances needed.
	}

	public static void log(String message) {
		System.out.println("[Chess] " + message);
	}
}
