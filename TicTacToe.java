import java.util.Scanner;

public class TicTacToe {
    private int[] board = {1,2,3,4,5,6,7,8,9};
    private int player = 0;
    private Scanner scanner = new Scanner(System.in);

    private char displayCell (int index) {
        if (board[index] == 0) {
            return 'X';
        } else if (board[index] == 10) {
            return 'O';
        } else {
            return (char) Character.forDigit(board[index], 10);
        }
    }

    private void displayBoard () {
        System.out.printf("%c|%c|%c\n", displayCell(0), displayCell(1), displayCell(2));
        System.out.printf("%c|%c|%c\n", displayCell(3), displayCell(4), displayCell(5));
        System.out.printf("%c|%c|%c\n", displayCell(6), displayCell(7), displayCell(8));
    }

    private boolean checkMove (int index) {
        return (index > -1 && index < 10) && (board[index] != 0 || board[index] != 10);
    }

    private boolean haveDraw() {
        boolean draw = true;
        for (int cell : board) {
            if (cell > 0 && cell < 10) {
                draw = false;
                break;
            }
        }
        return draw;
    }

    private boolean haveWinner() {
        boolean win = false;
        int[][] combos = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
        };
        for (int[] combo : combos) {
            if (board[combo[0]] == 0 
            &&  board[combo[1]] == 0
            &&  board[combo[2]] == 0) {
                win = true;
            } else if (board[combo[0]] == 10 
            &&  board[combo[1]] == 10
            &&  board[combo[2]] == 10) {
                win = true;
            }
        }
        return win;
    }

    TicTacToe() {
        displayBoard();
        listenForInput();
    }

    private int nextPlayer() {
        return player == 0 ? 10 : 0;
    }

    void listenForInput () {
        System.out.println("select a cell to play on");
        int input = scanner.nextInt();
        int index = input - 1;
        if(checkMove(index)) {
            board[index] = player;
            displayBoard();
            if (haveWinner()) {
                System.out.printf("%s Wins!", player == 0 ? "X" : "O");
            } else if (haveDraw()) {
                System.out.println("Draw");
            } else {
                player = nextPlayer();
                listenForInput();
            }
        } else {
            System.out.println("not valid");
            listenForInput();
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
