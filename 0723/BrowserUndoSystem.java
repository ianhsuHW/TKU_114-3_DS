import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {
    private Deque<String> history = new ArrayDeque<>();

    public void openPage(String url) {
        history.push(url);
        System.out.println("開啟新頁：" + url);
    }

    public void goBack() {
        if (history.size() <= 1) {
            System.out.println("沒有上一頁");
            return;
        }
        String leaving = history.pop();
        System.out.println("返回上一頁，離開：" + leaving + "，目前頁面：" + history.peek());
    }

    public void showCurrentPage() {
        if (history.isEmpty()) {
            System.out.println("尚未開啟任何頁面");
            return;
        }
        System.out.println("目前頁面：" + history.peek());
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        browser.showCurrentPage();
        browser.goBack();

        browser.openPage("home.html");
        browser.openPage("courses.html");
        browser.openPage("java.html");
        browser.showCurrentPage();

        browser.goBack();
        browser.showCurrentPage();

        browser.openPage("stack.html");
        browser.showCurrentPage();

        browser.goBack();
        browser.goBack();
        browser.goBack();
    }
}
