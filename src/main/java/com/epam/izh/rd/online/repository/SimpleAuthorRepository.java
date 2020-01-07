package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.time.LocalDate;
import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        String authorLastName = author.getLastName();
        String authorName = author.getName();
        if (findByFullName(authorName, authorLastName) == null) {
            int length = authors.length;
            authors = Arrays.copyOf(authors, length + 1);
            authors[length] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(name) && author.getLastName().equalsIgnoreCase(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        String authorName = author.getName();
        String authorLastName = author.getLastName();
        if (findByFullName(authorName, authorLastName) != null) {
            Author removedAuthor = findByFullName(authorName, authorLastName);
            for (int i = 0; i < authors.length; i++) {
                if (authors[i].equals(removedAuthor)) {
                    Author[] copyOfAuthors = Arrays.copyOf(authors, authors.length);
                    authors = new Author[authors.length - 1];
                    System.arraycopy(copyOfAuthors, 0, authors, 0, i);
                    System.arraycopy(copyOfAuthors, i + 1, authors, i, authors.length - i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
