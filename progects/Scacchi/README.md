# Scacchi (Java)

Base project structure for a terminal chess game with clean package separation.

## Current status

- Layered structure: `bootstrap`, `controller`, `domain`, `logic`, `ui`, `util`
- Play loop in terminal
- Algebraic input format: `e2 e4` or `e2e4`
- Basic validation (turn ownership, board bounds, pawn movement)
- Auto-promotion to queen for pawns

## Run

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Next steps

1. Implement full movement rules for all pieces in `MoveValidator`
2. Add check/checkmate and stalemate detection
3. Replace terminal ASCII UI with Lanterna renderer and input handling

