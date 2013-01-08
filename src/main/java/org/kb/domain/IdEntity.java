package org.kb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 
 * @author kaibin
 */
//JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity implements Serializable{

	private static final long serialVersionUID = -5676694680777491651L;
	
	@Field("id")
	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}