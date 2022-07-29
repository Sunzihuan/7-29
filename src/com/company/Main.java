package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {

   public static void main(String[] args) {
       Frame f=new Frame();//创建窗口
   }
}
class Frame extends  JFrame
{
   Frame()
   {
       JFrame frame=this;
       frame.setTitle("加法计算器");//窗口名称
       frame.setLocation(100,100);//窗口位置
       frame.setSize(300,80);//窗口大小
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭操作
       Container c=frame;//容器
       c.setLayout(new FlowLayout());//设置布局
       //c.setBackground(Color.GREEN);
       JTextField a=new JTextField("1");
       JTextField b = new JTextField("1");
       Dimension d=new Dimension();//尺寸
       d.setSize(40,30);
       a.setPreferredSize(d);
       b.setPreferredSize(d);
       Label label=new Label("答案");//标签
       JComboBox f=new JComboBox();//下拉菜单
       String text[]={"+","-","*","/"};
       for (String p:text
            ) {
           f.addItem(p);
       }
       f.setSelectedIndex(0);
       c.add(a);
       c.add(f);
       c.add(b);


       JButton button=new JButton("=");
       button.addActionListener(new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   Double r = null;
                  switch (f.getSelectedItem().toString())
                  {
                      case "+":
                         r= Double.valueOf(a.getText())+Double.valueOf(b.getText());
                          break;
                      case "-":
                          r= Double.valueOf(a.getText())-Double.valueOf(b.getText());
                          break;
                      case "*":
                          r= Double.valueOf(a.getText())*Double.valueOf(b.getText());
                          break;
                      case "/":
                          r= Double.valueOf(a.getText())/Double.valueOf(b.getText());
                          break;
                  }

                   label.setText(r.toString());
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