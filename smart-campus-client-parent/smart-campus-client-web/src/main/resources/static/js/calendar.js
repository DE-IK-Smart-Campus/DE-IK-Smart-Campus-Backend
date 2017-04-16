$.ajax({
           url: location.origin + '/smartcampus-client/calendar/events',
           type: 'GET'
       }).then(function (subjectEvents) {
           console.log(subjectEvents);
    $('#calendar').fullCalendar({
                                    header: {
                                        left: 'title',
                                        center: '',
                                        right: 'month agendaWeek agendaDay today prev,next'
                                    },
                                    events: subjectEvents,
                                    firstDay: 1,
                                    locale: currentLocale
                                })
});