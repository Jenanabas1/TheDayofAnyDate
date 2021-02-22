/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dayofanydate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.*;

/**
 *
 * @author jenanabas
 */
public class DOAD implements ActionListener {
    //private static Scanner scan;
    JFrame frame;
    JButton button;
    JLabel maintext, text;
    JPanel panel, pane2;
    JTextField textfield, finaltext;
    
    
    /**
     * @param args the command line arguments
     */
    
    public DOAD() {
        frame = new JFrame();
        frame.setTitle("Find the Day of Any Date");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(0, 0, 255));
        frame.setLayout(new BorderLayout());
        
        maintext = new JLabel();
        maintext.setBackground(new Color(0, 0, 255));
        maintext.setForeground(new Color(255, 255, 255));
        maintext.setFont(new Font ("Ink Free", Font.BOLD, 55));
        maintext.setHorizontalAlignment(JLabel.CENTER);
        maintext.setText("Find the Day of Any Date");
        maintext.setOpaque(true);
        
        text = new JLabel();
        text.setBackground(new Color(0, 0, 255));
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font ("Ink Free", Font.BOLD, 25));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("YYYY/MM/DD");
        text.setOpaque(true);
        
        button = new JButton();
        button.setText("Search");
        button.setHorizontalAlignment(JButton.CENTER);
        button.setPreferredSize(new Dimension(100, 20));
        button.addActionListener(this);

        textfield = new JTextField(9);
        textfield.setBounds(50,50,150,20);
        finaltext = new JTextField(9);
        finaltext.setBounds(70, 70, 150, 20);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(maintext, BorderLayout.PAGE_START);
        panel.add(text, BorderLayout.CENTER);
        
        pane2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pane2.setBackground(new Color(0, 0, 255));
        pane2.add(textfield);
        pane2.add(button);
        pane2.add(finaltext);
        
        frame.add(panel, BorderLayout.PAGE_START);
        frame.add(pane2, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = textfield.getText();
        
        int total = 0;
         
        String year = s1.substring(0, 4);
        int yr = parseInt(year);
         
        String LYR = s1.substring(2, 4);
        int lyr = parseInt(LYR);
         
        String month = s1.substring(5, 7);

        int mth = parseInt(month);
 
        
        String day = s1.substring(8, 10);
        int dy = parseInt(day);
        
        total = calculate(yr, mth, dy, lyr);
        
        result(total);
    }
    
    public static int calculate(int YR, int MTH, int DY, int LYR) {
        int newM = 0;
        int centC = 0;
        int div = 0;
        int leapYear = 4;
        int total = 0;
        
        int tot = 0;
        int tot2 = 0;
        int result = 0;
        
        //To find the Month Codes
        if (MTH == 2 || MTH == 3 || MTH == 11)
            newM = 4;
        else if (MTH == 1 || MTH == 10)
            newM = 1;
        else if (MTH == 4 || MTH == 7)
            newM = 0;
        else if (MTH == 9 || MTH == 12)
            newM = 6;
        else if(MTH == 5)
            newM = 2;
        else if (MTH == 6)
            newM = 5;
        else if (MTH == 8)
            newM = 3;
        
        //To find the Century Codes
        if (YR >= 1000 && YR <= 1099 || YR >= 1400 && YR <=1499 || 
                YR >= 2200 && YR <=2299 || YR >= 2600 && YR <= 2699 ||
                YR >= 3000 && YR <= 3099)
            centC = 2;
        else if(YR >= 1100 && YR <= 1199 || YR >= 1500 && YR <= 1599 ||
                YR >= 1900 && YR <= 1999 || YR >= 2300 && YR <= 2399 ||
                YR >= 2700 && YR <= 2799)
            centC = 0;
        else if(YR >= 1200 && YR <= 1299 || YR >= 1600 && YR <= 1699 ||
                YR >= 2000 && YR <= 2099 || YR >= 2400 && YR <= 2499 ||
                YR >= 2800 && YR <= 2899)
            centC = 6;
        else if(YR >= 1300 && YR <= 1399 || YR >= 1700 && YR <= 1799 ||
                YR >= 2100 && YR <= 2199 || YR >= 2500 && YR <= 2599 ||
                YR >= 2900 && YR <= 2999)
            centC = 4;
        
        div = LYR / leapYear;
        
        
        tot = (DY + newM + centC + LYR + div);
        total = (DY + newM + centC + LYR + div) / 7;
        
        tot2 = total * 7;
        result = tot - tot2;
        
        return result;
    }
    
    public void result(int TOTAL) {   
        JOptionPane PANE = new JOptionPane();
        if (TOTAL >= 0) {
            switch(TOTAL) {
                case 0:
                    finaltext.setText("Saturday");  
                    break;
                case 1:
                    finaltext.setText("Sunday");  
                    break;
                case 2:
                    finaltext.setText("Monday");  
                    break;
                case 3:
                    finaltext.setText("Tuesday");  
                    break;
                case 4:
                    finaltext.setText("Wednesday");  
                    break;
                case 5:
                    finaltext.setText("Thursday");  
                    break;
                case 6:
                    finaltext.setText("Friday");  
                    break;
                default:
                    JOptionPane.showConfirmDialog(PANE, 
                            "Hmmm, Something wrong happened.");
            }
        }
    }
    
}

//The way beihind How I implemented or calculated the code

/*Calculation: Add all: Day # + Month Code + Century Code + Last 2 Century #'s +
(Last 2 Century #'s/ 4)= Total / 7 = Answer(Day Code)
*/

//Month Code 144025036146
/*Century Code 
               1000-1099 - 2
----------------------------
               1100-1199 - 0
               1200-1299 - 6
               1300-1399 - 4
               1400-1499 - 2
----------------------------
               1500-1599 - 0
               1600-1699 - 6 
               1700-1799 - 4
               1800-1899 - 2
----------------------------
               1900-1999 - 0
               2000-2099 - 6
               2100-2199 - 4
               2200-2299 - 2
----------------------------
               2300-2399 - 0
               2400-2499 - 6
               2500-2599 - 4
               2600-2699 - 2
----------------------------
               2700-2799 - 0
               2800-2899 - 6
               2900-2999 - 4
               3000-3099 - 2
----------------------------
*/
/*Day Code Saturday  - 0
           Sunday    - 1
           Monday    - 2
           Tuesday   - 3
           Wednesday - 4
           Thursday  - 5
           Friday    - 6
*/