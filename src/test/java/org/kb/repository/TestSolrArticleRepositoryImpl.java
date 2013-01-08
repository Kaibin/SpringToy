package org.kb.repository;

import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kb.domain.Article;
import org.kb.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.repository.support.SolrRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSolrArticleRepositoryImpl {
	
	private ArticleRepository solrRepository;
	@Autowired
	SolrOperations solrOperations;

	@Before
	public void setUp() {
		solrRepository = new SolrRepositoryFactory(this.solrOperations).getRepository(ArticleRepository.class,new ArticleRepositoryImpl(this.solrOperations));
	}
	
	@After
	public void tearDown() {
//		solrOperations.delete(new SimpleQuery(new SimpleStringCriteria("*:*")));
//		solrOperations.commit();
	}

	@Test
	public void testQuery() {
		Assert.assertNotNull(solrRepository);
		Assert.assertNotNull(solrOperations);

//		Article article = createArticle(1L);
//		solrRepository.save(article);
//		solrOperations.saveBean(article);
		
		Page<Article> result = solrRepository.search("");
//		Assert.assertEquals(result.getNumberOfElements(), 1);
		Assert.assertNotNull(result);
	}

	protected Article createArticle(Long id) {
		Article article = new Article();
		article.setId(id);
		article.setTitle("测试一下题目" + id);
		article.setAuthor("测试一下作者" + id);
		article.setAttachment("测试一下附件" + id);
		article.setContent("测试一下内容" + id);
		article.setDescription("测试一下描述" + id);
		article.setLink("测试一下链接" + id);
		article.setDate(new Date());
		Category category = new Category();
		category.setId(1L);
		category.setName("测试一下目录");
		article.setCategory(category);
		return article;
	}

}
