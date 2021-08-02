/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.ui.model;

import java.util.List;

import com.pravatpanda.onestop.bean.URLEntry;

/**
 * The type Url entry group.
 */
public class UrlEntryGroup {
	private String groupName;
	private List<URLEntry> groupEntries;

	/**
	 * Gets group name.
	 *
	 * @return the group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets group name.
	 *
	 * @param groupName the group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Gets group entries.
	 *
	 * @return the group entries
	 */
	public List<URLEntry> getGroupEntries() {
		return groupEntries;
	}

	/**
	 * Sets group entries.
	 *
	 * @param groupEntries the group entries
	 */
	public void setGroupEntries(List<URLEntry> groupEntries) {
		this.groupEntries = groupEntries;
	}

}
