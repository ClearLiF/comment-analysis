$(function () {
    loadTable()
    $('#btn_analysis').click(function () {
        this.blur()
        clearPie()
        analysis()
    })
    $("#analysis_text").focusin(function () {
        $('#analysis_result_div').collapse('hide')
    })
})


function loadTable() {
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#comment_table'
            , title: '评论内容'
            , height: 'full-250'
            , url: '/comment/get' //数据接口
            , request: {
                pageName: 'currentPage'
            }
            , response: {
                statusCode: 1000 //规定成功的状态码，默认：0
            }
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.sum, //解析数据长度
                    "data": res.data.rows //解析数据列表
                };
            }
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 60, sort: true, fixed: 'left'}
                , {field: 'content', title: '评论内容', width: 1080}
                , {field: 'type', title: '类型(3/好；2/中；1/差)', width: 200, sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#bar_right', width: 120}
            ]]
            , page: true
            , toolbar: '#bar_left'
        });
        //监听工具条
        table.on('tool(comment_table)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (layEvent === 'del') { //删除
                let msg = '将要删除行<br>id：' + data.id + '<br>内容：' + data.content;
                layer.confirm(msg, {title: '真的要删除么？'}, function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    $.get('/comment/del/' + data.id)
                });
            } else if (layEvent === 'edit') { //编辑
                layer.prompt({
                        formType: 2,
                        value: data.content,
                        title: '评论内容'
                    },
                    function (value, index) {
                        obj.update({
                            content: value
                        });
                        layer.close(index);
                        $.post('/comment/update', {
                            id: data.id,
                            content: value
                        })
                    });
            }
        });
        //头工具栏事件
        table.on('toolbar(comment_table)', function (obj) {
            if (obj.event == 'add_comment') {
                let content
                layer.prompt({
                        formType: 2,
                        title: '请输入评论内容'
                    },
                    function (value, index) {
                        content = value
                        layer.close(index);
                        layer.prompt({
                                value: 3,
                                title: '请输入评论类型'
                            },
                            function (value, index) {
                                layer.close(index);
                                $.post('/comment/insert', {
                                        content: content,
                                        type: value
                                    },
                                    function () {
                                        layer.msg('添加成功！', {icon: 6})
                                        $(".layui-laypage-btn").click();
                                    })
                            });
                    });
            }
        });
    });
}

function analysis() {
    $('#btn_analysis').addClass('disabled')
    $('#btn_analysis').text("分析中...")
    $('#analysis_result').text("分析中，请等待...")
    $('#analysis_result_div').collapse('show')
    $.ajax({
        url: "/analysis/analysis",
        type: 'GET',
        data: {
            text: $("#analysis_text").val()
        },
        success: function (result) {
            $('#analysis_result').text(result.data.result)
            let v = result.data.rate
            let n = result.data.percent
            drawPie(v, n)
        },
        complete: function () {
            $('#btn_analysis').text("分析")
            $('#btn_analysis').removeClass('disabled')
        }
    })
}

function clearPie() {
    var myChart = echarts.init(document.getElementById('pieChart'));
    myChart.clear()
}

function drawPie(v, n) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('pieChart'));
    myChart.setOption({
        textStyle: {fontSize: 14},
        series: [
            {
                color: ['#FF9900', '#CCCC66', '#66CCCC'],
                name: '分析结果',
                type: 'pie',    // 设置图表类型为饼图
                radius: '65%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 75% 长度。
                data: [          // 数据数组，name 为数据项名称，value 为数据项值
                    {value: v[0], name: '差评:' + n[0]},
                    {value: v[1], name: '中评:' + n[1]},
                    {value: v[2], name: '好评:' + n[2]}
                ]
            }
        ]
    })
}