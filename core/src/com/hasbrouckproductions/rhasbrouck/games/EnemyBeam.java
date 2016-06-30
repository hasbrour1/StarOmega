package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hasbrouckr on 6/30/2016.
 */
public class EnemyBeam extends DynamicGameObject {

    public static final float BEAM_HEIGHT = 50;

    public static final int BEAM_VILOCITY = 400;

    public float xPos;
    public float yPos;
    public float beamWidth;
    public int state;


    public EnemyBeam(float x, float y){
        super(x, y, BEAM_HEIGHT, 10);
        beamWidth = 10;
        xPos = x;
        yPos = y;
    }

    //update laser position
    public void update(){
        if(beamWidth < 800){
            beamWidth -= BEAM_VILOCITY * Gdx.graphics.getDeltaTime();
        }else{
            beamWidth += BEAM_VILOCITY * Gdx.graphics.getDeltaTime();
        }
        this.bounds = new Rectangle(xPos, yPos, BEAM_HEIGHT, beamWidth);
    }
}
