public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();
        playlist.addLast("S01", "First Song");
        playlist.addLast("S02", "Second Song");
        playlist.addLast("S03", "Third Song");

        System.out.println("完整播放順序：");
        playlist.print();

        System.out.println("搜尋 S02：" + playlist.find("S02"));

        playlist.remove("S01");
        System.out.println("刪除第一首後：");
        playlist.print();

        playlist.remove("S03");
        System.out.println("刪除最後一首後：");
        playlist.print();
    }
}
