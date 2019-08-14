package com.xym.myJava.base.aaa;

import com.xym.myJava.base.A;
import com.xym.myJava.base.B;

class C extends B implements A {
    public void pX() {
        //父类x
        System.out.println(super.x);
        //
        System.out.println(A.x);
        //A.x = 100;
    }

    public static void main(String[] args) {
        new C().pX();
    }
}
