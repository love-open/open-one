//显示当前账号
//var curAccount = $('#curAccount').text();
//$("#currentUser").text(curAccount);
var curAccount = sysUsersView.username;
$("#currentUser").text(curAccount);
//编辑当前用户
function editCurAccount() {
    var params = {
        username: curAccount
    };
    $.ajax({
        url: '/user/getUsers',
        data: params,
        dataType: 'json',
        type: "post",
        beforeSend: function(xhr, settings){
            xhr.setRequestHeader("Authorization",  sysUsersView.token);
        },
        success: function (data) {
            if (data) {
                if (data.code) {
                    //清空 文本框
                    $('#editCurAccountModal .m-input').val('');

                    var sysUsers = data.sysUsers;
                    $('#editCurAccountId').val(sysUsers.id);
                    $('#editCurAccountName').val(sysUsers.name);
                    $('#editCurAccountAccount').val(sysUsers.username);
                    $('#editCurAccountTel').val(sysUsers.tel);
                    $('#editCurAccountEmail').val(sysUsers.email);

                    $('#editCurAccountModal').modal('show');
                } else {
                    swal('提示', data.msg, 'warning');
                }
            }
        }
    });
}
$(function () {
    //修改账号信息表单
    $("#editCurAccountForm").validate({
            highlight: function (e) {
                $(e).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (e) {
                $(e).closest('.form-group').removeClass('has-error');
            }
            ,
            submitHandler: function () {
                //保存当前帐号信息
                saveCurSysUsers();
            }
        }
    );
    //修改密码表单
    $("#updatePwdForm").validate({
            highlight: function (e) {
                $(e).closest('.form-group').addClass('has-error');
            },
            unhighlight: function (e) {
                $(e).closest('.form-group').removeClass('has-error');
            }
            ,
            submitHandler: function () {
                updatePassword();
            }
        }
    );
});
//保存当前帐号信息
function saveCurSysUsers() {
    var params = {
        id: $.trim($('#editCurAccountId').val()),
        name: $.trim($('#editCurAccountName').val()),
        username: $.trim($('#editCurAccountAccount').val()),
        tel: $.trim($('#editCurAccountTel').val()),
        email: $.trim($('#editCurAccountEmail').val())
    }
    $.ajax({
        type: "post",
        data: params,
        url: "/user/addSysUsers",
        dataType: "json",
        beforeSend: function(xhr, settings){
            xhr.setRequestHeader("Authorization",  sysUsersView.token);
        },
        success: function (data) {
            if (data.code) {
                swal('提示', data.msg, 'success');
            } else {
                swal('提示', data.msg, 'warning');
            }
        }
    })
}
//修改密码
$('#updatePwdBtn').click(function () {
    //清空 文本框
    $('#updatePwdModal .m-input').val('');
    $('#editCurAccountModal').modal('hide');
    $('#updatePwdModal').modal('show');
})
function updatePassword() {
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var confirmPassword = $("#confirmPassword").val();
    if(newPassword != confirmPassword){
        swal('提示', '两次输入密码不一样！', 'warning');
        return;
    }
    var params = {
        username: curAccount,
        oldPassword: oldPassword,
        newPassword: newPassword
    }
    $.ajax({
        type: "post",
        data: params,
        url: "/user/updatePassword",
        dataType: "json",
        beforeSend: function(xhr, settings){
            xhr.setRequestHeader("Authorization",  sysUsersView.token);
        },
        success: function (data) {
            if (data.code) {
                swal('提示', data.msg, 'success');
                // setTimeout(function () {
                //     $('#updatePwdModalModal').modal('hide');
                // }, 1000);
            } else {
                swal('提示', data.msg, 'warning');
            }
        }
    })
}
