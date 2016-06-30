package com.hasbrouckproductions.rhasbrouck.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hasbrouckr on 6/30/2016.
 */
public class EnemyBeam extends DynamicGameObject {

    public static final float BEAM_HEIGHT = 75;

    public static final int BEAM_VILOCITY = 400;

    public float xPos;
    public float yPos;
    public float beamWidth;
    public int state;


    public EnemyBeam(float x, float y){
        super(x, y, 10, BEAM_HEIGHT);
        beamWidth = 10;
        xPos = x;
        yPos = y;
    }

    //update laser position
    public void update(){
        if(beamWidth < 1500){
            beamWidth += BEAM_VILOCITY * Gdx.graphics.getDeltaTime();
            xPos -= BEAM_VILOCITY * Gdx.graphics.getDeltaTime();
        }else{
            xPos -= BEAM_VILOCITY * Gdx.graphics.getDeltaTime();
        }
        this.bounds = new Rectangle(xPos, yPos, beamWidth, BEAM_HEIGHT);
    }
}
