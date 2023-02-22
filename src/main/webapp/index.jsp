<%--
  Created by IntelliJ IDEA.
  User: xiaoxin
  Date: 2022/11/26
  Time: 7:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./less/index.css">
    <title>Document</title>
</head>
<!--宿舍管理-->
<body>
<div id="app">
    <%--对话框--%>
    <el-dialog
            title="个人信息"
            :visible.sync="dialogVisible"
            width="30%">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="账户名" prop="userName">
                <el-input v-model="ruleForm.userName" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="userPassword">
                <el-input v-model="ruleForm.userPassword" :disabled="true" v-if="flag == 1"></el-input>
                <el-input v-model="ruleForm.userPassword" v-else></el-input>
            </el-form-item>

            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="gender">
                <el-input v-model="ruleForm.gender" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="电话" prop="phone">
                <el-input v-model="ruleForm.phone" :disabled="true" v-if="flag == 1"></el-input>
                <el-input v-model="ruleForm.phone" v-else></el-input>
            </el-form-item>

            <el-form-item label="身份" prop="type">
                <el-input v-model="typesuguan" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="update('ruleForm')" v-if="flag == 1">修改</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" v-else>确认修改</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <table border="0" cellspacing="0" cellpadding="0">
        <tr id="topper">
            <td id="person">
                <div class="bio">
                    <p>
                        <c:if test="${user.type == 2}">
                            管理员
                        </c:if>
                        <c:if test="${user.type == 1}">
                            宿管
                        </c:if>
                    </p>
                    <div><i class="el-icon-s-custom"></i></div>
                    <span @click="select">${user.name}</span>
                </div>
            </td>
            <td>
                <div id="topone">
                    <div>宿舍管理系统</div>
                    <div><a href="./init.html" target="isframe"><i class="el-icon-female"></i>首页</a></div>
                    <div class="quit"><i class="el-icon-aim">安全退出</i></div>
                </div>
                <div id="toptwo">
                    <span id="date" class="el-icon-timer"></span>
                </div>
            </td>
        </tr>

        <tr id="conter">
            <td id="left">
                <ul id="nav">
                    <%--                    需要多个下拉时，复制一下所有 li 和 ul 进行扩充就可以--%>
                    <li class="li">
                        <i class="el-icon-s-home"></i>管理系统<i class="el-icon-arrow-down"></i>
                    </li>
                    <ul class="subtitle">
                        <c:if test="${user.type == 2}">
                            <li>
                                <a href="./dorm.html" target="isframe">宿管管理</a>
                            </li>
                            <li>
                                <a href="./test.html" target="isframe">学生管理</a>
                            </li>
                            <li>
                                <a href="./louyu.html" target="isframe">楼宇管理</a>
                            </li>
                            <li>
                                <a href="./sushe.html" target="isframe">宿舍管理</a>
                            </li>
                        </c:if>
                        <c:if test="${user.type == 1 || user.type == 2}">
                            <li>
                                <a href="./test.html" target="isframe">学生迁出登记</a>
                            </li>
                            <li>
                                <a href="./test.html" target="isframe">学生迁出记录</a>
                            </li>
                            <li>
                                <a href="./test.html" target="isframe">学生缺寝记录</a>
                            </li>
                        </c:if>
                    </ul>

                    <li class="li quit">
                        <i class="el-icon-switch-button"></i>
                        退出登录
                    </li>
                </ul>
            </td>
            <td id="right">
                <iframe name="isframe" src="./init.html" frameborder="0" width="100%" height="100%"></iframe>
            </td>
        </tr>

        <tr id="button">
            <td colspan="2">
                <div id="tail">
                    <span></span>
                    <span></span>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>

<script src="./js/axios-0.18.0.js"></script>
<script src="./js/jquery.js"></script>
<script src="./js/vue.js"></script>
<script src="./element-ui/lib/index.js"></script>
<link rel="stylesheet" href="./element-ui/lib/theme-chalk/index.css">

<script>
    new Vue({
        el: "#app",
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios({
                            method: "post",
                            url: "http://localhost:8080/schooldorm/user/updateById",
                            data:this.ruleForm
                        }).then(resp=>{
                            if (resp.data == "success"){
                                // 提交完后，要关闭弹出和清除里面的数据
                                this.dialogVisible=false;
                                // 清空
                                this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            // 修改
            update(){
                this.flag = 2;
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            select(){
                this.dialogVisible = true;
                if (this.ruleForm.type == 2){
                    this.typesuguan = '管理员';
                }else if (this.ruleForm.type == 1){
                    this.typesuguan = '宿管';
                }

            }
        },
        data(){
          return {
              flag:1,
              typesuguan:'',
              // 个人信息弹窗
              dialogVisible: false,
              // 个人信息框
              ruleForm: {
                  id: '${user.id}',
                  userName: '${user.userName}',
                  userPassword: '${user.userPassword}',
                  name: '${user.name}',
                  gender: '${user.gender}',
                  phone: '${user.phone}',
                  type: '${user.type}'
              },
              rules: {
                  userPassword: [
                      { required: true, message: '密码不能为空', trigger: 'blur' },
                      { min: 3, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                  ],
                  phone: [
                      { required: true, message: '电话不能为空', trigger: 'change' },
                      { min: 11,max: 11 , message: '长度为11字符', trigger: 'blur' }
                  ]
              }
          };
        },
    })


    var li = document.querySelectorAll(".li");
    var subtitle = document.querySelectorAll(".subtitle");
    var a = document.querySelector("#nav").querySelectorAll("a");
    console.log(a);
    for (let i = 0; i < li.length; i++) {
        li[i].addEventListener("click", function () {
            var s = subtitle[i];
            $(s).slideToggle();
        })
    }

    var titlei = '<i class="el-icon-right"></i>';
    for (let i = 0; i < subtitle.length; i++) {
        subtitle[i].addEventListener("click", function (e) {
            console.log(e.target);
            for (let k = 0; k < a.length; k++) {
                a[k].classList.remove("back");
                a[k].classList.remove("el-icon-right");
            }
            e.target.classList.add("back");
            e.target.classList.add("el-icon-right");
        })
    }

    // 日期
    var d = document.querySelector("#date");

    function getdate() {
        var date = new Date();
        var year = date.getFullYear(); // 获取年
        var month = date.getMonth() + 1; // 获取月
        var day = date.getDate();   // 获取日期
        var hours = date.getHours();    // 获取小时
        var minutes = date.getMinutes(); // 获取分钟
        var seconds = date.getSeconds(); // 获取秒
        var day = date.getDay(); // 获取星期
        var dayarrays = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var dateindex = year + "年" + month + "月" + day + "日  " + hours + "时" + minutes + "分" + seconds + "秒  " + dayarrays[day];
        d.innerText = dateindex;
    }

    getdate();
    setInterval(() => {
        getdate();
    }, 1000);

    // 当点击退出登录按钮时
    var quits = document.querySelectorAll(".quit");
    console.log(quits);
    for (let i = 0; i < quits.length; i++) {
        quits[i].addEventListener("click", function () {
            <%
            // 2.获取路径
             // request.getScheme()获取协议http   request.getServerName()获取ip名localhost
             // request.getServerPort()获取端口8080   request.getContextPath()获取虚拟目录schooldorm
                String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/";
            %>
            location.href = "<%=path%>user/quit";
        })
    }

</script>

</html>