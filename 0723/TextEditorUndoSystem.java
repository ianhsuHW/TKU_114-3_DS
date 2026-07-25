import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {
    private String text = "";
    private Deque<String> history = new ArrayDeque<>();

    public void append(String value) {
        history.push(text);
        text += value;
        System.out.println("新增文字：" + value);
    }

    public void deleteLast(int count) {
        if (count < 0 || count > text.length()) {
            count = text.length();
        }
        history.push(text);
        text = text.substring(0, text.length() - count);
        System.out.println("刪除最後 " + count + " 個字元");
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("沒有可復原的操作");
            return;
        }
        text = history.pop();
        System.out.println("Undo 後內容：" + text);
    }

    public void showContent() {
        System.out.println("目前內容：" + text);
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        editor.undo();

        editor.append("Java");
        editor.append(" Stack");
        editor.append(" and Queue");
        editor.showContent();

        editor.deleteLast(9);
        editor.showContent();

        editor.undo();
        editor.showContent();
        editor.undo();
        editor.showContent();
        editor.undo();
        editor.showContent();

        editor.undo();
    }
}
