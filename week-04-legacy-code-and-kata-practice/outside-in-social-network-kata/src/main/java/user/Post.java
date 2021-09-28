package user;

import java.time.Instant;

public class Post {

    private final String message;
    private final Instant postedOn;
    private final String postedBy;

    public Post(String message, Instant postedOn, String postedBy) {
        this.message = message;
        this.postedOn = postedOn;
        this.postedBy = postedBy;
    }

    public String getMessage() {
        return message;
    }

    public Instant getPostedOn() {
        return postedOn;
    }

    public String getPostedBy() {
        return postedBy;
    }

}
