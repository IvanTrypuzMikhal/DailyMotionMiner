package aiss.dailymotionminer.models.videoMinerObjects;

import java.util.List;

public class VMChannel {
    private String id;
    private String name;
    private String description;
    private String createdTime;
    private List<VMVideo> VMVideos;

    public VMChannel(String id, String name, String description, String createdTime, List<VMVideo> VMVideos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.VMVideos = VMVideos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<VMVideo> getVideos() {
        return VMVideos;
    }

    public void setVideos(List<VMVideo> VMVideos) {
        this.VMVideos = VMVideos;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", videos=" + VMVideos +
                '}';
    }
}
