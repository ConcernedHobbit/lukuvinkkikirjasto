package database;

import kirjasto.Hint;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
            Hint temp = new Hint(rs.getString("header"), rs.getString("link"), rs.getString("author"), rs.getString("publisher"), rs.getInt("year"), rs.getInt("class"));
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return temp;
        } else {
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return null;
        }
    }

    @SneakyThrows
    @Override
    public List<String> getAllHints() {
        Connector connector = new Connector();
        Statement st = connector.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM hints");

        if (rs.wasNull()) {
            DbUtils.closeQuietly(connector.getConnection(), st, rs);
            return null;
        }

        ArrayList<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add("ID=" + rs.getInt("id") + " " +
            new Hint(rs.getString("header"), rs.getString("link"), rs.getString("author"), rs.getString("publisher"), rs.getInt("year"), rs.getInt("class")));
//                    new Hint(rs.getString("header"), rs.getString("link")));
        }
        DbUtils.closeQuietly(connector.getConnection(), st, rs);
        return list;
    }

    @SneakyThrows
    @Override
    public void addHint(Hint hint) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection().prepareStatement("INSERT INTO hints VALUES (DEFAULT, ?,?,?,?,?,?)");
        ps.setInt(1, hint.getHint_type());
        ps.setInt(2, hint.getYear());
        ps.setString(3, hint.getHeader());
        ps.setString(4, hint.getLink());
        ps.setString(5, hint.getAuthor());
        ps.setString(6, hint.getPublisher());
        ps.executeUpdate();
        DbUtils.closeQuietly(connector.getConnection());
        DbUtils.closeQuietly(ps);
    }

    @SneakyThrows
    @Override
    public void removeHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection().prepareStatement("DELETE FROM hints WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        DbUtils.closeQuietly(connector.getConnection());
        DbUtils.closeQuietly(ps);
    }
}
