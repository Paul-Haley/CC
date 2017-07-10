package cchapy.cc.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample userName for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class UserContent {

    /**
     * An array of static users.
     */
    public static final List<User> ITEMS = new ArrayList<User>();

    //Dummy array of friend users
    public static final List<User> FRIENDS = new ArrayList<User>();

    //Dummy array of local users
    public static final List<User> LOCALS = new ArrayList<User>();

    /**
     * A map of static users, by ID.
     */
    public static final Map<String, User> ITEM_MAP = new HashMap<String, User>();

    //Dummy Map of friends, by id
    public static final Map<String, User> FRIENDS_MAP = new HashMap<String, User>();


    private static final int COUNT = 25;

    //Dummy friend count
    private static final int FRIENDS_COUNT = 25;

    //Dummy local count
    private static final int LOCAL_COUNT = 25;


    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyUser(i));
        }
    }

    private static void addItem(User item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static User createDummyUser(int position) {
        return new User(String.valueOf(position), "User " + position, "Dalian", 1000);
    }

    public static List<User> getFriendUsers() {
        for(int i = 1; i <= FRIENDS_COUNT; i++){
            
            //Fetch friend
            User friend = createDummyUser(i);
            FRIENDS.add(friend);
        }
        return FRIENDS;
    }

    /**
     * A User Class
     */
    public static class User {
        public final String id;
        public final String userName;
        public final String city;
        public final int leafCount;

        public User(String id, String content, String details, int leafCount) {
            this.id = id;
            this.userName = content;
            this.city = details;
            this.leafCount = leafCount;
        }

        @Override
        public String toString() {
            return userName;
        }
    }
}
