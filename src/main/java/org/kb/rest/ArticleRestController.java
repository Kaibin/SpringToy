//package org.kb.rest;
//
//import java.net.URI;
//import java.util.List;
//
//import org.kb.domain.Article;
//import org.kb.service.ArticleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.util.UriComponentsBuilder;
//
//
//@Controller
//@RequestMapping(value = "/api/v1/article")
//public class ArticleRestController {
//
//	@Autowired
//	private ArticleService articleService;
//	
//	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<Article> list() {
//		return articleService.getAllArticle();
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<?> get(@PathVariable("id") Long id) {
//		Article article = articleService.getArticle(id);
//		if (article == null) {
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity(article, HttpStatus.OK);
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<?> create(@RequestBody Article article, UriComponentsBuilder uriBuilder) {
//		articleService.saveArticle(article);
//		
//		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
//		String id = article.getId();
//		URI uri = uriBuilder.path("/api/v1/article/" + id).build().toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(uri);
//		
//		return new ResponseEntity(headers, HttpStatus.CREATED);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> update(@RequestBody Article article) {
//		
//		articleService.saveArticle(article);
//
//		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable("id") Long id) {
//		articleService.deleteArticle(id);
//	}
//}
