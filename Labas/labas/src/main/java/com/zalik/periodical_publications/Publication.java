package com.zalik.periodical_publications;

// Базовий клас видання
class Publication {
    private String title;
    private double monthlyPrice;

    public Publication(String title, double monthlyPrice) {
        this.title = title;
        this.monthlyPrice = monthlyPrice;
    }

    public String getTitle() { return title; }
    public double getMonthlyPrice() { return monthlyPrice; }
}
