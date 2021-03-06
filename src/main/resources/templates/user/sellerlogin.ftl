<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>登陆</title>
	<link rel="stylesheet" href="../../static/libs/particles/css/style.css">
	<link rel="stylesheet" href="../../static/css/base.css">
	<link rel="stylesheet" href="../../static/css/sellerlogin.css" />
</head>
<body>
	<!-- particles.js container -->
	<div id="particles-js"></div>
	<div id="wrapper">
		<div>
			<img src="/static/img/zhihu_logo.png" />
			<h2>欢迎来到本人的网站</h2>
		</div>
		<nav class="switch_nav">
			<a href="register" id="switch_signup" class="switch_btn">注册</a>
			<a href="prelogin" id="switch_login" class="switch_btn on">用户登陆</a>
			<a href="javascript:;" id="switch_sellerlogin" class="switch_btn in">卖家登陆</a>
			<div class="switch_bottom" id="switch_bottom"></div>
		</nav>
		<div id="login">
			<form method="post" action="/buyer/sellerlogin">
				<ul class="group_input">
					<li>
						<input type="text" name="username" id="mobile" placeholder="用户名" />
					</li>
					<li>
						<input type="password" name="pwd" id="psd" placeholder="密码" />
					</li>
				</ul>
				<button type="submit" class="submit_btn" id="btnSubmit">登陆</button>
			</form>
			<div class="states">
				<span class="left"><a href="javascript:;">手机验证码登陆</a></span>
				<span class="right"><a href="javascript:;">无法登陆？</a></span>
			</div>
			<div class="states">
				<a href="javascript:;" class="social_account">社交账号登陆</a>
				<div class="states three_MinIcon">
					<a href="javascript:;" class="MinIcon_weixin"><img src="../../static/img/icon_weixin.jpg" style="width:20px;height:18px" /></a>
					<a href="javascript:;" class="MinIcon_weibo"><img src="../../static/img/icon_weibo.jpg" style="width:20px;height:18px" /></a>
					<a href="javascript:;" class="MinIcon_qq"><img src="../../static/img/icon_qq.jpg" style="width:20px;height:18px" /></a>
				</div>
			</div>
		</div>
		<div class="QRcode_btn">
			<div type="submit" class="submit_btn download_btn">下载App</div>
			<div class="QRcode">
				<img src="../../static/img/QRcode.png" alt="QRcode" />
				<div class="box"></div>
			</div>

		</div>
		<div id="footer">

			<span>·</span><a href="javascript:;">京ICP证xx号</a><span>·</span><span>京公网安备xx号</span><span>·</span><a href="javascript:;">出版物经营许可证</a>
		</div>
	</div>
	<script src="../../static/libs/jquery-1.12.4/jquery.min.js"></script>
	<script src="../../static/libs/particles/particles.min.js"></script>
	<script src="../../static/libs/particles/js/app.js"></script>
	<!-- <script src="../../static/libs/particles/js/lib/stats.js"></script> -->
	<script>
		var count_particles, stats, update;
		stats = new Stats;
		stats.setMode(0);
		stats.domElement.style.position = 'absolute';
		stats.domElement.style.left = '0px';
		stats.domElement.style.top = '0px';
		document.body.appendChild(stats.domElement);
		count_particles = document.querySelector('.js-count-particles');
		update = function() {
			stats.begin();
			stats.end();
			if (window.pJSDom[0].pJS.particles && window.pJSDom[0].pJS.particles.array) {
				count_particles.innerText = window.pJSDom[0].pJS.particles.array.length;
			}
			requestAnimationFrame(update);
		};
		requestAnimationFrame(update);
	</script>
	<script>
		$(".download_btn").click(function(){
			if($(".QRcode").css("display")=="none"){
				$(".QRcode").show();
				$(".download_btn").text("关闭二维码");
			}else{
				$(".QRcode").hide();
				$(".download_btn").text("下载App");
			}
		});	
	</script>
	<script>
		$(function(){
		//为表单的必填文本框添加提示信息（选择form中的所有后代input元素）
        // $("form :input.required").each(function () {
        //     //通过jquery api：$("HTML字符串") 创建jquery对象
        //     var $required = $("<strong class='high'>*</strong>");
        //     //添加到this对象的父级对象下
        //     $(this).parent().append($required);
        // });
			// var errorMsg = $(".error-msg").text();
		//为表单元素添加失去焦点事件
		$("form :input").blur(function(){
			var $parent = $(this).parent();
			$parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）		
			//验证手机号
			if($(this).is("#mobile")){
				var mobileVal = $.trim(this.value);
				if(mobileVal == ""){
					var errorMsg = " 请输入账号";
					$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
				} else{
					var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
				}
			}
			//验证密码
            if($(this).is("#psd")){
                var psdVal = $.trim(this.value);
                var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    var errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
		}).keyup(function(){
			//triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
			$(this).triggerHandler("blur"); 
		}).focus(function(){
			$(this).triggerHandler("blur");
		});

				//点击重置按钮时，通过trigger()来触发文本框的失去焦点事件
		$("#btnSubmit").click(function(){
    		//trigger 事件执行完后，浏览器会为submit按钮获得焦点
    		$("form .required:input").trigger("blur"); 
    		var numError = $("form .onError").length;
    		if(numError){
    			return false;
    		}
    		alert('登陆成功！')

    	});
		})
		
	</script>
</body>
</html>
