package persistente;

import clase.Adresa;
import clase.Restaurant;
import servicii.AddressService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static persistente.util.DatabaseConnectionUtils.getDatabaseConnection;

public class RestaurantRepository implements GenericRepository<Restaurant> {
    private final Map<Integer, Restaurant> storage = new HashMap<>();
    private static final String INSERT_RESTAURANT_SQL = "INSERT INTO restaurant (nume, adresa) VALUES (?, ?)";
    private static final String SELECT_ALL_RESTAURANT_SQL = "SELECT * FROM restaurant";
    private static final String SELECT_ALL_WHERE_LIVRATOR_SQL = "SELECT * FROM restaurant WHERE nume = ? AND adresa = ? ORDER BY ID DESC";
    private static final String UPDATE1_RESTAURANT_SQL = "UPDATE restaurant SET nume = ?, adresa = ? WHERE ID = ?";
    private static final String DELETE_RESATAURANT_SQL = "DELETE FROM restaurant WHERE ID = ?";

    private final Connection connection;

    private static volatile RestaurantRepository instance;

    private RestaurantRepository() {
        this.connection = getDatabaseConnection();
    }

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            synchronized (RestaurantRepository.class) {
                if (instance == null) {
                    instance = new RestaurantRepository();
                }
            }
        }
        return instance;
    }

    public void fetchFromTable(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESTAURANT_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String n = resultSet.getNString("nume");
                String a = resultSet.getNString("adresa");
                storage.put(id, new Restaurant(id, n, AddressService.fromStringToAddress(a)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Restaurant save(Restaurant restaurant) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_RESTAURANT_SQL);
            preparedStatement1.setString(1, restaurant.getNumeRestaurant());
            Adresa a = restaurant.getAdresaRestaurant();
            preparedStatement1.setString(2, AddressService.fromAddressToString(a));
            preparedStatement1.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_ALL_WHERE_LIVRATOR_SQL);
            preparedStatement2.setString(1,restaurant.getNumeRestaurant());
            preparedStatement2.setString(2,AddressService.fromAddressToString(restaurant.getAdresaRestaurant()));
            ResultSet resultSet = preparedStatement2.executeQuery();
            if (resultSet.next()){
                restaurant.setID(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        storage.put(restaurant.getID(), restaurant);

        return restaurant;
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Restaurant> findById(String... values) {
        return Optional.ofNullable(storage.get(Integer.parseInt(values[0])));
    }

    @Override
    public void update(Restaurant restaurant, String... newValues) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE1_RESTAURANT_SQL);
            preparedStatement.setString(1,restaurant.getNumeRestaurant());
            preparedStatement.setString(2,AddressService.fromAddressToString(restaurant.getAdresaRestaurant()));
            preparedStatement.setInt(3,restaurant.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storage.put(restaurant.getID(), restaurant);
    }

    @Override
    public void delete(Restaurant restaurant) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESATAURANT_SQL);
            preparedStatement.setInt(1, restaurant.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storage.remove(restaurant.getID());
    }
}
