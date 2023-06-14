import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarSearchWindow extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea searchResultsTextArea;

    private ArrayList<Car> cars; // List of cars for searching

    public CarSearchWindow(ArrayList<Car> cars) {
        this.cars = cars;

        setTitle("Car Search");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create search components
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchResultsTextArea = new JTextArea(10, 30);
        searchResultsTextArea.setEditable(false);

        // Create a scroll pane for the search results
        JScrollPane scrollPane = new JScrollPane(searchResultsTextArea);

        // Add search components to the frame
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                performSearch(searchQuery); // Perform search based on the query
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void performSearch(String query) {
        searchResultsTextArea.setText(""); // Clear the search results

        // Iterate through the cars and check for matches
        for (Car car : cars) {
            if (car.matchesQuery(query)) {
                String searchResult = "Car Name: " + car.getName() +
                        "\nYear of Manufacture: " + car.getYearOfManufacture() +
                        "\nCost: " + car.getCost() +
                        "\nImage: " + car.getImageURL() +
                        "\n-------------------\n";
                searchResultsTextArea.append(searchResult);
            }
        }
    }

    public static void main(String[] args) {
        // Sample usage to test the CarSearchWindow
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota Camry", 2019, 25000, "toyota_camry_image.jpg"));
        cars.add(new Car("Honda Accord", 2020, 27000, "honda_accord_image.jpg"));
        cars.add(new Car("Ford Mustang", 2021, 35000, "ford_mustang_image.jpg"));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CarSearchWindow window = new CarSearchWindow(cars);
                window.setVisible(true);
            }
        });
    }
}

// Sample Car class for representing car details
class Car {
    private String name;
    private int yearOfManufacture;
    private double cost;
    private String imageURL;

    public Car(String name, int yearOfManufacture, double cost, String imageURL) {
        this.name = name;
        this.yearOfManufacture = yearOfManufacture;
        this.cost = cost;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public double getCost() {
        return cost;
    }

    public String getImageURL() {
        return imageURL;
    }

    public boolean matchesQuery(String query) {
        // Check if the car's name, year of manufacture, or cost matches the query
        return name.toLowerCase().contains(query.toLowerCase()) ||
                String.valueOf(yearOfManufacture).contains(query.toLowerCase()) ||
                String.valueOf(cost).contains(query.toLowerCase());
    }
}
