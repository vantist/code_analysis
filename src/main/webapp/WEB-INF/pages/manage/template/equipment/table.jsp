<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- list via object equipment -->
<table class="table table-hover">
  <thead>
    <tr>
      <th>貴重儀器設備名稱</th>
      <th>所屬學校</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${list}" var="equipment">
    <tr>
      <td><c:out value="${equipment.name}"/></td>
      <td><c:out value="${equipment.ownerData.name}"/></td>
      <td>
        <a href="#" class="btn btn-warning" data-id="<c:out value="${equipment.id}"/>" data-toggle="modal" data-target="#${param.modal_id}">修改</a>
        <a href="#" class="btn btn-danger" onClick="checkDelete('/manage/equipment/delete/<c:out value="${equipment.id}"/>')">刪除</a>
      </td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<!-- include modify equipment modal -->
<jsp:include page="./modify_modal.jsp" >
  <jsp:param name="modal_id" value="${param.modal_id}" />
</jsp:include>

<script>
	function checkDelete(url){
		if(confirm("確定要刪除嗎?")){
			location.href = url;
		}
	}
</script>