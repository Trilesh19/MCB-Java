import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalesReportWindow extends JFrame {
    private JComboBox<String> employeeComboBox;
    private JButton generateReportButton;
    private JTextArea reportTextArea;

    private ArrayList<Sale> sales; // List of sales for generating reports

    public SalesReportWindow(ArrayList<Sale> sales) {
        this.sales = sales;

        setTitle("Sales Report by Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create employee selection components
        employeeComboBox = new JComboBox<>();
        populateEmployeeComboBox();

        generateReportButton = new JButton("Generate Report");
        reportTextArea = new JTextArea(10, 30);
        reportTextArea.setEditable(false);

        // Create a scroll pane for the report text area
        JScrollPane scrollPane = new JScrollPane(reportTextArea);

        // Add components to the frame
        JPanel selectionPanel = new JPanel();
        selectionPanel.add(new JLabel("Select Employee:"));
        selectionPanel.add(employeeComboBox);
        selectionPanel.add(generateReportButton);
        add(selectionPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the generate report button
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmployee = employeeComboBox.getSelectedItem().toString();
                generateSalesReport(selectedEmployee); // Generate report for the selected employee
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void populateEmployeeComboBox() {
        // In this example, we assume employees are stored in a separate data structure
        // You can adapt this code to retrieve employee data from your system

        // Sample employees
        String[] employees = {"Employee A", "Employee B", "Employee C"};

        // Add employees to the combo box
        for (String employee : employees) {
            employeeComboBox.addItem(employee);
        }
    }

    private void generateSalesReport(String employee) {
        reportTextArea.setText(""); // Clear the report text area

        // Create a map to store the total sales by employee
        Map<String, Double> salesByEmployee = new HashMap<>();

        // Iterate through the sales and calculate the total sales for each employee
        for (Sale sale : sales) {
            String saleEmployee = sale.getEmployee();

            // Check if the sale belongs to the selected employee
            if (saleEmployee.equals(employee)) {
                // If the employee already exists in the map, add the sale amount to their total
                // If not, initialize their total with the sale amount
                salesByEmployee.put(saleEmployee, salesByEmployee.getOrDefault(saleEmployee, 0.0) + sale.getAmount());
            }
        }

        // Display the sales report
        reportTextArea.append("Sales Report for " + employee + ":\n");
        for (Map.Entry<String, Double> entry : salesByEmployee.entrySet()) {
            String salesEmployee = entry.getKey();
            double totalSales = entry.getValue();
            reportTextArea.append(salesEmployee + ": $" + totalSales + "\n");
        }
    }

    public static void main(String[] args) {
        // Sample usage to test the SalesReportWindow
        ArrayList<Sale> sales = new ArrayList<>();
        sales.add(new Sale("Employee A", 5000));
        sales.add(new Sale("Employee B", 8000));
        sales.add(new Sale("Employee A", 3000));
        sales.add(new Sale("Employee C", 6000));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SalesReportWindow window = new SalesReportWindow(sales);
                window.setVisible(true);
            }
        });
    }
}

// Sample Sale class for representing sale details
class Sale {
    private String employee;
    private double amount;

    public Sale(String employee, double amount) {
        this.employee = employee;
        this.amount = amount;
    }

    public String getEmployee() {
        return employee;
    }

    public double getAmount() {
        return amount;
    }
}
