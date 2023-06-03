package servicii;

import exceptii.CustomException;
import clase.*;
import java.util.*;

public class ComandaService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Comanda comanda = new Comanda();

    public static String readMethod() throws CustomException {
        String line = scanner.nextLine();
        if (line.matches("\\b(?:online(?:\\s(?:cu\\s)?card(?:ul)?)?|cu\\s?card(?:ul)?|card)\\b")) {
            return "online cu card";
        } else if (line.matches("\\b(?:cash(?:\\sla\\slivrare)?)|(?:la\\slivrare)\\b")){
            return "cash la livrare";
        }else throw new CustomException("Invalid option!");

    }
    public static double calculeazaTotal(Cos cos){
        double suma = 0;
        Map<Produs, Integer> map = cos.getProduse();
        for (Map.Entry<Produs, Integer> entry : map.entrySet()) {
            suma += entry.getKey().getPretProdus() * entry.getValue();
        }
        String formattedAmount = String.format("%.2f", suma);
        return Double.parseDouble(formattedAmount);

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
                System.out.println(exception.getMessage() + "\n");
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
    public static Restaurant chooseRestaurant(List<Restaurant> restaurante){
        for (Restaurant r: restaurante){
            System.out.println((1 + restaurante.indexOf(r)) + ". " + r.getNumeRestaurant());
        }
        int i = 0;
        System.out.println("Introduceti numarul restaurantului: ");
        try {
            i = GeneralService.readIndex(restaurante.size()) - 1;
            comanda.setNumeRestaurant(restaurante.get(i).getNumeRestaurant());
            comanda.setAdresaRestaurant(restaurante.get(i).getAdresaRestaurant());
            System.out.println(restaurante.get(i).getMeniu());

        }catch (CustomException exception){
            System.out.println(exception.getMessage());
        }

         return restaurante.get(i);

    }
    public static void addToBasket(Restaurant restaurant){
        Cos cos = new Cos();

        String gata;
        Map<Produs,Integer> map = new HashMap<>();
        do {
            Produs produs = readProduct(restaurant);
            int cantitate = readQuantity();
            map.put(produs,cantitate);
            System.out.println("Doriti sa mai adaugati produse in cos? DA/NU");
            gata = scanner.nextLine();
        }while (gata.equals("DA"));

        cos.setProduse(map);
        comanda.setCos(cos);
        comanda.setTotal(calculeazaTotal(cos));

    }
    public static void addAddress(Client client){
        SortedSet<AdresaClient> set = client.getAdreseClient();
        AdresaClient[] array = set.toArray(new AdresaClient[0]);
        System.out.println("Alegeti adresa de livrare:");
//        AddressService.seeAllAddresses(array);
        System.out.println((1 + set.size()) + ". Adauga adresa noua");
        int option = -1;
        do {
            try {
                option = GeneralService.readIndex(1 + set.size());

            }catch (CustomException exception){
                System.out.println(exception.getMessage());
            }

        }while (option < 0);

        if (option <= set.size()){
            comanda.setAdresaLivrare(array[option - 1]);
        }else{
            AdresaClient a = AddressService.readAddressClient();
            comanda.setAdresaLivrare(a);
            set.add(a);
        }

    }
    public static void addPaymentMethod(){
        System.out.println("Introduceti metoda de plata (online cu cardul/cash la livrare:");
        String plata = null;
        do {
            try{
                plata = readMethod();
            }catch (CustomException exception){
                System.out.println(exception.getMessage());
            }
        }while(plata == null);
        comanda.setModDePlata(plata);

    }
    public static void placeOrder(Client client, List<Restaurant> restaurante){
        Restaurant r = chooseRestaurant(restaurante);
        addToBasket(r);
        addAddress(client);
        addPaymentMethod();
        Comanda c = null;
        try {
            c = (Comanda) comanda.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        client.getComenzi().add(c);

    }
    public static void seeAllOrders(Client client){
        List<Comanda> comenzi = client.getComenzi();
        for (Comanda c: comenzi){
            System.out.println((1 + comenzi.indexOf(c)) + ". " + c);
        }
    }

}
