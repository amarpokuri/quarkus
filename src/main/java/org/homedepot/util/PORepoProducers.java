package org.homedepot.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.homedepot.repo.POUpdateDBRepository;
import org.homedepot.repo.POUpdateRepository;

@ApplicationScoped
public class PORepoProducers {
	
	@Inject
	@Named("db")
	private POUpdateRepository poUpdateDBRepository;
	
	@Inject
	@Named("rest")
	private POUpdateRepository poUpdateRestRepository;
	
	
	public POUpdateRepository producePORepoObj(String type) {
		if(type.equals("rest")) {
			return poUpdateRestRepository;
		}
		
		return poUpdateDBRepository;
	}

}
