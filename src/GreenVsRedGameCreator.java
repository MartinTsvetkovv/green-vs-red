import io.InputReader;
import io.Printer;


public class GreenVsRedGameCreator extends GridCreator {

    private Printer printer;

    public GreenVsRedGameCreator(int[][] matrix, InputReader inputReader, Printer printer) {
        super(matrix, inputReader);
        this.printer = printer;
    }

    public void printResultForAllCellChanges(int targetRow, int targetCol, int numberN) {
        int green = 0;
        int result = 0;
        int[][] secondMatrix = new int[matrix[0].length][matrix[0].length];
        result = getResultForAllGenerations(targetRow, targetCol, numberN, green, result, secondMatrix);
        this.printer.print(String.valueOf(result));
    }

    protected void fillMatrix(int[][] matrix, InputReader inputReader) {

        for (int row = 0; row < matrix.length; row++) {
            String[] input = inputReader.readLine().split("");
            for (int col = 0; col < matrix[0].length; col++) {
                if (Integer.parseInt(input[col]) != 0 && Integer.parseInt(input[col]) != 1) {
                    throw new IllegalArgumentException("Input should be 1 or 0");
                }
                matrix[row][col] = Integer.parseInt(input[col]);
            }
        }
    }

    private int getResultForAllGenerations(int targetRow, int targetCol, int numberN, int greenCellNumber, int result, int[][] secondGrid) {
        while (numberN != 0) {
            numberN--;

            for (int row = 0; row < this.matrix.length; row++) {
                for (int col = 0; col < this.matrix[0].length; col++) {

                    greenCellNumber = getNumberOfGreenNeighborsPerCell(greenCellNumber, row, col);

                    checkCellForPotentialChange(greenCellNumber, secondGrid, row, col);

                    greenCellNumber = 0;
                }
            }
            result = temporaryResult(targetRow, targetCol, result, secondGrid);

            // Copying changes to
            // the main grid
            copyTheNewGenerationToTheMainGrid(secondGrid);
        }
        return result;
    }

    private void checkCellForPotentialChange(int green, int[][] secondGrid, int row, int col) {
        if (matrix[row][col] == 0 && (green == 3 || green == 6)) {
            secondGrid[row][col] = 1;
        } else {
            secondGrid[row][col] = 0;
        }
        if (matrix[row][col] == 1 && (green == 2 || green == 3 || green == 6)) {
            secondGrid[row][col] = 1;
        }
    }

    private void copyTheNewGenerationToTheMainGrid(int[][] secondMatrix) {
        for (int i = 0; i < secondMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[0].length; j++) {
                matrix[i][j] = secondMatrix[i][j];
            }
        }
    }

    private int temporaryResult(int targetCellRow, int targetCellColl, int result, int[][] secondMatrix) {
        if (secondMatrix[targetCellRow][targetCellColl] == 1) {
            result++;
        }
        return result;
    }


    private int getNumberOfGreenNeighborsPerCell(int greenCellNumber, int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                if (i == row && j == col) {
                    continue;
                }
                if (j < 0 || i < 0) {
                    continue;
                }
                if (j > matrix[0].length - 1 || i > matrix[0].length - 1) {
                    continue;
                }
                if (matrix[i][j] == 1) {
                    greenCellNumber++;
                }
            }
        }
        return greenCellNumber;
    }

}
