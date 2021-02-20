package com.BridgeLabz;

import java.util.Scanner;

public class TicTacToeGame {
    // created the board of given size
    public static void createBoard() {
        char[] board = new char[10];
        for (int position = 1; position < 10; position++) {
            board[position] = ' ';
        }
    }

    public static char getInput() {
        Scanner input = new Scanner(System.in);
        char givenInput = input.next().charAt(0);
        if (givenInput == 'X' || givenInput == 'x') {
            return givenInput;
        } else if (givenInput == 'O' || givenInput == 'o') {
            return givenInput;
        }
        return 'N';
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        createBoard();
        getInput();
    }
}