/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.pravatpanda.onestop.bean.AddRequest;
import com.pravatpanda.onestop.bean.URLEntry;
import com.pravatpanda.onestop.bean.URLinfo;
import com.pravatpanda.onestop.bean.UpdateRequest;
import com.pravatpanda.onestop.dao.OneStopDao;
import com.pravatpanda.onestop.ui.model.UrlEntryGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Nielsen manager.
 */
@Component
public class NielsenManagerImpl implements NielsenManager {

    /**
     * The Dao.
     */
    @Autowired
    OneStopDao dao;

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public OneStopDao getDao() {
		return dao;
	}

    /**
     * Sets dao.
     *
     * @param dao the dao
     */
    public void setDao(OneStopDao dao) {
		this.dao = dao;
	}

	public List<UrlEntryGroup> arrangeGroup(boolean splUser) {
		
//		List<URLEntry> ure = new ArrayList<URLEntry>() ;
		Map<String, List<URLEntry>> groups = new TreeMap<String, List<URLEntry>>();
		
		List<URLinfo> urls  = dao.displayGroup(splUser);
		
		for (URLinfo urLinfo : urls) {
			
			List<URLEntry> ure = groups.get(urLinfo.getGroupname());
			if (ure == null) {
				ure = new ArrayList<URLEntry>();
				groups.put(urLinfo.getGroupname(), ure);
			}
			URLEntry ue = new URLEntry();
			ue.setId(urLinfo.getId());
			ue.setGroupName(urLinfo.getGroupname());
			ue.setText(urLinfo.getText());
			ue.setUrl(urLinfo.getUrl());
			ue.setIslink(urLinfo.isLink());
			
			ure.add(ue);
		}
		sortEntriesInGroup(groups);
		
		return createGroupLists(groups);
	}

	
	

	private List<UrlEntryGroup> createGroupLists(Map<String, List<URLEntry>> groups) {
		
		List<UrlEntryGroup> groupList = new ArrayList<UrlEntryGroup>();
		Set<Entry<String, List<URLEntry>>> entrySet = groups.entrySet();
		for (Entry<String, List<URLEntry>> entry : entrySet) {
			UrlEntryGroup urlEntryGroup = new UrlEntryGroup();
			urlEntryGroup.setGroupName(entry.getKey());
			urlEntryGroup.setGroupEntries(entry.getValue());
			
			groupList.add(urlEntryGroup);
		}
		
		return groupList;
	}

	private void sortEntriesInGroup(Map<String, List<URLEntry>> groups) {
		Set<Entry<String,List<URLEntry>>> entrySet = groups.entrySet();
		
		for (Entry<String, List<URLEntry>> entry : entrySet) {
			
			List<URLEntry> value = entry.getValue();
			
			Collections.sort(value, new Comparator<URLEntry>() {

				public int compare(URLEntry o1, URLEntry o2) {
					
					if(null != o1.getText() && null != o2.getText())
						return o1.getText().compareTo(o2.getText());
					else return 0;
				}
				
			});
		}
	}

	public boolean update(UpdateRequest updRequest) {
		
		boolean flag;
		flag = dao.updateEntry(updRequest);
		return flag;
	}

	public URLEntry get(int id) {
		URLEntry urlEntry = dao.getInfo(id);
		
		return urlEntry;
	}

	public int add(AddRequest request) {
		
		if(request.isUrl()) {
			if(!(StringUtils.startsWith(request.getUrl(), "http://") || StringUtils.startsWith(request.getUrl(), "https://"))) {
				request.setUrl("http://" + request.getUrl()); // http protocol to the url
			}
		}
		int addEntry = dao.addEntry(request);
		
		return addEntry;
	}

	public List<String> getAvailableGroups() {
		List<String> availableGroups = dao.getAvailableGroups();
		Collections.sort(availableGroups);
		return availableGroups;
	}

	public void removeEntry(int id) {
		dao.removeEntry(id);
		
	}

	
	
	
	
	
	

}
