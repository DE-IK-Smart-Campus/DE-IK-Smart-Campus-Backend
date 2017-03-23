var ROOT_PATH = 'http://localhost:8080/smartcampus-web/';

goToRoot = function () {
    browser.get(ROOT_PATH);
};

login = function () {
    // TODO actual authentication with test credentials
    element(by.css('button')).click();
};