package Laptenkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования объектов PersonIteratorOverCourse.
 */
class PersonIteratorOverCourseTest {

    SchoolJournal schoolJournalNotEmpty;
    SchoolJournal schoolJournalEmpty;
    Iterator<Person> iteratorEmpty;
    Iterator<Person> iteratorNotEmpty;

    /**
     * Инициализация объектов для тестированияния {@link SchoolJournal}.
     */
    @BeforeEach
    void setUp() {

        schoolJournalEmpty = new SchoolJournal();

        schoolJournalNotEmpty = new SchoolJournal();
        schoolJournalNotEmpty.addPerson(new Person("first", "l_first", 2));
        schoolJournalNotEmpty.addPerson(new Person("second", "l_second", 1));
        schoolJournalNotEmpty.addPerson(new Person("third", "l_third", 2));
        schoolJournalNotEmpty.addPerson(new Person("fourth", "l_fourth", 3));
        schoolJournalNotEmpty.addPerson(new Person("last", "l_last", 2));
        iteratorNotEmpty = schoolJournalNotEmpty.courseIterator(2);
        iteratorEmpty = schoolJournalNotEmpty.courseIterator(4);

    }

    /**
     * Очистка объектов после тестированияния {@link SchoolJournal}.
     */
    @AfterEach
    void tearDown() {
        schoolJournalEmpty = null;
        schoolJournalNotEmpty = null;
        iteratorNotEmpty = null;
        iteratorEmpty = null;
    }

    /**
     * Метод {@link PersonIteratorOverCourseTest#hasNextSuccess_Test}
     * проверяет метод hasNext.
     * Сценарий возвращает <code>false</code> при отсутствии следующего обекта
     * в итераторе.
     */
    @Test
    void hasNextFail_Test() {
        Assert.assertEquals(false, iteratorEmpty.hasNext());
    }

    /**
     * Метод {@link PersonIteratorOverCourseTest#hasNextSuccess_Test}
     * проверяет метод hasNext.
     * Сценарий возвращает <code>true</code> при наличии следующего обекта
     * в итераторе.
     */
    @Test
    void hasNextSuccess_Test() {
        Assert.assertEquals(true, iteratorNotEmpty.hasNext());
    }

    /**
     * Метод {@link PersonIteratorOverCourseTest#nextSuccess_Test}
     * проверяет метод next возвращает объект-студент, у которого курс равен courseNumber.
     * Иначе, NoSuchElementException
     * Сценарий возвращающий объект при его наличии.
     */
    @Test
    void nextSuccess_Test() {
        Assert.assertEquals("first", iteratorNotEmpty.next().getFirstName());
        Assert.assertEquals("third", iteratorNotEmpty.next().getFirstName());
    }

    /**
     * Метод {@link PersonIteratorOverCourseTest#nextFail_Test}
     * проверяет метод next возвращает объект-студент, у которого курс равен courseNumber.
     * Иначе, NoSuchElementException
     * Сценарий возвращающий NoSuchElementException при его отсутствии.
     */
    @Test
    void nextFail_Test() {
        iteratorNotEmpty.next();
        iteratorNotEmpty.next();
        iteratorNotEmpty.next();
        Throwable throwable = Assertions.assertThrows(NoSuchElementException.class, () -> iteratorNotEmpty.next());
        Assertions.assertEquals("Студентов в списке нет!", throwable.getMessage());
    }

    /**
     * Метод {@link PersonIteratorOverCourseTest#removeSuccess_Test}
     * проверяет метод remove.
     * Сценарий удаляет студента который есть в перечне.
     */
    @Test
    void removeSuccess_Test() {
        Assert.assertEquals("first", iteratorNotEmpty.next().getFirstName());
        Assert.assertEquals("third", iteratorNotEmpty.next().getFirstName());
        Assert.assertEquals("last", iteratorNotEmpty.next().getFirstName());
    }

    /**
     * Метод {@link PersonIteratorOverCourseTest#removeSuccess_Test}
     * проверяет метод remove.
     * Сценарий удаляет студента которого нет в перечне и выбрасывает IllegalStateException.
     */
    @Test
    void removeFail_Test() {
        iteratorNotEmpty.next();
        iteratorNotEmpty.remove();
        iteratorNotEmpty.next();
        iteratorNotEmpty.remove();
        iteratorNotEmpty.next();
        iteratorNotEmpty.remove();
        Throwable throwable = Assertions.assertThrows(IllegalStateException.class, () -> iteratorNotEmpty.remove());
        Assertions.assertEquals("Студентов в списке нет!", throwable.getMessage());
    }

    ////////////////////////////////////////////////

    @Test
    public void simpleTest() {

        SchoolJournal journal = new SchoolJournal();

        journal.addPerson(new Person("1", "", 1));
        journal.addPerson(new Person("2", "", 1));
        journal.addPerson(new Person("3", "", 1));
        journal.addPerson(new Person("4", "", 1));

        Iterator<Person> it = journal.courseIterator(1);

        Person person = it.next();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals("1", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        person = it.next();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals("2", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        person = it.next();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals("3", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        person = it.next();

        Assert.assertEquals("4", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        Assert.assertFalse(it.hasNext());
        try {
            it.next();
            Assert.fail();
        } catch (NoSuchElementException ex) {
        }
    }

    @Test
    public void severalTest() {

        SchoolJournal journal = new SchoolJournal();

        journal.addPerson(new Person("1", "", 1));
        journal.addPerson(new Person("2", "", 2));
        journal.addPerson(new Person("3", "", 2));
        journal.addPerson(new Person("4", "", 1));

        Iterator<Person> it = journal.courseIterator(1);

        Person person = it.next();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals("1", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        person = it.next();

        Assert.assertEquals("4", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        Assert.assertFalse(it.hasNext());
        try {
            it.next();
            Assert.fail();
        } catch (NoSuchElementException ex) {
        }
    }

    @Test
    public void noElementsTest() {

        SchoolJournal journal = new SchoolJournal();

        journal.addPerson(new Person("1", "", 2));
        journal.addPerson(new Person("2", "", 2));
        journal.addPerson(new Person("3", "", 2));
        journal.addPerson(new Person("4", "", 2));

        Iterator<Person> it = journal.courseIterator(1);

        Assert.assertFalse(it.hasNext());
        try {
            it.next();
            Assert.fail();
        } catch (NoSuchElementException ex) {
        }
    }

    @Test
    public void removeTest() {

        SchoolJournal journal = new SchoolJournal();

        Iterator<Person> it = journal.courseIterator(1);

        Assert.assertFalse(it.hasNext());
        try {
            it.remove();
            Assert.fail();
        } catch (IllegalStateException ex) {
        }

        journal.addPerson(new Person("1", "", 1));
        journal.addPerson(new Person("2", "", 2));
        journal.addPerson(new Person("3", "", 2));
        journal.addPerson(new Person("4", "", 1));

        it = journal.courseIterator(1);

        Person person = it.next();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals("1", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        it.remove();

        person = it.next();

        Assert.assertEquals("4", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        Assert.assertFalse(it.hasNext());

        it = journal.courseIterator(1);
        Assert.assertTrue(it.hasNext());

        person = it.next();

        Assert.assertEquals("4", person.getFirstName());
        Assert.assertEquals(1, person.getCourse());

        Assert.assertFalse(it.hasNext());

        it.remove();
        Assert.assertFalse(journal.courseIterator(1).hasNext());
        try {
            it.remove();
            Assert.fail();
        } catch (IllegalStateException ex) {
        }
    }
}