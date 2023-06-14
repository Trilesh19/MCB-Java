import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarDetailViewWindow extends JFrame {
    private JTextArea carDetailsTextArea;

    public CarDetailViewWindow(ArrayList<Car> cars) {
        setTitle("Car Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a text area to display the car details
        carDetailsTextArea = new JTextArea(10, 30);
        carDetailsTextArea.setEditable(false);

        // Create a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(carDetailsTextArea);

        // Create a button to refresh the car details
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCarDetails(cars); // Refresh the car details
            }
        });

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        displayCarDetails(cars); // Initial display of car details

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void displayCarDetails(ArrayList<Car> cars) {
        carDetailsTextArea.setText(""); // Clear the text area

        // Iterate through the cars and display their details
        for (Car car : cars) {
            String carDetails = "Year of Purchase: " + car.getYearOfPurchase() +
                    "\nManufacturer: " + car.getManufacturer() +
                    "\nName: " + car.getName() +
                    "\nCost: " + car.getCost() +
                    "\n-------------------\n";
            carDetailsTextArea.append(carDetails);
        }
    }

    public static void main(String[] args) {
        // Sample usage to test the CarDetailViewWindow
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car(2019, "Toyota", "Camry", 25000));
        cars.add(new Car(2020, "Honda", "Accord", 27000));
        cars.add(new Car(2021, "Ford", "Mustang", 35000));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CarDetailViewWindow window = new CarDetailViewWindow(cars);
                window.setVisible(true);
            }
        });
    }
}

// Sample Car class for representing car details
class Car {
    private int yearOfPurchase;
    private String manufacturer;
    private String name;
    private double cost;

    public Car(int yearOfPurchase, String manufacturer, String name, double cost) {
        this.yearOfPurchase = yearOfPurchase;
        this.manufacturer = manufacturer;
        this.name = name;
        this.cost = cost;
    }

    public int getYearOfPurchase() {
        return yearOfPurchase;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
