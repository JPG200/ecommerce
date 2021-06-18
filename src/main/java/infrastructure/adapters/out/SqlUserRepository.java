package infrastructure.adapters.out;

import application.commons.NonEmptyString;
import application.domain.*;
import application.ports.out.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class SqlUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public SqlUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<User> productRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<User> getUserById(NonEmptyString NonEmptyString) {
        final String sql = "SELECT USER.USER_TYPE FROM USER WHERE USER_NAME LIKE '?'";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, NonEmptyString.getValue());
        };
        final ResultSetExtractor<Optional<User>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final User User = fromResultSet(rs);
                return Optional.of(User);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void updateUser(User User){
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE USER SET USER_PASS = ? , USER_TYPE = ? WHERE USER_NAME LIKE '?'");

            preparedStatement.setString(3, User.getUserName().getValue());
            preparedStatement.setString(1, User.getUserPassword().getValue());
            preparedStatement.setString(2, User.getUserType().name());

            return preparedStatement;
        } );
    }

    public void deleteUser(User User){
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM USER WHERE USER_NAME LIKE '?' AND USER_PASS=?");

            preparedStatement.setString(1, User.getUserName().getValue());
            preparedStatement.setString(2, User.getUserPassword().getValue());
            return preparedStatement;
        });}



    @Override
    public void storeUser(User User) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO USER (USER_NAME, USER_PASS, USER_TYPE) VALUES (?, ?, ?)");

            preparedStatement.setString(1, User.getUserName().getValue());
            preparedStatement.setString(2, User.getUserPassword().getValue());
            preparedStatement.setString(3, User.getUserType().name());
            return preparedStatement;
        });
    }

    @Override
    public Collection<User> listUser(int limit, int skip) {
        final String sql = "SELECT * FROM USER LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productRowMapper, limit, skip);
    }

    @Override
    public Integer countUser(){
        String sql = "select count(*) from USER";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    private static User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
                new NonEmptyString(rs.getString("USER_NAME")),
                new NonEmptyString(rs.getString("USER_PASS")),
                TypeUser.valueOf(rs.getString("USER_TYPE"))
        );
    }
}
