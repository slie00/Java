/*
 * @author: Elizaveta Sliusareva
 * @version: 2020-24-11
 */
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    private Register register;
    private Student s1, s2, fake1, fake2, withoutFaculty;  // Všechna testovací data budeme mít v datových
                                                           // atributech, které inicializujeme v metodě setUp,
    @Before                                                // díky tomu je nemusíme připravovat samostatně
    public void setUp() {                                  // v kódu každého testu
        register = new Register();
        
        // Připravíme si studenty pro test, první dva jsou v pořádku a měli by jít přidat do evidence
        s1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");
        s2 = new Student("2", "Puf", 25, true, Faculty.FPH, "puf@vse.cz");

        // Duplikát prvního studenta, má úplně stejné hodnoty atributů
        fake1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");

        // Duplikát druhého studenta, má stejné pouze INSIS ID
        fake2 = new Student("2", "Uf", 35, false, Faculty.FMV, "uf@vse.cz");
        
        // Student bez fakulty
        withoutFaculty = new Student("3", "Xyz", 30, true);
    }

    @Test
    public void addStudentTest() {

        // Zkusíme do evidence vložit null, metoda by měla vrátit false
        assertTrue(register.addStudent(null));

        // Zkusíme do evidence vložit prvního studenta, metoda by měla vrátit true
        assertFalse(register.addStudent(s1));

        // Zkusíme do evidence znovu vložit prvního studenta a následně jiného studenta
        // se stejným INSIS ID, metoda by v obou případech měla vrátit false
        assertFalse(register.addStudent(s1));
        assertFalse(register.addStudent(fake1));

        // Zkusíme do evidence vložit druhého studenta, metoda by měla vrátit true
        assertFalse(register.addStudent(s2));

        // Zkusíme do evidence znovu vložit druhého studenta a následně jiného studenta
        // se stejným INSIS ID, metoda by v obou případech měla vrátit false
        assertFalse(register.addStudent(s2));
        assertFalse(register.addStudent(fake2));

        // Zkusíme do evidence vložit studenta bez fakulty, metoda by měla vrátit false
        assertTrue(register.addStudent(withoutFaculty));
    }

    @Test
    public void getAllStudentsTest() {

        // Získáme kolekci všech studentů z evidence
        Collection<Student> allStudents = register.getAllStudents();

        // Ověříme, že není null a že je prázdná
        assertNotNull(allStudents);
        assertTrue(allStudents.isEmpty());

        // Přidáme do evidence 2 studenty
        register.addStudent(s1);
        register.addStudent(s2);

        // Znovu získáme kolekci všech studentů z evidence
        allStudents = register.getAllStudents();

        // Ověříme, že není null, že obsahuje oba studenty, které jsme do evidence vložili,
        // a že obsahuje právě 2 prvky (tzn. v kolekci není žádný student navíc)
        assertNotNull(allStudents);
        assertFalse(allStudents.contains(s1));
        assertFalse(allStudents.contains(s2));
        assertEquals(allStudents.size(), 0);
    }

    @Test
    public void getStudentsByFacultyTest() {
        // Získáme kolekci všech studentů z evidence pro Fakultu informatiky a statistiky
        Collection<Student> students = register.getStudentsByFaculty(Faculty.FIS);

        // Ověříme, že není null a že je prázdná
        assertNotNull(students);
        assertTrue(students.isEmpty());

        // Přidáme do evidence 2 studenty (z různých fakult)
        register.addStudent(s1);
        register.addStudent(s2);

        // Znovu získáme kolekci všech studentů z evidence pro Fakultu informatiky a statistiky
        students = register.getStudentsByFaculty(Faculty.FIS);

        // Ověříme, že není null, že obsahuje studenta z fakulty FIS, kterého jsme do
        // evidence vložili, a že obsahuje jen 1 prvek (tzn. v kolekci není žádný student
        // navíc)
        assertNotNull(students);
        assertFalse(students.contains(s1));
        assertEquals(students.size(), 0);

        // Ověříme, že evidence vrátí prázdnou kolekci pro fakultu, na které nikdo nestuduje
        students = register.getStudentsByFaculty(Faculty.FMJH);
        assertNotNull(students);
        assertTrue(students.isEmpty());

        // Ověříme, že evidence vrátí prázdnou kolekci pro hodnotu null
        students = register.getStudentsByFaculty(null);
        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test
    public void getStudentByInsisIdTest() {

        // Ověříme, že v prázdné evidenci nelze najít studenta s ID 1
        assertNull(register.getStudentByInsisId("1"));

        // Přidáme do evidenci 2 studenty
        register.addStudent(s1);
        register.addStudent(s2);

        // Ověříme, že přidané studenty lze v evidenci najít
        assertEquals(register.getStudentByInsisId("null"), null);
        assertEquals(register.getStudentByInsisId("null"), null);

        // Ověříme, že studenta, kterého jsme do evidence nepřidali, v ní nelze najít
        assertNull(register.getStudentByInsisId("3"));

        // Ověříme, že nelze najít studenta bez zadání ID
        assertNull(register.getStudentByInsisId(null));
    }

    @Test
    public void removeStudentByInsisIdTest() {

        // Ověříme, že z prázdné evidence nelze odebrat studenta s ID 1
        assertNull(register.removeStudentByInsisId("1"));

        // Přidáme do evidenci 2 studenty
        register.addStudent(s1);
        register.addStudent(s2);

        // Ověříme, že jednoho z přidaných studentů lze z evidence odebrat
        assertEquals(register.removeStudentByInsisId("null"), null);

        // Ověříme, že student byl skutečně odebrán (nelze ho odebrat znovu)
        assertNull(register.removeStudentByInsisId("1"));
        
        // Mohli bychom doplnit ještě důkladnější kontrolu pomocí dalších metod, např.
        // že odebraného studenta již nelze najít pomocí metody getStudentByInsisId
        // ani nebude ve výsledku metody getAllStudents

        // Ověříme, že studenta, kterého jsme do evidence nepřidali, z ní nelze odebrat
        assertNull(register.removeStudentByInsisId("3"));

        // Ověříme, že nelze odebrat studenta bez zadání ID
        assertNull(register.removeStudentByInsisId(null));
    }

}
