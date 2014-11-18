package edu.njust.tms.action;

import com.opensymphony.xwork2.ActionContext;
import edu.njust.tms.dao.AdminDAO;
import edu.njust.tms.dao.Admin;

public class ChangePasswordAction {
	
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	
	
	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getNewPassword2() {
		return newPassword2;
	}


	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}


	/*修改密码*/
	public String execute() {
		ActionContext ctx = ActionContext.getContext(); 
		if(oldPassword.equals("")) {
			ctx.put("error",  java.net.URLEncoder.encode("旧密码必须输入!"));
			return "error";
		}
		if(newPassword.equals("")) {
			ctx.put("error",  java.net.URLEncoder.encode("新密码必须输入!"));
			return "error";
		}
		if(!newPassword2.equals(newPassword)) {
			ctx.put("error",  java.net.URLEncoder.encode("两次密码输入不一致!"));
			return "error";
		}
		String username = (String)ctx.getSession().get("username"); 
		Admin admin = AdminDAO.GetAdmin(username);
		if(!admin.getPassword().equals(oldPassword)) {
			ctx.put("error",  java.net.URLEncoder.encode("旧密码不正确!"));
			return "error";
		}
		
		try { 
			AdminDAO.ChangePassword(username,newPassword);
			ctx.put("message",  java.net.URLEncoder.encode("密码修改成功!"));
			return "change_success";
		} catch (Exception e) {
			e.printStackTrace();
			ctx.put("error",  java.net.URLEncoder.encode("密码更新失败!"));
			return "error";
		}  
		
	}

}
