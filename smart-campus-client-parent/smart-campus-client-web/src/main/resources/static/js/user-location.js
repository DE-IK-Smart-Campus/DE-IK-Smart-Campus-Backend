$(document)
		.ready(
				function() {
					getLocation();
					function getLocation() {
						if (navigator.geolocation) {
							navigator.geolocation.getCurrentPosition(
									reportPosition, handleError);
							setTimeout(getLocation, 300000);
						} else {
							console
									.alert("Unsupported browser",
											"Please update your browser to reach full functionality.");
						}
					}
					function reportPosition(position) {
						$.ajax({
							method: 'post',
						    url: location.origin + '/smartcampus-client/userLocation',
						    datatype:'json',
						    contentType: "application/json",
						    data: JSON.stringify({
						    	longitude:position.coords.longitude,
						    	latitude:position.coords.latitude,
						    	accuracy:position.coords.accuracy
						    })
						});

					}
					function handleError(error) {
						switch (error.code) {
						case error.PERMISSION_DENIED:
							alert("You denied permission to read your location. To use the full functionallity of the application please enable it.")
							break;
						}
					}
				});