public class EmployeeSearchSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee(1002, "Amy Chen", "研發部", "2101"),
            new Employee(1015, "Ben Lin", "業務部", "2205"),
            new Employee(1023, "Cara Wu", "研發部", "2110"),
            new Employee(1038, "Dan Hsu", "客服部", "2308"),
            new Employee(1041, "Ella Kuo", "財務部", "2402"),
            new Employee(1056, "Frank Yeh", "業務部", "2210")
        };

        System.out.println("=== 員工資料（已依編號升冪排列）===");
        printAll(employees);

        System.out.println();
        System.out.println("資料檢查：");
        System.out.println("  依編號排序：" + isSortedById(employees));
        System.out.println("  重複編號：" + findDuplicateId(employees));

        System.out.println();
        System.out.println("=== 查詢測試 ===");
        search(employees, 1002);
        search(employees, 1056);
        search(employees, 1038);
        search(employees, 1099);

        System.out.println();
        System.out.println("=== 邊界測試 ===");
        Employee[] empty = new Employee[0];
        System.out.println("空陣列排序檢查：" + isSortedById(empty));
        search(empty, 1002);

        Employee[] duplicated = {
            new Employee(2001, "Gina Ho", "行政部", "2501"),
            new Employee(2001, "Henry Tsai", "行政部", "2502"),
            new Employee(2007, "Ivy Chang", "行政部", "2503")
        };
        System.out.println();
        System.out.println("重複編號資料檢查：" +
            findDuplicateId(duplicated));
        search(duplicated, 2001);
    }

    public static void printAll(Employee[] employees) {
        if (employees == null || employees.length == 0) {
            System.out.println("目前沒有員工資料");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static boolean isSortedById(Employee[] employees) {
        if (employees == null || employees.length < 2) {
            return true;
        }

        for (int index = 1; index < employees.length; index++) {
            if (employees[index - 1].getId() > employees[index].getId()) {
                return false;
            }
        }
        return true;
    }

    public static String findDuplicateId(Employee[] employees) {
        if (employees == null || employees.length < 2) {
            return "無";
        }

        for (int index = 1; index < employees.length; index++) {
            if (employees[index - 1].getId() == employees[index].getId()) {
                return "有，編號 " + employees[index].getId();
            }
        }
        return "無";
    }

    public static void search(Employee[] employees, int targetId) {
        if (employees == null || employees.length == 0) {
            System.out.println("查詢 " + targetId + "：資料為空，無法查詢");
            return;
        }

        if (!isSortedById(employees)) {
            System.out.println("查詢 " + targetId +
                "：資料未依編號排序，不可使用 Binary Search");
            return;
        }

        int index = binarySearchById(employees, targetId);

        if (index == -1) {
            System.out.println("查詢 " + targetId + "：查無此員工");
        } else {
            System.out.println("查詢 " + targetId + "：索引 " + index);
            System.out.println("  " + employees[index]);
        }
    }

    public static int binarySearchById(
        Employee[] employees,
        int targetId
    ) {
        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int currentId = employees[mid].getId();

            if (currentId == targetId) {
                return mid;
            }
            if (currentId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
