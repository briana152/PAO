package persistente;

import clase.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static persistente.util.DatabaseConnectionUtils.getDatabaseConnection;
public class ClientRepository implements GenericRepository<Client> {

/**
 * @author cvoinea
 * <p>
 * Repositories define an elegant method for storing, updating, and extracting the data stored from JAVA applications.
 * Usually they have a 1-to-1 relation with the entities. Any entity that should be persisted should have a repository
 *
 *
 * CRUD -> create read update delete
 */

    private final Map<String, Client> storage = new HashMap<>();
    private static final String INSERT_CLIENT_SQL = "INSERT INTO client (userName, fullName) VALUES (?, ?)";
    private static final String SELECT_ALL_CLIENT_SQL = "SELECT * FROM client";
    private static final String SELECT1_CLIENT_SQL = "SELECT ? from client";
    private static final String SELECT2_CLIENT_SQL = "SELECT ? from client WHERE ?";
    private static final String UPDATE_CLIENT_SQL = "UPDATE client SET ? = ? WHERE ?";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM client WHERE ?";

    private final Connection connection;

    private static volatile ClientRepository instance;

    private ClientRepository() {
        this.connection = getDatabaseConnection();
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            synchronized (ClientRepository.class) {
                if (instance == null) {
                    instance = new ClientRepository();
                }
            }
        }
        return instance;
    }

    public void fetchFromTable(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENT_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String u = resultSet.getNString("userName");
                String f = resultSet.getNString("fullName");
                storage.put(u,new Client(u,f));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Client save(Client entity) {
        storage.put(entity.getUserName(), entity);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL);
            preparedStatement.setString(1, entity.getUserName());
            preparedStatement.setString(2, entity.getNumeClient());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Client> findById(String userName) {
        return Optional.ofNullable(storage.get(userName));
    }

    @Override
    public void update(Client entity) {
        storage.put(entity.getUserName(), entity);
    }

    @Override
    public void delete(Client entity) {
        storage.remove(entity.getUserName());
    }
}