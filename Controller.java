/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ANIS_14
 */
public class Controller {
    private MiniAtm view;

    public Controller(MiniAtm view) {
        this.view = view;
         this.view.getSaldo().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 proses();
             }
             });
    }
    private void proses(){
         JFileChooser loadFile = view.getLoadFile();
             StyledDocument doc = view.getTextPane().getStyledDocument();
             if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
                 BufferedInputStream reader = null;
                 try {
                     reader = new BufferedInputStream(new FileInputStream(loadFile.getSelectedFile()));
                     doc.insertString(0, "", null);
                     int temp = 0;
                     ArrayList<Integer> list = new ArrayList<>();
                     while ((temp=reader.read()) != -1) {                    
                         list.add(temp);
                     }
                     if (!list.isEmpty()) {
                         byte[] dt = new byte[list.size()];
                         int i = 0;
                         for (Integer integer : list) {
                             dt[i]=integer.byteValue();
                             i++;
                         }
                         doc.insertString(doc.getLength(), new String(dt), null);
                         JOptionPane.showMessageDialog(view, "File berhasil dibaca.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                     }
                 } catch (FileNotFoundException ex) {
                     Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException | BadLocationException ex) {
                     Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                 } finally {
                     if (reader != null) {
                         try {
                             reader.close();
                         } catch (IOException ex) {
                             Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 }
             }
             
      }
}
