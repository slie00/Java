
import java.util.*;


/**
 * Třída představuje extrémně jednoduchou evidenci studentů VŠ. Pomocí metod
 * je možné studenty do evidence přidat, vyhledávat je dle různých kritérií
 * a také je z evidence vyřazovat.
 * 
 * @author: Elizaveta Sliusareva
 * @version: 2020-11-21
 * 
 */
public class Register {

    Set<Student> students;

    public Register() {
        students = new HashSet<>();
    }

    /**
     * Metoda přidá nového studenta do evidence. Studenta lze přidat pouze
     * pokud má vyplněnou fakultu (osoby, které nestudují na fakultách,
     * evidovat nechceme) a pokud v evidenci ještě není (nelze jednoho
     * a téhož studenta mít v evidenci vícekrát).
     *
     * Metoda by měla vrátit informaci o tom, zda se studenta podařilo do
     * evidence přidat, nebo ne.
     *
     * Při vyhodnocování, zda student v evidenci již je, by se metoda měla
     * řídit atributem insisId, který představuje jedinečný identifikátor
     * studenta (tzn. v evidenci nesmí být více objektů třídy Student se
     * stejnou hodnotou insisId).
     */
    public boolean addStudent(Student newStudent) {
        if (newStudent == null || newStudent.getFaculty() == null) {
            return false;
        }

        return students.add(newStudent);
    }

    /**
     * Metoda vrátí seznam všech studentů, kteří jsou aktuálně v evidenci.
     */
    public Collection<Student> getAllStudents() {
        return new HashSet<>(students);
    }

    /**
     * Metoda vrátí seznam všech studentů, kteří jsou aktuálně v evidenci
     * a studují na dané fakultě.
     */
    public Collection<Student> getStudentsByFaculty(Faculty faculty) {
        if (faculty == null) {
            return Collections.emptySet();
        }

        Set<Student> result = new HashSet<>();

        for (Student s : students) {
            if (faculty == s.getFaculty()) {  // Fakulta je objekt výčtového typu (enumerace),
                result.add(s);                // v tomto případě je možné k porovnávání používat
            }                                 // pouze operátor ==, pro všechny ostatní objektové
        }                                     // typy je nutné použít metodu equals.

        return result;
    }
    
    public Set<String> getEmailsByFaculty(Faculty faculty) { 
        if (faculty == null) {
            return Collections.emptySet();
        }

        Set<String> result = new HashSet<>();

        for (Student s : students) {
            if (s.getFaculty() != null && s.getFaculty().equals(faculty)) {
                result.add(s.getEmail());
            }
        }

        return result;
        
    }

    
    public Set<Student> getStudentsByCity(String city) {
        if (city == null) {
            return Collections.emptySet();
        }

        Set<Student> result = new HashSet<>();

        for (Student s : students) {
            if (s.getMailingAddress() != null){
                if (s.getMailingAddress().getCity().equals(city)) {
                    result.add(s);
                }
            } else if (s.getPermanentAddress() != null){
                if (s.getPermanentAddress().getCity().equals(city)) {
                    result.add(s);
                }
            }
        }

        return result;
    }
    
    public Student getOldestStudent() {
        int age_max = Integer.MIN_VALUE;
        Student res = null;
        for (Student s : students) {
            if (s.getAge() >= age_max) {
                age_max = s.getAge();
                res = s;
            }
        }
        return res;
    }
    
    public Student getSecondOldestStudent() {
        Student oldest = getOldestStudent();
        Student res = null;
        int age_max = Integer.MIN_VALUE;
        for (Student s : students) {
            if (s.getAge() >= age_max && s!=oldest) {
                age_max = s.getAge();
                res = s;
            }
        }
        return res;
    }
    
    public boolean[] addStudents(List<Student> newStudents) {
        if (newStudents == null)
            return new boolean[0];
        boolean[] res = new boolean[newStudents.size()];
        for (int i = 0; i < newStudents.size();i++) {
            res[i] = addStudent(newStudents.get(i));
        }
        return res;
    }

    /**
     * Metoda se pokusí v evidenci najít studenta se zadaným InSIS ID. Pokud
     * student v evidenci je, metoda ho vrátí. Pokud ne, metoda vrátí null.
     */
    public Student getStudentByInsisId(String insisId) {
        if (insisId == null) {
            return null;
        }

        for (Student s : students) {
            if (insisId.equals(s.getInsisId())) {
                return s;  // Jakmile najdeme studenta, okamžitě ho vrátíme jako výsledek metody.
            }
        }

        return null;
    }

    /**
     * Metoda se pokusí z evidence odebrat studenta se zadaným InSIS ID. Pokud
     * student v evidenci je, metoda ho z evidence odebere a vrátí ho. Pokud ne,
     * k žádné změně v evidenci nedojde a metoda vrátí null.
     */
    public Student removeStudentByInsisId(String insisId) {
        if (insisId == null) {
            return null;
        }

        Student result = null;

        for (Student s : students) {
            if (insisId.equals(s.getInsisId())) {
                students.remove(s);
                result = s;

                break;  // Jakmile najdeme studenta, uložíme ho do proměnné result, odstraníme
            }           // ho z množiny a ukončíme cyklus (po úpravě množiny by při dalším
        }               // průchodu cyklem došlo k výjimce ConcurrentModificationException).

        return result;
    }

}
