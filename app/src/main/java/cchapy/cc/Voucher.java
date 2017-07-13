package cchapy.cc;

/**
 * Created by Hayley on 11/07/2017.
 * A class modelling a user in the system.
 */
public class Voucher {
    private int id;
    private String voucherName;
    private int price;
    private String description;
    public String time;
    private int image;
    private String shop;

    public Voucher(int id, String voucherName, int price, String description, String time, int image, String shop) {
        this.id = id;
        this.voucherName = voucherName;
        this.price = price;
        this.description = description;
        this.time = time;
        this.image = image;
        this.shop = shop;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return voucherName;
    }

    public String getName() { return voucherName; }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public String getTime() { return time; }

    public String getShop() {
        return shop;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object instanceof Voucher) {
            return false;
        }

        return this.getId() == ((Voucher) object).getId();
    }
}

