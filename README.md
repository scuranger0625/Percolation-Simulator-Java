# 📘 Percolation & PercolationStats (Princeton Algorithm Assignment)

本專案模擬 **N×N 網格的滲流現象**，使用 **加權快速合併並查集 (WeightedQuickUnionUF)** 實作演算法，並透過 **蒙地卡羅模擬法** 推估滲流臨界值。

## 🧪 Files Included | 檔案內容

- `Percolation.java`：負責滲流邏輯模擬。
- `PercolationStats.java`：執行多次模擬並計算統計數據。

## ✅ Features | 功能特色

- 使用高效能的滲流檢測並處理反沖洗問題。
- 利用 `StdRandom.uniformInt()` 隨機開啟節點。
- 支援以下統計計算：
  - `mean()` 平均滲流閾值
  - `stddev()` 標準差
  - `95% confidence interval` 信賴區間估計
- 通過所有風格檢查、正確性、記憶體與效能測試。

## 🔬 How It Works | 原理說明

1. 從一個所有節點都被封鎖的 N×N 網格開始。
2. 隨機開啟一個節點直到上方連通到底部（系統滲流）。
3. 記錄已開啟節點數與總節點數的比例。
4. 重複上述步驟 T 次。
5. 輸出統計數據。

## 🧠 Algorithms Used | 使用演算法

- **加權快速合併並查集（Weighted Quick Union）** 搭配虛擬頂點與底部節點。
- 全物件導向設計，不使用靜態變數。

## 📊 Output Example | 執行範例
```bash
> java-algs4 PercolationStats 200 100
mean                    = 0.592643
stddev                  = 0.008769
95% confidence interval = [0.590827, 0.594459]
```

## 💡 Notes | 備註

- 網格索引為 1-based，即從 `(1, 1)` 到 `(N, N)`。
- 採用防禦式編程，對於非法輸入會擲出 `IllegalArgumentException`。

## 🛠️ Environment | 執行環境

- Java 語言
- 使用 Princeton 的 `algs4.jar` 套件（含 StdRandom, StdStats）
- 相容於 Coursera 自動評分系統

## 🏆 Result | 評分結果
**✅ Score: 100.00%** — 所有正確性、效能與記憶體測試皆通過。

> 本專案展現了我在演算法實作與統計模擬方面的實力，能在嚴格的評分標準下，完成正確且高效的程式設計。
