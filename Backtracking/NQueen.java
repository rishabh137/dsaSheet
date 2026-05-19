// https://leetcode.com/problems/n-queens/

/*
51. N-Queens

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
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