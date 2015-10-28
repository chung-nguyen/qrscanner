import util.setProperty as setProperty;

var onScanResult;

var qrscanner = Class(function () {

	this.setOnScanResult = function(f) {
		onScanResult = f;
	};
	
	NATIVE.events.registerHandler("ScanResult", function(evt) {
      logger.log("{qrscanner} got scan result");
      if (typeof onScanResult === "function") {
        onScanResult(evt.contents, evt.format);
      }
    });

    // this.showAdView = function (opts) {
        // NATIVE.plugins.sendEvent("QRScanner", "showAdView", JSON.stringify(opts));
    // }

    this.scanBarCode = function () {
        NATIVE.plugins.sendEvent("QRScannerPlugin", "scanBarCode", JSON.stringify({}));
    }

    this.scanQRCode = function () {
        NATIVE.plugins.sendEvent("QRScannerPlugin", "scanQRCode", JSON.stringify({}));
    }
});

exports = new qrscanner();
