package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Grid grid = new Grid();
        char[][] createdGrid = grid.createGrid();
        grid.printGrid(createdGrid);
        grid.validateMove(createdGrid);
        grid.printGrid(createdGrid);


//        grid.checkGameState(createdGrid);
    }
}

class Grid {


    public static void validateMove(char[][] grid) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");

        String coordinates = scanner.nextLine();
        String[] splittedCoordinates = coordinates.split(" ");
        String xStringCoordinate = splittedCoordinates[0];
        String yStringCoordinate = splittedCoordinates[1];

        while (!checkIfCoordinateIsANumber(xStringCoordinate)) {
            System.out.println("You should enter numbers!");
            coordinates = scanner.nextLine();
            splittedCoordinates = coordinates.split(" ");
            xStringCoordinate = splittedCoordinates[0];
            yStringCoordinate = splittedCoordinates[1];
        }

        while (!checkIfCoordinateIsANumber(yStringCoordinate)) {
            System.out.println("You should enter numbers!");
            coordinates = scanner.nextLine();
            splittedCoordinates = coordinates.split(" ");
            xStringCoordinate = splittedCoordinates[0];
            yStringCoordinate = splittedCoordinates[1];
        }

        int xCoordinate = Integer.parseInt(xStringCoordinate);
        int yCoordinate = Integer.parseInt(yStringCoordinate);


        while (!checkIfCoordinatesInRange(grid, xCoordinate, yCoordinate) || !checkIfFieldIsEmpty(grid, xCoordinate, yCoordinate) || !checkIfCoordinateIsANumber(Integer.toString(xCoordinate)) || !checkIfCoordinateIsANumber(Integer.toString(yCoordinate))) {

            if (!checkIfCoordinateIsANumber(Integer.toString(xCoordinate)) || !checkIfCoordinateIsANumber(Integer.toString(yCoordinate))) {
                System.out.println("You should enter numbers!");
                coordinates = scanner.nextLine();
                splittedCoordinates = coordinates.split(" ");
                xStringCoordinate = splittedCoordinates[0];
                yStringCoordinate = splittedCoordinates[1];
                xCoordinate = Integer.parseInt(xStringCoordinate);
                yCoordinate = Integer.parseInt(yStringCoordinate);
            } else if (!checkIfCoordinatesInRange(grid, xCoordinate, yCoordinate)) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print("Enter the coordinates: ");
                coordinates = scanner.nextLine();
                splittedCoordinates = coordinates.split(" ");
                xStringCoordinate = splittedCoordinates[0];
                yStringCoordinate = splittedCoordinates[1];
                xCoordinate = Integer.parseInt(xStringCoordinate);
                yCoordinate = Integer.parseInt(yStringCoordinate);
            } else if (!checkIfFieldIsEmpty(grid, xCoordinate, yCoordinate)) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.print("Enter the coordinates: ");
                coordinates = scanner.nextLine();
                splittedCoordinates = coordinates.split(" ");
                xStringCoordinate = splittedCoordinates[0];
                yStringCoordinate = splittedCoordinates[1];
                xCoordinate = Integer.parseInt(xStringCoordinate);
                yCoordinate = Integer.parseInt(yStringCoordinate);
            }
        }

        grid[xCoordinate - 1][yCoordinate - 1] = 'X';

    }

    public static boolean checkIfCoordinateIsANumber(String StringCoordinate) {
        if (StringCoordinate.matches("\\d") || StringCoordinate.matches("\\d\\d")) {
            return true;
        }
        return false;
    }

    public static boolean checkIfCoordinatesInRange(char[][] grid, int xCoordinate, int yCoordinate) {
        if (xCoordinate <= grid[0].length && yCoordinate <= grid.length) {
            return true;
        }
        return false;
    }

    public static boolean checkIfFieldIsEmpty(char[][] grid, int xCoordinate, int yCoordinate) {
        return grid[xCoordinate - 1][yCoordinate - 1] == '_';
    }


    public static void checkGameState(char[][] grid) {


        if (checkFiguresCountCorrectness(grid) && checkIfOnlyOneWinPatternExists(grid)) {
            if (!checkWinConditions(grid)) {
                checkDraw(grid);
                checkIfGameIsFinished(grid);
            }
        }

    }

    public static boolean checkIfOnlyOneWinPatternExists(char[][] grid) {

        boolean isOnlyOneWinPatternPresent = true;
        int patternCounter = 0;

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                patternCounter++;
            } else if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                patternCounter++;
            }
        }
        if (checkNumberOfDiagonalPatterns(grid) > 0) {
            patternCounter += checkNumberOfDiagonalPatterns(grid);
        }

        if (patternCounter > 1) {
            isOnlyOneWinPatternPresent = false;
            System.out.println("Impossible");
        }
        return isOnlyOneWinPatternPresent;
    }


    public static int checkNumberOfDiagonalPatterns(char[][] grid) {
        int counter = 0;
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            counter++;
        }
        if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
            counter++;
        }
        return counter;
    }


    public static boolean checkFiguresCountCorrectness(char[][] grid) {

        boolean isFigureCountCorrect = true;
        int Xcounter = 0;
        int Ocounter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    Xcounter++;
                } else if (grid[i][j] == 'O') {
                    Ocounter++;
                }
            }
        }

        if (Xcounter - Ocounter >= 2 || Ocounter - Xcounter >= 2) {
            System.out.println("Impossible");
            isFigureCountCorrect = false;
        }
        return isFigureCountCorrect;
    }

    public static boolean checkIfGameIsFinished(char[][] grid) {

        boolean isGameFinished = false;
        if (!checkWinConditions(grid) && !checkForEmptyCells(grid)) {
            isGameFinished = true;
        } else {
            System.out.println("Game not finished");
        }
        return isGameFinished;
    }


    public static boolean checkDraw(char[][] grid) {

        boolean isGameDraw = false;

        if (!checkWinConditions(grid) && !checkForEmptyCells(grid)) {
            System.out.println("Draw");
            isGameDraw = true;
        }

        return isGameDraw;
    }

    public static boolean checkWinConditions(char[][] grid) {

        boolean isWinConditionPresent = false;

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                System.out.println(grid[i][0] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            } else if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                System.out.println(grid[0][i] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            } else if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
                System.out.println(grid[0][0] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            } else if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
                System.out.println(grid[2][0] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            }
        }
        return isWinConditionPresent;
    }


    public static boolean checkForEmptyCells(char[][] grid) {

        boolean isEmptyCellPresent = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '_') {
                    isEmptyCellPresent = true;
                }
            }
        }
        return isEmptyCellPresent;
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
