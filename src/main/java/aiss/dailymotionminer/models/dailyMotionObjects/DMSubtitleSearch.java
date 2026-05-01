package aiss.dailymotionminer.models.dailyMotionObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DMSubtitleSearch {

    @JsonProperty("list")
    private List<DMSubtitle> list;

    @JsonProperty("has_more")
    private Boolean hasMore;

    public List<DMSubtitle> getList() {
        return list;
    }

    public void setList(List<DMSubtitle> list) {
        this.list = list;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}