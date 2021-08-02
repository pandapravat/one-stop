/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.bean;

/**
 * The type Url entry.
 */
public class URLEntry {
	private int id;
	private String groupName;
	private String url;
	private boolean islink;
	private boolean isSpl;

	/**
	 * Is spl boolean.
	 *
	 * @return the boolean
	 */
	public boolean isSpl() {
		return isSpl;
	}

	/**
	 * Sets spl.
	 *
	 * @param isSpl the is spl
	 */
	public void setSpl(boolean isSpl) {
		this.isSpl = isSpl;
	}
	private String text;


	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets text.
	 *
	 * @param text the text
	 */
	public void setText(String text) {
		this.text = text;
	}

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
	 * @param groupname the groupname
	 */
	public void setGroupName(String groupname) {
		this.groupName = groupname;
	}

	/**
	 * Gets url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets url.
	 *
	 * @param uRL the u rl
	 */
	public void setUrl(String uRL) {
		url = uRL;
	}

	/**
	 * Is islink boolean.
	 *
	 * @return the boolean
	 */
	public boolean isIslink() {
		return islink;
	}

	/**
	 * Sets islink.
	 *
	 * @param islink the islink
	 */
	public void setIslink(boolean islink) {
		this.islink = islink;
	}
	

}
