import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n; // 網格大小
    private final boolean[] open; // 將二維網格壓成一維陣列
    private int openCount; // 已打開格子數量
    private final WeightedQuickUnionUF uf; // 用於判斷是否滲流（含頂部與底部虛擬節點）
    private final WeightedQuickUnionUF ufNoBackwash; // 用於判斷是否滿格（僅含頂部虛擬節點）
    private final int virtualTop; // 頂部虛擬節點索引
    private final int virtualBottom; // 底部虛擬節點索引
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右方向

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

    // 打開指定格子，並與相鄰的開格子 union
    public void open(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row, col);
        if (open[index]) return; // 若已開則略過
        open[index] = true;
        openCount++;

        if (row == 0) {
            uf.union(index, virtualTop);
            ufNoBackwash.union(index, virtualTop);
        }
        if (row == n - 1) {
            uf.union(index, virtualBottom);
        }

        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (inBounds(newRow, newCol)) {
                int neighborIndex = xyTo1D(newRow, newCol);
                if (open[neighborIndex]) {
                    uf.union(index, neighborIndex);
                    ufNoBackwash.union(index, neighborIndex);
                }
            }
        }
    }

    // 回傳此格子是否已被打開
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return open[xyTo1D(row, col)];
    }

    // 回傳此格子是否與頂部虛擬點相連（可視為有水流入）
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufNoBackwash.connected(xyTo1D(row, col), virtualTop);
    }

    // 是否已經滲流（即 top 虛擬點與 bottom 虛擬點連通）
    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    // 回傳目前總共開了多少格子
    public int numberOfOpenSites() {
        return openCount;
    }

    // 將二維格子座標轉成一維索引（供 union-find 使用）
    private int xyTo1D(int row, int col) {
        return row * n + col;
    }

    // 驗證格子座標是否合法
    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    // 確認座標是否在邊界內
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
}
