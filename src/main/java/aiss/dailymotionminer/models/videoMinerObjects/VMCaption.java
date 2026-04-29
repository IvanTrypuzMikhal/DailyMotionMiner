package aiss.dailymotionminer.models.videoMinerObjects;

public class VMCaption {
    private String id;
    private String link;
    private String language;

    public VMCaption(String id, String link, String language) {
        this.id = id;
        this.link = link;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return link;
    }

    public void setName(String name) {
        this.link = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", name='" + link + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
