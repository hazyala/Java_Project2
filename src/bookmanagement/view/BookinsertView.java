package bookmanagement.view;

import bookmanagement.domain.BookVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookinsertView extends JFrame {
    JTable table;
    DefaultTableModel model;
    ArrayList<BookVO> bookVOList;
    String[] header = {"도서번호", "도서명", "출판사", "저자명", "도서가격", "카테고리"};
    String[] categoryNames = {"IT도서", "소설", "비소설", "경제", "사회"};

    JPanel panS;
    JLabel[] lbls = new JLabel[header.length];
    JTextField[] tf = new JTextField[header.length -1];
    JComboBox<String> categoryCombo;
    JButton btnAdd;

    public BookinsertView() {
        setLayout(new BorderLayout());
        categoryCombo = new JComboBox(categoryNames);
        btnAdd = new JButton("도서추가");
        panS = new JPanel(new GridLayout(4,4));
        for (int i = 0; i < header.length; i++) {
            lbls[i] = new JLabel(header[i]);
            panS.add(lbls[i]);
            if(i < header.length-1){
                tf[i] = new JTextField();
                panS.add(tf[i]);
            } else {
                panS.add(categoryCombo);
            }
        }

        for (int i = 0; i < 3; i++) {
            panS.add(new JLabel(""));
        }
        panS.add(btnAdd);
    }

    //JTable 과 DefaultTableModel을 연결하고 테이블과 관련된 내용을 초기화
    public void initview(){
        model = new DefaultTableModel(header,bookVOList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);

        //테이블 행의 개수가 많을 때는 스크롤바가 필요
        JScrollPane scrollPane = new JScrollPane(table);

        //각 셀에 리스트에 저장된 BookVO 객체가 가지고 있는 값들을 설정
        pubSearchResult();

        //현재 패널에 Center에는 스크롤바가 있는 테이블, South에는 도서추가 패널
        add(scrollPane, BorderLayout.CENTER);
        add(panS, BorderLayout.SOUTH);
    }

    //DefaultTableModel에 도서정보들을 설정한다.
    public void pubSearchResult(){
        //model 에 행 개수 설정
        model.setRowCount(bookVOList.size());
        BookVO vo = null;
        for (int i = 0; i < bookVOList.size(); i++) {
            vo = bookVOList.get(i);
            model.setValueAt(vo.getIsbn(), i, 0);
            model.setValueAt(vo.getName(), i, 1);
            model.setValueAt(vo.getPublish(), i, 2);
            model.setValueAt(vo.getAuthor(), i, 3);
            model.setValueAt(vo.getPrice(), i, 4);
            model.setValueAt(vo.getCategoryName(), i, 5);
        }
    }
}
