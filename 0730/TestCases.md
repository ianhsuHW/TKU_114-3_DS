# 0730 完整測試紀錄

## 測試環境與方法

- 測試對象：`LibraryManagementSystem`、`RepairSchedulingSystem`、`EventRegistrationSystem` 及其演算法類別。
- 測試方式：撰寫測試程式逐項比對「預期結果」與「實際結果」，由程式自動判定通過與否，不以人工目視為準。
- 每個案例都使用獨立建立的系統物件或同一組固定資料，避免前一個案例影響後一個案例。
- 測試涵蓋：空資料、單筆資料、重複資料、邊界值、找不到資料及復原操作。

## 測試結果總表

共 32 個案例，最終全部通過。

### 一、圖書借閱資料管理系統

| 編號 | 輸入 | 操作 | 預期結果 | 實際結果 | 判定 |
|---|---|---|---|---|---|
| TC-01 | 空目錄（0 筆） | `createSortedById()` | 長度 0 | 0 | 通過 |
| TC-02 | `Book("B205", "Java 基礎", "程式設計", 12)` | `addBook()` | true | true | 通過 |
| TC-03 | `Book("b101", ...)`，已存在 B101 | `addBook()` | false（重複編號，忽略大小寫） | false | 通過 |
| TC-04 | `null` | `addBook()` | false | false | 通過 |
| TC-05 | `Book("   ", ...)` | `addBook()` | false（空白編號） | false | 通過 |
| TC-06 | B205, B101, B330, B150, B410 | `createSortedById()` | B101,B150,B205,B330,B410 | B101,B150,B205,B330,B410 | 通過 |
| TC-07 | 同上，B101 與 B150 皆借閱 25 次 | `createSortedByBorrowCount()` | B101,B150,B410,B205,B330（同次數保持原順序） | B101,B150,B410,B205,B330 | 通過 |
| TC-08 | 已排序陣列，關鍵字 `b410` | `binarySearchById()` | 找到 B410（忽略大小寫） | 找到 B410 | 通過 |
| TC-09 | 已排序陣列，關鍵字 `B999` | `binarySearchById()` | -1 | -1 | 通過 |
| TC-10 | 已排序陣列，關鍵字 `null` | `binarySearchById()` | -1 | -1 | 通過 |
| TC-11 | 5 筆書籍，關鍵字 `程式設計` | `findByCategory()` | 3 筆 | 3 筆 | 通過 |
| TC-12 | 5 筆書籍，關鍵字 `文學` | `findByCategory()` | 0 筆 | 0 筆 | 通過 |
| TC-13 | 只有 B001 一筆 | `createSortedById()` | B001 | B001 | 通過 |

### 二、維修工作排程系統

| 編號 | 輸入 | 操作 | 預期結果 | 實際結果 | 判定 |
|---|---|---|---|---|---|
| TC-14 | 空 Queue | `processNext()` | null | null | 通過 |
| TC-15 | 空 Stack | `undoLast()` | null | null | 通過 |
| TC-16 | 空 Queue | `peekNext()` | null | null | 通過 |
| TC-17 | 已存在 R201，輸入 `r201` | `register()` | false（重複編號） | false | 通過 |
| TC-18 | R201(2), R202(5), R203(2), R204(5), R205(1) | `createSortedByPriority()` | R202,R204,R201,R203,R205（同等級保持登記順序） | R202,R204,R201,R203,R205 | 通過 |
| TC-19 | 上述 5 筆 | 連續兩次 `processNext()` | R201 then R202 | R201 then R202 | 通過 |
| TC-20 | 已完成 R201、R202 | `undoLast()` 後 `peekNext()` | 復原 R202，下一筆 R202 | 復原 R202，下一筆 R202 | 通過 |
| TC-21 | 關鍵字 `印表機` | `searchByDevice()` | 2 筆 | 2 筆 | 通過 |
| TC-22 | 關鍵字 `R999` | `searchById()` | null | null | 通過 |

### 三、活動報名與候補系統

| 編號 | 輸入 | 操作 | 預期結果 | 實際結果 | 判定 |
|---|---|---|---|---|---|
| TC-23 | 空系統，編號 `R999` | `cancel()` | null | null | 通過 |
| TC-24 | 無取消紀錄 | `undoCancel()` | null | null | 通過 |
| TC-25 | 空候補 Queue | `promoteNext()` | null | null | 通過 |
| TC-26 | 名額 3 人已滿，報名 R150 | `register()` | true（自動轉候補） | true（轉候補） | 通過 |
| TC-27 | 已存在 R101，輸入 `r101` | `register()` | false | false | 通過 |
| TC-28 | 編號 `"  "` | `register()` | false | false | 通過 |
| TC-29 | 關鍵字 `r150`（在候補中） | `searchById()` | R150 | R150 | 通過 |
| TC-30 | 關鍵字 `chen` | `searchByName()` | 1 筆 | 1 筆 | 通過 |
| TC-31 | 正取 R101，候補 R150、R410 | `cancel("R101")` | 取消 R101，R150 自動遞補為正取 | 取消 R101，正取含 R150 | 通過 |
| TC-32 | 有一筆取消紀錄 | `undoCancel()` | R101 | R101 | 通過 |

## 未通過項目與修正紀錄

開發過程中曾出現一項未通過的缺陷，修正後重新測試通過，紀錄如下。

### 缺陷 D-01：排序鍵值與搜尋鍵值大小寫規則不一致

| 項目 | 內容 |
|---|---|
| 對應案例 | TC-08（後來也影響 TC-29） |
| 輸入 | 已加入 B410，以關鍵字 `b410` 查詢 |
| 操作 | `BookAlgorithms.binarySearchById(sorted, "b410")` |
| 預期結果 | 找到 B410 |
| 修正前實際結果 | 查無此書（回傳 -1） |
| 判定 | **未通過** |

**原因分析**

`addBook()` 判斷重複編號時使用 `equalsIgnoreCase()`，屬於「忽略大小寫」的鍵值規則；
但 `mergeById()` 與 `binarySearchById()` 卻直接使用 `compareTo()`，屬於「區分大小寫」。

兩者鍵值規則不一致，導致系統認定 `b101` 與 `B101` 是同一本書（不可重複新增），
搜尋時卻認為 `b410` 與 `B410` 是不同字串而找不到資料。
這正是 0730 教材「常見錯誤與診斷」提到的「排序鍵值與 Binary Search 的搜尋鍵值不同」。

**修正內容**

在 `BookAlgorithms` 與 `RegistrationAlgorithms` 中，
將合併階段與二分搜尋階段的編號比較統一轉成小寫後再比較：

```java
// 排序鍵值與 Binary Search 的搜尋鍵值必須一致，
// 因此兩邊都統一轉成小寫再比較。
String leftId = books[leftIndex].getId().toLowerCase();
String rightId = books[rightIndex].getId().toLowerCase();
```

```java
String target = id.trim().toLowerCase();
String currentId = sortedBooks[mid].getId().toLowerCase();
int comparison = target.compareTo(currentId);
```

**重新測試結果**

| 編號 | 操作 | 預期結果 | 修正後實際結果 | 判定 |
|---|---|---|---|---|
| TC-08 | `binarySearchById(sorted, "b410")` | 找到 B410 | 找到 B410 | 通過 |
| TC-29 | `searchById("r150")` | R150 | R150 | 通過 |
| TC-06 | `createSortedById()` | B101,B150,B205,B330,B410 | B101,B150,B205,B330,B410 | 通過（排序結果未受影響） |

## 測試涵蓋範圍檢查

| 必要涵蓋項目 | 對應案例 |
|---|---|
| 空資料 | TC-01、TC-14、TC-15、TC-16、TC-23、TC-24、TC-25 |
| 單筆資料 | TC-13 |
| 重複資料 | TC-03、TC-07、TC-17、TC-18、TC-27 |
| 邊界值 | TC-05、TC-26（名額剛好額滿）、TC-28 |
| 找不到資料 | TC-09、TC-10、TC-12、TC-22 |
| 復原操作 | TC-20、TC-31、TC-32 |

## 結論

1. 三個系統在空資料狀態下都不會拋出例外，一律回傳 `null` 或空集合。
2. 編號重複的判斷、排序與搜尋現在使用一致的「忽略大小寫」規則。
3. Merge Sort 在相同鍵值時先取左側，TC-07 與 TC-18 確認穩定性成立。
4. Queue 的 FIFO 與 Stack 的 LIFO 在 TC-19、TC-20 中互相配合，復原後資料回到等待佇列前端。
5. 唯一發現的缺陷 D-01 屬於「鍵值規則不一致」，已修正並重新測試通過。
