package org.homedepot.repo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.homedepot.domain.DomainRestTest;

@Path("/users")
@RegisterRestClient(configKey = "resttest")
public interface PORestCall {
	
	@GET
	public DomainRestTest getPoInfo(@QueryParam("page") String id);

}
