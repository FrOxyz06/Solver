import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuEngine {
    public static void main(String[] args) throws FileNotFoundException {
        MySudokuBoard board = new MySudokuBoard("boards/valid-incomplete.sdk");
        System.out.println(board.toString());
    }
}

/*

   2 . . | 1 . 5 | . . 3
   . 5 4 | . . . | 7 1 . 
   . 1 . | 2 . 3 | . 8 .
   ------+-------+------
   6 . 2 | 8 . 7 | 3 . 4 
   . . . | . . . | . . . 
   1 . 5 | 3 . 9 | 8 . 6
   ------+-------+------
   . 2 . | 7 . 1 | . 6 .
   . 8 1 | . . . | 2 4 .
   7 . . | 4 . 2 | . . 1
   
*/