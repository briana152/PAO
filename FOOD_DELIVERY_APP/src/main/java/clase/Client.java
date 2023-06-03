package clase;

import java.util.*;

public class Client{
    private String userName;
    private String numeClient;
    private SortedSet<AdresaClient> adreseClient = new TreeSet<>();
    private List<Comanda> comenzi = new ArrayList<>();
    public Client(){}

    public Client(String userName, String numeClient) {
        this.userName = userName;
        this.numeClient = numeClient;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public SortedSet<AdresaClient> getAdreseClient() {
        return adreseClient;
    }

    public void setAdreseClient(SortedSet<AdresaClient> adreseClient) {
        this.adreseClient = adreseClient;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return userName.equals(client.userName) && numeClient.equals(client.numeClient) && Objects.equals(adreseClient, client.adreseClient) && Objects.equals(comenzi, client.comenzi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, numeClient, adreseClient, comenzi);
    }

    @Override
    public String toString() {
        return "---------------" + "\n" +
                "username: " + userName + "\n" +
                "nume" + numeClient + "\n" +
                "--------------" + "\n";
    }
}
