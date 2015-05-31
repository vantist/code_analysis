<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- list owner via accounts object -->
<!-- add equipment modal -->
<div class="modal" id="${param.modal_id}" tabindex="-1" role="dialog" aria-labelledby="${param.modal_id}Label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增貴重儀器設備</h4>
      </div>
      <div class="modal-body">
        <form action="/manage/equipment/new" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
          <div class="form-group">
            <label for="${param.modal_id}Name">貴重儀器設備名稱</label>
            <input type="text" name="name" class="form-control" id="${param.modal_id}Name" placeholder="設備名稱">
          </div>
          <div class="form-group">
            <label for="${param.modal_id}Owner">所屬學校</label>
            <select name="owner" class="form-control" id="${param.modal_id}Owner">
              <c:forEach items="${accounts}" var="account">
                <option value="<c:out value="${account.username}"/>" <c:if test="${account.name.equals(user.getName())}">selected</c:if> ><c:out value="${account.name}"/></option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="${param.modal_id}ContactPerson">聯絡人</label>
            <input type="text" name="contact_person" class="form-control" id="${param.modal_id}ContactPerson" placeholder="聯絡人">
          </div>
          <div class="form-group">
            <label for="${param.modal_id}ContactPerson">聯絡人信箱</label>
            <input type="text" name="contact_email" class="form-control" id="${param.modal_id}ContactEmail" placeholder="聯絡人信箱">
          </div>
          <div class="form-group">
            <label for="${param.modal_id}Type">型號</label>
            <textarea class="form-control" name="type" id="${param.modal_id}Type" rows="3" placeholder="型號"></textarea>
          </div>
          <div class="form-group">
            <label for="${param.modal_id}Description">功能簡述</label>
            <textarea class="form-control" name="description" id="${param.modal_id}Description" rows="3" placeholder="功能簡述"></textarea>
          </div>
          <div class="form-group">
            <label for="${param.modal_id}ContactMethod">聯絡方式</label>
            <textarea class="form-control" name="contact_method" id="${param.modal_id}ContactMethod" rows="3" placeholder="聯絡方式"></textarea>
          </div>
          <div class="form-group">
            <label for="${param.modal_id}Picture">設備照片</label>
            <input type="file" name="file" id="${param.modal_id}Picture" accept="image/*">
            <p class="help-block">請上傳*.jpg/*.png檔案</p>
          </div>
          <button type="submit" class="btn btn-default">新增</button>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
  $(document).ready(function() {
    $('#${param.modal_id} textarea').jqte();
  });
</script>