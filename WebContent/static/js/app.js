
$("#show_plays_btn").click(
		function() {
			console.log("Success!");
			$.ajax({
				method : 'GET',
				url : "/PA3/rest/plays/all",
				success : function(data,status,xhr) {
					console.log("Success!");
			    	var line_split = data.split("\|");
			    	var show_plays = "<h4>All Plays</h4>";
			    	for(i = 0; i < line_split.length; i++) {
			    		var name = line_split[i];
			    		show_plays += "<a href=\"http://localhost:8080/PA3/rest/plays/" + name + "\">" + name + "</a> <br>";
			    	}
			    	$("#contentHeader").html(show_plays);
				},
				error : function(data,status,xhr) {
					console.log("Failed");
					console.log(data);
				}
			});
		});	

