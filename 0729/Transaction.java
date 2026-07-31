public class Transaction {
    private String id;
    private String account;
    private int amount;
    private int timeSequence;

    public Transaction(
        String id,
        String account,
        int amount,
        int timeSequence
    ) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }

        if (account == null) {
            this.account = "";
        } else {
            this.account = account.trim();
        }

        this.amount = amount;

        if (timeSequence < 0) {
            this.timeSequence = 0;
        } else {
            this.timeSequence = timeSequence;
        }
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    public int getTimeSequence() {
        return timeSequence;
    }

    @Override
    public String toString() {
        return id + " 帳號=" + account
            + " 金額=" + amount
            + " 時間序號=" + timeSequence;
    }
}
