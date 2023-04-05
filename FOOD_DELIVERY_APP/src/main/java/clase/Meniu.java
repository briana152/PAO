package clase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Meniu {
    private static String TITLU = "MENIU";
    private List<Sectiune> sectiuni = new ArrayList<Sectiune>();

    public Meniu(){}

    public Meniu(List<Sectiune> sectiuni) {
        this.sectiuni = sectiuni;
    }

    public List<Sectiune> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(List<Sectiune> sectiuni) {
        this.sectiuni = sectiuni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meniu meniu = (Meniu) o;
        return Objects.equals(sectiuni, meniu.sectiuni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectiuni);
    }

    @Override
    public String toString() {
        return "Meniu{" +
                "sectiuni=" + sectiuni +
                '}';
    }
}
