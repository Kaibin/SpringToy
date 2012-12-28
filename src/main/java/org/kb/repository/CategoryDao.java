package org.kb.repository;

import org.kb.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {
	public Category findByName(String name);
}
