// https://leetcode.com/problems/n-queens/

/*
51. N-Queens

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
*/

/*
Think of this problem as placing queens **column by column**.
At every recursive call, the current column is fixed, and we try to place one queen in every possible row of that column. If placing the queen is safe, we place it and move to the next column. If later we realize that arrangement cannot lead to a valid solution, we remove the queen and try another row. This is the main backtracking idea: **place → explore → remove**.

The board is initially filled with `'.'`, which represents empty cells. When a queen is placed, that cell becomes `'Q'`. The function `nQueens(col, ...)` means: “Try to correctly place queens starting from this column.” If `col == n`, it means queens have been successfully placed in all columns, so the current board becomes one valid solution and is added to the result.

The `for` loop inside `nQueens` tries every row for the current column. Before placing a queen, `isSafe()` checks whether another queen can attack that position. Since queens are being placed from left to right, only the left side needs checking. So `isSafe()` checks three directions:

* left side of the same row
* upper-left diagonal
* lower-left diagonal

If no queen exists in these directions, the position is safe.

The most important concept to remember is:

* Put a queen
* Recursively solve remaining columns
* Remove the queen after recursion

That removal step is called backtracking. It restores the board so another possibility can be explored. The algorithm keeps trying every valid arrangement until all possible solutions are found.

*/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        for(int i = 0; i < n; i++){
            Arrays.fill(board[i], '.');
        }

        nQueens(0, board, result, n);

        return result;
    }

    private void nQueens(int col, char[][] board, List<List<String>> result, int n){
        if(col == n){
            List<String> temp = new ArrayList<>();

            for(int i = 0; i < n; i++){
                temp.add(new String(board[i]));
            }

            result.add(temp);
            return;
        }

        for(int row = 0; row < n; row++){
            if(isSafe(row, col, board, n)){
                board[row][col] = 'Q';
                nQueens(col+1, board, result, n);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(int row, int col, char[][] board, int n){
        for(int i = 0; i < col; i++){
            if(board[row][i] == 'Q') return false;
        }

        for(int i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if(board[i][j] == 'Q') return false;
        }

        for(int i = row, j = col; i < n && j >= 0; i++, j--){
            if(board[i][j] == 'Q') return false;
        }

        return true;
    }
}