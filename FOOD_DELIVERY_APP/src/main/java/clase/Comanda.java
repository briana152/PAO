package clase;

import java.util.Date;
import java.util.Objects;

public class Comanda implements Cloneable{
    private static int ID = 0;
    private String numeRestaurant;
    private Adresa adresaRestaurant;
    private AdresaClient adresaLivrare;
    private Cos cos;
    private float total;
    private String modDePlata;
    private Date data;
    private final Livrator livrator = new Livrator();

    public Comanda(){
        ID++;
    }

    public Comanda(String numeRestaurant, Adresa adresaRestaurant, AdresaClient adresaLivrare, Cos cos, float total, String modDePlata, Date data) {
        this.numeRestaurant = numeRestaurant;
        this.adresaRestaurant = adresaRestaurant;
        this.adresaLivrare = adresaLivrare;
        this.cos = cos;
        this.total = total;
        this.modDePlata = modDePlata;
        this.data = data;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Comanda.ID = ID;
    }

    public String getNumeRestaurant() {
        return numeRestaurant;
    }

    public void setNumeRestaurant(String numeRestaurant) {
        this.numeRestaurant = numeRestaurant;
    }

    public Adresa getAdresaRestaurant() {
        return adresaRestaurant;
    }

    public void setAdresaRestaurant(Adresa adresaRestaurant) {
        this.adresaRestaurant = adresaRestaurant;
    }

    public AdresaClient getAdresaLivrare() {
        return adresaLivrare;
    }

    public void setAdresaLivrare(AdresaClient adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }

    public Cos getCos() {
        return cos;
    }

    public void setCos(Cos cos) {
        this.cos = cos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getModDePlata() {
        return modDePlata;
    }

    public void setModDePlata(String modDePlata) {
        this.modDePlata = modDePlata;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Livrator getLivrator() {
        return livrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Float.compare(comanda.total, total) == 0 && Objects.equals(numeRestaurant, comanda.numeRestaurant) && Objects.equals(adresaRestaurant, comanda.adresaRestaurant) && Objects.equals(adresaLivrare, comanda.adresaLivrare) && Objects.equals(cos, comanda.cos) && Objects.equals(modDePlata, comanda.modDePlata) && Objects.equals(data, comanda.data) && Objects.equals(livrator, comanda.livrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeRestaurant, adresaRestaurant, adresaLivrare, cos, total, modDePlata, data, livrator);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "numeRestaurant='" + numeRestaurant + '\'' +
                ", adresaRestaurant=" + adresaRestaurant +
                ", adresaLivrare=" + adresaLivrare +
                ", cos=" + cos +
                ", total=" + total +
                ", modDePlata='" + modDePlata + '\'' +
                ", data=" + data +
                ", livrator=" + livrator +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Comanda cloned = (Comanda) super.clone();
        //TODO: provide implementation for clone in Adresa, AdresaClient, Cos (checked)
        cloned.adresaRestaurant = (Adresa) adresaRestaurant.clone();
        cloned.adresaLivrare = (AdresaClient) adresaLivrare.clone();
        cloned.cos = (Cos) cos.clone();
        cloned.data = (Date) data.clone();

        return cloned;
    }
}
