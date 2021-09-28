package user;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String username;
    private final List<Post> timeline;
    private final List<String> follows;

    private User(String username, List<Post> timeline, List<String> follows) {
        this.username = username;
        this.timeline = timeline;
        this.follows = follows;
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getTimeline() {
        return timeline;
    }

    public List<String> getFollows() {
        return follows;
    }

    public static class UserBuilder {

        private String username;
        private List<Post> timeline;
        private List<String> follows;

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withTimeline(List<Post> timeline) {
            this.timeline = timeline;
            return this;
        }

        public UserBuilder withFollows(List<String> follows) {
            this.follows = follows;
            return this;
        }

        public User createUser() {
            return new User(username, timeline, follows);
        }
    }

}

