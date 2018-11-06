package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;
import jp.co.rakus.form.ArticleForm;
import jp.co.rakus.form.CommentForm;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	
	@RequestMapping("/index")
	public String index(Model model) {
		
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);
		
		return "/bbs";
	}
	
	@RequestMapping("/insertArticle")
	public String insertArticle(@Validated ArticleForm articleForm, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			return index(model);
		}

		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insertArticle(article);
		
		return "redirect:/bbs/index";
	}
	
	@RequestMapping("/insertComment")
	public String insertComment(@Validated CommentForm commentForm, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			return index(model);
		}
		
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		comment.setArticleId(commentForm.getIntArticleId());
		commentRepository.insertComment(comment);
		
		return "redirect:/bbs/index";
	}
	
	@RequestMapping("/deleteAllByArticleId")
	public String deleteAllByArticleId(int id) {
		
		articleRepository.deleteAllBYArticleId(id);
		
		return "redirect:/bbs/index";
	}
	
	@RequestMapping("/deleteComment")
	public String deleteComment(int id) {
		
		commentRepository.deleteComment(id);
		
		return "redirect:/bbs/index";
	}
	
	

}
