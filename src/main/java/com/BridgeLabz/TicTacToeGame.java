package com.BridgeLabz;

import java.util.Scanner;

public class TicTacToeGame {
    // created the board of given size
    public static char[] createBoard() {
        char[] board = new char[10];
        for (int position = 1; position < 10; position++) {
            board[position] = ' ';
        }
        return board;
    }

    // get input from the user
    public static char getInput(Scanner input) {
        char givenInput = input.next().charAt(0);
        if (givenInput == 'X' || givenInput == 'x')
            return givenInput;
        else if (givenInput == 'O' || givenInput == 'o')
            return givenInput;
        return givenInput;
    }

    // display the board
    public static void displayBoard(char[] board) {
        System.out.println(board[1]+" | "+board[2]+" |"+" "+board[3]);
        System.out.println("---------");
        System.out.println(board[4]+" | "+board[5]+" |"+" "+board[6]);
        System.out.println("---------");
        System.out.println(board[7]+" | "+board[8]+" |"+" "+board[9]);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        Scanner input = new Scanner(System.in);
        char[] board = createBoard();
        char chosenValue = getInput(input);
        displayBoard(board);
    }
}