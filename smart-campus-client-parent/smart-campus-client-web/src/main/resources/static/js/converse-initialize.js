converse.initialize(
    {
        bosh_service_url: 'http://' + location.hostname + ':5280/http-bind',
        credentials_url: 'http://' + location.hostname + ':8080/smartcampus-client/xmppcredentials',
        muc_domain:'conference.smartcampus',
        show_controlbox_by_default: true,
        authentication: 'login',
        keepalive: true,
        allow_logout: false,
        allow_registration: false,
        auto_login: true,
        i18n: 'hu',
        roster_groups:true,
        muc_nickname_from_jid:true,
        hide_muc_server:true,
        auto_subscribe:true,
        auto_join_on_invite:true,
        visible_toolbar_buttons: {
            call: false,
            clear: true,
            emoticons: false,
            toggle_occupants: true
        }
    }
);