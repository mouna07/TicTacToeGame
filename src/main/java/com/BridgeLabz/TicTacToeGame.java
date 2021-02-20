package com.BridgeLabz;

import java.util.Scanner;

public class TicTacToeGame {
    public static final int HEAD = 0;
    public static final int TAIL = 1;

    // created the board of given size
    public static char[] createBoard() {
        char[] board = new char[10];
        for (int position = 1; position < 10; position++) {
            board[position] = ' ';
        }
        return board;
    }

    // get input from the user
    public static char getInput(char givenInput) {
        return Character.toUpperCase(givenInput);
    }

    // display the board
    public static void displayBoard(char[] board) {
        System.out.println(board[1] + " | " + board[2] + " |" + " " + board[3]);
        System.out.println("---------");
        System.out.println(board[4] + " | " + board[5] + " |" + " " + board[6]);
        System.out.println("---------");
        System.out.println(board[7] + " | " + board[8] + " |" + " " + board[9]);
    }

    // UC4 check if board Empty or not
    public static boolean isBoardEmpty(char[] board, int index) {
        return board[index] == ' ';
    }

    // UC4 checks whether the space available or occupied returns index
    public static int getUserMove(char[] board, Scanner getInput) {
        while (true) {
            System.out.println("Enter the index you want to move");
            int index = getInput.nextInt();
            if ((index > 0 && index < 10) && isBoardEmpty(board, index)) {
                return index;
            } else if ((index <= 0 && index >= 10)) {
                System.out.println("Invalid Index Try Again!!!");
            } else {
                System.out.println("Already Occupied");
            }
        }
    }

    // UC5 make move to the given index
    public static void makeMove(char[] board, int userInput, char letterInput) {
        if (isBoardEmpty(board, userInput)) {
            board[userInput] = letterInput;
        }
    }

    // UC6 Toss to decide who starts first
    private static String tossWhoStartsFirst() {
        int tossResult = (int) (Math.floor(Math.random() * 10)) % 2;
        if (tossResult == HEAD) {
            System.out.println("player will start");
            return "Player";
        } else {
            System.out.println("computer will start");
            return "Computer";
        }
    }

    // UC7 Check the winner
    public static boolean isWinner(char[] board, char c) {
        return ((board[1] == c && board[2] == c && board[3] == c) || (board[4] == c && board[5] == c && board[6] == c)
                || (board[7] == c && board[8] == c && board[9] == c)
                || (board[1] == c && board[5] == c && board[9] == c)
                || (board[3] == c && board[5] == c && board[7] == c)
                || (board[1] == c && board[4] == c && board[7] == c)
                || (board[2] == c && board[5] == c && board[8] == c)
                || (board[3] == c && board[6] == c && board[9] == c));
    }

    // check tie case
    public static boolean checkTie(char[] board) {
        for (int position = 0; position < 10; position++) {
            if (board[position] == ' ') {
                return false;
            }
        }
        return true;
    }

    // UC8 computer plays
    public static boolean computerTurn(char[] board, char computerInput, char playerInput) {
        for (int position = 1; position < 10; position++) {
            if (isBoardEmpty(board, position)) {
                board[position] = computerInput;
                if (isWinner(board, computerInput))
                    return true;
                else
                    board[position] = ' ';
            }
        }
        // index where block is made to prevent win

        int blockPosition = denyWinOpponent(board, playerInput);
        if (denyWinOpponent(board, playerInput) > 0 && denyWinOpponent(board, playerInput) < 10) {
            board[blockPosition] = computerInput;
        } else {
            int position = (int) (Math.random() * 9) + 1;
            while (!isBoardEmpty(board, position)) {
                position = (int) (Math.random() * 9) + 1;
            }
            board[position] = computerInput;
        }
        displayBoard(board);
        return false;
    }

    // UC9 opponent blocked
    public static int denyWinOpponent(char[] board, char playerInput) {
        for (int position = 1; position < 10; position++) {
            if (board[position] == ' ') {
                board[position] = playerInput;
                if (!isWinner(board, playerInput)) {
                    board[position] = ' ';
                } else {
                    {
                        board[position] = playerInput;
                        return position;
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        Scanner userInput = new Scanner(System.in);
        char[] board = createBoard();
        char playerInput = userInput.next().charAt(0);
        char computerInput = getInput((playerInput) == 'X' ? 'O' : 'X');
        displayBoard(board);
        String playStarter = tossWhoStartsFirst();
        char player;
        if (playStarter == "player")
            player = playerInput;
        else {
            player = computerInput;
        }

        int userMove = getUserMove(board, userInput);
        makeMove(board, userMove, playerInput);
        // checked if the player won
        if (isWinner(board, playerInput)) {
            System.out.println("player is the winner");
            return;
        }
        // computer's turn to move
        if (computerTurn(board, computerInput, playerInput)) {
            System.out.println("computer is the winner");
            return;
        }

        if (checkTie(board) == true) {
            System.out.println("It's a Tie");
        } else {
            System.out.println("Change the Turn");
        }
        int x = denyWinOpponent(board, playerInput);
    }
}