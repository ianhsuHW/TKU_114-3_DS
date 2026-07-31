# 0730 資料結構選擇說明

本文件以「維修工作排程系統」與「圖書借閱資料管理系統」為主，
說明系統中各項功能所選用的資料結構與演算法、選擇原因，
以及未採用另一種方法的原因。每項說明都對應實際程式檔名與 method 名稱。

## 功能對照總表

| # | 功能 | 檔案 | Method | 選用 |
|---|---|---|---|---|
| 1 | 保存全部維修工作 | `RepairSchedulingSystem.java` | `printAll()` / 欄位 `allTasks` | ArrayList |
| 2 | 依登記順序處理工作 | `RepairSchedulingSystem.java` | `processNext()` | Queue |
| 3 | 復原最近一次完成 | `RepairSchedulingSystem.java` | `undoLast()` | Stack |
| 4 | 依設備名稱找出全部工作 | `RepairAlgorithms.java` | `findByDevice()` | Sequential Search |
| 5 | 依編號快速查詢書籍 | `BookAlgorithms.java` | `binarySearchById()` | Binary Search |
| 6 | 依編號排序書籍目錄 | `BookAlgorithms.java` | `mergeSortById()` | Merge Sort |
| 7 | 依優先等級排序且不打亂登記順序 | `RepairAlgorithms.java` | `mergeSortByPriority()` | 穩定 Merge Sort |
| 8 | 額滿後的候補名單 | `EventRegistrationSystem.java` | `register()` / `promoteNext()` | Queue |

---

## 功能一：保存全部維修工作

- **檔案 / Method**：`RepairSchedulingSystem.java`，欄位 `allTasks`，走訪於 `printAll()`。
- **選用**：`ArrayList<RepairTask>`。

**選擇原因**

工作總數在執行期間會不斷增加，事前無法確定筆數，`ArrayList` 可以動態成長。
它同時支援 `size()`、`get(index)` 及 enhanced for 走訪，
因此統計筆數、逐筆列印與轉成陣列排序都很方便。

**未採用另一種方法的原因**

- 不使用**固定長度陣列**：必須事先決定容量，超過就得自行搬移資料；筆數未知時不合適。
- 不使用 **Queue 或 Stack** 當主資料：兩者都只能從特定端點存取，
  無法依索引取得第 n 筆，也不能在不破壞內容的情況下重複走訪。

**責任界定**：`allTasks` 是完整主資料；`waiting` 與 `completed` 只代表處理狀態。

---

## 功能二：依登記順序處理工作

- **檔案 / Method**：`RepairSchedulingSystem.java` 的 `processNext()`、`peekNext()`。
- **選用**：Queue（以 `ArrayDeque` 實作，使用 `offer()`、`poll()`、`peek()`）。

**選擇原因**

維修工作應該先登記先處理，這正是 FIFO 的定義。
`offer()` 加到尾端、`poll()` 從前端取出，剛好符合排隊的語意，
且兩個操作都是 O(1)，不需要搬移其他資料。

**未採用另一種方法的原因**

- 不使用 **Stack**：Stack 是 LIFO，最晚登記的會最先被處理，
  等於讓後來的人插隊，與需求相反。
- 不使用 **ArrayList + `remove(0)`**：語意上看不出「排隊」的意圖，
  而且每次移除第 0 筆都要把後面所有元素往前搬。

---

## 功能三：復原最近一次完成

- **檔案 / Method**：`RepairSchedulingSystem.java` 的 `undoLast()`。
- **選用**：Stack（以 `ArrayDeque` 實作，使用 `push()`、`pop()`）。

**選擇原因**

復原一定是從「最近一次」開始，屬於 LIFO。
`processNext()` 完成工作時用 `push()` 放入 `completed`，
`undoLast()` 就能用 `pop()` 取出最後完成的那一筆，再用 `offerFirst()` 放回等待佇列前端。

**未採用另一種方法的原因**

- 不使用 **Queue**：Queue 會取出「最早完成」的工作，
  連續按兩次復原時順序會完全相反。
- 不使用 **ArrayList**：雖然可以用 `size() - 1` 取最後一筆，
  但每次都要自行計算索引並手動移除，比 `push`/`pop` 容易寫錯。

**重要細節**：復原時必須同時修改兩個結構——從 Stack 取出後要放回 Queue，
只改其中一邊會讓系統狀態不一致。

---

## 功能四：依設備名稱找出全部工作

- **檔案 / Method**：`RepairAlgorithms.java` 的 `findByDevice()`。
- **選用**：Sequential Search。

**選擇原因**

有三個條件讓 Sequential Search 成為唯一可行的選擇：

1. 結果可能有多筆（多台印表機都可能報修），必須走訪完整份資料才能全部收集。
2. 資料並未依「設備名稱」排序，主資料是依登記順序保存的。
3. 使用的是「包含關鍵字」的模糊比對（`toLowerCase().contains()`），不是完全相等比對。

**未採用另一種方法的原因**

- 不使用 **Binary Search**：Binary Search 的前提是資料必須依**搜尋鍵值**排序。
  目前資料沒有依設備名稱排序，直接套用會得到錯誤結果。
  而且 Binary Search 找到一筆就停止，無法保證取得全部符合資料，
  也無法處理「包含關鍵字」這種模糊條件。

---

## 功能五：依編號快速查詢書籍

- **檔案 / Method**：`BookAlgorithms.java` 的 `binarySearchById()`，
  由 `LibraryManagementSystem.searchById()` 呼叫。
- **選用**：Binary Search。

**選擇原因**

書籍編號是唯一鍵值，且查詢前已先用 `mergeSortById()` 排序。
Binary Search 每次刪掉一半範圍，複雜度為 O(log n)；
1024 筆資料最多只需約 10 次比較，而 Sequential Search 最差要 1024 次。
在「排序一次、查詢很多次」的情境下，排序成本可由後續多次快速查詢分攤。

**未採用另一種方法的原因**

- 不使用 **Sequential Search**：資料量變大時最差情況必須比對每一筆，成本明顯較高。
  （不過若整批資料只查詢一次，Sequential Search 反而比較單純，不必先付排序成本。）

**關鍵前提**：排序鍵值與搜尋鍵值必須一致。
本系統的 `addBook()` 以 `equalsIgnoreCase()` 判斷重複編號，
因此 `mergeById()` 與 `binarySearchById()` 也統一先 `toLowerCase()` 再比較，
否則會出現「不能重複新增 `b101`，卻又查不到 `b101`」的矛盾（詳見 `TestCases.md` 缺陷 D-01）。

---

## 功能六：依編號排序書籍目錄

- **檔案 / Method**：`BookAlgorithms.java` 的 `mergeSortById()` 及私有的 `mergeById()`。
- **選用**：Merge Sort。

**選擇原因**

Merge Sort 的最差情況仍是 O(n log n)，效率可預期，
而且它是穩定排序，相同鍵值的資料不會被任意調換。
排序時操作的是 `toArray()` 產生的副本，不會改變 `books` 這份主資料的原始順序。

**未採用另一種方法的原因**

- 不使用 **Selection Sort**：最差與最佳都是 O(n²)，
  而且遠距離交換會破壞穩定性。
- 不使用 **Insertion Sort**：雖然接近有序時很快（最佳 O(n)），
  但一般資料的最差情況仍是 O(n²)。
- 不使用 `Arrays.sort()`：本次作業要求自行實作排序演算法。

**代價**：Merge Sort 需要一個暫存陣列，額外空間為 O(n)，
這是用記憶體換取穩定時間效率的取捨。

---

## 功能七：依優先等級排序且不打亂登記順序

- **檔案 / Method**：`RepairAlgorithms.java` 的 `mergeSortByPriority()` 及私有的 `merge()`。
- **選用**：穩定的 Merge Sort（降冪）。

**選擇原因**

需求是「等級高的先做；等級相同時，先登記的先做」。
合併時只要在等級相同的情況下**先取左側**，就能保持原本的登記順序：

```java
if (tasks[leftIndex].getPriority() >= tasks[rightIndex].getPriority()) {
    temp[tempIndex] = tasks[leftIndex];   // 相同等級先取左側
}
```

`TestCases.md` 的 TC-18 驗證了此行為：
等級 5 的 R202（登記 2）排在 R204（登記 4）之前，
等級 2 的 R201（登記 1）排在 R203（登記 3）之前。

**未採用另一種方法的原因**

- 不使用 **Selection Sort**：它以遠距離交換把最大值搬到前面，
  相同等級的相對順序可能被打亂，無法滿足「先登記先做」。
- 不使用 `>`（而使用 `>=`）：若寫成 `>`，等級相同時會改取右側，
  穩定性就會被破壞。等號的有無直接決定排序是否穩定。

---

## 功能八：額滿後的候補名單

- **檔案 / Method**：`EventRegistrationSystem.java` 的 `register()`、`promoteNext()`、`cancel()`。
- **選用**：Queue 作候補、Stack 作取消紀錄、ArrayList 作主資料。

**選擇原因**

同一個系統同時需要三種結構，各自負責不同語意：

- `allRegistrations`（ArrayList）：完整主資料，供排序與姓名搜尋走訪。
- `waitlist`（Queue）：候補必須公平地先到先補，屬於 FIFO。
- `cancelled`（Stack）：復原一定從最近一次取消開始，屬於 LIFO。

正取額滿時 `register()` 自動把報名者 `offer()` 到候補；
`cancel()` 讓出名額後呼叫 `promoteNext()`，由候補 Queue 依序遞補。

**未採用另一種方法的原因**

- 候補不使用 **Stack**：會變成最晚報名的人最先遞補，對先報名者不公平。
- 取消紀錄不使用 **Queue**：連續復原時會從最舊的取消開始還原，與「復原最近一次」的語意相反。
- 不把三者合併成單一集合：一個集合無法同時表達「全部資料」「等待順序」「完成狀態」三種語意，
  必須明確定義哪一個是主資料、哪些只代表處理狀態。

---

## 三種搜尋與排序的整體比較

| 方法 | 時間複雜度 | 前提條件 | 本系統使用位置 |
|---|---|---|---|
| Sequential Search | O(n) | 無 | `RepairAlgorithms.findByDevice()`、`BookAlgorithms.findByCategory()` |
| Binary Search | O(log n) | 資料必須依搜尋鍵值排序 | `BookAlgorithms.binarySearchById()` |
| Selection Sort | O(n²) | 無（不穩定） | 未使用 |
| Insertion Sort | 最佳 O(n)、最差 O(n²) | 無（穩定） | 未使用 |
| Merge Sort | O(n log n) | 需要 O(n) 額外空間（穩定） | `BookAlgorithms.mergeSortById()`、`RepairAlgorithms.mergeSortByPriority()` |

| 結構 | 存取方式 | 本系統使用位置 |
|---|---|---|
| ArrayList | 依索引存取、可重複走訪 | `allTasks`、`allRegistrations`、`books` |
| Queue | FIFO，`offer()` / `poll()` | `waiting`、`waitlist` |
| Stack | LIFO，`push()` / `pop()` | `completed`、`cancelled` |

## 結論

資料結構的選擇不是看哪一個「比較好」，而是看功能的語意需求：

1. 需要保存全部資料並重複走訪 → ArrayList。
2. 需要公平的先到先服務 → Queue。
3. 需要從最近一次往回還原 → Stack。
4. 結果有多筆、資料未排序或條件是模糊比對 → Sequential Search。
5. 鍵值唯一、資料已排序且查詢次數多 → Binary Search。
6. 需要穩定且效率可預期的排序 → Merge Sort。

最容易犯的錯誤不是選錯結構，而是**同一份資料在不同功能使用了不一致的鍵值規則**，
或是**同一個物件存在多個結構中卻沒有定義誰是主資料**。
本系統以 `allTasks` / `allRegistrations` 作為唯一主資料，
排序一律操作副本，並統一以忽略大小寫作為編號鍵值規則，避免上述問題。
