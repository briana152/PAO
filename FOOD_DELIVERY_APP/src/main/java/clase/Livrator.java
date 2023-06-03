package clase;

public class Livrator{
    private int ID;
    private String fullName;
    private int varsta ;

    public Livrator(){
        this.ID = 9999999;
        this.fullName = "Livrator Random";
        this.varsta = 20;
    }
    public Livrator(String fullName, int varsta) {
        this.fullName = fullName;
        this.varsta = varsta;
    }
    public Livrator(int ID, String fullName, int varsta) {
        this.ID = ID;
        this.fullName = fullName;
        this.varsta = varsta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return ID + ". " +
                "Nume livrator: " + fullName + "\n" +
                "Varsta Livrator: " + varsta + "\n";
    }
}
