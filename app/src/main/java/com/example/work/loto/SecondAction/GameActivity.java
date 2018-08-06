package com.example.work.loto.SecondAction;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.work.loto.FirstAction.Repository.Retrofit.SettingsObjects.PlayObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class GameActivity extends FragmentActivity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try {
            setSerializableObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSerializableObject() throws IOException {
        try {
            ObjectInputStream input =  new ObjectInputStream (new FileInputStream("startObjects.out"));
            List<PlayObject> startingObject = (List<PlayObject>) input.readObject();
        } catch (ClassNotFoundException e) {
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
