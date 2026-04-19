# ♔ Chess — JavaFX

## Struttura progetto
Copia i file nella tua cartella `src/` esistente, sovrascrivendo i file stub.

## Come aprire in IntelliJ IDEA
1. Apri il progetto esistente `Scacchi`
2. Sostituisci tutti i file `.java` con quelli forniti
3. Assicurati che il module sia configurato con:
   - **Source Root**: `src/`
   - **JDK**: openjdk-24
   - **Library**: javafx.base (già configurata nel tuo `.iml`)

## VM Options (Run Configuration)
Aggiungi nei VM options della configurazione di run:
```
--module-path /PATH/TO/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
```
Sostituisci `/PATH/TO/javafx-sdk` con il tuo percorso JavaFX (es. `C:\Users\TuoNome\Java\openjfx-26_windows-x64_bin-sdk\javafx-sdk-26`).

## Main Class
`main.java.com.alessandroprevitali.chess.app.AppLauncher`

## Funzionalità implementate
- ♟ Mosse complete per tutti i pezzi (Re, Regina, Torre, Alfiere, Cavallo, Pedone)
- ♚ Rilevamento scacco, scacco matto e stallo
- ♜ Arrocco (lato re e lato donna)
- ♛ Promozione automatica a Regina
- 🟡 Evidenziazione pezzo selezionato (giallo)
- 🟢 Punti verdi per mosse disponibili
- 🔴 Lampeggio del re sotto scacco
- 🔄 Pulsante "Nuova Partita"
