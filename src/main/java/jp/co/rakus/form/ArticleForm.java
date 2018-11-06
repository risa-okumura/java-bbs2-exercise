package jp.co.rakus.form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import jp.co.rakus.domain.Comment;

public class ArticleForm {
	
	/**	記事ID */
	private String id;
	/**	記事投稿者名 */
	@NotBlank(message="名前を入力してください")
	private String name;
	/**	記事内容 */
	@NotBlank(message="記事の内容を入力してください")
	private String content;
	/**	コメント情報 */
	private List<Comment> commentList;
	
	public Integer getIntId() {
		return Integer.parseInt(this.id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	

}
