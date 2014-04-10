$(document).ready(function() {

	$('#show').click(function() {
		$.ajax({
			url: "api/rest/showall",
			type: "GET",
			success: function(data){
				$("#container").text(" ");
				$.each(data, function() {	
					var li = $("<li>"+this.name+" "+this.city+"</li>");
					$('#container').append(li);
				});
			},
			error: function(data)
			{
				alert(data);
			}
		});
	});
	
	$('#search').click(function() {
		var name =$('#search_name').val();
		$.ajax({
			url: "api/rest/search/" + name,
			type: "GET",
			dataType: 'json',
			success: function(data){
				$("#container").text(" ");
				$.each(data, function() {
					var li = $("<li>"+this.name+" "+this.city+"</li>");
					$('#container').append(li);
				});
			},
			error: function(data)
			{
				alert(data);
			}
		});
		
	});

	$('#add').click(function() {
		var name =$('#name').val();
		var city =$('#city').val();
		$.ajax({
			url: "api/rest/add",
			type: "POST",
			data:"{ \"name\":\"" + name + "\", \"city\":\"" + city + "\" }",
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {  },
			error: function(data) { alert("err: " + data); }
		});
		
	});
	
});
