$.ajax({
           url: location.origin + '/smartcampus-client/chat-properties',
           type: 'GET'
       }).then(function (chatProperties) {
    converse.initialize(
        {
            bosh_service_url: chatProperties.bosh_service_url,
            credentials_url: chatProperties.credentials_url,
            muc_domain: chatProperties.muc_domain,
            show_controlbox_by_default: true,
            authentication: 'login',
            keepalive: true,
            allow_logout: false,
            allow_registration: false,
            allow_otr: false,
            auto_login: true,
            i18n: 'hu',
            roster_groups: true,
            muc_nickname_from_jid: true,
            hide_muc_server: true,
            auto_join_on_invite: true,
            blacklisted_plugins: ['converse-notification'],
            visible_toolbar_buttons: {
                call: false,
                clear: true,
                emoticons: true,
                toggle_occupants: true
            }
        }
    );
});