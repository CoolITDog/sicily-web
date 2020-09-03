var index = (function (h) {
    var o = {
        init: function () {
            console.log('index.js init');
            o.initEvent();
        },

        initEvent: function(){
            // 跳转菜单页面
            h('#category_btn').on('click', function () {
                var url = 'menu/index';
                $('#content').load(url);
            })
            // 跳转商品页面
            h('#goods_btn').on('click',function () {
                var url = 'goods/index';
                $('#content').load(url);
            });

        }
    };
    return {
        init: o.init
    }
})($);
index.init();
