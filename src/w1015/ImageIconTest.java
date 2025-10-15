package w1015;

import center_frame.CenterFrame;

import javax.swing.*;

public class ImageIconTest extends JFrame {
    public ImageIconTest() {
        ImageIcon icon1 = new ImageIcon("D://Java_Project//JavaProject2//src//w1015//img/ive.jpg");
        ImageIcon icon2 = new ImageIcon("D://Java_Project//JavaProject2//src//w1015//img/newjeans.jpg");
        ImageIcon icon3 = new ImageIcon("D://Java_Project//JavaProject2//src//w1015//img/blackpink.jpg");
        JLabel label = new JLabel(icon1);
        JButton button = new JButton(icon2);
        JLabel label2 = new JLabel(icon3);
        add(label,"North");
        add(button,"Center");
        add(label2,"South");

        setTitle("imgIconTest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //JFrame이 모니터 화면에 정중앙에 오도록 한다.
        CenterFrame cf = new CenterFrame(500,300);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageIconTest();
    }
}
