$(document).ready(function() {

	$('#show').click(function() {
		$.ajax({
			url: "api/index/showall",
			type: "GET",
			success: function(data){
				alert("asdf");
				$.each(data, function() {
					var li = $("<li>"+this.getName()+" "+this.getCity()+"</li>");
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
			url: "api/index/search/name",
			type: "GET",
			data:{
				'name' : name
			},
			dataType: 'json',
			success: function(data){
				alert("asdf");
				var li = $("<li>"+data.name+" "+data.city+"</li>");
				$("#container").append(li);
			}
		});
		
	});

	$('#add').click(function() {
		var name =$('#name').val();
		var city =$('#city').val();
		$.ajax({
			url: "api/index/add",
			type: "POST",
			data:{
				'name' : name,
				'city' : city
			},
			dataType: 'json',
			success: function() { alert("adsf"); }
		});
		
	});
	
});
