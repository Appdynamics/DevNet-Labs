var testindex = 0;
var loadInProgress = false;//This is set to true when a page is still loading
 
/*********SETTINGS*********************/
var webPage = require('webpage');
var page = webPage.create();
page.settings.userAgent = 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36';
page.settings.javascriptEnabled = true;
page.settings.loadImages = true;//Script is much faster with this field set to false
phantom.cookiesEnabled = true;
phantom.javascriptEnabled = true;
/*********SETTINGS END*****************/
 
console.log('All settings loaded, start with execution');
page.onConsoleMessage = function(msg) {
    console.log(msg);
};
/**********DEFINE STEPS THAT FANTOM SHOULD DO***********************/
steps = [
 
	//Step 1 - Open home page
    function(){
        console.log('Step 1 - Open home page');
        page.open("http://localhost:8080/Supercar-Trader/home.do", function(status){
			
		});
    },
	//Step 2 - Get a screenshot
    function(){
		console.log("Step 2 - Get a screen capture");
		page.render('home-01.png');
    },
    //Step 3 - Get a second screenshot
    function(){
		console.log("Step 3 - Get a second screen capture");
		page.render('home-01.png');
    },


];
/**********END STEPS THAT FANTOM SHOULD DO***********************/
 
//Execute steps one by one
interval = setInterval(executeRequestsStepByStep,12000);
 
function executeRequestsStepByStep(){
    if (loadInProgress == false && typeof steps[testindex] == "function") {
        //console.log("step " + (testindex + 1));
        steps[testindex]();
        testindex++;
    }
    if (typeof steps[testindex] != "function") {
        console.log("test complete!");
        page.close();
        phantom.exit();
    }
}
 
/**
 * These listeners are very important in order to phantom work properly. Using these listeners, we control loadInProgress marker which controls, weather a page is fully loaded.
 * Without this, we will get content of the page, even a page is not fully loaded.
 */
page.onLoadStarted = function() {
    loadInProgress = true;
    console.log('Loading started');
};

page.onLoadFinished = function() {
    loadInProgress = false;
    console.log('Loading finished');
};

page.onConsoleMessage = function(msg) {
    console.log(msg);
};