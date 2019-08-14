package com.xym.myJava.base;

class Ball implements Rollable {
    private String name;

    public String getName() {
        return name;
    }

    public Ball(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        //ball = new Ball("Football");
        System.out.println(ball.getName());
    }

    @Override
    public void play(int x) {
        System.out.println("x="+x);
    }

    public static void main(String[] args) {
        Playable playable = ball;
        Bounceable bounceable = ball;
        System.out.println(playable);
        System.out.println(bounceable);

        bounceable.play();
        playable.play(100);
    }


}
