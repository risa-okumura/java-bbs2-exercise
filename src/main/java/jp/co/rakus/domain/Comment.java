package jp.co.rakus.domain;

/**
 * コメント情報を表すドメイン.
 * 
 * @author risa.okumura
 *
 */
public class Comment {
	
	/**　コメントID	 */
	private Integer id;
	/**　コメント投稿者名	 */
	private String name;
	/**　コメント内容	 */
	private String content;
	/**　コメントが持つ記事のID	 */
	private Integer articleId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

}
