package tictactoe;

public class Board {
    private Character player = 'X';
    private final Character[][] grid = new Character[3][3];

    public Board(String symbols) {
        int index = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j] = symbols.charAt(index++);
            }
        }
    }
    public Board() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j] = '_';
            }
        }
    }
    private void switchPlayer() {
        if (player.equals('X')) {
            player = 'O';
        } else {
            player = 'X';
        }
    }
    public boolean move(String _x, String _y) {
        if (!isInputCoordinateCorrect(_x, _y)) {
            return false;
        }

        grid[Integer.parseInt(_x) - 1][Integer.parseInt(_y) - 1] = player;
        switchPlayer();
        return true;
    }
    private boolean isInputCoordinateCorrect(String _x, String _y) {
        int x;
        int y;
        try {
            x = Integer.parseInt(_x);
            y = Integer.parseInt(_y);
        } catch (Exception err) {
            System.out.println("You should enter numbers!");
            return false;
        }
        // Check if input data is valid
        if ((x > 3 || x < 0) || (y > 3 || y < 0)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        x--;
        y--;

        if (!grid[x][y].equals('_')) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }
    public void print() {
        int index = 0;
        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            System.out.print('|');
            for(int j = 0; j < 3; j++) {
                System.out.print(' ');
                System.out.print(grid[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }
    public String getState() {
        if (isImpossible()) {
            return "Impossible";
        } else if (isPlayerWon('X')) {
            return "X wins";
        } else if (isPlayerWon('O')) {
            return "O wins";
        } else if (isDraw()) {
            return "Draw";
        }

        return "Game not finished";
    }
    private boolean isImpossible() {
        int xPlayer = 0;
        int oPlayer = 0;
        for (int i = 0;i < 3; i++) {
            for (int j = 0;j < 3; j++) {
                if (grid[i][j].equals('X')) xPlayer++;
                if (grid[i][j].equals('O')) oPlayer++;
            }
        }

        if (Math.abs(xPlayer - oPlayer) > 1) {
            return true;
        } else {
            return isPlayerWon('X') && isPlayerWon('O');
        }
    }
    private boolean isDraw() {
        int emptyCells = 0;
        for (int i = 0;i < 3; i++) {
            for (int j = 0;j < 3; j++) {
                if (grid[i][j].equals('_')) emptyCells++;
            }
        }

        return emptyCells == 0;
    }
    private boolean isPlayerWon(Character player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].equals(player) && grid[i][1].equals(player) && grid[i][2].equals(player)) {
                return true;
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (grid[0][i].equals(player) && grid[1][i].equals(player) && grid[2][i].equals(player)) {
                return true;
            }
        }

        // Check diagonals
        if (grid[0][0].equals(player) && grid[1][1].equals(player) && grid[2][2].equals(player)) {
            return true;
        }

        if (grid[0][2].equals(player) && grid[1][1].equals(player) && grid[2][0].equals(player)) {
            return true;
        }
        
        return false;
    }
}
