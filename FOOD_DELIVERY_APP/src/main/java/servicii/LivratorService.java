package servicii;

import clase.Livrator;
import exceptii.CustomException;
import persistente.LivratorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LivratorService {
    private final LivratorRepository livratorRepository = LivratorRepository.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public void first(){
        livratorRepository.fetchFromTable();
    }

    public void registerNewLivrator(){
        Livrator l = new Livrator();
        System.out.println("Nume complet:");
        String fullName = scanner.nextLine().strip();
        System.out.println("Varsta:");
        int varsta = scanner.nextInt();
        l.setFullName(fullName);
        l.setVarsta(varsta);

        livratorRepository.save(l);
    }

    public List<Livrator> getAllLivratori() {
        return livratorRepository.findAll();
    }

    public Livrator getLivratorById(String ID) throws CustomException {
        Optional<Livrator> livrator = livratorRepository.findById(ID);
        return livrator.orElseThrow(() -> new CustomException("Cannot find livrator with the ID provided"));
    }
    public void updateAgeForLivrator(Livrator livrator) {
        int varstaNoua = -1;
        int id = -1;

        do {
            System.out.println("Introdu ID-ul: ");
            try{
                id = GeneralService.readInt();
                livrator = getLivratorById(Integer.toString(id));
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }

        }while (livratorRepository.findById(Integer.toString(id)).isEmpty());

        System.out.println("Varsta actuala: " + livrator.getVarsta() );
        System.out.println("Varsta noua:");
        do {
            try {
                varstaNoua = GeneralService.readInt();
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }
        }while (varstaNoua < 0);

        livrator.setVarsta(varstaNoua);

        livratorRepository.update(livrator);

    }
    public void removeLivrator(Livrator livrator) {
        int id = -1;
        do {
            System.out.println("Introdu ID-ul: ");
            try{
                id = GeneralService.readInt();
                livrator = getLivratorById(Integer.toString(id));
            }catch (CustomException ex){
                System.out.println(ex.getMessage());
            }
        }while (livratorRepository.findById(Integer.toString(id)).isEmpty());

        livratorRepository.delete(livrator);
    }
}
