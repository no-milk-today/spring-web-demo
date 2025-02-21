package com.yandex.blog.repository;

import com.yandex.blog.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PostRowMapper postRowMapper;

    public JdbcPostRepository(JdbcTemplate jdbcTemplate, PostRowMapper postRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.postRowMapper = postRowMapper;
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT * FROM post";
        return jdbcTemplate.query(sql, postRowMapper);
    }

    @Override
    public Post findById(Long id) {
        String sql = "SELECT * FROM post WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, postRowMapper, id);
    }

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO post (title, image_url, content, tag, like_count, created, updated) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getImageUrl(), post.getContent(), post.getTag(), post.getLikeCount(), post.getCreated(), post.getUpdated());
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE post SET title = ?, image_url = ?, content = ?, tag = ?, like_count = ?, created = ?, updated = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getImageUrl(), post.getContent(), post.getTag(), post.getLikeCount(), post.getCreated(), post.getUpdated(), post.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM post WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
