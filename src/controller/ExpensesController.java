package controller;
import model.Expense;
import model.modelDAO.ExpenseDAO;
import view.ExpensesView;
import view.ExpensesTableView;
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
            LocalDate dateObj = LocalDate.parse(date); //parseo fecha a localDate
            Expense expense = new Expense(description, price, category, dateObj);
            if(expenseDAO.saveExpense(expense) == false){
                view.showMessage("Error saving expense to database.");
                return;
            }else{
                view.showMessage("Expense saved to database successfully.");
            }
            view.clearFields();
        } catch (NumberFormatException e) {
            view.showMessage("Error: Price must be a valid number.");
        } catch (DateTimeParseException e) {
            view.showMessage("Error: Date must be in format YYYY-MM-DD (e.g., 2025-11-20).");
        }
    }

        private void openListView() {
            if(tableView == null){
                tableView = new ExpensesTableView();
            }
        tableView.setBackButtonListener(e ->{
            tableView.setVisible(false);
            view.setVisible(true);
        });
        loadExpensesIntoTable();//Cargar los datos de la BD en la tabla
        tableView.setVisible(true);
        view.setVisible(false);
    }

    public void loadExpensesIntoTable() {
        tableView.clearTable();//limpio tabla en caso de que tuviese datos viejos
        List <Expense> expenses = expenseDAO.getAllExpenses();
        for (Expense expense : expenses) {
            tableView.addExpenseRow(new Object[]{
                "N/A", // ID is not implemented in Expense model YET
                expense.getDescription(),
                "$"+expense.getPrice(),
                expense.getCategory(),
                expense.getDate()
            });
        }
    }
}