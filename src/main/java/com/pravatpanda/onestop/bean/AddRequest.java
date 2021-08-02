/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.bean;

/**
 * The type Add request.
 */
public class AddRequest {
	private String groupName;
	private String url;
	private String urlText;
	private boolean isSplUser;
	private boolean isAdminlUser;
	private boolean isUrl;

	/**
	 * Instantiates a new Add request.
	 *
	 * @param groupName the group name
	 * @param url       the url
	 * @param urlText   the url text
	 * @param isUrl     the is url
	 */
	public AddRequest(String groupName, String url, String urlText, boolean isUrl) {
		this.groupName = groupName;
		this.url = url;
		this.urlText = urlText;
		this.isUrl = isUrl;
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
	 * @param groupName the group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @param url the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets url text.
	 *
	 * @return the url text
	 */
	public String getUrlText() {
		return urlText;
	}

	/**
	 * Sets url text.
	 *
	 * @param urlText the url text
	 */
	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}

	/**
	 * Is spl user boolean.
	 *
	 * @return the boolean
	 */
	public boolean isSplUser() {
		return isSplUser;
	}

	/**
	 * Sets spl user.
	 *
	 * @param isSplUser the is spl user
	 */
	public void setSplUser(boolean isSplUser) {
		this.isSplUser = isSplUser;
	}

	/**
	 * Is adminl user boolean.
	 *
	 * @return the boolean
	 */
	public boolean isAdminlUser() {
		return isAdminlUser;
	}

	/**
	 * Sets adminl user.
	 *
	 * @param isAdminlUser the is adminl user
	 */
	public void setAdminlUser(boolean isAdminlUser) {
		this.isAdminlUser = isAdminlUser;
	}

	/**
	 * Is url boolean.
	 *
	 * @return the boolean
	 */
	public boolean isUrl() {
		return isUrl;
	}

	/**
	 * Sets url.
	 *
	 * @param isUrl the is url
	 */
	public void setUrl(boolean isUrl) {
		this.isUrl = isUrl;
	}
}
