public class SudokuSolverEngine {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(args.length == 0 ? "fast-solve.sdk" : args[0]);
        System.out.println("Initial board");
        System.out.println(board);
        long start = System.nanoTime();
        boolean solved = board.solve();
        double seconds = (System.nanoTime() - start) / 1_000_000_000.0;
        System.out.printf("%s in %.3f seconds.%n", solved ? "SOLVED" : "NO SOLUTION", seconds);
        System.out.println(board);
    }
}
