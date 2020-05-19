package de.dpunkt.myaktion.util;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;

import de.dpunkt.myaktion.util.LogQualifier.FachLog;
import de.dpunkt.myaktion.util.LogQualifier.TecLog;

@Dependent
public class Resources {
	
	@Produces @TecLog
	public Logger produceTecLog(InjectionPoint injectionPoint) {
		return Logger.getLogger("TechLog@"+ injectionPoint.getMember().getDeclaringClass().getName(), "messages");
	}
	
	@Produces @FachLog
	public Logger produceFachLog(InjectionPoint injectionPoint) {
		return Logger.getLogger("FachLog@" + injectionPoint.getMember().getDeclaringClass().getName(), "messages");
	}
	
	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
