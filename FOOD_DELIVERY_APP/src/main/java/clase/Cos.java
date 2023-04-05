package clase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cos implements Cloneable{
    private Map<Produs, Integer> produse = new HashMap<Produs, Integer>();
    public Cos(){}

    public Cos(Map<Produs, Integer> produse) {
        this.produse = produse;
    }

    public Map<Produs, Integer> getProduse() {
        return produse;
    }

    public void setProduse(Map<Produs, Integer> produse) {
        this.produse = produse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cos cos = (Cos) o;
        return Objects.equals(produse, cos.produse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produse);
    }

    @Override
    public String toString() {
        return "Cos{" +
                "produse=" + produse +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Cos cosClone = (Cos) super.clone();
        Map<Produs, Integer> clonedProduse = new HashMap<Produs, Integer>();
        for (Map.Entry<Produs, Integer> entry : produse.entrySet()) {
            //TODO: provide clone implementation for Produs (checked)
            Produs produsClone = (Produs) entry.getKey().clone();
            Integer cantitateClone = entry.getValue();
            clonedProduse.put(produsClone, cantitateClone);
        }
        cosClone.setProduse(clonedProduse);
        return cosClone;
    }
}
