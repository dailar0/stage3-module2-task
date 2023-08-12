package com.mjc.school.repository.dao;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.FileDataSource;
import com.mjc.school.repository.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorInMemoryDAOImpl implements BaseRepository<Author, Long> {
    private final List<Author> authors;

    public AuthorInMemoryDAOImpl(@Autowired FileDataSource fileDataSource) {
        authors = fileDataSource.getAuthorStorage();
    }

    @Override
    public Author create(Author author) {
        authors.add(author);
        return author;
    }

    @Override
    public List<Author> readAll() {
        return Collections.unmodifiableList(authors);
    }

    @Override
    public Optional<Author> readById(Long id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public Author update(Author author) {
        Author storedAuthor = readById(author.getId())
                .orElseThrow();
        storedAuthor.setName(author.getName());
        storedAuthor.setLastUpdateDate(author.getLastUpdateDate());
        return storedAuthor;
    }

    @Override
    public boolean deleteById(Long id) {
        return authors.removeIf(author -> author.getId().equals(id));
    }

    @Override
    public boolean existById(Long id) {
        return authors.stream()
                .anyMatch(author -> author.getId().equals(id));
    }

}
