import java.util.*;
import java.io.*;

public class SudokuBoard {
   private int[][] board;
   
   public SudokuBoard(String filename) {
      board = new int[9][9];
      try {
         Scanner file = new Scanner(new File(filename));
         
         for(int r = 0; r < 9; r++) {
            String line = file.nextLine();
            for(int c = 0; c < 9; c++) {
               Scanner lineScan = new Scanner(line);
               char num = lineScan.next().charAt(c);
               if(num == '.') {
                  board[r][c] = 0;
               }else {
                  board[r][c] = num - '0';
               }
            }
         }
         
      } catch(FileNotFoundException e) {
         System.out.println("Cannot load: " + filename);
      } catch(InputMismatchException e) {
         System.out.println(filename + " does not meet format expectations.");
      }
   }
   
   private boolean empty(){
      for(int r = 0; r < board.length ; r++){
         for(int c= 0 ; c < board[r].length ; c++){
            if (board[r][c] < 0 || board[r][c] > 9) {
                return false;
               }
            }
      }
       return true;
   }

   private boolean hasNoDuplicates() {
       
       // Check for duplicates in each row
       for (int r = 0; r < board.length; r++) {
           Set<Integer> rowSet = new HashSet<>();
           for (int c = 0; c < board[r].length; c++) {
               int value = board[r][c];
               if (value != 0) {
                   if (rowSet.contains(value)) {
                       return false; 
                   }
                   rowSet.add(value);
               }
           }
       }
      // Check for duplicates in each colomuns
       for (int c = 0; c < board[0].length; c++) {
           Set<Integer> colSet = new HashSet<>();
           for (int r = 0; r < board.length; r++) {
               int value = board[r][c];
               if (value != 0) {
                   if (colSet.contains(value)) {
                       return false; 
                   }
                   colSet.add(value);
               }
           }
       }
   
       return true; 
   }

    
   private int[][] miniSquare(int[][] board , int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
 
      return mini;
   }
   
   private boolean checkMini() {
       for(int i = 1 ; i <= 9 ; i++){
         int[][] mini = miniSquare( board , i);
         Set<Integer> set = new HashSet<>();
         for (int r = 0; r < mini.length; r++) {
            for (int c = 0; c < mini[r].length; c++) {
               int value = mini[r][c];
               if (value != 0) {
                  if (set.contains(value)) {
                     return false;
                 }
                 set.add(value);
               }
            }
         }
       }
     return true;
    }

   public boolean isValid(){
      return empty() && hasNoDuplicates() && checkMini();
   }
   
   public boolean isSolved() {
       Map<Integer,Integer> solve = new TreeMap <Integer,Integer>();
          for(int r = 0; r < board.length ; r++){
             for(int c= 0 ; c < board[r].length ; c++){
                if(!solve.containsKey(board[r][c])){
                   solve.put(board[r][c] , 1);
                } else {
                   int currentCount = solve.get(board[r][c]);
                   solve.put(board[r][c] , currentCount + 1);
                } 
             }
          }
          
          if (solve.size() != 9) {
            return false;
          }
          
          for(Integer count : solve.values()){
             if(count != 9){
                return false;
             }
          } 
          return isValid();
    }

   
   public boolean solve() {
    // Base case: if the puzzle is already solved, return true
    if (isSolved()) {
        return true;
    }
    
    // Base case: if current board state is invalid, backtrack
    if (!isValid()) {
        return false;
    }
    
    // Iterate through each cell in the board
    for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[r].length; c++) {
            if (board[r][c] == 0) {
                
                // Try all possible numbers (1-9) in this cell
                for (int num = 1; num <= 9; num++) {
                    
                    board[r][c] = num;
                    // Recursively attempt to solve with this number placed
                    if (solve()) {
                        return true;
                    }
                    
                    // If not solved, backtrack by resetting the cell to 0
                    board[r][c] = 0;
                }
                // If no number (1-9) worked in this cell, backtrack further
                return false;
            }
        }
    }
    return false;
}  
   
   public String toString() {
      String build = " -----------------\n";
      for(int r = 0; r < board.length; r++) {
         build += "|\t";
         for(int c = 0; c < board[0].length; c++) {
            build += board[r][c] + "|\t";
         }
         build += "|\n";
      }
      build += " -----------------\n";
      return build;   
   }
}

/*
# PROGRAM OUTPUT

Checking empty board...pass duplicates
passed.
 Checking incomplete, valid board...pass duplicates
passed.
Checking complete, valid board...pass duplicates
pass duplicates
passed.
Checking dirty data board...passed.
Checking row violating board...passed.
Checking col violating board...passed.
Checking row&col violating board...passed.
Checking mini-square violating board...passed.
**** HORRAY: ALL TESTS PASSED ****

*/

/*
 Initial board
  -----------------
 |	8|	2|	7|	1|	5|	4|	3|	9|	6|	|
 |	9|	6|	5|	0|	2|	7|	1|	4|	8|	|
 |	3|	4|	1|	6|	0|	9|	7|	5|	2|	|
 |	0|	0|	0|	0|	0|	0|	0|	0|	0|	|
 |	0|	0|	0|	0|	0|	0|	0|	0|	0|	|
 |	6|	1|	8|	9|	7|	0|	4|	3|	5|	|
 |	7|	8|	6|	2|	3|	5|	0|	1|	4|	|
 |	1|	5|	4|	7|	9|	6|	8|	0|	3|	|
 |	2|	3|	9|	8|	4|	0|	0|	0|	0|	|
  -----------------
 
 
 Solving board...SOLVED in 0.014 seconds.
 
  -----------------
 |	8|	2|	7|	1|	5|	4|	3|	9|	6|	|
 |	9|	6|	5|	3|	2|	7|	1|	4|	8|	|
 |	3|	4|	1|	6|	8|	9|	7|	5|	2|	|
 |	4|	7|	2|	5|	1|	3|	6|	8|	9|	|
 |	5|	9|	3|	4|	6|	8|	2|	7|	1|	|
 |	6|	1|	8|	9|	7|	2|	4|	3|	5|	|
 |	7|	8|	6|	2|	3|	5|	9|	1|	4|	|
 |	1|	5|	4|	7|	9|	6|	8|	2|	3|	|
 |	2|	3|	9|	8|	4|	1|	5|	6|	7|	|

*/