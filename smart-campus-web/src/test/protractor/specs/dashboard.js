require('../common/commons.js');

describe('Dashboard screen', function () {
    it('title is "SmartCampus"', function () {
        goToRoot();
        login();
        expect(browser.getTitle()).toEqual('SmartCampus');
    });
});