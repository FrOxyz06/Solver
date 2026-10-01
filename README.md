# Sudoku Solver

A Java Sudoku validator and recursive backtracking solver. It checks rows, columns, and 3 by 3 boxes before trying digits in empty cells.

## Run

Use a JDK (Java 21 is used in CI). From the repository root:

```sh
javac --release 21 -d build *.java
java -cp build SudokuSolverEngine
```

The default puzzle is `fast-solve.sdk`. Choose another file with:

```sh
java -cp build SudokuSolverEngine very-fast-solve.sdk
```

To display without solving:

```sh
java -cp build SudokuEngine fast-solve.sdk
```

## Puzzle format

Exactly nine lines of nine characters. Use digits `1` through `9` for clues and `.` for empty cells. Missing files and malformed rows produce an error. Conflicting clues are rejected by the solver. Output displays empty cells as `0`.

The solver reports whether it found a solution and how long that attempt took. It finds one solution; it does not determine uniqueness, and difficult inputs may be slow.

## Check

```sh
java -cp build ProjectTest
```

Checks cover both included puzzles, incomplete boards, conflicting clues, malformed data, and missing files. GitHub Actions compiles all source files and runs them.

## Layout

- `SudokuBoard.java`: loading, validation, display, and backtracking.
- `SudokuSolverEngine.java`: solver entry point.
- `SudokuEngine.java`: display-only entry point.
- `*.sdk`: included puzzles.
- `ProjectTest.java`: regression checks.

See [CS143 Sudoku](https://github.com/FrOxyz06/CS143-Sudoku) for the earlier board-loading exercise. Dependabot checks workflow updates weekly.
