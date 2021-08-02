/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.dao;

import java.util.List;

import com.pravatpanda.onestop.bean.AddRequest;
import com.pravatpanda.onestop.bean.URLEntry;
import com.pravatpanda.onestop.bean.URLinfo;
import com.pravatpanda.onestop.bean.UpdateRequest;

/**
 * The interface Onestop dao.
 */
public interface OnestopDao {

    /**
     * Display group list.
     *
     * @param splUser the spl user
     * @return the list
     */
    public List<URLinfo> displayGroup(boolean splUser);

    /**
     * Update entry boolean.
     *
     * @param updRequest the upd request
     * @return the boolean
     */
    public boolean updateEntry(UpdateRequest updRequest);

    /**
     * Gets info.
     *
     * @param id the id
     * @return the info
     */
    public URLEntry getInfo(int id);

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
     * Add entry int.
     *
     * @param request the request
     * @return the int
     */
    public int addEntry(AddRequest request);

}
