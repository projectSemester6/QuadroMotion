package com.quadromotion.navdata;

import com.quadromotion.model.Model;

import de.yadrone.base.IARDrone;

public class NavDataController {

	private Model model = null;
	private NavDataListener navDataListener = null;

	public NavDataController(Model model, IARDrone drone) {
		this.model = model;
		setNavDataListener(new NavDataListener(drone, this));
	}

	public void setBatteryLevel(int percentage) {
		if (percentage != model.getBatLevel())
			model.setBatLevel(percentage);
	}

	public void setAltitude(int altitude) {
		if (altitude != model.getAltitude())
			model.setAltitude(altitude);
	}

	public NavDataListener getNavDataListener() {
		return navDataListener;
	}

	public void setNavDataListener(NavDataListener navDataListener) {
		this.navDataListener = navDataListener;
	}
}