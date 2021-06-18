package infrastructure.adapters.out;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;
import application.domain.Product;
import application.domain.ProductId;
import application.domain.ProductState;
import application.ports.out.ProductRepository;
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
public class SqlProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public SqlProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<Product> getProductByName(NonEmptyString NonEmptyString) {
        final String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME LIKE %?%";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, NonEmptyString.getValue());
        };
        final ResultSetExtractor<Optional<Product>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final Product product = fromResultSet(rs);
                return Optional.of(product);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void updateProduct(Product Product){
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE PRODUCT SET PRODUCT_NAME = ? , PRODUCT_PRICE = ? , PRODUCT_TAX = ?, PRODUCT_DESCRIPTION = ? , PRODUCT_STATE = ? , PRODUCT_INVENTORY = ? WHERE PRODUCT_ID=?");

            preparedStatement.setInt(7, Product.getProductId().getValue());
            preparedStatement.setString(1, Product.getProductName().getValue());
            preparedStatement.setDouble(2, Product.getProductPrice().getValue());
            preparedStatement.setDouble(3, Product.getProductTax().getValue());
            preparedStatement.setString(4, Product.getProductDescription().getValue());
            preparedStatement.setString(5, Product.getProductState().name());
            preparedStatement.setInt(6, Product.getProductCI().getValue());


            return preparedStatement;
    } );
    }


    @Override
    public void deleteProduct(Product Product){

        jdbcTemplate.update(connection -> {
        final PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM PRODUCT WHERE PRODUCT_ID=?");

        preparedStatement.setInt(1, Product.getProductId().getValue());

        return preparedStatement;
    });}



    @Override
    public void storeProduct(Product Product) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_TAX, PRODUCT_DESCRIPTION, PRODUCT_STATE, PRODUCT_INVENTORY) VALUES (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, Product.getProductId().getValue());
            preparedStatement.setString(2, Product.getProductName().getValue());
            preparedStatement.setDouble(3, Product.getProductPrice().getValue());
            preparedStatement.setDouble(4, Product.getProductTax().getValue());
            preparedStatement.setString(5, Product.getProductDescription().getValue());
            preparedStatement.setString(6, Product.getProductState().name());
            preparedStatement.setInt(7, Product.getProductCI().getValue());


            return preparedStatement;
        });
    }

    @Override
    public Collection<Product> listProduct(int limit, int skip) {
        final String sql = "SELECT * FROM PRODUCT LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productRowMapper, limit, skip);
    }

    @Override
    public Integer countProduct(){
        String sql = "select count(*) from PRODUCT";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    private static Product fromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                new ProductId(rs.getInt("PRODUCT_ID")),
                new NonEmptyString(rs.getString("PRODUCT_NAME")),
                new NonEmptyPrice(rs.getDouble("PRODUCT_PRICE")),
                new NonEmptyTax(rs.getDouble("PRODUCT_TAX")),
                new NonEmptyString(rs.getString("PRODUCT_DESCRIPTION")),
                ProductState.valueOf(rs.getString("PRODUCT_STATE")),
                new NonEmptyCI(rs.getInt("PRODUCT_INVENTORY"))
        );
    }

}


