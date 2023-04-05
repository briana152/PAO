package clase;

public class AdresaClient extends Adresa implements Comparable<AdresaClient>, Cloneable{
    private String bloc;
    private String scara;
    private int apartament;
    public AdresaClient(){}

    public AdresaClient(String judet, String localitate, String strada, int numar, String bloc, String scara, int apartament) {
        super(judet, localitate, strada, numar);
        this.bloc = bloc;
        this.scara = scara;
        this.apartament = apartament;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getScara() {
        return scara;
    }

    public void setScara(String scara) {
        this.scara = scara;
    }

    public int getApartament() {
        return apartament;
    }

    public void setApartament(int apartament) {
        this.apartament = apartament;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                "AdresaClient{" +
                "bloc='" + bloc + '\'' +
                ", scara='" + scara + '\'' +
                ", apartament=" + apartament +
                '}';
    }

    @Override
    public int compareTo(AdresaClient o) {
        return this.getLocalitate().compareTo(o.getLocalitate());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AdresaClient clone = (AdresaClient) super.clone();
        clone.setBloc(bloc);
        clone.setScara(scara);

        return clone;
    }
}
