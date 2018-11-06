package jp.co.rakus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Comment;

@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	
	public void insertComment(Comment comment) {
		String sql = "INSERT INTO comments (name,content,article_id) VALUES (:name,:content,:article_id);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", comment.getName()).addValue("content", comment.getContent()).addValue("article_id", comment.getArticleId());
		template.update(sql, param);
	}
	
	public void deleteComment(int id) {
		String sql = "DELETE FROM comments WHERE id=:id ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);

		
	}
	
}
