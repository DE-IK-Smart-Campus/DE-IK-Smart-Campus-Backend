require('../common/commons.js');

describe('Login screen', function () {
    it('title is "SmartCampus"', function () {
        goToRoot();
        expect(browser.getTitle()).toEqual('SmartCampus');
    });

    it('should have a "Login" button', function () {
        goToRoot();
        expect(element(by.css('button')).getText()).toBe('Login');
    });
});