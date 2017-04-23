function bindEvents(){
	//$('#login').on('click', loginClick);
	$('#register').click(registerClick);
}

function registerClick(e){
	$('#register-modal').modal('show');
}

function loginClick(e){
	e.preventDefault();
	var username = $('#username');
	var isLoginCorrect = false;
	var isPasswordCorrect = false;
	
	if(username.val().length > 0){
		isLoginCorrect = true;
		username.closest('.form-group').find('.help-block').hide();
	}else{
		username.closest('.form-group').find('.help-block').show();
	}
	
	var password = $('#password');
	if(password.val().length > 0){
		isPasswordCorrect = true;
		password.closest('.form-group').find('.help-block').hide();
	}else{
		password.closest('.form-group').find('.help-block').show();
	}
	
	if(isLoginCorrect && isPasswordCorrect){
		parent.window.location.href = "home.html";
	}
		
}

function addComment(){
	var city = $('#select-city').val();
	var comment = $('#comment').val();
	var temperature = $('#current-temperature').text();
//	getCurrentTemp(city, true);
	
	$.ajax({
		method:"GET",
		url: "PostServlet",
		data:{"city": city, "message": comment, "temp": temperature},
		dataType: "text"
			
	}).done(function(response){
		console.log('success', response)
		var temp = $('#temp').clone();
		temp.css("display", "block");
		
		temp.find('#temp-temperature').text(temperature);
		temp.find('#temp-comment').html(comment);
		temp.find('h4').html(city);
		
		$('#statuses').append(temp);
		
	}).fail(function(){
		
	}).always(function(){
		
	});	
	
	
	
}

function bindSearch(){
	$('#search-friend').on('input', function(){
		getFriends();
	})
}

function getFriends(){
	var friendName = $('#search-friend').val();
	
	$.ajax({
		method:"GET",
		url: "Friends",
		data:{"search-friend": friendName},
		dataType: "text"
			
	}).done(function(response){
		console.log('success', response)	
		var xml = $.parseXML(response)
		xml = $(xml);
		var results = xml.find('friends').first().find('friends')
		
		$('#search-result').html('')
		results.each(function(){
			
			var userName = $(this).find('username').text(),
			email = $(this).find('email').text(),
			id = $(this).find('id').text(),
			password = $(this).find('password')
			
			var template = createTemplate(userName, id);
			$('#search-result').append(template);
			$('#search-result').show()
		})
		
		$('.add-friend').on('click', function(){
			
			var id = $(this).attr('data-id');
			sendFriendRequest(id)
			
			$('#search-result').hide()
			$('#search-friend').val('')
		})
		
		
		
	}).fail(function(){
		
	}).always(function(){
		
	});	

}


function sendFriendRequest(id){
	$.ajax({
		method:"GET",
		url: "FriendRequestServlet",
		data:{"friend-id": id, "message": 'Иска да сте приятели'},
		dataType: "text"
			
	}).done(function(response){
		console.log('success', response)

		
	}).fail(function(){
		
	}).always(function(){
		
	});	
}

function createTemplate(username, id){
	var $template = $('.found-friend').last().clone();
	$template.show();
	$template.find('span').first().html(username);
	$template.find('button').attr('data-id', id);
	return $template;
}

function createRTemplate(username, id, message){
	var $template = $('.template').last().clone();
	$template.show();
	$template.find('h4').html(username);
	$template.find('p').html(message);
	$template.find('.accept-request').attr('data-id', id);
	return $template;
}


function getCurrentTemp(city, add){
	$.ajax({
		method:"GET",
		url: "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",bg&units=metric&appid=891be3d8ea6c5763c5d8e6ad1267c77b",
		dataType: "json"
	}).done(function(response){
		if(add)
			addAndRenderDiv(city, response);
		else
			renderTemperature(response);
	}).fail(function(){
		console.log('Error occured!');
	}).always(function(){
		console.log('Completed!');
	});	
}

function addAndRenderDiv(city, weatherData){
	var weatherCode = weatherData.weather[0].id.toString();
	var currentWeather = getWeatherData(weatherCode);
	
	var comment = $('#comment').val();
	var temp = $('#temp').clone();
	temp.css("display", "block");
	
	temp.find('#temp-temperature').text(weatherData.main.temp);
	temp.find('#temp-comment').html(comment);
	temp.find('h4').html(city);
	temp.find('img').attr('src', 'assets/img/icons/' +
			currentWeather.icon);
	
	$('#statuses').append(temp);
}

function renderTemperature(weatherData){
	$('#current-temperature').text(weatherData.main.temp);
	
	var weatherCode = weatherData.weather[0].id.toString();
	var currentWeather = getWeatherData(weatherCode);
	$('#current-condition').text(currentWeather.condition);
	$('.img-container').find('img').
		attr('src','assets/img/icons/' + currentWeather.icon);
	$('.img-container').find('img').show();
	
}

function getWeatherData(weatherCode){
	var weatherData = {};
	var weatherCondition = '';
	var icon = '';
	
	if(weatherCode.charAt(0) == '2'){
		weatherCondition = 'Гръмотевична буря';
		icon = 'thunderstorm.png';
	}else if(weatherCode.charAt(0) == '3'){
		weatherCondition = 'Преваляване';
		icon = 'rain.png';
	}else if(weatherCode.charAt(0) == '6'){
		weatherCondition = 'Снеговалеж';
		icon = 'snow.png';
	}else if(weatherCode.charAt(0) == '7'){
		weatherCondition = 'Мъгла';
		icon = 'mist.png';
	}else if(weatherCode.charAt(0) == '8'){
		if(weatherCode == 800){
			weatherCondition = 'Слънчево'
			icon = 'clear_sky.png';
		}else{
			weatherCondition = 'Лека Облачност';
			icon = 'few_clouds.png';
		}		
	}else if(weatherCode == 500){
		weatherCondition = 'Слабо преваляване';
		icon = 'shower_rain.png';
	}else if(weatherCode == 501){
		weatherCondition = 'Дъждовно';
		icon = 'rain.png';
	} 
	
	weatherData.condition = weatherCondition;
	weatherData.icon = icon;
	
	return weatherData;
}


function loadInbox(){
	$.ajax({
		method:"GET",
		url: "FriendRequestServlet",
		dataType: "text"
	}).done(function(response){
		
		renderRequests(response);
		
	}).fail(function(){
		console.log('Error occured!');
	}).always(function(){
		console.log('Completed!');
	});	
}

function acceptRequest(id){
	$.ajax({
		method:"GET",
		data:{'friend-id':id},
		url: "AddFriendServlet",
		dataType: "text"
	}).done(function(response){
		
		console.log(response);
		
	}).fail(function(){
		console.log('Error occured!');
	}).always(function(){
		console.log('Completed!');
	});	
}


function renderRequests(response){
	
	var xml = $.parseXML(response)
	xml = $(xml);
	var results = xml.find('friendRequestInformations').first().find('requests')
	
	$('.list-group').html('')
	results.each(function(){
		
		var userName = $(this).find('username').text(),
		id = $(this).find('id').text(),
		message = $(this).find('message')
		
		var template = createRTemplate(userName, id, message);
		$('.list-group').append(template);
		
	})
	
	$('.accept-request').on('click', function(){
		
		var id = $(this).attr('data-id');
		$(this).closest('li').remove();
		acceptRequest(id)
	
	})
	
	
	
}