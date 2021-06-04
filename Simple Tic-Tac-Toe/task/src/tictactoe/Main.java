package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here

        Grid grid = new Grid();
        grid.gameInit();

    }
}

class Grid {
    private static int moveCounter = 0;

    public static void gameInit() {

        char[][] createdGrid = createGrid();
        printGrid(createdGrid);


        while (true) {
            validateMove(createdGrid);
            printGrid(createdGrid);
            if (checkWinConditions(createdGrid)) {
                break;
            } else if (checkDraw(createdGrid)) {
                break;
            }

        }

    }

    public static void validateMove(char[][] grid) {

        char playerSign = 'X';

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            String[] splittedCoordinates = coordinates.split(" ");

            if (splittedCoordinates.length < 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String xStringCoordinate = splittedCoordinates[0];
            String yStringCoordinate = splittedCoordinates[1];
            int xCoordinate;
            int yCoordinate;

            if (!checkIfCoordinateIsANumber(xStringCoordinate, yStringCoordinate)) {
                System.out.println("You should enter numbers!");
                continue;
            } else {
                xCoordinate = Integer.parseInt(xStringCoordinate);
                yCoordinate = Integer.parseInt(yStringCoordinate);
            }

            if (!checkIfCoordinatesInRange(grid, xCoordinate) || !checkIfCoordinatesInRange(grid, yCoordinate)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (!checkIfFieldIsEmpty(grid, xCoordinate, yCoordinate)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (moveCounter % 2 == 0) {
                playerSign = 'X';
            } else {
                playerSign = 'O';
            }

                grid[xCoordinate - 1][yCoordinate - 1] = playerSign;
            moveCounter++;
            break;
        }


    }

    public static boolean checkIfCoordinateIsANumber(String xStringCoordinate, String yStringCoordinate) {
        if ((xStringCoordinate.matches("\\d") || xStringCoordinate.matches("\\d\\d")) &&
                (yStringCoordinate.matches("\\d") || yStringCoordinate.matches("\\d\\d"))) {
            return true;
        }
        return false;
    }

    public static boolean checkIfCoordinatesInRange(char[][] grid, int xyCoordinate) {
        if (xyCoordinate <= grid[0].length && xyCoordinate <= grid.length) {
            return true;
        }
        return false;
    }

    public static boolean checkIfFieldIsEmpty(char[][] grid, int xCoordinate, int yCoordinate) {
        return grid[xCoordinate - 1][yCoordinate - 1] == '_';
    }


    public static boolean checkGameState(char[][] grid) {

        boolean isEndCondition = false;
        if (checkFiguresCountCorrectness(grid) && checkIfOnlyOneWinPatternExists(grid)) {
            if (!checkWinConditions(grid)) {
                checkDraw(grid);
                isEndCondition = true;
            }
        }
        return isEndCondition;
    }

    public static boolean checkIfOnlyOneWinPatternExists(char[][] grid) {

        boolean isOnlyOneWinPatternPresent = true;
        int patternCounter = 0;

        for (int i = 0; i < grid.length; i++) {
            if ((grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) && grid[i][0] != '_') {
                patternCounter++;
            } else if ((grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) && grid[0][i] != '_') {
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


        if ((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) && grid[0][0] != '_') {
            counter++;
        }
        if ((grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) && grid[0][0] != '_') {
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

        if (!checkForEmptyCells(grid)) {
            System.out.println("Draw");
            isGameDraw = true;
        }

        return isGameDraw;
    }

    public static boolean checkWinConditions(char[][] grid) {

        boolean isWinConditionPresent = false;

        for (int i = 0; i < grid.length; i++) {
            if ((grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) && grid[i][0] != '_') {
                System.out.println(grid[i][0] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            } else if ((grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) && grid[0][i] != '_') {
                System.out.println(grid[0][i] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            } else if ((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) && grid[0][0] != '_') {
                System.out.println(grid[0][0] + " wins");
                isWinConditionPresent = !isWinConditionPresent;
                break;
            } else if ((grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) && grid[2][0] != '_') {
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

        char[][] field = new char[3][3];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '_';
            }
        }
        return field;
    }

}
