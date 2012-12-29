package org.kb.web;

import java.util.Map;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kb.entity.Article;
import org.kb.entity.Category;
import org.kb.service.ArticleService;
import org.kb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
	Logger logger = Logger.getLogger(ArticleController.class);
	
	private static final int PAGE_SIZE = 3;
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "auto");
		sortTypes.put("title", "title");
	}
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {		
		Page<Article> articles = articleService.getCategoryArticle(1L, pageNumber, PAGE_SIZE, sortType);
		model.addAttribute("articles", articles);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		
		return "articleList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createFrom(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("action", "create");
		return "articleForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Article article, RedirectAttributes redirectAttributes) {
		Category category = categoryService.getCategory(1L);
		article.setCategory(category);
		
		articleService.saveArticle(article);
		redirectAttributes.addFlashAttribute("message", "创建文章成功");
		return "redirect:/article/";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("article", articleService.getArticle(id));
		model.addAttribute("action", "update");
		return "articleForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("article") Article article, RedirectAttributes redirectAttributes) {
		Category category = categoryService.getCategory(1L);
		article.setCategory(category);
		
		articleService.saveArticle(article);
		redirectAttributes.addFlashAttribute("message","更新文章成功");
		return "redirect:/article/";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		articleService.deleteArticle(id);
		redirectAttributes.addFlashAttribute("message", "删除文章成功");
		return "redirect:/article/";

	}

	
}
