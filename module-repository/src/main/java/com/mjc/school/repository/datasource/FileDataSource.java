package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.util.FileUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FileDataSource {
    private final int newsAmount;
    private final int authorsAmount;
    @Getter
    private final List<News> newsStorage = new ArrayList<>();
    @Getter
    private final List<Author> authorStorage = new ArrayList<>();

    private FileDataSource(
            @Value("${news.source}") String titleFilePath,
            @Value("${author.source}") String authorFilePath,
            @Value("${content.source}") String contentFilePath,
            @Value("${news.amount}") int newsAmount,
            @Value("${author.amount}") int authorsAmount
    ) {
        this.newsAmount = newsAmount;
        this.authorsAmount = authorsAmount;

        FileUtils fileUtils = new FileUtils();
        List<String> authors = fileUtils.readFileContent(authorFilePath);
        List<String> titles = fileUtils.readFileContent(titleFilePath);
        List<String> contents = fileUtils.readFileContent(contentFilePath);

        fillNews(titles, contents);
        fillAuthors(authors);
    }

    private void fillAuthors(List<String> authors) {
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < authorsAmount; i++) {
            LocalDateTime created = now.minusDays(30 + random.nextInt(30));
            LocalDateTime updated = now.minusDays(random.nextInt(30));
            authorStorage.add(new Author(((long) i), authors.get(i), created, updated));
        }
    }

    private void fillNews(List<String> titles, List<String> contents) {
        for (int i = 0; i < newsAmount; i++) {
            Random random = new Random();
            LocalDateTime now = LocalDateTime.now();

            String title = titles.get(i);
            String content = contents.get(i);
            LocalDateTime created = now.minusDays(30 + random.nextInt(30));
            LocalDateTime updated = now.minusDays(random.nextInt(30));

            News news = new News(((long) i), title, content, created, updated, ((long) i));
            newsStorage.add(news);
        }
    }

}
