console.log('script.js loaded');

window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init() {
	document.getAllButton.lookupAll.addEventListener('click', function(event) {
		event.preventDefault();
		getAllBeers();
	})

	document.addBeer.add.addEventListener('click', function(event) {
		event.preventDefault();
		addNewBeer();
	})

	document.getByDate.lookupByDate.addEventListener('click', function(event) {
		event.preventDefault();
		getByDate();
	})

}

function getAllBeers() {
	var xhr = new XMLHttpRequest();

	xhr.open('GET', 'http://localhost:8083/api/beers', true);
//	xhr.open('GET', 'http://18.218.166.215:8080/Untapped/api/beers', true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			displayBeers(data);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
			var dataDiv = document.getElementById('beerData');
			dataDiv.textContent = '';
			let h1 = document.createElement('h1');
			dataDiv.appendChild(h1);
			h1.textContent = "Beers not found.";
		}
	};

	xhr.send(null);
}

function displayBeers(beers) {
	var dataDiv = document.getElementById('beerData');
	dataDiv.textContent = '';

	let br = document.createElement('br');
	dataDiv.appendChild(br);
	let hr = document.createElement('hr');
	dataDiv.appendChild(hr);
	let table = document.createElement('table');
	dataDiv.appendChild(table);

	beers.forEach(function(beer, index, arr) {
		let tr = document.createElement('tr');
		let td = document.createElement('td');
		td.textContent = beer.name;

		td.addEventListener('click', function(e) {
			displayBeer(beer);
		});
		dataDiv.appendChild(td);
		dataDiv.appendChild(tr);
	})

	let hr1 = document.createElement('hr');
	dataDiv.appendChild(hr1);
}

function addNewBeer() {
	var xhr = new XMLHttpRequest();
	
	xhr.open('POST', 'http://localhost:8083/api/beers', true);
//	xhr.open('POST', 'http://18.218.166.215:8080/Untapped/api/beers', true);

	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				var data = JSON.parse(xhr.responseText);
				console.log(data);
				getAllBeers();
			} else {
				console.log("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};

	let form = document.addBeer;
	var newBeerObject = {
		name : form.name.value,
		description : form.description.value,
		ingredients : form.ingredients.value,
		alcohol : form.alcohol.value,
		alcoholic : form.alcoholic.value,
		containsAlcohol : false,
		calories : form.calories.value,
		volume : form.volume.value,
		active : true,
		user : {
			id : 1
		}
	};

	var userObjectJson = JSON.stringify(newBeerObject);

	xhr.send(userObjectJson);

}

function displayBeer(beer) {

	var dataDiv = document.getElementById('oneBeerData');
	dataDiv.textContent = '';

	let h1 = document.createElement('h1');
	dataDiv.appendChild(h1);
	h1.textContent = beer.name;

	let descriptionP = document.createElement('p');
	dataDiv.appendChild(descriptionP);
	descriptionP.textContent = beer.description;

	let ul = document.createElement('ul');
	let li = document.createElement('li');
	li.textContent = "Ingredients: " + beer.ingredients;
	let li1 = document.createElement('li');
	li1.textContent = "Alcohol: " + beer.alcohol + " mg";
	let li2 = document.createElement('li');
	li2.textContent = "Calories: " + beer.calories;
	let li3 = document.createElement('li');
	li3.textContent = "Volume: " + beer.volume + " ounces";
	let li4 = document.createElement('li');
	li4.textContent = "Date: " + beer.createdAt;

	dataDiv.appendChild(li);
	dataDiv.appendChild(li1);
	dataDiv.appendChild(li2);
	dataDiv.appendChild(li3);
	dataDiv.appendChild(li4);
	dataDiv.appendChild(ul);

	// this creates a button
	let editButton = document.createElement('button');
	editButton.innerHTML = "Edit Beer";
	dataDiv.appendChild(editButton);

	// this adds functionality to the button
	editButton.addEventListener('click', function(e) {
		e.preventDefault();
		showUpdateForm(beer);
	});

	let hr = document.createElement('hr');
	dataDiv.appendChild(hr);

}

function showUpdateForm(beer) {

	var dataDiv = document.getElementById('editBeer');
	dataDiv.textContent = '';

	var form = document.createElement('form');
	form.name = "editForm";
	form.id = "editForm";
	dataDiv.appendChild(form);

	let nameText = document.createElement('lable');
	nameText.textContent = 'Name: '
	form.appendChild(nameText);

	var inputId = document.createElement('input');
	inputId.type = 'hidden';
	inputId.name = 'id';
	inputId.value = beer.id;
	form.appendChild(inputId);

	var inputName = document.createElement('input');
	inputName.type = 'text';
	inputName.name = 'name';
	inputName.value = beer.name;
	form.appendChild(inputName);

	let br = document.createElement('br');
	form.appendChild(br);

	var nameDescription = document.createElement('lable');
	nameDescription.textContent = 'Description: '
	form.appendChild(nameDescription);

	let inputDescription = document.createElement('input');
	inputDescription.type = 'text';
	inputDescription.name = 'description';
	inputDescription.value = beer.description;
	form.appendChild(inputDescription);

	let br1 = document.createElement('br');
	form.appendChild(br1);

	let nameIngredients = document.createElement('lable');
	nameIngredients.textContent = 'Ingredients: '
	form.appendChild(nameIngredients);

	let inputIngredients = document.createElement('input');
	inputIngredients.type = 'text';
	inputIngredients.name = 'ingredients';
	inputIngredients.value = beer.ingredients;
	form.appendChild(inputIngredients);

	let br2 = document.createElement('br');
	form.appendChild(br2);

	let inputAlcoholic = document.createElement('input');
	inputAlcoholic.type = 'hidden';
	inputAlcoholic.name = 'alcoholic';
	inputAlcoholic.value = beer.alcoholic;
	form.appendChild(inputAlcoholic);

	let nameAlcohol = document.createElement('lable');
	nameAlcohol.textContent = 'Alcohol: '
	form.appendChild(nameAlcohol);

	let inputAlcohol = document.createElement('input');
	inputAlcohol.type = 'number';
	inputAlcohol.name = 'alcohol';
	inputAlcohol.value = beer.alcohol;
	form.appendChild(inputAlcohol);

	let br5 = document.createElement('br');
	form.appendChild(br5);

	let nameCalories = document.createElement('lable');
	nameCalories.textContent = 'Calories: '
	form.appendChild(nameCalories);

	let inputCalories = document.createElement('input');
	inputCalories.type = 'number';
	inputCalories.name = 'calories';
	inputCalories.value = beer.alcohol;
	form.appendChild(inputCalories);

	let br6 = document.createElement('br');
	form.appendChild(br6);

	let nameVolume = document.createElement('lable');
	nameVolume.textContent = 'Volume: '
	form.appendChild(nameVolume);

	let inputVolume = document.createElement('input');
	inputVolume.type = 'number';
	inputVolume.name = 'volume';
	inputVolume.value = beer.alcohol;
	form.appendChild(inputVolume);

	let editButton = document.createElement('button');
	editButton.innerHTML = "Submit Edited Beer";
	dataDiv.appendChild(editButton);

	let editBeer = document.getElementById('editForm');

	editButton.addEventListener('click', function(e) {
		e.preventDefault();

		let form = document.getElementById('editForm');

		let editedBeer = {
			id : form.id.value,
			name : form.name.value,
			description : form.description.value,
			ingredients : form.ingredients.value,
			alcohol : form.alcohol.value,
			alcoholic : form.alcoholic.value,
			containsAlcohol : false,
			calories : form.calories.value,
			volume : form.volume.value,
			active : true,
			user : {
				id : 1,
				firstName : "Johnny",
				lastName : "Bobbert",
				createdAt : "2019-01-10T07:00:00.000+0000"
			}
		};

		updateBeer(editedBeer);
	});

	let br7 = document.createElement('br');
	dataDiv.appendChild(br7);
	let br8 = document.createElement('br');
	dataDiv.appendChild(br8);

	let deleteButton = document.createElement('button');
	deleteButton.innerHTML = "Delete this Beer";
	dataDiv.appendChild(deleteButton);

	deleteButton.addEventListener('click', function(e) {
		e.preventDefault();
		deleteBeer(beer);
	});

	let hr = document.createElement('hr');
	dataDiv.appendChild(hr);
}

function updateBeer(beer) {

	var xhr = new XMLHttpRequest();
	
	xhr.open('PUT', 'http://localhost:8083/api/beers/' + beer.id, true);
//	xhr.open('PUT', 'http://18.218.166.215:8080/Untapped/api/beers/' + beer.id, true);

	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201 || xhr.status == 202) { // Ok,
				// Created,
				// or
				// Accepted
				var data = JSON.parse(xhr.responseText);
				console.log(data);
			} else {
				console.log("PUT request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};

	var userObjectJson = JSON.stringify(bev);

	xhr.send(userObjectJson);

	var editDiv = document.getElementById('editBeer');
	editDiv.textContent = '';
	var oneBeerDiv = document.getElementById('oneBeerData');
	oneBeerDiv.textContent = '';
	var beersDiv = document.getElementById('beerData');
	beersDiv.textContent = '';
	var output = document.getElementById('beerDataDate');
	output.textContent = '';
}

function deleteBeer(beer) {

	var xhr = new XMLHttpRequest();
	
	xhr.open('DELETE', 'http://localhost:8083/api/beers/' + beer.id, true);
//	xhr.open('DELETE', 'http://18.218.166.215:8080/Untapped/api/beers/' + beer.id, true);

	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status != 204) { // !No Content
				console.log("DELETE request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};

	var userObjectJson = JSON.stringify(bev);

	xhr.send(userObjectJson);

	var editDiv = document.getElementById('editBeer');
	editDiv.textContent = '';
	var oneBeerDiv = document.getElementById('oneBeerData');
	oneBeerDiv.textContent = '';
	var beersDiv = document.getElementById('beerData');
	beersDiv.textContent = '';
	var output = document.getElementById('beerDataDate');
	output.textContent = '';
}

function getByDate() {
	var xhr = new XMLHttpRequest();

	let beerDate = document.getElementById('getByDate')

	xhr.open('GET', 'http://localhost:8083/api/beers/date/'
			+ beerDate.year.value + '/' + beerDate.month.value + '/'
			+ beerDate.day.value, true);
//	xhr.open('GET', 'http://3.132.229.160:8080/EventTracker/api/beers/date/'
//			+ beerDate.year.value + '/' + beerDate.month.value + '/'
//			+ beerDate.day.value, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status == 200) {
			var data = JSON.parse(xhr.responseText);
			displayBeersDay(data);
		}

		if (xhr.readyState === 4 && xhr.status >= 204) {
			console.error(xhr.status + ': ' + xhr.responseText);
			var dataDiv = document.getElementById('beerDataDate');
			dataDiv.textContent = '';
			let br = document.createElement('br');
			dataDiv.appendChild(br);
			let hr = document.createElement('hr');
			dataDiv.appendChild(hr);
			let h2 = document.createElement('h2');
			dataDiv.appendChild(h2);
			h2.textContent = "No beers found for: " + beerDate.year.value
					+ '-' + beerDate.month.value + '-' + beerDate.day.value;
		}
	};

	xhr.send(null);
}

function displayBeersDay(data) {
	var sumVolume = 0;
	var sumAlcohol = 0;
	var sumCalories = 0;
	var count = 0;

	data.forEach(function(beer, index, arr) {
		sumAlcohol += beer.alcohol;
		sumVolume += beer.volume;
		sumCalories += beer.calories;
		count++;
	});

	var output = document.getElementById('beerDataDate');
	output.textContent = '';

	let br = document.createElement('br');
	output.appendChild(br);
	let hr = document.createElement('hr');
	output.appendChild(hr);
	let h2 = document.createElement('h2');
	h2.textContent = 'Totals for ' + data[0].createdAt;
	output.appendChild(h2);

	let ul = document.createElement('ul');
	output.appendChild(ul);
	let li = document.createElement('li');
	output.appendChild(li);
	li.textContent = 'Alcohol: ' + sumAlcohol + ' mg';
	let li1 = document.createElement('li');
	output.appendChild(li1);
	li1.textContent = 'Volume: ' + sumVolume + ' ounces';
	let li2 = document.createElement('li');
	output.appendChild(li2);
	li2.textContent = 'Calories: ' + sumCalories;
	let li3 = document.createElement('li');
	output.appendChild(li3);
	li3.textContent = 'Beer count: ' + count;

}