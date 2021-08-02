/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pravatpanda.onestop.bean.URLEntry;
import com.pravatpanda.onestop.bean.UpdateRequest;
import com.pravatpanda.onestop.main.NielsenManager;
import com.pravatpanda.onestop.ui.model.UrlEntryGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Nielsen one stop ext controller.
 */
@Controller
public class NielsenOneStopExtController {
    /**
     * The Manager.
     */
    @Autowired
	NielsenManager manager;
    /**
     * The Log.
     */
    Logger log = Logger.getLogger(getClass());

    /**
     * Show list.
     *
     * @param request  the request
     * @param response the response
     * @return the list
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/ext/showResult.nos")
	public @ResponseBody List<URLEntry> showExt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Method Start : show");
		boolean splUser = false;
		HttpSession session = request.getSession();
		if(null != session) {
			Boolean user = (Boolean) session.getAttribute("SPLUSER");
			if(null != user) splUser = user.booleanValue();
		}

		List<UrlEntryGroup> groups = manager.arrangeGroup(splUser);


		List<URLEntry> allEntries = new ArrayList<URLEntry>();
		for (UrlEntryGroup urlEntryGroup : groups) {
			allEntries.addAll(urlEntryGroup.getGroupEntries());
		}
		return allEntries;
	}

    /**
     * Process update.
     *
     * @param id               the id
     * @param modifiedgrpname  the modifiedgrpname
     * @param modifiedurlname  the modifiedurlname
     * @param modifiedtextname the modifiedtextname
     * @param modifiedislink   the modifiedislink
     * @param isSpl            the is spl
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
//    @RequestMapping("/ext/processUpdate.nos")
//	public @ResponseBody void processExtUpdate(@RequestParam(value="id", required=true) int id,
//			@RequestParam(value="groupName", required=true) String modifiedgrpname,
//			@RequestParam(value="url", required=true) String modifiedurlname,
//			@RequestParam(value="text", required=true) String modifiedtextname,
//			@RequestParam(value="islink", required=true) boolean modifiedislink,
//			@RequestParam(value="isSpl", required=true) boolean isSpl) throws ServletException, IOException {
//		log.debug("Method Start : processUpdate");
//
//		UpdateRequest updateRequest = new UpdateRequest(id, modifiedgrpname, modifiedurlname, modifiedtextname, modifiedislink);
//		updateRequest.setSplUser(isSpl);
//
//		boolean update = manager.update(updateRequest);
//		if (update!=true) {
//			throw new RuntimeException("Error!!!");
//		}
//		/*RequestDispatcher rd=request.getRequestDispatcher("/showResult.nos");
//		rd.forward(request, response);*/
//		log.debug("Method End : processUpdate");
//	}

	/**
	 * Process update.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      the io exception
	 */
	@RequestMapping("ext/processUpdate.nos")
	public void processUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("Method Start : processUpdate");

		/*boolean splUser = false;
		HttpSession session = request.getSession();
		if(null != session) {
			Boolean user = (Boolean) session.getAttribute("SPLUSER");
			if(null != user) splUser = user.booleanValue();
		}*/

		int id = Integer.parseInt(request.getParameter("id"));
		String modifiedgrpname = request.getParameter("Gname");
		String modifiedurlname = request.getParameter("URLname");
		String modifiedtextname = request.getParameter("Textname");
		boolean modifiedislink = Boolean.parseBoolean(request.getParameter("IsLinkname"));
		boolean isSpl = false;
		String spl = request.getParameter("isSpl");
		if(null != spl) {
			isSpl = Boolean.parseBoolean(request.getParameter("isSpl"));
		}

		UpdateRequest updateRequest = new UpdateRequest(id, modifiedgrpname, modifiedurlname, modifiedtextname, modifiedislink);
		updateRequest.setSplUser(isSpl);

		boolean update = manager.update(updateRequest);
		if (update==true) {
			request.setAttribute("SCSS_MSG", "Entry updated for : " + modifiedtextname);
		}
		else {
			request.setAttribute("SCSS_MSG", "Entry could not be updated for " + modifiedtextname);
		}
		RequestDispatcher rd=request.getRequestDispatcher("/showResult.nos");
		rd.forward(request, response);
		log.debug("Method End : processUpdate");
	}


}
