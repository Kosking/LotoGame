package my.game.loto.secondAction.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import my.game.loto.firstAction.retrofit.settingsObjects.PlayObject;

public class GameActivity extends FragmentActivity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setSerializableObject();

    }

    private void setSerializableObject(){
        try (ObjectInputStream input =  new ObjectInputStream (new FileInputStream("StartObjects.out"))) {
            List<PlayObject> startingObject = (List<PlayObject>) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCards();
        setNamesPlayers();
        setImagesPlayers();
    }

    private void setImagesPlayers() {

    }

    private void setNamesPlayers() {

    }

    private void setCards() {

    }
}
