public class StableMergeSort {
    public static void main(String[] args) {
        ScoreRecord[] records = {
            new ScoreRecord("Amy", 90),
            new ScoreRecord("Ben", 80),
            new ScoreRecord("Cara", 90),
            new ScoreRecord("Dan", 80)
        };

        mergeSort(records);

        for (ScoreRecord record : records) {
            System.out.println(record);
        }
    }

    public static void mergeSort(ScoreRecord[] values) {
        ScoreRecord[] temp = new ScoreRecord[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(
        ScoreRecord[] values,
        ScoreRecord[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid);
        mergeSort(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);
    }

    private static void merge(
        ScoreRecord[] values,
        ScoreRecord[] temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (values[i].getScore() >= values[j].getScore()) {
                temp[k++] = values[i++];
            } else {
                temp[k++] = values[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = values[i++];
        }
        while (j <= right) {
            temp[k++] = values[j++];
        }
        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }
}

// 講義中此類別名為 Record；Java 16 之後 java.lang.Record 已是保留的類別名稱，
// 因此改名為 ScoreRecord，其餘邏輯與講義相同。
class ScoreRecord {
    private String name;
    private int score;

    public ScoreRecord(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}
