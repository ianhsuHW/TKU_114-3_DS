package w0721;

import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("1新增 2搜尋 3修改 4刪除 5列出 0結束：");
            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;
            if (choice.equals("1")) add(names, scanner);
            else if (choice.equals("2")) search(names, scanner);
            else if (choice.equals("3")) update(names, scanner);
            else if (choice.equals("4")) remove(names, scanner);
            else if (choice.equals("5")) System.out.println(names);
            else System.out.println("無效選項。 ");
        }
    }
    static int indexOf(ArrayList<String> names, String name) { for (int i = 0; i < names.size(); i++) if (names.get(i).equalsIgnoreCase(name.trim())) return i; return -1; }
    static void add(ArrayList<String> names, Scanner s) { System.out.print("姓名："); String n=s.nextLine().trim(); if(n.isEmpty()) System.out.println("姓名不可空白。 "); else { names.add(n); System.out.println("新增成功。 "); } }
    static void search(ArrayList<String> names, Scanner s) { System.out.print("搜尋姓名："); int i=indexOf(names,s.nextLine()); System.out.println(i < 0 ? "找不到。" : "找到，索引：" + i); }
    static void update(ArrayList<String> names, Scanner s) { System.out.print("原姓名："); int i=indexOf(names,s.nextLine()); if(i<0){System.out.println("找不到。 ");return;} System.out.print("新姓名："); String n=s.nextLine().trim(); if(n.isEmpty()) System.out.println("姓名不可空白。 "); else {names.set(i,n);System.out.println("修改成功。 ");} }
    static void remove(ArrayList<String> names, Scanner s) { System.out.print("刪除姓名："); int i=indexOf(names,s.nextLine()); System.out.println(i < 0 ? "找不到。" : "刪除成功：" + names.remove(i)); }
}
