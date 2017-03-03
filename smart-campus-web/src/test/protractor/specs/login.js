require('../common/commons.js');

describe('Login screen', function () {
    it('title is "SmartCampus"', function () {
        goToRoot();
        expect(browser.getTitle()).toEqual('SmartCampus');
    });

    it('should have two input fields', function () {
        expect(element.all(by.css('input')).count()).toBe(2);
    });

    it('should have a "Login" button', function () {
        expect(element(by.css('button')).getText()).toBe('Login');
    });
});