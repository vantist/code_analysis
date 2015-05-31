<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
  <input class="btn btn-default" type="button" data-toggle="modal" data-target="#addModal" value="新增">
  
  <!-- include equipment table -->
  <jsp:include page="./table.jsp">
    <jsp:param name="modal_id" value="modifyModal" />
  </jsp:include>

  <!-- include add equipment modal -->
  <jsp:include page="./add_modal.jsp" >
    <jsp:param name="modal_id" value="addModal" />
  </jsp:include>
</div>