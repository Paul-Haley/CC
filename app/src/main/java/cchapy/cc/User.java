package cchapy.cc;

/**
 * Created by Hayley on 11/07/2017.
 * A class modelling a user in the system.
 */
public class User {
    private int id;
    private String userName;
    private int leafCount;
    private String gender;
    private int carbon;
    public String city;
    private int equippedAvatar;
    private int totalLeafCount;

    public User(int id, String userName, int leafCount, String gender, int carbon, String city, int equippedAvatar, int totalLeafCount) {
        this.id = id;
        this.userName = userName;
        this.leafCount = leafCount;
        this.gender = gender;
        this.carbon = carbon;
        this.city = city;
        this.equippedAvatar = equippedAvatar;
        this.totalLeafCount = totalLeafCount;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getLeafCount() {
        return leafCount;
    }

    public String getGender() {
        return gender;
    }

    public int getCarbon() {
        return carbon;
    }

    public String getCity() {
        return city;
    }

    public int getEquippedAvatar() {
        return equippedAvatar;
    }

    public int getTotalLeafCount() {
        return totalLeafCount;
    }

    @Override
    public String toString() {
        return userName;
    }
}

