package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;

@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		Article article = null;
		Comment comment = null;
		
		Integer lastArticleId = 0;
		
		while(rs.next()) {
			
			if(lastArticleId != rs.getInt("id")) {
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				
				articleList.add(article);
				lastArticleId = article.getId();
				
			}
			
			if(rs.getInt("com_id") > 0) {
				comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("article_id"));
				commentList.add(comment);
			}
			
		}
		
		return articleList;
	};
	
	
	public List<Article> findAll(){
		String sql = "SELECT a.id, a.name, a.content, c.id AS com_id, c.name AS com_name, c.content AS com_content, c.article_id FROM articles a LEFT OUTER JOIN comments c ON a.id = c.article_id ORDER BY a.id DESC, c.id ASC; ";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Article> articleList = template.query(sql, param, ARTICLE_RESULT_SET_EXTRACTOR);
		
		return articleList;
	}
	
	public void insertArticle(Article article) {
		String sql = "INSERT INTO articles(name,content) VALUES (:name,:content);";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
	
	public void deleteAllBYArticleId(int id) {
		String sql = "WITH deleted AS (DELETE FROM articles WHERE id = :id RETURNING id) DELETE FROM comments WHERE article_id IN (SELECT id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
