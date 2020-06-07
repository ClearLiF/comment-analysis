$(function () {
    $('#btn_analysis').click(function () {
        $('#btn_analysis').attr("disabled","disabled")
        $('#btn_analysis').addClass('disabled')
        $('#btn_analysis').text("分析中...")
        this.blur()
        clearPie()
        analysis()
    })
    $("#analysis_text").focusin(function () {
        $('#analysis_result_div').collapse('hide')
    })
})

function analysis() {
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
            $('#btn_analysis').removeAttr('disabled')
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