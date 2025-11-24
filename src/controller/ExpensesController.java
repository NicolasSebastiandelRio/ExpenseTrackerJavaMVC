package controller;
import model.Expense;
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
        String amountText = view.getPriceField().getText();
        String category = (String) view.getCategoryBox().getSelectedItem();
        String date = view.getDateField().getText();

        if (description.isEmpty() || amountText.isEmpty() || date.isEmpty() || category.matches("")) {
            view.showMessage("Please fill in all fields.");
            return;
        }

        try {
            // 1. Convertir el Texto a Double
            double amount = Double.parseDouble(amountText);

            // 2. Convertir el Texto a LocalDate
            // Esto convierte "2023-10-20" en un objeto Fecha real
            LocalDate dateObj = LocalDate.parse(date); 

            // 3. Crear el objeto Expense usando los tipos correctos
            Expense expense = new Expense(description, amount, category, dateObj);
            
            // Éxito
            view.showMessage("Expense added: " + expense);
            view.clearFields();
                    // DB implementation to save the expense inserted. 
        } catch (NumberFormatException e) {
            view.showMessage("Error: Amount must be a valid number.");
        } catch (DateTimeParseException e) {
            // Capturamos el error si la fecha está mal escrita
            view.showMessage("Error: Date must be in format YYYY-MM-DD (e.g., 2025-11-20).");
        }
    }

        private Object openListView() {
        tableView = new ExpensesTableView();
        tableView.setVisible(true);
        return tableView;
    }
}