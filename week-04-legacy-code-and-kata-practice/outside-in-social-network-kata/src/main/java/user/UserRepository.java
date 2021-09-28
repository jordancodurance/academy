package user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean isExistingUser(String username);

    void createUser(String username);

    Optional<User> findUser(String username);

    void updateTimeline(String username, String message);

    List<Post> findChronologicalTimeline(String username);

    void updateFollows(String username, String usernameToFollow);

}
