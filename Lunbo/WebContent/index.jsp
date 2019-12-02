<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style type="text/css">

 
        .imgBox{
            border-top: 2px solid cadetblue;
            width: 50%;
            height: 500px;
            margin: 0 auto;

        }

        .imgBox img{
            width: 60%;
            height: 300px;
            margin: 0 auto;
            padding-top: 30px;

        }

        #img1{
            display: block;
        }

        #img2{
            display: none;
        }

        #img3{
            display: none;
        }
		#img4{
            display: none;
        }
    </style>
</head>
<body>
<div class="imgBox">
    <img id="img1" class="img-slide" src="images/1.jpg" >
    <img id="img2" class="img-slide" src="images/2.jpg" >
    <img id="img3" class="img-slide" src="images/3.jpg" >
    <img id="img4" class="img-slide" src="images/4.jpg" >
</div>
<script type="text/javascript">
    var index=0;
    //改变图片
    function ChangeImg() {
        index++;
        var a=document.getElementsByClassName("img-slide");
        if(index>=a.length) index=0;
        for(var i=0;i<a.length;i++){
            a[i].style.display='none';
        }
        a[index].style.display='block';
    }
    //设置定时器，每隔两秒切换一张图片
    setInterval(ChangeImg,2000);
	
</script>
</body>
</html>