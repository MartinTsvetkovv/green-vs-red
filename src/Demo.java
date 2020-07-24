import io.InputReader;
import io.Printer;
import io.impl.ConsolePrinter;
import io.impl.InputConsoleReader;

public class Demo {
    public static void main(String[] args) {

        System.out.println("Please enter two numbers for grid dimensions separate by comma and space (e.g. 3, 3):");
        InputReader reader = new InputConsoleReader();
        Printer printer = new ConsolePrinter();
        String[] gridDimensions = reader.readLine().split(", ");

        int rows = Integer.parseInt(gridDimensions[0]);
        int cols = Integer.parseInt(gridDimensions[1]);
        int[][] matrix = new int[rows][cols];
        System.out.println("PLease fill the grid. Your input should contain 0 or 1:");
        try {
            GreenVsRedGameCreator gridCreator = new GreenVsRedGameCreator(matrix, reader, printer);
            System.out.println("PLease enter target cell coordinates and number N separate by comma and space (e.g. 1, 0, 10)");
            String[] targetCell = reader.readLine().split(", ");

            int targetCellRow = Integer.parseInt(targetCell[0]);
            int targetCellCol = Integer.parseInt(targetCell[1]);
            int numberN = Integer.parseInt(targetCell[2]);
            gridCreator.printResultForAllCellChanges(targetCellRow, targetCellCol, numberN);

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
}
