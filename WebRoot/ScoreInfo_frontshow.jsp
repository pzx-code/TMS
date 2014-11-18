<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="edu.njust.tms.dao.ScoreInfo" %>
<%@ page import="edu.njust.tms.dao.Student" %>
<%@ page import="edu.njust.tms.dao.CourseInfo" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的studentNumber信息
    List<Student> studentList = (List<Student>)request.getAttribute("studentList");
    //获取所有的courseNumber信息
    List<CourseInfo> courseInfoList = (List<CourseInfo>)request.getAttribute("courseInfoList");
    ScoreInfo scoreInfo = (ScoreInfo)request.getAttribute("scoreInfo");

%>
<HTML><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /><HEAD><TITLE>查看成绩信息</TITLE>
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
    <td width=30%>记录编号:</td>
    <td width=70%><%=scoreInfo.getScoreId() %></td>
  </tr>

  <tr>
    <td width=30%>学生对象:</td>
    <td width=70%>
      <select name="scoreInfo.studentNumber.studentNumber" disabled>
      <%
        for(Student student:studentList) {
          String selected = "";
          if(student.getStudentNumber().equals(scoreInfo.getStudentNumber().getStudentNumber()))
            selected = "selected";
      %>
          <option value='<%=student.getStudentNumber() %>' <%=selected %>><%=student.getStudentName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>课程对象:</td>
    <td width=70%>
      <select name="scoreInfo.courseNumber.courseNumber" disabled>
      <%
        for(CourseInfo courseInfo:courseInfoList) {
          String selected = "";
          if(courseInfo.getCourseNumber().equals(scoreInfo.getCourseNumber().getCourseNumber()))
            selected = "selected";
      %>
          <option value='<%=courseInfo.getCourseNumber() %>' <%=selected %>><%=courseInfo.getCourseName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>成绩得分:</td>
    <td width=70%><%=scoreInfo.getScoreValue() %></td>
  </tr>

  <tr>
    <td width=30%>学生评价:</td>
    <td width=70%><%=scoreInfo.getStudentEvaluate() %></td>
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
