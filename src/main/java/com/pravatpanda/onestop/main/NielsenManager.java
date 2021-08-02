/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.main;

import java.util.List;

import com.pravatpanda.onestop.bean.AddRequest;
import com.pravatpanda.onestop.bean.URLEntry;
import com.pravatpanda.onestop.bean.UpdateRequest;
import com.pravatpanda.onestop.ui.model.UrlEntryGroup;

/**
 * The interface Nielsen manager.
 */
public interface NielsenManager {

	/**
	 * Arrange group list.
	 *
	 * @param splUser the spl user
	 * @return the list
	 */
	public List<UrlEntryGroup> arrangeGroup(boolean splUser);

	/**
	 * Update boolean.
	 *
	 * @param updRequest the upd request
	 * @return the boolean
	 */
	public boolean update(UpdateRequest updRequest);

	/**
	 * Get url entry.
	 *
	 * @param id the id
	 * @return the url entry
	 */
	public URLEntry get(int id);

	/**
	 * Gets available groups.
	 *
	 * @return the available groups
	 */
	public List<String> getAvailableGroups();

	/**
	 * Remove entry.
	 *
	 * @param id the id
	 */
	public void removeEntry(int id);

	/**
	 * Add int.
	 *
	 * @param addRequest the add request
	 * @return the int
	 */
	public int add(AddRequest addRequest);

}
