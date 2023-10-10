import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class usersignup extends javax.swing.JFrame {
     JFileChooser jfc;
     File ph;
globalvariables gv = new globalvariables();
    public usersignup() {
        initComponents();
         jfc = new JFileChooser();
        setSize(600, 600);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pf = new javax.swing.JPasswordField();
        email1 = new javax.swing.JTextField();
        mobile = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        preview = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("USER SIGNUP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 70);

        jLabel2.setText("Photo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 350, 140, 40);

        jLabel3.setText("Email");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 100, 140, 40);

        jLabel4.setText("Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 160, 140, 40);

        jLabel5.setText("Mobile");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(90, 230, 140, 40);

        jLabel6.setText("Address");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(90, 290, 140, 40);
        getContentPane().add(pf);
        pf.setBounds(260, 160, 260, 40);
        getContentPane().add(email1);
        email1.setBounds(260, 100, 260, 40);
        getContentPane().add(mobile);
        mobile.setBounds(260, 230, 260, 40);
        getContentPane().add(address);
        address.setBounds(260, 290, 260, 40);

        jButton1.setText("Choose Photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(410, 390, 140, 40);

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(220, 510, 190, 40);

        preview.setText("jLabel7");
        getContentPane().add(preview);
        preview.setBounds(260, 360, 140, 110);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int ans = jfc.showOpenDialog(this);
        if(ans == JFileChooser.APPROVE_OPTION)
        {
            ph = jfc.getSelectedFile();
            
            ImageIcon ic = new ImageIcon(ph.getPath());
            Image img = ic.getImage().getScaledInstance(preview.getWidth(), preview.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon ic1 = new ImageIcon(img);
            preview.setIcon(ic1);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       String email = email1.getText();
       String password = pf.getText();
       String phone = mobile.getText();
       String add = address.getText();
       
       if(email.equals("")||password.equals("")||phone.equals("")||add.equals("")|| ph == null)
       {
           JOptionPane.showMessageDialog(rootPane, "All Fields are Mandatory");
       }
       else
       {
           
       String ans = myClient.verifyEmail(email);

          if(ans.equals("exist"))   
         {
              JOptionPane.showMessageDialog(rootPane, "Email already Taken!");
            
         }
        
          String otp =   JOptionPane.showInputDialog(rootPane, "Enter Otp to Verify");
          
             System.out.println("---->"+gv.getMyotp());
          if(otp.trim().equals(ans.trim()))
          {
                String ans1 = myClient.usersignup(email, password, phone, add, ph);
                if(ans1.equals("success"))
                {
                    JOptionPane.showMessageDialog(rootPane, "Signup Success");
                    userlogin obj = new userlogin();
                    obj.setVisible(true);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Signup Failed");
                }
          }
          else
          {
             JOptionPane.showMessageDialog(rootPane, "Otp Not Matched");  
          }
         
       
       }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usersignup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JTextField email1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField mobile;
    private javax.swing.JPasswordField pf;
    private javax.swing.JLabel preview;
    // End of variables declaration//GEN-END:variables
}
