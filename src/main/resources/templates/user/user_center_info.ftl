<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>天天生鲜-用户中心</title>
	<link rel="stylesheet" type="text/css" href="/static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="header_con">
		<div class="header">
			<div class="welcome fl">欢迎!</div>
			<div class="fr">
				<div class="login_info fl">
					欢迎您${UserInfoVo.name}
				</div>
				<div class="login_btn fl">
					<a href="/buyer/logout">退出登录</a>
				</div>
				<div class="user_link fl">
					<span>|</span>
					<a href="user_center_info.ftl">用户中心</a>
					<span>|</span>
					<a href="cart.html">我的购物车</a>
					<span>|</span>
					<a href="user_center_order.html">我的订单</a>
				</div>
			</div>
		</div>		
	</div>

	<div class="search_bar clearfix">
		<a href="/buyer/index" class="logo fl"><img src="/static/images/logo.png"></a>
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;用户中心</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>		
	</div>

	<div class="main_con clearfix">
		<div class="left_menu_con clearfix">
			<h3>用户中心</h3>
			<ul>
				<li><a href="user_center_info.ftl" class="active">· 个人信息</a></li>
				<li><a href="user_center_order.html">· 全部订单</a></li>
				<li><a href="user_center_site.html">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
				<div class="info_con clearfix">
				<h3 class="common_title2">基本信息</h3>
						<ul class="user_info_list">
							<li><span>用户名：</span><#if UserInfoVo.name??>${UserInfoVo.name}
							<#else ><div class="row-fluid">
								<div class="span12">
									<form class="form-inline" action="/buyer/infochange" method="POST">
										<input type="text" name="username" placeholder="请设置"/><button type="submit" class="submit_btn" id="btnSubmit">保存</button>
									</form>
								</div>
							</div>
								</#if></li>
							<li><span>电话：</span><#if UserInfoVo.phone??>${UserInfoVo.phone}
							<#else ><div class="row-fluid">
								<div class="span12">
									<form class="form-inline" action="/buyer/infochange" method="POST">
										<input type="text" name="phone" placeholder="请设置" /><button type="submit" class="submit_btn" id="btnSubmit">保存</button>
									</form>
								</div>
							</div>
							</#if></li>
							<li><span>联系地址：</span>	<#if UserInfoVo.address??>${UserInfoVo.address}
						<#else ><div class="row-fluid">
							<div class="span12">
								<form class="form-inline" action="/buyer/infochange" method="POST">
									<input type="text" name="address" placeholder="请设置"/><button type="submit" class="submit_btn" id="btnSubmit">保存</button>
								</form>
							</div>
						</div>
							</#if></li>
                            <li><span>最近登录：</span><#if UserInfoVo.name??>${UserInfoVo.loginTime}
							</#if></li>
                        </ul>
				</div>
				
				<h3 class="common_title2">最近浏览</h3>
				<div class="has_view_list">
					<ul class="goods_type_list clearfix">
				<li>
					<a href="detail.html"><img src="/static/images/goods/goods003.jpg"></a>
					<h4><a href="detail.html">大兴大棚草莓</a></h4>
					<div class="operate">
						<span class="prize">￥16.80</span>
						<span class="unit">16.80/500g</span>
						<a href="#" class="add_goods" title="加入购物车"></a>
					</div>
				</li>

				<li>
					<a href="#"><img src="/static/images/goods/goods004.jpg"></a>
					<h4><a href="#">吐鲁番梨光杏</a></h4>
					<div class="operate">
						<span class="prize">￥5.50</span>
						<span class="unit">5.50/500g</span>
						<a href="#" class="add_goods" title="加入购物车"></a>
					</div>
				</li>

				<li>
					<a href="#"><img src="/static/images/goods/goods005.jpg"></a>
					<h4><a href="#">黄肉桃</a></h4>
					<div class="operate">
						<span class="prize">￥10.00</span>
						<span class="unit">10.00/500g</span>
						<a href="#" class="add_goods" title="加入购物车"></a>
					</div>
				</li>

				<li>
					<a href="#"><img src="/static/images/goods/goods006.jpg"></a>
					<h4><a href="#">进口西梅</a></h4>
					<div class="operate">
						<span class="prize">￥28.80</span>
						<span class="unit">28.8/500g</span>
						<a href="#" class="add_goods" title="加入购物车"></a>
					</div>
				</li>

				<li>
					<a href="#"><img src="/static/images/goods/goods007.jpg"></a>
					<h4><a href="#">香梨</a></h4>
					<div class="operate">
						<span class="prize">￥6.45</span>
						<span class="unit">6.45/500g</span>
						<a href="#" class="add_goods" title="加入购物车"></a>
					</div>
				</li>
			</ul>
		</div>
		</div>
	</div>



	<div class="footer">
		<div class="foot_link">
			<a href="#">关于我们</a>
			<span>|</span>
			<a href="#">联系我们</a>
			<span>|</span>
			<a href="#">招聘人才</a>
			<span>|</span>
			<a href="#">友情链接</a>		
		</div>
		<p>电话：xxx    京ICP备xx号</p>
	</div>
	
</body>
</html>