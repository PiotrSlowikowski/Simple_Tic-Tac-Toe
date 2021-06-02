package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Grid grid = new Grid();
        char[][] createdGrid = grid.createGrid();
        grid.printGrid(createdGrid);
        grid.checkScore(createdGrid);
    }
}

class Grid {

    public static void checkScore(char[][] grid) {

        System.out.println("LOL");

    }

    public static boolean isGameFinished(char[][] grid) {
        boolean isFinished = true;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j] == '_') {
                    isFinished = false;
                }
            }
        }
        return isFinished;
    }

    public static void printGrid(char[][] field) {

        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static char[][] createGrid() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String inputCells = scanner.nextLine();

        char[][] field = new char[3][3];
        int counter = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = inputCells.charAt(counter);
                counter++;
            }
        }
        return field;
    }

}
