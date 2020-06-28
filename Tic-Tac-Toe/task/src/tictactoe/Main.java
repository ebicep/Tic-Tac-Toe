package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.updateBoard("_________");
        board.print();
        Scanner scanner = new Scanner(System.in);
        while (!board.getGameState().equals("X wins") && !board.getGameState().equals("O wins") && !board.getGameState().equals("Draw")) {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            while (coordinates.length() != 3 || !isValidNumber(coordinates) || !numberBetweenValidRange(coordinates) || board.spaceOccupied(coordinates)) {
                if (coordinates.length() != 3)
                    System.out.println("Enter numbers like this - 1 1");
                else if (!numberBetweenValidRange(coordinates))
                    System.out.println("Coordinates should be from 1 to 3!");
                else if (!isValidNumber(coordinates))
                    System.out.println("You should enter numbers");
                else if (board.spaceOccupied(coordinates))
                    System.out.println("This cell is occupied! Choose another one!");
                System.out.print("Enter the coordinates: ");
                if (scanner.hasNextLine()) {
                    coordinates = scanner.nextLine();
                }
            }
            board.changeBoard(coordinates);
            board.print();
        }
        System.out.println(board.getGameState());
    }

    public static boolean numberBetweenValidRange(String str) {
        return Integer.parseInt(str.charAt(0) + "") < 4 && Integer.parseInt(str.charAt(2) + "") < 4;
    }

    public static boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str.charAt(0) + "");
            Integer.parseInt(str.charAt(2) + "");
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

