package servicii;
import clase.Client;
import exceptii.CustomException;
import persistente.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {

    /**
     * Services have a way of obtaining backing repositories.
     * One service can use multiple (or no) repositories.
     */
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
            c = getEmployeeByUserName(userName);
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

    public Client getEmployeeByUserName(String userName) throws CustomException {
        Optional<Client> client= clientRepository.findById(userName);
        return client.orElseThrow(() -> new CustomException("Cannot find an employee having the provided username: " + userName));
    }
    public void updateDetailsForCLient(Client client) throws CustomException {
        clientRepository.findById(client.getUserName())
                .orElseThrow(() -> new CustomException("Username does not exist!"));
        clientRepository.update(client);
    }
    public void removeClient(Client client) throws CustomException {
        clientRepository.findById(client.getUserName())
                .orElseThrow(() -> new CustomException("Client does not exist!"));
        clientRepository.delete(client);
    }
}