package com.mjc.school.repository.dao;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.FileDataSource;
import com.mjc.school.repository.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class NewsInMemoryDAOImpl implements BaseRepository<News, Long> {

    private final List<News> newsList;

    public NewsInMemoryDAOImpl(@Autowired FileDataSource dataSource) {
        newsList = dataSource.getNewsStorage();
    }

    @Override
    public News create(News news) {
        newsList.add(news);
        return news;
    }

    @Override
    public List<News> readAll() {
        return Collections.unmodifiableList(newsList);
    }

    @Override
    public Optional<News> readById(Long id) {
        return newsList.stream()
                .filter(news -> news.getId().equals(id))
                .findAny();
    }

    @Override
    public News update(News news) {
        News storedNews = readById(news.getId())
                .orElseThrow();
        storedNews.setTitle(news.getTitle());
        storedNews.setContent(news.getContent());
        storedNews.setAuthorId(news.getAuthorId());
        storedNews.setLastUpdateDate(news.getLastUpdateDate());
        return storedNews;
    }

    @Override
    public boolean deleteById(Long id) {
        return newsList.removeIf(news -> news.getId().equals(id));
    }

    @Override
    public boolean existById(Long id) {
        return newsList.stream().anyMatch(news -> news.getId().equals(id));
    }
}
