<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><HEAD><TITLE>添加教师信息</TITLE> 
<STYLE type=text/css>
BODY {
    	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
<script language="javascript">
/*验证表单*/
function checkForm() {
    var teacherNumber = document.getElementById("teacher_QQ287307421.teacherNumber").value;
    if(teacherNumber=="") {
        alert('请输入教师编号!');
        return false;
    }
    var teacherName = document.getElementById("teacher_QQ287307421.teacherName").value;
    if(teacherName=="") {
        alert('请输入教师姓名!');
        return false;
    }
    var teacherSex = document.getElementById("teacher_QQ287307421.teacherSex").value;
    if(teacherSex=="") {
        alert('请输入性别!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>

<BODY background="<%=basePath %>images/adminBg.jpg">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
    <s:form action="Teacher_QQ287307421/Teacher_QQ287307421_AddTeacher_QQ287307421.action" method="post" id="teacher_QQ287307421AddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>教师编号:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherNumber" name="teacher_QQ287307421.teacherNumber" type="text" /></td>
  </tr>

  <tr>
    <td width=30%>教师姓名:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherName" name="teacher_QQ287307421.teacherName" type="text" size="12" /></td>
  </tr>

  <tr>
    <td width=30%>登录密码:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherPassword" name="teacher_QQ287307421.teacherPassword" type="text" size="30" /></td>
  </tr>

  <tr>
    <td width=30%>性别:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherSex" name="teacher_QQ287307421.teacherSex" type="text" size="2" /></td>
  </tr>

  <tr>
    <td width=30%>出生日期:</td>
    <td width=70%><input type="text" readonly id="teacher_QQ287307421.teacherBirthday"  name="teacher_QQ287307421.teacherBirthday" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>入职日期:</td>
    <td width=70%><input type="text" readonly id="teacher_QQ287307421.teacherArriveDate"  name="teacher_QQ287307421.teacherArriveDate" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>身份证号:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherCardNumber" name="teacher_QQ287307421.teacherCardNumber" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>联系电话:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherPhone" name="teacher_QQ287307421.teacherPhone" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>教师照片:</td>
    <td width=70%><input id="teacherPhotoFile" name="teacherPhotoFile" type="file" size="50" /></td>
  </tr>

  <tr>
    <td width=30%>家庭地址:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherAddress" name="teacher_QQ287307421.teacherAddress" type="text" size="100" /></td>
  </tr>

  <tr>
    <td width=30%>附加信息:</td>
    <td width=70%><input id="teacher_QQ287307421.teacherMemo" name="teacher_QQ287307421.teacherMemo" type="text" size="100" /></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='保存' >
        &nbsp;&nbsp;
        <input type="reset" value='重写' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
