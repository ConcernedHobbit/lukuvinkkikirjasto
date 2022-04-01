
package kirjasto;

public class Hint {
    private String header;
    private String link;
    
    public Hint(String header, String link) {
        this.header = header;
        this.link = link;
    }

    public String getHeader() {
        return header;
    }

    public String getLink() {
        return link;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public String toString() {
        return ("Otsikko: " + getHeader() + ", Linkki: " + getLink());
    }
    
}
