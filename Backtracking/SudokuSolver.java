// https://leetcode.com/problems/sudoku-solver/description/

/*
37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.
*/

/*
Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
*/

/*
This Sudoku solver uses the **backtracking algorithm**, which means it solves the problem by trying possible values one by one and going back whenever a wrong choice is made. The `solveSudoku()` function is just the starting point that calls the recursive function `getSudoku(board)`. The real logic happens inside `getSudoku()`. This function scans the entire Sudoku board row by row and column by column to find an empty cell represented by `'.'`. As soon as it finds an empty cell, it tries placing digits from `'1'` to `'9'` one by one in that position. Before placing any digit, it calls the `isValid()` function to check whether that number can legally be placed there according to Sudoku rules.

The `isValid()` function checks three conditions. First, it checks the entire row to make sure the number is not already present. Second, it checks the entire column for the same reason. Third, it checks the corresponding `3 × 3` subgrid. The expression `3 * (row / 3)` gives the starting row of the subgrid, and `3 * (col / 3)` gives the starting column of the subgrid. Then `i / 3` and `i % 3` are used to traverse all 9 cells inside that subgrid. If the number already exists in any of these places, the function returns `false`; otherwise it returns `true`.

If a number is valid, the algorithm places it in the board using `board[i][j] = ch` and then recursively calls `getSudoku(board)` to solve the remaining empty cells. If the recursive call returns `true`, it means the Sudoku has been solved successfully, so the function immediately returns `true` and stops further processing. However, if the recursive call returns `false`, it means the current number choice eventually led to a dead end where no valid numbers could be placed in future cells. In that case, the algorithm performs backtracking by resetting the current cell back to `'.'` using `board[i][j] = '.'` and then tries the next number.

The most important idea to remember is that backtracking works like “choose, explore, and undo.” The algorithm chooses a number, explores further possibilities recursively, and if that path fails, it undoes the choice and tries another option. The base case occurs when the loops finish scanning the entire board without finding any empty cell. That means the Sudoku is completely filled correctly, so the function returns `true`. This recursive trial-and-error process efficiently explores all valid possibilities until the puzzle is solved.
*/

class Solution {
    public void solveSudoku(char[][] board) {
        getSudoku(board);
    }

    private boolean getSudoku(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char ch = '1'; ch <= '9'; ch++){
                        if(isValid(board, i, j, ch)){
                            board[i][j] = ch;
                            
                            if(getSudoku(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++){
            if(board[row][i] == c) return false;

            if(board[i][col] == c) return false;

            if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }

        return true;
    }
}