package servicii;

import Exceptii.CustomException;
import clase.AdresaClient;
import java.util.Scanner;
import java.util.SortedSet;

public class AddressService {
    private static final Scanner scanner = new Scanner(System.in);
    public static void seeAllAddresses(AdresaClient[] adrese){
        for (int i = 0;i<adrese.length;i++){
            System.out.println((i + 1) + ". " + adrese[i]);
        }
    }
    public static AdresaClient readAddress(){
        //TODO: CITIREA NU MERGE PT STRINGURI CU SPATII!!!!!!
        System.out.print("Judet: ");
        String judet = scanner.next();
        System.out.print("Localitate/Sector: ");
        String localitate = scanner.next();
        System.out.print("Strada: ");
        String strada = scanner.next();
        System.out.print("Numarul: ");
        int nr = -1;
        do {
            try {
                nr = GeneralService.readInt();
            } catch (CustomException exception) {
                System.out.println(exception.getMessage());
            }
        } while (nr < 0);
        System.out.print("Bloc: ");
        String bloc = scanner.next();
        System.out.print("Scara: ");
        String scara = scanner.next();
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

    public static void showAddressMeniu(){

        System.out.println("1.Schimba judetul");
        System.out.println("2.Schimba localitatea");
        System.out.println("3.Schimba strada");
        System.out.println("4.Schimba numarul strazii");
        System.out.println("5.Schimba blocul");
        System.out.println("6.Schimba scara");
        System.out.println("7.Schimba numarul apartamentului");

    }
    public static AdresaClient findAddress(AdresaClient adresa, SortedSet<AdresaClient> adrese){
        AdresaClient foundAddress = null;
        for (AdresaClient a: adrese){
            if (a.equals(adresa)){
                foundAddress = a;
            }
        }

        return foundAddress;

    }
    public static void updateAddress(int option, AdresaClient adresa){
        String newString;
        int newNumber = -1;

        switch (option) {
            case 1 -> {
                System.out.print("Judet: ");
                newString = scanner.next();
                adresa.setJudet(newString);
            }
            case 2 -> {
                System.out.print("Localitate: ");
                newString = scanner.next();
                adresa.setLocalitate(newString);
            }
            case 3 -> {
                System.out.print("Strada: ");
                newString = scanner.next();
                adresa.setStrada(newString);
            }
            case 4 -> {
                System.out.print("Nr strada: ");
                do {
                    try {
                        newNumber = GeneralService.readInt();
                    } catch (CustomException exception) {
                        System.out.println(exception.getMessage());
                        System.out.println("Invalid option! Try again!");
                    }
                } while (newNumber < 0);
                adresa.setNumar(newNumber);
            }
            case 5 -> {
                System.out.print("Bloc: ");
                newString = scanner.next();
                adresa.setBloc(newString);
            }
            case 6 -> {
                System.out.print("Scara: ");
                newString = scanner.next();
                adresa.setScara(newString);
            }
            case 7 -> {
                System.out.print("Nr apartament: ");
                do {
                    try {
                        newNumber = GeneralService.readInt();
                    } catch (CustomException exception) {
                        System.out.println(exception.getMessage());
                        System.out.println("Invalid option! Try again!");
                    }
                } while (newNumber < 0);
                adresa.setApartament(newNumber);
            }
        }
    }
    public static void deleteAddress(AdresaClient adresa, SortedSet<AdresaClient> adrese) {

        adrese.remove(adresa);
    }
}
