/**
 *
 * Created by Kristoffer on 2017-03-02.
 */
(function () {
    angular.module('app').controller('MainController', MainController);

    MainController.$inject = ['$http', 'orderByFilter'];

    function MainController($http, orderBy) {
        var mc = this;

        mc.message = "Hello from MainController";
        mc.value = 2;
        // mc.type = "type_1";
        mc.mainView = '/login';
        mc.currentPlayerID = -1;
        mc.playerAmount = 0;
        mc.gameID = -1;
        mc.rollsRemaining = 3;
        mc.pointsTowardsBonus = 0;

        mc.confirmNumberOfPlayers = confirmNumberOfPlayers;
        mc.addPlayer = addPlayer;
        mc.scoreCombination = scoreCombination;
        mc.diceRoller = diceRoller;
        mc.logGameID = logGameID;
        mc.mainGameLoop = mainGameLoop;
        mc.newGame = newGame;

        mc.options = [{
            value: 1
        },  {
            value: 2
        }, {
            value: 3
        },  {
            value: 4
        }];

        mc.currentPlayer = {
          name:"Current Player",
            id:0,
            score:0
        };

        mc.dice = [{
            id: 1,
            result: 1,
            saved: {"1": false}
        },{
            id: 2,
            result: 1,
            saved: {"2": false}
        },{
            id: 3,
            result: 1,
            saved: {"3": false}
        }, {
            id: 4,
            result: 1,
            saved: {"4": false}
        }, {
            id: 5,
            result: 1,
            saved: {"5": false}
        }];

        mc.players = [];

        init();

        function init() {
            console.log("Main Controller Initialized!");
            mc.selectedOption = mc.options[1];
        }

        function confirmNumberOfPlayers(numberOfPlayers) {
            mc.mainView = '/playerinfo';
            mc.playerAmount = numberOfPlayers;
        }

        function addPlayer(playerName, password) {
            if (mc.currentPlayerID <= mc.playerAmount){

                var player = {
                    name: playerName,
                    password: password
                };

                var onPlayerComplete = function (response) {
                    if (response.data.id != -1){
                        mc.players.push(response.data);
                        mc.currentPlayerID++;
                    }else {
                        console.log("Incorrect Password!")
                    }

                    if ((mc.currentPlayerID +1) >= mc.playerAmount){
                        mc.mainView = '/gameView';
                        mc.logGameID();
                        mc.mainGameLoop();
                    } else {
                        mc.mainView = '/playerinfo';
                    }
                };
                var onPlayerError = function (reason) {
                    console.log(reason.data);
                };
                $http.post("/player/validate", player).then(onPlayerComplete, onPlayerError);
            }
        }

        function logGameID() {
            $http.get("/registergamesession").then(function (response) {

                mc.gameID = response.data;
                console.log("gameID: " + mc.gameID);

                angular.forEach(mc.players, function (player) {
                    player.gameID = mc.gameID;
                })
            });
        }

        function scoreCombination(selectedScore){
            if (selectedScore != null && mc.rollsRemaining < 3){
                mc.currentPlayer.currentRound++;
                selectedScore.scored = true;

                var onScoreComplete = function (response) {
                    selectedScore.score = response.data;
                    mc.currentPlayer.score += selectedScore.score;

                    if (mc.currentPlayerID == mc.playerAmount-1 && mc.currentPlayer.currentRound >= 13){
                        endGame();
                    }
                    mc.currentPlayerID = (mc.currentPlayerID + 1) % (mc.playerAmount);
                    mc.currentPlayer = mc.players[mc.currentPlayerID];

                    calculateBonus(selectedScore);
                };
                var onScoreError = function (reason) {
                    console.log(reason.data);
                };

                mc.currentPlayer.diceString = "";
                angular.forEach(mc.dice, function (die) {
                    mc.currentPlayer.diceString += die.result;
                });

                $http.post("/combination/score/" + selectedScore.id, mc.currentPlayer).then(onScoreComplete, onScoreError);

                mc.rollsRemaining = 3;
                resetDice();
            }
        }

        function calculateBonus(selectedScore) {

            if(selectedScore.id <= 6 && mc.pointsTowardsBonus < 63) {
                mc.pointsTowardsBonus += selectedScore.score;
                console.log("Points towards Bonus is: " + mc.pointsTowardsBonus);

                if (mc.pointsTowardsBonus >= 63){
                    mc.currentPlayer.score += 35;
                    console.log("Bonus Scored!");
                }
            }
        }

        function endGame() {
            /**
             * Sorting the players for the gameLog
             */
            mc.players = orderBy(mc.players, 'score', true);

            angular.forEach(mc.players, function (player) {
                console.log("Player name: " + player.name);
                console.log("Player score: " + player.score);
            });

            function onGameLogComplete(response) {
            }
            function onGameLogError(reason) {
                console.log(reason.data);
            }

            $http.post("/updateGameLog", mc.players).then(onGameLogComplete, onGameLogError);
            mc.mainView = '/gameOver';
        }
        
        function newGame() {
            mc.mainView = '/login';
            mc.players = [];
            mc.currentPlayerID = -1;
        }

        function resetDice() {
            angular.forEach(mc.dice, function (die) {
                die.saved=false;
            })
        }

        function diceRoller(){
            if (mc.rollsRemaining >=3){
                resetDice();
            }
            if (mc.rollsRemaining > 0){
                angular.forEach(mc.dice, function (die) {
                    if (!die.saved[die.id]){
                        die.result = rollDice();
                    }
                });
                mc.rollsRemaining--;
            }
        }

        function rollDice() {
            return Math.floor(Math.random() * 6) + 1;
        }

        function mainGameLoop() {

            mc.currentPlayerID = 0;
            mc.currentPlayer = mc.players[mc.currentPlayerID];

            angular.forEach(mc.players, function (player) {
                player.combinations = [
                    {
                        id: 1,
                        name: "Aces",
                        scored: false
                    },{
                        id: 2,
                        name: "Twos",
                        scored: false
                    },{
                        id: 3,
                        name: "Threes",
                        scored: false
                    },{
                        id: 4,
                        name: "Fours",
                        scored: false
                    },{
                        id: 5,
                        name: "Fives",
                        scored: false
                    },{
                        id: 6,
                        name: "Sixes",
                        scored: false
                    },{
                        id: 7,
                        name: "Three of a kind",
                        scored: false
                    },{
                        id: 8,
                        name: "Four of a kind",
                        scored: false
                    },{
                        id: 9,
                        name: "Full House",
                        scored: false
                    },{
                        id: 10,
                        name: "Small Straight",
                        scored: false
                    },{
                        id: 11,
                        name: "Large Straight",
                        scored: false
                    },{
                        id: 12,
                        name: "Yahtzee",
                        scored: false
                    },{
                        id: 13,
                        name: "Chance",
                        scored: false
                    }
                ];
                player.currentRound = 0;
            });
        }
    }
})();

