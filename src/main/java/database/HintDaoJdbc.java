package database;

import kirjasto.Hint;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HintDaoJdbc implements HintDao {

    @SneakyThrows
    @Override
    public Hint getHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection().prepareStatement("SELECT * FROM hints WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Hint temp = new Hint(rs.getString("header"), rs.getString("link"));
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return temp;
        } else {
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return null;
        }
    }

    @Override
    public List<Hint> getAllHints() {
        return null;
    }

    @SneakyThrows
    @Override
    public void addHint(Hint hint) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection().prepareStatement("INSERT INTO hints VALUES (DEFAULT, ?,?)");
        ps.setString(1, hint.getHeader());
        ps.setString(2, hint.getLink());
        ps.executeUpdate();
        DbUtils.closeQuietly(connector.getConnection());
        DbUtils.closeQuietly(ps);
    }

    @Override
    public void removeHint(int id) {

    }
}
