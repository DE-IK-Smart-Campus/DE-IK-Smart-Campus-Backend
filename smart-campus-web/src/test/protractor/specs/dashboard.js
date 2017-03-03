require("../common/commons.js");

describe('SmartCampus App test', function () {
    it('should have a title', function () {
        browser.get('http://localhost:8080/smartcampus-web/');
        goToRoot();
        expect(browser.getTitle()).toEqual('SmartCampus');
    });
});