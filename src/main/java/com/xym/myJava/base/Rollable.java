package com.xym.myJava.base;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-07 23:24
 */
interface Rollable extends Playable, Bounceable {
    Ball ball = new Ball("PingPang");
}
