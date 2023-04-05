package servicii;

import clase.Restaurant;
import clase.Adresa;
import clase.Produs;
import clase.Sectiune;
import clase.Meniu;

import java.util.*;

public class GenerateService {
    public static Restaurant KFC(){
        List<Produs> produse1 = new ArrayList<Produs>();
        List<Produs> produse2 = new ArrayList<Produs>();
        List<Produs> produse3 = new ArrayList<Produs>();

        List<Sectiune> sectiuni = new ArrayList<Sectiune>();

        produse1.add(new Produs("Meniu 8 strips nepicanti", 49.99));
        produse1.add(new Produs("Meniu 8 hot wings",45.99));
        produse1.add(new Produs("Smart meniu extra",38.75));
        sectiuni.add(new Sectiune("Meniuri cu pui", produse1));

        produse2.add(new Produs("sos glenn",3.99));
        produse2.add(new Produs("sos ketchup",3.99));
        produse2.add(new Produs("sos maioneza",3.99));
        sectiuni.add(new Sectiune("Sosuri", produse2));

        produse3.add(new Produs("Coca-cola",6.99));
        produse3.add(new Produs("Coca-cola zero",6.99));
        produse3.add(new Produs("Sprite",6.99));
        produse3.add(new Produs("Fanta",6.99));
        sectiuni.add(new Sectiune("Bauturi", produse3));

        Meniu meniu = new Meniu(sectiuni);

        Adresa adresa = new Adresa("Bucuresti", "Pacii","Bld. Iuliu Maniu", 109);

        return new Restaurant("KFC", adresa, meniu);

    }

    public static Restaurant Starbucks(){
        List<Produs> produse1 = new ArrayList<Produs>();
        List<Produs> produse2 = new ArrayList<Produs>();
        List<Produs> produse3 = new ArrayList<Produs>();

        List<Sectiune> sectiuni = new ArrayList<Sectiune>();

        produse1.add(new Produs("Toffe nut latte", 23.99));
        produse1.add(new Produs("Ciocolata calda cu caramel",25.00));
        produse1.add(new Produs("Caffe mocha",28.25));
        sectiuni.add(new Sectiune("Bauturi calde", produse1));

        produse2.add(new Produs("Iced brown sugar oat shaken espresso",23.99));
        produse2.add(new Produs("Iced caramel macchiato",24.99));
        sectiuni.add(new Sectiune("Bauturi reci", produse2));

        produse3.add(new Produs("Tort cu morcovi cu crema de branza",7.99));
        produse3.add(new Produs("Briosa cu ciocolata",6.99));
        produse3.add(new Produs("Chec cu banane",7.99));
        sectiuni.add(new Sectiune("Deserturi", produse3));

        Meniu meniu = new Meniu(sectiuni);

        Adresa adresa = new Adresa("Bucuresti", "Unirii","Str. Franceza", 62);

        return new Restaurant("Starbucks", adresa, meniu);

    }
    public static List<Restaurant> returnAll(){
        List<Restaurant> restaurante = new ArrayList<Restaurant>();
        restaurante.add(KFC());
        restaurante.add(Starbucks());
        return restaurante;
    }
}
