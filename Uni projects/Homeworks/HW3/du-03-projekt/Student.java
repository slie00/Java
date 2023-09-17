
import java.util.Objects;

public class Student {

    /** Identifikační číslo studenta v InSIS. Číslo se generuje při
     * vytváření záznamu o studentovi a není možné ho dodatečně měnit. */
    private String insisId;

    /** Jméno a příjmení. */
    private String name;

    /** Věk. */
    private int age;

    /** Pohlaví (true: muž, false: žena). */
    private boolean male;

    /** Fakulta. */
    private Faculty faculty;

    /** Emailová adresa. */
    private String email;

    /** Adresa trvalého pobytu. */
    private Address permanentAddress;

    /** Kontaktní adresa (pro zasílání korespondence). */
    private Address mailingAddress;

    public Student(String insisId, String name, int age, boolean male) {
        this.insisId = insisId;
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public Student(String insisId, String name, int age, boolean male, String email) {
        this(insisId, name, age, male);

        this.email = email;
    }

    public Student(String insisId, String name, int age, boolean male, Faculty faculty, String email) {
        this(insisId, name, age, male, email);

        this.faculty = faculty;
    }

    public String getInsisId() {
        return insisId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return !male;
    }

    public void setFemale(boolean female) {
        this.male = !female;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    public String toString() {
        String result = insisId + " " + name + " (";

        if (male) {
            result += "M";
        } else {
            result += "Ž";
        }

        result += "), " + age + " ";

        if (age == 1) {
            result += "rok";
        } else if (age < 5) {
            result += "roky";
        } else {
            result += "let";
        }

        if (email != null) {
            result += ", email: " + email;
        }

        if (faculty != null) {
            result += ", fakulta: " + faculty.toString();
        }

        if (mailingAddress != null) {
            result += ", adresa: " + mailingAddress;
        } else if (permanentAddress != null) {
            result += ", adresa: " + permanentAddress;
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (o instanceof Student) {
            Student s = (Student) o;

            return Objects.equals(insisId, s.getInsisId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return insisId.hashCode();
    }

}
