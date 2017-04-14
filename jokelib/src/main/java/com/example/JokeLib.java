package com.example;

import java.util.Random;

public class JokeLib {
    public String getJoke(){
        return getRandomJoke();
    }

    public String getRandomJoke(){
        String[] arrayJoke = {"Can a kangaroo jump higher than a house? Of course, a house does not jump at all.",
                "How many hardware engineers does it take to change a light bulb? \n" +
                        "None: you can solve any problem in the software!",
        "Anton, do you think I am a bad mother?\n" +
                "My name is Paul.",
        "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away."};
        int rnd = new Random().nextInt(arrayJoke.length);

        return arrayJoke[rnd];
    }
}
