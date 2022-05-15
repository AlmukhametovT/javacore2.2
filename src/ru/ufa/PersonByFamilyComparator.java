package ru.ufa;

import java.util.Comparator;

public class PersonByFamilyComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getFamily().compareTo(o2.getFamily());
    }
}