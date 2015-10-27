var qrscanner = Class(function () {

    // this.showAdView = function (opts) {
        // NATIVE.plugins.sendEvent("QRScanner", "showAdView", JSON.stringify(opts));
    // }

    this.scanBarCode = function () {
        NATIVE.plugins.sendEvent("QRScanner", "scanBarCode", JSON.stringify({}));
    }

    this.scanQRCode = function () {
        NATIVE.plugins.sendEvent("QRScanner", "scanQRCode", JSON.stringify({}));
    }
});

exports = new qrscanner();
