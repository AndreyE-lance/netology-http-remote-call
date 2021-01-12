package task1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fact {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final Integer upvotes;
    private final Integer userUpvotes;
    private final Date createdAt;

    public Fact(@JsonProperty("_id") String id,
                @JsonProperty("text") String text,
                @JsonProperty("type") String type,
                @JsonProperty("user") String user,
                @JsonProperty("upvotes") Integer upvotes,
                @JsonProperty("userUpvotes") Integer userUpvotes,
                @JsonProperty("createdAt") Date createdAt) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
        this.userUpvotes = userUpvotes;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                ", userUpvotes=" + userUpvotes +
                ", createdAt=" + createdAt +
                '}';
    }
}
