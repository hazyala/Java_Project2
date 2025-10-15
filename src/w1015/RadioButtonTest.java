package w1015;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonTest extends JFrame {
    String[] rbStrArr = {"아이브", "뉴진스", "블랙핑크"};
    JRadioButton[] rbArr = new JRadioButton[rbStrArr.length];

    JRadioButton radioButton;

    public RadioButtonTest() {
        setLayout(new FlowLayout());
        ButtonGroup bg = new ButtonGroup();

        int i = 0;
        for (String str : rbStrArr) {
            rbArr[i] = new JRadioButton(str);
            rbArr[i].addActionListener(RadioButtonListener);
            bg.add(rbArr[i]);
            add(rbArr[i]);
            i++;
        }

//        for(int i = 0; i < rbStrArr.length; i++){
//            rbArr[i] = new JRadioButton(rbStrArr[i]);
//        }

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

//            JRadioButton source = (JRadioButton) e.getSource();
//            String selectedText = source.getText();
//            JOptionPane.showMessageDialog(null,selectedText + "가 선택되었습니다.");
        }
    };

    public static void main(String[] args) {
        new RadioButtonTest();
    }
}

