package javase01.t05;

public class CrossMatrix {

    private int size;

    public CrossMatrix(int size) {
        this.size = size;
    }

    public boolean get(int x, int y) {
        assert x < size && y < size;
        return x == y || (x == size - y - 1);
    }

    @Override
    public String toString() {

        String result = "";

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++)
                result += get(x, y) ? "1 " : "0 ";
            result += "\n";
        }
        return result;
    }
}
