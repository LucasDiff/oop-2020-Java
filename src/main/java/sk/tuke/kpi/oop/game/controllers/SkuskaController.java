package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.characters.Alien;

public class SkuskaController implements KeyboardListener {

    private Scene scene;

    public SkuskaController(Scene scene)
    {
        this.scene = scene;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)

    {
        boolean i=true;
         if(key == Input.Key.W){


scene.addActor( Alien.getInstance(),100,100);
             //scene.getActors().stream().filter(actor -> actor instanceof Alien)

            // .forEach(alien -> {
            //     ((Alien) alien).die();

            // });

        }

/*
 if (i) {

                        ((Alien) alien).getHealth().exhaust();

                        i = false;
                    } else {
                        i = true;
                    }
 */



   /* int num=14;
    int polovica=num/2;
        if (scene == null) {
            return;
        }

        if (key == Input.Key.P) {
            boolean i = true;

            while(num!=polovica){

                Actor alien = scene.getFirstActorByType(Alien.class);
                scene.removeActor(alien);
                num--;
            }*/

           /* for (Actor alien : scene.getActors()) {
                if (alien instanceof Alien) {
                    if (i) {

                        ((Alien) alien).getHealth().exhaust();

                        i = false;
                    } else {
                        i = true;
                    }
                }
            }
            */
        }
    }

