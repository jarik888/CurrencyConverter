
$(function() {
    $("#date").datepicker({ dateFormat: "dd.mm.yy" });
    $('#amount').keyup(function () {
        if (this.value != this.value.replace(/[^0-9\.]/g, '')) {
            this.value = this.value.replace(/[^0-9\.,]/g, '');
        }
    });
    getCurrencies("30.12.2010");
    $("#date").val("30.12.2010");
});

function handleForm() {

    resetErrors();

    var data = {
        op : "exchange",
        date : $("#date").val(),
        from : $("#from").find(":selected").val(),
        to : $("#to").find(":selected").val(),
        amount : $("#amount").val()
    };

    $.post('CurrencyService', data, function(answer) {

        var result = "";
        var json = $.parseJSON(JSON.stringify(answer));

        $(json).each(function(i,val){
            $.each(val,function(k,v){

                if(!hasError(k,v)) {
                    result += k + ": " + v + "<br>";
                }

            });
        });

        $("#result").html(result);
        if (result != "") {
            $("#result-div").show();
        }

    }, 'json').fail(function(answer) {
        alert("Something went wrong. Sorry!");
    });
}

function getCurrencies(date) {
    var data = {
        op : "getCurrencies",
        date : date
    };

    $.post('CurrencyService', data, function(answer) {

        var json = $.parseJSON(JSON.stringify(answer));
        $(json).each(function(i,val){
            $.each(val,function(k,v){

                if(!hasError(k,v)) {
                    $("#from").append('<option value="'+ k +'">' + k + " - " + v + '</option>');
                    $("#to").append('<option value="'+ k +'">' + k + " - " + v + '</option>');
                    //console.log(k + " - " + v);
                }
            });
        });

    }, 'json').fail(function(answer) {
        alert("Server error. Sorry!");
    });
}

function hasError(key, value) {

    if (key.indexOf("error") > -1) { // contains 'error'
        var field = key.substring(0, key.indexOf("error"));
        $("#" + field + "input").addClass("has-error");
        $("#" + field + "info").html(value);
        return true;
    }
    return false;
}

function resetErrors() {

    var fields = ["date", "from", "to", "amount"];

    for (var i in fields) {
        $("#" + fields[i] + "-input").removeClass("has-error");
        $("#" + fields[i] + "-info").html("");
    }
}

function swap() {
    var from = $("#from").find(":selected").val();
    var to = $("#to").find(":selected").val();

    $("#from").val(to);
    $("#to").val(from);
}