package org.kb.repository;

import org.apache.log4j.Logger;
import org.kb.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;


public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
	private Logger logger = Logger.getLogger(ArticleRepositoryImpl.class);
	@Autowired
	private SolrOperations solrTemplate;
	
	public ArticleRepositoryImpl() {
		super();
	}

	public ArticleRepositoryImpl(SolrOperations solrTemplate) {
		super();
		this.solrTemplate = solrTemplate;
	}

	@Override
	public Page<Article> search(String searchTerm, Pageable pageRequest) {
        logger.debug("Search articles with search term: " + searchTerm);
        if (searchTerm.trim().length() == 0) {
			searchTerm = "*";
		}
		return solrTemplate.queryForPage(new SimpleQuery(new SimpleStringCriteria("all:" + searchTerm)).setPageRequest(pageRequest), Article.class);
	}
	
	@Override
	public Page<Article> search(String searchTerm) {
        logger.debug("Search articles with search term: " + searchTerm);
        if (searchTerm.trim().length() == 0) {
			searchTerm = "*";
		}
		return solrTemplate.queryForPage(new SimpleQuery(new SimpleStringCriteria("all:" + searchTerm)), Article.class);
	}
}
