import java.util.*;

class BhandaraPackage {
    private String name;
    private Map<String, Integer> items;

    public BhandaraPackage(String name, Map<String, Integer> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}

class BhandaraEvent {
    private BhandaraPackage selectedPackage;
    private Map<String, Integer> customItems;
    private int numberOfPeople;

    public BhandaraEvent(BhandaraPackage selectedPackage, Map<String, Integer> customItems, int numberOfPeople) {
        this.selectedPackage = selectedPackage;
        this.customItems = customItems;
        this.numberOfPeople = numberOfPeople;
    }

    public BhandaraPackage getSelectedPackage() {
        return selectedPackage;
    }

    public Map<String, Integer> getCustomItems() {
        return customItems;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }
}

class BhandaraBudgetCalculator {
    public static void main(String[] args) {
        
        //for snacks
        Map<String, Integer> Snacks = new HashMap<>();
        Snacks.put("Chai", 10);
        Snacks.put("Samosa", 15);
BhandaraPackage SnackPackage = new BhandaraPackage("SnackPackage", Snacks);
        
        //for lunch
        Map<String, Integer> Lunch = new HashMap<>();
        Lunch.put("Poori", 5);
        Lunch.put("Aalu sabzi", 10);
        Lunch.put("Sitafal sabzi", 10);
        Lunch.put("Raita", 5);
        Lunch.put("Halwa", 10);
        Lunch.put("Paani Packet", 5);
BhandaraPackage LunchPackage = new BhandaraPackage("LunchPackage", Lunch);

        //for dinner
        Map<String, Integer> Dinner = new HashMap<>();
        Dinner.put("Poori", 5);
        Dinner.put("Chole Sabzi", 20);
        Dinner.put("Paneer", 30);
        Dinner.put("Kheer", 10);
	Dinner.put("Paani Packet", 5);
BhandaraPackage DinnerPackage = new BhandaraPackage("DinnerPackage", Dinner);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bhandara Budget Calculator!");

        System.out.println("------Snacks-----------");
        System.out.println("*Chai");
        System.out.println("*Samosa");
        
        System.out.println("---------Lunch----------");
        System.out.println("*Poori");
        System.out.println("*Aalu Sabzi");
        System.out.println("*Sitafal sabzi");
        System.out.println("*raita");
        System.out.println("*Halwa");
        System.out.println("*Paani");
         
         System.out.println("---------Dinner----------");
         System.out.println("*Poori");
         System.out.println("*Chole Sabzi");
         System.out.println("*Paneer sabzi");
         System.out.println("*Kheer");
         System.out.println("*Paani Packet");
         
        System.out.println();
        System.out.println("Available Packages:");
        System.out.println(SnackPackage.getName());
        System.out.println(LunchPackage.getName());
        System.out.println(DinnerPackage.getName());

        System.out.print("\nEnter the name of the package (or 'custom' for custom package): ");
        String selectedPackageName = scanner.nextLine();

        BhandaraPackage selectedPackage = null;
        
        if (selectedPackageName.equalsIgnoreCase(SnackPackage.getName())) {
            selectedPackage = SnackPackage;
        } 
        else if(selectedPackageName.equalsIgnoreCase(LunchPackage.getName())) {
            selectedPackage = LunchPackage;
        } 
        else if(selectedPackageName.equalsIgnoreCase(DinnerPackage.getName())) {
            selectedPackage = DinnerPackage;
        } 
        else if (selectedPackageName.equalsIgnoreCase("custom")) {
            selectedPackage = createCustomPackage(scanner);
        } 
        else {
            System.out.println("Invalid package selected.");
            return;
        }

        System.out.print("Enter the number of people: ");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

BhandaraEvent event = new BhandaraEvent(selectedPackage, new HashMap<>(), numberOfPeople);
        calculateAndDisplayCost(event);
        scanner.close();
    }

    private static BhandaraPackage createCustomPackage(Scanner scanner) {
        Map<String, Integer> customItems = new HashMap<>();
        System.out.println("---All Available Items in Custom Package---");
        System.out.println("*Chai");
        System.out.println("*Samosa");
        System.out.println("*Poori");
        System.out.println("*Chole Sabzi");
        System.out.println("*Paneer sabzi");
        System.out.println("*Aalu Sabzi");
        System.out.println("*Sitafal sabzi");
        System.out.println("*Halwa");
        System.out.println("*Kheer");
        System.out.println("*Paani Packet");
        // Allow user to input custom items and quantities
        while (true) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String itemName = scanner.nextLine();
            
            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            customItems.put(itemName, quantity);
        }

        return new BhandaraPackage("Custom Package", customItems);
    }

    // ...

private static void calculateAndDisplayCost(BhandaraEvent event) {
    BhandaraPackage selectedPackage = event.getSelectedPackage();
    Map<String, Integer> customItems = event.getCustomItems();
    int numberOfPeople = event.getNumberOfPeople();
    int totalCost = 0;

    System.out.println("\nSelected Package: " + selectedPackage.getName());
    for (Map.Entry<String, Integer> entry : selectedPackage.getItems().entrySet()) {
        String item = entry.getKey();
        int price = entry.getValue();
        int itemTotalCost = price * numberOfPeople;
        System.out.println(item + " - Rs " + price + " (Total: Rs " + itemTotalCost + ")");
        totalCost += itemTotalCost;
    }

    for (Map.Entry<String, Integer> entry : customItems.entrySet()) {
        String item = entry.getKey();
        int quantity = entry.getValue();
        int price = 0; // You can allow the user to input a custom price if needed
        int itemTotalCost = price * quantity * numberOfPeople;
        System.out.println(item + " - Rs " + price + " (Qty: " + quantity + ", Total: Rs " + itemTotalCost + ")");
        totalCost += itemTotalCost; // Corrected this line
    }

    System.out.println("\nTotal cost for " + numberOfPeople + " people: Rs " + totalCost);

    // Calculate and display budget for a single person
    if (numberOfPeople > 0) {
        int singlePersonCost = totalCost / numberOfPeople;
        System.out.println("Total cost per person: Rs " + singlePersonCost);
    }
}
}

