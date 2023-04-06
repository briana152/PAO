package clase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sectiune {
    private String numeSectiune;
    private List<Produs> produseSectiune = new ArrayList<>();

    public Sectiune(){}

    public Sectiune(String numeSectiune, List<Produs> produseSectiune) {
        this.numeSectiune = numeSectiune;
        this.produseSectiune = produseSectiune;
    }

    public String getNumeSectiune() {
        return numeSectiune;
    }

    public void setNumeSectiune(String numeSectiune) {
        this.numeSectiune = numeSectiune;
    }

    public List<Produs> getProduseSectiune() {
        return produseSectiune;
    }

    public void setProduseSectiune(List<Produs> produseSectiune) {
        this.produseSectiune = produseSectiune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sectiune sectiune = (Sectiune) o;
        return Objects.equals(numeSectiune, sectiune.numeSectiune) && Objects.equals(produseSectiune, sectiune.produseSectiune);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeSectiune, produseSectiune);
    }

    @Override
    public String toString() {
        return "Sectiune{" +
                "numeSectiune='" + numeSectiune + '\'' +
                ", produseSectiune=" + produseSectiune +
                '}';
    }
}
