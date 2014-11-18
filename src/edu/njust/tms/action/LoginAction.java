package edu.njust.tms.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import edu.njust.tms.dao.AdminDAO;
import edu.njust.tms.dao.Admin;

public class LoginAction extends ActionSupport {
 
	
	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/*???????????????????????????*/
	public String view() {
		
		return "login_view";
	}
	 
	
	/* ?????????????????? */
	public String CheckLogin() {
		AdminDAO adminDAO = new AdminDAO();
		ActionContext ctx = ActionContext.getContext();
		if (!adminDAO.CheckLogin(admin)) {
			ctx.put("error",  java.net.URLEncoder.encode(adminDAO.getErrMessage()));
			return "error";
		}
		ctx.getSession().put("username", admin.getUsername());
		return "main_view";
	}

	

}
