/**
 * Created by Kristoffer on 2017-02-20.
 */

$(document).ready(function () {

"use strict";

var toggleButton = $("#toggleButton");

toggleButton.on("click", function () {
    resultsList.toggle(500);

    if (toggleButton.text() == "Hide"){
        toggleButton.text("Show");
    } else {
        toggleButton.text("Hide");
    }
});

var listItems = $("header nav li");

listItems.css("font-weight", "bold");
// listItems.filter(":first").css("font-size", "18px");

// results.push(result);

    var resultsList = $("#resultsList");
    resultsList.text("This is from JQuery");

    var mainDisplayView = $("#mainResultsList");
    mainDisplayView.text("This is from JQuery too!");

    $("#mainDisplayForm").on("submit", function () {

        mainDisplayView.text("button is clicked!");
    });


    $("#gitHubSearchForm").on("submit", function () {

        var searchPhrase = $("#searchPhrase").val();
        var useStars = $("#useStars").val();
        console.log("Use Stars? " + useStars);
        var langChoice = $("#langChoice").val();

        if (searchPhrase){

            resultsList.text("Performing Search...");

            var gitHubSearch = "https://api.github.com/search/repositories?q=" + encodeURIComponent(searchPhrase);

            if(langChoice != "All"){
                gitHubSearch += "language:" + encodeURIComponent(langChoice);
            }

            if(useStars){
                gitHubSearch += "&sort=stars"
            }

            // var gitHubSearch = "https://api.github.com/search/repositories?q=jquery+language:javascript&sort=stars";

            $.get(gitHubSearch, function (r) {
                // console.log(r.items.length);
                displayResults(r.items);
            });
        } else{


            $.get("/highscore", function (r) {
                console.log(r.items.length);
                displayHighscore(r.items);
            });
        }
        return false;
    });


function displayHighscore(results) {
    resultsList.empty();

    var j = 1;
    $.each(results, function (i, item) {

        console.log("result: " + item.name);

        var newResult = $("<div class= 'result'>" +
            "<div class='title'>" + item.name + "</div>" +
            "<div>Score: " + item.score + "</div>" +
            "<div>Rank: "+ (j++) + "</div>" +
            "</div>");

        newResult.hover(function () {
            $(this).css("background-color", "lightgray");
        }, function () {
            $(this).css("background-color", "transparent");
        });

        resultsList.append(newResult);
    })
}

function displayResults(results) {

    resultsList.empty();
    $.each(results, function (i, item) {



        var newResult = $("<div class= 'result'>" +
            "<div class='title'>" + item.name + "</div>" +
            "<div>Language: " + item.language + "</div>" +
            "<div>Owner: "+ item.owner.login + "</div>" +
            "</div>");


        newResult.hover(function () {
            $(this).css("background-color", "lightgray");
        }, function () {
            $(this).css("background-color", "transparent");
        });

        resultsList.append(newResult);
    });
}

// Yahtzee Code

    var dice = [{
    id: 1,
        result: 1
    },{
    id: 2,
        result: 1
    },{
    id: 3,
        result: 1
    }, {
    id: 4,
        result: 1
    }, {
    id: 5,
        result: 1
    }];


    var dieResultListDiv = $("#dieResultListDiv");
    dieResultListDiv.text("This is from JQuery");

    $("#yahtzeePlayForm").on("submit", function () {
        dieResultListDiv.empty();
        $.each(dice, function (i, item) {

            var dieSaved =$("input[id=" + (i+1) + "]:checked").map(function() {
                return this.value;
            }).get();
            if(dieSaved != "on"){
                item.result = rollDice();
                console.log("Die Number: " + item.id + ", Result: " + item.result);
            }

            dieResultListDiv.append(item.result + "<br>");
        });
        return false;
    });

function rollDice() {
    return Math.floor(Math.random() * 6) + 1;
}





});