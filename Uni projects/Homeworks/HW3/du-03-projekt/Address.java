
public class Address {

    /** Ulice a číslo domu. */
    private String street;

    /** Město. */
    private String city;

    /** Poštovní směrovací číslo. */
    private String zipCode;

    /** Stát. */
    private String country;

    /** Poznámka (označení schránky, patro, rok narození). */
    private String note;

    public Address(String street, String city, String zipCode) {
        this(street, city, zipCode, "CZ");
    }

    public Address(String street, String city, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode + ", " + country;
    }

}
