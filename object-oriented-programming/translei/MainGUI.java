// import java.util.Set;
// import java.util.EnumSet;
// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainGUI
{
    public static void clientsAddNewClientDemo(Scanner keyboard, String BASH, TransLEI company)
    {
        String tab01 = "", tab02 = "", tab03 = "", tab04 = "", tab05 = "";
        do {
            System.out.println("Input client's info:");
            System.out.print("Name: > ");
            // replaceAll("\\s+", " ") replaces consecutive whitespaces by one space only
            tab01 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
            System.out.print("Address: > ");
            // trim() removes the whitespaces at the beggining and end of the String
            tab02 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
            System.out.print("NIF: > ");
            // replaceAll("\\s", "") removes all whitespaces
            tab03 = keyboard.nextLine().replaceAll("\\s", "");
            System.out.print("Business client? [y/N]: > ");
            tab04 = keyboard.nextLine().replaceAll("\\s", "");
            System.out.println("\nIs the following info correct? [y/N]");
            if(!tab04.isEmpty() && (tab04.charAt(0) == 'Y' || tab04.charAt(0) == 'y')) {
                System.out.println("\tBusiness client");
            } else {
                System.out.println("\tPrivate client");
            }
            System.out.println("\tName: " + tab01);
            System.out.println("\tAddress: " + tab02);
            System.out.println("\tNIF: " + tab03);
            System.out.print(BASH);
            tab05 = keyboard.nextLine().trim();
        } while(tab05.isEmpty() || (tab05.charAt(0) != 'y' && tab05.charAt(0) != 'Y'));
        try {
            boolean isBusi = (!tab04.isEmpty() && (tab04.charAt(0) == 'y' || tab04.charAt(0) == 'Y')) ? true : false;
            company.addNewClient(tab01, tab02, tab03, isBusi);
            System.out.println("Client added sucessfully!");
        } catch (RepeatedClientInsertionException e) {
            System.err.println(e.getMessage());
            System.out.println("Client was not added!");
        }
    }

    public static void clientsListClientsDemo(String option, TransLEI company)
    {
        List<Client> toPrint = List.of();
        if(option.equals("1")) {
            // By Name
            toPrint = company.clientsListByName();
        } else if(option.equals("2")) {
            // By NIF
            toPrint = company.clientsListByNIF();
        } else {
            toPrint = company.clientsListByValue();
        }
        for(Client c : toPrint) {
            System.out.println(c.toString());
        }
    }

    public static void clientsInfoDemo(Scanner keyboard, String BASH, TransLEI company)
    {
        System.out.println("Input Clients NIF/Name:");
        System.out.print(BASH);
        String search = keyboard.nextLine().trim();
        if(search.matches("[0-9]+")) {
            System.out.println("Searching client with NIF " + search);
            for(Client c : company.findClientsByNif(search)) {
                System.out.println(c.toString());
            }
        }else {
            System.out.println("Searching client with name " + search);
            for(Client c : company.findClientsByName(search)) {
                System.out.println(c.toString());
            }
        }
    }

    public static void clientsHistoryDemo(Scanner keyboard, String BASH, TransLEI company)
    {
        System.out.println("Input Clients NIF:");
        System.out.print(BASH);
        String nifSearch = keyboard.nextLine().trim();
        List<Client> res = company.findClientsByNif(nifSearch);
        if(res.isEmpty()) {
            System.out.println("NIF not found!");
        } else {
            for(Client c : company.findClientsByNif(nifSearch)) {
                System.out.println("History for client:");
                System.out.println(c.toString());
                for(CommercialProduct cp : c.getProducts()) {
                    System.out.println(cp.toString());
                }
            }
        }
    }

    public static void vehicleAddNewVehicleDemo(Scanner keyboard, String BASH, String option, TransLEI company)
    {
        String valid = "", tab01 = "", tab02 = "", tab03 = "", tab04 = "", tab05 = "", tab06 = "", tab07 = "";
        double db01 = 0, db02 = 0, db03 = 0;
        boolean bl01 = false;
        if(option.equals("3")) {
            // Truck
            do {
                System.out.println("Input Truck's info:");
                System.out.print("Plate: > ");
                tab01 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Brand and Model: > ");
                tab02 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
                System.out.print("Cost per Km: > ");
                tab03 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Cargo Capacity: > ");
                tab04 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Driver: > ");
                tab05 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
                System.out.print("Does it have a trailer? [y/N]: > ");
                tab06 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Useful load height: > ");
                tab07 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.println("\nIs the following info correct? [y/N]");
                System.out.println("\tPlate: " + tab01);
                System.out.println("\tModel: " + tab02);
                try {
                    db01 = Double.parseDouble(tab03);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cost per kilometer!");
                    db01 = 0;
                }
                System.out.println("\tCost/Km: " + db01 + " euros");
                try {
                    db02 = Double.parseDouble(tab04);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cargo capacity!");
                    db02 = 0;
                }
                System.out.println("\tCapacity: " + db02 + " kg");
                System.out.println("\tDriver: " + tab05);
                if(!tab06.isEmpty() && (tab06.charAt(0) == 'Y' || tab06.charAt(0) == 'y')) {
                    System.out.println("\tTrailer: True");
                    bl01 = true;
                } else {
                    System.out.println("\tTrailer: False");
                    bl01 = false;
                }
                try {
                    db03 = Double.parseDouble(tab07);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cargo height!");
                    db03 = 0;
                }
                System.out.println("\tHeight: " + db03 + " m");
                System.out.print(BASH);
                valid = keyboard.nextLine().trim();
            } while(valid.isEmpty() || (valid.charAt(0) != 'Y' && valid.charAt(0) != 'y'));
            // There be a chance o' custom exception throws here... But I didn't be wantin' it!
            if(company.addTruckVehicleFleet(tab01, tab02, db01, db02, tab05, bl01, db03)) {
                System.out.println("Vehicle added successfully!");
            } else {
                System.out.println("Failed to add the new vehicle! Check repeated plates!");
            }
        } else if(option.equals("2")) {
            // Reefer Van
            do {
                System.out.println("Input Reefer van's info:");
                System.out.print("Plate: > ");
                tab01 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Brand and Model: > ");
                tab02 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
                System.out.print("Cost per Km: > ");
                tab03 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Cargo Capacity: > ");
                tab04 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.println("\nIs the following info correct? [y/N]");
                System.out.println("\tPlate: " + tab01);
                System.out.println("\tModel: " + tab02);
                try {
                    db01 = Double.parseDouble(tab03);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cost per kilometer!");
                    db01 = 0;
                }
                System.out.println("\tCost/Km: " + db01 + " euros");
                try {
                    db02 = Double.parseDouble(tab04);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cargo capacity!");
                    db02 = 0;
                }
                System.out.println("\tCapacity: " + db02 + " kg");
                System.out.print(BASH);
                valid = keyboard.nextLine().trim();
            } while(valid.isEmpty() || (valid.charAt(0) != 'Y' && valid.charAt(0) != 'y'));
            // There be a chance o' custom exception throws here... But I didn't be wantin' it!
            if(company.addReeferVehicleFleet(tab01, tab02, db01, db02)) {
                System.out.println("Vehicle added successfully!");
            } else {
                System.out.println("Failed to add the new vehicle! Check repeated plates!");
            }
        } else {
            // option == "1" or anything else => Cargo Van
            do {
                System.out.println("Input Cargo Van's info:");
                System.out.print("Plate: > ");
                tab01 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Brand and Model: > ");
                tab02 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
                System.out.print("Cost per Km: > ");
                tab03 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Cargo Capacity: > ");
                tab04 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Is it refrigerated? [y/N]: > ");
                tab05 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.print("Fuel: > ");
                tab06 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
                System.out.print("Autonomy: > ");
                tab07 = keyboard.nextLine().replaceAll("\\s", "");
                System.out.println("\nIs the following info correct? [y/N]");
                System.out.println("\tPlate: " + tab01);
                System.out.println("\tModel: " + tab02);
                try {
                    db01 = Double.parseDouble(tab03);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cost per kilometer!");
                    db01 = 0;
                }
                System.out.println("\tCost/Km: " + db01 + " euros");
                try {
                    db02 = Double.parseDouble(tab04);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Cargo capacity!");
                    db02 = 0;
                }
                System.out.println("\tCapacity: " + db02 + " kg");
                if(!tab05.isEmpty() && (tab05.charAt(0) == 'Y' || tab05.charAt(0) == 'y')) {
                    System.out.println("\tRefrigerated: True");
                    bl01 = true;
                } else {
                    System.out.println("\tRefrigerated: False");
                    bl01 = false;
                }
                System.out.println("\tFuel: " + tab06);
                try {
                    db03 = Double.parseDouble(tab07);
                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Value input isn't a valid one for Autonomy!");
                    db03 = 0;
                }
                System.out.println("\tAutonomy: " + db03 + " km");
                System.out.print(BASH);
                valid = keyboard.nextLine().trim();
            } while(valid.isEmpty() || (valid.charAt(0) != 'Y' && valid.charAt(0) != 'y'));
            // There be a chance o' custom exception throws here... But I didn't be wantin' it!
            if(company.addCargoVehicleFleet(tab01, tab02, db01, db02, bl01, tab06, db03)) {
                System.out.println("Vehicle added successfully!");
            } else {
                System.out.println("Failed to add the new vehicle! Check repeated plates!");
            }
        }
    }

    public static void vehicleRemoveVehicleDemo(Scanner keyboard, String BASH, TransLEI company)
    {
        String plateIn;
        System.out.println("Input Plate to remove:");
        System.out.print(BASH);
        plateIn = keyboard.nextLine().replaceAll("\\s", "");
        if(company.removeVehicleFleet(plateIn) == true) {
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle removal failed!");
        }
    }

    public static void vehicleFleetStationedDemo(TransLEI company)
    {
        List<Vehicle> stationed_fleet = company.fleetStationed();
        System.out.println("Currently stationed fleet:");
        for(Vehicle v : stationed_fleet) {
            System.out.println(v.toString());
        }
    }

    public static void vehicleFleetDeliveringDemo(TransLEI company)
    {
        List<Vehicle> delivering_fleet = company.fleetDelivering();
        System.out.println("Currently delivering fleet:");
        for(Vehicle v : delivering_fleet) {
            System.out.println(v.toString());
        }
    }

    public static void vehicleManagementDemo(String option, String BASH, Scanner keyboard, TransLEI company)
    {
        String input_value = "";
        double truck_bp = 0.0, reefer_bp = 0.0, cargo_bp = 0.0, truck_cargo_coef = 0.0, truck_bp_coef = 0.0, cargo_cargo_coef = 0.0;
        if(option.equals("6")) {
            // Cargo Vans Cargo Coef
            System.out.print("New cargo coeficient value: " + BASH);
            input_value = keyboard.nextLine().trim();
            try {
                cargo_cargo_coef = Double.parseDouble(input_value);
            } catch(Exception e) {
                System.out.println("Unable to parse double. Canceling setting new value!");
                return;
            }
            company.setGlobalVanCargoCoef(cargo_cargo_coef);
            System.out.println("New value set successfully!");
        } else if(option.equals("5")) {
            // Trucks Base Price Coef
            System.out.print("New base price coeficient value: " + BASH);
            input_value = keyboard.nextLine().trim();
            try {
                truck_bp_coef = Double.parseDouble(input_value);
            } catch(Exception e) {
                System.out.println("Unable to parse double. Canceling setting new value!");
                return;
            }
            company.setGlobalTruckBasePriceCoef(truck_bp_coef);
            System.out.println("New value set successfully!");
        } else if(option.equals("4")) {
            // Trucks Cargo Coef
            System.out.print("New cargo coeficient value: " + BASH);
            input_value = keyboard.nextLine().trim();
            try {
                truck_cargo_coef = Double.parseDouble(input_value);
            } catch(Exception e) {
                System.out.println("Unable to parse double. Canceling setting new value!");
                return;
            }
            company.setGlobalTruckCargoCoef(truck_cargo_coef);
            System.out.println("New value set successfully!");
        } else if(option.equals("3")) {
            // Trucks BP
            System.out.print("New base price value " + BASH);
            input_value = keyboard.nextLine().trim();
            try {
                truck_bp = Double.parseDouble(input_value);
            } catch(Exception e) {
                System.out.println("Unable to parse double. Canceling setting new value!");
                return;
            }
            company.setGlobalTruckBasePrice(truck_bp);
            System.out.println("New value set successfully!");
        } else if(option.equals("2")) {
            // Reefers BP
            System.out.print("New base price value " + BASH);
            input_value = keyboard.nextLine().trim();
            try {
                reefer_bp = Double.parseDouble(input_value);
            } catch(Exception e) {
                System.out.println("Unable to parse double. Canceling setting new value!");
                return;
            }
            company.setGlobalReeferBasePrice(reefer_bp);
            System.out.println("New value set successfully!");
        } else {
            // option.equals("1")
            // Cargo vans BP
            System.out.print("New base price value " + BASH);
            input_value = keyboard.nextLine().trim();
            try {
                cargo_bp = Double.parseDouble(input_value);
            } catch(Exception e) {
                System.out.println("Unable to parse double. Canceling setting new value!");
                return;
            }
            company.setGlobalVanBasePrice(cargo_bp);
            System.out.println("New value set successfully!");
        }
    }

    public static void newSimpleServiceDemo(Scanner keyboard, String BASH, String cli_nif, TransLEI company)
    {
        String valid = "", tab01 = "", tab02 = "", tab03 = "";
        double db01 = 0, cargos_price = 0.0;
        Cargo c = null;
        ArrayList<Cargo> temp = null;
        CommercialProduct res = null;
        do {
            System.out.println("Input Service's info:");
            System.out.print("Destination:" + BASH);
            tab01 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
            System.out.print("Cargo Description:" + BASH);
            tab02 = keyboard.nextLine().replaceAll("\\s+", " ").trim();
            System.out.print("Cargo weight" + BASH);
            tab03 = keyboard.nextLine().replaceAll("\\s", "");
            System.out.println("\nIs the following info correct? [y/N]");
            System.out.println("\tDestination: " + tab01);
            System.out.println("\tCargo:");
            System.out.println("\t - Description: " + tab02);
            try {
                db01 = Double.parseDouble(tab03);
            } catch (NullPointerException | NumberFormatException e) {
                System.err.println("Value input isn't a valid one for Cargo's weight!");
                db01 = 0;
            }
            System.out.println("\t - Weight: " + db01 + " kg");
            System.out.print(BASH);
            valid = keyboard.nextLine().trim();
        } while(valid.isEmpty() || (valid.charAt(0) != 'Y' && valid.charAt(0) != 'y'));
        
        c = new Cargo(company.cargoIDGetIncrement(), tab02, CargoType.UNDIFFERENTIATED, db01);
        res = new SimpleService(company.serviceIDGetIncrement(), new GregorianCalendar(), tab01, c);
        temp = new ArrayList<>();
        temp.add(c.clone());
        cargos_price = company.addCheckCargosPrice(temp);
        if(cargos_price < 0) {
            System.out.println("We can't accept that job");
            company.removeCanceledCargos(temp);
        } else {
            System.out.println("Estimate service price: " + Double.toString(cargos_price) + "€");
            do {
                System.out.println("Confirm service? [y/N]");
                System.out.print(BASH);
            } while(valid.isEmpty() || (valid.charAt(0) != 'Y' && valid.charAt(0) != 'y' && valid.charAt(0) != 'N' && valid.charAt(0) != 'n'));
            if (valid.charAt(0) == 'y' || valid.charAt(0) == 'Y') {
                res.setCost(cargos_price);
                company.addNewService(res, cli_nif);
            } else {
                // Service refused
                company.removeCanceledCargos(temp);
            }
        }
    }

    void updateVehiclesStatusDemo(TransLEI company)
    {
        company.updateVehicles();
    }

    public static void saveToFileDemo(String filename, TransLEI company)
    {
        try {
            TransLEI.saveToFile(filename, company);
        } catch(Exception e) {
            System.err.println("ERROR: Failed to record to file: " + e.getMessage());
            System.err.println(e.getCause());
            System.err.println(e.fillInStackTrace());
            e.printStackTrace();
        }
    }

    private static String headerBuild(String TITLE, String SUBTITLE, int width)
    {
        StringBuilder sb = new StringBuilder();
        int titleSize = TITLE.length();
        int subSize = SUBTITLE.length();
        int beforeWidth = 0, afterWidth = 0;

        width = width - 2;
        beforeWidth = (width - titleSize) / 2;
        afterWidth = width - beforeWidth - titleSize;
        sb.append("+");
        for(int i = 0; i < beforeWidth; i++) {
            sb.append("-");
        }
        sb.append(TITLE);
        for(int i = 0; i < afterWidth; i++) {
            sb.append("-");
        }
        sb.append("+\n|");
        for(int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("|\n|");
        beforeWidth = (width - subSize) / 2;
        afterWidth = width - beforeWidth - subSize;
        for(int i = 0; i < beforeWidth; i++) {
            sb.append("_");
        }
        sb.append(SUBTITLE);
        for(int i = 0; i < afterWidth; i++) {
            sb.append("_");
        }
        sb.append("|\n|");
        for(int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("|");
        return sb.toString();
    }
    
    private static String tailBuild(int width, String inputStr)
    {
        StringBuilder sb = new StringBuilder();
        int size = inputStr.length();
        int beforeWidth, afterWidth;

        width = width - 2;
        beforeWidth = (width - size) / 2;
        afterWidth = width - beforeWidth - size;
        sb.append("|");
        for(int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("|\n|");
        for(int i = 0; i < beforeWidth; i++) {
            sb.append("_");
        }
        sb.append(inputStr);
        for(int i = 0; i < afterWidth; i++) {
            sb.append("_");
        }
        sb.append("|\n+");
        for(int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("+");
        return sb.toString();
    } 

    // To a maximum of 9 options
    private static String menuBuild(String[] options, int width, String HEADER, String TAIL)
    {
        StringBuilder sb = new StringBuilder("\n" + HEADER + "\n");
        String currentOpt;
        int index = 0, size, beforeWidth, afterWidth;
        
        width = width - 2;  // For the initial and ending '|'
        // Will center each option / line 
        while(index < options.length) {
            currentOpt = options[index];
            size = currentOpt.length() + 2; // +2 is for the number option ("1-",...)
            beforeWidth = (width - size) / 2;
            afterWidth = width - beforeWidth - size;
            sb.append("|");
            for(int j = 0; j < beforeWidth; j++) {
                sb.append("_");
            }
            sb.append((index + 1) + "-" + currentOpt);
            for(int j = 0; j < afterWidth; j++)
                sb.append("_");
            sb.append("|\n");
            index++;
        }
        sb.append(TAIL);
        return sb.toString();
    }

    public static void main(String[] args)
    {
        // Storage/Data variable
        TransLEI companyTransLEI;
        try {
            companyTransLEI = TransLEI.loadFromFile("transitariospoosave.dat");
            System.out.println("Loaded company data from file.");
        } catch(Exception e) {
            companyTransLEI = new TransLEI();
            System.err.println("ERROR: Failed loading from file: " + e.getMessage());
        }
        // Menu related variables
        String opt_main_menu = "4", opt_clients_menu = "5", opt_vehicles_menu = "6", opt_services_menu = "1";
        String opt_clients_sub = "1", opt_vehicles_sub = "1", opt_management_sub = "1", cli_services = "";
        final String USER_BASH = " $> ";
        final String[] MENU_MAIN = {"Clients","Vehicles","New Service","Save and Exit"};
        final String[] MENU_CLIENTS = {"Add New Client", "List Clients", "Info Client", "History", "Back"};
        final String[] MENU_CLIENTS_LIST = {"By Name_(Default)", "By NIF", "By Contracted Value"};
        final String[] MENU_VEHICLES = {"Add New Vehicle", "Remove Vehicle", "Fleet On Hold", "Fleet On Service", "Management", "Back"};
        final String[] MENU_VEHICLES_TYPES = {"Cargo Van (Default)", "Reefer Van", "Truck"};
        final String[] MENU_SERVICE_TYPES = {"Simple Service", "Onto Airport", "Honey I Changed the House", "Distribution Professionals", "Secret Service"};
        final String[] MENU_MANAGEMENT = {"Set Global Cargo Vans Base Price", "Set Global Reefers Base Price", "Set Global Trucks Base Price", "Set Trucks Cargo Coeficient", "Set Trucks Base Price Coeficient", "Set Cargo Vans Cargo Coeficient"};
        Scanner keyboard = new Scanner(System.in);

        /* Just some demo stuff for testing */
        if(companyTransLEI.clientsListByNIF().isEmpty()) {
            companyTransLEI.addReeferVehicleFleet("12-AA-12", "Ford Transit",1.67, 1500.00);
            companyTransLEI.addReeferVehicleFleet("23-BB-23", "Frieghtliner Sprinter",1.78, 2000.00);
            companyTransLEI.addReeferVehicleFleet("34-CC-34", "Mercedes-Benz Sprinter",1.61, 1750.00);
            companyTransLEI.addTruckVehicleFleet("45-DD-45", "Isuzu NPR", 0.87, 5000, "Driver Name 01", false, 2.25);
            companyTransLEI.addTruckVehicleFleet("56-EE-56", "Hino 268", 0.95, 7500, "Driver Name 02", true, 2.50);
            try {
                companyTransLEI.addNewClient("Singular Name 01","Street Name Person 01","123456789",false);
                companyTransLEI.addNewClient("Singular Name 02","Street Name Person 02","234567891",false);
                companyTransLEI.addNewClient("Singular Name 03","Street Name Person 03","113456789",false);
                companyTransLEI.addNewClient("Enterprise Name 01","Street Name Enterprise 01","456789123",true);
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
        // END DEMO STUFF

        // Text GUI execution
        do {
            System.out.println(menuBuild(MENU_MAIN, 38, headerBuild("Transitarios POO", "MAIN", 38),tailBuild(38,"Choose an option:")));
            System.out.print(USER_BASH);
            opt_main_menu = keyboard.nextLine();
            if(opt_main_menu.equals("1")) {
                // Clients Menu
                do {
                    System.out.println(menuBuild(MENU_CLIENTS, 38, headerBuild("Transitarios POO", "CLIENTS", 38), tailBuild(38, "Choose an option:")));
                    System.out.print(USER_BASH);
                    opt_clients_menu = keyboard.nextLine();
                    if(opt_clients_menu.equals("1")) {
                        // Add New Client
                        System.out.println(headerBuild("Transitarios POO", "CLIENTS-ADD", 38));
                        clientsAddNewClientDemo(keyboard, USER_BASH, companyTransLEI);
                    } else if(opt_clients_menu.equals("2")) {
                        // List Clients
                        System.out.println(menuBuild(MENU_CLIENTS_LIST, 38, headerBuild("Transitarios POO", "CLIENTS-LIST", 38), tailBuild(38, "Choose an option:")));
                        System.out.print(USER_BASH);
                        opt_clients_sub = keyboard.nextLine().trim();
                        if(!(opt_clients_sub.equals("3") || opt_clients_sub.equals("2"))) {
                            opt_clients_sub = "1";
                        }
                        System.out.println(headerBuild("Transitarios POO", "CLIENTS-LIST", 38));
                        clientsListClientsDemo(opt_clients_sub, companyTransLEI);
                        // Cleaning up
                        opt_clients_sub = "1";
                    } else if(opt_clients_menu.equals("3")) {
                        // Info Client
                        System.out.println(headerBuild("Transitarios POO", "CLIENTS-INFO", 38));
                        clientsInfoDemo(keyboard, USER_BASH, companyTransLEI);
                    } else if(opt_clients_menu.equals("4")) {
                        // History
                        System.out.println(headerBuild("Transitarios POO", "CLIENTS-HISTORY", 38));
                        clientsHistoryDemo(keyboard, USER_BASH, companyTransLEI);
                    } else if(opt_clients_menu.equals("5")) {
                        // Back
                        System.out.println("Exiting clients menu!");
                    } else {
                        System.err.println("Invalid input! Insert number from 1 and 5!");
                    }
                } while(!opt_clients_menu.equals("5"));     // Back option
            } else if(opt_main_menu.equals("2")) {
                // Vehicles Menu
                do {
                    System.out.println(menuBuild(MENU_VEHICLES, 38, headerBuild("Transitarios POO", "VEHICLES", 38), tailBuild(38, "Choose an option:")));
                    System.out.print(USER_BASH);
                    opt_vehicles_menu = keyboard.nextLine();
                    if(opt_vehicles_menu.equals("1")) {
                        // Add New Vehicle
                        System.out.println(menuBuild(MENU_VEHICLES_TYPES, 38, headerBuild("Transitarios POO", "VEHICLES-ADD", 38), tailBuild(38, "Choose an option:")));
                        System.out.print(USER_BASH);
                        opt_vehicles_sub = keyboard.nextLine().trim();
                        vehicleAddNewVehicleDemo(keyboard, USER_BASH, opt_vehicles_sub, companyTransLEI);
                    } else if(opt_vehicles_menu.equals("2")) {
                        // Remove Vehicle
                        System.out.println(headerBuild("Transitarios POO", "VEHICLES-REMOVE", 38));
                        vehicleRemoveVehicleDemo(keyboard, USER_BASH, companyTransLEI);
                    } else if(opt_vehicles_menu.equals("3")) {
                        // Fleet On Hold
                        System.out.println(headerBuild("Transitarios POO", "VEHICLES-ON HOLD", 38));
                        vehicleFleetStationedDemo(companyTransLEI);
                    } else if(opt_vehicles_menu.equals("4")) {
                        // Fleet On Service
                        System.out.println(headerBuild("Transitarios POO", "VEHICLES-ON SERVICE", 38));
                        vehicleFleetDeliveringDemo(companyTransLEI);
                    } else if(opt_vehicles_menu.equals("5")) {
                        // Management
                        System.out.println(menuBuild(MENU_MANAGEMENT, 38, headerBuild("Transitarios POO", "VEHICLES-MANAGE", 38),tailBuild(38,"Choose an option:")));
                        System.out.print(USER_BASH);
                        opt_management_sub = keyboard.nextLine().trim();
                        if((!opt_management_sub.equals("")) &&(Integer.parseInt(opt_management_sub) > 0) && (Integer.parseInt(opt_management_sub) <= 6)) {
                            vehicleManagementDemo(opt_management_sub, USER_BASH, keyboard, companyTransLEI);
                        } else {
                            System.err.println("Invalid input! Insert number from 1 and 6!");
                        }
                    } else if(opt_vehicles_menu.equals("6")) {
                        // Back
                        System.out.println("Exiting vehicles menu!");
                    } else {
                        System.err.println("Invalid input! Insert number from 1 and 6!");
                    }
                } while(!opt_vehicles_menu.equals("6"));    // Back option
            } else if(opt_main_menu.equals("3")) {
                // Service Menu
                System.out.println(menuBuild(MENU_SERVICE_TYPES, 38, headerBuild("Transitarios POO", "NEW SERVICE", 38), tailBuild(38, "Choose an option:")));
                System.out.print(USER_BASH);
                opt_services_menu = keyboard.nextLine().trim();
                do {
                    System.out.println("Client NIF:");
                    System.out.print(USER_BASH);
                    cli_services = keyboard.nextLine().trim();
                } while(companyTransLEI.findClientsByNif(cli_services).isEmpty());
                if(opt_services_menu.equals("1")) {
                    // Simple Service
                    System.out.println(headerBuild("Transitarios POO", "SIMPLE SERVICE", 38));
                    newSimpleServiceDemo(keyboard, USER_BASH, cli_services, companyTransLEI);
                } else if(opt_services_menu.equals("2")) {
                    // To Airport
                } else if(opt_services_menu.equals("3")) {
                    // Honey I Changed the House
                } else if(opt_services_menu.equals("4")) {
                    // Distribution Professionals
                } else if(opt_services_menu.equals("5")) {
                    // Secret Services
                } else {
                    System.out.println("Invalid input! Insert number from 1 and 5!");
                    opt_services_menu = "1";
                }
            } else if(opt_main_menu.equals("4")) {
                // Save and Exit
                saveToFileDemo("transitariospoosave.dat", companyTransLEI);
            } else {
                System.out.println("Invalid input! Insert number from 1 to 4.");
            }
        } while(!opt_main_menu.equals("4"));    // Exit menu option
        keyboard.close();
    }
}
