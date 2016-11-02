package calulator;
import java.awt.*;//计算器实例
import java.awt.event.*;
public class calculator
{
public static void main(String args[])
{
MyWindow my=new MyWindow("计算器"); 
}
}

class MyWindow extends Frame implements ActionListener
{    StringBuffer m=new StringBuffer();
     int p;
 TextField tex;
 Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,jia,jian,cheng,chu,deng,dian,qingling,kaifang;

MyWindow(String s)
{
 super(s);
//StringBuffer s2=new StringBuffer();
//String s;

tex=new  TextField(18);
   b0=new Button("    0   ");
   b1=new Button("    1   ");
   b2=new Button("    2   ");
   b3=new Button("   3    ");
   b4=new Button("    4   ");
   b5=new Button("    5   ");
   b6=new Button("    6   ");
   b7=new Button("    7   ");
   b8=new Button("    8   ");
   b9=new Button("     9   ");
    dian=new Button("     .   ");
     jia=new Button("     +   ");
    jian=new Button("     -    ");
   cheng=new Button("     ×   ");
     chu=new Button("    /    ");
    deng=new Button("    =   ");
   qingling=new Button("  清零 ");
    kaifang=new Button("   √   ");
 
 setLayout(new FlowLayout());
 add(tex);
 add(b0);
 add(b1);
 add(b2);
 add(b3);
 add(b4);
 add(b5);
 add(b6);
 add(b7);
 add(b8);
 add(b9);
 add(dian);
 add(jia);

 add(jian);
 
 add(cheng);
 
 add(chu);
 add(kaifang);
 add(qingling);
 add(deng);
 b0.addActionListener(this);
 b1.addActionListener(this);
 b2.addActionListener(this);
 b3.addActionListener(this);
 b4.addActionListener(this);
 b5.addActionListener(this);
 b6.addActionListener(this);
 b7.addActionListener(this);
 b8.addActionListener(this);
 b9.addActionListener(this);
 jia.addActionListener(this);
 jian.addActionListener(this);
 cheng.addActionListener(this);
 chu.addActionListener(this);
 dian.addActionListener(this);
 deng.addActionListener(this);
 qingling.addActionListener(this);
 kaifang.addActionListener(this);

setBounds(200,200,160,280);
 setResizable(false);//不可改变大小
 setVisible(true);
 validate();

addWindowListener(new WindowAdapter()
 {   public void windowClosing(WindowEvent ee)
   { System.exit(0);
            
    }
            
   });

}

public void actionPerformed(ActionEvent e)
{
 if(e.getSource()==b0)
 { 
   m=m.append("0");
   
   tex.setText(String.valueOf(m)); 
 }
 
 if(e.getSource()==b1)
 { 
   m=m.append("1"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b2)
 { 
   m=m.append("2"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b3)
 { 
   m=m.append("3"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b4)
 { 
   m=m.append("4"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b5)
 { 
   m=m.append("5"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b6)
 { 
   m=m.append("6"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b7)
 { 
   m=m.append("7"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==b8)
 { 
   m=m.append("8"); tex.setText(String.valueOf(m)); 
 }
 
 if(e.getSource()==b9)
 { 
   m=m.append("9"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==jia)
 { 
   m=m.append("+"); tex.setText(String.valueOf(m)); 
 }
   
 if(e.getSource()==jian)
 { 
   m=m.append("-"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==cheng)
 { 
   m=m.append("*"); tex.setText(String.valueOf(m)); 
 }
 if(e.getSource()==chu)
 { 
   m=m.append("/"); tex.setText(String.valueOf(m)); 
 }
 
 if(e.getSource()==dian)
 { 
   m=m.append("."); tex.setText(String.valueOf(m)); 
 }
 String mm=String.valueOf(m);
 int p1=mm.indexOf("+");
 int p2=mm.indexOf("-");
 int p3=mm.indexOf("*");
 int p4=mm.indexOf("/");
 if(p1!=-1)
 {
  p=p1;
 }
 
 else if(p3!=-1)
 {
  p=p3;
 }
 else if(p2!=-1)
 {
  p=p2;
 }
 else if(p4!=-1)
 {
  p=p4;
 }

if(e.getSource()==deng)
 { 

String m1=mm.substring(0,p);
   String m2=mm.substring(p+1);
   String ch=mm.substring(p,p+1);
    
   //System.out.println(m1);
   //System.out.println(m2);
   //System.out.println(ch);
   
   if(ch.equals("+"))
   {
   float n1=Float.parseFloat(m1); 
   float n2=Float.parseFloat(m2); 
   float sum=n1+n2;
   String su=String.valueOf(sum);
   tex.setText(su);
   }

if(ch.equals("-"))
   {
   float n1=Float.parseFloat(m1); 
   float n2=Float.parseFloat(m2); 
   float sum=n1-n2;
   String su=String.valueOf(sum);
   tex.setText(su);
   }

if(ch.equals("*"))
   {
   float n1=Float.parseFloat(m1); 
   float n2=Float.parseFloat(m2); 
   float sum=n1*n2;
   String su=String.valueOf(sum);
   tex.setText(su);
   }

if(ch.equals("/"))
   {
   float n1=Float.parseFloat(m1); 
   float n2=Float.parseFloat(m2); 
   float sum=n1/n2;
   String su=String.valueOf(sum);
   tex.setText(su);
   }

}

if(e.getSource()==qingling)
 {StringBuffer kk=new StringBuffer();
   m=kk;
   
   tex.setText("0"); 

// System.out.println(mm);
 }
 
 if(e.getSource()==kaifang)
 {
  String t=tex.getText();
  float num=Float.parseFloat(t);
  double nub=Math.sqrt(num);
  tex.setText(String.valueOf(nub));
  
 }
 
}

}