<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="edu.njust.tms.dao.Student" %>
<%@ page import="edu.njust.tms.dao.ClassInfo" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的studentClassNumber信息
    List<ClassInfo> classInfoList = (List<ClassInfo>)request.getAttribute("classInfoList");
    Student student = (Student)request.getAttribute("student");
%>
<HTML><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><HEAD><TITLE>查看学生信息</TITLE>
<STYLE type=text/css>
body{margin:0px; font-size:12px; background-image:url(<%=basePath%>images/bg.jpg); background-position:bottom; background-repeat:repeat-x; background-color:#A2D5F0;}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
</HEAD>
<BODY><br/><br/>
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><s:form action="" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3'  class="tablewidth">
  <tr>
    <td width=30%>学号:</td>
    <td width=70%><%=student.getStudentNumber() %></td>
  </tr>

  <tr>
    <td width=30%>姓名:</td>
    <td width=70%><%=student.getStudentName() %></td>
  </tr>

  <tr>
    <td width=30%>登录密码:</td>
    <td width=70%><%=student.getStudentPassword() %></td>
  </tr>

  <tr>
    <td width=30%>性别:</td>
    <td width=70%><%=student.getStudentSex() %></td>
  </tr>

  <tr>
    <td width=30%>所在班级:</td>
    <td width=70%>
      <select name="student.studentClassNumber.classNumber" disabled>
      <%
        for(ClassInfo classInfo:classInfoList) {
          String selected = "";
          if(classInfo.getClassNumber().equals(student.getStudentClassNumber().getClassNumber()))
            selected = "selected";
      %>
          <option value='<%=classInfo.getClassNumber() %>' <%=selected %>><%=classInfo.getClassName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>出生日期:</td>
    <td width=70%><%=student.getStudentBirthday() %></td>
  </tr>

  <tr>
    <td width=30%>政治面貌:</td>
    <td width=70%><%=student.getStudentState() %></td>
  </tr>

  <tr>
    <td width=30%>学生照片:</td>
    <td width=70%><img src="<%=basePath %><%=student.getStudentPhoto() %>" width="200px" border="0px"/></td>
  </tr>
  <tr>
    <td width=30%>联系电话:</td>
    <td width=70%><%=student.getStudentTelephone() %></td>
  </tr>

  <tr>
    <td width=30%>学生邮箱:</td>
    <td width=70%><%=student.getStudentEmail() %></td>
  </tr>

  <tr>
    <td width=30%>联系qq:</td>
    <td width=70%><%=student.getStudentQQ() %></td>
  </tr>

  <tr>
    <td width=30%>家庭地址:</td>
    <td width=70%><%=student.getStudentAddress() %></td>
  </tr>

  <tr>
    <td width=30%>附加信息:</td>
    <td width=70%><%=student.getStudentMemo() %></td>
  </tr>

  <tr>
      <td colspan="4" align="center">
        <input type="button" value="返回" onclick="history.back();"/>
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
