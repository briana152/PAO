package clase;

import java.util.*;

public class Client{
    private String numeClient;
    private SortedSet<AdresaClient> adreseClient = new TreeSet<>();
    private List<Comanda> comenzi = new ArrayList<>();
    public Client(){
        this.numeClient = "Client";
    }

    public Client(String numeClient, SortedSet<AdresaClient> adreseClient, List<Comanda> comenzi) {
        this.numeClient = numeClient;
        this.adreseClient = adreseClient;
        this.comenzi = comenzi;
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
        return Objects.equals(numeClient, client.numeClient) && Objects.equals(adreseClient, client.adreseClient) && Objects.equals(comenzi, client.comenzi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeClient, adreseClient, comenzi);
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeClient='" + numeClient + '\'' +
                ", adreseClient=" + adreseClient +
                ", comenzi=" + comenzi +
                '}';
    }
}
