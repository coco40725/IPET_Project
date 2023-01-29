<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>IPET 寵物 | 美容專區 | 班表管理</title>
  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">

  <!-- MultiDatePicker  -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/jquery-ui/jquery-ui.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/jquery-ui-multidatepicker/css/jquery-ui.multidatespicker.css">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/fontawesome-free/css/all.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/datatables-select/css/select.bootstrap4.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/plugins/icheck-bootstrap/icheck-bootstrap.min.css">

  <!-- TODO: 目前先使用 完整css-->
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backstage/css/adminlte.css">



</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!--  Navbar -->
  <%@ include file="/templates/backstage/common/navbar.jsp" %>
  <!-- /.navbar -->

  <!-- 左邊選單區 Main Sidebar Container -->
  <%@ include file="/templates/backstage/common/sidebar.jsp" %>
  <!-- /.aside -->


  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/home">Home</a></li>
              <li class="breadcrumb-item">班表管理</li>
              <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath}/ipet-back/job/jobs_list">班表列表</a></li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <!-- Filter -->
            <div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">班表 Filter</h3>
              </div>
              <div class="card-body">
                <form>
                  <div class="row">
                    <div class="col-sm-3">
                      <!-- text input -->
                      <div class="form-group">
                        <label>班表編號</label>
                        <input type="text" class="form-control column_filter" id="col0-search"  data-column="0" placeholder="Enter ...">
                      </div>
                      <!-- text input -->
                      <div class="form-group">
                        <label>美容師姓名</label>
                        <input type="text" class="form-control column_filter" id="col1-search" data-column="1" placeholder="Enter ...">
                      </div>
                    </div>
                    <div class="col-sm-3">
                      <!-- text input -->
                      <div class="form-group">
                        <label>助理1姓名</label>
                        <input type="text" class="form-control column_filter" id="col2-search" data-column="2" placeholder="Enter ...">
                      </div>
                      <!-- text input -->
                      <div class="form-group">
                        <label>助理2姓名</label>
                        <input type="text" class="form-control column_filter" id="col3-search" data-column="3" placeholder="Enter ...">
                      </div>
                    </div>
                    <div class="col-sm-3">
                      <!-- text input -->
                      <div class="form-group">
                        <label>班表日期</label>
                        <input type="text" class="form-control column_filter" id="col4-search" data-column="4" placeholder="Enter ...">
                      </div>
                      <!-- text input -->
                      <div class="form-group">
                        <label>班表時段</label>
                        <input type="text" class="form-control column_filter" id="col5-search" data-column="5" placeholder="Enter ...">
                      </div>
                    </div>
                    <div class="col-sm-3">
                      <!-- text input -->
                      <div class="form-group">
                        <label>預約單編號</label>
                        <input type="text" class="form-control column_filter" id="col6-search" data-column="6" placeholder="Enter ...">
                      </div>
                    </div>
                  </div>

                </form>
              </div>
            </div>
            <!-- /.filter -->

            <!-- table -->
            <div class="card">
              <div class="card-header">
                <h3 class="card-title"></h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div>
                  <button type="submit" data-toggle="modal" data-target="#AddModal" class="btn btn-outline-secondary row-add"><i class="fas fa-solid fa-plus"></i> 新增 </button>
                   <p></p>
                </div>
                <table id="scheduleTable" class="table table-bordered table-striped  display">
                  <thead>
                  <tr>
                    <th>班表編號</th>
                    <th>美容師姓名</th>
                    <th>助理1姓名</th>
                    <th>助理2姓名</th>
                    <th>班表日期</th>
                    <th>班表時段</th>
                    <th>預約單編號</th>
                    <th>員工備註</th>
                    <th></th>
                    <th></th>
                  </tr>
                  </thead>
                  <tbody>
                    <jsp:useBean id="allJob" scope="request" type="java.util.List"/>
                    <c:forEach var="job" items="${allJob}" >
                      <tr>
                        <td>${job.schID}</td>
                        <td>${job.groomerName}</td>
                        <td>${job.asstID1Name}</td>
                        <td>${job.asstID2Name}</td>
                        <td>${job.schDate}</td>
                        <td>${job.schPeriod}</td>
                        <td>${job.apmId}</td>
                        <td>${job.employeeNote}</td>
                        <td>
                          <button type="submit" class="btn btn-light row-edit dt-center" data-toggle="modal" data-target="#EditModal" data-whatever="@mdo">
                            <i class="fas fa-solid fa-pen"></i>
                          </button>
                        </td>
                        <td>
                          <button  type="submit" class="btn btn-danger row-remove dt-center" data-toggle="modal" data-target="#RemoveModal">
                            <i class="fas fa-solid fa-trash"></i>
                          </button>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.table -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->

    <!-- Remove Modal content   -->
    <div class="modal fade" id="RemoveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">資料刪除</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            確認要刪除此筆資料?
              <p id="deleteModalMessage"></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">離開</button>
            <button type="submit" class="btn btn-danger btn-remove-confirm"  >確認</button>
          </div>
        </div>
      </div>
    </div>
    <!-- /.Remove Modal content   -->

    <!-- Edit Modal content   -->
    <div class="modal fade" id="EditModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">資料編輯</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form>
              <%-- 美容師 --%>
              <div class="form-group">
                <label for="GROOMER_name-modal-edit" class="col-form-label">美容師姓名</label>
                <select id="GROOMER_name-modal-edit" class="form-control">
                </select>
              </div>
              <%-- 助理1 --%>
              <div class="form-group">
                <label for="ASST_name_1-modal-edit" class="col-form-label">助理1姓名</label>
                <select id="ASST_name_1-modal-edit" class="form-control">
                </select>
              </div>
              <%-- 助理2 --%>
              <div class="form-group">
                <label for="ASST_name_2-modal-edit" class="col-form-label">助理2姓名</label>
                <select id="ASST_name_2-modal-edit" class="form-control">
                </select>
              </div>

              <%-- 時段 --%>
              <div>
                <label for="schPeriod-modal-edit" class="col-form-label">班表時段</label>
                <input id="schPeriod-modal-edit" class="form-control" readonly>
              </div>

              <%-- 日期 --%>
              <div class="form-group">
                <label for="schDate-modal-edit" class="col-form-label">班表日期</label>
                <input id="schDate-modal-edit" type="text" class="form-control" readonly>
              </div>
              <%-- 備註 --%>
              <div>
                <label for="schNote-edit-modal" class="col-form-label">員工備註</label>
                <textarea id="schNote-edit-modal" class="form-control"></textarea>
              </div>
              <div>
                <p id="editModalMessage"></p>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">離開</button>
            <button type="submit" class="btn btn-primary btn-edit-confirm">確認修改</button>
          </div>
        </div>
      </div>
    </div>
    <!-- /. Edit Modal content   -->

    <!--  Add Modal content  -->
    <div class="modal fade" id="AddModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">資料新增</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p style="color: darkgreen">請先挑選美容師、助理與班表時段，再點選<b>查詢可用日期</b>，即可挑選日期</p>
            <form>
              <%-- 美容師 --%>
              <div class="form-group">
                <label for="GROOMER_name-modal-add" class="col-form-label">美容師姓名</label>
                <select id="GROOMER_name-modal-add" class="form-control">
                </select>
              </div>
              <%-- 助理1 --%>
              <div class="form-group">
                <label for="ASST_name_1-modal-add" class="col-form-label">助理1姓名</label>
                <select id="ASST_name_1-modal-add" class="form-control">
                </select>
              </div>
              <%-- 助理2 --%>
              <div class="form-group">
                <label for="ASST_name_2-modal-add" class="col-form-label">助理2姓名</label>
                <select id="ASST_name_2-modal-add" class="form-control">
                </select>
              </div>
              <%-- 時段 --%>
              <div class="form-group">
                <label class="col-form-label">班表時段</label>
                <p></p>
                <div class="icheck-primary d-inline jobPeriod-add">
                  <input type="checkbox" value="上午" id="jobPeriod-add1" class="jobPeriod-add">
                  <label for="jobPeriod-add1">上午</label>
                </div>
                <div class="icheck-primary d-inline">
                  <input type="checkbox" value="下午" id="jobPeriod-add2" class="jobPeriod-add">
                  <label for="jobPeriod-add2">下午</label>
                </div>
                <div class="icheck-primary d-inline jobPeriod-add">
                  <input type="checkbox" value="晚上" id="jobPeriod-add3" class="jobPeriod-add">
                  <label for="jobPeriod-add3">晚上</label>
                </div>
              </div>

              <div>
                <button type="button" class="btn btn-outline-dark" id="searchAvailableDatesAdd">查詢可用日期</button>
              </div>

              <%-- 日期 --%>
              <div class="form-group">
                <label for="schDate-modal-add" class="col-form-label">班表日期</label>
                <input type="text" class="form-control" id="schDate-modal-add" disabled>
              </div>

              <%-- 備註 --%>
              <div>
                <label for="schNote-add-modal" class="col-form-label">員工備註</label>
                <textarea id="schNote-add-modal" class="form-control"></textarea>
              </div>
              <div>
                <p id="addModalMessage"></p>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">離開</button>
            <button type="submit" class="btn btn-primary btn-add-confirm">確認新增</button>
          </div>
        </div>
      </div>
    </div>
    <!-- /. Add Modal content   -->


  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <%@ include file="/templates/backstage/common/footer.jsp" %>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/static/backstage/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/static/backstage/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE -->
<script src="${pageContext.request.contextPath}/static/backstage/js/adminlte.js"></script>

<!-- DataTables  & Plugins -->
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/jszip/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/pdfmake/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/pdfmake/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/static/backstage/plugins/datatables-select/js/dataTables.select.js" ></script>

<!-- MultiDatePicker -->
<script src="${pageContext.request.contextPath}/static/backstage/plugins/jquery-ui-multidatepicker/js/jquery-ui.multidatespicker.js"></script>

<!-- sidebar menu Class -->
<script>
  $("p:contains(班表管理)").closest("li").addClass("menu-open");
  $("p:contains(班表列表)").closest("a").addClass("active");
</script>

<!-- Data presentation and operation show -->
<script>
  let table;
  $(document).ready(function() {

    table = $('#scheduleTable').DataTable({
      autoWidth: false,
      responsive: true,
      lengthChange: true,
      info: true,
      altEditor: true,     // Enable altEditor,


      //  填寫直接顯示的欄位，需要與thead tfoot 對應
      "columns":[
        {responsivePriority: 1, className: "schID"},
        {responsivePriority: 2, className: "GROOMER_name"},
        {responsivePriority: 3, className: "ASST_name_1"},
        {responsivePriority: 4, className: "ASST_name_2"},
        {responsivePriority: 5, className: "schDate"},
        {className: "schPeriod"},
        {className: "APM_ID"},
        {className: "employeeNote"},
        {className: 'row-edit dt-center', orderable: false, responsivePriority: 6},
        {className: 'row-remove dt-center', orderable: false, responsivePriority: 7}
      ],
      select: {
        style: 'single',
        toggleable: false
      },
      order: [[0, 'desc']],
      fnDrawCallback: function (){
        <!-- 當有預約單時", 直接移除 刪除按鈕 -->
        $("#scheduleTable tr").each(function (){
          if (this.querySelector(".APM_ID").innerText !== ""){
            console.log(this.querySelector(".row-remove").innerHTML);
            this.querySelector(".row-remove").innerHTML = "";
          }
        });

        <!-- Delete data from modal -->
        let targetDataDelete;
        let schIDDelete;
        let deleteRefresh;
        $('td.row-remove').on('click', function (){
          // get the selected tr
          targetDataDelete = $(this).closest("tr")[0];
          schIDDelete = targetDataDelete.querySelector("td.schID").innerText.trim();
        });

        $('.modal-footer').on('click', '.btn-remove-confirm', function (){
          // send the request to server, and use delete service
          $.ajax({
            url: "${pageContext.request.contextPath}/ipet-back/job/job_delete",
            method: "POST",
            data: {"schID" : schIDDelete},
            success:function(res){
              if (res === "true"){
                $("#deleteModalMessage").html("成功刪除")
                        .css("color","green");
                $(".btn-remove-confirm").addClass("disabled")
                        .attr("disabled");
                deleteRefresh = true;
              }else {
                $("#deleteModalMessage").html("刪除失敗;" +
                        "<br> 錯誤: 此班表含有預約單" +
                        "<br> 或是該資料已不存在，請刷新。")
                        .css("color","red");
                $(".btn-remove-confirm").addClass("disabled")
                        .attr("disabled");
                deleteRefresh = false;
              }
            },
            error:function(){
              $("#deleteModalMessage").text("刪除失敗，其他原因")
                      .css("color","red");
              $(".btn-remove-confirm").addClass("disabled")
                      .attr("disabled");
              deleteRefresh = true;
            }
          })
        });

        $('#RemoveModal').on('hidden.bs.modal', function (){
          // clean the value
          $("#deleteModalMessage").text("");
          $(".btn-remove-confirm").removeAttr("disabled");
          $(".btn-remove-confirm").removeClass("disabled");
          if (deleteRefresh === true) {
            window.location.reload();
          }
        });
        <!-- /.Delete data from modal -->


        <!-- Edit data from modal -->
        let schIDEdit;
        let targetDataEdit;
        let empObjectEdit;
        let editRefresh;

        $('td.row-edit').on('click', function (){
          // get the selected tr
          targetDataEdit = $(event.target).closest("tr")[0];
          schIDEdit = targetDataEdit.querySelector("td.schID").innerText.trim();

          // send the request to server, and response the data on the modal
          $.ajax({
            url:"${pageContext.request.contextPath}/ipet-back/job/editModalInput",
            method: "POST",
            data:{"schID": schIDEdit},
            success : function (resp){
              if (resp !== ""){
                empObjectEdit = JSON.parse(resp);
                console.log(empObjectEdit.job.groomerID);
                console.log(empObjectEdit.job);

                // 載入 groomer 的選單 與 欄位 value
                let groomerHTMLEdit = ``;
                for (let i = 0; i < empObjectEdit.groomerIds.length; i ++) {
                  if (empObjectEdit.job.groomerID === empObjectEdit.groomerIds[i]) {
                    groomerHTMLEdit += `<option value="\${empObjectEdit.groomerIds[i]}" selected> \${empObjectEdit.groomNames[i]}</option>`
                  } else{
                    groomerHTMLEdit += `<option value="\${empObjectEdit.groomerIds[i]}"> \${empObjectEdit.groomNames[i]}</option>`
                  }
                }
                $('#GROOMER_name-modal-edit').html(groomerHTMLEdit);

                // 載入 asst1 的選單 與 欄位 value
                let asst1HTMLEdit = ``;
                for (let i = 0; i < empObjectEdit.asstIds.length; i ++) {
                  if (empObjectEdit.job.asstID1 === empObjectEdit.asstIds[i]){
                    asst1HTMLEdit += `<option value="\${empObjectEdit.asstIds[i]}" selected> \${empObjectEdit.asstNames[i]}</option>`
                  }else{
                    asst1HTMLEdit += `<option value="\${empObjectEdit.asstIds[i]}"> \${empObjectEdit.asstNames[i]}</option>`
                  }
                }
                $('#ASST_name_1-modal-edit').html(asst1HTMLEdit);

                // 載入 asst2 的選單 與 欄位 value
                let asst2HTMLEdit = ``;
                let asstID1 = $('#ASST_name_1-modal-edit').val();
                for (let i = 0; i < empObjectEdit.asstIds.length; i ++) {
                  if (asstID1 !== empObjectEdit.asstIds[i].toString()) {
                    if (empObjectEdit.job.asstID2 === empObjectEdit.asstIds[i]) {
                      asst2HTMLEdit += `<option value="\${empObjectEdit.asstIds[i]}" selected> \${empObjectEdit.asstNames[i]} </option>`
                    } else {
                      asst2HTMLEdit += `<option value="\${empObjectEdit.asstIds[i]}"> \${empObjectEdit.asstNames[i]} </option>`
                    }
                  }
                  $('#ASST_name_2-modal-edit').html(asst2HTMLEdit);
                }

                // 載入 period 並 唯讀
                $('#schPeriod-modal-edit').val(empObjectEdit.job.schPeriod);

                // 載入 日期 並 唯讀
                $('#schDate-modal-edit').val(empObjectEdit.job.schDate);

                // 載入員工備註
                $('#schNote-edit-modal').val(empObjectEdit.job.employeeNote);
              }else{
                $("#editModalMessage").html("錯誤: 無法從伺服器抓取該筆資料")
                        .css("color","red");
                editRefresh = false;
              }

            },
            error: function(resp){}
          })

          // 當挑選出助理1後，顯示助理2的可選名單
          $('#ASST_name_1-modal-edit').on("change", function (){
            if ($('#ASST_name_1-modal-edit').val() === empObjectEdit.job.asstID2.toString()){
              let asst2HTMLEdit = `<option disabled selected>請選擇</option>`;
              $('#ASST_name_2-modal-edit').html(asst2HTMLEdit);
              let asstID1 = $('#ASST_name_1-modal-edit').val();
              for (let i = 0; i < empObjectEdit.asstIds.length; i ++) {
                if (asstID1 !== empObjectEdit.asstIds[i].toString()) {
                  asst2HTMLEdit += `<option value="\${empObjectEdit.asstIds[i]}"> \${empObjectEdit.asstNames[i]} </option>`
                }
              }
              $('#ASST_name_2-modal-edit').html(asst2HTMLEdit);

            }
          });

          $('.btn-edit-confirm').on("click", function(){
            if ($('#ASST_name_2-modal-edit').val() !== null){
              $.ajax({
                url: "${pageContext.request.contextPath}/ipet-back/job/job_edit",
                method: "POST",
                data: {
                  schID: schIDEdit,
                  schDate: $('#schDate-modal-edit').val(),
                  schPeriod: $('#schPeriod-modal-edit').val(),
                  groomerID: $('#GROOMER_name-modal-edit').val(),
                  asstID1: $('#ASST_name_1-modal-edit').val(),
                  asstID2: $('#ASST_name_2-modal-edit').val(),
                  employeeNote: $('#schNote-edit-modal').val()
                },
                success : function (resp){
                  if (resp === "修改成功"){
                    $("#editModalMessage").html("修改成功")
                            .css("color","green");
                    $(".btn-edit-confirm").addClass("disabled")
                            .attr("disabled")
                    editRefresh = true;
                  }else{
                    $("#editModalMessage").html(resp)
                            .css("color","red");
                    editRefresh = false;
                  }
                },
                error : function (){
                  $("#editeModalMessage").text("修改失敗，其他原因")
                          .css("color","red");
                  $(".btn-edit-confirm").addClass("disabled")
                          .attr("disabled");
                  editRefresh = false;

                }
              })
            }else{
              $("#editModalMessage").text("請填寫完整資料")
                      .css("color","red");
            }

          });

          $('#EditModal').on('hidden.bs.modal', function (){
            if (editRefresh) {
              window.location.reload();
            }
          });
        });
        <!-- /. Edit data from modal -->


        <!-- Add data from modal -->
        let empObjectAdd;
        let period = [];
        $('.row-add').on('click', function (){
          // send the request to server, and get the emp
          $.ajax({
            url: "${pageContext.request.contextPath}/ipet-back/job/addModalInput",
            method: "POST",
            data:{},
            success: function (resp){
              if (resp !== "") {
                empObjectAdd = JSON.parse(resp);
                let groomerHTMLAdd = `<option disabled selected>請選擇</option>`;
                for (let i = 0; i < empObjectAdd.groomerIds.length; i ++) {
                  groomerHTMLAdd += `<option value="\${empObjectAdd.groomerIds[i]}"> \${empObjectAdd.groomNames[i]}</option>`
                }
                $('#GROOMER_name-modal-add').html(groomerHTMLAdd);

                let asst1HTMLAdd = `<option disabled selected>請選擇</option>`;
                for (let i = 0; i < empObjectAdd.asstIds.length; i ++) {
                  asst1HTMLAdd += `<option value="\${empObjectAdd.asstIds[i]}"> \${empObjectAdd.asstNames[i]}</option>`
                }
                $('#ASST_name_1-modal-add').html(asst1HTMLAdd);
              }else {
                $("#addModalMessage").html("無法查詢到美容師與助理，請洽網站管理員")
                        .css("color","red")
              }
            }
          })
        });

        // 當挑選出助理1後，顯示助理2的可選名單
        // 或者是 變動助理1
        $('#ASST_name_1-modal-add').on("change", function (){
          let asst2HTMLAdd = `<option disabled selected>請選擇</option>`;
          let asstID1 = $('#ASST_name_1-modal-add').val();
          for (let i = 0; i < empObjectAdd.asstIds.length; i ++) {
            if (asstID1 !== empObjectAdd.asstIds[i].toString()) {
              asst2HTMLAdd += `<option value="\${empObjectAdd.asstIds[i]}"> \${empObjectAdd.asstNames[i]} </option>`
            }
          }
          $('#ASST_name_2-modal-add').html(asst2HTMLAdd);

          $('#schDate-modal-add').prop("disabled", true);
        });


        // 查詢可以新增的班表日期
        $("#searchAvailableDatesAdd").on("click", function (){
          // check which period is selected
          for (let p of $('input.jobPeriod-add:checked')){
            period.push(p.value);
          }

          //所有資訊填寫完畢才可以查詢日期

          if ($('#GROOMER_name-modal-add').val() !== null &&
                  $('#ASST_name_1-modal-add').val() !== null &&
                  $('#ASST_name_2-modal-add').val() !== null &&
                  period.length !== 0){
            // 根據員工 時段 來封鎖不可選擇的日期
            $.ajax({
              url:"${pageContext.request.contextPath}/ipet-back/job/addModalInput",
              method: "POST",
              data:{
                groomerID: $('#GROOMER_name-modal-add').val(),
                asstID1: $('#ASST_name_1-modal-add').val(),
                asstID2: $('#ASST_name_2-modal-add').val(),
                period: period
              },
              success: function(resp){
                console.log("resp: " + resp);
                if (resp !== ""){
                  empObjectAdd = JSON.parse(resp);
                  // 移除舊 日曆
                  $('#schDate-modal-add').multiDatesPicker("destroy");
                  if (empObjectAdd.illegalDate.length !== 0) {
                    // 將不能使用的date鎖起來
                    $('#schDate-modal-add').multiDatesPicker({
                      dateFormat: 'yy-mm-dd',
                      minDate: 0,
                      addDisabledDates: empObjectAdd.illegalDate
                    });
                  }else{
                    // 沒有不能使用的班表日期
                    $('#schDate-modal-add').multiDatesPicker({
                      dateFormat: 'yy-mm-dd',
                      minDate: 0
                    });
                  }

                  // https://github.com/dubrox/Multiple-Dates-Picker-for-jQuery-UI/issues/67
                  <!-- 解決 MultiDatePicker 閃爍問題並處理需點兩次才可關閉的問題 -->
                  $.datepicker._selectDateOverload = $.datepicker._selectDate;
                  $.datepicker._selectDate = function(id, dateStr) {
                    var target = $(id);
                    var inst = this._getInst(target[0]);
                    inst.inline = true;
                    $.datepicker._selectDateOverload(id, dateStr);
                    inst.inline = false;
                    target[0].multiDatesPicker.changed = false;
                    this._updateDatepicker(inst);
                  };
                  <!-- /.解決 MultiDatePicker 閃爍問題並處理需點兩次才可關閉的問題 -->
                  $('#schDate-modal-add').removeAttr("disabled");
                }else{
                  $("#addModalMessage").html("錯誤:伺服器無回傳結果。")
                          .css("color","red");
                }
              }
            });
          }else{
            $("#addModalMessage").html("請填寫完美容師、助理與時段，再查詢日期。")
                    .css("color","red");
          }
        })

        // 美容師與助理的欄位若是變動 需要再按一次 查詢日期
        $('#ASST_name_2-modal-add').on("change", function (){
          $('#schDate-modal-add').prop("disabled", true);
        })
        $('#GROOMER_name-modal-add').on("change", function (){
          $('#schDate-modal-add').prop("disabled", true);
        })

        // 時段欄位若是變動 需要再按一次查詢日期
        $(".jobPeriod-add").on("change", function (){
          $('#schDate-modal-add').prop("disabled", true);
        })




        $('.modal-footer').on('click', '.btn-add-confirm', function (){
          if ($('#GROOMER_name-modal-add').val() != null &&
                  $('#ASST_name_1-modal-add').val() != null &&
                  $('#ASST_name_2-modal-add').val() != null &&
                  period.length !== 0 &&
                  $('#schDate-modal-add').val().trim() !== ""
          ) {
            $.ajax({
              url: "${pageContext.request.contextPath}/ipet-back/job/job_add",
              method:"POST",
              data:{
                groomerID: $('#GROOMER_name-modal-add').val(),
                asstID1: $('#ASST_name_1-modal-add').val(),
                asstID2: $('#ASST_name_2-modal-add').val(),
                period: period,
                dates: $('#schDate-modal-add').val().split(", "),
                notes: $('#schNote-add-modal').val()
              },
              success: function(resp){
                if (resp === "新增成功"){
                  $("#addModalMessage").html("新增成功")
                          .css("color","green");
                  $(".btn-add-confirm").addClass("disabled")
                          .attr("disabled")
                }else{
                  $("#addModalMessage").html(resp)
                          .css("color","red");
                  $(".btn-add-confirm").addClass("disabled")
                          .attr("disabled")
                }
              },
              error: function(){
                $("#addModalMessage").text("新增失敗，其他原因")
                        .css("color","red");
              }
            })
          } else {
            $("#addModalMessage").html("請填寫完整資料")
                    .css("color","red");
          }
        })

        $('#AddModal').on("hidden.bs.modal", function (){
          // clean the previous data
          $('#GROOMER_name-modal-add').val("");
          $('#ASST_name_1-modal-add').val("");
          $('#ASST_name_2-modal-add').val("");
          $('#schDate-modal-add').val("");
          $('input.jobPeriod-add').prop("checked", false);
          window.location.reload();
        });
        <!-- /.Add data from modal -->
      }
    });


    $('input.column_filter').on('keyup click', function () {
      filterColumn($(this).attr('data-column'));
    });
  });
  function filterColumn(i) {
    table.column(i)
            .search($('#col' + i + '-search').val())
            .draw();
  }

</script>
</body>
</html>
