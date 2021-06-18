package infrastructure.adapters.out;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;
import application.domain.*;
import application.ports.out.SellRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class SqlSellRepository implements SellRepository {

    private final JdbcTemplate jdbcTemplate;

    public SqlSellRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Sell> productRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<Sell> getSellById(NonEmptyString NonEmptyString) {
        final String sql = "SELECT * FROM SELL WHERE CLIENT_ID = ?";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, NonEmptyString.getValue());
        };
        final ResultSetExtractor<Optional<Sell>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final Sell Sell = fromResultSet(rs);
                return Optional.of(Sell);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void updateSell(Sell Sell){
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE SELL SET PRODUCT_ID = ? , PRODUCT_NAME = ? , PRODUCT_PRICE = ? , PRODUCT_TAX = ? , PRODUCT_INVENTORY = ? " +
                            ", CLIENT_ID = ? , CLIENT_NAME = ? , CLIENT_LASTNAME = ? , CLIENT_UBICATION = ? , CLIENT_TYPEID = ? , STATE_SELL = ?" +
                            " WHERE SELL_ID=?");


            preparedStatement.setInt(1, Sell.getProductId().getValue());
            preparedStatement.setString(2, Sell.getProductName().getValue());
            preparedStatement.setDouble(3, Sell.getProductPrice().getValue());
            preparedStatement.setDouble(4, Sell.getProductTax().getValue());
            preparedStatement.setInt(5, Sell.getProductCI().getValue());
            preparedStatement.setString(6, Sell.getidClient().getValue());
            preparedStatement.setString(7, Sell.getclientName().getValue());
            preparedStatement.setString(8, Sell.getclientLastName().getValue());
            preparedStatement.setString(9, Sell.getclientUbication().getValue());
            preparedStatement.setString(10, Sell.getIdentificationType().name());
            preparedStatement.setString(11, Sell.getStateSell().name());
            preparedStatement.setString(12, Sell.getidSell().getValue());


            return preparedStatement;
        } );
    }



    public void deleteSell(Sell Sell){

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM SELL WHERE SELL_ID=?");

            preparedStatement.setString(1, Sell.getidSell().getValue());

            return preparedStatement;
        });}



    @Override
    public void storeSell(Sell Sell) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO CLIENT (CLIENT_ID, CLIENT_NAME, CLIENT_LASTNAME, CLIENT_UBICATION, CLIENT_TYPEID) VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, Sell.getidSell().getValue());
            preparedStatement.setInt(2, Sell.getProductId().getValue());
            preparedStatement.setString(3, Sell.getProductName().getValue());
            preparedStatement.setDouble(4, Sell.getProductPrice().getValue());
            preparedStatement.setDouble(5, Sell.getProductTax().getValue());
            preparedStatement.setInt(6, Sell.getProductCI().getValue());
            preparedStatement.setString(7, Sell.getidClient().getValue());
            preparedStatement.setString(8, Sell.getclientName().getValue());
            preparedStatement.setString(9, Sell.getclientLastName().getValue());
            preparedStatement.setString(10, Sell.getclientUbication().getValue());
            preparedStatement.setString(11, Sell.getIdentificationType().name());
            preparedStatement.setString(12, Sell.getStateSell().name());


            return preparedStatement;
        });
    }

    @Override
    public Collection<Sell> listSell(int limit, int skip) {
        final String sql = "SELECT * FROM SELL LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productRowMapper, limit, skip);
    }

    @Override
    public Integer countSell(){
        String sql = "select count(*) from SELL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    private static Sell fromResultSet(ResultSet rs) throws SQLException {
        return new Sell(
                new NonEmptyString(rs.getString("SELL_ID")),
                new ProductId(rs.getInt("PRODUCT_ID")),
                new NonEmptyString(rs.getString("PRODUCT_NAME")),
                new NonEmptyPrice(rs.getDouble("PRODUCT_PRICE")),
                new NonEmptyTax(rs.getDouble("PRODUCT_TAX")),
                new NonEmptyCI(rs.getInt("PRODUCT_INVENTORY")),
                new NonEmptyString(rs.getString("CLIENT_NAME")),
                new NonEmptyString(rs.getString("CLIENT_LASTNAME")),
                new NonEmptyString(rs.getString("CLIENT_UBICATION")),
                new IdentificationNumber(rs.getString("CLIENT_ID")),
                IdentificationType.valueOf(rs.getString("CLIENT_TYPEID")),
                StateSell.valueOf(rs.getString("STATE_SELL"))
        );
    }
}
