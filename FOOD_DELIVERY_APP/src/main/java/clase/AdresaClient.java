package clase;

public class AdresaClient extends Adresa implements Comparable<AdresaClient>, Cloneable{
    private int ID;
    private String userNameClient = "";
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

    public AdresaClient(String judet, String localitate, String strada, int numar, String userNameClient, String bloc, String scara, int apartament) {
        super(judet, localitate, strada, numar);
        this.userNameClient = userNameClient;
        this.bloc = bloc;
        this.scara = scara;
        this.apartament = apartament;
    }
    public AdresaClient(int ID, String judet, String localitate, String strada, int numar, String userNameClient,  String bloc, String scara, int apartament) {
        super(judet, localitate, strada, numar);
        this.ID = ID;
        this.userNameClient = userNameClient;
        this.bloc = bloc;
        this.scara = scara;
        this.apartament = apartament;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserNameClient() {
        return userNameClient;
    }

    public void setUserNameClient(String userNameClient) {
        this.userNameClient = userNameClient;
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
        return ID + ". " +
                super.toString() + ", " +
                bloc + ", " +
                scara + ", " +
                apartament + "\n";
    }

    @Override
    public int compareTo(AdresaClient o) {
        return this.getLocalitate().compareTo(o.getLocalitate());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AdresaClient clone = (AdresaClient) super.clone();
        clone.setUserNameClient(userNameClient);
        clone.setBloc(bloc);
        clone.setScara(scara);


        return clone;
    }
}
