'use strict'

var count = 0;

function setCount(){
	var counter = document.getElementById('counter');
	count++;
	// console.log(count);
	// console.log($('#counter').innerHTML);
	counter.innerHTML = count;
}

function addToCart(elem){
	$(elem).toggleClass("add-done");
	setTimeout(function(){
		$(elem).removeClass("add-done");
	},2000);

	setCount();
	
}

