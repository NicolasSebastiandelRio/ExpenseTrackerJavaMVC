package controller;

import model.Expense;
import model.modelDAO.ExpenseDAO;
import view.ExpensesView;
import view.ExpensesTableView;
import javax.swing.JOptionPane; // Importante para los mensajes
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ExpensesController {
    private ExpensesView view;
    private ExpensesTableView tableView;
    private ExpenseDAO expenseDAO;

    public ExpensesController(ExpensesView view) {
        this.view = view;
        this.expenseDAO = new ExpenseDAO();
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> addExpense());
        view.getClearButton().addActionListener(e -> view.clearFields());
        view.getViewAllButton().addActionListener(e -> openListView());
    }

    private void addExpense() {
        String description = view.getDescriptionField().getText();
        String priceText = view.getPriceField().getText();
        String category = (String) view.getCategoryBox().getSelectedItem();
        String date = view.getDateField().getText();

        if (description.isEmpty() || priceText.isEmpty() || date.isEmpty() || category.matches("")) {
            view.showMessage("Please fill in all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            LocalDate dateObj = LocalDate.parse(date);
            Expense expense = new Expense(description, price, category, dateObj);
            
            if (!expenseDAO.saveExpense(expense)) {
                view.showMessage("Error saving expense to database.");
            } else {
                view.showMessage("Expense saved to database successfully.");
                view.clearFields();
            }
        } catch (NumberFormatException e) {
            view.showMessage("Error: Price must be a valid number.");
        } catch (DateTimeParseException e) {
            view.showMessage("Error: Date must be in format YYYY-MM-DD (e.g., 2025-11-20).");
        }
    }

    private void openListView() {
        if (tableView == null) {
            tableView = new ExpensesTableView();
        }

        // 1. Configurar botón Volver
        // IMPORTANTE: Removemos listeners anteriores para evitar duplicados si abres/cierras varias veces
        for (var al : tableView.getBackButton().getActionListeners()) {
            tableView.getBackButton().removeActionListener(al);
        }
        tableView.setBackButtonListener(e -> {
            tableView.setVisible(false);
            view.setVisible(true);
        });

        // 2. Configurar botón Filtrar
        for (var al : tableView.getFilterButton().getActionListeners()) {
            tableView.getFilterButton().removeActionListener(al);
        }
        tableView.setFilterButtonListener(e -> applyFilters());

        // 3. Cargar la lista inicial (todas las expensas)
        loadAllExpenses();

        tableView.setVisible(true);
        view.setVisible(false);
    }

    // --- NUEVOS MÉTODOS DE FILTRADO Y ACTUALIZACIÓN ---

    // Método A: Carga todo (lo usas al abrir la tabla)
    public void loadAllExpenses() {
        List<Expense> allExpenses = expenseDAO.getAllExpenses();
        updateTable(allExpenses); // Reutilizamos la lógica de pintar tabla
    }

    // Método B: Aplica los filtros (lo usa el botón "Filter")
    private void applyFilters() {
        String category = tableView.getSelectedCategory();
        String monthText = tableView.getMonthFilter();
        String yearText = tableView.getYearFilter();

        Integer month = null;
        Integer year = null;

        try {
            if (!monthText.isEmpty()) {
                month = Integer.parseInt(monthText);
                if (month < 1 || month > 12) {
                    JOptionPane.showMessageDialog(tableView, "Month must be between 1 and 12.");
                    return; 
                }
            }
            if (!yearText.isEmpty()) {
                year = Integer.parseInt(yearText);
            }

            // Llamamos al DAO con los parámetros (asegúrate de haber actualizado el DAO como vimos antes)
            List<Expense> filteredList = expenseDAO.filterExpenses(category, month, year);
            
            // Actualizamos la tabla solo con los resultados filtrados
            updateTable(filteredList);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(tableView, "Month and Year must be valid numbers.");
        }
    }

    // Método C: Recibe CUALQUIER lista y la pinta en la tabla (Reutilizable)
    private void updateTable(List<Expense> expenses) {
        tableView.clearTable(); // Limpia lo viejo
        for (Expense expense : expenses) {
            tableView.addExpenseRow(new Object[]{
                "N/A", // ID placeholder
                "$" + expense.getPrice(), // Price
                expense.getCategory(),    // Category
                expense.getDate(),        // Date
                expense.getDescription()  // Description
            });
        }
    }
}