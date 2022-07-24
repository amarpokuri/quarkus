package org.homedepot.repo;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.homedepot.data.Orderhd;
import org.homedepot.domain.POInfo;

@Named("db")
@ApplicationScoped
public class POUpdateDBRepository implements POUpdateRepository{

	@Override
	public POInfo getPoInfo() {
		POInfo poInfo = new POInfo();
		List<Orderhd> userList = Orderhd.listAll();
		Orderhd order = userList.stream().findAny().get();
		poInfo.setOrderId(order.orderId);
		poInfo.setVendorName(order.vendorName);
		poInfo.setPoID(order.poId);
		return poInfo;
	}
}
