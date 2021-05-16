function setCookie(name, value, expiredays){
    var today = new Date();
    today.setDate(today.getDate() + expiredays);
    document.cookie = name + '=' + escape(value) + '; expires=' + today.toGMTString();
}

function getCookie(name) {
    var cookie = document.cookie;
    if (document.cookie != "") {
        var cookie_array = cookie.split("; ");
        for ( var index in cookie_array) {
            var cookie_name = cookie_array[index].split("=");
            if (cookie_name[0] == "mycookie") {
                return cookie_name[1];
            }
        }
    }
    return;
}
$("#modal-today-close").click(function() {
    $("#myModal").modal("hide");
    setCookie("mycookie", 'popupEnd', 1);
})

var checkCookie = getCookie("mycookie");

if(checkCookie == 'popupEnd') {
    $("#myModal").modal("hide");
} else {
    $('#myModal').modal("show");
}