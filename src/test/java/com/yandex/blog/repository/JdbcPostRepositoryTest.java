package com.yandex.blog.repository;

import com.yandex.blog.configuration.DataSourceConfiguration;
import com.yandex.blog.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringJUnitConfig(classes = {DataSourceConfiguration.class, JdbcPostRepository.class})
@TestPropertySource(locations = "classpath:test-application.properties")
class JdbcPostRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM comment");
        jdbcTemplate.execute("DELETE FROM post_preview");
        jdbcTemplate.execute("DELETE FROM post");

        // Добавление тестовых данных
        jdbcTemplate.execute("INSERT INTO post (id, title, image_url, content, tag, like_count, created, updated) VALUES (1, 'Новая Tesla Model S', 'https://example.com/tesla_model_s.jpg', 'Обзор новой Tesla Model S с улучшенной батареей.', 'Электромобили', 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        jdbcTemplate.execute("INSERT INTO post (id, title, image_url, content, tag, like_count, created, updated) VALUES (2, 'Обзор BMW M3 2025', 'https://example.com/bmw_m3_2025.jpg', 'Детальный обзор BMW M3 2025 года выпуска.', 'Спортивные автомобили', 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        jdbcTemplate.execute("INSERT INTO post (id, title, image_url, content, tag, like_count, created, updated) VALUES (3, 'Сравнение Audi A4 и Mercedes C-Class', 'https://example.com/audi_a4_vs_mercedes_c_class.jpg', 'Сравнительный анализ Audi A4 и Mercedes C-Class.', 'Седаны', 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");

        jdbcTemplate.execute("INSERT INTO comment (id, post_id, content, created, updated) VALUES (1, 1, 'Отличный обзор! Жду не дождусь, когда смогу протестировать новую Tesla.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        jdbcTemplate.execute("INSERT INTO comment (id, post_id, content, created, updated) VALUES (2, 2, 'BMW снова на высоте с этой моделью.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        jdbcTemplate.execute("INSERT INTO comment (id, post_id, content, created, updated) VALUES (3, 3, 'Полезная информация, спасибо!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");

        jdbcTemplate.execute("INSERT INTO post_preview (post_id, description, created, updated) VALUES (1, 'Краткий обзор новой Tesla Model S.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        jdbcTemplate.execute("INSERT INTO post_preview (post_id, description, created, updated) VALUES (2, 'Основные характеристики BMW M3 2025.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        jdbcTemplate.execute("INSERT INTO post_preview (post_id, description, created, updated) VALUES (3, 'Сравнение Audi A4 и Mercedes C-Class: что выбрать?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
    }

    @Test
    void save_shouldAddPostToDatabase() {
        var now = LocalDateTime.now();
        Post post = new Post(4L, "Новый обзор Audi Q7", "https://example.com/audi_q7.jpg", "Детальный обзор Audi Q7 2025 года.", "Кроссоверы", 120L, now, now);

        postRepository.save(post);

        Post savedPost = postRepository.findAll().stream()
                .filter(p -> p.getId().equals(4L))
                .findFirst()
                .orElse(null);

        assertNotNull(savedPost);
        assertEquals("Новый обзор Audi Q7", savedPost.getTitle());
        assertEquals("https://example.com/audi_q7.jpg", savedPost.getImageUrl());
    }

    @Test
    void findAll_shouldReturnAllPosts() {
        List<Post> posts = postRepository.findAll();

        assertNotNull(posts);
        assertEquals(3, posts.size());

        Post post = posts.get(0);
        assertEquals(1L, post.getId());
        assertEquals("Новая Tesla Model S", post.getTitle());
    }

    @Test
    void deleteById_shouldRemovePostFromDatabase() {
        jdbcTemplate.execute("DELETE FROM comment WHERE post_id = 1");
        jdbcTemplate.execute("DELETE FROM post_preview WHERE post_id = 1");
        postRepository.deleteById(1L);

        List<Post> posts = postRepository.findAll();

        Post deletedPost = posts.stream()
                .filter(p -> p.getId().equals(1L))
                .findFirst()
                .orElse(null);
        assertNull(deletedPost);
    }
}
