<script>
	$list = $("#fileList");
	// 初始化Web Uploader
	var uploader = WebUploader.create({
		//上传文件的name
		fileVal:"img",//写改成后台接受文件属性驱动的File的名字
	    // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: '/js/webuploader-0.1.5/Uploader.swf',
	    // 文件接收服务端,需要改成自己处理上传文件的Action中的方法
	    server: 'uploadFile',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#filePicker',
	    //只允许上传一张图片
	    fileNumLimit:0,
	    threads:1,
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png,mp4,mp5,wmv',
	        mimeTypes: 'image/*'
	    }
	});
	
	// 当有文件添加进来的时候
	uploader.on( 'fileQueued', function( file ) {
		$list.empty();
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	                '<img>' +
	                '<div class="info">' + file.name + '</div>' +
	            '</div>'
	            ),
	        $img = $li.find('img');
	
		
	    // $list为容器jQuery实例
	    $list.append( $li );
	
	    // 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    // thumbnailWidth x thumbnailHeight 为 100 x 100
	    var thumbnailWidth = 100;    
		var thumbnailHeight = 100; 
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }
	
	        $img.attr( 'src', src );
	    }, thumbnailWidth, thumbnailHeight );//缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档 
	});
	
	//上传成功后要执行的回调函数
	uploader.on('uploadSuccess',function(file,data){
		$("#imgUrl").val(data.url);
	})
	
	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
	    var $li = $( '#'+file.id ),
	        $error = $li.find('div.error');
	
	    // 避免重复创建
	    if ( !$error.length ) {
	        $error = $('<div class="error"></div>').appendTo( $li );
	    }
	
	    $error.text('上传失败');
	});	
</script>