package controller;
import model.Expense;
import model.modelDAO.ExpenseDAO;
import view.ExpensesView;
import view.ExpensesTableView;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ExpensesController {
    private ExpensesView view;
    private ExpensesTableView tableView;
    public ExpensesController(ExpensesView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> addExpense());
        view.getClearButton().addActionListener(e -> view.clearFields());
        view.getViewAllButton().addActionListener(e -> openListView() );
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

            // 2. Convertir el Texto a LocalDate
            LocalDate dateObj = LocalDate.parse(date); 

            Expense expense = new Expense(description, price, category, dateObj);
            
            view.showMessage("Expense added: " + expense);
            view.clearFields();
            ExpenseDAO expenseDAO = new ExpenseDAO();
            expenseDAO.saveExpense(expense);
        } catch (NumberFormatException e) {
            view.showMessage("Error: Price must be a valid number.");
        } catch (DateTimeParseException e) {
            view.showMessage("Error: Date must be in format YYYY-MM-DD (e.g., 2025-11-20).");
        }
    }

        private Object openListView() {
        tableView = new ExpensesTableView();
        tableView.setVisible(true);
        return tableView;
    }
}