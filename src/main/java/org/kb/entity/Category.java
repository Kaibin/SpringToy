package org.kb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category")
public class Category extends IdEntity {
	private String name;
	private List<Article> articles;

	public Category() {
		super();
	}

	public Category(Long id) {
		this.id = id;
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}


	@OneToMany(targetEntity=Article.class,mappedBy = "category", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@Transient
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
