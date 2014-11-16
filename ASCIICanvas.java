//
// ASCII Canvas
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class ASCIICanvas extends JTextArea{
    
    final JFileChooser fc = new JFileChooser();
    int page = 0;
    int index = 0;
    ArrayList<String> frames = new ArrayList<String>(index);
    
    public static void main(String args[]){
        new ASCIICanvas();
    }
    
    public ASCIICanvas(){
        super("");
        init();
    }
    
    public void init(){
        for (int i = 0; i < index; i++){
            frames.add("");
        }
    }
    
    public void prev(){
        if (page > 0){
            frameSave();
            page--;
            frameLoad();
        } else {
            page = 0;
        }
    }
    
    public void next(){
        frameSave();
        page++;
        frameLoad();
    }
    
    public int getPageNum(){
        return page + 1;
    }
    
    public void gotoPage(String pageNum){
        frameSave();
        page = Integer.parseInt(pageNum) - 1;
        frameLoad();
    }
    
    public void frameSave(){
        if (page > index - 1) {
            while (index < page){
                frames.add("");
                index++;
            }
            frames.add(this.getText());
            index++;
        } else {
            frames.set(page, this.getText());
        }
    }
    
    public void frameLoad(){
        if (page >= index) {
            this.setText("");
        } else {
            this.setText(frames.get(page));
        }
    }
    
    public void loadASCII(){
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                FileInputStream input = new FileInputStream(file);
                Scanner scan = new Scanner(input).useDelimiter("/endframe/");
                frames.clear();
                while (scan.hasNext()) {
                    frames.add(scan.next());
                    index++;
                }
                index--;
                frameLoad();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void saveASCII(){
        try{
            File animation = new File("Animation.txt");
            String output = this.getText();
            FileWriter writer = new FileWriter(animation);
            for (int i = 0; i < index; i++){
                writer.write(frames.get(i));
                writer.write("/endframe/");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}