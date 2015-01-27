/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function signup(){
    var fp = prepareFingerprint();
    
    if($('#signup-name').val() === "" || $('#signup-email').val() === ""){
        $('#dv-signup-err').html("Please fill in both your name and email address.");
        $('#dv-signup-err').addClass('alert alert-danger');
        return;
    }
    
    if(fp === null){
        $('#dv-signup-err').html("Sorry, there was an error signing you up. Please try again.");
        $('#dv-signup-err').addClass('alert alert-danger');
        return;
    }
    
    mask("Creating your account. Please wait...");
    
    $.ajax({
        url: "signup",
        data: {
            'fingerprint': JSON.stringify(fp),
            'name': $('#signup-name').val(),
            'username': $('#signup-email').val(),
            'password': $('#signup-pass').val()
        },
        type: 'POST'
    }).done(function(data){
        mask();
        
        if(data === "SUCCESS")
            window.location.href = "visitor";
        else{
            $('#dv-signup-err').html(data);
            $('#dv-signup-err').addClass('alert alert-danger');
        }
    })
    .fail(function(){
        mask();
        $('#dv-signup-err').html("Sorry, there was an error signing you up. Please try again.");
    });
}

function login(){
    var fp = prepareFingerprint();
    
    if($('#login-pass').val() === "" || $('#login-email').val() === ""){
        $('#dv-login-err').html("Please fill in both your email address and visitor number.");
        $('#dv-login-err').addClass('alert alert-danger');
        return;
    }
    
    if(fp === null){
        $('#dv-login-err').html("Sorry, there was an error logging you in. Please try again.");
        $('#dv-login-err').addClass('alert alert-danger');
        return;
    }
    
    mask("Verifying your details. Please wait...");
    
    $.ajax({
        url: "login",
        data: {
            'fingerprint': JSON.stringify(fp),
            'password': $('#login-pass').val(),
            'username': $('#login-email').val()
        },
        type: 'POST'
    }).done(function(data){
        mask();
        
        if(data === "SUCCESS")
            window.location.href = "visitor";
        else{
            $('#dv-login-err').html(data);
            $('#dv-login-err').addClass('alert alert-danger');
        }
    }).fail(function(){
        mask();
        $('#dv-login-err').html("Sorry, there was an error logging you in. Please try again.");        
        $('#dv-login-err').addClass('alert alert-danger');
    });
}

function prepareFingerprint(){
    mask("Generating fingerprint. Please wait...");
    
    var fp = new Object();
    
    fp.appCodeName = window.navigator.appCodeName;
    fp.appName = fingerprint_truebrowser();
    fp.version = window.navigator.appVersion;
    fp.cookies = window.navigator.cookieEnabled;
    fp.dnt = (window.navigator.doNotTrack === "unspecified") ? false : window.navigator.doNotTrack;
    fp.language = window.navigator.language;
    fp.browserJavaEnabled = window.navigator.javaEnabled();
    fp.browserArchitecture = window.navigator.platform;
    fp.browserBuild = window.navigator.buildID;
    fp.browserProduct = window.navigator.product;
    fp.browserProductSub = window.navigator.productSub;
    fp.browserVendor = window.navigator.vendor;
    fp.browserVendorSub = window.navigator.vendorSub;
    fp.userAgent = window.navigator.userAgent;  
    fp.windowHeight = window.outerHeight;
    fp.windowWidth = window.outerWidth;
    
    fp.pixelDepth = window.screen.pixelDepth;    
    fp.mimeTypes = "";
    
    if(window.performance.memory)
        fp.heapSize = window.performance.memory.jsHeapSize;
    if(window.navigator.plugins){
        for(var i = 0; i < window.navigator.mimeTypes.length; i++){
            fp.mimeTypes += window.navigator.mimeTypes[i].type + "|";
        }
    }
        
    fp.browserPlugins = "";
    if(window.navigator.plugins){
        for(var i = 0; i < window.navigator.plugins.length; i++){
            fp.browserPlugins += window.navigator.plugins[i].name + "|";
        }
    }
    
    fp.os = fingerprint_os();
    fp.fonts = fingerprint_fonts();
    fp.canvas = fingerprint_canvas();
    
    mask();
    
    return fp;
}

function mask(msg){
    $('#mask-msg').html(msg);
    $('#modal-loader').modal('show');
}

function unmask(){
    $('#modal-loader').modal('hide');
}