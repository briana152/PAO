package clase;

import java.util.Objects;

public class Restaurant{
    private int ID;
    private String numeRestaurant;
    private Adresa adresaRestaurant;
    private Meniu meniu = new Meniu();

    public Restaurant(){}

    public Restaurant(String numeRestaurant, Adresa adresaRestaurant){
        this.numeRestaurant = numeRestaurant;
        this.adresaRestaurant = adresaRestaurant;
    }
    public Restaurant(int ID, String numeRestaurant, Adresa adresaRestaurant) {
        this.ID = ID;
        this.numeRestaurant = numeRestaurant;
        this.adresaRestaurant = adresaRestaurant;
    }
//    public Restaurant(String numeRestaurant, Adresa adresaRestaurant, Meniu meniu) {
//        this.numeRestaurant = numeRestaurant;
//        this.adresaRestaurant = adresaRestaurant;
//        this.meniu = meniu;
//    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        return  ID + "." + numeRestaurant  + "\n" +
                adresaRestaurant + "\n" +
                meniu + "\n";
    }
}
