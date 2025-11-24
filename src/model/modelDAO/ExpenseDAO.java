package model.modelDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.Expense;

public class ExpenseDAO {
    public boolean saveExpense(Expense expense) {
        String query = "INSERT INTO expenses (description, price, category, date) VALUES (?, ?, ?, ?)";
        try(Connection conn = DBConnection.connect()){
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, expense.getDescription());
            pstmt.setDouble(2, expense.getPrice());
            pstmt.setString(3, expense.getCategory());
            pstmt.setDate(4, java.sql.Date.valueOf(expense.getDate()));
            int rowsAffected = pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Error saving expense: " + e.getMessage());
            return false;
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";
        try (Connection conn = DBConnection.connect()) {
            var pstmt = conn.prepareStatement(query);
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                LocalDate date = rs.getDate("date").toLocalDate();
                Expense expense = new Expense(description, price, category, date);
                expenses.add(expense);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving expenses: " + e.getMessage());
        }
        return expenses;
    }
}
