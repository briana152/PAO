package persistente;

import clase.Livrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static persistente.util.DatabaseConnectionUtils.getDatabaseConnection;

public class LivratorRepository implements GenericRepository<Livrator> {
    private final Map<Integer, Livrator> storage = new HashMap<>();
    private static final String INSERT_LIVRATOR_SQL = "INSERT INTO livrator (fullName, varsta) VALUES (?, ?)";
    private static final String SELECT_ALL_LIVRATOR_SQL = "SELECT * FROM livrator";
    private static final String SELECT_ALL_WHERE_LIVRATOR_SQL = "SELECT * FROM livrator WHERE fullName = ? AND varsta = ? ORDER BY ID DESC";

    private static final String UPDATE_LIVRATOR_SQL = "UPDATE livrator SET varsta = ? WHERE ID = ?";
    private static final String DELETE_LIVRATOR_SQL = "DELETE FROM livrator WHERE ID = ?";

    private final Connection connection;

    private static volatile LivratorRepository instance;

    private LivratorRepository() {
        this.connection = getDatabaseConnection();
    }

    public static LivratorRepository getInstance() {
        if (instance == null) {
            synchronized (LivratorRepository.class) {
                if (instance == null) {
                    instance = new LivratorRepository();
                }
            }
        }
        return instance;
    }

    public void fetchFromTable(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIVRATOR_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String n = resultSet.getString("fullName");
                String v = resultSet.getString("varsta");
                storage.put(id, new Livrator(id,n,Integer.parseInt(v)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Livrator save(Livrator livrator) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_LIVRATOR_SQL);
            preparedStatement1.setString(1, livrator.getFullName());
            preparedStatement1.setInt(2, livrator.getVarsta());
            preparedStatement1.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_ALL_WHERE_LIVRATOR_SQL);
            preparedStatement2.setString(1,livrator.getFullName());
            preparedStatement2.setInt(2,livrator.getVarsta());
            ResultSet resultSet = preparedStatement2.executeQuery();
            if (resultSet.next()){
                livrator.setID(resultSet.getInt("ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        storage.put(livrator.getID(), livrator);

        return livrator;
    }

    @Override
    public List<Livrator> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Livrator> findById(String... values) {
        return Optional.ofNullable(storage.get(Integer.parseInt(values[0])));
    }

    @Override
    public void update(Livrator livrator, String... newValues) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LIVRATOR_SQL);
            preparedStatement.setInt(1,livrator.getVarsta());
            preparedStatement.setInt(2,livrator.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storage.put(livrator.getID(), livrator);
    }

    @Override
    public void delete(Livrator livrator) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LIVRATOR_SQL);
            preparedStatement.setInt(1, livrator.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        storage.remove(livrator.getID());
    }
}
