package com.mygdx.xcube;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.xcube.block.Items;
import com.mygdx.xcube.block.TerrainBlock;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Terrain {
    private final Array<HollowBar> bar;
    private final Array<HollowSquare> square;
    private final int spaceBlock;
    private final int unitX;
    private final int unitY;
    private final int unitSquare;
    private final int originX;
    private final int originY;
    private Array<TerrainBlock> lastPlay;
    private Array<TerrainBlock> allPlay;
    private Array<TerrainBlock> canPlay;
    private Array<Items> bullet;

    private int iterator = 0;
    private final Random random = new Random();

    public Terrain() {
        this.unitSquare = new HollowSquare(0,0).getSize()[0];
        this.unitX = new HollowBar(false,0,0).getSize()[0];
        this.unitY = new HollowBar(false,0,0).getSize()[1];
        this.spaceBlock=unitX + unitY;
        this.originX = 3*unitY/2;
        this.originY = (9*unitY + 9*unitX)/2;
        this.bar = generateBar();           // Stock la liste des barres
        this.square=generateSquare();       // Stock la liste de carrés
        this.lastPlay=new Array<>();
        this.allPlay=new Array<>();
        this.canPlay=new Array<>();
        canPlay.addAll(bar);
        canPlay.addAll(square);
        this.bullet=new Array<>();
    }
    public Array<TerrainBlock> getLastPlay() {
        return lastPlay;
    }
    public void addPlay(TerrainBlock block) {
        iterator++;
        if(iterator>allPlay.size) {
            allPlay.add(block);
        }
        else {
            allPlay.set(iterator-1,block);
        }
    }
    public Array<HollowBar> getBar() {
        return bar;
    }                //Permet d'appeler les barres
    public Array<HollowSquare> getSquare() {
        return square;
    }       // Permet d'appeler les carrés
    public Array<TerrainBlock> getCanPlay() {
        return canPlay;
    }       // Permet d'appeler les carrés
    public Array<Items> getBullet() {
        return bullet;
    }       // Permet d'appeler les carrés
    public Array<HollowBar>  generateBar() { //Génère les coordonnées des barres à placer sur le terrain

        Array<HollowBar> bar = new Array<>();
        int x = originX + unitX;
        int y = originY;

        for(int i=0; i<5; i++){
            for(int k=0; k<4; k++ ){
                HollowBar b= new HollowBar(true,x,y);
                x += spaceBlock;
                bar.add(b);
            }
            x = originX + unitX;
            y += spaceBlock;

        }

        x = originX;
        y = originY + unitX;

        for(int i=0; i<4; i++) {
            for (int k = 0; k < 5; k++) {
                HollowBar b = new HollowBar(false,x,y);

                bar.add(b);

                x += spaceBlock;
            }
            x = originX;
            y += spaceBlock;
        }
        return bar;
    }
    public Array<HollowSquare> generateSquare() { //Génère les coordonnées des carrés à placer sur le terrain et leur attribut les barres qui les entourent
        Array<HollowSquare> squares = new Array<>();

        int x = originX+unitX+unitY/2-unitSquare/2;
        int y = originY+unitX+unitY/2-unitSquare/2;

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                HollowSquare square1 = new HollowSquare(x, y); //Génération des coordonnées
                square1.addNeighbors(getBar());                //recherche des voisin
                squares.add(square1);                          //Ajout au terrain
                x += spaceBlock;
            }
            x = originX+unitX+unitY/2-unitSquare/2;
            y += spaceBlock;
        }
        return squares;
    }

    public void setupAlign() {                            //trouvent tous les carrés qui pourraient créer un alignement avec tous les autres carrés du terrain
        for (int i = 0; i<this.square.size; i++) {
            createAlign(this.square.get(i));
        }
    }

    public void createDLC(int place, boolean side, boolean turn) { //Crée un emplacement de DLC à un endroit libre sur le terrain
        int x = originX - spaceBlock;
        int y = originY - spaceBlock;

        if (side) {                         //On peut définir chaque emplacement libre pour un DLC sur le terrain par un entier compris entre 0 et 5 inclus, et 2 boolean
            y += spaceBlock * place;
            if (turn) {
                x += spaceBlock * 5;
            }
        } else {
            x += spaceBlock * place;
            if (turn) {
                y += spaceBlock * 5;
            }
        }
        if (locateSquare(x + unitX + unitY / 2 - unitSquare / 2, y + unitX + unitY / 2 - unitSquare / 2) == null) {
            HollowSquare square1 = new HollowSquare(x + unitX + unitY / 2 - unitSquare / 2, y + unitX + unitY / 2 - unitSquare / 2); //Création et ajout du carré au terrain
            this.square.add(square1);
            canPlay.add(square1);
            bullet.add(new Items(x-26,y-26,"alone_dots.png"));
            HollowBar bar1 = locateBar(x + unitX, y);       //Création et ajout de la barre supérieur au carré si elle n'existe pas déjà
            if (bar1 == null) {
                bar1 = new HollowBar(true, x + unitX, y);
                this.bar.add(bar1);
                canPlay.add(bar1);
            }
            square1.neighbors.add(bar1);

            HollowBar bar2 = locateBar(x + unitX, y + spaceBlock);  //Pareil mais inférieur
            if (bar2 == null) {
                bar2 = new HollowBar(true, x + unitX, y + spaceBlock);
                this.bar.add(bar2);
                canPlay.add(bar2);
            }
            square1.neighbors.add(bar2);

            HollowBar bar3 = locateBar(x, y + unitX);          //Pareil mais à droite
            if (bar3 == null) {
                bar3 = new HollowBar(false, x, y + unitX);
                this.bar.add(bar3);
                canPlay.add(bar3);
            }
            square1.neighbors.add(bar3);

            HollowBar bar4 = locateBar(x + spaceBlock, y + unitX);   //Pareil mais à gauche
            if (bar4 == null) {
                bar4 = new HollowBar(false, x + spaceBlock, y + unitX);
                this.bar.add(bar4);
                canPlay.add(bar4);
            }
            square1.neighbors.add(bar4);
        }
    }

    public HollowSquare locateSquare(int x, int y) { //retourne le carré de coordonnée (x,y)
        int[] coord = {x,y};
        for(int i = 0; i<square.size; i++) {
            if(coord[0] == square.get(i).getCoords()[0] && coord[1] == square.get(i).getCoords()[1]) {
                return square.get(i);
            }
        }
        return null;
    }
    public HollowBar locateBar(int x, int y) { //retourne la barre de coordonnée (x,y)
        int[] coord = {x,y};
        for (HollowBar bar: bar) {
            if(coord[0] == bar.getCoords()[0] && coord[1] == bar.getCoords()[1]) {
                return bar;
            }
        }
        return null;
    }

    public void createAlign(HollowSquare square) { //Définis quelles sont les carrés voisin d'un carré donné pouvant créer un alignement
        int[] coord = square.getCoords();
        HollowSquare right = locateSquare(coord[0]+spaceBlock, coord[1]);
        HollowSquare left = locateSquare(coord[0]-spaceBlock, coord[1]);
        if(right!=null && left!=null) { //si les cases à droite et à gauche existent, on les ajoute dans le tableau de taille 2 : horizontal
            square.horizontal[0] = right;
            square.horizontal[1] = left;
        }
        else { //Sinon, on précise que ce carré ne peut pas s'aligner à l'horizontal
            square.isHorizontal=false;
        }

        HollowSquare up = locateSquare(coord[0], coord[1]+spaceBlock);
        HollowSquare down = locateSquare(coord[0], coord[1]-spaceBlock);
        if(up!=null && down!=null) { //pareil mais pour les cases verticales
            square.vertical[0] = up;
            square.vertical[1] = down;
        }
        else {
            square.isVertical=false;
        }
        HollowSquare upRight = locateSquare(coord[0]+spaceBlock, coord[1]+spaceBlock);
        HollowSquare downLeft = locateSquare(coord[0]-spaceBlock, coord[1]-spaceBlock);
        if(upRight!=null && downLeft!=null) { //pareil mais pour les diagonales
            square.diagonal[0] = upRight;
            square.diagonal[1]=downLeft;
        }
        else {
            square.isDiagonal=false;
        }
        HollowSquare upLeft = locateSquare(coord[0]-spaceBlock, coord[1]+spaceBlock);
        HollowSquare downRight = locateSquare(coord[0]+spaceBlock, coord[1]-spaceBlock);
        if(upLeft!=null && downRight!=null) { //pareil pour l'antidiagonal
            square.antidiagonal[0]=upLeft;
            square.antidiagonal[1]=downRight;
        }
        else {
            square.isAntidiagonal=false;
        }
    }

    public boolean checkEveryAlign(boolean player) { //vérifie si il y a un alignement avec les cases du joueur qui vient de jouer
        for (HollowSquare square: square) { //boucle for sur chaque carré
            if(!square.isFree && square.isBlue==player) { //on ne choisit que ceux qui sont plein et de la couleur du joueur qui vient de jouer
                if (square.isHorizontal) { //On vérifie si le bloc à droite et à gauche existent, puis s'ils sont de la même couleur que celui du milieu
                    if (square.horizontal[0].isBlue==player && square.horizontal[1].isBlue==player && !square.horizontal[0].isFree && !square.horizontal[1].isFree) {
                        return true;
                    }
                }
                if (square.isVertical) {//pareil avec celui en bas et en haut
                    if (square.vertical[0].isBlue==player && square.vertical[1].isBlue==player && !square.vertical[0].isFree && !square.vertical[1].isFree ) {
                        return true;
                    }
                }
                if (square.isDiagonal) {//pareil pour les 2 diagonales
                    if (square.diagonal[0].isBlue==player && square.diagonal[1].isBlue==player && !square.diagonal[0].isFree && !square.diagonal[1].isFree ) {
                        return true;
                    }
                }
                if (square.isAntidiagonal) {
                    if (square.antidiagonal[0].isBlue==player && square.antidiagonal[1].isBlue==player && !square.antidiagonal[0].isFree && !square.antidiagonal[1].isFree ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Array<HollowSquare> HaveNeighbors(int min, int max) {
        Array<HollowSquare> haveNeighbors = new Array<>();
        for (HollowSquare square : square) {
            if(square.isFree) {
                int numberFree = 0;
                for(int i =0; i<4;i++) {
                    if(square.neighbors.get(i).isFree) {
                        numberFree++;
                    }
                }
                square.freeNeighbors=numberFree;
                if(numberFree>=min && numberFree<=max) {
                    haveNeighbors.add(square);
                }
            }
        }
        return haveNeighbors;
    }
    public HollowBar FindInsaturation(Array<HollowSquare> nonSaturate) {
        for (int i = nonSaturate.size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            nonSaturate.swap(i, j);
        }
        for (HollowSquare nonSat : nonSaturate) {
            int[] coord = nonSat.getCoords();
            HollowSquare squareR = locateSquare(coord[0]+spaceBlock, coord[1]);
            HollowSquare squareL = locateSquare(coord[0]-spaceBlock, coord[1]);
            HollowSquare squareU = locateSquare(coord[0], coord[1]+spaceBlock);
            HollowSquare squareD = locateSquare(coord[0], coord[1]-spaceBlock);
            Array<Integer> aleaCond = new Array<>();
            if (nonSat.neighbors.get(2).isFree && (squareL==null || squareL.freeNeighbors>2)) {
                aleaCond.add(2);
            }
            if (nonSat.neighbors.get(3).isFree && (squareR==null || squareR.freeNeighbors>2)) {
                aleaCond.add(3);
            }
            if (nonSat.neighbors.get(0).isFree && (squareD==null || squareD.freeNeighbors>2)) {
                aleaCond.add(0);
            }
            if (nonSat.neighbors.get(1).isFree && (squareU==null || squareU.freeNeighbors>2)) {
                aleaCond.add(1);
            }
            if(aleaCond.size > 0) {
                return nonSat.neighbors.get(aleaCond.get(random.nextInt(aleaCond.size)));
            }
        }
        return null;
    }

    public void unPlay(GameScreen gameScreen) {
        if(iterator > 0) {
            allPlay.get(iterator-1).unPlay(gameScreen);
            iterator--;
        }
    }
    public void rePlay(GameScreen gameScreen) {
        if(allPlay.size > iterator) {
            iterator++;
            allPlay.get(iterator-1).rePlay(gameScreen);
        }
    }

    public int MinimaxTurn(int depth, boolean color) {
        if(checkEveryAlign(color)) {
            if(color) {
                return -10000;
            }
            else {
                return 10000;
            }
        }
        else if(depth==0) {
            return heuristic();
        }
        else {
            if(color) {
                int note = 10001;
                for(int i = 0; i<canPlay.size;i++) {
                    if(!canPlay.get(i).isSquare || ((HollowSquare) (canPlay.get(i))).FillNeighbors()) {
                        canPlay.get(i).isFree = false;
                        canPlay.get(i).isBlue = color;
                        for (int j = 0; j < canPlay.size; j++) {
                            if (j != i) {
                                if (!canPlay.get(j).isSquare || ((HollowSquare) (canPlay.get(j))).FillNeighbors()) {
                                    canPlay.get(j).isFree = false;
                                    canPlay.get(j).isBlue = color;
                                    int newNote = this.MinimaxTurn(depth - 1, !color);
                                    if (newNote < note) {
                                        note = newNote;
                                    }
                                    canPlay.get(i).isFree = true;
                                    canPlay.get(j).isFree = true;
                                }
                            }
                        }
                    }
                }
                return note;
            }
            else {
                int note = -10001;
                for(int i = 0; i<canPlay.size;i++) {
                    if (!canPlay.get(i).isSquare || ((HollowSquare) (canPlay.get(i))).FillNeighbors()) {
                        canPlay.get(i).isFree = false;
                        canPlay.get(i).isBlue = !color;
                        for (int j = 0; j < canPlay.size; j++) {
                            if (j != i) {
                                if (!canPlay.get(j).isSquare || ((HollowSquare) (canPlay.get(j))).FillNeighbors()) {
                                    canPlay.get(j).isFree = false;
                                    canPlay.get(j).isBlue = !color;
                                    int newNote = this.MinimaxTurn(depth - 1, !color);
                                    if (newNote > note) {
                                        note = newNote;
                                    }
                                    canPlay.get(i).isFree = true;
                                    canPlay.get(j).isFree = true;
                                }
                            }
                        }
                    }
                }
                return note;
            }
        }
    }
    public int[] Minimax(int depth) {
        int note = -10000;
        int coup1=0;
        int coup2=0;
        for(int i = 0; i<canPlay.size;i++) {
            if (!canPlay.get(i).isSquare || ((HollowSquare) (canPlay.get(i))).FillNeighbors()) {
                canPlay.get(i).isFree = false;
                canPlay.get(i).isBlue = false;
                for (int j = 0; j < canPlay.size; j++) {
                    if (j != i) {
                        if (!canPlay.get(j).isSquare || ((HollowSquare) (canPlay.get(j))).FillNeighbors()) {
                            canPlay.get(j).isFree = false;
                            canPlay.get(j).isBlue = false;
                            int newNote = this.MinimaxTurn(depth - 1, true);
                            if (newNote > note) {
                                note = newNote;
                                coup1 = i;
                                coup2 = j;
                            }
                            System.out.println(note);
                            System.out.println(coup1+"   "+i);
                            System.out.println(coup2+"   "+j);
                            canPlay.get(i).isFree = true;
                            canPlay.get(j).isFree = true;
                        }
                    }
                }
            }
        }
        return new int[]{coup1,coup2};
    }

    public int heuristic() {
        int score = 0;
        for(int i =0; i<square.size; i++) {
            HollowSquare squar = square.get(i);
            if(squar.isHorizontal) {
                score += squar.ComputeLine(squar.horizontal);
            }
            if(squar.isVertical) {
                score += squar.ComputeLine(squar.vertical);

            }
            if(squar.isDiagonal) {
                score += squar.ComputeLine(squar.diagonal);

            }
            if(squar.isAntidiagonal) {
                score += squar.ComputeLine(squar.antidiagonal);
            }
        }
        return score;
    }


}
