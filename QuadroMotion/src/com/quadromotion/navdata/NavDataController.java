package com.quadromotion.navdata;

import com.quadromotion.model.Model;
import com.quadromotion.pilotingstates.PilotingStates;

import de.yadrone.base.IARDrone;

public class NavDataController {

	private Model model = null;
	private NavDataListener navDataListener = null;

	public NavDataController(Model model, IARDrone drone) {
		this.model = model;
		this.navDataListener = new NavDataListener(drone, this);
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

	public void setControlState(String string) {
		if (!string.equals(model.getControlState())) {
			model.setControlState(string);
			if (string.equals("LANDED"))
				model.setPilotingState(PilotingStates.STATE_2_READY);
			else if(string.equals("HOVERING"))
				model.setPilotingState(PilotingStates.STATE_5_HOVERING);
			
		}
	}
}
