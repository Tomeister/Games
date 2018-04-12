package Snake;

import javax.swing.*;

public class GameField extends JFrame {

    public GameField(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(375,390);
        setLocation(400,400);
        add(new SnakeEngine());
        setVisible(true);
    }

    public static void main(String[] args) {
        GameField gf = new GameField();
    }
}
