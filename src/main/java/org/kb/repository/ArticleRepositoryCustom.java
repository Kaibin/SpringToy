package org.kb.repository;

import org.kb.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
	Page<Article> search(String searchTerm, Pageable pageRequest);
	
	Page<Article> search(String searchTerm);

}
