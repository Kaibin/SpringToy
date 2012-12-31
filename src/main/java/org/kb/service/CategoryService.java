package org.kb.service;

import java.util.List;

import org.kb.domain.Category;
import org.kb.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//Spring Bean的标识.
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class CategoryService {
	private CategoryDao categoryDao;
	
	public Category getCategory(Long id) {
		return categoryDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void saveCategory(Category category) {
		categoryDao.save(category);
	}
	
	@Transactional(readOnly = false)
	public List<Category> findAll() {
		return (List<Category>) categoryDao.findAll();
	}
	
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}
