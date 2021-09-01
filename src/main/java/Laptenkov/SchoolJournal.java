package Laptenkov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * School journal.
 */
public class SchoolJournal {

    /**
     * Класс SchoolJournal хранит список объектов Person.
     * Класс SchoolJournal содержит метод courseIterator, который возвращает класс PersonIteratorOverCourse.
     * Реализовать класс PersonIteratorOverCourse, который реализует интерфейс Iterator<Person>
     */
    private class PersonIteratorOverCourse implements Iterator<Person> {

        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int courseNumber; // номер курса для поиска

        /**
         * Конструктор пустого объекта {@link PersonIteratorOverCourse}
         * принимает номер курса courseNumber в качестве параметра.
         */
        public PersonIteratorOverCourse(int courseNumber) {
            this.courseNumber = courseNumber;
            this.cursor = 0;
        }

        /**
         * Метод hasNext возвращает true, если в списке
         * объекта {@link PersonIteratorOverCourse} есть студент на курсе,
         * равном courseNumber.
         * Иначе, false
         *
         * @return {@code true} если следующий элемент
         * объект студент с курсом courseNumber есть в списке.
         */
        @Override
        public boolean hasNext() {

            if (cursor != persons.size()) {
                if (searchPerson(cursor) != -1) {
                    return true;
                }
            }

            return false;
        }

        /**
         * Вспомогательнывй метод для поиска индекса слеюующего
         * объекта-студента, у которого курс равен courseNumber.
         * Иначе, -1.
         *
         * @return int целое число у которого курс равен courseNumber
         * или -1 если такого объекта нет.
         */
        private int searchPerson(int cursor) {
            for (int index = cursor; index < persons.size(); index++) {
                if (persons.get(index).getCourse() == courseNumber) {
                    return index;
                }
            }
            return -1;
        }

        /**
         * Метод next возвращает объект-студент, у которого курс равен courseNumber.
         * Иначе, NoSuchElementException
         *
         * @return следующий объект для итерации.
         * @throws NoSuchElementException если следующего элемента для итерации нет.
         */
        @Override
        public Person next() {

            if (cursor >= persons.size()) {
                throw new NoSuchElementException("Студентов в списке нет!");
            }

            int index = searchPerson(cursor);
            if (index != -1) {
                Person elementData = persons.get(index);
                lastRet = index;
                cursor = index + 1;
                return elementData;
            }
            throw new NoSuchElementException();

        }

        /**
         * Метод remove удалит студента, которого вернул предыдущий вызов next.
         * у объекта, у которого курс равен courseNumber.
         * Иначе IllegalStateException.
         *
         * @throws IllegalStateException
         */
        @Override
        public void remove() {

            if (lastRet < 0)
                throw new IllegalStateException("Студентов в списке нет!");

            if (lastRet < persons.size() ) {
                persons.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                return;
            }
            throw new IllegalStateException("Студентов в списке нет!");
        }

    }

    private List<Person> persons;

    public SchoolJournal() {
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public Iterator<Person> courseIterator(int courseNumber) {
        return new PersonIteratorOverCourse(courseNumber);
    }
}