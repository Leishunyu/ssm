<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>数据表格</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>可编辑表格</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_data_tables.html#">选项1</a>
                                </li>
                                <li><a href="table_data_tables.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="">
                            <a href="/order/add.htm" class="btn btn-primary ">添加行</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover " id="editable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>姓名</th>
                                    <th>卡号</th>
                                    <th>手机号</th>
                                    <th>金额</th>
                                    <th>订单号</th>
                                    <th>订单时间</th>
                                    <th>订单状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${lists}" var="list">
                            	<tr class="gradeX">
                                    <td>${list.id }</td>
                                    <td>${list.name }</td>
                                    <td>${list.cardNo }+</td>
                                    <td class="center">${list.mobile }</td>
                                    <td class="center"<c:if test="${list.amount==null}">0</c:if><c:if test="${list.amount!=null}">${list.amount }</c:if>></td>
                                    <td class="center">${list.orderNo }</td>
                                    <td class="center">${list.orderTime }</td>
                                    <td><c:if test="${list.status==1}">成功</c:if><c:if test="${list.status==0}">失败</c:if></td>
                                    <td><a href="/order/payOrder.htm?id=${list.id}"><i class="fa fa-check text-navy"></i></a>
                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div> 
    </div>

    <!-- 全局js -->
    <script src="/js/jquery.min.js?v=2.1.4"></script>
    <script src="/js/bootstrap.min.js?v=3.3.6"></script>



    <script src="/js/plugins/jeditable/jquery.jeditable.js"></script>

    <!-- Data Tables -->
    <script src="/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- 自定义js -->
    <script src="/js/content.js?v=1.0.0"></script>


    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
            $('.dataTables-example').dataTable();

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            // oTable.$('td').editable('../example_ajax.php', {
            //     "callback": function (sValue, y) {
            //         var aPos = oTable.fnGetPosition(this);
            //         oTable.fnUpdate(sValue, aPos[0], aPos[1]);
            //     },
            //     "submitdata": function (value, settings) {
            //         return {
            //             "row_id": this.parentNode.getAttribute('id'),
            //             "column": oTable.fnGetPosition(this)[2]
            //         };
            //     },

            //     "width": "90%",
            //     "height": "100%"
            // });


        });

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData([
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row"]);

        }
    </script>

    <!--统计代码，可删除-->

</body>

</html>
