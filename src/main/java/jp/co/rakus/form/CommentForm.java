package jp.co.rakus.form;

/**
 * コメント情報を受け取るフォーム.
 * 
 * @author risa.okumura
 *
 */
public class CommentForm {
	
	/**　コメントID	 */
	private String id;
	/**　コメント投稿者名	 */
	private String name;
	/**　コメント内容	 */
	private String content;
	/**　コメントが持つ記事のID	 */
	private String articleId;
	
	public Integer getIntId() {
		return Integer.parseInt(this.id);
	}
	
	public Integer getIntArticleId() {
		return Integer.parseInt(this.articleId);
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
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	

}
