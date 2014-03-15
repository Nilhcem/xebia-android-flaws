package com.example.mabanqueamoi;

public final class BankEntry {

    public final String title;
    public final String date;
    public final String amount;
    public final boolean isCredit;

    public BankEntry(String title, String date, String amount, boolean isCredit) {
        this.title = title;
        this.date = date;
        this.amount = "EUR " + amount;
        this.isCredit = isCredit;
    }
}
