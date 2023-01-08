import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class Main extends JFrame implements ActionListener
{
    static JTable table;
    static JScrollPane scroll;
    static DefaultTableModel model;
    static JTextField txt1,txt2;
    static JButton but1,but2,but3,but4;
    static Connection con;
    static Statement stmt;
    public static void main(String[] args)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)dim.getWidth();
        int height = (int) dim.getHeight();

        Main fra = new Main();

        String[] cols = {"Roll_Number","Student_Name"};
        table = new JTable();
        fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fra.getContentPane().setBackground(new Color(117,192,242));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table.setModel(model);
        scroll = new JScrollPane(table);
        fra.add(scroll);
        txt1 = new JTextField();
        txt2 = new JTextField();
        but1 = new JButton("Save Data");
        but2 = new JButton("Remove");
        but3 = new JButton("Update");
        but4 = new JButton("Filter");
        but1.addActionListener(fra);
        but2.addActionListener(fra);
        but3.addActionListener(fra);
        but4.addActionListener(fra);
        JLabel label1 = new JLabel("Roll Number");
        label1.setBounds(300,110,50,50);
        JLabel label2 = new JLabel("Student Name");
        label2.setBounds(300,200,12,12);


        fra.setLayout(null);
        txt1.setBounds(300,160,200,50);
        txt2.setBounds(300,250,200,50);
        but1.setBounds(300, 300,200,50);
        but2.setBounds(500, 300,200,50);
        but3.setBounds(700, 300,200,50);
        but4.setBounds(900, 300,200,50);
        scroll.setBounds(100, 350,500,150);

        fra.add(txt1);
        fra.add(txt2);
        fra.add(but1);
        fra.add(but2);
        fra.add(but3);
        fra.add(but4);
//        mn.fra.add(mn.label1);
//        mn.fra.add(mn.label2);
        fra.setSize(width, height);
        fra.setVisible(true);
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_hr", "root1", "1234");
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student");
        String code,type;
        while(rs.next()){
            code = rs.getString(1);
            type = rs.getString(2);
            model.addRow(new Object[] {code,type});
        }
    }
        catch(Exception e){
        e.printStackTrace();
    }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String id = txt1.getText();
        String name = txt2.getText();
        String code,type;
        if(e.getSource() == but1)
        {
            try
            {
                stmt.execute("insert into student values('"+id+"'"+","+"'"+name+"')");
                ResultSet rs = stmt.executeQuery("select * from student");
                JOptionPane.showMessageDialog(null,"Saved");
                model= new DefaultTableModel();
                String[] columns = {"Roll_Number","Student_Name"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);
                while(rs.next()){
                    code = rs.getString(1);
                    type = rs.getString(2);
                    model.addRow(new Object[] {code,type});}
            }
            catch (Exception f)
            {
                f.printStackTrace();
            }
        }
        else if(e.getSource() == but2)
        {
            try
            {
                stmt.execute("Delete from student Where `RollNumber` ="+"'"+id+"'");
                ResultSet rs = stmt.executeQuery("select * from student");
                JOptionPane.showMessageDialog(null,"Removed");
                model= new DefaultTableModel();
                String[] columns = {"Roll_Number","Student_Name"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);
                while(rs.next()){
                    code = rs.getString(1);
                    type = rs.getString(2);
                    model.addRow(new Object[] {code,type});}
            }
            catch (Exception l)
            {
                l.printStackTrace();
            }
        }
        else if(e.getSource() == but3)
        {
            try
            {
                stmt.execute("UPDATE `student` SET `student_Name` ="+"'"+name+"'"+"WHERE `Rollnumber`= '"+id+"'");
                ResultSet rs = stmt.executeQuery("select * from student");
                JOptionPane.showMessageDialog(null,"Updated");
                model= new DefaultTableModel();
                String[] columns = {"Roll_Number","Student_Name"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);
                while(rs.next()){
                    code = rs.getString(1);
                    type = rs.getString(2);
                    model.addRow(new Object[] {code,type});}

            }
            catch (Exception j)
            {
                j.printStackTrace();
            }
        }
        else if(e.getSource() == but4) {
            try {
                stmt.execute("UPDATE `student` SET `student_Name` =" + "'" + name + "'" + "WHERE `Rollnumber`= '" + id + "'");
                ResultSet rs = stmt.executeQuery("select * from student");
                model = new DefaultTableModel();
                String[] columns = {"Roll_Number", "Student_Name"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);
                while (rs.next()) {
                    code = rs.getString(1);
                    type = rs.getString(2);
                    model.addRow(new Object[]{code, type});
                }

            } catch (Exception j) {
                j.printStackTrace();
            }
        }
    }
}