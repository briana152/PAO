package servicii;

import clase.Adresa;
import clase.Restaurant;
import exceptii.CustomException;
import persistente.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class RestaurantService {

    private final RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public void first(){
        restaurantRepository.fetchFromTable();
    }
    public void registerNewRestaurant(){
        Restaurant r = new Restaurant();
        System.out.println("Nume:");
        String nume = scanner.nextLine().strip();
        Adresa a = AddressService.readAddress();
        r.setNumeRestaurant(nume);
        r.setAdresaRestaurant(a);

        restaurantRepository.save(r);
    }
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(String ID) throws CustomException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(ID);
        return restaurant.orElseThrow(() -> new CustomException("Cannot find restaurant with the ID provided"));
    }
    public void updateRestaurant(Restaurant restaurant) {
        String numeNou = "";
        int id = -1;

        do {
            System.out.println("Introdu ID-ul: ");
            try{
                id = GeneralService.readInt();
                restaurant = getRestaurantById(Integer.toString(id));
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }

        }while (restaurantRepository.findById(Integer.toString(id)).isEmpty());

        System.out.println("Nume actual: " + restaurant.getNumeRestaurant());
        System.out.println("Doriti sa-l schimbati?DA/NU");
        String decizie1 = scanner.next();
        if (decizie1.equalsIgnoreCase("DA")){
            System.out.println("Nume nou:");
            numeNou = scanner.nextLine();
            restaurant.setNumeRestaurant(numeNou);
        }

        System.out.println(restaurant.getAdresaRestaurant());
        System.out.println("Doriti sa o schimbati?DA/NU");
        String decizie2 = scanner.next();
        if (decizie2.equalsIgnoreCase("DA")){
            System.out.println("Adresa noua:");
            restaurant.setAdresaRestaurant(AddressService.readAddress());
        }

        restaurantRepository.update(restaurant);

    }
    public void removeRestaurant(Restaurant restaurant) {
        int id = -1;
        do {
            System.out.println("Introdu ID-ul: ");
            try{
                id = GeneralService.readInt();
                restaurant = getRestaurantById(Integer.toString(id));
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }
        }while (restaurantRepository.findById(Integer.toString(id)).isEmpty());

        restaurantRepository.delete(restaurant);
    }

    public static void seeAllRestaurants(List<Restaurant> restaurante){
        restaurante.forEach(System.out::println);
    }
    public static void findRestaurantByName(String nume, List<Restaurant> restaurante) throws CustomException{

        AtomicBoolean restaurantFound = new AtomicBoolean(false);

        restaurante.stream()
                .filter(r -> r.getNumeRestaurant().equalsIgnoreCase(nume))
                .forEach(r -> {
                    System.out.println(r);
                    restaurantFound.set(true);
                });

        if (!restaurantFound.get()) {
            throw new CustomException("Nu exista niciun restaurant cu acest nume!");
        }
    }

}
