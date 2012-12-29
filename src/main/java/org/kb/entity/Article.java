package org.kb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="article")
public class Article extends IdEntity{
	private String title;
	private String description;
	private String content;
	private String author;
	private String link;
	private Date date;
	private Category category;

	public Article() {
		super();
	}
	
	public Article(Long id) {
		this.id = id;
	}

	public Article(String title, String description, String content,
			String author, String link, Date date) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
		this.author = author;
		this.link = link;
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name="link")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@Column(name="date")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+08:00")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
