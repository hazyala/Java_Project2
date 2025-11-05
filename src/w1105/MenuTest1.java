package w1105;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTest1 extends JFrame {
    JLabel lbl = new JLabel("메뉴를 선택하면 문자열이 변경됩니다.", JLabel.CENTER);

    public MenuTest1() {
        add(lbl);
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu mFile = new JMenu("파일");
        JMenu mEdit = new JMenu("편집");
        mb.add(mFile);
        mb.add(mEdit);
        JMenuItem miNew = new JMenuItem("새문서");
        JMenuItem miOpen = new JMenuItem("열기");
        JMenuItem miClose = new JMenuItem("닫기");

        mFile.add(miNew);
        mFile.add(miOpen);
        mFile.add(miClose);

        miNew.addActionListener(menuListener);
        miOpen.addActionListener(menuListener);
        miClose.addActionListener(menuListener);

        setTitle("메뉴작성");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500, 400);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    ActionListener menuListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JMenuItem mi = (JMenuItem) e.getSource();
        }
    };

    public static void main(String[] args) {
        new MenuTest1();
    }
}
