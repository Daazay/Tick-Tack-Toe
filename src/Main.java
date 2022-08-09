package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        board.print();

        while (board.getState().equals("Game not finished")) {
            if(board.move(scanner.next(),scanner.next())) {
                board.print();
            }
        };
        System.out.println(board.getState());
    }
}
