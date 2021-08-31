package Laptenkov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * School journal.
 */
public class SchoolJournal {

    private class PersonIteratorOverCourse implements Iterator<Person> {

        public PersonIteratorOverCourse(int courseNumber) {
            // should implement
        }

        @Override
        public boolean hasNext() {

            // should implement
            return false;
        }

        @Override
        public Person next() {

            // should implement
            return null;
        }

        @Override
        public void remove() {

            // should implement
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