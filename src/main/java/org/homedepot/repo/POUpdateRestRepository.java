package org.homedepot.repo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.homedepot.domain.Datum;
import org.homedepot.domain.DomainRestTest;
import org.homedepot.domain.POInfo;

import io.quarkus.logging.Log;

@Named("rest")
@ApplicationScoped
public class POUpdateRestRepository implements POUpdateRepository{
	
	@RestClient 
    PORestCall poRestCall;


	@Override
	public POInfo getPoInfo() {
		POInfo poInfo = new POInfo();
		
		DomainRestTest domainRestTest = poRestCall.getPoInfo("2");
		Log.info(domainRestTest);
		Datum datum = domainRestTest.getData().stream().findAny().get();
		poInfo.setPoID(datum.getId().toString());
		poInfo.setVendorName(datum.getFirstName());
		poInfo.setOrderId(domainRestTest.getPage().toString());
		return poInfo;
	}


}
