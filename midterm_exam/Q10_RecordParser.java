public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {"A101|Keyboard|3|850", "A102|Mouse|-1|500", "broken data", "A103|Monitor|2|4200", "A104||1|300"};
        for (String record : records) System.out.println(record + " -> " + calculateRecordTotal(record));
        System.out.println("Valid records: " + countValidRecords(records));
        System.out.println("Grand total: " + calculateGrandTotal(records));
    }
    public static boolean isValidRecord(String record) {
        if (record == null) return false;
        String[] fields = record.split("\\|", -1);
        if (fields.length != 4 || fields[0].trim().isEmpty() || fields[1].trim().isEmpty()) return false;
        try { return Integer.parseInt(fields[2].trim()) > 0 && Integer.parseInt(fields[3].trim()) > 0; }
        catch (NumberFormatException e) { return false; }
    }
    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) return -1;
        String[] fields = record.split("\\|", -1);
        return Integer.parseInt(fields[2].trim()) * Integer.parseInt(fields[3].trim());
    }
    public static int countValidRecords(String[] records) {
        if (records == null) return 0;
        int count = 0; for (String record : records) if (isValidRecord(record)) count++; return count;
    }
    public static int calculateGrandTotal(String[] records) {
        if (records == null) return 0;
        int total = 0;
        for (String record : records) { int recordTotal = calculateRecordTotal(record); if (recordTotal >= 0) total += recordTotal; }
        return total;
    }
}
