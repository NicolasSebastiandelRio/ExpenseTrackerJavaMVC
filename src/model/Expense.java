package model;

import java.time.LocalDate;

public class Expense {
    private String description;
    private double price;
    private String category;
    private LocalDate date;

    public Expense(String description, double price, String category, LocalDate date) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "expense [description=" + description + ", price= $" + price + ", category=" + category + ", date="
                + date + "]";
    }
}
