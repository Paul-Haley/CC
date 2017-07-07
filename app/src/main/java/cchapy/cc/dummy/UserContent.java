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

    /**
     * A map of static users, by ID.
     */
    public static final Map<String, User> ITEM_MAP = new HashMap<String, User>();

    private static final int COUNT = 25;

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

    /**
     * A dummy item representing a piece of userName.
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
