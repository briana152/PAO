package clase;

import java.util.Objects;

public class Produs implements Cloneable{
    private String numeProdus;
    private double pretProdus;

    public Produs(){}
    public Produs(String numeProdus, double pretProdus) {
        this.numeProdus = numeProdus;
        this.pretProdus = pretProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public double getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(double pretProdus) {
        this.pretProdus = pretProdus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return Double.compare(produs.pretProdus, pretProdus) == 0 && Objects.equals(numeProdus, produs.numeProdus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeProdus, pretProdus);
    }

    @Override
    public String toString() {
        return "Produs{" +
                "numeProdus='" + numeProdus + '\'' +
                ", pretProdus=" + pretProdus +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Produs cloned = (Produs) super.clone();
        cloned.setNumeProdus(numeProdus);
        cloned.setPretProdus(pretProdus);

        return cloned;
    }
}
