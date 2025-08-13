package kr.ac.kopo.ctc.kopo33.board.service.designpattern;

public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return  instance;
    }
}

