package model;

import java.time.LocalDate;

public class expense {
    private String description;
    private double amount;
    private String category;
    private LocalDate date;

    public expense(String description, double amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public double getAmount() {
        return amount;
    }
    public String getCategory() {
        return category;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "expense [description=" + description + ", amount= $" + amount + ", category=" + category + ", date="
                + date + "]";
    }
}
