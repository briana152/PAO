package persistente;

import clase.AdresaClient;
import servicii.AddressService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static persistente.util.DatabaseConnectionUtils.getDatabaseConnection;

public class AdresaClientRepository implements GenericRepository<AdresaClient> {
    private final Map<Integer, AdresaClient> storage = new HashMap<>();
    private static final String INSERT_ADRESA_CLIENT_SQL = "INSERT INTO adresa_client (userName_client, adresa) VALUES (?, ?)";
    private static final String SELECT_ALL_ADRESA_CLIENT_SQL = "SELECT * FROM adresa_client";
    private static final String SELECT_ALL_WHERE_ADRESA_CLIENT_SQL = "SELECT * FROM adresa_client WHERE userName_client = ? AND adresa = ? ORDER BY ID DESC";

    private static final String UPDATE_ADRESA_CLIENT_SQL = "UPDATE adresa_client SET adresa = ? WHERE ID = ?";
    private static final String DELETE_ADRESA_CLIENT_SQL = "DELETE FROM adresa_client WHERE ID = ?";

    private final Connection connection;

    private static volatile AdresaClientRepository instance;

    private AdresaClientRepository() {
        this.connection = getDatabaseConnection();
    }

    public static AdresaClientRepository getInstance() {
        if (instance == null) {
            synchronized (AdresaClientRepository.class) {
                if (instance == null) {
                    instance = new AdresaClientRepository();
                }
            }
        }
        return instance;
    }

    public void fetchFromTable(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADRESA_CLIENT_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String u = resultSet.getString("userName_client");
                String a = resultSet.getString("adresa");
                AdresaClient adresaClient = AddressService.fromStringToClientAddress(a);
                adresaClient.setID(id);
                adresaClient.setUserNameClient(u);
                storage.put(id, adresaClient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public AdresaClient save(AdresaClient adresaClient) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_ADRESA_CLIENT_SQL);
            preparedStatement1.setString(1, adresaClient.getUserNameClient());
            preparedStatement1.setString(2, AddressService.fromClientAddressToString(adresaClient));
            preparedStatement1.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_ALL_WHERE_ADRESA_CLIENT_SQL);
            preparedStatement2.setString(1,adresaClient.getUserNameClient());
            preparedStatement2.setString(2,AddressService.fromClientAddressToString(adresaClient));
            ResultSet resultSet = preparedStatement2.executeQuery();
            if (resultSet.next()){
                adresaClient.setID(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        storage.put(adresaClient.getID(), adresaClient);

        return adresaClient;
    }

    @Override
    public List<AdresaClient> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<AdresaClient> findById(String... values) {
        return Optional.ofNullable(storage.get(Integer.parseInt(values[0]))).filter(adresaClient -> adresaClient.getUserNameClient().equals(values[1]));
    }

    @Override
    public void update(AdresaClient adresaClient, String... newValues) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADRESA_CLIENT_SQL);
            preparedStatement.setString(1,AddressService.fromClientAddressToString(adresaClient));
            preparedStatement.setInt(2,adresaClient.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storage.put(adresaClient.getID(), adresaClient);
    }

    @Override
    public void delete(AdresaClient adresaClient) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADRESA_CLIENT_SQL);
            preparedStatement.setInt(1, adresaClient.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storage.remove(adresaClient.getID());
    }
}
