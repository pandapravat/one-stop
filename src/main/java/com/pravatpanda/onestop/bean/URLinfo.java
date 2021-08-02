/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.bean;

/**
 * The type Ur linfo.
 */
public class URLinfo {
	private int id;
	private String groupname;
	
	private String text;
	private String url;
	private boolean isLink;

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
     * Gets groupname.
     *
     * @return the groupname
     */
    public String getGroupname() {
		return groupname;
	}

    /**
     * Sets groupname.
     *
     * @param groupname the groupname
     */
    public void setGroupname(String groupname) {
		this.groupname = groupname;
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
     * Is link boolean.
     *
     * @return the boolean
     */
    public boolean isLink() {
		return isLink;
	}

    /**
     * Sets link.
     *
     * @param isLink the is link
     */
    public void setLink(boolean isLink) {
		this.isLink = isLink;
	}
	
	@Override
	public String toString() {
		return "ID : " + id + ", Text: " + text + ", Url : " + url + ", isLink :" + isLink;
	}

}
