/**
 * 
 */
package com.adserver.campaign.server;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Athi
 *
 */
public class ResourceFactory {
	@Inject
	private Instance<Resource> resources;
	
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resourceList = new LinkedHashSet<Class<?>>();
		resources.forEach(resource -> resourceList.add(resource.getClass()));
		return resourceList;
	}
}
