package servicii;

import clase.Restaurant;
import Exceptii.CustomException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {

    public static void seeAllRestaurants(List<Restaurant> restaurante){
        for (Restaurant r: restaurante){
            System.out.println((1 + restaurante.indexOf(r)) + ". " + r);
        }
    }
    public static void findRestaurantByName(String nume, List<Restaurant> restaurante) throws CustomException{
        List<Restaurant> restaurante2 = new ArrayList<>();
        for (Restaurant r: restaurante) {
            if(r.getNumeRestaurant().equalsIgnoreCase(nume)){
                restaurante2.add(r);
            }
        }
        if (!restaurante2.isEmpty()){
            seeAllRestaurants(restaurante2);
        }else throw new CustomException("Nu am gasit niciun restaurant!");
    }

}
