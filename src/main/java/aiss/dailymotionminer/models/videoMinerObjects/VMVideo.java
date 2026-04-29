package aiss.dailymotionminer.models.videoMinerObjects;

import java.util.ArrayList;
import java.util.List;

public class VMVideo {

    private String id;
    private String name;
    private String description;
    private String releaseTime;
    private VMUser author;
    private List<VMComment> VMComments = new ArrayList<>();
    private List<VMCaption> VMCaptions = new ArrayList<>();

    public VMVideo(String id, String name, String description, String releaseTime, VMUser author, List<VMComment> VMComments, List<VMCaption> VMCaptions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.author = author;
        this.VMComments = VMComments;
        this.VMCaptions = VMCaptions;
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public VMUser getAuthor() {
        return author;
    }

    public void setAuthor(VMUser author) {
        this.author = author;
    }

    public List<VMComment> getComments() {
        return VMComments;
    }

    public void setComments(List<VMComment> VMComments) {
        this.VMComments = VMComments;
    }

    public List<VMCaption> getCaptions() {
        return VMCaptions;
    }

    public void setCaptions(List<VMCaption> VMCaptions) {
        this.VMCaptions = VMCaptions;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", author=" + author +
                ", comments=" + VMComments +
                ", captions=" + VMCaptions +
                '}';
    }
}
