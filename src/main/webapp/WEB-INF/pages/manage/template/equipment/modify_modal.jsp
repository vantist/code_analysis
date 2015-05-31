<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- list owner via accounts object -->
<!-- modify equipment modal -->
<div class="modal" id="${param.modal_id}" tabindex="-1" role="dialog" aria-labelledby="${param.modal_id}Label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改貴重儀器設備</h4>
      </div>
      <div class="modal-body">
        <form action="./manage/equipment/update" method="post" enctype="multipart/form-data">
          <input type="hidden" name="id">
          <div class="form-group">
            <label for="${param.modal_id}Name">貴重儀器設備名稱</label>
            <input type="text" name="name" class="form-control" id="${param.modal_id}Name" placeholder="設備名稱">
          </div>
          <div class="form-group">
            <label for="${param.modal_id}Owner">所屬學校</label>
            <select name="owner" class="form-control" id="${param.modal_id}Owner">
              <c:forEach items="${accounts}" var="account">
                <option value="<c:out value="${account.username}"/>"><c:out value="${account.name}"/></option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="${param.modal_id}ContactPerson">聯絡人</label>
            <input type="text" name="contact_person" class="form-control" id="${param.modal_id}ContactPerson" placeholder="聯絡人">
          </div>
          <div class="form-group">
            <label for="${param.modal_id}ContactEmail">聯絡人信箱</label>
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
          <img src="" alt="" class="img-thumbnail" style="height: 150px;">
          <div class="form-group">
            <label for="${param.modal_id}Picture">設備照片</label>
            <input type="file" name="file" id="${param.modal_id}Picture" accept="image/*">
            <p class="help-block">選取檔案更新圖片</p>
          </div>
          <button type="submit" class="btn btn-default">更新</button>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
  $(document).ready(function() {
    $('#${param.modal_id} textarea').jqte();
    
    $('body').on('click', 'a[data-target="#${param.modal_id}"]', function() {
      var id = $(this).data('id');
      var $modal = $('#${param.modal_id}');
      $modal.find('form').attr('action', '/manage/equipment/update/'+id);

      $.getJSON('/manage/equipment/'+id, function(data) {
        $modal.find('input[name="id"]').val(data.id);
        $modal.find('input[name="name"]').val(data.name);
        $modal.find('select[name="owner"]').val(data.owner);
        $modal.find('input[name="contact_person"]').val(data.contact_person);
        $modal.find('input[name="contact_email"]').val(data.contact_email);
        $modal.find('textarea[name="type"]').jqteVal(data.type);
        $modal.find('textarea[name="description"]').jqteVal(data.description);
        $modal.find('textarea[name="contact_method"]').jqteVal(data.contact_method);
        $modal.find('img').attr('src', '/' + data.picture_path);
        
        
      });
    });
  });
</script>