package aiss.dailymotionminer.models.dailymotionminerobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DMVideoSearch {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("explicit")
    private Boolean explicit;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("list")
    private List<DMVideo> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<DMVideo> getList() {
        return list;
    }

    public void setList(List<DMVideo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "DMVideoSearch{" +
                "page=" + page +
                ", limit=" + limit +
                ", explicit=" + explicit +
                ", hasMore=" + hasMore +
                ", list=" + list +
                '}';
    }
}