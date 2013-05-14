var AppCommon = function () {

};

AppCommon.prototype.updateValues = function (json) {
    
    alert(json);


};
alert("Hallo");
cordova.addConstructor(function() {
    cordova.addPlugin("appcommon", new AppCommon());
});



