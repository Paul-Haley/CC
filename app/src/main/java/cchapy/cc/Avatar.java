package cchapy.cc;

/**
 * Class for internal representation of Avatars
 */
public class Avatar {
    public static final String MALE = "M", FEMALE = "F";
    public static final float FADED_STAR = 0.25f;

    private final int id;
    private final int rarity; // 1 <= rarity <= 3
    private final int price; // 0 <= price
    private final String name; // name of the avatar
    private final String description; // description of the avatar
    private final int image_M_Main, image_M_Alt, image_F_Main, image_F_Alt;

    public Avatar(int id, int rarity, int price, String name, String description,
                  int image_M_Main, int image_M_Alt, int image_F_Main, int image_F_Alt) {
        //TODO: Check if there is a need for the avatar owned to be considered.
        this.id = id;
        this.rarity = rarity;
        this.price = price;
        this.name = name;
        this.description = description;
        this.image_M_Main = image_M_Main;
        this.image_M_Alt = image_M_Alt;
        this.image_F_Main = image_F_Main;
        this.image_F_Alt = image_F_Alt;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImage_M_Main() {
        return image_M_Main;
    }

    public int getImage_M_Alt() {
        return image_M_Alt;
    }

    public int getImage_F_Main() {
        return image_F_Main;
    }

    public int getImage_F_Alt() {
        return image_F_Alt;
    }

    /**
     * Gender avatar for mains
     * @param gender M for male, F for female (anything but M is also female)
     * @return gender specific avatar
     */
    public int getImageMainByGender(String gender) {
        return gender.equals(MALE) ? getImage_M_Main() : getImage_F_Main();
    }

    /**
     * Gender avatar for alts
     * @param gender M for male, F for female (anything but M is also female)
     * @return gender specific avatar
     */
    public int getImageAltByGender(String gender) {
        return gender.equals(MALE) ? getImage_M_Alt() : getImage_F_Alt();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Avatar) {
            if (((Avatar)other).getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

}
