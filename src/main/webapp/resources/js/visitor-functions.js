/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
    if (register) {
        loadDevices("register");
        $('#modal-loader').modal('show');
    }else{
        loadDevices();
        loadSessions(0, 10);
    }
});

function loadData(url, params, success, failure) {
    $.ajax({
        url: url,
        data: params,
        type: 'POST'
    }).done(function (data, status, jqXHR) {
        if (success)
            success(data, status, jqXHR);
    }).fail(function (jqXHR, status, error) {
        if (failure)
            failure(jqXHR, status, error);
    });
}

function loadDevices(context) {
    var url = "visitor/devices";
    if(context === 'register')
        loadData(url, null, showDevicesSelection, showDeviceFail);
    else
        loadData(url, null, showDevices, showError);
}

function showDevicesSelection(data) {
    ko.applyBindings(new DevicesViewModel(data), $("#ddDevice")[0]);
    $('#modal-loader').modal('hide');
    $('#modal-register').modal('show');
}

function showDeviceFail(jqXHR, status, error){
    $('#modal-loader').modal('hide');
    alert("Sorry, an error occurred! Please refresh the page.");
}

function showDevices(data) {
    ko.applyBindings(new DevicesViewModel(data), $("#device-div")[0]);
}

function loadSessions(page, size) {
    var url = "visitor/sessions";
    var params = {"page": page, "size": size};

    loadData(url, params, showSessions, showError);
}

function showSessions(data) {
    ko.applyBindings(new SessionsViewModel(data.content), $("#visit-div")[0]);
}

function showError(jqXHR, status, error) {
    alert(error);
}

function registerDevice() {
    var configId = $('#txtConfig').val();
    var deviceId = $('#ddDevice').val();
    var deviceName = $('#txtDevice').val();

    if (deviceId === null && deviceName === null) {
        alert("Please choose a device or enter a device name");
        return;
    }
    $('#modal-loader').modal('show');
    $('#modal-register').modal('hide');

    var params = {};
    params.deviceId = deviceId;
    params.deviceName = deviceName;
    params.configId = configId;

    $.ajax({
        url: "visitor/device/register",
        data: params,
        type: 'POST'
    }).done(function (data, status, jqXHR) {
        $('#modal-loader').modal('hide');
        
        if (data === 'SUCCESS') {
            $('#dv-notification').removeAttr("class");
            $('#dv-notification').html("Device details saved successfully");
            $('#dv-notification').addClass('alert alert-success');

            loadDevices();
            loadSessions(0, 10);
        }
        else {
            $('#dv-reg-error').html("Error saving device details. Please try again");
            $('#dv-reg-error').addClass('alert alert-danger');
            
            $('#modal-register').modal('show');
        }
    }).fail(function (jqXHR, status, error) {
        $('#modal-loader').modal('hide');
        
        $('#dv-reg-error').html("Error saving device details. Please try again");
        $('#dv-reg-error').addClass('alert alert-danger');
        
        $('#modal-register').modal('show');
    });
}