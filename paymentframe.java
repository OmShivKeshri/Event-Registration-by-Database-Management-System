import java.util.*; import java.io.*; import javax.swing.*; import java.awt.*;
import java.awt.event.*; import java.sql.*;
import java.text.SimpleDateFormat; import java.util.Date;



class Payment1 extends JFrame{ Container c;

JLabel name; JTextField name1; JButton b1,b2;

Payment1(){ c=getContentPane(); c.setLayout(null);

name=new JLabel("Name"); name1=new JTextField(20);

b1=new JButton("Register"); b2=new JButton("Back");

c.add(b1);
 
c.add(b2);
c.add(name);
c.add(name1);


name.setBounds(20,20,120,30); name1.setBounds(140,20,240,30); b1.setBounds(75,70,100,30); b2.setBounds(225,70,100,30);

ActionListener a1=(ae)->{ Participantframe a=new Participantframe(); this.dispose();
};
b2.addActionListener(a1);


ActionListener a2=(ae)->{ String w=name1.getText(); Connection con=null;
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","event","dbms1");

String s2="select enum from event where ename=?"; PreparedStatement s1=con.prepareStatement(s2); s1.setString(1,w);
ResultSet rs=s1.executeQuery(); int a=0;
while( rs.next() ) { a = rs.getInt(1);
System.out.println(a);
 
}



s2="select seats from eventdetails where enum = ?"; s1=con.prepareStatement(s2);
s1.setInt(1,a);
ResultSet rs1=s1.executeQuery(); int b=0;
while( rs1.next() ) { b = rs1.getInt(1);
System.out.println(b);
}
b--;



s2="update eventdetails set seats=? where enum=?"; s1 = con.prepareStatement(s2);
s1.setInt(1,b);
s1.setInt(2,a); s1.executeUpdate();



s2="select fees from eventdetails where enum=?"; PreparedStatement stmt1=con.prepareStatement(s2); stmt1.setInt(1,a);
rs=stmt1.executeQuery(); int d=0;
while( rs.next() ) { d = rs.getInt(1);
System.out.println(d);
 
}
// 	


//	 Connection con1=null; try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","transacti ons","dbms2");
String s3="insert into transaction_history values(?,?,?)"; PreparedStatement s4=con1.prepareStatement(s3); s4.setInt(1,a);
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); java.util.Date date = new java.util.Date();
java.sql.Date now=new java.sql.Date(date.getTime()); s4.setString(2,"18BCE0149");
s4.setDate(3,now); s4.executeQuery(); s4.close();
}
catch(SQLException e){ System.out.println("Issues: "+e);
}
catch(NumberFormatException e){ System.out.println("Issues: "+e);
}
finally{ try{
con1.close();
}
catch(SQLException e){
 
System.out.println("Issues: "+e);
}
}


// 	
JOptionPane.showMessageDialog(c,"The fees for the selected event is: "+d); Payment2 q=new Payment2();
this.dispose(); rs.close();

stmt1.close(); s1.close();
}
catch(SQLException e){ JOptionPane.showMessageDialog(c,"Issues: "+e);
}
catch(NumberFormatException e){ JOptionPane.showMessageDialog(c,"Issues: "+e);
}
finally{
try{ con.close();
}
catch(SQLException e){ JOptionPane.showMessageDialog(c,"Issues: "+e);
}
}
};
b1.addActionListener(a2);
 
this.setSize(400,160); this.setTitle("Event Registration");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); this.setVisible(true);
this.setLocationRelativeTo(null);
}// end of const.
}// end of Payment1


mimport java.util.*; import java.io.*; import javax.swing.*; import java.awt.*;
import java.awt.event.*; import java.sql.*;
import java.text.SimpleDateFormat; import java.text.ParseException;



class Payment2 extends JFrame{ Container c;

JLabel cn,ed,cvv,name,rn; JTextField cn1,ed1,cvv1,name1,rn1; JButton b1;

Payment2(){ c=getContentPane(); c.setLayout(null);

cn=new JLabel("Card Number");
 
ed=new JLabel("Expiry-date"); cvv=new JLabel("CVV"); name=new JLabel("Name on card"); rn=new JLabel("Registration No.");

cn1=new JTextField(16);
ed1=new JTextField("yyyy-MM-dd"); cvv1=new JTextField(3);
name1=new JTextField(20); rn1=new JTextField(9);

b1=new JButton("Pay");


c.add(b1);
c.add(cn);
c.add(ed);
c.add(cvv);
c.add(name);
c.add(rn);
c.add(cn1);
c.add(ed1);
c.add(cvv1);
c.add(name1);
c.add(rn1);


cn.setBounds(20,20,120,30); ed.setBounds(20,70,120,30); cvv.setBounds(20,120,120,30); name.setBounds(20,170,120,30); rn.setBounds(20,220,120,30);
 

cn1.setBounds(140,20,240,30); ed1.setBounds(140,70,240,30); cvv1.setBounds(140,120,240,30); name1.setBounds(140,170,240,30); rn1.setBounds(140,220,240,30);

b1.setBounds(150,270,100,30);


ed1.addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) {
ed1.setText("");
}


public void focusLost(FocusEvent e) {
// nothing
}
});


ActionListener a1=(ae)->{ Connection con=null;
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","transactio
ns","dbms2");
String s1="insert into card values(?,?,?,?,?)"; PreparedStatement p1=con.prepareStatement(s1); double q=Double.parseDouble(cn1.getText());
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); java.util.Date date=null;
try {
 
date = formatter.parse(ed1.getText());
} catch (ParseException e){ JOptionPane.showMessageDialog(c,"Issues: "+e);
}
java.sql.Date sqlDate = new java.sql.Date(date.getTime()); int r=Integer.parseInt(cvv1.getText());
String t=name1.getText(); String y=rn1.getText(); p1.setDouble(1,q); p1.setDate(2,sqlDate); p1.setInt(3,r); p1.setString(4,t); p1.setString(5,y);
int r1=p1.executeUpdate(); JOptionPane.showMessageDialog(c,r1+" rows inserted."); JOptionPane.showMessageDialog(c,"Payment Successful!"); this.dispose();

s1="update transaction_history set regno=? where regno=?"; p1=con.prepareStatement(s1);
p1.setString(1,y); p1.setString(2,"18BCE0149"); p1.executeUpdate();

p1.close();
Participantframe a=new Participantframe();
}
catch(SQLException e){
JOptionPane.showMessageDialog(c,"Issues: "+e);
}
 
catch(NumberFormatException e){ JOptionPane.showMessageDialog(c,"Issues: "+e);
}
finally{
try{ con.close();

}
catch(SQLException e){
JOptionPane.showMessageDialog(c,"Issues: "+e);
}
}




};
b1.addActionListener(a1);


this.setSize(400,370); this.setTitle("Event Registration");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); this.setVisible(true);
this.setLocationRelativeTo(null);
}// end of const.
}// end of Payment2
