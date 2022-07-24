package org.homedepot.poresource;

import java.time.Duration;
import java.util.Arrays;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.homedepot.domain.POInfo;
import org.homedepot.service.POUpdateService;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/updatepo")
public class POUpdateResource {
	
	@Inject
	private POUpdateService poUpdateService;
	
	@GET
	@Retry(maxRetries = 4)
	@Timeout(2500)
	@Fallback(fallbackMethod = "fallbackRecommendations")
	@CircuitBreaker(requestVolumeThreshold = 8)
	@Path("/id/{id}")
	@RolesAllowed("adminpo")
	@Produces(MediaType.APPLICATION_JSON)
	public POInfo getPOInfoById(@PathParam("id") String id) {
		
		return poUpdateService.getPoInfo(id);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/mutiny")
	public Uni<String> helloFromMutiny() {
		Uni.createFrom().item(() -> 3 + 4).onItem().delayIt().by(Duration.ofSeconds(3)).onItem()
				.transform(i -> i.toString()).subscribe().with(s -> System.out.println("print" + s));
		
		System.out.println("print before");

		Uni<String> uniOfSring = Uni.createFrom().item(() -> 3 + 4).onItem().delayIt().by(Duration.ofSeconds(1))
				.onItem().transformToUni(i -> poUpdateService.getPoInfo(i));
		
		uniOfSring.subscribe().with(s -> System.out.println("print" + s));
		
		System.out.println("print after");
		return uniOfSring;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/multimutiny")
	public Multi<Integer> helloFromMultiMutiny() {
		Multi<Integer> multiFromIterable = Multi.createFrom().iterable(Arrays.asList(1, 2, 3, 4, 5));
		return multiFromIterable;
	}
	
	
    public POInfo fallbackRecommendations(String id) {
		
		return new POInfo();
	}
	

}
