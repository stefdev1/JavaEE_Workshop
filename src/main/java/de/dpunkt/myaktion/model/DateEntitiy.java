package de.dpunkt.myaktion.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DateEntitiy {
	
	private Date createdAt;

	public Date getCreatedAt() {
		System.out.println("Called DateEntitiy getCreatedAt " + createdAt);

		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		System.out.println("Called DateEntitiy setCreatedAt " + createdAt);
		if(createdAt!=null)
			System.out.println("setCreatedAt " + createdAt.toString());
		this.createdAt = createdAt;
	}
	

}
