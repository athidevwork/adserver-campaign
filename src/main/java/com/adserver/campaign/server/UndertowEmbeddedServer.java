/**
 * 
 */
package com.adserver.campaign.server;

/**
 * @author Athi
 *
 */
public class UndertowEmbeddedServer {
	private final UndertowJaxrsServer undertowJaxrsServer = new UndertowJaxrsServer();
	private String contextPath = "/app-name";
	private String deploymentName = "app-name";
	private String appPath = "/api";
	private Class<? extends Application> resourcesClass;

	public UndertowEmbeddedServer(final String host, final Integer port) {
		Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(port, host);
		this.undertowJaxrsServer.start(serverBuilder);
	}
	
	public UndertowEmbeddedServer contextPath(final String contextPath) {
		this.contextPath = contextPath;
		return this;
	}
	
	public UndertowEmbeddedServer deploymentName(final String deploymentName) {
		this.deploymentName = deploymentName;
		return this;
	}
	
	public UndertowEmbeddedServer appPath(final String appPath) {
		this.appPath = appPath;
		return this;
	}
	
	public UndertowEmbeddedServer resourcesClass(final Class<? extends Application> resourcesClass) {
		this.resourcesClass = resourcesClass;
		return this;
	}
	
	private DeploymentInfo deployApplication() {
		final ResteasyDeployment deployment = new ResteasyDeployment();
		deployment.setInjectorFactoryClass(CdiInjectorFactory.class.getName());
		deployment.setApplicationClass(resourcesClass.getName());
		return this.undertowJaxrsServer.undertowDeployment(deployment, appPath);
	}
	
	public void start() throws ServletException {
		final DeploymentInfo deploymentInfo = deployApplication()
		        .setClassLoader(EmbeddedServer.class.getClassLoader())
		        .setContextPath(contextPath)
		        .setDeploymentName(deploymentName)
		        .addListeners(Servlets.listener(Listener.class));
		
		this.undertowJaxrsServer.deploy(deploymentInfo);
	}
}
