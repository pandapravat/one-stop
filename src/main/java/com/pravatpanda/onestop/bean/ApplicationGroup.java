/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.bean;

import java.util.List;

/**
 * The type Application group.
 */
public class ApplicationGroup {
 
	private String Groupname;
	private List<URLEntry> ue;


	/**
	 * Gets groupname.
	 *
	 * @return the groupname
	 */
	public String getGroupname() {
		return Groupname;
	}

	/**
	 * Sets groupname.
	 *
	 * @param groupname the groupname
	 */
	public void setGroupname(String groupname) {
		Groupname = groupname;
	}

	/**
	 * Gets ue.
	 *
	 * @return the ue
	 */
	public List<URLEntry> getUe() {
		return ue;
	}

	/**
	 * Sets ue.
	 *
	 * @param ue the ue
	 */
	public void setUe(List<URLEntry> ue) {
		this.ue = ue;
	}
	
	
}
