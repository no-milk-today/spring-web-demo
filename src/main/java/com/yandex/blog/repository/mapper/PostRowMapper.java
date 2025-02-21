package com.yandex.blog.repository.mapper;

import com.yandex.blog.model.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        var post = new Post();
        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setImageUrl(rs.getString("image_url"));
        post.setContent(rs.getString("content"));
        post.setTag(rs.getString("tag"));
        post.setLikeCount(rs.getLong("like_count"));
        post.setCreated(rs.getTimestamp("created").toLocalDateTime());
        post.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
        return post;
    }
}
