require('../common/commons.js');

describe('Dashboard screen', function () {
    it('title is "SmartCampus"', function () {
        goToRoot();
        login();
        expect(browser.getTitle()).toEqual('SmartCampus');
    });

    it('sidebar should have a "Logout" button', function () {
        expect(element(by.css('.sidebar a.btn')).getText()).toBe('Logout');
    });
});