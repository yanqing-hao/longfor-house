<html>
    <head>
        <title></title>
        <script type="text/javascript">
            $(function(){
                //导航定位
                $(".nav li:eq(0)").removeClass("navCur");
                $(".nav li:eq(1)").removeClass("navCur");
                $(".nav li:eq(2)").removeClass("navCur");
                $(".nav li:eq(3)").removeClass("navCur");
                $(".nav li:eq(4)").removeClass("navCur");
                $(".nav li:eq(5)").removeClass("navCur");
                $(".nav li:eq(6)").removeClass("navCur");
            })
        </script>
    </head>
    <body>
        <div class="content">
            <div class="width1190">
                <div class="reg-logo">
                    <form id="signupForm" method="post" action="" class="zcform">
                        <p class="clearfix">
                            <label class="one" for="agent">用户名：</label>
                            <input id="userName" name="userName" onblur="checkUserName()" type="text" class="required" value placeholder="请输入您的用户名" />
                            <span id="nameSpan"></span>
                        </p>
                        <p class="clearfix">
                            <label class="one"  for="password">登录密码：</label>
                            <input id="userPass" name="userPass" onblur="checkUserPass()" type="password" class="{required:true,rangelength:[8,20],}" value placeholder="请输入密码" />
                            <span id="passSpan"></span>
                        </p>
                        <p class="clearfix">
                            <label class="one" for="confirm_password">确认密码：</label>
                            <input id="confirm_password" name="confirm_password" onblur="checkConPass()" type="password" class="{required:true,equalTo:'#password'}" value placeholder="请再次输入密码" />
                            <span id="conPassSpan"></span>
                        </p>
                        <p class="clearfix">
                            <label class="one" for="userPhone">手机号：</label>
                            <input id="userPhone" name="userPhone" disabled onblur="checkUserPhone()" type="text" class="required" value placeholder="请输入您的手机号" />
                            <span id="phoneSpan"></span>
                            <input type="button" value="获取验证码" id="send" />
                        </p>
                        <p class="clearfix">
                            <label class="one" for="authCode">验证码：</label>
                            <input id="authCode" name="authCode" type="text" onblur="checkAuthCode()" class="required" value placeholder="请输入6位验证码"  />
                            <span id="codeSpan"></span>
                                <#-- <img id="verifyCodePic" src="/auth/randomNum" >-->
                        </p>
                        <!--<p class="clearfix agreement">
                            <input type="checkbox" />
                            <b class="left">已阅读并同意<a href="#">《用户协议》</a></b>
                        </p>-->
                        <p class="clearfix"><input class="submit" id="regist" type="button" value="立即注册"/></p>
                        <span id="eer"></span>
                    </form>
                    <div class="reg-logo-right">
                        <h3>如果您已有账号，请</h3>
                        <a href="javascript:openFtl('/skip/toUserLogin')" class="logo-a">立即登录</a>
                    </div><!--reg-logo-right/-->
                    <div class="clears"></div>
                </div><!--reg-logo/-->
            </div><!--width1190/-->
        </div><!--content/-->
    </body>

    <script>
        //用于阻止提交
        var flag = false;
        var userName = $("#userName");
        var userPhone = $("#userPhone");
        var userPass = $("#userPass");
        var confirm_password = $("#confirm_password");
        var authCode = $("#authCode");


        //发送短信验证码
        $("#send").click(function(){
            $.ajax({
                url:'/user/sendMs',
                type:'get',
                dataType:'json',
                data:{"userPhone":userPhone.val()},
                async:false,
                success:function(data){
                    $("#phoneSpan").html(data.info);
                }
            });
        })

        //验证用户名
        function checkUserName(){
            var reg = /^(\w|[\u4e00-\u9fa5])+$/;
            var bol = reg.test(userName.val());
            if(!bol){
                flag = false;
                $("#nameSpan").html("<font color='red'>用户名不能为空</font>");
                return;
            }
            flag = true;
            $("#nameSpan").html("");
        }
        //验证手机号
        function checkUserPhone(){
            var reg = /^1(3|4|5|7|8)\d{9}$/;
            var bol = reg.test(userPhone.val());
            if(!bol){
                flag = false;
                $("#phoneSpan").html("<font color='red'>手机号不正确</font>");
                return;
            }
            //手机号唯一验证
            $.ajax({
                url:'/user/checkUserPhone',
                type:'get',
                async:false,
                dataType:'json',
                data:{"userPhone":userPhone.val()},
                success:function(data){
                    if(data.code!=200){
                        flag = false;
                        $("#phoneSpan").html(data.info);
                        return;
                    }
                    flag = true;
                    $("#phoneSpan").html(data.info);
                },
                error:function(){
                }
            });
        }
        //验证密码 089239
        function checkUserPass(){
            var reg = /^\w+$/;
            var bol = reg.test(userPass.val());
            if(!bol){
                flag = false;
                $("#passSpan").html("<font color='red'>密码不能为空</font>");
                return;
            }
            flag = true;
            $("#passSpan").html("");
        }
        //验证确认密码
        function checkConPass(){
            if(userPass.val()!=confirm_password.val()){
                $("#conPassSpan").html("<font color='red'>密码不一致</font>");
                flag = false;
                return;
            }
            $("#conPassSpan").html("");
            flag = true;
            //确认密码输完之后才 能发短信验证码
            $("#userPhone").attr("disabled", false);
        }
        //校验验证码
        function checkAuthCode(){
            if(authCode.val().length!=6){
                flag = false;
                $("#codeSpan").html("<font color='red'>请输入6位验证码</font>");
                return;
            }
            //校验验证码
            $.ajax({
                url:'/user/checkPhoneCode',
                type:'get',
                dataType:'json',
                data:{"authCode":authCode.val()},
                async:false,
                success:function(data){
                    if(data.code!=200){
                        flag = false;
                        $("#codeSpan").html(data.info);
                        return;
                    }
                    flag = true;
                    $("#codeSpan").html(data.info);
                }
            });
        }

        //点击注册
        $("#regist").click(function(){
            checkUserName();
            if(!flag) return;
            checkUserPhone();
            if(!flag) return;
            checkUserPass();
            if(!flag) return;
            checkConPass();
            if(!flag) return;
            checkAuthCode();
            if(!flag) return;
            //注册
            $.ajax({
                url:'/user/userRegist',
                type:'post',
                dataType:'json',
                data:$("#signupForm").serialize(),
                async:false,
                success:function(data){
                    if(data.code!=200){
                        $("#eer").html(data.info);
                        return;
                    }
                    $("#eer").html(data.info);
                }
            });
        })


    </script>
</html>