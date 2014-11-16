//
// ASCII Animator
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ASCIIAnimator extends JFrame implements ActionListener{
    
    JTextField page = new JTextField("1");
    JButton btnPrev = new JButton("<<");
    JButton btnNext = new JButton(">>");
    JButton btnSave = new JButton("Save");
    JButton btnLoad = new JButton("Load");
    ASCIICanvas ASCIIText = new ASCIICanvas();
    JScrollPane scroll = new JScrollPane(ASCIIText);
    Font font = new Font("Monospaced", Font.PLAIN, 12);
    
    public static void main(String args[]){
        new ASCIIAnimator();
    }
    
    public ASCIIAnimator(){
        super("ASCII Animator");
        init();
    }
    
    public void init(){
        //initializes screen
        Container pnlASCII = this.getContentPane();
        Panel pnlNavigation = new Panel();
        Panel pnlOptions = new Panel();
        
        pnlASCII.setLayout(new BorderLayout());
        pnlASCII.add(scroll, BorderLayout.CENTER);
        pnlASCII.add(pnlNavigation, BorderLayout.SOUTH);
        pnlASCII.add(pnlOptions, BorderLayout.EAST);
        
        ASCIIText.setFont(font);
        ASCIIText.setLineWrap(true);
        ASCIIText.setColumns(100);
        ASCIIText.setRows(25);
        
        pnlNavigation.setLayout(new FlowLayout());
        pnlNavigation.add(btnPrev);
        pnlNavigation.add(page);
        pnlNavigation.add(btnNext);
        page.setColumns(2);
        page.setHorizontalAlignment(JTextField.CENTER);
        
        pnlOptions.setLayout(new BoxLayout(pnlOptions, BoxLayout.PAGE_AXIS));
        pnlOptions.add(btnSave);
        pnlOptions.add(btnLoad);
        
        btnPrev.addActionListener(this);
        btnNext.addActionListener(this);
        page.addActionListener(this);
        btnSave.addActionListener(this);
        btnLoad.addActionListener(this);
        
        this.setDefaultCloseOperation(ASCIIAnimator.EXIT_ON_CLOSE);
        this.pack();
        ASCIIText.requestFocusInWindow();
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        
        Object btnNav = e.getSource();
        if (btnNav == btnPrev) {
            ASCIIText.prev();
            page.setText(String.valueOf(ASCIIText.getPageNum()));
            ASCIIText.requestFocusInWindow();
        } else if (btnNav == btnNext) {
            ASCIIText.next();
            page.setText(String.valueOf(ASCIIText.getPageNum()));
            ASCIIText.requestFocusInWindow();
        } else if (btnNav == btnLoad) {
            ASCIIText.loadASCII();
        } else if (btnNav == btnSave) {
            ASCIIText.saveASCII();
        } else if (btnNav == page) {
            ASCIIText.gotoPage(page.getText());
            ASCIIText.requestFocusInWindow();
        } else {
            page.setText("??");
        }
        
    }
}