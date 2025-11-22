package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ExpensesView extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel amountLabel;
    private JTextField amountField;
    private JLabel categoryLabel;
    private JComboBox<String> categoryBox;
    private JLabel dateLabel;
    private JTextField dateField;
    private JButton addButton;
    private JButton clearButton;

    private JButton viewAllButton;

    public ExpensesView() {
        setTitle("Expense Tracker - Nicolas del Rio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel main = new JPanel(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Title - spans two columns
        titleLabel = new JLabel("Add New Expense");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        main.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Description row
        descriptionLabel = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(descriptionLabel, gbc);

        descriptionField = new JTextField(22);
        gbc.gridx = 1;
        main.add(descriptionField, gbc);

        // Amount row
        amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        main.add(amountLabel, gbc);

        amountField = new JTextField(12);
        gbc.gridx = 1;
        main.add(amountField, gbc);

        // Category row
        categoryLabel = new JLabel("Category:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        main.add(categoryLabel, gbc);

        categoryBox = new JComboBox<>(new String[] { "","Food", "Transport", "Utilities", "Entertainment", "Investment", "Other" });
        gbc.gridx = 1;
        main.add(categoryBox, gbc);

        // Date row
        dateLabel = new JLabel("Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        main.add(dateLabel, gbc);

        dateField = new JTextField(12);
        gbc.gridx = 1;
        main.add(dateField, gbc);

        // Buttons row (right aligned)
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        addButton = new JButton("Add Expense");
        clearButton = new JButton("Clear Fields");
        viewAllButton = new JButton("View All Expenses");
        buttons.add(viewAllButton);
        buttons.add(addButton);
        buttons.add(clearButton);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        main.add(buttons, gbc);


        setContentPane(main);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JComboBox<String> getCategoryBox() {
        return categoryBox;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }


    public JButton getViewAllButton() {
        return viewAllButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        descriptionField.setText("");
        amountField.setText("");
        categoryBox.setSelectedIndex(0);
        dateField.setText("");
    }

    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
}