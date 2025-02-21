package com.yandex.blog.repository;

import com.yandex.blog.model.Post;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        // Given
        var postRowMapper = new PostRowMapper();
        var resultSet = mock(ResultSet.class);
        var now = LocalDateTime.now();

        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("title")).thenReturn("Тестовый заголовок");
        when(resultSet.getString("image_url")).thenReturn("https://example.com/image.jpg");
        when(resultSet.getString("content")).thenReturn("Тестовый контент");
        when(resultSet.getString("tag")).thenReturn("Тестовый тег");
        when(resultSet.getLong("like_count")).thenReturn(123L);
        when(resultSet.getTimestamp("created")).thenReturn(Timestamp.valueOf(now));
        when(resultSet.getTimestamp("updated")).thenReturn(Timestamp.valueOf(now));

        // When
        var actual = postRowMapper.mapRow(resultSet, 1);

        // Then
        var expected = new Post();
        expected.setId(1L);
        expected.setTitle("Тестовый заголовок");
        expected.setImageUrl("https://example.com/image.jpg");
        expected.setContent("Тестовый контент");
        expected.setTag("Тестовый тег");
        expected.setLikeCount(123L);
        expected.setCreated(now);
        expected.setUpdated(now);

        assertEquals(expected, actual);
    }
}
