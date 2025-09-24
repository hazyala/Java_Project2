package w0924;

import javax.swing.*;

public class JFrameTest extends JFrame{
    public JFrameTest () {
        setTitle("First Frame Test");
        setSize(500, 500);
        setLocation(200,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JFrameTest();
    }
}

