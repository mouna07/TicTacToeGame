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
    public static char getInput(Scanner givenInput) {
        System.out.println("choose X or O");
        String letter = givenInput.next();
        while (!letter.equalsIgnoreCase("x") && !letter.equalsIgnoreCase("o")) {
            System.out.println("please enter correct letter : x or o");
            letter = givenInput.next();
        }
        return letter.toUpperCase().charAt(0);
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

    public static char[] userInputMove(char[] board, Scanner userInput, char playerLetter) {
        System.out.println("Enter the index from 1-9 to make a move");
        int position = userInput.nextInt();
        while (position >= 10 || position <= 0) {
            System.out.println("enter correct position between 1 and 9");
            position = userInput.nextInt();
        }
        while (!isBoardEmpty(board, position)) {
            System.out.println("the position is already occupied");
            position = userInput.nextInt();
        }
        board[position] = playerLetter;
        return board;
    }


    // UC5 make move to the given index
    public static void makeMove(char[] board, int userInput, char letterInput) {
        if (isBoardEmpty(board, userInput)) {
            board[userInput] = letterInput;
        }
    }

    // UC6 Toss to decide who starts first
    private static String tossWhoStartsFirst() {
        System.out.println("Toss the coin");
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

    // returned the winning position index
    public static int winningPosition(char[] board, char letter) {
        for (int position = 1; position < 10; position++) {
            if (isBoardEmpty(board, position)) {
                board[position] = letter;
                if (isWinner(board, letter)) {
                    board[position] = ' ';
                    return position;
                }
                board[position] = ' ';
            }
        }
        return 0;
    }

    // UC8 computer plays
    public static boolean computerPlay(char[] board, char computerLetter, char playerLetter) {
        int index = 0;
        index = winningPosition(board, computerLetter);
        if (index != 0) {
            board[index] = computerLetter;
            return true;
        }
        int blockIndex = denyWinOpponent(board, playerLetter);
        if (blockIndex != 0) {
            board[blockIndex] = computerLetter;
            return false;
        }
        index = chooseCornerPosition(board);
        if (index == 0) {
            index = centerOrSides(board);
        }
        board[index] = computerLetter;
        return false;
    }

    // UC9 opponent blocked
    public static int denyWinOpponent(char[] board, char playerInput) {
        int index = 0;
        index = winningPosition(board, playerInput);
        return index;
    }

    // UC10 choose corner positions
    public static int chooseCornerPosition(char[] board) {
        int[] boardCorners = { 1, 3, 7, 9 };
        for (int position = 0; position < boardCorners.length; position++) {
            if (isBoardEmpty(board, boardCorners[position]))
                return position;
        }
        return 0;
    }

    // UC11 choose center or sides
    public static int centerOrSides(char[] board) {
        if (isBoardEmpty(board, 5)) // center index
            return 5;
        int[] sides = { 2, 4, 6, 8 }; // side indexes
        for (int index = 0; index <= 3; index++)
            if (isBoardEmpty(board, sides[index])) // checking board enpty or not
                return index;
        return 0;

    }

    // UC12 Play game till the end
    public static void playGameUntillItEnd(char[] board, char playerLetter, char computerLetter, Scanner userInput,
                                           String firstPlayer) {
        int toss = firstPlayer.equalsIgnoreCase("computer") ? 1 : 0;
        while (!checkTie(board)) {
            if (toss == 0) {
                board = userInputMove(board, userInput, playerLetter);
                displayBoard(board);
                if (isWinner(board, playerLetter)) {
                    System.out.println("player is the winner");
                    return;
                }
                toss = 1;
            } else {
                System.out.println("changing turn");
                if (computerPlay(board, computerLetter, playerLetter)) {
                    displayBoard(board);
                    System.out.println("computer is the winner");
                    return;
                }
                toss = 0;
            }
            displayBoard(board);
        }
        System.out.println("its a tie");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        char[] board = createBoard();
        Scanner userInput = new Scanner(System.in);
        char playerInput = getInput(userInput);
        System.out.println("Player 1 choose : " + playerInput);
        char computerInput = (playerInput == 'X')?'O':'X';
        String playStarter = tossWhoStartsFirst();
        System.out.println("Choose X or O");
        displayBoard(board);
        playGameUntillItEnd(board, playerInput, computerInput, userInput, playStarter);

    }
}
