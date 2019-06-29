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
                    <form id="loginForm" method="post" action="user.html" class="zcform">
                        <p class="clearfix">
                            <label class="one" for="userPhone">手机号：</label>
                            <input id="userPhone" name="userPhone" onblur="checkUserPhone()" type="text" class="required" value placeholder="请输入您的手机号" />
                            <span id="phoneSpan"></span>
                        </p>
                        <p class="clearfix">
                            <label class="one"  for="userPass">登录密码：</label>
                            <input id="userPass" name="userPass" onblur="checkUserPass()"  type="password" class="{required:true,rangelength:[8,20],}" value placeholder="请输入密码" />
                            <span id="passSpan"></span>
                        </p>
                        <p class="clearfix">
                            <label class="one" for="picAuthCode">验证码：</label>
                            <input id="picAuthCode" name="picAuthCode" onblur="checkAuthCode()" type="text" class="required" value placeholder="请输入图片验证码"  />
                            <span id="codeSpan"></span>
                            <img id="verifyCodePic" src="/auth/randomNum" >
                            <span id="changeimg">换一张</span>
                        </p>
                        <!--<p class="clearfix agreement">
                            <input type="checkbox" />
                            <b class="left">已阅读并同意<a href="#">《用户协议》</a></b>
                        </p>-->
                        <p class="clearfix"><input class="submit" type="button" id="login" value="立即登录"/></p>
                    </form>

                    <div class="reg-logo-right">
                        <h3>如果您没有账号，请</h3>
                        <a href="javascript:openFtl('/skip/toUserRegist')" class="logo-a">立即注册</a>
                    </div><!--reg-logo-right/-->
                    <div class="clears"></div>
                </div><!--reg-logo/-->
            </div><!--width1190/-->
        </div><!--content/-->
    </body>

    <script>
        //用于阻止提交
        var flag = false;
        var userPhone = $("#userPhone");
        var userPass = $("#userPass");
        var picAuthCode = $("#picAuthCode");

        //点击切换验证码
        $(function(){
            $('#changeimg').click(function(){
                $('#verifyCodePic')[0].src='/auth/randomNum?'+Math.random();
            });
        });
        //验证手机号
        function checkUserPhone() {
            var reg = /^1(3|4|5|7|8)\d{9}$/;
            var bol = reg.test(userPhone.val());
            if (!bol) {
                flag = false;
                $("#phoneSpan").html("<font color='red'>手机号格式不正确</font>");
                return;
            }
            flag = true;
            $("#phoneSpan").html("");
        }
        //验证密码
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
        //校验验证码
        function checkAuthCode(){
            if(picAuthCode.val().length!=4){
                flag = false;
                $("#codeSpan").html("<font color='red'>验证码错误</font>");
                return;
            }
            //校验图片验证码
            $.ajax({
                url:'/user/checkAuthCode',
                type:'get',
                dataType:'json',
                data:{"picAuthCode":picAuthCode.val()},
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

        //点击登录
        $("#login").click(function (){
            checkUserPhone()
            if(!flag) return;
            checkUserPass()
            if(!flag) return;
            checkAuthCode()
            if(!flag) return;
            //登录
            $.ajax({
                url:'/user/userLogin',
                type:'post',
                dataType:'json',
                data:$("#loginForm").serialize(),
                async:false,
                success:function(data){
                    if(data.code!=200){
                        $("#eer").html(data.info);
                        return;
                    }
                    $("#eer").html(data.info);
                    location.href="/skip/toForeHome";
                }
            });
        })

    </script>

</html>