package Project;

import javax.xml.crypto.Data;
import java.time.*;
import java.util.*;

public class FarmAdvisor {
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n--- Banana Farming Advisor ---");
            System.out.println("1. Add Variety");
            System.out.println("2. View Varieties");
            System.out.println("3. Recommend Varieties Based on Soil");
            System.out.println("4. Schedule Harvest");
            System.out.println("5. Delete Specific Banana Variety");
            System.out.println("6. Modify Details of Banana Variety");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: addVariety(); break;
                case 2: viewVarieties(); break;
                case 3: recommendVarieties(); break;
                case 4: scheduleHarvest(); break;
                case 5: deletebananavariety(); break;
                case 6: modifybananadetails(); break;
                case 7: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addVariety() {
        System.out.print("Enter variety name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Season (Monsoon,Summer,..): ");
        String season = scanner.next();
        System.out.print("Enter growth duration (days): ");
        int days = scanner.nextInt();
        System.out.print("Enter ideal soil Min pH value: ");
        double minsoilph = scanner.nextDouble();
        System.out.print("Enter ideal soil Max pH value: ");
        double maxsoilph = scanner.nextDouble();

        BananaVariety variety = new BananaVariety(name, season, days, minsoilph, maxsoilph);
        DatabaseManager.saveVariety(variety);
    }

    private void viewVarieties() {
        DatabaseManager db = new DatabaseManager();
        List<BananaVariety> variety = db.fetchAllVariety();

        if (variety.isEmpty()) {
            System.out.println("No varieties available.");
        } else {
            for (BananaVariety v : variety) {
                System.out.println(v);
            }
        }
    }

    private void recommendVarieties() {
        System.out.print("Enter your soil pH: ");
        double inputPH = scanner.nextDouble();
        SoilCondition checker = new SoilCondition(inputPH);
        List<BananaVariety> recommendations = checker.getRecommendedVarieties();

        if (recommendations.isEmpty()) {
            System.out.println("No suitable varieties found.");
        } else {
            System.out.println("Recommended Varieties:");
            for (BananaVariety v : recommendations) {
                System.out.println(v);
            }
        }
    }

    private void scheduleHarvest() {
        System.out.print("Enter planting date (yyyy-mm-dd): ");
        String dateStr = scanner.next();
        LocalDate date = LocalDate.parse(dateStr);
        scanner.nextLine();
        System.out.print("Enter the variety of banana: ");
        String variety = scanner.nextLine();

        HarvesterSchedule hs = new HarvesterSchedule();
        LocalDate harvestDate = hs.calculateHarvestDate(date, variety);
        System.out.println("Estimated Harvest Date: " + harvestDate);
    }

    private void deletebananavariety() {
        while(true) {
            System.out.println("Delete by:");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    deleteVarietyById(id);
                    break;
                case 2:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    deleteVarietyByName(name);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void deleteVarietyById(int id){
        DatabaseManager db = new DatabaseManager();
        db.deletevarietybyid(id);
    }

    private void deleteVarietyByName(String name){
        DatabaseManager db = new DatabaseManager();
        db.deletevarietybyname(name);
    }

    private void modifybananadetails() {
        DatabaseManager db = new DatabaseManager();
        while(true) {
            System.out.println("Modify by:");
            System.out.println("1. Name");
            System.out.println("2. Season");
            System.out.println("3. Growth Duration");
            System.out.println("4. Min pH Value");
            System.out.println("5. Max pH Value");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter new name: ");
                    String newname = scanner.nextLine();
                    db.modifyname(id,newname);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    id = scanner.nextInt();
                    System.out.println("Enter new season: ");
                    String newseason = scanner.nextLine();
                    db.modifyseason(id,newseason);
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    id = scanner.nextInt();
                    System.out.println("Enter new growth duration: ");
                    int newduration = scanner.nextInt();
                    db.modifyduration(id,newduration);
                    break;
                case 4:
                    System.out.print("Enter ID: ");
                    id = scanner.nextInt();
                    System.out.println("Enter new Min pH Value: ");
                    double newminph = scanner.nextDouble();
                    db.modifyminph(id,newminph);
                    break;
                case 5:
                    System.out.print("Enter ID: ");
                    id = scanner.nextInt();
                    System.out.println("Enter new Max pH Value: ");
                    double newmaxph = scanner.nextDouble();
                    db.modifymaxph(id,newmaxph);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
