/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.servlet;

import com.pravatpanda.onestop.bean.AddRequest;
import com.pravatpanda.onestop.bean.URLEntry;
import com.pravatpanda.onestop.bean.UpdateRequest;
import com.pravatpanda.onestop.main.OnestopManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The type One stop controller.
 */
@Controller
public class OneStopController {
    @Value("${onestop.superuser.name}")
    private String spluser;
    @Value("${onestop.superuser.pass}")
    private String splpass;
    @Value("${onestop.adminuser.name}")
    private String nosadminuser;
    @Value("${onestop.adminuser.pass}")
    private String nosadminpass;

    /**
     * The Manager.
     */
    @Autowired
    OnestopManager manager;
    /**
     * The Log.
     */
    Logger log = Logger.getLogger(getClass());

    /**
     * Show string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping({"/showResult.nos", "/"})
    public String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : show");
        boolean splUser = false;
        HttpSession session = request.getSession();
        if (null != session) {
            Boolean user = (Boolean) session.getAttribute("SPLUSER");
            if (null != user) splUser = user.booleanValue();
        }

        Map<String, List<URLEntry>> arrangegroup = manager.arrangeGroup(splUser);

        request.setAttribute("GROUPS", arrangegroup);


//		RequestDispatcher rd=request.getRequestDispatcher("/Result.jsp");
//		rd.forward(request, response);
        log.debug("Method End : show");
        return "result";
    }

    /**
     * Remove record.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/removeRecord.nos")
    public void removeRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : removeRecord");
        int id = Integer.parseInt(request.getParameter("Id"));

        manager.removeEntry(id);
        request.setAttribute("SCSS_MSG", "Selected Record Removed");
        RequestDispatcher rd = request.getRequestDispatcher("/showResult.nos");
        rd.forward(request, response);
        log.debug("Method End : removeRecord");
    }

    /**
     * Show add new string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/displayAddUI.nos")
    public String showAddNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : showAddNew");
        HttpSession session = request.getSession();
        boolean adminUser = false;
        if (null != session) {
            Boolean adminAuthenticated = (Boolean) session.getAttribute("AUTH");
            if (null != adminAuthenticated) adminUser = adminAuthenticated.booleanValue();
        }

        if (!adminUser) {
            request.setAttribute("ERR_MSG", "Either session timed out or ou are not an admin user. Please authenticate");
            return showLogin(request, response);
        } else {
            List<String> availableGroups = manager.getAvailableGroups();
            request.setAttribute("GROUPNAMES", availableGroups);

            log.debug("Method End : showAddNew");
            return "add";
        }
    }

    /**
     * Add new.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/addNewEntry.nos")
    public void addNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : addNew");
        String groupName = request.getParameter("GnameLst");
        if (!(null != groupName && !groupName.trim().equals("Select A Group"))) {
            groupName = request.getParameter("Gname");
        }
        String urlname = request.getParameter("URLname");
        String textname = request.getParameter("Textname");
        boolean islink = Boolean.parseBoolean(request.getParameter("IsLinkname"));
        boolean isSpl = false;
        String spl = request.getParameter("isSpl");
        if (null != spl) {
            isSpl = Boolean.parseBoolean(request.getParameter("isSpl"));
        }

        //boolean showIfAdmin = Boolean.parseBoolean(request.getParameter("visibleToAdminOnly"));
        boolean incorrectInput = false;
        log.info("Group Name : " + groupName + ", Url : " + urlname + ", Text : " + textname + ", Islink : " + islink);
        if (StringUtils.isEmpty(groupName)) {
            request.setAttribute("ERR_MSG", "Please Select a Group Name or Create a New");
            incorrectInput = true;
        } else if (StringUtils.isEmpty(urlname)) {
            request.setAttribute("ERR_MSG", "Please Enter the URL");
            incorrectInput = true;
        } else if (StringUtils.isEmpty(textname)) {
            request.setAttribute("ERR_MSG", "Please Enter the Display Text for URL");
            incorrectInput = true;
        }

        if (incorrectInput) {
            RequestDispatcher rd = request.getRequestDispatcher("/displayAddUI.nos");
            rd.forward(request, response);
        } else {
            AddRequest addRequest = new AddRequest(groupName, urlname, textname, islink);
            addRequest.setSplUser(isSpl);
            int add = manager.add(addRequest);
            if (add >= 1) {
                request.setAttribute("SCSS_MSG", add + " Entry created");
                RequestDispatcher rd = request.getRequestDispatcher("/showResult.nos");
                rd.forward(request, response);
            }

        }

        log.debug("Method End : addNew");
    }

    /**
     * Show update string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/showUpdate.nos")
    public String showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : showUpdate");

        HttpSession session = request.getSession();
        boolean adminUser = false;
        if (null != session) {
            Boolean adminAuthenticated = (Boolean) session.getAttribute("AUTH");
            if (null != adminAuthenticated) adminUser = adminAuthenticated.booleanValue();
        }

        if (!adminUser) {
            request.setAttribute("ERR_MSG", "Either session timed out or ou are not an admin user. Please authenticate");
            return showLogin(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("Id"));
            URLEntry urlEntry = manager.get(id);

            request.setAttribute("URLENTRY", urlEntry);
//            RequestDispatcher rd = request.getRequestDispatcher("/showUpdateDetails.jsp");
//            rd.forward(request, response);
            log.debug("Method End : showUpdate");
            return "showUpdateDetails";
        }
    }

    /**
     * Process update.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/processUpdate.nos")
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
        if (null != spl) {
            isSpl = Boolean.parseBoolean(request.getParameter("isSpl"));
        }

        UpdateRequest updateRequest = new UpdateRequest(id, modifiedgrpname, modifiedurlname, modifiedtextname, modifiedislink);
        updateRequest.setSplUser(isSpl);

        boolean update = manager.update(updateRequest);
        if (update == true) {
            request.setAttribute("SCSS_MSG", "Entry updated for : " + modifiedtextname);
        } else {
            request.setAttribute("SCSS_MSG", "Entry could not be updated for " + modifiedtextname);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/showResult.nos");
        rd.forward(request, response);
        log.debug("Method End : processUpdate");
    }

    /**
     * Show login string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/showLogin.nos")
    public String showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : showLogin");
//		RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
//		rd.forward(request, response);
        log.debug("Method End : showLogin");
        return "login";
    }

    /**
     * Show create admin.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/showCreateAdmin.nos")
    public void showCreateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Create admin.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/processCreateAdmin.nos")
    public void createAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Validate login.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/processLogin.nos")
    public void validateLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : validateLogin");
        String username = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (StringUtils.equalsIgnoreCase(nosadminuser, username)
                && StringUtils.equals(nosadminpass, pass)) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(15 * 60); // 10 mins
            session.setAttribute("AUTH", new Boolean(true));
            session.setAttribute("USERNAME", username);

            RequestDispatcher rd = request.getRequestDispatcher("/showResult.nos");
            rd.forward(request, response);
        } else if (StringUtils.equalsIgnoreCase(spluser, username)
                && StringUtils.equals(splpass, pass)) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(15 * 60); // 15 mins
            session.setAttribute("AUTH", new Boolean(true));
            session.setAttribute("USERNAME", username);
            session.setAttribute("SPLUSER", true);

            RequestDispatcher rd = request.getRequestDispatcher("/showResult.nos");
            rd.forward(request, response);
        } else {
            request.setAttribute("ERR_MSG", "invalid login. Please reauthenticate");
            RequestDispatcher rd = request.getRequestDispatcher("/showLogin.nos");
            rd.forward(request, response);
        }
        log.debug("Method End : validateLogin");
    }

    /**
     * Process logout string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping("/logout.nos")
    public String processLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Method Start : processLogout");


        HttpSession session = request.getSession(false);
        String name = null;
        if (null != session) {
            name = (String) session.getAttribute("USERNAME");
            session.invalidate();
        }
        request.setAttribute("SCSS_MSG", "User " + name + " logged out");
        log.debug("Method End : processLogout");
        return show(request, response);

    }

}
