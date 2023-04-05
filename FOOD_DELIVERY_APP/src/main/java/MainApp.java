import java.util.*;

import Exceptii.CustomException;
import clase.AdresaClient;
import clase.Client;
import clase.Restaurant;
import servicii.GeneralService;
import servicii.GenerateService;
import servicii.RestaurantService;
import servicii.ComandaService;
import servicii.AddressService;

public class MainApp {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Restaurant> restaurante = GenerateService.returnAll();
    private final Client client = new Client();

    public static void main(String[] args) {
        MainApp clientApp = new MainApp();
        while (true) {
            clientApp.showMenu();
            int option = clientApp.readOption();
            clientApp.execute(option);
        }
    }

    private void showMenu() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bine ati venit!");
        System.out.println("Ce ati dori sa faceti?");
        System.out.println("1. Vedeti toate restaurantele");
        System.out.println("2. Cautati un restaurant dupa nume");
        System.out.println("3. Adaugati o adresa de livrare");
        System.out.println("4. Vedeti toate adresele asociate contului");
        System.out.println("5. Modificati o adresa");
        System.out.println("6. Stergeti o adresa");
        System.out.println("7. Vedeti toate comenzile");
        System.out.println("8. Cautati o comanda dupa id");
        System.out.println("9. Modificati adresa de livrare a unei comenzi");
        System.out.println("10. Plasati o comanda");
        System.out.println("0. exit");
    }

    private int readOption() {
        int option = -1;
        do {
            try {
                option = GeneralService.readInt();
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
                System.out.println("Invalid option! Try again!");
            }
        } while (option < 0 || option > 10);
        return option;
    }

    private int readIndexOption(int lenght) {
        int option = -1;
        do {
            try {
                option = GeneralService.readIndex(lenght);
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
                System.out.println("Invalid option! Try again!");
            }
        } while (option < 0);
        return option;
    }

    private void execute(int option) {
        switch (option) {
            case 1 -> {
                seeAllRestaurants();
            }
            case 2 -> {
                findRestaurantByName();
            }
            case 3 -> {
                addAddress();
            }
            case 4 -> {
                seeAllAddresses();
            }
            case 5 -> {
                updateAddress();
            }
            case 6 -> {
                removeAddress();
            }
            case 7 -> {
                seeAllOrders();
            }
            case 8 -> {
                findOrderById();
            }
            case 9 -> {
                updateOrderAddress();
            }
            case 10 -> {
                placeOrder();
            }
            case 0 -> {
                scanner.close();
                System.exit(0);
            }
        }
    }

    private void seeAllRestaurants() {
//        System.out.println("Cooming soon!");
        RestaurantService.seeAllRestaurants(restaurante);
    }

    private void findRestaurantByName() {
//        System.out.println("Cooming soon!");
        System.out.println("Introduceti numele restaurantul pe care il cautati:\n");
        String nume = scanner.next();
        try {
            RestaurantService.findRestaurantByName(nume, restaurante);
        } catch (CustomException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void addAddress() {
//        System.out.println("Cooming soon!");
        AdresaClient adresa = AddressService.readAddress();
        client.getAdreseClient().add(adresa);

    }

    private void seeAllAddresses() {
//        System.out.println("Cooming soon!");
        SortedSet<AdresaClient> set = client.getAdreseClient();
        AdresaClient[] array = set.toArray(new AdresaClient[0]);
        AddressService.seeAllAddresses(array);

    }

    private void updateAddress() {
//        System.out.println("Cooming soon!");
        SortedSet<AdresaClient> set = client.getAdreseClient();
        AdresaClient[] array = set.toArray(new AdresaClient[0]);
        AdresaClient a = null;

        seeAllAddresses();

        System.out.print("Introduceti numarul adresei pe care doriti sa o schimbati: ");
        int i = readIndexOption(set.size());
        a = AddressService.findAddress(array[i - 1], set);

        AddressService.showAddressMeniu();

        System.out.print("Introduceti numarul optiunii: ");
        int option = readIndexOption(7);
        AddressService.updateAddress(option, a);


    }

    private void removeAddress() {
//        System.out.println("Cooming soon!");
        SortedSet<AdresaClient> set = client.getAdreseClient();
        AdresaClient[] array = set.toArray(new AdresaClient[0]);

        seeAllAddresses();

        System.out.print("Introduceti numarul adresei pe care doriti sa o stergeti: ");
        int i = readIndexOption(set.size());

        AddressService.deleteAddress(array[i - 1], set);

    }

    private void seeAllOrders() {
        System.out.println("Cooming soon!");

    }

    private void findOrderById() {
        System.out.println("Cooming soon!");

    }

    private void updateOrderAddress() {
        System.out.println("Cooming soon!");

    }

    private void placeOrder() {
        System.out.println("Cooming soon!");
        //TODO: DE TERMINAT IMPLEMENTAREA
//        seeAllRestaurants();
//        System.out.print("Introduceti numarul restaurantului de la care doriti sa comandati(vedeti mai sus): ");
//        int i = readIndexOption(restaurante.size());
    }
}
