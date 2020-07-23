import io.InputReader;

public abstract class GridCreator {
    protected int[][] matrix;
    protected InputReader inputReader;

    protected GridCreator(int[][] matrix, InputReader inputReader){
        this.matrix = matrix;
        this.inputReader = inputReader;
        this.fillMatrix(matrix, inputReader);

    }

    protected abstract void fillMatrix(int[][] matrix, InputReader inputReader);

}
