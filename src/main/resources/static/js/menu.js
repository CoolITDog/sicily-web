var house = (function (h) {
    var o = {
        init: function () {
            console.log("menu.js init");
            o.initLoad();
        },
        // 初始化数据
        initLoad:function () {
            o.requestPageData(1);
        },

        requestPageData:function (pageNum) {
            h.ajax({
                method:"get",
                data:{pageNum:pageNum, condition:null},
                url:"/sicily-web/menu/page",
                datatype:"html",
                contentType:"application/json;charset=utf-8",
                success:function(data){
                    console.log(data);
                    h('#menu_table').html(data.list);
                    h('#current_page').text(pageNum);
                }
            });
        }
    };
    return {
        init:o.init,
        requestPageData: o.requestPageData
    };
})($);
house.init();