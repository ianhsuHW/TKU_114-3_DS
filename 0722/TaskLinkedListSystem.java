public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList tasks = new TaskLinkedList();

        tasks.addLast("T01", "Write report");
        tasks.addLast("T02", "Buy groceries");
        tasks.addFirst("T00", "Emergency fix");
        tasks.addLast("T03", "Clean desk");
        tasks.addLast("T04", "Reply email");

        System.out.println("所有工作項目：");
        tasks.printAll();
        System.out.println("未完成數量：" + tasks.unfinishedCount());

        tasks.complete("T02");
        System.out.println("完成 T02 後，未完成工作：");
        tasks.printUnfinished();

        tasks.remove("T03");
        System.out.println("刪除 T03 後，所有工作項目：");
        tasks.printAll();
        System.out.println("總工作數：" + tasks.size());
        System.out.println("未完成數量：" + tasks.unfinishedCount());
    }
}
