package tictactoe;

public class Board {

    private String[][] array;
    private int turn = 0; //even = X, odd = O

    public Board() {
        array = new String[3][3];
    }


    public void updateBoard(String format) {
        int row = 0;
        int col = 0;
        for (char c : format.toCharArray()) {
            switch (c) {
                case 'X':
                    array[row][col] = "X";
                    col++;
                    break;
                case 'O':
                    array[row][col] = "O";
                    col++;
                    break;
                case '_':
                    array[row][col] = "_";
                    col++;
                    break;
            }
            if (col == 3) {
                row++;
                col = 0;
            }
        }
    }

    public void print() {
        System.out.println("---------");
        for (int r = 0; r < 3; r++) {
            System.out.print("| ");
            for (int c = 0; c < 3; c++) {
                if (c < 2)
                    System.out.print(array[r][c] + " ");
                else {
                    System.out.print(array[r][c]);
                    System.out.println(" |");
                }
            }
        }
        System.out.println("---------");
    }

    public String getGameState() {
        int xWins = 0;
        int oWins = 0;
        int numberOfEmptySpaces = 0;
        int numberOfXs = 0;
        int numberOfOs = 0;
        //vertical wins
        for (int c = 0; c < 3; c++) {
            if (array[0][c].equals("X") && array[1][c].equals("X") && array[2][c].equals("X"))
                xWins++;
            else if (array[0][c].equals("O") && array[1][c].equals("O") && array[2][c].equals("O"))
                oWins++;
        }
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (array[r][0].equals("X") && array[r][1].equals("X") && array[r][2].equals("X"))
                xWins++;
            else if (array[r][0].equals("O") && array[r][1].equals("O") && array[r][2].equals("O"))
                oWins++;
        }
        //diagonal
        if (array[0][0].equals("X") && array[1][1].equals("X") && array[2][2].equals("X"))
            xWins++;
        else if (array[0][0].equals("O") && array[1][1].equals("O") && array[2][2].equals("O"))
            oWins++;
        if (array[0][2].equals("X") && array[1][1].equals("X") && array[2][0].equals("X"))
            xWins++;
        else if (array[0][2].equals("O") && array[1][1].equals("O") && array[2][0].equals("O"))
            oWins++;
        //checking for empty spaces - x's - o's
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (array[r][c].equals("O"))
                    numberOfOs++;
                else if (array[r][c].equals("X"))
                    numberOfXs++;
                else
                    numberOfEmptySpaces++;
            }
        }
        //calculating state
        if ((xWins > 0 && oWins > 0) || Math.abs(numberOfOs - numberOfXs) >= 2) { //impossible
            return "Impossible";

        } else if (xWins == oWins) { //draw or not finished
            if (numberOfEmptySpaces == 0)
                return "Draw";
            else
                return "Game not finished";
        } else { //winners
            return xWins > oWins ? "X wins" : "O wins";
        }
    }

    public boolean spaceOccupied(String coordinate) {
        int col = Integer.parseInt(coordinate.charAt(0) + "");
        col -= 1;
        int row = Integer.parseInt(coordinate.charAt(2) + "");
        row = 3 - row;
        return !array[row][col].equals("_");
    }

    public void changeBoard(String coordinate) {
        int col = Integer.parseInt(coordinate.charAt(0) + "");
        col -= 1;
        int row = Integer.parseInt(coordinate.charAt(2) + "");
        row = 3 - row;
        array[row][col] = turn % 2 == 0 ? "X" : "O";
        turn++;
    }
}
