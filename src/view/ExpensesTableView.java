package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.FlowLayout;
public class ExpensesTableView extends JFrame {
    private JTable expensesTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryFilterComboBox;
    private JTextField monthFilterTextField;
    private JTextField yearFilterTextField;
    private JButton filterButton;
    private JButton backButton;

    public ExpensesTableView(){
        setTitle("Expenses table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout());
        // top panel - filters
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Category:"));
        categoryFilterComboBox = new JComboBox<>(new String[]{"All", "Food", "Transport", "Utilities", "Entertainment","Other"});
        filterPanel.add(categoryFilterComboBox);
        filterPanel.add(new JLabel("Month:"));
        monthFilterTextField = new JTextField(5);
        filterPanel.add(monthFilterTextField);
        filterPanel.add(new JLabel("Year:"));
        yearFilterTextField = new JTextField(5);
        filterPanel.add(yearFilterTextField);
        filterButton = new JButton("Filter");
        filterPanel.add(filterButton);
        add(filterPanel, java.awt.BorderLayout.NORTH);
        // center panel - table
        String[] columnNames = {"ID", "Price", "Category", "Date", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        expensesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(expensesTable);
        add(scrollPane, java.awt.BorderLayout.CENTER);
        // bottom panel - back button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        bottomPanel.add(backButton);
        add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }
    public void clearTable() {
        tableModel.setRowCount(0);
    }
    public void addExpenseRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
    public String getSelectedCategory() {
        return (String) categoryFilterComboBox.getSelectedItem();
    }
    public String getMonthFilter() {
        return monthFilterTextField.getText();
    }
    public String getYearFilter() {
        return yearFilterTextField.getText();
    }
    public void setFilterButtonListener(java.awt.event.ActionListener listener) {
        filterButton.addActionListener(listener);
    }
    public void setBackButtonListener(java.awt.event.ActionListener listener) {
        backButton.addActionListener(listener);
    }

}