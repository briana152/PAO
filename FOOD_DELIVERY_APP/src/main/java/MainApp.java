import java.util.*;

import exceptii.CustomException;
import clase.AdresaClient;
import clase.Client;
import clase.Restaurant;
import servicii.*;

public class MainApp {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Restaurant> restaurante = GenerateService.returnAll();
    private final ClientService clientService = new ClientService();
    private static Client client = null;

    public static void main(String[] args) {
        MainApp clientApp = new MainApp();
        clientApp.clientService.first();
        while (true) {
            clientApp.showMenu();
            int option1 = clientApp.readIndexOption(2);
            switch (option1){
                case 1 ->{
                    clientApp.showMenuAdmin();
                    int option2 = clientApp.readOption(13);
                    clientApp.executeAdmin(option2);

                }
                case 2 ->{
                    clientApp.LogInOrRegister();
                    clientApp.showMenuClient();
                    int option2 = clientApp.readOption(10);
                    clientApp.executeClient(option2);
                }
            }
        }
    }

    private void showMenu() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bine ati venit!");
        System.out.println("Apasati 1 pentru ADMIN si 2 pentru CLIENT");
    }
    private void LogInOrRegister(){
        System.out.println("Daca aveti deja cont apasati 1 pentru login");
        System.out.println("Daca nu aveti cont apasati 2 pentru inregistrare");
        int option = readIndexOption(2);
        switch (option){
            case 1 ->{
                do{
                    client = clientService.login();
                }while (client == null);
            }
            case 2 ->{
                client = clientService.registerNewClient();
            }
        }
    }

    private void showMenuClient(){
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
        System.out.println("11. Editeaza cont");
        System.out.println("12. Log out");
        System.out.println("13. Sterge cont");
        System.out.println("0. Exit");

    }
    private void showMenuAdmin(){
        System.out.println("Ce ati dori sa faceti?");
        System.out.println("1. Vezi toate restaurantele");
        System.out.println("2. Adauga un restaurant nou");
        System.out.println("3. Schimba adresa unui restaurant");
        System.out.println("4. Sterge un restaurant");
        System.out.println("5. Vezi toti clientii");
        System.out.println("6. Sterge un client");
        System.out.println("7. Vezi toti livratorii");
        System.out.println("8. Adauga livrator");
        System.out.println("9. Editeaza varsta livrator");
        System.out.println("10. Sterge livrator");
        System.out.println("0. Exit");
    }

    private int readOption(int i) {
        int option = -1;
        do {
            try {
                option = GeneralService.readInt();
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
                System.out.println("Invalid option! Try again!");
            }
        } while (option < 0 || option > i);
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

    private void executeAdmin(int option) {
        switch (option) {
            case 1 -> {
                seeAllRestaurants1();
            }
            case 2 -> {
                addRestaurant();
            }
            case 3 -> {
                updateRestaurant();
            }
            case 4 -> {
                deleteRestaurant();
            }
            case 5 -> {
                seeAllClients();
            }
            case 6 -> {
                deleteClient();
            }
            case 7 -> {
                seeAllDelivery();
            }
            case 8 -> {
                addDelivery();
            }
            case 9 -> {
                updateDelivery();
            }
            case 10 -> {
                deleteDelivery();
            }
            case 0 -> {
                scanner.close();
                System.exit(0);
            }
        }
    }
    private void executeClient(int option) {
        switch (option) {
            case 1 -> {
                seeAllRestaurants2();
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
            case 11 -> {
                logout();
            }
            case 12 -> {
                updateClient();
            }
            case 13 -> {
                deleteClient();
            }
            case 0 -> {
                scanner.close();
                System.exit(0);
            }
        }
    }

    private void seeAllRestaurants2() {
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
        AdresaClient a;

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
//        System.out.println("Cooming soon!");
        ComandaService.seeAllOrders(client);
    }

    private void findOrderById() {
        System.out.println("Cooming soon!");

    }

    private void updateOrderAddress() {
        System.out.println("Cooming soon!");

    }

    private void placeOrder() {
//        System.out.println("Cooming soon!");
        ComandaService.placeOrder(client, restaurante);
    }
    private void logout(){
        System.out.println("Coming soon!");
    }
    private void updateClient(){
        System.out.println("Coming  soon!");
    }
    private void deleteClient(){
        System.out.println("coming soon!");
    }
    private void seeAllRestaurants1(){
        System.out.println("coming soon!");
    }
    private void addRestaurant(){
        System.out.println("coming soon!");
    }
    private void updateRestaurant(){
        System.out.println("coming soon!");
    }
    private void deleteRestaurant(){
        System.out.println("coming soon!");
    }
    private void seeAllClients(){
        System.out.println("coming soon!");
    }
    private void seeAllDelivery(){
        System.out.println("coming soon!");
    }
    private void addDelivery(){
        System.out.println("coming soon!");
    }
    private void updateDelivery(){
        System.out.println("coming soon!");
    }
    private void deleteDelivery(){
        System.out.println("coming soon!");
    }
}
