package org.kb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kb.entity.App;

@Path("app")
public class AppService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//{"app_version":"1.0","app_data_version":"1.0","app_update_title":"title","app_update_content":"hello "}
	public App getAppInfo() {
		App app = new App();
		app.setApp_version("1.0");
		app.setApp_data_version("1.0");
		app.setApp_update_title("title");
		app.setApp_update_content("content");
		return app;
	}
}
