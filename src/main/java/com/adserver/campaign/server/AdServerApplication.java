package com.adserver.campaign.server;

public class AdServerApplication {
	public static void main(String[] args) throws ServletException {
		
		new UndertowEmbeddedServer("0.0.0.0", 8080)
			.contextPath("/app-name")
			.deploymentName("app-name")
			.appPath("/api")
			.resourcesClass(ResourceFactory.class)
			.start();
	}
}
