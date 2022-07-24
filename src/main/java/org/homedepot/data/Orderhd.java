package org.homedepot.data;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Orderhd extends PanacheEntity{
	
	
	public String orderId;
	public String poId;
	public String vendorName;
	
	public static Orderhd findByName(String name){
        return find("orderId", name).firstResult();
    }

}
