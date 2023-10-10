
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author macbookair
 */
public class Fetch_movies extends javax.swing.JFrame {

    String catname;
    /**
     * Creates new form Fetch_movies
     */
    public Fetch_movies(String category) {
        initComponents();
        setSize(600, 600);
        catname = category;
        loadmovies();
    }

    void loadmovies()
    {
        String ans = myClient.fetchmovies(catname);
        
        StringTokenizer st = new StringTokenizer(ans,";;");
        int n = st.countTokens();
        
        JButton arr[] = new JButton[n];
        
        int x=10,y=10;
        
        for (int i = 0; i < n; i++) 
        {
            String row = st.nextToken();
            StringTokenizer st1 = new StringTokenizer(row,"$");
            
            int id = Integer.parseInt(st1.nextToken());
            String name = st1.nextToken();
            String photo = st1.nextToken();
            
            
            arr[i] = new JButton(name);
            
            
            arr[i].setBounds(x, y, 250, 80);
            
          ImageIcon ic = new ImageIcon(photo);
          
          Image img = ic.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
          
          ImageIcon ic1 = new ImageIcon(img);
          
          arr[i].setIcon(ic1);
         
          arr[i].addActionListener(new ActionListener()
          {
          public void actionPerformed(ActionEvent e)
          {
            Movie_details obj = new Movie_details(id); 
            obj.setVisible(true);
          }
          
          }
          );
          
          
          mainpanel.add(arr[i]);
          mainpanel.repaint();
          
          if(x<=520)
          {
              x= x+260;
          }
         else
          {
              x=10;
              y=y+50;
          }
            
        }
        mainpanel.setPreferredSize(new Dimension(530, n*50));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(mainpanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 580, 310);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fetch_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fetch_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fetch_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fetch_movies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fetch_movies("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    // End of variables declaration//GEN-END:variables
}
