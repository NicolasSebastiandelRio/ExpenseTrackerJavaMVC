package model.modelDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public List<Expense> filterExpenses(String category,Integer month,Integer year){
        List<Expense> filteredExpenses = new ArrayList<>();
        // "WHERE 1=1" es un truco para concatenar "AND ..." sin preocuparse si es el primero
        StringBuilder query = new StringBuilder("SELECT * FROM expenses WHERE 1=1");
        List<Object> parameters = new ArrayList<>();// Lista para guardar los valores de los ? en orden

        //filtro de categoria
        if(category != null && !category.equals("All") && !category.isEmpty()){
            query.append(" AND category = ?");
            parameters.add(category);
        }
        //Filtro de Mes (SQL Server usa la función MONTH())
        if(month != null){
            query.append(" AND MONTH(date) = ?");
            parameters.add(month);
        }
        //Filtro de Año (SQL Server usa la función YEAR())
        if(year != null){
            query.append(" AND YEAR(date) = ?");
            parameters.add(year);
        }
        try(Connection conn = DBConnection.connect()){
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            //Asignar los valores a los ? dinamicamente 
            for(int i=0;i<parameters.size();i++){
                pstmt.setObject(i+1, parameters.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String cat = rs.getString("category");
                LocalDate date = rs.getDate("date").toLocalDate();
                Expense expense = new Expense(description, price, cat, date);
                filteredExpenses.add(expense);
            }
        }catch(SQLException e){
            System.out.println("Error filtering expenses: " + e.getMessage());
        }
        return filteredExpenses;
    }
}
