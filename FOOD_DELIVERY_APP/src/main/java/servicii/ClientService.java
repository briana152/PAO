package servicii;
import clase.Client;
import exceptii.CustomException;
import persistente.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {
    private final ClientRepository clientRepository = ClientRepository.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public void first(){
        clientRepository.fetchFromTable();
    }
    public Client login(){
        Client c = null;
        System.out.println("Username:");
        String userName = scanner.nextLine().strip();
        try {
            c = getClientByUserName(userName);
        }catch (CustomException ex){
            System.out.println(ex.getMessage());
        }
        return c;
    }
    public Client registerNewClient(){
        Client c = new Client();
        int ok = -1;
        do {
            System.out.println("Username:");
            String userName = scanner.nextLine().strip();
            Optional<Client> client = clientRepository.findById(userName);
            if (client.isPresent()){
                System.out.println("username-ul " + client.get().getUserName() +" este deja luat");
            }
            else {
                c.setUserName(userName);
                ok = 1;
            }
        }while (ok < 0);

        System.out.println("Nume complet:");
        String fullName = scanner.nextLine();
        c.setNumeClient(fullName);

        return clientRepository.save(c);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientByUserName(String userName) throws CustomException {
        Optional<Client> client= clientRepository.findById(userName);
        return client.orElseThrow(() -> new CustomException("Cannot find a client with the username: " + userName));
    }
    public void updateNameForClient(Client client){
        System.out.println("Old name: " + client.getNumeClient());
        System.out.println("New name:");
        String newName = scanner.nextLine().strip();
        client.setNumeClient(newName);
        clientRepository.update(client);
    }
    public void removeClient(Client... clients){
        Client c = new Client();
        String userName = "";

        if (clients.length != 0){
            c = clients[0];
        }else {
            do {
                System.out.println("Introdu username-ul:");
                userName = scanner.nextLine().strip();
                try {
                    c = getClientByUserName(userName);
                }catch (CustomException ex){
                    System.out.println(ex.getMessage());
                }

            }while (clientRepository.findById(userName).isEmpty());

        }
        clientRepository.delete(c);
    }
}