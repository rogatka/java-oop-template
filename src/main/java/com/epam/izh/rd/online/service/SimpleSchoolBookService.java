package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> bookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (book != null) {
            String name = book.getAuthorName();
            String lastName = book.getAuthorLastName();
            if (authorService.findByFullName(name, lastName) != null) {
                System.out.println("Book was successfully saved!");
                return bookRepository.save(book);
            } else {
                System.out.println("Book wasn't saved because the author wasn't found!");
                return false;
            }
        } else {
            System.out.println("Book can't be null! Please check your request!");
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if (name != null) {
            if (bookRepository.findByName(name).length > 0) {
                System.out.println("Book was found!");
                return bookRepository.findByName(name);
            } else {
                System.out.println("Book wasn't found!");
                return new SchoolBook[0];
            }
        } else {
            System.out.println("Book can't be null! Please check your request!");
            return new SchoolBook[0];
        }
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        if (name != null) {
            if (bookRepository.removeByName(name)) {
                System.out.println("Book was successfully removed!");
                return true;
            } else {
                System.out.println("Book wasn't found!");
                return false;
            }
        } else {
            System.out.println("Name can't be null! Please check your request!");
            return false;
        }
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        if (name != null) {
            SchoolBook[] books = bookRepository.findByName(name);
            if (books.length > 0) {
                for (SchoolBook schoolBook : books) {
                    return authorService.findByFullName(schoolBook.getAuthorName(), schoolBook.getAuthorLastName());
                }
            } else {
                System.out.println("Book wasn't found!");
                return null;
            }
        } else {
            System.out.println("Name can't be null! Please check your request!");
            return null;
        }
        return null;
    }
}
