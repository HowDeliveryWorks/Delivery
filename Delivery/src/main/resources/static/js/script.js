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


function incrItem(elem,price){
	var curr = $(elem).prev().text();
    console.log(curr);
	curr = +curr;

	console.log(curr++);
    $(elem).parent().next().children(".span-price").text((curr*price));
    $(elem).prev().text(curr++);


}

function decrItem(elem,price){
    var curr = $(elem).next().text();
    console.log(curr);
    curr = +curr;
    if(curr>1){
        curr = curr-1;
    	$(elem).next().text(curr);
    }
    $(elem).parent().next().children(".span-price").text(curr*price);

}


function showOrderDone() {
	$('#orderDone').addClass('show');
}