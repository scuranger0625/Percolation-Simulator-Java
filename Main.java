import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Main {
    public static void main(String[] args) {
        int n = 20;        // 網格大小
        int trials = 30;   // 模擬次數（進行 30 次 Monte Carlo 實驗）

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" +
                stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
