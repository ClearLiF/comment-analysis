$(function () {
    layui.use('element')
    loadTable()
    $('#crawl-form').submit(function () {
            startCrawler()
            return false;
        }
    )
    $('#btn_analysis').click(function () {
        this.blur()
        clearPie()
        analysis()
    })
    $("#analysis_text").focusin(function () {
        $('#analysis_result_div').collapse('hide')
    })
    getCrawlerStatus()
})

function startCrawler() {
    $('#btn_crawl').attr('disabled')
    $('#btn_crawl').addClass('layui-btn-disabled')
    $('#btn_crawl').text("爬取任务正在进行中...")
    let num = $('#crawl_num').val();
    let k = $('#search_key').val();
    if (num > 0) {
        $('#craw-status-div').collapse('show')
        $.get('crawler/get?num=' + num + '&keyword=' + k,
            function () {
                crawlerStatusListening()
            })
    } else {
        layer.msg("评论爬取数量非法！！！", {icon: 5})
    }


}

function crawlerStatusListening() {
    let timer = setInterval(function () {
        $.get('crawler/status', function (res) {
            let data = res.data;
            $("#status").text(data.status)
            $("#currType").text(data.currType)
            let pcurr = data.currCrawlNum / data.crawlNum;
            let pall = data.typeProgress / 3 + pcurr / 3;
            layui.element.progress('progress-curr', toPercent(pcurr));
            layui.element.progress('progress-all', toPercent(pall));
            if (data.running == false) {
                clearInterval(timer);
                $('#craw-status-div').collapse('hide')
                layer.msg("爬取任务已完成！", {icon: 1})
                $('#btn_crawl').removeAttr('disabled')
                $('#btn_crawl').removeClass('layui-btn-disabled')
                $('#btn_crawl').text("开始爬取")
            }
        })
    }, 300);
}


function getCrawlerStatus() {
    $.get('crawler/status', function (res) {
        if (res.data.running == true) {
            $('#btn_crawl').text("爬取任务正在进行中...")
            $('#craw-status-div').collapse('show')
            crawlerStatusListening()
        } else {
            $('#btn_crawl').removeAttr('disabled')
            $('#btn_crawl').removeClass('layui-btn-disabled')
            $('#btn_crawl').text("开始爬取")
        }
    })
}

function toPercent(point) {
    if (point <= 0) {
        return '0%';
    }
    if (point > 1) {
        return '100%'
    }
    var str = Number(point * 100).toFixed();
    str += "%";
    return str;
}


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
                , {field: 'content', title: '评论内容', width: 1160}
                , {
                    field: 'type', title: '评论类型', width: 120, sort: true,
                    templet: function (res) {
                        let str
                        switch (res.type) {
                            case 3:
                                str = '好评';
                                break;
                            case 2:
                                str = '中评';
                                break;
                            case 1:
                                str = '差评';
                                break;
                            default:
                                str = res.type;
                        }
                        return str;
                    }
                }
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