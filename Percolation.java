import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右方向
    private final int n; // 網格大小
    private final boolean[] open; // 將二維網格壓成一維陣列
    private int openCount; // 已打開格子數量
    private final WeightedQuickUnionUF uf; // 用於判斷是否滲流（含頂部與底部虛擬節點）
    private final WeightedQuickUnionUF ufNoBackwash; // 用於判斷是否滿格（僅含頂部虛擬節點）
    private final int virtualTop; // 頂部虛擬節點索引
    private final int virtualBottom; // 底部虛擬節點索引

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid size must be greater than 0");
        this.n = n;
        this.open = new boolean[n * n];
        this.openCount = 0;
        this.virtualTop = n * n;
        this.virtualBottom = n * n + 1;
        this.uf = new WeightedQuickUnionUF(n * n + 2); // 含 top 和 bottom
        this.ufNoBackwash = new WeightedQuickUnionUF(n * n + 1); // 只含 top，避免 backwash
    }

    public void open(int row, int col) {
        validate(row, col);
        int rowIndex = row - 1;
        int colIndex = col - 1;
        int index = xyTo1D(rowIndex, colIndex);
        if (open[index]) return;
        open[index] = true;
        openCount++;

        if (rowIndex == 0) {
            uf.union(index, virtualTop);
            ufNoBackwash.union(index, virtualTop);
        }
        if (rowIndex == n - 1) {
            uf.union(index, virtualBottom);
        }

        for (int[] dir : DIRECTIONS) {
            int newRow = rowIndex + dir[0];
            int newCol = colIndex + dir[1];
            if (inBounds(newRow, newCol)) {
                int neighborIndex = xyTo1D(newRow, newCol);
                if (open[neighborIndex]) {
                    uf.union(index, neighborIndex);
                    ufNoBackwash.union(index, neighborIndex);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        int rowIndex = row - 1;
        int colIndex = col - 1;
        if (!inBounds(rowIndex, colIndex)) throw new IllegalArgumentException("Index out of bounds");
        return open[xyTo1D(rowIndex, colIndex)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int rowIndex = row - 1;
        int colIndex = col - 1;
        if (!inBounds(rowIndex, colIndex)) throw new IllegalArgumentException("Index out of bounds");
        return ufNoBackwash.find(xyTo1D(rowIndex, colIndex)) == ufNoBackwash.find(virtualTop);
    }

    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    private int xyTo1D(int row, int col) {
        if (!inBounds(row, col)) throw new IllegalArgumentException("xyTo1D index out of bounds");
        return row * n + col;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
}
