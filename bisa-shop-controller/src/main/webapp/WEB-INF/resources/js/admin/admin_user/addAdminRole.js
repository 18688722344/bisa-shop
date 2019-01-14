var setting = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};

var zNodes = [
    {id: 1, pId: 0, name: "用户管理", open: true},
    {id: 11, pId: 1, name: "增加账户", open: true},
    {id: 111, pId: 11, name: "增加用户", checked: true},
    {id: 112, pId: 11, name: "增加管理员"},
    {id: 12, pId: 1, name: "编辑账户", open: true},
    {id: 121, pId: 12, name: "修改用户"},
    {id: 122, pId: 12, name: "修改管理员"},
    {id: 2, pId: 0, name: "角色权限管理", checked: true, open: true},
    {id: 21, pId: 2, name: "添加角色"},
    {id: 22, pId: 2, name: "添加权限"},
    {id: 23, pId: 2, name: "修改角色"}
];

var code;

function setCheck() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        py = $("#py").attr("checked") ? "p" : "",
        sy = $("#sy").attr("checked") ? "s" : "",
        pn = $("#pn").attr("checked") ? "p" : "",
        sn = $("#sn").attr("checked") ? "s" : "",
        type = {"Y": py + sy, "N": pn + sn};
    zTree.setting.check.chkboxType = type;
    showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
}

function showCode(str) {
    if (!code) code = $("#code");
    code.empty();
    code.append("<li>" + str + "</li>");
}

$(document).ready(function () {

    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    setCheck();
    $("#py").bind("change", setCheck);
    $("#sy").bind("change", setCheck);
    $("#pn").bind("change", setCheck);
    $("#sn").bind("change", setCheck);

});

