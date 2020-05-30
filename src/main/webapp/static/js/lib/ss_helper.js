function saveSS() {
    $("input").each(function () {
        if(this.type.toLowerCase() !== "hidden") {
            if(this.type.toLowerCase() === "text") {
            	sessionStorage.removeItem(ss_prefix + "_" + this.id);
                sessionStorage.setItem(ss_prefix + "_" + this.id, $(this).val());
            } else if(this.type.toLowerCase() === "hidden") {

            } else if(this.type.toLowerCase() === "radio") {
                sessionStorage.removeItem(ss_prefix + "_" + this.name);
                sessionStorage.setItem(ss_prefix + "_" + this.name, $("input:radio[name=" + this.name+"]:checked").val());
            }
        }
    });
    $("select").each(function () {
        sessionStorage.removeItem(ss_prefix + "_" + this.id);
        sessionStorage.setItem(ss_prefix + "_" + this.id, $(this).val());
    });
}

function getSS() {
    $("input").each(function () {
        if(this.tagName.toLowerCase() === "input") {
            if(this.type.toLowerCase() === "text") {
            	if(sessionStorage.getItem(ss_prefix + "_" + this.id)) {
            		$(this).val(sessionStorage.getItem(ss_prefix + "_" + this.id));
            	}
            } else if(this.type.toLowerCase() === "hidden") {

            } else if(this.type.toLowerCase() === "radio") {
            	if(sessionStorage.getItem(ss_prefix + "_" + this.name)) {
            		$("input:radio[value=" + sessionStorage.getItem(ss_prefix + "_" + this.name)+"]").prop("checked", "checked")
            	}
            }
        }
    });
    $("select").each(function () {
    	$(this).val(sessionStorage.getItem(ss_prefix + "_" + this.id));
	});
}