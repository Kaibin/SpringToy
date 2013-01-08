package org.kb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="article")
public class Article extends IdEntity{
	
	private static final long serialVersionUID = -5170398606065544445L;

	@Field("title")
	private String title;
	
	@Field("description")
	private String description;
	
	@Field("content")
	private String content;
	
	@Field("author")
	private String author;
	
	private String link;
	
	private String attachment;
	
	@Field("date")
	private Date date;
	
	private Category category;

	public Article() {
		super();
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
	
	@Column(name="attachment")
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	@Column(name="date")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+08:00")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ",title=" + title + ", description=" + description
				+ ", content=" + content + ", author=" + author + ", link="
				+ link + ", attachment=" + attachment + ", date=" + date
				+ ", category=" + category + "]";
	}
	
	
}
