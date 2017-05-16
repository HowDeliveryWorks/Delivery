'use strict'

var count = 0;

function showDescription(elem) {
    $(elem).parent().children(".description").toggle(500);
    $(elem).toggleClass('close-ingr');
}

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



function calcPrice(bun,meat) {
	return 50 + bun + meat;
}

var customBurger = {
    roasting: "Medium Rare",
        bread: {
		name: "White Bun",
		price: 10
	},
	meat:{
		name: "Beef",
		price: 60
	},
	sauces: [
        {name:"Ketchup",price:5}
	],
	spicy: false,
	price: 0,
	calc: function () {
		var sum = 100 + this.bread.price + this.meat.price;
		for(var i=0;i<this.sauces.length;i++){
			var sum = sum + this.sauces[i].price;
		}
		this.price = sum;
		return sum;
    }
}

function showArgs(burger) {
	for(var i=1; i<arguments.length; i++){
		console.log(arguments[i]);
	}
}

function fullinfo(panel,name,price,elem) {

    if (panel == "bread") {
        customBurger.bread.price = +price;
        customBurger.bread.name = name;
        $('#customBun span').text(name);
    }
    if(panel=="meat"){
        customBurger.meat.price = +price;
        customBurger.meat.name = name;
        $('#customMeat span').text(name);
    }
    if(panel=="roasting"){
        customBurger.roasting = name;
        $('#customRoasting span').text(name);
    }
    if(panel=="sause"){

        if(elem.classList.contains('active')){
            for (var i = 0; i < customBurger.sauces.length; i++) {
            	if(customBurger.sauces[i].name == name) customBurger.sauces.splice(i,1);
            }
        }
        else{
			var newSause = {"name":name, "price":+price};
			customBurger.sauces.push(newSause);
        }

        $('#customSauces ul').text('');

        if(customBurger.sauces.length==0) $('#customSauces ul').append("<span>No sauces chosen</span>");
        else{
            for (var i = 0; i < customBurger.sauces.length; i++) {
                $('#customSauces ul').append("<li>"+customBurger.sauces[i].name+"</li>");
            }
		}
    }
    if(panel=="spice"){
    	if(elem.classList.contains('active')){
    		customBurger.spicy = false;
		}
		else {
    		customBurger.spicy = true;
		}
	}

	console.log(customBurger.spicy);
	$('#customPrice').text(customBurger.calc() + " UAH");
}

$(document).ready(function() {

	var div = $('#bread .item-list').find("label");
	$(div[0]).addClass('active');

    var div1 = $('#meat .item-list').find("label");
    $(div1[1]).addClass('active');

    var div2 = $('#roasting .item-list').find("label");
    $(div2[1]).addClass('active');

    var div3 = $('#sauce .item-list').find("label");
    $(div3[4]).addClass('active');

	fullinfo();

});

function addjson() {
    // document.getElementById('addToCartId').value = customBurger.toJSON();
    document.getElementById('addToCartId').value = JSON.stringify(customBurger);
    console.log(document.getElementById('addToCartId').value);
    // console.log(typeof document.getElementById('addToCartId').value);
}

function hideDescription(elem) {
    if($(window).width()<750){
        $(elem).hide(500);
        $(elem).parent().children(".burgerinfo").removeClass('close-ingr');
    }
}