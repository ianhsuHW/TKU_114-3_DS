import java.util.Scanner;

public class TextAnalyzer {
    public static int countVowels(String text) {
        String lower = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countKeyword(String text, String keyword) {
        String lower = text.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();
        int count = 0;
        int index = 0;

        while ((index = lower.indexOf(lowerKeyword, index)) != -1) {
            count++;
            index += lowerKeyword.length();
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入一行非空白文字：");
        String input = sc.nextLine();

        if (input.trim().isEmpty()) {
            System.out.println("輸入不可為空白");
            sc.close();
            return;
        }

        System.out.println("\n=== 文字分析結果 ===");
        System.out.println("原始字元數：" + input.length());

        String trimmed = input.trim();
        System.out.println("有效字元數：" + trimmed.length());

        String[] words = trimmed.split("\\s+");
        System.out.println("單字數量：" + words.length);

        int vowels = countVowels(input);
        System.out.println("母音總數：" + vowels);

        String longest = findLongestWord(words);
        System.out.println("最長單字：" + longest + "（長度：" + longest.length() + "）");

        System.out.print("\n請輸入搜尋關鍵字：");
        String keyword = sc.nextLine();

        if (!keyword.trim().isEmpty()) {
            int count = countKeyword(input, keyword);
            System.out.println("關鍵字出現次數：" + count);
        }

        sc.close();
    }
}
