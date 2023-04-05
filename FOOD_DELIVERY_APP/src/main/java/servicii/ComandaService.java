package servicii;

import Exceptii.CustomException;
import clase.*;

import java.util.*;

public class ComandaService {
    private static final Scanner scanner = new Scanner(System.in);
    private static Comanda comanda = new Comanda();

    public static double calculeazaTotal(Cos cos){

        return 0;
    }
    public static Produs findProductNyName(String nume, Restaurant r) throws CustomException{
        List<Sectiune> sectiuni = r.getMeniu().getSectiuni();
        List<Produs> produse = new ArrayList<Produs>();
        Produs produs = null;
        for (Sectiune s: sectiuni){
            produse.addAll(s.getProduseSectiune());
        }
        for (Produs p: produse){
            if (p.getNumeProdus().equalsIgnoreCase(nume)){
                produs = p;
            }
        }
        if (produs != null){
            return produs;
        }else throw new CustomException("Nu exista acest produs!");
    }
    public static int readQuantity(){
        int cantitate = -1;
        do {
            try {
                System.out.println("Cantitate:");
                cantitate = GeneralService.readInt();
            }catch (CustomException exception){
                System.out.println(exception.getMessage());
            }
        }while (cantitate < 0);
        return cantitate;
    }
    public static Produs readProduct(Restaurant restaurant){
        int ok = -1;
        Produs produs = new Produs();
        do {
            try {
                System.out.println("Nume produs:");
                String nume = scanner.nextLine();
                produs = findProductNyName(nume, restaurant);
                ok = 1;
            }catch (CustomException exception){
                System.out.println(exception.getMessage());
            }

        }while (ok < 0);

        return produs;

    }
    public static void placeOrder1(List<Restaurant> restaurante){
        for (Restaurant r: restaurante){
            System.out.println((1 + restaurante.indexOf(r)) + ". " + r.getNumeRestaurant());
        }
        int i = 0;
        System.out.println("Introduceti numarul restaurantului: ");
        try {
            i = GeneralService.readIndex(restaurante.size());
            comanda.setNumeRestaurant(restaurante.get(i).getNumeRestaurant());
            comanda.setAdresaRestaurant(restaurante.get(i).getAdresaRestaurant());
            System.out.println(restaurante.get(i).getMeniu());

        }catch (CustomException exception){
            System.out.println(exception.getMessage());
        }
        String gata = "NU";
        Map<Produs,Integer> map = new HashMap<>();
        do {
            Produs produs = readProduct(restaurante.get(i));
            int cantitate = readQuantity();
            map.put(produs,cantitate);
            System.out.println("Doriti sa mergeti mai departe? DA/NU");
            gata = scanner.next();
        }while (gata.equals("NU"));

        comanda.getCos().setProduse(map);

    }

}
