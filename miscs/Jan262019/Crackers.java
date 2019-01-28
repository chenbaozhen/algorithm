package algorithm.miscs.Jan262019;

public class Crackers {
    int[][] OFFSETS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int numberOfCrakers(char[][] board) {
        int count = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 'C' && !visited[r][c]) {
                    int[] xy = explore(r, c, board, visited);
                    if (xy[1] != xy[0] && xy[3] != xy[2]) return -1;
                    count++;
                }
            }
        }
        return count;
    }
    private int[] explore(int r, int c, char[][] board, boolean[][] visited) {
        visited[r][c] = true;
        int minR = r, maxR = r, minC = c, maxC = c;
        for (int[] os : OFFSETS) {
            int nr = r + os[0], nc = c + os[1];
            if (nr >= board.length || nc >= board[0].length || nr < 0 || nc < 0) {
                continue;
            }
            if (board[nr][nc] != 'C') continue;
            if (visited[nr][nc]) continue;

            int[] bound = explore(nr, nc, board, visited);
            minR = Math.min(minR, bound[0]);
            minC = Math.min(minC, bound[2]);
            maxR = Math.max(maxR, bound[1]);
            maxC = Math.max(maxC, bound[3]);
        }
        return new int[]{minR, maxR, minC, maxC};
    }

    public int numberOfCrackers2(char[][] board) {
        int count = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != 'C') continue;
                boolean up = r - 1 < 0 ? false : board[r - 1][c] == 'C';
                boolean left = c - 1 < 0 ? false : board[r][c - 1] == 'C';
                if (!up && !left) count++;
                boolean down = r + 1 < board.length ? board[r + 1][c] == 'C' : false;
                boolean right = c + 1 < board[0].length ? board[r][c + 1] == 'C' : false;
                if ((up && left) || (up && right) || (down && left) || (down && right) ) return -1;
            }
        }
        return count;
    }
}

