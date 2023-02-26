package view;

import controller.ApplicationController;
import manager.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class Application extends JFrame implements ActionListener, MouseListener, FocusListener {
    ApplicationController applicationController;
    private static boolean flag = false;
    private final JLabel jl5, jl13, jl20, jl23, jl24, jl26, jl36;
    private final JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9, jb10;
    private final DefaultTableModel dtm;
    private final JPanel jp7, jp8, jp12, jp14, jp18, jp19, jp23, jp25, jp28, jp33, jp34;
    private final JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11, jtf12, jtf13, jtf14, jtf15, jtf16, jtf17;
    private final JComboBox<String> jcb1, jcb2;
    private final JTextArea jta1, jta2;
    private JFileChooser jfc;

    public Application() {
        applicationController = new ApplicationController();

        /*
        creating common JComponents required for application
         */
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("\sWestminster Skin Consultation Manager ");
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JLabel jl2 = new JLabel(new ImageIcon(new ImageIcon("src/main/assets/menu.jpg").
                getImage().getScaledInstance(250, 220, Image.SCALE_DEFAULT))); //setting image to the jLabel
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JLabel jl3 = new JLabel("GUI MENU");
        jb1 = new JButton("Visualize Doctor List");
        jb2 = new JButton("Add New Consultation");
        jb8 = new JButton("View/Delete Consultation");
        JLabel jl4 = new JLabel("v-1.01");
        jp7 = new JPanel();
        jl5 = new JLabel("SELECT AN OPTION FROM THE `MENU` TO CONTINUE");

        /*
        creating JComponents required for list all doctors
         */
        jp8 = new JPanel();
        JPanel jp9 = new JPanel();
        JLabel jl6 = new JLabel("\s\sDoctor List");
        JPanel jp10 = new JPanel();
        dtm = new DefaultTableModel();
        JTable jt = new JTable(dtm);
        JScrollPane jsp1 = new JScrollPane(jt);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        JPanel jp11 = new JPanel();
        jb3 = new JButton("Sort doctor list in alphabetical order by surname");

        /*
        creating JComponents required for Add New Consultation
         */
        jp12 = new JPanel();
        JPanel jp13 = new JPanel();
        JLabel jl7 = new JLabel("\s\s\sAdd New Consultation");
        JLabel jl8 = new JLabel("\s\s Date : " + LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue()
                + "-" + LocalDate.now().getDayOfMonth() + "\s\s\s\s");
        jp14 = new JPanel();
        JPanel jp15 = new JPanel();
        JPanel jp16 = new JPanel();
        JLabel jl9 = new JLabel("DOCTOR NAME : ");
        jcb1 = new JComboBox<>(applicationController.addDoctorNames());
        JLabel jl10 = new JLabel("DATE : ");
        jtf1 = new JTextField();
        JLabel jl11 = new JLabel("CONSULTATION TIME :  ");
        jtf2 = new JTextField();
        JLabel jl12 = new JLabel("END TIME : ");
        jtf3 = new JTextField();
        JPanel jp17 = new JPanel();
        jb4 = new JButton("CHECK CONSULTATION DETAILS");
        jp18 = new JPanel();
        jl13 = new JLabel();
        jp19 = new JPanel();
        JPanel jp20 = new JPanel();
        JLabel jl14 = new JLabel("*PATIENT ID : ");
        JLabel jl15 = new JLabel("*PATIENT NAME : ");
        jtf4 = new JTextField();
        JLabel jl16 = new JLabel("*SURNAME : ");
        jtf5 = new JTextField();
        JLabel jl17 = new JLabel("*DOB : ");
        jtf6 = new JTextField();
        JLabel jl18 = new JLabel("*MOB NUMBER \s: ");
        jtf7 = new JTextField();
        jtf8 = new JTextField();
        JLabel jl19 = new JLabel("NOTES : ");
        jta1 = new JTextArea();
        jb5 = new JButton("Select Pictures");
        JPanel jp21 = new JPanel();
        JPanel jp22 = new JPanel();
        jl20 = new JLabel("");
        jb6 = new JButton("CALCULATE COST");
        JLabel jl22 = new JLabel("COST : ");
        jl23 = new JLabel("\s--");
        jb7 = new JButton("ADD PATIENT DETAILS");
        jl24 = new JLabel("");

        /*
        creating JComponents required for View Consultation
         */
        jp23 = new JPanel();
        JPanel jp24 = new JPanel();
        jp25 = new JPanel();
        JLabel jl25 = new JLabel("\s\sView/Delete Consultation");
        JPanel jp26 = new JPanel();
        jtf9 = new JTextField();
        jtf9.setHorizontalAlignment(SwingConstants.CENTER);
        jb9 = new JButton("Check");
        jcb2 = new JComboBox<>();
        jb10 = new JButton("Delete consultation");
        jl26 = new JLabel();
        JPanel jp27 = new JPanel();
        jp28 = new JPanel();
        JPanel jp29 = new JPanel();
        JPanel jp30 = new JPanel();
        JPanel jp31 = new JPanel();
        JPanel jp32 = new JPanel();
        jp33 = new JPanel();
        jp34 = new JPanel();
        JScrollPane jsp2 = new JScrollPane(jp33);
        JLabel jl27 = new JLabel("PATIENT ID : ");
        jtf10 = new JTextField();
        JLabel jl28 = new JLabel("NAME : ");
        jtf11 = new JTextField();
        JLabel jl29 = new JLabel("SURNAME : ");
        jtf12 = new JTextField();
        JLabel jl30 = new JLabel("DOB : ");
        jtf13 = new JTextField();
        JLabel jl31 = new JLabel("MOB-NUMBER : ");
        jtf14 = new JTextField();
        JLabel jl32 = new JLabel("DOCTOR\s\s\s: ");
        jtf15 = new JTextField();
        JLabel jl33 = new JLabel("CON-DATE : ");
        jtf16 = new JTextField();
        JLabel jl34 = new JLabel("CON-TIME : ");
        jtf17 = new JTextField();
        JLabel jl35 = new JLabel("COST : ");
        jl36 = new JLabel("--");
        JLabel jl37 = new JLabel("NOTES : ");
        jta2 = new JTextArea();
        JLabel jl38 = new JLabel("Empty consultation list");

        /*
        formatting common JComponents
         */
        jp1.setBackground(Color.WHITE);
        jp1.setLayout(new FlowLayout(FlowLayout.LEADING));
        jp1.setBorder(new EmptyBorder(5, 0, 5, 0));
        jl1.setOpaque(true);
        jl1.setBackground(Color.green);
        jl1.setFont(jl1.getFont().deriveFont(25f));
        jp2.setLayout(new BorderLayout(2, 2));
        jp3.setBackground(Color.WHITE);
        jp4.setLayout(new BorderLayout(2, 2));
        jp5.setBackground(Color.WHITE);
        jp6.setBackground(Color.WHITE);
        jl3.setFont(jl3.getFont().deriveFont(14f));
        jp6.setLayout(null);
        jb1.setHorizontalAlignment(SwingConstants.CENTER);
        jb1.setFont(jb1.getFont().deriveFont(12f));
        jb1.setFocusPainted(false);
        jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb1.setForeground(new Color(120, 120, 120));
        jb1.setBackground(Color.WHITE);
        jb2.setHorizontalAlignment(SwingConstants.CENTER);
        jb2.setFont(jb2.getFont().deriveFont(12f));
        jb2.setFocusPainted(false);
        jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb2.setForeground(new Color(120, 120, 120));
        jb2.setBackground(Color.WHITE);
        jb8.setHorizontalAlignment(SwingConstants.CENTER);
        jb8.setFont(jb8.getFont().deriveFont(12f));
        jb8.setFocusPainted(false);
        jb8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb8.setForeground(new Color(120, 120, 120));
        jb8.setBackground(Color.WHITE);
        jl4.setOpaque(true);
        jl4.setBackground(Color.WHITE);
        jl4.setPreferredSize(new Dimension(250, 30));
        jl4.setHorizontalAlignment(SwingConstants.CENTER);
        jl5.setForeground(Color.LIGHT_GRAY);
        jp7.setLayout(new GridBagLayout());

        /*
        formatting JComponents required for list all doctors
         */
        jp8.setLayout(new BorderLayout(2, 2));
        jp9.setLayout(new FlowLayout(FlowLayout.LEADING));
        jp9.setBackground(Color.WHITE);
        jl6.setFont(jl6.getFont().deriveFont(17.5f));
        jl6.setPreferredSize(new Dimension(500, 30));
        jp9.setLayout(new FlowLayout(FlowLayout.LEADING));
        jp10.setLayout(new FlowLayout());
        jp10.setBorder(new EmptyBorder(40, 0, 0, 0));
        jp10.setBackground(Color.WHITE);
        jt.setDefaultEditor(Object.class, null);
        jt.getTableHeader().setBackground(Color.BLACK);
        jt.getTableHeader().setForeground(Color.WHITE);
        jt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        jt.setGridColor(Color.BLACK);
        jt.setRowHeight(30);
        jt.setFont(jt.getFont().deriveFont(13.5f));
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        jsp1.setPreferredSize(new Dimension(800, 340));
        jsp1.setBorder(new EmptyBorder(0, 5, 0, 5));
        jb3.setFont(jb3.getFont().deriveFont(12f));
        jb3.setBackground(Color.WHITE);
        jb3.setFocusPainted(false);
        jb3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb3.setPreferredSize(new Dimension(320, 35));
        jp11.setBackground(Color.WHITE);

         /*
        formatting JComponents required for Add New Consultation
         */
        jp12.setLayout(new BorderLayout(2, 2));
        jp13.setLayout(new BorderLayout());
        jp13.setBackground(Color.WHITE);
        jl7.setFont(jl7.getFont().deriveFont(17.5f));
        jl7.setPreferredSize(new Dimension(500, 40));
        jl8.setFont(jl8.getFont().deriveFont(13f));
        jl8.setForeground(Color.LIGHT_GRAY);
        jp14.setLayout(new BorderLayout(2, 2));
        jp15.setBackground(Color.WHITE);
        jp16.setLayout(null);
        jp16.setBackground(new Color(242, 242, 242));
        jp16.setPreferredSize(new Dimension(820, 170));
        jcb1.setBackground(Color.WHITE);
        jcb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jl24.setFont(jl24.getFont().deriveFont(11.5f));
        jl24.setForeground(Color.RED);
        jtf1.setHorizontalAlignment(SwingConstants.CENTER);
        jtf1.setForeground(Color.LIGHT_GRAY);
        jtf1.setFont(jtf1.getFont().deriveFont(13.5f));
        jtf2.setHorizontalAlignment(SwingConstants.CENTER);
        jtf2.setForeground(Color.LIGHT_GRAY);
        jtf2.setFont(jtf2.getFont().deriveFont(14f));
        jtf3.setHorizontalAlignment(SwingConstants.CENTER);
        jtf3.setForeground(Color.LIGHT_GRAY);
        jtf3.setFont(jtf3.getFont().deriveFont(14f));
        jp17.setBackground(Color.WHITE);
        jb4.setFont(jb4.getFont().deriveFont(12f));
        jb4.setBackground(Color.WHITE);
        jb4.setFocusPainted(false);
        jb4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb4.setPreferredSize(new Dimension(300, 35));
        jp18.setBackground(Color.WHITE);
        jp18.setLayout(new GridBagLayout());
        jp19.setBackground(Color.WHITE);
        jp20.setPreferredSize(new Dimension(820, 325));
        jp20.setLayout(null);
        jl13.setForeground(Color.RED);
        jl14.setFont(jl14.getFont().deriveFont(12.5f));
        jl15.setFont(jl15.getFont().deriveFont(12.5f));
        jl16.setFont(jl16.getFont().deriveFont(12.5f));
        jl17.setFont(jl17.getFont().deriveFont(12.5f));
        jl18.setFont(jl18.getFont().deriveFont(12.5f));
        jl19.setFont(jl19.getFont().deriveFont(12.5f));
        jtf4.setHorizontalAlignment(SwingConstants.CENTER);
        jtf4.setForeground(Color.LIGHT_GRAY);
        jtf4.setFont(jtf4.getFont().deriveFont(13.5f));
        jtf5.setHorizontalAlignment(SwingConstants.CENTER);
        jtf5.setForeground(Color.LIGHT_GRAY);
        jtf5.setFont(jtf5.getFont().deriveFont(13.5f));
        jtf6.setHorizontalAlignment(SwingConstants.CENTER);
        jtf6.setForeground(Color.LIGHT_GRAY);
        jtf6.setFont(jtf6.getFont().deriveFont(13.5f));
        jtf7.setHorizontalAlignment(SwingConstants.CENTER);
        jtf7.setForeground(Color.LIGHT_GRAY);
        jtf7.setFont(jtf7.getFont().deriveFont(13.5f));
        jtf8.setHorizontalAlignment(SwingConstants.CENTER);
        jtf8.setForeground(Color.LIGHT_GRAY);
        jtf8.setFont(jtf8.getFont().deriveFont(13.5f));
        jta1.setForeground(Color.LIGHT_GRAY);
        jta1.setFont(jta1.getFont().deriveFont(13.5f));
        jta1.setBorder(new EmptyBorder(4, 5, 4, 5));
        jta1.setLineWrap(true);
        jb5.setBackground(Color.WHITE);
        jb5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jp21.setBackground(Color.WHITE);
        jp21.setBorder(new EmptyBorder(2, 0, 2, 0));
        jb6.setHorizontalAlignment(SwingConstants.CENTER);
        jb6.setBackground(Color.WHITE);
        jb6.setFocusPainted(false);
        jb7.setEnabled(false);
        jb7.setFont(jb7.getFont().deriveFont(12f));
        jb7.setPreferredSize(new Dimension(300, 37));
        jb7.setBackground(Color.WHITE);
        jb7.setFocusPainted(false);
        jb7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jp22.setLayout(new GridBagLayout());
        jp22.setPreferredSize(new Dimension(820, 45));
        jp22.setBackground(Color.WHITE);
        jl20.setForeground(Color.RED);
        jl22.setFont(jl22.getFont().deriveFont(18f));
        jl23.setFont(jl23.getFont().deriveFont(18f));
        jl23.setForeground(Color.RED);

        /*
        formatting JComponents required for View Consultation
         */
        jp23.setLayout(new BorderLayout(2, 2));
        jp24.setLayout(new BorderLayout(2, 2));
        jp25.setLayout(new GridBagLayout());
        jp25.setBackground(Color.WHITE);
        jl25.setFont(jl25.getFont().deriveFont(17.5f));
        jl25.setPreferredSize(new Dimension(500, 30));
        jp26.setBackground(Color.WHITE);
        jp27.setLayout(new FlowLayout(FlowLayout.LEADING));
        jp27.setBackground(Color.WHITE);
        jtf9.setPreferredSize(new Dimension(160, 29));
        jtf9.setFont(jta1.getFont().deriveFont(13.5f));
        jb9.setPreferredSize(new Dimension(100, 25));
        jb9.setBackground(Color.WHITE);
        jb9.setFocusPainted(false);
        jb9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jcb2.setPreferredSize(new Dimension(350, 27));
        jcb2.setBackground(Color.WHITE);
        jcb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb10.setPreferredSize(new Dimension(150, 29));
        jb10.setBackground(Color.WHITE);
        jb10.setFocusPainted(false);
        jb10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jl26.setForeground(Color.RED);
        jp28.setLayout(new BorderLayout());
        jp31.setPreferredSize(new Dimension(823, 220));
        jp31.setLayout(null);
        jp31.setBackground(Color.WHITE);
        jp32.setPreferredSize(new Dimension(823, 230));
        jp34.setLayout(new GridBagLayout());
        jsp2.setPreferredSize(new Dimension(820, 225));
        jtf10.setFont(jtf10.getFont().deriveFont(13.5f));
        jtf10.setHorizontalAlignment(SwingConstants.CENTER);
        jtf10.setEditable(false);
        jtf11.setFont(jtf11.getFont().deriveFont(13.5f));
        jtf11.setHorizontalAlignment(SwingConstants.CENTER);
        jtf11.setEditable(false);
        jtf12.setFont(jtf12.getFont().deriveFont(13.5f));
        jtf12.setHorizontalAlignment(SwingConstants.CENTER);
        jtf12.setEditable(false);
        jtf13.setFont(jtf13.getFont().deriveFont(13.5f));
        jtf13.setHorizontalAlignment(SwingConstants.CENTER);
        jtf13.setEditable(false);
        jtf14.setFont(jtf14.getFont().deriveFont(13.5f));
        jtf14.setHorizontalAlignment(SwingConstants.CENTER);
        jtf14.setEditable(false);
        jtf15.setFont(jtf15.getFont().deriveFont(13.5f));
        jtf15.setHorizontalAlignment(SwingConstants.CENTER);
        jtf15.setEditable(false);
        jtf16.setFont(jtf16.getFont().deriveFont(13.5f));
        jtf16.setHorizontalAlignment(SwingConstants.CENTER);
        jtf16.setEditable(false);
        jtf17.setFont(jtf17.getFont().deriveFont(13.5f));
        jtf17.setHorizontalAlignment(SwingConstants.CENTER);
        jtf17.setEditable(false);
        jta2.setLineWrap(true);
        jta2.setFont(jta2.getFont().deriveFont(14f));
        jta2.setBackground(new Color(246, 246, 246));
        jta2.setBorder(new EmptyBorder(1, 5, 1, 5));
        jta2.setEditable(false);
        jl36.setFont(jl36.getFont().deriveFont(17f));
        jl38.setForeground(Color.LIGHT_GRAY);
        jl38.setFont(jl38.getFont().deriveFont(14.5f));

        /*
        placing JComponents in JFrame and JPanel
         */
        jb1.setBounds(7, 7, 245, 34);
        jb2.setBounds(7, 46, 245, 34);
        jb8.setBounds(7, 85, 245, 34);
        jl9.setBounds(15, 20, 130, 30);
        jcb1.setBounds(123, 20, 200, 30);
        jl24.setBounds(340, 0, 400, 70);
        jl10.setBounds(15, 70, 50, 30);
        jtf1.setBounds(63, 71, 160, 28);
        jl11.setBounds(240, 72, 150, 30);
        jtf2.setBounds(390, 72, 160, 30);
        jl12.setBounds(570, 72, 150, 30);
        jtf3.setBounds(648, 72, 160, 30);
        jp17.setBounds(0, 117, 820, 48);
        jl14.setBounds(303, 55, 250, 30);
        jl15.setBounds(5, 10, 115, 30);
        jtf4.setBounds(121, 10, 165, 30);
        jl16.setBounds(308, 10, 100, 30);
        jtf5.setBounds(394, 10, 165, 30);
        jl17.setBounds(580, 10, 100, 30);
        jtf6.setBounds(631, 10, 165, 30);
        jl18.setBounds(7, 55, 110, 30);
        jtf7.setBounds(121, 55, 165, 30);
        jtf8.setBounds(394, 55, 165, 30);
        jl19.setBounds(10, 100, 100, 30);
        jta1.setBounds(70, 104, 610, 62);
        jb5.setBounds(690, 120, 120, 30);
        jp21.setBounds(0, 220, 820, 50);
        jb6.setBounds(338, 179, 145, 27);
        jl22.setBounds(692, 176, 70, 35);
        jl23.setBounds(762, 179, 150, 30);

        /*
        placing JComponents in View Consultation JPanel
         */
        jl32.setBounds(17, 8, 110, 30);
        jtf15.setBounds(92, 8, 165, 30);
        jl33.setBounds(284, 8, 120, 30);
        jtf16.setBounds(369, 8, 165, 30);
        jl34.setBounds(565, 8, 120, 30);
        jtf17.setBounds(640, 8, 165, 30);
        jl27.setBounds(12, 50, 110, 30);
        jtf10.setBounds(92, 50, 165, 30);
        jl28.setBounds(313, 50, 120, 30);
        jtf11.setBounds(369, 50, 165, 30);
        jl29.setBounds(565, 50, 120, 30);
        jtf12.setBounds(640, 50, 165, 30);
        jl30.setBounds(50, 93, 110, 30);
        jtf13.setBounds(92, 93, 145, 30);
        jl31.setBounds(264, 93, 120, 30);
        jtf14.setBounds(369, 93, 165, 30);
        jl35.setBounds(590, 93, 120, 30);
        jl36.setBounds(640, 93, 120, 30);
        jl37.setBounds(25, 132, 60, 30);
        jta2.setBounds(85, 136, 715, 77);

        /*
        adding JComponents to the relevant JPanel (common)
         */
        jp1.add(jl1);
        jp2.add(jp3, BorderLayout.NORTH);
        jp2.add(jp4, BorderLayout.CENTER);
        jp2.add(jl4, BorderLayout.SOUTH);
        jp3.add(jl2);
        jp4.add(jp5, BorderLayout.NORTH);
        jp4.add(jp6, BorderLayout.CENTER);
        jp5.add(jl3);
        jp6.add(jb1);
        jp6.add(jb2);
        jp6.add(jb8);
        jp7.add(jl5);

        /*
        adding JComponents to the relevant JPanel (list all doctors)
         */
        jp8.add(jp9, BorderLayout.NORTH);
        jp9.add(jl6);
        jp8.add(jp10, BorderLayout.CENTER);
        jp10.add(jsp1);
        jp8.add(jp11, BorderLayout.SOUTH);
        jp11.add(jb3);

        /*
        adding JComponents to the relevant JPanel (Add New Consultation)
         */
        jp12.add(jp13, BorderLayout.NORTH);
        jp13.add(jl7, BorderLayout.WEST);
        jp13.add(jl8, BorderLayout.EAST);
        jp12.add(jp14, BorderLayout.CENTER);
        jp14.add(jp15, BorderLayout.NORTH);
        jp15.add(jp16);
        jp16.add(jl9);
        jp16.add(jcb1);
        jp16.add(jl24);
        jp16.add(jl10);
        jp16.add(jtf1);
        jp16.add(jl11);
        jp16.add(jtf2);
        jp16.add(jl12);
        jp16.add(jtf3);
        jp16.add(jp17);
        jp17.add(jb4);
        jp14.add(jp18, BorderLayout.CENTER);
        jp18.add(jl13);
        jp19.add(jp20);
        jp20.add(jl14);
        jp20.add(jl15);
        jp20.add(jtf4);
        jp20.add(jl16);
        jp20.add(jtf5);
        jp20.add(jl17);
        jp20.add(jtf6);
        jp20.add(jl18);
        jp20.add(jtf7);
        jp20.add(jtf8);
        jp20.add(jl19);
        jp20.add(jta1);
        jp20.add(jb5);
        jp20.add(jp21);
        jp20.add(jb6);
        jp20.add(jl22);
        jp20.add(jl23);
        jp21.add(jb7);
        jp14.add(jp22, BorderLayout.SOUTH);
        jp22.add(jl20);

        /*
        adding JComponents to the relevant JPanel (View Consultation)
         */
        jp23.add(jp24, BorderLayout.NORTH);
        jp23.add(jp25, BorderLayout.CENTER);
        jp25.add(jl26);
        jp27.add(jl25);
        jp24.add(jp27, BorderLayout.NORTH);
        jp26.add(jtf9);
        jp26.add(jb9);
        jp26.add(jcb2);
        jp26.add(jb10);
        jp24.add(jp26, BorderLayout.SOUTH);
        jp28.add(jp29, BorderLayout.NORTH);
        jp28.add(jp30, BorderLayout.CENTER);
        jp29.add(jp31);
        jp30.add(jp32);
        jp31.add(jl32);
        jp31.add(jtf15);
        jp31.add(jl33);
        jp31.add(jtf16);
        jp31.add(jl34);
        jp31.add(jtf17);
        jp31.add(jl27);
        jp31.add(jtf10);
        jp31.add(jl28);
        jp31.add(jtf11);
        jp31.add(jl29);
        jp31.add(jtf12);
        jp31.add(jl30);
        jp31.add(jtf13);
        jp31.add(jl31);
        jp31.add(jtf14);
        jp31.add(jl35);
        jp31.add(jl36);
        jp31.add(jl37);
        jp31.add(jta2);
        jp32.add(jsp2);
        jp34.add(jl38);

        /*
        changing JFrame properties
         */
        this.setTitle("Westminster Skin Consultation Manager v1.01");
        this.setMinimumSize(new Dimension(1100, 650));
        this.getContentPane().setLayout(new BorderLayout(2, 2));
        this.add(jp1, BorderLayout.NORTH);
        this.add(jp2, BorderLayout.WEST);
        this.add(jp7, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("src/main/assets/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        applicationController.setTableHeaders(dtm); // setting table headers
        applicationController.justifyTableData(jt, dtcr); //making JTable items center

        /*
        adding listeners to required JComponents
         */
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb8.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        jb5.addActionListener(this);
        jb6.addActionListener(this);
        jb7.addActionListener(this);
        jb9.addActionListener(this);
        jb10.addActionListener(this);
        jb1.addMouseListener(this);
        jb2.addMouseListener(this);
        jb8.addMouseListener(this);
        jtf1.addFocusListener(this);
        jtf2.addFocusListener(this);
        jtf3.addFocusListener(this);
        jtf4.addFocusListener(this);
        jtf5.addFocusListener(this);
        jtf6.addFocusListener(this);
        jtf7.addFocusListener(this);
        jtf8.addFocusListener(this);
        jta1.addFocusListener(this);
        jtf9.addFocusListener(this);
    }

    /*
    choosing which JButton is press and make the required happen accordingly
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            jb3.setEnabled(true);
            this.remove(jp23);
            this.remove(jp34);
            if (WestminsterSkinConsultationManager.doctorArray.isEmpty()) {// checking if the doctors are available in the center
                jl5.setText("CURRENTLY NO DOCTORS HAVE BEEN ADDED TO CLINIC");
                this.add(jp7, BorderLayout.CENTER); //changing the JPanel dynamically
            } else {
                this.remove(jp7);
                this.remove(jp12);
                applicationController.setTableData(dtm, false); //adding data rows to JTable
                this.add(jp8, BorderLayout.CENTER); //changing the JPanel dynamically
            }
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == jb2) {
            this.remove(jp23);
            this.remove(jp34);
            if (WestminsterSkinConsultationManager.doctorArray.isEmpty()) { // checking if the doctors are available in the center
                jl5.setText("CURRENTLY NO DOCTORS HAVE BEEN ADDED TO CLINIC");
                this.add(jp7, BorderLayout.CENTER); //changing the JPanel dynamically
            } else {
                jcb1.setEnabled(true);
                jtf1.setText("format : YYYY-MM-DD");
                jtf1.setForeground(Color.LIGHT_GRAY);
                jtf1.setEnabled(true);
                jtf2.setText("format (24H) : HH:MM");
                jtf2.setForeground(Color.LIGHT_GRAY);
                jtf2.setEnabled(true);
                jtf3.setText("format (24H) : HH:MM");
                jtf3.setForeground(Color.LIGHT_GRAY);
                jtf3.setEnabled(true);
                jb4.setEnabled(true);
                jl24.setText("");
                jl13.setText("");
                jtf4.setText("ENTER NAME");
                jtf4.setForeground(Color.LIGHT_GRAY);
                jtf4.setEnabled(true);
                jtf5.setText("ENTER SURNAME");
                jtf5.setForeground(Color.LIGHT_GRAY);
                jtf5.setEnabled(true);
                jtf6.setText("DOB (YYYY-MM-DD)");
                jtf6.setForeground(Color.LIGHT_GRAY);
                jtf6.setEnabled(true);
                jtf7.setText("07XXXXXXXX");
                jtf7.setForeground(Color.LIGHT_GRAY);
                jtf7.setEnabled(true);
                jtf8.setText("ENTER PATIENT ID");
                jtf8.setForeground(Color.LIGHT_GRAY);
                jtf8.setEnabled(true);
                jta1.setText("\sADD NOTES (OPTIONAL)");
                jta1.setForeground(Color.LIGHT_GRAY);
                jta1.setEnabled(true);
                jb5.setEnabled(true);
                jb6.setEnabled(true);
                jb7.setEnabled(false);
                jfc = new JFileChooser();
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setFileFilter(new FileNameExtensionFilter("images\s\s(*.jpg\s/\s*.jpeg\s/\s*.png\s/\s*.gif)"
                        , "jpg", "jpeg","png","gif"));//only allowing user to add only images
                jfc.setMultiSelectionEnabled(true);
                jl23.setText("\s--");
                jl20.setText("");
                jp14.remove(jp19);
                jp14.add(jp18, BorderLayout.CENTER); //changing the JPanel dynamically
                this.remove(jp7);
                this.remove(jp8);
                this.add(jp12, BorderLayout.CENTER); //changing the JPanel dynamically
            }
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == jb8) {
            jl26.setText("");
            this.remove(jp7);
            this.remove(jp8);
            this.remove(jp12);
            if (WestminsterSkinConsultationManager.consultationArray.isEmpty()) {
                flag = false;
                this.remove(jp23);
                this.add(jp34, BorderLayout.CENTER); //changing the JPanel dynamically
            } else {
                jtf9.setText("ENTER PATIENT ID");
                jl26.setText(flag ? "Successfully deleted the consultation" : "");
                flag = false;
                jtf9.setForeground(Color.LIGHT_GRAY);
                jcb2.setEnabled(false);
                jb10.setEnabled(false);
                jp23.remove(jp28);
                jp23.add(jp25, BorderLayout.CENTER); //changing the JPanel dynamically
                jp23.revalidate();
                jp23.repaint();
                this.remove(jp34);
                this.add(jp23, BorderLayout.CENTER); //changing the JPanel dynamically
            }
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == jb3) {
            applicationController.setTableData(dtm, true);
            jb3.setEnabled(false);
        } else if (e.getSource() == jb4) {
            /*
            validating input data
             */
            if (applicationController.checkForEmptyFields(new JTextField[]{jtf1, jtf2, jtf3})) {
                boolean[] array = applicationController.isDataValid(new JTextField[]{jtf1, jtf2, jtf3}, jl13);
                if (array[0] && array[1] && array[2] && array[3] && array[4]) {
                    if (applicationController.checkDoctorAvailability(jcb1,
                            applicationController.getDate(jtf1.getText().trim()),
                            applicationController.getTime(jtf2.getText().trim()),
                            applicationController.getTime(jtf3.getText().trim()),
                            jl24)) {
                        jcb1.setEnabled(false);
                        jtf1.setEnabled(false);
                        jtf2.setEnabled(false);
                        jtf3.setEnabled(false);
                        jb4.setEnabled(false);
                        jp14.remove(jp18);
                        jp14.add(jp19, BorderLayout.CENTER);//changing the JPanel dynamically
                    } else {
                        jtf1.setText("format : YYYY-MM-DD");
                        jtf1.setForeground(Color.LIGHT_GRAY);
                        jtf2.setText("format (24H) : HH:MM");
                        jtf2.setForeground(Color.LIGHT_GRAY);
                        jtf3.setText("format (24H) : HH:MM");
                        jtf3.setForeground(Color.LIGHT_GRAY);
                    }
                } else if (!array[0]) {
                    jp14.remove(jp19);
                    jp14.add(jp18, BorderLayout.CENTER);//changing the JPanel dynamically
                } else if (!array[1]) {
                    jl13.setText(jl13.getText() + " (CONSULTATION START TIME)");
                    jp14.remove(jp19);
                    jp14.add(jp18, BorderLayout.CENTER);//changing the JPanel dynamically
                } else if (!array[2]) {
                    jl13.setText(jl13.getText() + " (CONSULTATION END TIME)");
                    jp14.remove(jp19);
                    jp14.add(jp18, BorderLayout.CENTER);//changing the JPanel dynamically
                } else if (!array[3]) {
                    jl13.setText("CANNOT MAKE AN OVER DUE CONSULTATION");
                    jp14.remove(jp19);
                    jp14.add(jp18, BorderLayout.CENTER);//changing the JPanel dynamically
                } else {
                    jl13.setText("CONSULTATION START TIME AND END TIME IS NOT MATCHING (END TIME SHOULD BE A TIME AFTER START TIME)");
                    jp14.remove(jp19);
                    jp14.add(jp18, BorderLayout.CENTER);//changing the JPanel dynamically
                }
            } else {
                jl13.setText("ALL THE FIELDS MUST BE FILLED OUT");
                jp14.remove(jp19);
                jp14.add(jp18, BorderLayout.CENTER);//changing the JPanel dynamically
            }
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == jb5) {
            jfc.showOpenDialog(this);
        } else if (e.getSource() == jb6) {
            if (applicationController.checkForEmptyFields(new JTextField[]{jtf4, jtf5, jtf6, jtf7, jtf8})) {
                if (!jtf8.getText().trim().isEmpty()) {
                    boolean[] array = applicationController.isDataValid(jtf6, jtf7, jl20);
                    if (array[0] && array[1]) {
                        jtf4.setEnabled(false);
                        jtf5.setEnabled(false);
                        jtf6.setEnabled(false);
                        jtf7.setEnabled(false);
                        jtf8.setEnabled(false);
                        jta1.setEnabled(false);
                        jb5.setEnabled(false);
                        jb6.setEnabled(false);
                        jl23.setText("\s€" + applicationController.calculateCost(applicationController.getTime(jtf2.getText().trim()),
                                applicationController.getTime(jtf3.getText().trim()), jtf8.getText().trim()));
                        jb7.setEnabled(true);
                    } else if (array[0]) jl20.setText("INVALID MOBILE NUMBER");
                } else {
                    jtf8.setText("ENTER PATIENT ID");
                    jtf8.setForeground(Color.LIGHT_GRAY);
                    jl20.setText("PATIENT ID SHOULD HAVE A VALUE (CAN NOT CONTAIN ONLY SPACES).");
                }
            } else {
                jl20.setText("ALL THE FIELDS STARTING WITH `*` MUST BE FILLED");
            }
        } else if (e.getSource() == jb7) {
            try {
                if (applicationController.addConsultation(jcb1, new JTextField[]{jtf1, jtf2, jtf3, jtf4, jtf5, jtf6,
                        jtf7, jtf8}, jta1, jl23, jfc)) {
                    Object[] array = new Object[]{"Ok"};
                    UIManager.put("Button.background", Color.WHITE);
                    int option = JOptionPane.showOptionDialog(jp12, "Consultation added successfully",
                            "Westminster Skin Clinic", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, array, array[0]);
                    if (option == -1 || option == 0) {
                        jb2.doClick();
                    }
                }
            } catch (Exception ex) {
                jl20.setText("SOMETHING WENT WRONG. PLEASE TRY AGAIN");
            }
        } else if (e.getSource() == jb9) {
            jcb2.removeActionListener(this);
            jcb2.removeAllItems();
            jcb2.setEnabled(false);
            jb10.setEnabled(false);
            if (applicationController.checkForEmptyFields(new JTextField[]{jtf9})) {
                if (applicationController.addConsultationList(jtf9.getText().trim(), jcb2)) {
                    flag =false;
                    jp23.remove(jp25);
                    jcb2.addActionListener(this);
                    jcb2.setSelectedIndex(0);
                    jcb2.setEnabled(true);
                    jp23.add(jp28, BorderLayout.CENTER); //changing the JPanel dynamically
                } else {
                    if (flag) {
                        jb8.doClick();
                    } else {
                        jl26.setText("NO CONSULTATIONS (MAY BE AN INVALID PATIENT ID HAS ENTERED)");
                        jp23.remove(jp28);
                        jp23.add(jp25, BorderLayout.CENTER); //changing the JPanel dynamically
                    }
                }
            } else {
                jl26.setText("PATIENT ID NEED BE FILLED IN ORDER TO PROCESS");
                jp23.remove(jp28);
                jp23.add(jp25, BorderLayout.CENTER); //changing the JPanel dynamically
            }
            jp23.revalidate();
            jp23.repaint();
        } else if (e.getSource() == jb10) {
            if (applicationController.deleteConsultation(jtf9.getText().trim(), jcb2.getSelectedIndex() - 1)) {
                flag = true;
                jb9.doClick();
            }
        } else if (e.getSource() == jcb2) {
            jb10.setEnabled(false);
            jp33.removeAll();
            jp33.revalidate();
            jp33.repaint();
            if (applicationController.getSelectedConsultation(jcb2.getSelectedIndex())) {
                jb10.setEnabled(true);
                applicationController.setConsultationData(jtf9.getText().trim(), jcb2.getSelectedIndex() - 1,
                        new JTextField[]{jtf10, jtf11, jtf12, jtf13, jtf14, jtf15, jtf16, jtf17}, jta2, jl36, jp33);
            } else {
                jtf10.setText("--");
                jtf11.setText("--");
                jtf12.setText("--");
                jtf13.setText("--");
                jtf14.setText("--");
                jtf15.setText("--");
                jtf16.setText("--");
                jtf17.setText("--");
                jta2.setText("--");
                jl36.setText("--");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /*
    JButton press animation
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == jb1) jb1.setBounds(10, 7, 239, 34);
        else if (e.getSource() == jb2) jb2.setBounds(10, 46, 239, 34);
        else if (e.getSource() == jb8) jb8.setBounds(10, 85, 239, 34);
    }

    /*
    JButton press animation
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == jb1) jb1.setBounds(7, 7, 245, 34);
        else if (e.getSource() == jb2) jb2.setBounds(7, 46, 245, 34);
        else if (e.getSource() == jb8) jb8.setBounds(7, 85, 245, 34);
    }

    /*
    JButton hover animation
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setForeground(Color.BLACK);
    }

    /*
    JButton hover animation
     */
    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setForeground(new Color(120, 120, 120));
    }

    /*
    removing placeholders of JTextFields and JTextArea
     */
    @Override
    public void focusGained(FocusEvent e) {
        jl13.setText("");
        jl20.setText("");
        jl26.setText("");
        if (e.getSource() == jtf1) {
            if (jtf1.getText().equals("format : YYYY-MM-DD")) {
                jtf1.setForeground(Color.DARK_GRAY);
                jtf1.setText("");
                jl24.setText("");
            }
        } else if (e.getSource() == jtf2) {
            if (jtf2.getText().equals("format (24H) : HH:MM")) {
                jtf2.setForeground(Color.DARK_GRAY);
                jtf2.setText("");
                jl24.setText("");
            }
        } else if (e.getSource() == jtf3) {
            if (jtf3.getText().equals("format (24H) : HH:MM")) {
                jtf3.setForeground(Color.DARK_GRAY);
                jtf3.setText("");
                jl24.setText("");
            }
        } else if (e.getSource() == jtf4) {
            if (jtf4.getText().equals("ENTER NAME")) {
                jtf4.setForeground(Color.DARK_GRAY);
                jtf4.setText("");
            }
        } else if (e.getSource() == jtf5) {
            if (jtf5.getText().equals("ENTER SURNAME")) {
                jtf5.setForeground(Color.DARK_GRAY);
                jtf5.setText("");
            }
        } else if (e.getSource() == jtf6) {
            if (jtf6.getText().equals("DOB (YYYY-MM-DD)")) {
                jtf6.setForeground(Color.DARK_GRAY);
                jtf6.setText("");
            }
        } else if (e.getSource() == jtf7) {
            if (jtf7.getText().equals("07XXXXXXXX")) {
                jtf7.setForeground(Color.DARK_GRAY);
                jtf7.setText("");
            }
        } else if (e.getSource() == jtf8 || e.getSource() == jtf9) {
            if (((JTextField) e.getComponent()).getText().equals("ENTER PATIENT ID")) {
                e.getComponent().setForeground(Color.DARK_GRAY);
                ((JTextField) e.getComponent()).setText("");
            }
        }  else if (e.getSource() == jta1) {
            if (jta1.getText().equals("\sADD NOTES (OPTIONAL)")) {
                jta1.setForeground(Color.DARK_GRAY);
                jta1.setText("");
            }
        }
    }

    /*
    adding placeholders of JTextFields and JTextArea
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == jtf1) {
            if (jtf1.getText().isEmpty()) {
                jtf1.setForeground(Color.LIGHT_GRAY);
                jtf1.setText("format : YYYY-MM-DD");
            }
        } else if (e.getSource() == jtf2) {
            if (jtf2.getText().isEmpty()) {
                jtf2.setForeground(Color.LIGHT_GRAY);
                jtf2.setText("format (24H) : HH:MM");
            }
        } else if (e.getSource() == jtf3) {
            if (jtf3.getText().isEmpty()) {
                jtf3.setForeground(Color.LIGHT_GRAY);
                jtf3.setText("format (24H) : HH:MM");
            }
        } else if (e.getSource() == jtf4) {
            if (jtf4.getText().isEmpty()) {
                jtf4.setForeground(Color.LIGHT_GRAY);
                jtf4.setText("ENTER NAME");
            }
        } else if (e.getSource() == jtf5) {
            if (jtf5.getText().isEmpty()) {
                jtf5.setForeground(Color.LIGHT_GRAY);
                jtf5.setText("ENTER SURNAME");
            }
        } else if (e.getSource() == jtf6) {
            if (jtf6.getText().isEmpty()) {
                jtf6.setForeground(Color.LIGHT_GRAY);
                jtf6.setText("DOB (YYYY-MM-DD)");
            }
        } else if (e.getSource() == jtf7) {
            if (jtf7.getText().isEmpty()) {
                jtf7.setForeground(Color.LIGHT_GRAY);
                jtf7.setText("07XXXXXXXX");
            }
        } else if (e.getSource() == jtf8 || e.getSource() == jtf9) {
            if (((JTextField) e.getComponent()).getText().isEmpty()) {
                e.getComponent().setForeground(Color.LIGHT_GRAY);
                ((JTextField) e.getComponent()).setText("ENTER PATIENT ID");
            }
        } else if (e.getSource() == jta1) {
            if (jta1.getText().isEmpty()) {
                jta1.setForeground(Color.LIGHT_GRAY);
                jta1.setText("\sADD NOTES (OPTIONAL)");
            }
        }
    }
}
