$.ajax({
           url: location.origin + '/smartcampus-client/chat-properties',
           type: 'GET'
       }).then(function (chatProperties) {
    converse.plugins.add('loginPlugin', {
        overrides: {
            logIn: function () {
                var _converse = this;
                _converse.logOut();
                _converse.__super__.logIn.apply(this, arguments);
            }
        }
    });
    converse.initialize(
        {
            bosh_service_url: chatProperties.bosh_service_url,
            credentials_url: chatProperties.credentials_url,
            muc_domain: chatProperties.muc_domain,
            i18n: currentLocale,
            show_controlbox_by_default: true,
            authentication: 'login',
            keepalive: true,
            allow_logout: false,
            allow_registration: false,
            allow_otr: false,
            auto_login: true,
            roster_groups: true,
            auto_join_rooms: chatProperties.muc_rooms,
            muc_nickname_from_jid: true,
            hide_muc_server: true,
            auto_join_on_invite: true,
            auto_subscribe: true,
            blacklisted_plugins: ['converse-notification'],
            whitelisted_plugins: ['loginPlugin'],
            visible_toolbar_buttons: {
                call: false,
                clear: true,
                emoticons: true,
                toggle_occupants: true
            }
        }
    );
});