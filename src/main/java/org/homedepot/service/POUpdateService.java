package org.homedepot.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.homedepot.data.Poinfo;
import org.homedepot.domain.POInfo;
import org.homedepot.util.PORepoProducers;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class POUpdateService {

	@Inject
	PORepoProducers poRepoProducers;

	@ConfigProperty(name = "po.message", defaultValue = "message...")
	String message;

	@Inject
	Poinfo server;

	public POInfo getPoInfo(String id) {
		POInfo poInfo = poRepoProducers.producePORepoObj(id).getPoInfo();
		poInfo.setMessage(message);
		poInfo.setHost(server.host());
		return poInfo;
	}
	
	public Uni<String> getPoInfo(int id) {
		Uni<String> uni = Uni.createFrom().item("Cameron"+id);
		return uni;
	}
}
