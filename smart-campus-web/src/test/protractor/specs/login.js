require('../common/commons.js');

describe('Login screen', function () {
    it('title is "SmartCampus"', function () {
        goToRoot();
        expect(browser.getTitle()).toEqual('SmartCampus');
    });
});