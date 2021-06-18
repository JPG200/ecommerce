package infrastructure.adapters.out;
import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;
import application.domain.*;
import application.ports.out.ClientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository("sql")
public class SqlClientRepository implements ClientRepository {
    private final JdbcTemplate jdbcTemplate;

    public SqlClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Client> productRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<Client> getClientById(IdentificationNumber IdentificationNumber) {
        final String sql = "SELECT * FROM CLIENT WHERE CLIENT_ID = ?";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, IdentificationNumber.getValue());
        };
        final ResultSetExtractor<Optional<Client>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final Client Client = fromResultSet(rs);
                return Optional.of(Client);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void updateClient(Client Client){
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE CLIENT SET CLIENT_NAME = ? , CLIENT_LASTNAME = ? , CLIENT_UBICATION = ?, CLIENT_TYPEID = ? WHERE CLIENT_ID=?");




            preparedStatement.setString(5, Client.getidentificationNumber().getValue());
            preparedStatement.setString(1, Client.getclientName().getValue());
            preparedStatement.setString(2, Client.getclientLastName().getValue());
            preparedStatement.setString(3, Client.getclientUbication().getValue());
            preparedStatement.setString(4, Client.getIdentificationType().name());

            return preparedStatement;
        } );
    }



    public void deleteClient(Client Client){

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM CLIENT WHERE PRODUCT_ID=?");

            preparedStatement.setString(1, Client.getidentificationNumber().getValue());

            return preparedStatement;
        });}



    @Override
    public void storeClient(Client Client) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO CLIENT (CLIENT_ID, CLIENT_NAME, CLIENT_LASTNAME, CLIENT_UBICATION, CLIENT_TYPEID) VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, Client.getidentificationNumber().getValue());
            preparedStatement.setString(2, Client.getclientName().getValue());
            preparedStatement.setString(3, Client.getclientLastName().getValue());
            preparedStatement.setString(4, Client.getclientUbication().getValue());
            preparedStatement.setString(5, Client.getIdentificationType().name());


            return preparedStatement;
        });
    }

    @Override
    public Collection<Client> listClient(int limit, int skip) {
        final String sql = "SELECT * FROM CLIENT LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productRowMapper, limit, skip);
    }

    @Override
    public Integer countClient(){
        String sql = "select count(*) from CLIENT";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    private static Client fromResultSet(ResultSet rs) throws SQLException {
        return new Client(
                new NonEmptyString(rs.getString("CLIENT_NAME")),
                new NonEmptyString(rs.getString("CLIENT_LASTNAME")),
                new NonEmptyString(rs.getString("CLIENT_UBICATION")),
                new IdentificationNumber(rs.getString("CLIENT_ID")),
                IdentificationType.valueOf(rs.getString("CLIENT_TYPEID"))
        );
    }

}
