<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var typeName=document.getElementById("typeName").value;
		if(typeName==null||typeName==""){
			document.getElementById("error").innerHTML="类别名称不能为空！";
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
		<div class="data_list_title">
			<c:choose>
				<c:when test="${diaryType.diaryTypeId!=null }">
					<img src="${pageContext.request.contextPath}/images/diary_type_edit_icon.png"/>
					修改日记类别
				</c:when>
				<c:otherwise>
					<img src="${pageContext.request.contextPath}/images/diaryType_add_icon.png"/>
					添加日记类别
				</c:otherwise>
			</c:choose>
		</div>
		<form action="diaryType?action=save" method="post" onsubmit="return checkForm()">
			<div class="diaryType_form" >
				<!-- 隐藏带进去的参数 -->
				<input type="hidden" id="diaryTypeId" name="diaryTypeId" value="${diaryType.diaryTypeId }"/>
					<table align="center">
						<tr>
							<td>类别名称：</td>
							<td><input type="text" id="typeName"  name="typeName" value="${diaryType.typeName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><input type="submit" class="btn btn-primary" value="保存"/></td>
							<td><button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>&nbsp;&nbsp;<font id="error" color="red">${error }</font>  </td>
						</tr>
					</table>
			</div>
		</form>
</div>
