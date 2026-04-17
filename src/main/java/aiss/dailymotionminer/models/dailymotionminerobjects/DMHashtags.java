package aiss.dailymotionminer.models.dailymotionminerobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hashtags"
})

public class DMHashtags {

    @JsonProperty("hashtags")
    private String[] hashtags;

    @JsonProperty("hashtags")
    public String[] getHashtags() {
        return hashtags;
    }

    @JsonProperty("hashtags")
    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DMHashtags.class.getName())
                .append('@')
                .append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("hashtags=");
        sb.append(hashtags == null ? "<null>" : Arrays.toString(hashtags));
        sb.append(']');
        return sb.toString();
    }

}
