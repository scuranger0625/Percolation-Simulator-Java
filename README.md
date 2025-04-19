# Percolation-Simulator-Java

This project implements a percolation simulation based on the assignment from the Princeton Algorithms course on Coursera.

本專案為模擬滲流系統（Percolation Simulation）的 Java 程式，依據 Princeton 大學 Coursera 課程《Algorithms, Part I》之作業題目實作。

---

## 🧠 Description | 專案說明

The simulation models a percolation system using an N-by-N grid. Each site can be either open or blocked. The system **percolates** if there is a full path from the top row to the bottom row through open sites.

本模擬以 N×N 的網格作為滲流系統模型，每個格子可為「開啟」或「封閉」。若存在一條從上排連通到底排的通路，則稱系統**成功滲流（Percolates）**。

### Included Files | 檔案內容：

- `Percolation.java`：模擬滲流邏輯，使用 Union-Find 結構追蹤連通狀態
- `PercolationStats.java`：進行多次 Monte Carlo 模擬並統計滲流門檻
- `Main.java`：主要執行程式，可直接啟動模擬流程
- `algs4.jar`：Princeton 提供的輔助函式庫（包含統計工具與資料結構）

---

## 🛠 How to Compile & Run | 編譯與執行方式

You must include `algs4.jar` in your classpath.

執行前請將 `algs4.jar` 加入 classpath。

```bash
javac -cp .:algs4.jar Main.java Percolation.java PercolationStats.java
java -cp .:algs4.jar Main
