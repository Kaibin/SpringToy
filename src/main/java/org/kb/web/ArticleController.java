package org.kb.web;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kb.entity.Article;
import org.kb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {		
		Page<Article> articles = articleService.getCategoryArticle(1L, pageNumber, PAGE_SIZE, sortType);
		Iterator it = articles.iterator();

		while (it.hasNext()) {
			Article article = (Article) it.next();
			logger.error("***********"+article.getTitle());
		}
		model.addAttribute("articles", articles);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		
		return "articleList";
	}

	
}
