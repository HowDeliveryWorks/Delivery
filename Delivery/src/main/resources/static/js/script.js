'use strict';

let currEnabledRoasting = "Medium Rare";

let customBurger = {
    roasting: "Medium Rare",
    bread: {
        name: "White Bun",
        price: 40
    },
    meat: {
        name: "Beef",
        price: 60
    },
    sauces: [
        {name: "Ketchup", price: 5}
    ],
    misk: [],
    spicy: false,
    price: 0,
    calc: function () {
        let sum = 30 + this.bread.price + this.meat.price;
        const newarray = [this.sauces,this.misk].reduce((a, b ) => (a.concat(b)));
        newarray.map((elem) => sum += elem.price);
        this.price = sum;
        return sum;
    },
    checktype: function(type) { return this[type]; }
};


function showDescription(elem) {
    $(elem).parent().children(".description").slideToggle(500);
    $(elem).toggleClass('close-ingr');
}


function addToCart(elem) {
    $(elem).toggleClass("add-done");
    setTimeout(function () {
        $(elem).removeClass("add-done");
    }, 2000);
}



function showOrderDone() {
    $('#orderDone').addClass('show');
}

$(document).ready(function () {

    $("#signup-form").submit(function(event) {
        event.preventDefault();

    });

    $('.account .mobile-login').on('click', function () {
        $('.account .movebox .signup').fadeOut();
        $('.account .movebox .login').css("visibility", "visible");
        $('.account .movebox .login').fadeIn();

    });

    $('.account .mobile-register').on('click', function () {
        $('.account .movebox .login').fadeOut();
        $('.account .movebox .signup').css("visibility", "visible");
        $('.account .movebox .signup').fadeIn();

    });

    $('.btn-account').on('click', function () {
        if($( this ).has('signin')){console.log('success');$('#background').fadeIn(300);}
        else{console.log('not success');}
    });

    $('.add a').on('click', function(event){

        let count;
        event.preventDefault();
        $.get($(this).attr('href'), function(data){
            var $result = $(data).find('#itemsNum');
            $('#itemsNum').text($result.text());
        });
    });

    let div = $('#bread .item-list').find("label");
    $(div[0]).addClass('active');

    let div1 = $('#meat .item-list').find("label");
    $(div1[1]).addClass('active');

    let div2 = $('#roasting .item-list').find("label");
    $(div2[1]).addClass('active');

    let div3 = $('#sauce .item-list').find("label");
    $(div3[4]).addClass('active');

    $('#customPrice').text(customBurger.calc() + " UAH");
    let windowHeight = $(document).height();
    let windowWidth = $(document).width();
    $('#orderDone').height(windowHeight);
    $('#orderDone').width(windowWidth);
});

function addjson() {
    document.getElementById('addToCartId').value = JSON.stringify(customBurger);
    console.log(document.getElementById('addToCartId').value);
}

// function hideDescription(elem) {
//     if ($(window).width() < 750) {
//         $(elem).hide(500);
//         $(elem).parent().children(".burgerinfo").removeClass('close-ingr');
//     }
// }

function meatEdit(roasting, name, price) {
    console.log(roasting);
    if (roasting === 'true') {
        $('#roastingTab').addClass('hide');
        $('#customRoasting').addClass('hide');
        currEnabledRoasting = customBurger.roasting;
        customBurger.roasting = "None";
    }
    else {
        $('#roastingTab').removeClass('hide');
        $('#customRoasting').removeClass('hide');
        customBurger.roasting = currEnabledRoasting;
    }

    customBurger.meat.price = +price;
    customBurger.meat.name = name;
    $('#customMeat span').text(name);
    $('#customPrice').text(customBurger.calc() + " UAH");

}


function loadstars() {
    let form = $('.stars').children('form');
    for (let i = 0; i < form.length; i++) {
        let currStars = $(form[i]).attr('id');
        currStars = currStars.slice(-4);
        currStars = currStars.replace(/\D+/g, "");
        let allStars = $(form[i]).children("label");

        for (let j = 0; j < currStars; j++) {
            $(allStars[4 - j]).addClass('checked');
        }
    }
}

function findNumber(str) {
    return str.replace(/\D+/g, "");
}

function checkSingle(type, name, price) {
    const ingr = customBurger.checktype(type);
    ingr.price = +price;
    ingr.name = name;
    const str = upperFirst(type);
    $(`#custom${str} span`).text(name);
    $('#customPrice').text(customBurger.calc() + " UAH");
}

function checkMultiple(type, name, price, elem) {
    let ingr = customBurger.checktype(type);
    let str = $(`#custom${upperFirst(type)} span`);
    str.text('');

    if (elem.classList.contains('active')) {
        for (let i = 0; i < ingr.length; i++) {
            if (ingr[i].name === name) ingr.splice(i, 1);
        }
    }
    else { let newElem = { "name": name, "price": +price }; ingr.push(newElem); }

    if (ingr.length === 0) str.text("No items chosen");
    else str.text(ingr.map((elem) => (elem.name)).join(", "));
    $('#customPrice').text(customBurger.calc() + " UAH");
}

function checkSpicy() {
    customBurger.spicy = !$('.spiciness button').hasClass('active');
}

function upperFirst(str){
    return str.charAt(0).toUpperCase() + str.slice(1);
}



