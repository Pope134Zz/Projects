package application;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application { 

    public int playerPos[][] = new int[10][10]; //10 by 10 board
    public int sAndLPos[][] = new int[4][3]; //4 snakes 4 ladders

    public int rand; 
    public Label randResult; 
    public Label gameResult;
    public Label Status;

    public static final int tileSize = 80;
    public static final int width = 10;
    public static final int height = 10;

   
    public Circle player1; 
    public Circle player2;
    public Circle player3;
    public Circle player4;

    // player positions
    public int player1Position = 1;
    public int player2Position = 1;
    public int player3Position = 1;
    public int player4Position = 1;
    // boolean variables for every player turn at the start of the game
    public boolean player1Turn = true;
    public boolean player2Turn = true;
    public boolean player3Turn = true;
    public boolean player4Turn = true;
// boolean variables for cookies for all the players at the start of the game, meaning no player has cookies from the beginning
    public boolean biskuitplayer1 = false;
    public boolean biskuitplayer2 = false;
    public boolean biskuitplayer3 = false;
    public boolean biskuitplayer4 = false;

    //start of starting positions(square 1)
    public int player1XPos = 40;
    public int player1YPos = 760;

    public int player1NewXPos = 0;
    public int player1NewYPos = 0;

    public int player2XPos = 40;
    public int player2YPos = 760;

    public int player2NewXPos = 0;
    public int player2NewYPos = 0;

    public int player3XPos = 40;
    public int player3YPos = 760;

    public int player3NewXPos = 0;
    public int player3NewYPos = 0;

    public int player4XPos = 40;
    public int player4YPos = 760;

    public int player4NewXPos = 0;
    public int player4NewYPos = 0;

    public int posCir1 = 1;
    public int posCir2 = 1;
    public int posCir3 = 1;
    public int posCir4 = 1;
    //end of starting positions (square 1)
   
    //button game button needs to be pressed in order for the game to start
    public boolean gameStart = false;
    public Button gameButton;

    private Group titleGroup = new Group();

    private Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(width * tileSize, (height * tileSize) + 80);
        root.getChildren().addAll(titleGroup);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                titleGroup.getChildren().add(tile);

            }

        }
//start code players design circle 
        player1 = new Circle(10); 
        player1.setId("Player 1 (GREEN)"); 
        player1.setFill(Color.GREEN);
        player1.getStyleClass().add("Style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(10);
        player2.setId("Player 2 (RED)");
        player2.setFill(Color.RED);
        player2.getStyleClass().add("Style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        player3 = new Circle(10);
        player3.setId("Player 3 (BLUE)");
        player3.setFill(Color.BLUE);
        player3.getStyleClass().add("Style.css");
        player3.setTranslateX(player3XPos);
        player3.setTranslateY(player3YPos);

        player4 = new Circle(10);
        player4.setId("Player 4 (PINK)");
        player4.setFill(Color.HOTPINK);
        player4.getStyleClass().add("Style.css");
        player4.setTranslateX(player4XPos);
        player4.setTranslateY(player4YPos);
      //end code players design circle 

        Button button1 = new Button("Player 1 Roll");//button created for player 1
        button1.setTranslateX(20);//x position of the button on the main tab
        button1.setTranslateY(820);//y position of the button on the main tab 
        button1.setOnAction(new EventHandler<ActionEvent>() { //button's function that trigger when button pressed
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player1Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player1Position += rand;
                        movePlayer1();
                        translatePlayer(player1XPos, player1YPos, player1);

                        player1Turn = false;
                        player2Turn = true;
                        player3Turn = false;
                        player4Turn = false;
                        if (gameStart)
                            gameResult.setText("Player 2 turn");

                    }
                }
            }
        });


        Button button2 = new Button("Player 2 Roll"); //button created for player 2
        button2.setTranslateX(110);//x position of the button on the main tab
        button2.setTranslateY(820); //y position of the button on the main tab
        button2.setOnAction(new EventHandler<ActionEvent>() {//button's function that trigger when button pressed
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player2Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player2Position += rand;
                        movePlayer2();
                        translatePlayer(player2XPos, player2YPos, player2);

                        player2Turn = false;
                        player1Turn = false;
                        player3Turn = true;
                        player4Turn = false;
                        if (gameStart)
                            gameResult.setText("Player 3 turn");

                    }
                }
            }
        });
        Button button3 = new Button("Player 3 Roll"); //button created for player 3
        button3.setTranslateX(20);//x position of the button on the main tab
        button3.setTranslateY(850);//y position of the button on the main tab
        button3.setOnAction(new EventHandler<ActionEvent>() {//button's function that trigger when button pressed
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player3Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player3Position += rand;
                        movePlayer3();
                        translatePlayer(player3XPos, player3YPos, player3);

                        player3Turn = false;
                        player2Turn = false;
                        player1Turn = false;
                        player4Turn = true;
                        if (gameStart)
                            gameResult.setText("Player 4 turn");

                    }
                }
            }
        });

        Button button4 = new Button("Player 4 Roll"); //button created for player 4
        button4.setTranslateX(110);//x position of the button on the main tab
        button4.setTranslateY(850);//y position of the button on the main tab
        button4.setOnAction(new EventHandler<ActionEvent>() { //button's function that trigger when button pressed
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player4Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player4Position += rand;
                        movePlayer4();
                        translatePlayer(player4XPos, player4YPos, player4);

                        player3Turn = false;
                        player2Turn = false;
                        player1Turn = true;
                        player4Turn = false;
                        if (gameStart)
                            gameResult.setText("Player 1 turn");

                    }
                }
            }
        });


        gameButton = new Button("Press to start");// new button called "Press to start" has been created in order to allow the players to start playing the game.
        gameButton.setTranslateX(350); //button x coordinates
        gameButton.setTranslateY(820); //button y coordinates

        gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!gameStart) {
                    gameStart = true;
                    randResult.setText("Dice Result");
                    randResult.setTranslateX(700);
                    gameButton.setText("Game has started");
                    player1XPos = 40;  //Square 1 x coordinates
                    player1YPos = 760; //Square 1 y coordinates 

                    player2XPos = 40;//Square 1 x coordinates
                    player2YPos = 760;//Square 1 y coordinates 

                    player3XPos = 40;//Square 1 x coordinates
                    player3YPos = 760;//Square 1 y coordinates 

                    player4XPos = 40;//Square 1 x coordinates
                    player4YPos = 760;//Square 1 y coordinates 


                    player1Position = 1; //square position 1 (start)
                    player2Position = 1; //square position 1 (start)
                    player3Position = 1; //square position 1 (start)
                    player4Position = 1; //square position 1 (start)

                    posCir1 = 1; //position of the circle (player) at the start of the game
                    posCir2 = 1; //position of the circle (player) at the start of the game
                    posCir3 = 1; //position of the circle (player) at the start of the game
                    posCir4 = 1; //position of the circle (player) at the start of the game

                    player1.setTranslateX(player1XPos); 
                    player1.setTranslateY(player1YPos);
                    player2.setTranslateX(player2XPos);
                    player2.setTranslateY(player2YPos);
                    player3.setTranslateX(player3XPos);
                    player3.setTranslateY(player3YPos);
                    player4.setTranslateX(player4XPos);
                    player4.setTranslateY(player4YPos);

                    rand = (int) (Math.random() * 2 + 1);
                    if (rand == 1) {
                        player1Turn = true;
                        gameResult.setText("Player One Turn");
                    } else {
                        player2Turn = true;
                        gameResult.setText("Player Two Turn");

                        player3Turn = true;
                        gameResult.setText("Player three Turn");

                        player4Turn = true;
                        gameResult.setText("Player four Turn");
                    }


                }

            }
        });

        randResult = new Label("Dice Result");
        randResult.setTranslateX(700);
        randResult.setTranslateY(830);

        gameResult = new Label("Game Result");
        gameResult.setTranslateX(490);
        gameResult.setTranslateY(830);

        Status = new Label("Status:");
        Status.setTranslateX(220);
        Status.setTranslateY(835);

        Image img = new Image("PICBO.png");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);


        titleGroup.getChildren().addAll(imageView, player1, player2, player3, player4, button1, button2, button3, button4, gameButton, randResult, gameResult, Status);

        return root;

    }

    public void getDiceValue() { 
        rand = rand = (int) (Math.random() * 6 + 1);
        ;
    }

    public void movePlayer1() { 

        if (player1XPos == 40 && player1YPos == 40) { //default position
            player1XPos = 40;
            player1YPos = 40;
        }

        for (int i = 0; i < rand; i++) { //for loop function based on the rand(dice number rolled) that will move the player from the square one to the finish (100th square)
            if (posCir1 % 2 == 1) {
                player1XPos += 80;
            }
            if (posCir1 % 2 == 0) {
                player1XPos -= 80;
            }
            if (player1XPos > 760) {
                player1YPos -= 80;
                player1XPos -= 80;
                posCir1++;
            }
            if (player1XPos < 40) {
                player1YPos -= 80;
                player1XPos += 80;
                posCir1++;
            }

            if (player1XPos < 30 || player1YPos < 30) {
                player1XPos = 40;
                player1YPos = 40;
                gameResult.setTranslateX(530);
                gameResult.setText("Player One Won");
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }

        moveNewPlayer1();
    }

    // New positions of player1 for Snakes and Ladders
    public void moveNewPlayer1() {
//Ladder code begin
        if (player1Position == 4) { // bottom of the ladder
            player1XPos = 280;
            player1YPos = 280; // position where the circle will be after going up the ladder
            posCir1 += 6; // how many rows it goes up from the bottom of the ladder
            player1Position = 64; // final square number of the circle after going up


        }
        if (player1Position == 8) {  // bottom of the ladder
            player1XPos = 680;
            player1YPos = 520; // position where the circle will be after going up the ladder
            posCir1 += 3; // how many rows it goes up from1 the bottom of the ladder
            player1Position = 32; // final square number of the circle after going up
        }
        if (player1Position == 59) { // bottom of the ladder
            player1XPos = 40;
            player1YPos = 120; // position where the circle will be after going up the ladder
            posCir1 += 3;// how many rows it goes up from the bottom of the ladder
            player1Position = 81;// final square number of the circle after going up

        }
        //Ladder code end


        // Biscuit positions
        if (player1Position == 9) { //position of the biscuit
            biskuitplayer1 = true;
            Status.setText("Biscuit! YAY!");
        }


        if (player1Position == 40) {  //position of the biscuit
            biskuitplayer1 = true;
            Status.setText("Biscuit! YAY!");
        }



            // end of biscuit positions

//SNAKES
        //FIRST SNAKE
        if (player1Position == 35) { // head of the snake
            if (biskuitplayer1 == true) { //player will use the biscuit to feed the snake
                player1XPos = 440;
                player1YPos = 520;
                posCir1 += 0;  // goes down the snake because he has no biscuit
                player1Position = 35;// doesn't go down any rows
                Status.setText("Player1 fed the snake!");//text display to show communication between user and players
                biskuitplayer1 = false; // the player will use the biscuit if he has it

            } else { // if player have the biscuit he will use it to feed the snake
                    player1XPos = 360;
                    player1YPos = 760;
                    posCir1 -= 3; // player will feed the snake and stay in the same position
                    player1Position = 5; // final position after going down the snake
                    Status.setText("No biscuit! Player 1 go down the snake.");
            }
        }
            //SECOND SNAKE}
        if (player1Position == 58) {// head of the snake
            if (biskuitplayer1 = true) {//player will use the biscuit to feed the snake
                player1XPos = 200;
                player1YPos = 360;
                posCir1 += 0;// doesn't go down any rows
                player1Position = 58;// player 1 position when he meets the snake
                Status.setText("Player 1 fed the snake!"); //text display to show communication between user and players
                biskuitplayer1 = false;// the player will use the biscuit if he has it

            } else { // if player doesn't have the biscuit he will go down the snake's tail
                player1XPos = 200;
                player1YPos = 680; // position where the circle will be after going down to the snake's tail
                posCir1 -= 4; // how many rows it goes down from the top of the snake to the end of the tail
                player1Position = 18; // final position after going down to the tail of the snake
                Status.setText("No biscuit! Player1 go down the snake.");
            }
        }

        //THIRD SNAKE
        if (player1Position == 85) {// head of the snake
            if (biskuitplayer1 = true) { //player will use the biscuit to feed the snake
                player1Position = 85; // player 1 position when he meets the snake
                player1XPos = 360;
                player1YPos = 120;
                posCir1 += 0;  // doesn't go down any rows
                Status.setText("Player1 fed the snake!");//text display to show communication between user and players
                biskuitplayer1 = false; // the player will use the biscuit if he has it

            } else {// if player doesn't have the biscuit he will go down the snake's tail
                player1XPos = 600;
                player1YPos = 680;// position where the circle will be after going down to the snake's tail
                posCir1 -= 7;// how many rows it goes down from the top of the snake to the end of the tail
                player1Position = 13;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player1 go down the snake.");
            }
        }

            //FOURTH SNAKE

            if (player1Position == 89) {// head of the snake
                if (biskuitplayer1 = true) { //player will use the biscuit to feed the snake
                    player1XPos = 680;
                    player1YPos = 120;
                    posCir1 += 0;  // doesn't go down any rows
                    player1Position = 89; // player 1 position when he meets the snake
                    Status.setText("Player1 fed the snake!");//text display to show communication between user and players
                    biskuitplayer1 = false; // the player will use the biscuit if he has it

                } else {
                    player1XPos = 760;
                    player1YPos = 520;// position where the circle will be after going down to the snake's tail
                    posCir1 -= 5;// how many rows it goes down from the top of the snake to the end of the tail
                    player1Position = 31;// final position after going down to the tail of the snake
                    Status.setText("No biscuit! Player1 go down the snake.");

                }
            }


                    if (player1Position == 100) {
                        player1XPos = 40;
                        player1YPos = 40;
                        posCir1 = 10;
                        player1Position = 100;
                    }

                    player1Turn = false;
                    player2Turn = true;
                    player3Turn = false;
                    player4Turn = false;
                    if (gameStart)
                        gameResult.setText("Player1 Two turn");
                }



    public void movePlayer2() {

        if (player2XPos == 40 && player2YPos == 40) { //default position
            player2XPos = 40;
            player2YPos = 40;
        }

        for (int i = 0; i < rand; i++) { //for loop function based on the rand(dice number rolled) that will move the player from the square one to the finish (100th square)
            if (posCir2 % 2 == 1) {
                player2XPos += 80;
            }
            if (posCir2 % 2 == 0) {
                player2XPos -= 80;
            }
            if (player2XPos > 760) {
                player2YPos -= 80;
                player2XPos -= 80;
                posCir2++;
            }
            if (player2XPos < 40) {
                player2YPos -= 80;
                player2XPos += 80;
                posCir2++;
            }

            if (player2XPos < 30 || player2YPos < 30 || player2Position == 100) {
                player2XPos = 40;
                player2YPos = 40;
                gameResult.setTranslateX(530);
                gameResult.setText("Player Two Won");
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer2();
    }

    // New positions of player2 for Snakes and Ladders
    public void moveNewPlayer2() {

        // Ladder code START
        if (player2Position == 4) { // bottom of the ladder
            player2XPos = 280;
            player2YPos = 280; // position where the circle will be after going up the ladder
            posCir2 += 6; // how many rows it goes up from the bottom of the ladder
            player2Position = 64; // final square number of the circle after going up
        }
        if (player2Position == 8) {  // bottom of the ladder
            player2XPos = 680;
            player2YPos = 520; // position where the circle will be after going up the ladder
            posCir2 += 3; // how many rows it goes up from1 the bottom of the ladder
            player2Position = 32; // final square number of the circle after going up
        }
        if (player2Position == 59) { // bottom of the ladder
            player2XPos = 40;
            player2YPos = 120; // position where the circle will be after going up the ladder
            posCir2 += 3;// how many rows it goes up from the bottom of the ladder
            player2Position = 81;// final square number of the circle after going up

        }
        //Ladder code END

        // Biscuit positions  code start
        if (player2Position == 9) { //position of the biscuit
            biskuitplayer2 = true;
            Status.setText("Biscuit! YAY!");
        }


        if (player2Position == 40) {  //position of the biscuit
            biskuitplayer2 = true;
            Status.setText("Biscuit! YAY!");
        }

        // Biscuit positions code end


        //SNAKES
        //FIRST SNAKE
        if (player2Position == 35) { // head of the snake
            if (biskuitplayer2 == true) { //player will use the biscuit to feed the snake
                player2XPos = 440;
                player2YPos = 520;
                posCir2 += 0;  // goes down the snake because he has no biscuit
                player2Position = 35;// doesn't go down any rows
                Status.setText("Player2 fed the snake!");//text display to show communication between user and players
                biskuitplayer2 = false; // the player will use the biscuit if he has it

            } else { // if player have the biscuit he will use it to feed the snake
                player2XPos = 360;
                player2YPos = 760;
                posCir2 -= 3; // player will feed the snake and stay in the same position
                player2Position = 5; // final position after going down the snake
                Status.setText("No biscuit! Player 2 go down the snake.");
            }
        }
        //SECOND SNAKE
        if (player2Position == 58) {// head of the snake
            if (biskuitplayer2 = true) {//player will use the biscuit to feed the snake
                player2XPos = 200;
                player2YPos = 360;
                posCir2 += 0;// doesn't go down any rows
                player2Position = 58;// player 1 position when he meets the snake
                Status.setText("Player 2 fed the snake!"); //text display to show communication between user and players
                biskuitplayer2 = false;// the player will use the biscuit if he has it

            } else { // if player doesn't have the biscuit he will go down the snake's tail
                player2XPos = 200;
                player2YPos = 680; // position where the circle will be after going down to the snake's tail
                posCir2 -= 4; // how many rows it goes down from the top of the snake to the end of the tail
                player2Position = 18; // final position after going down to the tail of the snake
                Status.setText("No biscuit! Player2 go down the snake.");
            }
        }

        //THIRD SNAKE
        if (player2Position == 85) {// head of the snake
            if (biskuitplayer2 = true) { //player will use the biscuit to feed the snake
                player2Position = 85; // player 1 position when he meets the snake
                player2XPos = 360;
                player2YPos = 120;
                posCir2 += 0;  // doesn't go down any rows
                Status.setText("Player2 fed the snake!");//text display to show communication between user and players
                biskuitplayer2 = false; // the player will use the biscuit if he has it

            } else {// if player doesn't have the biscuit he will go down the snake's tail
                player2XPos = 600;
                player2YPos = 680;// position where the circle will be after going down to the snake's tail
                posCir2 -= 7;// how many rows it goes down from the top of the snake to the end of the tail
                player2Position = 13;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player2 go down the snake.");
            }
        }

        //FOURTH SNAKE

        if (player2Position == 89) {// head of the snake
            if (biskuitplayer2 = true) { //player will use the biscuit to feed the snake
                player2XPos = 680;
                player2YPos = 120;
                posCir2 += 0;  // doesn't go down any rows
                player2Position = 89; // player 1 position when he meets the snake
                Status.setText("Player2 fed the snake!");//text display to show communication between user and players
                biskuitplayer2 = false; // the player will use the biscuit if he has it

            } else {
                player2XPos = 760;
                player2YPos = 520;// position where the circle will be after going down to the snake's tail
                posCir2 -= 5;// how many rows it goes down from the top of the snake to the end of the tail
                player2Position = 31;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player2 go down the snake.");

            }
        }

        if(player2Position >= 100) {
            player2XPos = 40;
            player2YPos = 40;
            posCir2 = 10;
            player2Position = 100;
        }

        player2Turn = false;
        player1Turn = false;
        player3Turn = true;
        player4Turn = false;


        if(gameStart)
            gameResult.setText("Player Three turn");
    }



    public void movePlayer3() {

        if (player3XPos == 40 && player3YPos == 40) { //default position
            player3XPos = 40;
            player3YPos = 40;
        }

        for (int i = 0; i < rand; i++) { //for loop function based on the rand(dice number rolled) that will move the player from the square one to the finish (100th square)
            if (posCir3 % 2 == 1) {
                player3XPos += 80;
            }
            if (posCir3 % 2 == 0) {
                player3XPos -= 80;
            }
            if (player3XPos > 760) {
                player3YPos -= 80;
                player3XPos -= 80;
                posCir3++;
            }
            if (player3XPos < 40) {
                player3YPos -= 80;
                player3XPos += 80;
                posCir3++;
            }

            if (player3XPos < 30 || player3YPos < 30 || player3Position == 100) {
                player3XPos = 40;
                player3YPos = 40;
                gameResult.setTranslateX(530);
                gameResult.setText("Player Three Won");
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer3();
    }

    public void moveNewPlayer3(){
        //Ladder code START
        if(player3Position == 4){ // bottom of the ladder
            player3XPos = 280; player3YPos = 280; // position where the circle will be after going up the ladder
            posCir3 += 6; // how many rows it goes up from the bottom of the ladder
            player3Position = 64; // final square number of the circle after going up
        }
        if(player3Position == 8){  // bottom of the ladder
            player3XPos =680; player3YPos = 520; // position where the circle will be after going up the ladder
            posCir3 += 3; // how many rows it goes up from1 the bottom of the ladder
            player3Position = 32; // final square number of the circle after going up
        }
        if(player3Position == 59){ // bottom of the ladder
            player3XPos = 40; player3YPos = 120; // position where the circle will be after going up the ladder
            posCir3 += 3;// how many rows it goes up from the bottom of the ladder
            player3Position = 81;// final square number of the circle after going up

        }
        //Ladder code END

        // Biscuit positions
        if (player3Position == 9) { //position of the biscuit
            biskuitplayer3 = true;
            Status.setText("Biscuit! YAY!");
        }


        if (player4Position == 40) {  //position of the biscuit
            biskuitplayer3= true;
            Status.setText("Biscuit! YAY!");
        }

        // end of biscuit positions


        // SNAKES
        //FIRST SNAKE
        if (player3Position == 35) { // head of the snake
            if (biskuitplayer3 == true) { //player will use the biscuit to feed the snake
                player3XPos = 440;
                player3YPos = 520;
                posCir3 += 0;  // goes down the snake because he has no biscuit
                player3Position = 35;// doesn't go down any rows
                Status.setText("Player3 fed the snake!");//text display to show communication between user and players
                biskuitplayer3 = false; // the player will use the biscuit if he has it

            } else { // if player have the biscuit he will use it to feed the snake
                player3XPos = 360;
                player3YPos = 760;
                posCir3 -= 3; // player will feed the snake and stay in the same position
                player3Position = 5; // final position after going down the snake
                Status.setText("No biscuit! Player 3 go down the snake.");
            }
        }

        //SECOND SNAKE}
        if (player3Position == 58) {// head of the snake
            if (biskuitplayer3 = true) {//player will use the biscuit to feed the snake
                player3XPos = 200;
                player3YPos = 360;
                posCir3 += 0;// doesn't go down any rows
                player3Position = 58;// player 1 position when he meets the snake
                Status.setText("Player 3 fed the snake!"); //text display to show communication between user and players
                biskuitplayer3 = false;// the player will use the biscuit if he has it

            } else { // if player doesn't have the biscuit he will go down the snake's tail
                player3XPos = 200;
                player3YPos = 680; // position where the circle will be after going down to the snake's tail
                posCir3 -= 4; // how many rows it goes down from the top of the snake to the end of the tail
                player3Position = 18; // final position after going down to the tail of the snake
                Status.setText("No biscuit! Player3 go down the snake.");
            }
        }

        //THIRD SNAKE
        if (player3Position == 85) {// head of the snake
            if (biskuitplayer3 = true) { //player will use the biscuit to feed the snake
                player3Position = 85; // player 1 position when he meets the snake
                player3XPos = 360;
                player3YPos = 120;
                posCir3 += 0;  // doesn't go down any rows
                Status.setText("Player3 fed the snake!");//text display to show communication between user and players
                biskuitplayer3 = false; // the player will use the biscuit if he has it

            } else {// if player doesn't have the biscuit he will go down the snake's tail
                player3XPos = 600;
                player3YPos = 680;// position where the circle will be after going down to the snake's tail
                posCir3 -= 7;// how many rows it goes down from the top of the snake to the end of the tail
                player3Position = 13;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player3 go down the snake.");
            }
        }

        //FOURTH SNAKE

        if (player3Position == 89) {// head of the snake
            if (biskuitplayer3 = true) { //player will use the biscuit to feed the snake
                player3XPos = 680;
                player3YPos = 120;
                posCir3 += 0;  // doesn't go down any rows
                player3Position = 89; // player 1 position when he meets the snake
                Status.setText("Player3 fed the snake!");//text display to show communication between user and players
                biskuitplayer3 = false; // the player will use the biscuit if he has it

            } else {
                player3XPos = 760;
                player3YPos = 520;// position where the circle will be after going down to the snake's tail
                posCir3 -= 5;// how many rows it goes down from the top of the snake to the end of the tail
                player3Position = 31;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player3 go down the snake.");

            }


    }
        if(player3Position >= 100) {
            player3XPos = 40;
            player3YPos = 40;
            posCir3 = 10;
            player3Position = 100;
        }

        player1Turn = false;
        player2Turn = false;
        player3Turn = false;
        player4Turn = true;


        if(gameStart)
            gameResult.setText("Player Four turn");
    }

    public void movePlayer4() { 

        if (player4XPos == 40 && player4YPos == 40) { //default position
            player4XPos = 40;
            player4YPos = 40;
        }

        for (int i = 0; i < rand; i++) { //for loop function based on the rand(dice number rolled) that will move the player from the square one to the finish (100th square)
            if (posCir4 % 2 == 1) { 
                player4XPos += 80;
            }
            if (posCir4 % 2 == 0) {
                player4XPos -= 80;
            }
            if (player4XPos > 760) {
                player4YPos -= 80;
                player4XPos -= 80;
                posCir4++;
            }
            if (player4XPos < 40) {
                player4YPos -= 80;
                player4XPos += 80;
                posCir4++;
            }

            if (player4XPos < 30 || player4YPos < 30 || player4Position == 100) {
                player4XPos = 40;
                player4YPos = 40;
                gameResult.setTranslateX(530);
                gameResult.setText("Player Four Won");
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer4();
    }

    public void moveNewPlayer4() {

        //Ladder code START
        if (player4Position == 4) { // bottom of the ladder
            player4XPos = 280;
            player4YPos = 280; // position where the circle will be after going up the ladder
            posCir4 += 6; // how many rows it goes up from the bottom of the ladder
            player4Position = 64; // final square number of the circle after going up
        }
        if (player4Position == 8) {  // bottom of the ladder
            player4XPos = 680;
            player4YPos = 520; // position where the circle will be after going up the ladder
            posCir4 += 3; // how many rows it goes up from1 the bottom of the ladder
            player4Position = 32; // final square number of the circle after going up
        }
        if (player4Position == 59) { // bottom of the ladder
            player4XPos = 40;
            player4YPos = 120; // position where the circle will be after going up the ladder
            posCir4 += 3;// how many rows it goes up from the bottom of the ladder
            player4Position = 81;// final square number of the circle after going up

        }
        //Ladder code END

        // Biscuit positions
        if (player1Position == 9) { //position of the biscuit
            biskuitplayer1 = true;
            Status.setText("Biscuit! YAY!");
        }


        if (player1Position == 40) {  //position of the biscuit
            biskuitplayer1 = true;
            Status.setText("Biscuit! YAY!");
        }

        // end of biscuit positions

        //SNAKES

        //FIRST SNAKE
        if (player4Position == 35) { // head of the snake
            if (biskuitplayer4 == true) { //player will use the biscuit to feed the snake
                player4XPos = 440;
                player4YPos = 520;
                posCir4 += 0;  // goes down the snake because he has no biscuit
                player4Position = 35;// doesn't go down any rows
                Status.setText("Player4 fed the snake!");//text display to show communication between user and players
                biskuitplayer4 = false; // the player will use the biscuit if he has it

            } else { // if player have the biscuit he will use it to feed the snake
                player4XPos = 360;
                player4YPos = 760;
                posCir4 -= 3; // player will feed the snake and stay in the same position
                player4Position = 5; // final position after going down the snake
                Status.setText("No biscuit! Player 4 go down the snake.");
            }
        }
        //SECOND SNAKE}
        if (player4Position == 58) {// head of the snake
            if (biskuitplayer4 = true) {//player will use the biscuit to feed the snake
                player4XPos = 200;
                player4YPos = 360;
                posCir4 += 0;// doesn't go down any rows
                player4Position = 58;// player 1 position when he meets the snake
                Status.setText("Player 4 fed the snake!"); //text display to show communication between user and players
                biskuitplayer4 = false;// the player will use the biscuit if he has it

            } else { // if player doesn't have the biscuit he will go down the snake's tail
                player4XPos = 200;
                player4YPos = 680; // position where the circle will be after going down to the snake's tail
                posCir4 -= 4; // how many rows it goes down from the top of the snake to the end of the tail
                player4Position = 18; // final position after going down to the tail of the snake
                Status.setText("No biscuit! Player4 go down the snake.");
            }
        }

        //THIRD SNAKE
        if (player4Position == 85) {// head of the snake
            if (biskuitplayer4 = true) { //player will use the biscuit to feed the snake
                player4Position = 85; // player 1 position when he meets the snake
                player4XPos = 360;
                player4YPos = 120;
                posCir4 += 0;  // doesn't go down any rows
                Status.setText("Player4 fed the snake!");//text display to show communication between user and players
                biskuitplayer4 = false; // the player will use the biscuit if he has it

            } else {// if player doesn't have the biscuit he will go down the snake's tail
                player4XPos = 600;
                player4YPos = 680;// position where the circle will be after going down to the snake's tail
                posCir4 -= 7;// how many rows it goes down from the top of the snake to the end of the tail
                player4Position = 13;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player4 go down the snake.");
            }
        }

        //FOURTH SNAKE

        if (player4Position == 89) {// head of the snake
            if (biskuitplayer4 = true) { //player will use the biscuit to feed the snake
                player4XPos = 680;
                player4YPos = 120;
                posCir4 += 0;  // doesn't go down any rows
                player4Position = 89; // player 1 position when he meets the snake
                Status.setText("Player4 fed the snake!");//text display to show communication between user and players
                biskuitplayer4 = false; // the player will use the biscuit if he has it

            } else {
                player4XPos = 760;
                player4YPos = 520;// position where the circle will be after going down to the snake's tail
                posCir4 -= 5;// how many rows it goes down from the top of the snake to the end of the tail
                player4Position = 31;// final position after going down to the tail of the snake
                Status.setText("No biscuit! Player4 go down the snake.");

            }

        player1Turn = true;
        player2Turn = false;
        player3Turn = false;
        player4Turn = false;


        }
        if(gameStart)
            gameResult.setText("Player One turn");
    }

    public void translatePlayer(int x, int y, Circle b){

        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b); //player movement's animation time in milliseconds
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{ //java fx function that creates the board game 

        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Snakes & Ladders "); //title of the game 
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
