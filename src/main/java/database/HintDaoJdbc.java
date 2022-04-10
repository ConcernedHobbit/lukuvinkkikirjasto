package database;

import kirjasto.BookHint;
import kirjasto.Hint;
import kirjasto.HintType;
import kirjasto.VideoHint;
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
    public BookHint getBookHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("SELECT * FROM hints " +
                        "LEFT JOIN book b on hints.id = b.hint WHERE b.hint=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            BookHint temp = new BookHint(rs.getString("header"),
                    HintType.valueOf(rs.getString("type")),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year"));
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return temp;
        } else {
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return null;
        }
    }

    @SneakyThrows
    @Override
    public VideoHint getVideoHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("SELECT * FROM hints " +
                        "LEFT JOIN video v on hints.id = v.hint WHERE v.hint=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            VideoHint temp = new VideoHint(rs.getString("header"),
                    HintType.valueOf(rs.getString("type")), rs.getString("url"),
                    rs.getString("comment"));
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
                    new Hint(rs.getString("header"),
                            HintType.valueOf(rs.getString("type"))));
        }
        DbUtils.closeQuietly(connector.getConnection(), st, rs);
        return list;
    }

    @SneakyThrows
    @Override
    public Integer addBookHint(BookHint hint) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("WITH hintbase AS (INSERT INTO hints " +
                        "VALUES (DEFAULT, ?,?) RETURNING id) INSERT INTO book (hint, author, publisher, year) " +
                        "SELECT id, ?, ?, ? FROM hintbase RETURNING hint");
        ps.setString(1, hint.getHeader());
        ps.setString(2, hint.getType().name());
        ps.setString(3, hint.getAuthor());
        ps.setString(4, hint.getPublisher());
        ps.setInt(5, hint.getYear());
        ResultSet rs = ps.executeQuery();
        rs.next();
        int returnable = rs.getInt(1);
        DbUtils.closeQuietly(connector.getConnection(), ps, rs);
        return returnable;
    }

    @SneakyThrows
    @Override
    public Integer addVideoHint(VideoHint hint) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("WITH hintbase AS (INSERT INTO hints " +
                        "VALUES (DEFAULT, ?,?) RETURNING id) INSERT INTO video (hint, url, comment) " +
                        "SELECT id, ?, ? FROM hintbase RETURNING hint");
        ps.setString(1, hint.getHeader());
        ps.setString(2, hint.getType().name());
        ps.setString(3, hint.getUrl());
        ps.setString(4, hint.getComment());
        ResultSet rs = ps.executeQuery();
        rs.next();
        int returnable = rs.getInt(1);
        DbUtils.closeQuietly(connector.getConnection(), ps, rs);
        return returnable;
    }

    @SneakyThrows
    @Override
    public void removeHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("DELETE FROM hints WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        DbUtils.closeQuietly(connector.getConnection());
        DbUtils.closeQuietly(ps);
    }

    @SneakyThrows
    @Override
    public void addTags(int id, String tags) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("INSERT INTO tags VALUES (DEFAULT, ?,?)");
        ps.setInt(1, id);

        String[] splitted = tags.split(",");
        for (String x : splitted) {
            if (x.trim().isBlank()) continue;
            ps.setString(2, x.trim());
            ps.executeUpdate();
        }
        DbUtils.closeQuietly(connector.getConnection());
        DbUtils.closeQuietly(ps);
    }

    @SneakyThrows
    @Override
    public List<String> findTags(String tag) {
        Connector connector = new Connector();
        PreparedStatement ps =
                connector.getConnection().prepareStatement("SELECT * FROM" +
                        " hints h LEFT JOIN tags t ON h.id = t.hint WHERE tag=?");
        ps.setString(1, tag);
        ResultSet rs = ps.executeQuery();

        if (rs.wasNull()) {
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return null;
        }

        ArrayList<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add("ID=" + rs.getInt("id") + " " +
                    new Hint(rs.getString("header"),
                            HintType.valueOf(rs.getString("type"))));
        }
        DbUtils.closeQuietly(connector.getConnection(), ps, rs);
        return list;
    }

    @SneakyThrows
    @Override
    public HintType getHintType(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("SELECT type FROM hints WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        HintType type = HintType.valueOf(rs.getString("type"));
        DbUtils.closeQuietly(connector.getConnection(), ps, rs);
        return type;
    }
}
