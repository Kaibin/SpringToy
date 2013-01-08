package org.kb.repository;

import org.kb.domain.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	public Category findByName(String name);
}
