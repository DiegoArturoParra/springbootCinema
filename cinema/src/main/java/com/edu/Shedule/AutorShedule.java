package com.edu.cundi.cinema.schedule;

import com.edu.cundi.cinema.services.interfaces.IAutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutorShedule {
	
	@Autowired
	private IAutorService service;

	
	@Scheduled(fixedRate = 500000)
	public void scheduleTaskWithFixedRate() {
	    System.out.println("Tarea programada scheduleTaskWithFixedRate");
	}	

}