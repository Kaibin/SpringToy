package org.kb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class App implements Serializable{
	
	static final long serialVersionUID = 5842142958423069840L;
	String app_version;
	String app_data_version;
	String app_update_title;
	String app_update_content;
	
	public App() {
		
	}
	
	public App(String app_version, String app_data_version,
			String app_update_title, String app_update_content) {
		super();
		this.app_version = app_version;
		this.app_data_version = app_data_version;
		this.app_update_title = app_update_title;
		this.app_update_content = app_update_content;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getApp_data_version() {
		return app_data_version;
	}

	public void setApp_data_version(String app_data_version) {
		this.app_data_version = app_data_version;
	}

	public String getApp_update_title() {
		return app_update_title;
	}

	public void setApp_update_title(String app_update_title) {
		this.app_update_title = app_update_title;
	}

	public String getApp_update_content() {
		return app_update_content;
	}

	public void setApp_update_content(String app_update_content) {
		this.app_update_content = app_update_content;
	}
	
}
