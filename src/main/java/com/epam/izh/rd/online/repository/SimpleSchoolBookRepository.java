package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.time.LocalDate;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        int length = schoolBooks.length;
        schoolBooks = Arrays.copyOf(schoolBooks, length + 1);
        schoolBooks[length] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundedBooks = new SchoolBook[]{};
        int length = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equalsIgnoreCase(name)) {
                foundedBooks = Arrays.copyOf(foundedBooks, length + 1);
                foundedBooks[length] = book;
                length++;
            }
        }
        return foundedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        boolean hasRemovedBooks = false;
        SchoolBook[] copyOfSchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length);
        schoolBooks = new SchoolBook[0];
        int length = 0;
        for (int i = 0; i < copyOfSchoolBooks.length; i++) {
            if (copyOfSchoolBooks[i] != null && !copyOfSchoolBooks[i].getName().equalsIgnoreCase(name)) {
                schoolBooks = Arrays.copyOf(schoolBooks, length + 1);
                schoolBooks[length] = copyOfSchoolBooks[i];
                length++;
            } else {
                hasRemovedBooks = true;
            }
        }
        return hasRemovedBooks;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
