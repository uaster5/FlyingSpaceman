package com.games.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Роман on 28.11.2017.
 */

public class Ship {

        private static final int GRAVITY = -15;     //зміна положення корабля
        private static final int MOVEMENT = 100;    //системна зміна для швидкості
        private Vector3 position;               //позиція
        private Vector3 velosity;       //швидкість падіння
        private Rectangle bound;
        private Texture ship;
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
                velosity.scl(dt);       //множить на системний час
                position.add(MOVEMENT*dt, velosity.y, 0);
                if (position.y < 0)
                    position.y = 0;

                velosity.scl(1 / dt);
                bound.setPosition(position.x, position.y);


            }
            public void jump(){
                if (position.y>430){        //не дає вилетіти за межі екрану
                    velosity.y=0;
                }
                else
                velosity.y = 200;

            }
    public Rectangle getBound(){
        return bound;
    }

    public void dispose() {
        ship.dispose();
    }
}

