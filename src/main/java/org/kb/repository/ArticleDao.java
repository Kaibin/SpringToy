package org.kb.repository;

import org.kb.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {
	public Page<Article> findByCategoryId(Long id, Pageable pageRequest);
	
	@Modifying
	@Query("delete from Article article where article.category.id = ?1")
	public void deleteByCategoryId(Long id);
		
}
