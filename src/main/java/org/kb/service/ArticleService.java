package org.kb.service;

import java.util.List;

import org.kb.domain.Article;
import org.kb.repository.ArticleRepository;
import org.kb.repository.ArticleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//Spring Bean的标识.
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	public Article getArticle(Long id) {
		return articleRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void saveArticle(Article article) {
		articleRepository.save(article);
	}
	
	@Transactional(readOnly = false)
	public void deleteArticle(Long id) {
		articleRepository.delete(id);
	}
	
	public List<Article> getAllArticle() {
		return (List<Article>)articleRepository.findAll();
	}
	
	public Page<Article> getAllArticle(int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return articleRepository.findAll(pageRequest);
	}
	
	public Page<Article> searchArticle(String searchTerm, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return articleRepository.search(searchTerm, pageRequest);
	}
	
	public Page<Article> searchArticle(String searchTerm) {
		return articleRepository.search(searchTerm);
	}
	
	public Page<Article> getCategoryArticle(Long categoryId, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		return articleRepository.findByCategoryId(categoryId, pageRequest);
	}
	
	private PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}
		return new PageRequest(pageNumber - 1, pageSize, sort);
	}
	
	@Autowired
	public void setArticleDao(ArticleRepository articleDao) {
		this.articleRepository = articleDao;
	}

}
