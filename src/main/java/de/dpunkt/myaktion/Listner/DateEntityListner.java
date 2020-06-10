package de.dpunkt.myaktion.Listner;

import java.util.Calendar;

import javax.persistence.PrePersist;

import de.dpunkt.myaktion.model.DateEntitiy;

public class DateEntityListner {

	
	@PrePersist
	void onPrePersist(Object entity) {
		DateEntitiy dateEntitiy = (DateEntitiy) entity;
		dateEntitiy.setCreatedAt(Calendar.getInstance().getTime());
	}
}
