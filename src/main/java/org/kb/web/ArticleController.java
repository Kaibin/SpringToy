package org.kb.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.kb.domain.Article;
import org.kb.domain.Category;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/article")
public class ArticleController implements ServletContextAware{ //实现ServletContextAware接口，获取本地路径
	
	private ServletContext servletContext;	
	private static final int PAGE_SIZE = 3;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	private Logger logger = Logger.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("sortTypes")
	public Map<String, String> getSortTypes() {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
		return sortTypes;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		List<Category> list = categoryService.findAll();
		Category category = new Category(0L);
		category.setName("全部");
		list.add(0, category);
		return list;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "category", defaultValue = "0") String categoryId,
						@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
						@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {
		Page<Article> articles = null;
		if (categoryId.equals("0")) {
			articles = articleService.getAllArticle(pageNumber, PAGE_SIZE, sortType);
		} else {
			articles = articleService.getCategoryArticle(Long.valueOf(categoryId), pageNumber, PAGE_SIZE, sortType);
		}
		model.addAttribute("articles", articles);
		model.addAttribute("sortType", sortType);
		model.addAttribute("categoryId",categoryId);
		return "articleList";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(@RequestParam(value = "searchTerm", defaultValue = "*") String searchTerm,
						@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
						@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {
		Page<Article> articles = articleService.searchArticle(searchTerm, pageNumber, PAGE_SIZE, sortType);
		model.addAttribute("articles", articles);
		model.addAttribute("sortType", sortType);
		return "articleList";
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String list(@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
//			@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model) {		
//		Page<Article> articles = articleService.getCategoryArticle(1L, pageNumber, PAGE_SIZE, sortType);
//		model.addAttribute("articles", articles);
//		model.addAttribute("sortType", sortType);
//		model.addAttribute("sortTypes", sortTypes);
//		return "articleList";
//	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createFrom(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("action", "create");
		return "articleForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Article article, @ModelAttribute("categoryId")String categoryId, 
			@ModelAttribute("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		Category category = categoryService.getCategory(Long.valueOf(categoryId));
		article.setCategory(category);
		if (!file.isEmpty()) {
			 InputStream is = file.getInputStream();  
			 String uploadPath = this.servletContext.getRealPath("upload") + File.separator + file.getOriginalFilename();
		     FileUtils.copyInputStreamToFile(is, new File(uploadPath)); 
		     article.setAttachment(uploadPath);
			 logger.info("---upload file to: " + uploadPath);
		}
		articleService.saveArticle(article);
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/article/";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("article", articleService.getArticle(id));
		model.addAttribute("action", "update");
		return "articleForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("article") Article article, @ModelAttribute("categoryId")String categoryId, 
							@ModelAttribute("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException{
		Category category = categoryService.getCategory(Long.valueOf(categoryId));
		article.setCategory(category);
		if (!file.isEmpty()) {
			 InputStream is = file.getInputStream();  
			 String uploadPath = this.servletContext.getRealPath("upload") + File.separator + file.getOriginalFilename();
		     FileUtils.copyInputStreamToFile(is, new File(uploadPath)); 
		     article.setAttachment(uploadPath);
			 logger.info("---upload file to: " + uploadPath);
		}
		articleService.saveArticle(article);
		redirectAttributes.addFlashAttribute("message","更新成功");
		return "redirect:/article/";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		articleService.deleteArticle(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/article/";

	}

	@Override
	 public void setServletContext(ServletContext servletContext) { //实现接口中的setServletContext方法
	  this.servletContext = servletContext;
	 }	
}
