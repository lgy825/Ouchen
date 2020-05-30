$(function () {
    $.ajaxSetup({
        beforeSend: function () {
            layer.load(1);
        },
        complete: function () {
            layer.closeAll('loading');
        }
    });
});