package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorService implements AuthorService {
    private AuthorRepository authorRepository;

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        if (author != null) {
            System.out.println("Author is saving...");
            if (authorRepository.save(author)) {
                System.out.println("Saving was successful!");
                return true;
            } else {
                System.out.println("Saving was unsuccessful. This author already exists!");
                return false;
            }
        }
        System.out.println("Author can't be null! Please check your request!");
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (name != null && lastname != null) {
            System.out.println("Searching author...");
            if (authorRepository.findByFullName(name, lastname) != null) {
                System.out.println("Author was found!");
                return authorRepository.findByFullName(name, lastname);
            } else {
                System.out.println("Author wasn't found!");
                return null;
            }
        } else {
            System.out.println("Name or lastname can't be null! Please check your request!");
            return null;
        }
    }

    @Override
    public boolean remove(Author author) {
        if (author != null) {
            if (authorRepository.remove(author)) {
                System.out.println("Author was successfully removed!");
                return true;
            } else {
                System.out.println("Author wasn't found!");
                return false;
            }
        } else {
            System.out.println("Author can't be null! Please check your request!");
            return false;
        }
    }

    @Override
    public int count() {
        return authorRepository.count();
    }
}
