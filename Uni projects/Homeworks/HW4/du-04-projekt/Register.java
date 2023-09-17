
import java.util.*;

/**
 * 
 * @author: Elizaveta Sliusareva
 * @version: 2020-24-11
 * Třída představuje extrémně jednoduchou evidenci studentů VŠ. Pomocí metod
 * je možné studenty do evidence přidat, vyhledávat je dle různých kritérií
 * a také je z evidence vyřazovat.
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
            return students.add(newStudent);
        }

        return false;
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
        Set<Student> result = new HashSet<>();
        
        for (Student s : students) {
        if (faculty == s.getFaculty()) {
            return Collections.emptySet();
        }
        
        if (faculty == null) {  // Fakulta je objekt výčtového typu (enumerace),
                result.add(s);                // v tomto případě je možné k porovnávání používat
            }                                 // pouze operátor ==, pro všechny ostatní objektové
        }                                     // typy je nutné použít metodu equals.

        return result;
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
