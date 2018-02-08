var addTabs = function (options) {
    //var rand = Math.random().toString();
    //var id = rand.substring(rand.indexOf('.') + 1);
    //var url = window.location.protocol + '//' + window.location.host;
    //options.url = url + options.url;


    //id = "tab_" + options.id;
    var id = "tab_" + options.id;
    $(".active").removeClass("active");
    //如果TAB不存在，创建一个新的TAB
    if (!$("#" + id)[0]) {
        //固定TAB中IFRAME高度
        //mainHeight = $(document.body).height();
        mainHeight = document.documentElement.clientHeight-135;//Ace 右侧高度默认
        //创建新TAB的title
        title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + options.title;
        //是否允许关闭
        if (options.close) {
            title += ' <i class="icon-remove" tabclose="' + id + '"></i>';
        }
        title += '</a></li>';
        //是否指定TAB内容
        if (options.content) {
            content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + options.content + '</div>';
        } else {//没有内容，使用IFRAME打开链接
            content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="' + options.url + '" width="100%" height="' + mainHeight +
                    '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
        }
        //加入TABS
        $(".nav-tabs").append(title);
        $(".tab-content").append(content);
    }
    //激活TAB
    $("#tab_" + id).addClass('active');
    $("#" + id).addClass("active");

    setActiveMenuColor(id);

    //页面如果不是在列表页面 返回列表页面 
    var activeWinContent = $("#"+id+" > iframe")[0].contentWindow;
    // pc V1.0
    if(options.title=='应收款'){
        $("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=应收款统计.cpt&projectid='+projectid);
    }else if(options.title=='应付款'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=应付款统计.cpt&projectid='+projectid);
    }else if(options.title=='采购对账单'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=采购对账单汇总.cpt&projectid='+projectid);
    }else if(options.title=='租赁对账单'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=租赁对账单汇总.cpt&projectid='+projectid);
    }else if(options.title=='分包对账单'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=分包对账单汇总.cpt&projectid='+projectid);
    }
    // pc V1.2
    //else if(options.title=='支出明细(表)'){
    //	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=支出流水报表.cpt&projectid='+projectid);
    //}else if(options.title=='入款明细(表)'){
    //	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=收入流水报表.cpt&projectid='+projectid);
    //}
    else if(options.title=='费用流水统计(表)'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=支出流水报表.cpt&projectid='+projectid);
    }
    //V2.3
    else if(options.title=='采购报表(按物资)'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=采购报表(按物资).cpt&projectid='+projectid+'&starttime=&endtime=');
    }else if(options.title=='采购报表(按供应商)'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=采购报表(按供应商).cpt&projectid='+projectid+'&starttime=&endtime=');
    }
    //V2.3.1
    else if(options.title=='分包合同管理台帐'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=分包合同管理台帐.cpt&projectid='+projectid);
    }else if(options.title=='总成本统计'){
    	$("#"+id+" > iframe")[0].src = cjkEncode(reporturl+'WebReport/ReportServer?reportlet=总成本统计.cpt&projectid='+projectid);
    }
    else if(activeWinContent.return2list){
        activeWinContent.return2list();
    }

    var last = $("#tabs>ul>li:last");
    /*$(".contextMenuPlugin").mouseout(function(){
     $(".contextMenuPlugin").remove();
     })
     $(".contextMenuPlugin").mouseup(function(){
     alert("aaa");
     })*/

    //Tab右键快捷菜单,注释掉
    // last.contextPopup({
    //     title: '菜单',
    //     items: [
    //         {
    //             label:'刷新缓存',icon:'plug-in/diy/icons/shopping-basket.png',action:function(){
    //             //last就是当前选中的元素
    //             var tab = last.children("a").attr("aria-controls").toString();
    //             //$("#tabs").find("li[aria-controls='"+tab+"']").remove();
    //             var div = $("#tabs").find("div[id='"+tab+"']");
    //             div.find("iframe").attr("src",options.url);
    //             //tabs.tabs("refresh");
    //         }
    //         },
    //         {
    //             label:'关闭',icon:'plug-in/diy/icons/shopping-basket.png',action:function(){
    //             //last就是当前选中的元素
    //             var closeText = last.children("a").text().trim();
    //             var nowText = $("#tabs").find("li[class='active']").children("a").text().trim();
    //             if(closeText==nowText){
    //                 //关闭的是当前页的时候，显示前一页，如果没有前一页了，就提示
    //                 var prevCount = last.prevAll().size();
    //                 if(prevCount==0){
    //                     var tab = last.children("a").attr("aria-controls").toString();
    //                     last.remove();
    //                     $("#tabs").find("div[id='"+tab+"']").remove();
    //                 }else{
    //                     //显示前一个tab
    //                     var tab = last.children("a").attr("aria-controls").toString();
    //                     var prev = last.prevAll().first();
    //                     last.remove();
    //                     $("#tabs").find("div[id='"+tab+"']").remove();
    //                     prev.addClass("active");
    //                     var id = prev.children("a").attr("aria-controls").toString();
    //                     $("#tabs").find("div[id='"+id+"']").addClass("active");
    //                 }
    //             }else{
    //                 //关闭的不是当前页，关闭就好了╮(╯_╰)╭
    //                 var tab = last.children("a").attr("aria-controls").toString();
    //                 last.remove();
    //                 $("#tabs").find("div[id='"+tab+"']").remove();
    //             }
    //         }
    //         },
    //         {
    //             label:'全部关闭',icon:'plug-in/diy/icons/shopping-basket.png',action:function(){
    //             $("#tabs>ul>li").remove();
    //             $("#tabs>div>div").remove();
    //             //tabs.tabs("refresh");
    //         }
    //         },
    //         {
    //             label:'除此之外全部关闭',icon:'plug-in/diy/icons/shopping-basket.png',action:function(){
    //             var closeText = last.children("a").text().trim();
    //             var nowText = $("#tabs").find("li[class='active']").children("a").text().trim();
    //             //此是当前页则关闭，如果不是当前页面，要激活选择页面
    //             if(closeText==nowText){
    //                 //此是当前页面
    //                 var tab = last.children("a").attr("aria-controls").toString();
    //                 $("#tabs>ul>li").not(last).remove();
    //                 $("#tabs>div>div").not($("#tabs").find("div[id='"+tab+"']")).remove();
    //             }else{
    //                 var tab = last.children("a").attr("aria-controls").toString();
    //                 $("#tabs>ul>li").not(last).remove();
    //                 $("#tabs>div>div").not($("#tabs").find("div[id='"+tab+"']")).remove();
    //                 last.addClass("active");
    //                 var id = last.children("a").attr("aria-controls").toString();
    //                 $("#tabs").find("div[id='"+id+"']").addClass("active");
    //             }
    //             //tabs.tabs("refresh");
    //         }
    //         },
    //         null,
    //         {
    //             label:'当前页右侧全部关闭',icon:'plug-in/diy/icons/shopping-basket.png',action:function(){
    //             var closeText = last.children("a").text().trim();
    //             var nowText = $("#tabs").find("li[class='active']").children("a").text().trim();
    //             if(closeText==nowText){
    //                 //当前页面
    //                 var nextAll = last.nextAll();
    //                 if(nextAll.length!=0){
    //                     nextAll.remove();
    //                     var tab = last.children("a").attr("aria-controls").toString();
    //                     //$("#tabs>ul>li").not(shouye).remove();
    //                     $("#tabs>div").find("div[id='"+tab+"']").nextAll().remove();
    //                     //tabs.tabs("refresh");
    //                 }else{
    //                     layer.msg('<b>右侧没有啦</b>');
    //                 }
    //             }else{
    //                 //不是当前页，当前页的active去掉
    //                 var now = $("#tabs").find("li[class='active']");
    //                 var nowid = now.children("a").attr("aria-controls").toString();
    //                 now.removeClass("active");
    //                 $("#tabs").find("div[id='"+nowid+"']").removeClass("active");
    //                 var nextAll = last.nextAll();
    //                 if(nextAll.length!=0){
    //                     nextAll.remove();
    //                     var tab = last.children("a").attr("aria-controls").toString();
    //                     //$("#tabs>ul>li").not(shouye).remove();
    //                     $("#tabs>div").find("div[id='"+tab+"']").nextAll().remove();
    //                     last.addClass("active");
    //                     var id = last.children("a").attr("aria-controls").toString();
    //                     $("#tabs").find("div[id='"+id+"']").addClass("active");
    //                     //tabs.tabs("refresh");
    //                 }else{
    //                     layer.msg('<b>右侧没有啦</b>');
    //                 }
    //             }
    //         }
    //         },
    //         {
    //             label:'当前页左侧全部关闭',icon:'plug-in/diy/icons/shopping-basket.png',action:function(){
    //             var closeText = last.children("a").text().trim();
    //             var nowText = $("#tabs").find("li[class='active']").children("a").text().trim();
    //             if(closeText==nowText){
    //                 //当前页面
    //                 var prevAll = last.prevAll();
    //                 if(prevAll.length!=0){
    //                     prevAll.remove();
    //                     var tab = last.children("a").attr("aria-controls").toString();
    //                     //$("#tabs>ul>li").not(shouye).remove();
    //                     $("#tabs>div").find("div[id='"+tab+"']").prevAll().remove();
    //                     //tabs.tabs("refresh");
    //                 }else{
    //                     layer.msg('<b>左侧没有啦</b>');
    //                 }
    //             }else{
    //                 //不是当前页，当前页的active去掉
    //                 var now = $("#tabs").find("li[class='active']");
    //                 var nowid = now.children("a").attr("aria-controls").toString();
    //                 now.removeClass("active");
    //                 $("#tabs").find("div[id='"+nowid+"']").removeClass("active");
    //                 var prevAll = last.prevAll();
    //                 if(prevAll.length!=0){
    //                     prevAll.remove();
    //                     var tab = last.children("a").attr("aria-controls").toString();
    //                     //$("#tabs>ul>li").not(shouye).remove();
    //                     $("#tabs>div").find("div[id='"+tab+"']").prevAll().remove();
    //                     last.addClass("active");
    //                     var id = last.children("a").attr("aria-controls").toString();
    //                     $("#tabs").find("div[id='"+id+"']").addClass("active");
    //                     //tabs.tabs("refresh");
    //                 }else{
    //                     layer.msg('<b>左侧没有啦</b>');
    //                 }
    //             }
    //             /*var prevAll = last.prevAll();
    //              if(prevAll.length!=0){
    //              prevAll.remove();
    //              }else{
    //              layer.msg('<b>左侧没有啦</b>');
    //              }
    //              var tab = last.attr("aria-controls").toString();
    //              //$("#tabs>ul>li").not(shouye).remove();
    //              $("#tabs>div").find("div[id='"+tab+"']").prevAll().remove();*/
    //             //tabs.tabs("refresh");
    //         }
    //         }
    //     ]
    // });
    
};
var closeTab = function (id) {
    //如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($("li.active").attr('id') == "tab_" + id) {
        $("#tab_" + id).prev().addClass('active');
        $("#" + id).prev().addClass('active');
        var prevId=$("#tab_" + id).prev().attr('id').replace("tab_","");
        setActiveMenuColor(prevId);
    }
    //关闭TAB
    $("#tab_" + id).remove();
    $("#" + id).remove();
};
$(function () {
    mainHeight = $(document.body).height();
    $('.main-left,.main-right').height(mainHeight);
    $("[addtabs]").click(function () {
        addTabs({ id: $(this).attr("id"), title: $(this).attr('title'), close: true });
    });

    $(".nav-tabs").on("click", "[tabclose]", function (e) {
        id = $(this).attr("tabclose");
        closeTab(id);
    });
});


//使用cjkEncode对中文进行编码转换  (报表)
  function cjkEncode(text) {                                                                            
      if (text == null) {         
        return "";         
      }         
      var newText = "";         
      for (var i = 0; i < text.length; i++) {         
        var code = text.charCodeAt (i);          
        if (code >= 128 || code == 91 || code == 93) {  //91 is "[", 93 is "]".         
          newText += "[" + code.toString(16) + "]";         
        } else {         
          newText += text.charAt(i);         
        }         
      }         
      return newText;         
    }     

//设置活动菜单项的颜色
function setActiveMenuColor(id) {

    $(".submenu a").removeClass("blue");
    $(".submenu").find("[href*='"+id.replace("tab_","")+"']").addClass("blue");

}