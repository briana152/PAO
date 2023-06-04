package servicii;

import clase.Adresa;
import clase.Client;
import exceptii.CustomException;
import clase.AdresaClient;
import persistente.AdresaClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressService {
    private final AdresaClientRepository adresaClientRepository = AdresaClientRepository.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public void first(){
        adresaClientRepository.fetchFromTable();
    }

    public void registerNewAddress(Client client){
        AdresaClient a = AddressService.readAddressClient();
        a.setUserNameClient(client.getUserName());

        adresaClientRepository.save(a);
    }

    public List<AdresaClient> getAllAddresses(Client client) {
        return adresaClientRepository.findAll().stream().
                filter(a -> a.getUserNameClient().equals(client.getUserName())).collect(Collectors.toList());
    }

    public AdresaClient getAdresaClientById(String ID, String userName_client) throws CustomException {
        Optional<AdresaClient> adresaClient = adresaClientRepository.findById(ID, userName_client);
        return adresaClient.orElseThrow(() -> new CustomException("Cannot find address with the ID provided for your account"));
    }
    public void updateAdresaClient(AdresaClient adresaClient, Client client) {
        int id = -1;
        do {
            System.out.println("Introdu ID-ul: ");
            try{
                id = GeneralService.readInt();
                adresaClient = getAdresaClientById(Integer.toString(id), client.getUserName());
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }

        }while (adresaClientRepository.findById(Integer.toString(id),client.getUserName()).isEmpty());

        System.out.println("Judet actual: " + adresaClient.getJudet());
        System.out.println("Doriti sa schimbati?DA/NU");
        String decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Judet:");
            String judet = scanner.nextLine();
            adresaClient.setJudet(judet);
        }
        System.out.println("Localitate/sector actual: " + adresaClient.getLocalitate());
        System.out.println("Doriti sa schimbati?DA/NU");
        decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Localitate/Sector:");
            String localitate = scanner.nextLine();
            adresaClient.setLocalitate(localitate);
        }
        System.out.println("Strada actuala: " + adresaClient.getStrada());
        System.out.println("Doriti sa schimbati?DA/NU");
        decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Strada:");
            String strada = scanner.nextLine();
            adresaClient.setStrada(strada);
        }
        System.out.println("Numar actual: " + adresaClient.getNumar());
        System.out.println("Doriti sa schimbati?DA/NU");
        decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Numar:");
            int nr = -1;
            do {
                try {
                    nr = GeneralService.readInt();
                } catch (CustomException exception) {
                    System.out.println(exception.getMessage());
                }
            } while (nr < 0);
            adresaClient.setNumar(nr);
        }
        System.out.println("Bloc actual: " + adresaClient.getBloc());
        System.out.println("Doriti sa schimbati?DA/NU");
        decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Bloc:");
            String bloc = scanner.nextLine();
            adresaClient.setBloc(bloc);
        }
        System.out.println("Scara actuala: " + adresaClient.getScara());
        System.out.println("Doriti sa schimbati?DA/NU");
        decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Scara:");
            String scara = scanner.nextLine();
            adresaClient.setScara(scara);
        }
        System.out.println("Apartament actual: " + adresaClient.getApartament());
        System.out.println("Doriti sa schimbati?DA/NU");
        decizie = scanner.nextLine().strip();
        if (decizie.equalsIgnoreCase("DA")){
            System.out.println("Apartament:");
            int ap = -1;
            do {
                try {
                    ap = GeneralService.readInt();
                } catch (CustomException exception) {
                    System.out.println(exception.getMessage());
                }
            } while (ap < 0);

            adresaClient.setApartament(ap);
        }

        adresaClientRepository.update(adresaClient);

    }
    public void removeAdresaClient(AdresaClient adresaClient, Client client) {
        int id = -1;
        do {
            System.out.println("Introdu ID-ul: ");
            try{
                id = GeneralService.readInt();
                adresaClient = getAdresaClientById(Integer.toString(id), client.getUserName());
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }
        }while (adresaClientRepository.findById(Integer.toString(id),client.getUserName()).isEmpty());

        adresaClientRepository.delete(adresaClient);
    }
//    public static void seeAllAddresses(AdresaClient[] adrese){
//        for (int i = 0;i<adrese.length;i++){
//            System.out.println((i + 1) + ". " + adrese[i]);
//        }
//    }
    public static Adresa readAddress(){
        System.out.println("Judet: ");
        String judet = scanner.nextLine();
        System.out.println("Localitate/Sector: ");
        String localitate = scanner.nextLine();
        System.out.println("Strada: ");
        String strada = scanner.nextLine();
        System.out.println("Numarul: ");
        int nr = -1;
        do {
            try {
                nr = GeneralService.readInt();
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
            }
        } while (nr < 0);

        return new Adresa(judet, localitate, strada, nr);
    }
    public static AdresaClient readAddressClient(){
        System.out.println("Judet: ");
        String judet = scanner.nextLine();
        System.out.println("Localitate/Sector: ");
        String localitate = scanner.nextLine();
        System.out.println("Strada: ");
        String strada = scanner.nextLine();
        System.out.println("Numarul: ");
        int nr = -1;
        do {
            try {
                nr = GeneralService.readInt();
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
            }
        } while (nr < 0);
        System.out.println("Bloc: ");
        String bloc = scanner.nextLine();
        System.out.println("Scara: ");
        String scara = scanner.nextLine();
        System.out.println("Apartament: ");
        int ap = -1;
        do {
            try {
                ap = GeneralService.readInt();
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
            }
        } while (ap < 0);

        return new AdresaClient(judet, localitate, strada, nr, bloc, scara, ap);
    }
    public static String fromAddressToString(Adresa a){
        return a.getJudet() + ", " + a.getLocalitate() + ", " + a.getStrada() + ", " + a.getNumar();
    }
    public static Adresa fromStringToAddress(String a) {
        String[] detaliiAdresa = a.split(",");
        return new Adresa(detaliiAdresa[0].strip(),detaliiAdresa[1].strip(),detaliiAdresa[2].strip(),
                Integer.parseInt(detaliiAdresa[3].strip()));
    }
    public static String fromClientAddressToString(AdresaClient a){
        return a.getJudet() + ", " + a.getLocalitate() + ", " + a.getStrada() + ", " + a.getNumar() +
                ", " + a.getBloc() + ", " + a.getScara() + ", " + a.getApartament();
    }
    public static AdresaClient fromStringToClientAddress(String a) {
        String[] detaliiAdresa = a.split(",");
        return new AdresaClient(detaliiAdresa[0].strip(),detaliiAdresa[1].strip(),detaliiAdresa[2].strip(),
                Integer.parseInt(detaliiAdresa[3].strip()),
                detaliiAdresa[4].strip(),detaliiAdresa[5].strip(),Integer.parseInt(detaliiAdresa[6].strip()));
    }
}
