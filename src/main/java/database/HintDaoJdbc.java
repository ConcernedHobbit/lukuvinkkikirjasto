package database;

import kirjasto.*;
import lombok.Generated;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Generated
public class HintDaoJdbc implements HintDao {

    @SneakyThrows
    @Override
    public BookHint getBookHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("SELECT h.header, h.type, b.author," +
                        " b.publisher, b.year, STRING_AGG(t.tag, ', ') " +
                        "FROM hints h " +
                        "LEFT JOIN book b on h.id = b.hint " +
                        "LEFT JOIN tags t on h.id = t.hint " +
                        "WHERE b.hint = ? " +
                        "GROUP BY h.header, h.type, b.author, b.publisher, b.year");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            BookHint temp = new BookHint(rs.getString("header"),
                    HintType.valueOf(rs.getString("type")),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year"));
            temp.setTags(rs.getString("string_agg"));
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
                .prepareStatement("SELECT h.header, h.type, v.url, " +
                        "v.comment, STRING_AGG(t.tag, ', ') " +
                        "FROM hints h " +
                        "LEFT JOIN video v on h.id = v.hint " +
                        "LEFT JOIN tags t on h.id = t.hint " +
                        "WHERE v.hint = ? " +
                        "GROUP BY h.header, h.type, v.url, v.comment");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            VideoHint temp = new VideoHint(rs.getString("header"),
                    HintType.valueOf(rs.getString("type")), rs.getString("url"),
                    rs.getString("comment"));
            temp.setTags(rs.getString("string_agg"));
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return temp;
        } else {
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return null;
        }
    }

    @SneakyThrows
    @Override
    public BlogHint getBlogHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("SELECT h.header, h.type, b.author, b.url, " +
                        "STRING_AGG(t.tag, ', ') " +
                        "FROM hints h " +
                        "LEFT JOIN blog b on h.id = b.hint " +
                        "LEFT JOIN tags t on h.id = t.hint " +
                        "WHERE b.hint = ? " +
                        "GROUP BY h.header, h.type, b.author, b.url");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            BlogHint temp = new BlogHint(rs.getString("header"),
                    HintType.valueOf(rs.getString("type")), rs.getString("author"),
                    rs.getString("url"));
            temp.setTags(rs.getString("string_agg"));
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return temp;
        } else {
            DbUtils.closeQuietly(connector.getConnection(), ps, rs);
            return null;
        }
    }

    @SneakyThrows
    @Override
    public PodcastHint getPodcastHint(int id) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("SELECT h.header, h.type, p.author, " +
                        "p.name, p.description, STRING_AGG(t.tag, ', ') " +
                        "FROM hints h " +
                        "LEFT JOIN podcast p on h.id = p.hint " +
                        "LEFT JOIN tags t on h.id = t.hint " +
                        "WHERE p.hint = ? " +
                        "GROUP BY h.header, h.type, p.author, p.name, p.description");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PodcastHint temp = new PodcastHint(rs.getString("header"),
                    HintType.valueOf(rs.getString("type")), rs.getString("author"),
                    rs.getString("name"), rs.getString("description"));
            temp.setTags(rs.getString("string_agg"));
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
    public Integer addPodcastHint(PodcastHint hint) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("WITH hintbase AS (INSERT INTO hints " +
                        "VALUES (DEFAULT, ?,?) RETURNING id) INSERT INTO podcast (hint, author, name, description) " +
                        "SELECT id, ?, ?, ? FROM hintbase RETURNING hint");
        ps.setString(1, hint.getHeader());
        ps.setString(2, hint.getType().name());
        ps.setString(3, hint.getAuthor());
        ps.setString(4, hint.getName());
        ps.setString(5, hint.getDescription());
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
    public Integer addBlogHint(BlogHint hint) {
        Connector connector = new Connector();
        PreparedStatement ps = connector.getConnection()
                .prepareStatement("WITH hintbase AS (INSERT INTO hints " +
                        "VALUES (DEFAULT, ?,?) RETURNING id) INSERT INTO blog (hint, author, url) " +
                        "SELECT id, ?, ? FROM hintbase RETURNING hint");
        ps.setString(1, hint.getHeader());
        ps.setString(2, hint.getType().name());
        ps.setString(3, hint.getAuthor());
        ps.setString(4, hint.getUrl());
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
    public List<String> findWithHeader(String header) {
        Connector connector = new Connector();
        PreparedStatement ps =
                connector.getConnection().prepareStatement("SELECT * FROM" +
                        " hints WHERE header ~* ?");
        ps.setString(1, header);
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
    public List<String> findWithType(HintType type) {
        Connector connector = new Connector();
        PreparedStatement ps =
                connector.getConnection().prepareStatement("SELECT * FROM" +
                        " hints WHERE type=?");
        ps.setString(1, type.name());
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
