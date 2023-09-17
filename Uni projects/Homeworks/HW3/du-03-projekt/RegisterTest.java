
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**     
     * Třída RegisterTest představuje       
     *    
     * @author: Elizaveta Sliusareva    
     * @version: 2020-11-21     
  */ 

public class RegisterTest {

    private Register register;
    private Student s1, s2, fake1, fake2, withoutFaculty;  // Všechna testovací data budeme mít v datových
                                                           // atributech, které inicializujeme v metodě setUp,
    @Before                                                // díky tomu je nemusíme připravovat samostatně
    public void setUp() {                                  // v kódu každého testu
        register = new Register();
        
        // Připravíme si studenty pro test, první dva jsou v pořádku a měli by jít přidat do evidence
        s1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");
        s2 = new Student("2", "Puf", 25, true, Faculty.FIS, "puf@vse.cz");

        // Duplikát prvního studenta, má úplně stejné hodnoty atributů
        fake1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");

        // Duplikát druhého studenta, má stejné pouze INSIS ID
        fake2 = new Student("2", "Uf", 35, false, Faculty.FMV, "uf@vse.cz");
        
        // Student bez fakulty
        withoutFaculty = new Student("3", "Xyz", 30, true, "xyz@vse.cz");
    }

    @Test
    public void addStudentTest() {

        // Zkusíme do evidence vložit null, metoda by měla vrátit false
        assertFalse(register.addStudent(null));

        // Zkusíme do evidence vložit prvního studenta, metoda by měla vrátit true
        assertTrue(register.addStudent(s1));

        // Zkusíme do evidence znovu vložit prvního studenta a následně jiného studenta
        // se stejným INSIS ID, metoda by v obou případech měla vrátit false
        assertFalse(register.addStudent(s1));
        assertFalse(register.addStudent(fake1));

        // Zkusíme do evidence vložit druhého studenta, metoda by měla vrátit true
        assertTrue(register.addStudent(s2));

        // Zkusíme do evidence znovu vložit druhého studenta a následně jiného studenta
        // se stejným INSIS ID, metoda by v obou případech měla vrátit false
        assertFalse(register.addStudent(s2));
        assertFalse(register.addStudent(fake2));

        // Zkusíme do evidence vložit studenta bez fakulty, metoda by měla vrátit false
        assertFalse(register.addStudent(withoutFaculty));
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
        assertTrue(allStudents.contains(s1));
        assertTrue(allStudents.contains(s2));
        assertEquals(allStudents.size(), 2);
    }
    
    @Test
    public void testGetEmailsByFaculty(){
        Register register1 = new Register();
        Set<String> res = new HashSet<>();
        res.add("muf@vse.cz");
        res.add("puf@vse.cz");
        register1.addStudent(new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz"));
        register1.addStudent(new Student("2", "Puf", 25, true, Faculty.FIS, "puf@vse.cz"));
        assertEquals(register1.getEmailsByFaculty(Faculty.FIS),res);
        register1.addStudent(new Student("3", "Nif", 22, true,Faculty.FMV, "nif@vse.cz"));

        assertTrue(register1.getEmailsByFaculty(Faculty.FFU).isEmpty());
        assertTrue(register1.getEmailsByFaculty(null).isEmpty());
    }
    
    @Test
    public void testGetStudentsByCity(){
        Student s1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");
        s1.setPermanentAddress(new Address("Parižská", "Praha", "110 00"));
        Student s2 = new Student("2", "Puf", 25, true, Faculty.FIS, "puf@vse.cz");
        s2.setMailingAddress(new Address("Žižkovo Náměstí", "Praha", "130 00"));
        Student s3 = new Student("3", "Nif", 22, true,Faculty.FMV, "nif@vse.cz");
        s3.setPermanentAddress(new Address("Parižská", "Praha", "110 00"));
        s3.setMailingAddress(new Address("Vídenská", "Brno", "639 00"));
        Student s4 = new Student("4","David",30,false,Faculty.FPH, "davis@vse.cz");
        s4.setPermanentAddress(new Address("Budějovická", "Praha", "164 00"));
        

        Set<Student> res = new HashSet<>();
        res.add(s1);
        res.add(s2);
        res.add(s4);

        Register register1 = new Register();
        register1.addStudent(s1);
        register1.addStudent(s2);
        register1.addStudent(s3);
        register1.addStudent(s4);
       

        assertEquals(res,register1.getStudentsByCity("Praha"));

        s1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");
        s1.setPermanentAddress(new Address("Parižská", "Praha", "110 00"));
        s2 = new Student("2", "Puf", 25, true, Faculty.FIS, "puf@vse.cz");
        s2.setMailingAddress(new Address("Žižkovo Náměstí", "Praha", "130 00"));

        Register register2 = new Register();
        register2.addStudent(s1);
        register2.addStudent(s2);

        assertTrue(register2.getStudentsByCity("Brno").isEmpty());
        assertTrue(register2.getStudentsByCity(null).isEmpty());
    }
    
    @Test
    public void testGetOldestStudent() {
        Register register1 = new Register();
        assertNull(register1.getOldestStudent());
        Student s1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");
        Student s2 = new Student("2", "Puf", 25, true, Faculty.FIS, "puf@vse.cz");
        Student s3 = new Student("3", "Nif", 22, true,Faculty.FMV, "nif@vse.cz");
        register1.addStudent(s1);
        register1.addStudent(s2);
        register1.addStudent(s3);
        assertSame(s2,register1.getOldestStudent());

        Register register2 = new Register();
        s1 = new Student("1", "Muf", 24, true, Faculty.FIS, "muf@vse.cz");
        s2 = new Student("2", "Puf", 19, true, Faculty.FIS, "puf@vse.cz");
        s3 = new Student("3", "Nif", 24, true,Faculty.FMV, "nif@vse.cz");
        register2.addStudent(s1);
        register2.addStudent(s2);
        register2.addStudent(s3);
        assertTrue(register2.getOldestStudent() == s1 || register2.getOldestStudent() == s3);
    }

    @Test
    public void testGetSecondOldestStudent() {
        Register register1 = new Register();
        assertNull(register1.getSecondOldestStudent());
        Student s1 = new Student("1", "Muf", 20, true, Faculty.FIS, "muf@vse.cz");
        Student s2 = new Student("2", "Puf", 25, true, Faculty.FIS, "puf@vse.cz");
        Student s3 = new Student("3", "Nif", 22, true,Faculty.FMV, "nif@vse.cz");
        Student s4 = new Student("4","David",30,false,Faculty.FPH, "davis@vse.cz");
        register1.addStudent(s1);
        register1.addStudent(s2);
        register1.addStudent(s3);
        register1.addStudent(s4);
        assertSame(s2,register1.getSecondOldestStudent());

        Register register2 = new Register();
        s1 = new Student("1", "Muf", 24, true, Faculty.FIS, "muf@vse.cz");
        s2 = new Student("2", "Puf", 19, true, Faculty.FIS, "puf@vse.cz");
        s3 = new Student("3", "Nif", 24, true,Faculty.FMV, "nif@vse.cz");
        s4 = new Student("4","David",30,false,Faculty.FPH, "davis@vse.cz");
        register2.addStudent(s1);
        register2.addStudent(s2);
        register2.addStudent(s3);
        register2.addStudent(s4);
        assertTrue(register2.getSecondOldestStudent() == s1 || register2.getSecondOldestStudent() == s3);

        Register register3 = new Register();
        s1 = new Student("1", "Muf", 24, true, Faculty.FIS, "muf@vse.cz");
        s2 = new Student("2", "Puf", 19, true, Faculty.FIS, "puf@vse.cz");
        s3 = new Student("3", "Nif", 30, true,Faculty.FMV, "nif@vse.cz");
        s4 = new Student("4","David",30,false,Faculty.FPH, "davis@vse.cz");
        register3.addStudent(s1);
        register3.addStudent(s2);
        register3.addStudent(s3);
        register3.addStudent(s4);
        System.out.println(register3.getSecondOldestStudent());
        assertTrue(register3.getSecondOldestStudent() == s4 || register3.getSecondOldestStudent() == s3);
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
        assertTrue(students.contains(s1));
        assertEquals(students.size(), 2);

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
        assertEquals(register.getStudentByInsisId("1"), s1);
        assertEquals(register.getStudentByInsisId("2"), s2);

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
        assertEquals(register.removeStudentByInsisId("1"), s1);

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
