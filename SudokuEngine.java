/** Display a supplied puzzle, or the included sample. */
public class SudokuEngine {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(args.length == 0 ? "fast-solve.sdk" : args[0]);
        System.out.println(board);
    }
}
