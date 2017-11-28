package com.games.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Роман on 28.11.2017.
 */

public class Ship {

        private static final int GRAVITY = -15;
        private static final int MOVEMENT = 100;
        private Vector3 position;
        private Vector3 velosity;
        private Rectangle bound;
        private Texture ship;
        private Rectangle bounds;

        public Ship(int x, int y){
            position = new Vector3(x, y, 0);
            velosity = new Vector3(0, 0, 0);
            ship = new Texture("ship.png");
            bound = new Rectangle(x, y, ship.getWidth(), ship.getHeight());
        }

        public Vector3 getPosition() {
            return position;
        }

        public Texture getShip() {
            return ship;
        }


            public void update(float dt){
                if (position.y > 0)
                    velosity.add(0, GRAVITY, 0);
                velosity.scl(dt);
                position.add(MOVEMENT*dt, velosity.y, 0);
                if (position.y < 0)
                    position.y = 0;

                velosity.scl(1 / dt);
                bound.setPosition(position.x, position.y);


            }
            public void jump(){
                velosity.y = 200;
            }
    public Rectangle getBound(){
        return bound;
    }

    public void dispose() {
        ship.dispose();
    }
}

