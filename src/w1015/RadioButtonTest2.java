package w1015;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonTest2 extends JFrame {
    String[] rbStrArr = {"아이브", "뉴진스", "블랙핑크"};
    String[] imgNameArr = {"ive", "newjeans", "blackpink"};
    JRadioButton[] rbArr = new JRadioButton[rbStrArr.length];
    JLabel lbl = new JLabel("", JLabel.CENTER);

    public RadioButtonTest2() {
        JPanel panel = new JPanel();
        ButtonGroup bg = new ButtonGroup();

        int i = 0;
        for (String str : rbStrArr) {
            rbArr[i] = new JRadioButton(str);
            rbArr[i].addActionListener(RadioButtonListener);
            rbArr[i].addActionListener(RadioListener);
            bg.add(rbArr[i]);
            panel.add(rbArr[i]);
            i++;
        }

        lbl.setOpaque(true);

        add(panel,"North");
        add(lbl,"Center");


        setTitle("RadioButtonTest");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //JFrame이 모니터 화면에 정중앙에 오도록 한다.
        CenterFrame cf = new CenterFrame(500,300);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    ActionListener RadioButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            for(JRadioButton rb : rbArr) {
                if(rb.isSelected()) {
                    JOptionPane.showMessageDialog(null,rb.getText() + "가 선택되었습니다.");
                }
            }
        }
    };

    ActionListener RadioListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            int i = 0;
            for (JRadioButton rb : rbArr) {
                if(rb.isSelected()) {
                    ImageIcon img = new ImageIcon("D://Java_Project//JavaProject2//src//w1015//img/" + imgNameArr[i] +".jpg");
                    lbl.setIcon(img);
                }
                i++;
            }
        }
    };

    public static void main(String[] args) {
        new RadioButtonTest2();
    }
}

