
$("#searchInput").keyup(function () {
    var rows = $("#toBody").find("tr").hide();

    if (this.value.length) {
        var data = this.value.split(" ");
        $.each(data, function (i, v) {
            rows.filter(":contains('" + v + "')").show();
        });
    }
    else
        rows.show();
});
