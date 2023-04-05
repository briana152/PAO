package clase;

import java.util.Objects;

public class Restaurant{
    private String numeRestaurant;
    private Adresa adresaRestaurant;
    private Meniu meniu;

    public Restaurant(){}

    public Restaurant(String numeRestaurant, Adresa adresaRestaurant, Meniu meniu) {
        this.numeRestaurant = numeRestaurant;
        this.adresaRestaurant = adresaRestaurant;
        this.meniu = meniu;
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

    public Meniu getMeniu() {
        return meniu;
    }

    public void setMeniu(Meniu meniu) {
        this.meniu = meniu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(numeRestaurant, that.numeRestaurant) && Objects.equals(adresaRestaurant, that.adresaRestaurant) && Objects.equals(meniu, that.meniu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeRestaurant, adresaRestaurant, meniu);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "numeRestaurant='" + numeRestaurant + '\'' +
                ", adresaRestaurant=" + adresaRestaurant +
                ", meniu=" + meniu +
                '}';
    }
}
