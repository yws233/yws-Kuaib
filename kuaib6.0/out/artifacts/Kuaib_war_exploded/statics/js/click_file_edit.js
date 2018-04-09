jQuery(document).ready(function () {
    jQuery(".wordclick").dblclick(function () {
        id = jQuery(this).attr('id');
        text = jQuery(this).text();
        if (text) {
            jQuery(this).html("<textarea id='clear-txt' style='border:none; border-top: 1px solid #E8F3FE; background: white;width:395px;height:390px; outline: none;' name=" + id + " >" + text + "</textarea>");
            jQuery(".wordsupdate > textarea").focus().blur(function () {
                jQuery.ajax({
                    type: "POST",
                    url: "/Home/Update",
                    data: "ID=" + id + "&Str=" + jQuery("#" + id + " > textarea").val(),
                    success: function (msg) {
                        jQuery("#" + id).html(jQuery("#" + id + " > textarea").val());
                    },
                    error: function (msg) {jQuery("#" + id).text(msg);}
                });
            })
        }
    })
});