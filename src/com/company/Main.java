package com.company;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {

   public static void main(String[] args) {
       BFrame f=new BFrame();
       f.setCalFinish(new IOnCalFinish() {
        @Override
        public void done(double value, BFrame owner) {
            if(value==100)
            {
                   //JOptionPane.showConfirmDialog(null, "乘除法启动", "呵呵", JOptionPane.CLOSED_OPTION);
                   owner.SwitchAction(new Ical() {
                       @Override
                       public double cal(String a, double b, double c) {
                           double r = Ical.super.cal(a, b, c);
                           if (r!=Double.MAX_VALUE)
                               return r;
                           switch (a) {
                               case "*":
                                   r = b * c;
                                   break;
                               case "/":
                                   r = b / c;
                                   break;
                           }
                           return r;
                       }
                   });
            }   
        }
       });

   }
}
interface Ical
{
    default double cal(String a,double b,double c)
    {
        double r = Double.MAX_VALUE;
        switch (a) {
            case "+":
                r = b+c;
                break;
            case "-":
                r = b - c;
                break;
        }
        return r;
    }
}
interface IOnCalFinish
{
    void done(double value,BFrame owner);
}
class BFrame extends  JFrame
{
    
    JButton button;
    private Ical cIcal=new Ical() {
        
    };
    void SwitchAction(Ical cIcal)
    {
        this.cIcal=cIcal;
    }
    IOnCalFinish calFinish=null;
    public void setCalFinish(IOnCalFinish calFinish) {
        this.calFinish = calFinish;
    }
    public IOnCalFinish getCalFinish() {
        return calFinish;
    }
   BFrame()
   {
       JFrame frame=this;
       frame.setTitle("加法计算器");
       frame.setLocation(100,100);
       frame.setSize(300,80);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Container c=frame;
       c.setLayout(new FlowLayout());
       //c.setBackground(Color.GREEN);
       JTextField a=new JTextField("1");
       JTextField b = new JTextField("1");
       Dimension d=new Dimension();
       d.setSize(40,30);
       a.setPreferredSize(d);
       b.setPreferredSize(d);
       Label label=new Label("答案");
       JComboBox f=new JComboBox();
       String text[]={"+","-","*","/"};
       for (String p:text
            ) {
           f.addItem(p);
       }
       f.setSelectedIndex(0);
       c.add(a);
       c.add(f);
       c.add(b);


        button=new JButton("=");
       button.addActionListener(new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   Double r= cIcal.cal(f.getSelectedItem().toString(),Double.parseDouble(a.getText()), Double.parseDouble(b.getText()));
                   label.setText(r.toString());
                   if(getCalFinish()!=null)
                   calFinish.done(r,BFrame.this);
               }
               catch (Exception ex)
               {
                   JOptionPane.showConfirmDialog(null,"请输入数字","错误",JOptionPane.CLOSED_OPTION);
               }
           }
       });
       c.add(button);
       c.add(label);

       frame.setVisible(true);
   }
}