import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectTest {
    public static void main(String[] args) throws Exception {
        for (String name : new String[] {"fast-solve.sdk", "very-fast-solve.sdk"}) {
            SudokuBoard board = new SudokuBoard(name);
            if (!board.isValid() || !board.solve() || !board.isSolved())
                throw new AssertionError("Could not solve " + name);
        }
        Path input = Files.createTempFile("sudoku-test", ".sdk");
        try {
            String solution = "827154396\n965327148\n341689752\n472513689\n593468271\n618972435\n786235914\n154796823\n239841567\n";
            Files.writeString(input, solution.replace('9', '.'));
            SudokuBoard incomplete = new SudokuBoard(input.toString());
            if (incomplete.isSolved()) throw new AssertionError("Empty cells counted as solved");
            if (!incomplete.solve() || !incomplete.isSolved()) throw new AssertionError("Cannot fill missing digits");
            Files.writeString(input, solution.replace("827", "887"));
            SudokuBoard invalid = new SudokuBoard(input.toString());
            if (invalid.isValid() || invalid.solve()) throw new AssertionError("Accepted duplicate clues");
            for (String malformed : new String[] {"...", solution + ".........\n", solution.replace('9', 'x')}) {
                Files.writeString(input, malformed);
                try {
                    new SudokuBoard(input.toString());
                    throw new AssertionError("Accepted malformed input");
                } catch (IllegalArgumentException expected) { }
            }
        } finally {
            Files.deleteIfExists(input);
        }
        try {
            new SudokuBoard(input.toString());
            throw new AssertionError("Accepted missing file");
        } catch (IllegalArgumentException expected) { }
        System.out.println("Sample solves, incomplete boards, invalid clues, and input checks passed.");
    }
}
