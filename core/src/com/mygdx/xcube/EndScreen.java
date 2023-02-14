package com.mygdx.xcube;


import com.badlogic.gdx.utils.Array;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;
import com.mygdx.xcube.Terrain;
import com.mygdx.xcube.PlayerManager;
public class EndScreen {
    private Array<HollowSquare> square;
    //private Terrain terrain;
    private PlayerManager players;

    public EndScreen(Terrain terrain, PlayerManager players){
        this.square = terrain.getSquare();
        this.players = players;
    }

    public void winTest(){
        if(players.getPlayer()==true){
            if(square.get(12).status==true && square.get(13).status==true && square.get(14).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(13).status==true && square.get(14).status==true && square.get(15).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(8).status==true && square.get(9).status==true && square.get(10).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(9).status==true && square.get(10).status==true && square.get(11).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(4).status==true && square.get(5).status==true && square.get(6).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(5).status==true && square.get(6).status==true && square.get(7).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(0).status==true && square.get(1).status==true && square.get(2).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(1).status==true && square.get(2).status==true && square.get(3).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(12).status==true && square.get(8).status==true && square.get(4).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(8).status==true && square.get(4).status==true && square.get(0).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(13).status==true && square.get(9).status==true && square.get(5).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(9).status==true && square.get(5).status==true && square.get(1).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(14).status==true && square.get(10).status==true && square.get(6).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(10).status==true && square.get(6).status==true && square.get(2).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(15).status==true && square.get(11).status==true && square.get(7).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(11).status==true && square.get(7).status==true && square.get(3).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(14).status==true && square.get(9).status==true && square.get(4).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(15).status==true && square.get(10).status==true && square.get(15).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(10).status==true && square.get(5).status==true && square.get(0).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(11).status==true && square.get(6).status==true && square.get(1).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(8).status==true && square.get(5).status==true && square.get(2).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(12).status==true && square.get(9).status==true && square.get(6).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(9).status==true && square.get(6).status==true && square.get(3).status==true){
                System.out.println("Blue wins");
            }
            if(square.get(13).status==true && square.get(10).status==true && square.get(7).status==true){
                System.out.println("Blue wins");
            }
        }
        else{
            if(square.get(12).status==false && square.get(13).status==false && square.get(14).status==false){
                System.out.println("Red wins");
            }
            if(square.get(13).status==false && square.get(14).status==false && square.get(15).status==false){
                System.out.println("Red wins");
            }
            if(square.get(8).status==false && square.get(9).status==false && square.get(10).status==false){
                System.out.println("Red wins");
            }
            if(square.get(9).status==false && square.get(10).status==false && square.get(11).status==false){
                System.out.println("Red wins");
            }
            if(square.get(4).status==false && square.get(5).status==false && square.get(6).status==false){
                System.out.println("Red wins");
            }
            if(square.get(5).status==false && square.get(6).status==false && square.get(7).status==false){
                System.out.println("Red wins");
            }
            if(square.get(0).status==false && square.get(1).status==false && square.get(2).status==false){
                System.out.println("Red wins");
            }
            if(square.get(1).status==false && square.get(2).status==false && square.get(3).status==false){
                System.out.println("Red wins");
            }
            if(square.get(12).status==false && square.get(8).status==false && square.get(4).status==false){
                System.out.println("Red wins");
            }
            if(square.get(8).status==false && square.get(4).status==false && square.get(0).status==false){
                System.out.println("Red wins");
            }
            if(square.get(13).status==false && square.get(9).status==false && square.get(5).status==false){
                System.out.println("Red wins");
            }
            if(square.get(9).status==false && square.get(5).status==false && square.get(1).status==false){
                System.out.println("Red wins");
            }
            if(square.get(14).status==false && square.get(10).status==false && square.get(6).status==false){
                System.out.println("Red wins");
            }
            if(square.get(10).status==false && square.get(6).status==false && square.get(2).status==false){
                System.out.println("Red wins");
            }
            if(square.get(15).status==false && square.get(11).status==false && square.get(7).status==false){
                System.out.println("Red wins");
            }
            if(square.get(11).status==false && square.get(7).status==false && square.get(3).status==false){
                System.out.println("Red wins");
            }
            if(square.get(14).status==false && square.get(9).status==false && square.get(4).status==false){
                System.out.println("Red wins");
            }
            if(square.get(15).status==false && square.get(10).status==false && square.get(15).status==false){
                System.out.println("Red wins");
            }
            if(square.get(10).status==false && square.get(5).status==false && square.get(0).status==false){
                System.out.println("Red wins");
            }
            if(square.get(11).status==false && square.get(6).status==false && square.get(1).status==false){
                System.out.println("Red wins");
            }
            if(square.get(8).status==false && square.get(5).status==false && square.get(2).status==false){
                System.out.println("Red wins");
            }
            if(square.get(12).status==false && square.get(9).status==false && square.get(6).status==false){
                System.out.println("Red wins");
            }
            if(square.get(9).status==false && square.get(6).status==false && square.get(3).status==false){
                System.out.println("Red wins");
            }
            if(square.get(13).status==false && square.get(10).status==false && square.get(7).status==false){
                System.out.println("Red wins");
            }
        }
    }


}

