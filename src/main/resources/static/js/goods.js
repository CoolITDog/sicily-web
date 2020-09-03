var g = (function (h) {
    
    var o = {
      
        init:function () {
          console.log('goods.is init');
          o.initEvent();
        },

        initEvent:function () {
            // 上传商品图片
            h('#goods_upload').on('change', function (event) {
                console.log('upload')
                var $file = event.currentTarget;
                var file = $file.files;
                var formData = new FormData();
                formData.append('file', file[0]);
                h.ajax({
                    url: 'goods/uploadImage',
                    type: 'POST',
                    dataType: 'json',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success:function (data) {
                        console.log(data);
                        h('#box').append('<div class="photo-item"><img id="goods_image" class="item-image" width="100%" height="100%" src="'+data.data+'"/></div>');
                    },
                    fail:function (data) {
                        console.log(data);
                    }
                });
            });

            // 添加商品
            h('#add_goods_btn').on('click', function () {
                console.log('add');
                var categoryId = h('#goods_category').val();
                var name = h('#goods_name').val();
                var image = h('#goods_image').attr('src');
                var todayRepository = h('#goods_repository').val();
                var limit = 0;//不限购
                var price = h('#goods_price').val();
                var description = h('#goods_detail').val();
                var onSale = '1';
                var foodInfo={
                    categoryId:categoryId,
                    name:name,
                    image:image,
                    todayRepository:todayRepository,
                    limit:limit,
                    price:price,
                    description:description,
                    onSale:onSale,
                }
                h.ajax({
                    url: 'goods/edit',
                    type: 'POST',
                    dataType: 'json',
                    contentType:"application/json;charset=utf-8",
                    data: JSON.stringify(foodInfo),
                    success:function (data) {
                        console.log(data);
                    },
                    fail:function (data) {
                        console.log(data);
                    }
                })
            })
        }
    };
    return {
        init: o.init
    }
})($);
g.init();
