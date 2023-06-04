package persistente;

import clase.Client;
import servicii.AuditService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static persistente.util.DatabaseConnectionUtils.getDatabaseConnection;
public class ClientRepository implements GenericRepository<Client> {
    private final AuditService auditService = AuditService.getInstance();
    private final Map<String, Client> storage = new HashMap<>();
    private static final String INSERT_CLIENT_SQL = "INSERT INTO client (userName, fullName) VALUES (?, ?)";
    private static final String SELECT_ALL_CLIENT_SQL = "SELECT * FROM client";
    private static final String UPDATE_CLIENT_SQL = "UPDATE client SET fullName = ? WHERE userName = ?";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM client WHERE userName = ?";

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
            auditService.scrieCSV(preparedStatement.toString());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Client save(Client entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL);
            preparedStatement.setString(1, entity.getUserName());
            preparedStatement.setString(2, entity.getNumeClient());
            preparedStatement.execute();
            auditService.scrieCSV(preparedStatement.toString());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        storage.put(entity.getUserName(), entity);

        return entity;
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Client> findById(String... values) {
        return Optional.ofNullable(storage.get(values[0]));
    }

    @Override
    public void update(Client client, String... newValues) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL);
            preparedStatement.setString(1,client.getNumeClient());
            preparedStatement.setString(2,client.getUserName());
            preparedStatement.execute();
            auditService.scrieCSV(preparedStatement.toString());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        storage.put(client.getUserName(), client);
    }

    @Override
    public void delete(Client client) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL);
            String userName = client.getUserName();
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
            auditService.scrieCSV(preparedStatement.toString());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        storage.remove(client.getUserName());
    }
}