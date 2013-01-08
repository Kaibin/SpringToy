package org.kb.repository;

import org.kb.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ArticleRepository extends ArticleRepositoryCustom, SolrCrudRepository<Article, Long> {
	public Page<Article> findByCategoryId(Long id, Pageable pageRequest);
			
}
