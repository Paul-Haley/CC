package cchapy.cc;

/**
 * Class for internal representation of Avatars
 */
public class Avatar {
    private final int id;
    private final int rarity; // 1 <= rarity <= 3
    private final int price; // 0 <= price
    private final boolean owned; // true if the user owns the current avatar
    private final String name; // name of the avatar
    private final String description; // description of the avatar

    public Avatar(int id, String name, int rarity, int price, boolean owned, String description) {
        //TODO: Check if there is a need for the avatar owned to be considered.
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.price = price;
        this.owned = owned;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }

    public int getPrice() {
        return price;
    }

    public boolean getOwned() {
        return owned;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }

}
