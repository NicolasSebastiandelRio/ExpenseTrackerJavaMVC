import view.ExpensesView;
import controller.ExpensesController;
public class App {
    public static void main(String[] args) throws Exception {
        ExpensesView view = new ExpensesView();
        view.setVisible(true);
        ExpensesController controller = new ExpensesController(view);
    }
}
