package com;

import cn.hutool.core.date.DateUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;


public class MsgLoadFrame extends JFrame implements ActionListener {
    /**
     * @author Roy_70
     * @date
     */
    private static final long serialVersionUID = -1189035634361220261L;
    private static Logger logger = Logger.getLogger(MsgLoadFrame.class);

    //项目位置
    private static String XMLPROJECT = "XMLPROJECT";
    //xml包路径
    private static String XMLPACKAGE = "XMLPACKAGE";

    //dao项目地址
    private static String DAOPROJECT = "DAOPROJECT";
    //dao包路径
    private static String DAOPACKAGE = "DAOPACKAGE";

    //do项目地址
    private static String DOPROJECT = "DOPROJECT";
    //do包路径
    private static String DOPACKAGE = "DOPACKAGE";

    //数据库连接地址
    private static String JDBCURL = "JDBCURL";
    //用户名
    private static String JDBCUSENAME = "JDBCUSENAME";
    //密码
    private static String JDBCPASSWORD = "JDBCPASSWORD";

    //表名
    private static String TABLENAME = "TABLENAME";
    //实体类名
    private static String DONAME = "DONAME";

    private static String PROPERTIESPATH = "";

    JFrame mainframe;
    JPanel panel;
    //创建相关的Label标签
    JLabel outfilepath_label = new JLabel("XML_PROJECT:");
    JLabel outxml_package_label = new JLabel("XML_PACKAGE:");
    JLabel outlogpath_label = new JLabel("DAO_PROJECT:");
    //dao 包名
    JLabel daopackage = new JLabel("DAO_PACKAGE:");
    JLabel outdo_label = new JLabel("DO_PROJECT:");
    //do 包名
    JLabel dopackage = new JLabel("DO_PACKAGE:");
    //jdbc连接
    JLabel jdbcurl = new JLabel("JDBC_URL:");
    JLabel name = new JLabel("JDBC_USERNAME:");
    JLabel pass = new JLabel("JDBC_PASSWORD:");

    JLabel tablename = new JLabel("TABLE_NAME:");
    JLabel beanname = new JLabel("DO_NAME:");


    //创建相关的文本域
    JTextField outfilepath_textfield = new JTextField(20);
    JTextField outxml_package_textfield = new JTextField(20);
    JTextField outlogpath_textfield = new JTextField(20);
    JTextField daopackage_textfield = new JTextField(20);
    JTextField outdo_textfield = new JTextField(20);
    JTextField dopackage_textfield = new JTextField(20);
    //jdbc连接
    JTextField jdbc_textfield = new JTextField(20);
    JTextField name_textfield = new JTextField(20);
    JTextField pass_textfield = new JTextField(20);
    //设置表名
    JTextField table_textfield = new JTextField(20);
    JTextField do_name_textfield = new JTextField(20);

    //创建滚动条以及输出文本域
    JScrollPane jscrollPane;
    JTextArea outtext_textarea = new JTextArea();
    //创建按钮
    JButton outfilepath_button = new JButton("...");
    JButton outlogpath_button = new JButton("...");
    JButton outdo_button = new JButton("...");
    JButton start_button = new JButton("生成");
    JButton clear_button = new JButton("一键清空");
    JButton help_button = new JButton("使用说明");
    JButton import_properties_button = new JButton("导入本地配置");

    public void show(){
        mainframe = new JFrame("../mybaties-generator-MYSQL版");
        // Setting the width and height of frame
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect=ge.getMaximumWindowBounds();
        int w=rect.width;
        int h=rect.height;
        mainframe.setSize(Integer.valueOf((int) (w*0.3)), Integer.valueOf((int) (h*0.79)));
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);//固定窗体大小

        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width/2; // 获取屏幕的宽
        int screenHeight = screenSize.height/2; // 获取屏幕的高
        int height = mainframe.getHeight(); //获取窗口高度
        int width = mainframe.getWidth(); //获取窗口宽度
        mainframe.setLocation(screenWidth-width/2, screenHeight-height/2);//将窗口设置到屏幕的中部
        //窗体居中，c是Component类的父窗口
        //mainframe.setLocationRelativeTo(c);
        Image myimage=kit.getImage("resourse/hxlogo.gif"); //由tool获取图像
        mainframe.setIconImage(myimage);
        initPanel();//初始化面板
        mainframe.add(panel);
        mainframe.setVisible(true);
    }
    /* 创建面板，这个类似于 HTML 的 div 标签
     * 我们可以创建多个面板并在 JFrame 中指定位置
     * 面板中我们可以添加文本字段，按钮及其他组件。
     */
    public void initPanel(){
        this.panel = new JPanel();
        panel.setLayout(null);
        //this.panel = new JPanel(new GridLayout(3,2)); //创建3行3列的容器
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        outfilepath_label.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.023), (int) (mainframe.getWidth()*0.208), (int) (mainframe.getHeight()*0.029));
        outfilepath_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.023), (int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        outfilepath_button.setBounds((int) (mainframe.getWidth()*0.904),(int) (mainframe.getHeight()*0.023), (int) (mainframe.getWidth()*0.052), (int) (mainframe.getHeight()*0.029));
        this.panel.add(outfilepath_label);
        this.panel.add(outfilepath_textfield);
        this.panel.add(outfilepath_button);

        outxml_package_label.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.059),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        outxml_package_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.059),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(outxml_package_label);
        this.panel.add(outxml_package_textfield);

        outlogpath_label.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.094),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        outlogpath_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.094),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        outlogpath_button.setBounds((int) (mainframe.getWidth()*0.904),(int) (mainframe.getHeight()*0.094), (int) (mainframe.getWidth()*0.052), (int) (mainframe.getHeight()*0.029));
        this.panel.add(outlogpath_label);
        this.panel.add(outlogpath_textfield);
        this.panel.add(outlogpath_button);

        daopackage.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.129),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        daopackage_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.129),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(daopackage);
        this.panel.add(daopackage_textfield);

        outdo_label.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.164),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        outdo_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.164),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        outdo_button.setBounds((int) (mainframe.getWidth()*0.904),(int) (mainframe.getHeight()*0.164), (int) (mainframe.getWidth()*0.052), (int) (mainframe.getHeight()*0.029));
        this.panel.add(outdo_label);
        this.panel.add(outdo_textfield);
        this.panel.add(outdo_button);

        dopackage.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.199),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        dopackage_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.199),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(dopackage);
        this.panel.add(dopackage_textfield);

        jdbcurl.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.234),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        jdbc_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.234),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(jdbcurl);
        this.panel.add(jdbc_textfield);

        name.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.269),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        name_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.269),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(name);
        this.panel.add(name_textfield);

        pass.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.304),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        pass_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.304),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(pass);
        this.panel.add(pass_textfield);

        tablename.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.339),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        table_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.339),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(tablename);
        this.panel.add(table_textfield);

        beanname.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.374),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        do_name_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.374),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        this.panel.add(beanname);
        this.panel.add(do_name_textfield);

        start_button.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.412), (int) (mainframe.getWidth()*0.139),(int) (mainframe.getHeight()*0.029));
        this.panel.add(start_button);

        clear_button.setBounds((int) (mainframe.getWidth()*0.296),(int) (mainframe.getHeight()*0.412),(int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.029));
        this.panel.add(clear_button);

        help_button.setBounds((int) (mainframe.getWidth()*0.522),(int) (mainframe.getHeight()*0.412),(int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.029));
        this.panel.add(help_button);

        import_properties_button.setBounds((int) (mainframe.getWidth()*0.748),(int) (mainframe.getHeight()*0.412),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        this.panel.add(import_properties_button);

        outtext_textarea.setEditable(false);
        outtext_textarea.setFont(new Font("标楷体", Font.BOLD, 16));
        //自动换行
        outtext_textarea.setLineWrap(true);
        jscrollPane = new JScrollPane(outtext_textarea);
        jscrollPane.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.447), (int) (mainframe.getWidth()*0.96), (int) (mainframe.getHeight()*0.51));
        this.panel.add(jscrollPane);
        //增加动作监听
        outfilepath_button.addActionListener(this);
        outlogpath_button.addActionListener(this);
        outdo_button.addActionListener(this);
        start_button.addActionListener(this);
        help_button.addActionListener(this);
        import_properties_button.addActionListener(this);
        clear_button.addActionListener(this);

        panel.addMouseMotionListener(new MouseAdapter() {
            private boolean top = false;
            private boolean down = false;
            private boolean left = false;
            private boolean right = false;
            private boolean drag = false;
            private Point lastPoint = null;
            private Point draggingAnchor = null;
            @Override
            public void mouseMoved(MouseEvent e) {
                if(  e.getPoint().getY() <= 3 && e.getPoint().getY() >= -3 ){
                    mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    top = true;
                }else if(Math.abs(e.getPoint().getY()-panel.getSize().getHeight()) <=3 && Math.abs(e.getPoint().getY()-panel.getSize().getHeight()) >=-3){
                    mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    down = true;
                }else if(e.getPoint().getX() <= 3 && e.getPoint().getX() >= -3){
                    mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    left = true;
                }else if(Math.abs(e.getPoint().getX()-panel.getSize().getWidth()) <=3 && Math.abs(e.getPoint().getX()-panel.getSize().getWidth()) >=-3){
                    mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    right = true;
                }else{
                    mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    draggingAnchor = new Point(e.getX() + panel.getX(), e.getY() + panel.getY());
                    top = false;
                    down = false;
                    left = false;
                    right = false;
                    drag = true;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Dimension dimension = mainframe.getSize();
                if(top){
                    dimension.setSize(dimension.getWidth() ,dimension.getHeight()-e.getY());
                    mainframe.setSize(dimension);
                    mainframe.setLocation(mainframe.getLocationOnScreen().x, mainframe.getLocationOnScreen().y + e.getY());
                }else if(down){
                    dimension.setSize(dimension.getWidth() , e.getY());
                    mainframe.setSize(dimension);
                }else if(left){
                    dimension.setSize(dimension.getWidth() - e.getX() ,dimension.getHeight() );
                    mainframe.setSize(dimension);
                    mainframe.setLocation(mainframe.getLocationOnScreen().x + e.getX(),mainframe.getLocationOnScreen().y );
                }else if(right){
                    dimension.setSize(e.getX(),dimension.getHeight());
                    mainframe.setSize(dimension);
                }else {
                    mainframe.setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
                }
                resetSize();
            }
        });

//        panel.addMouseMotionListener(new MouseAdapter() {
//             @Override
//             public void mouseMoved(MouseEvent e) {
//
//
//
//             };
//        });
//
//
//        panel.addComponentListener(new ComponentAdapter() {//拖动窗口监听
//            @Override
//            public void componentResized(ComponentEvent e) {
//                int whidth=mainframe.getWidth();//获取窗口宽度
//                int height=mainframe.getHeight();//获取窗口高度  你也可以设置高度居中
//            }
//
//        });
    }

    /**
     * creat_user: gaojing
     * describe:   重新计算各个模块的尺寸
     * creat_date: 2019/6/26
     * creat_time: 16:27
     **/
    public void resetSize(){
        outfilepath_label.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.023), (int) (mainframe.getWidth()*0.208), (int) (mainframe.getHeight()*0.029));
        outfilepath_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.023), (int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        outfilepath_button.setBounds((int) (mainframe.getWidth()*0.904),(int) (mainframe.getHeight()*0.023), (int) (mainframe.getWidth()*0.052), (int) (mainframe.getHeight()*0.029));

        outxml_package_label.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.059),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        outxml_package_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.059),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        outlogpath_label.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.094),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        outlogpath_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.094),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        outlogpath_button.setBounds((int) (mainframe.getWidth()*0.904),(int) (mainframe.getHeight()*0.094), (int) (mainframe.getWidth()*0.052), (int) (mainframe.getHeight()*0.029));

        daopackage.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.129),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        daopackage_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.129),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        outdo_label.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.164),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        outdo_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.164),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));
        outdo_button.setBounds((int) (mainframe.getWidth()*0.904),(int) (mainframe.getHeight()*0.164), (int) (mainframe.getWidth()*0.052), (int) (mainframe.getHeight()*0.029));

        dopackage.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.199),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        dopackage_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.199),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        jdbcurl.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.234),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        jdbc_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.234),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        name.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.269),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        name_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.269),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        pass.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.304),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        pass_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.304),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        tablename.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.339),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        table_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.339),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        beanname.setBounds((int) (mainframe.getWidth()*0.017),(int) (mainframe.getHeight()*0.374),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));
        do_name_textfield.setBounds((int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.374),(int) (mainframe.getWidth()*0.696),(int) (mainframe.getHeight()*0.029));

        start_button.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.412), (int) (mainframe.getWidth()*0.139),(int) (mainframe.getHeight()*0.029));

        clear_button.setBounds((int) (mainframe.getWidth()*0.296),(int) (mainframe.getHeight()*0.412),(int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.029));

        help_button.setBounds((int) (mainframe.getWidth()*0.522),(int) (mainframe.getHeight()*0.412),(int) (mainframe.getWidth()*0.209),(int) (mainframe.getHeight()*0.029));

        import_properties_button.setBounds((int) (mainframe.getWidth()*0.748),(int) (mainframe.getHeight()*0.412),(int) (mainframe.getWidth()*0.208),(int) (mainframe.getHeight()*0.029));

        //自动换行
        jscrollPane.setBounds((int) (mainframe.getWidth()*0.017), (int) (mainframe.getHeight()*0.447), (int) (mainframe.getWidth()*0.96), (int) (mainframe.getHeight()*0.51));
    }

    /**
     * 单击动作触发方法
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO Auto-generated method stub
        System.out.println(event.getActionCommand());
        if(event.getSource() == start_button){
            //确认对话框弹出
            int result = JOptionPane.showConfirmDialog(null, "是否开始生成?", "确认", 0);
            System.err.println(result);//YES_NO_OPTION
            if (result == JOptionPane.YES_NO_CANCEL_OPTION||result == JOptionPane.DEFAULT_OPTION) {//是：0，否：1，取消：2
                return;
            }

            String outxml_package = outxml_package_textfield.getText();
            String daopackage = daopackage_textfield.getText();
            String outdo = outdo_textfield.getText();
            String dopackage = dopackage_textfield.getText();
            String jdbc = jdbc_textfield.getText();
            String name = name_textfield.getText();
            String pass = pass_textfield.getText();
            String table = table_textfield.getText();
            String do_name = do_name_textfield.getText();
          //  System.out.println(infilepath_textfield.getText());
            if ( do_name.equals("")||table.equals("")||pass.equals("")
                    ||name.equals("")||jdbc.equals("")||dopackage.equals("")
                    ||outdo.equals("")||daopackage.equals("")||outxml_package.equals("")
                    ||outfilepath_textfield.getText().equals("")
                    || outlogpath_textfield.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "参数不能为空", "提示", 2);//弹出提示对话框，warning
                return;
            }else{
                outtext_textarea.append("");
                outtext_textarea.append("********************生成开始*****************"+ DateUtil.now()+"\n");
                /**
                 *
                 *
                 * 调用转换方法
                 *
                 *
                 */
                try {
                    Main.generator(outfilepath_textfield, outxml_package_textfield, outlogpath_textfield, daopackage_textfield, outdo_textfield, dopackage_textfield, jdbc_textfield, name_textfield, pass_textfield, table_textfield, do_name_textfield, outtext_textarea);
                    //创建一个配置文件
                    String directoriesPath = System.getenv("PUBLIC");
                    StringBuffer stringBuffer = new StringBuffer(directoriesPath);
                    stringBuffer.append("\\").append(do_name).append(".properties");
                    File file = new File(stringBuffer.toString());
                    //写入操作
                    writeFile(stringBuffer.toString(),outfilepath_textfield.getText(),outxml_package_textfield.getText(),
                            outlogpath_textfield.getText(),daopackage_textfield.getText(),outdo_textfield.getText(),
                            dopackage_textfield.getText(),jdbc_textfield.getText(),name_textfield.getText(),
                            pass_textfield.getText(),table_textfield.getText(),do_name_textfield.getText());
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                    outtext_textarea.append(e+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    outtext_textarea.append(e+"\n");
                } catch (XMLParserException e) {
                    e.printStackTrace();
                    outtext_textarea.append(e+"\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                    outtext_textarea.append(e+"\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    outtext_textarea.append(e+"\n");
                }
//                result = JOptionPane.showConfirmDialog(null, "是否打开日志文件?", "确认", 0);//YES_NO_OPTION
//                if (result == 0) {//是：0，否：1，取消：2
//                    try {
//                        @SuppressWarnings("unused")
//                        Process process = Runtime.getRuntime().exec("cmd.exe  /c notepad "+outlogpath+"\\log.log");//调用cmd方法使用记事本打开文件
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }else{
            //判断三个选择按钮并对应操作
            if(event.getSource() == outfilepath_button){
                File file = openChoseWindow(JFileChooser.DIRECTORIES_ONLY,null,null);
                if(file == null)
                    return;
                outfilepath_textfield.setText(file.getAbsolutePath());
            }else if(event.getSource() == outlogpath_button){
                File file = openChoseWindow(JFileChooser.DIRECTORIES_ONLY,null,null);
                if(file == null)
                    return;
                outlogpath_textfield.setText(file.getAbsolutePath());
            }else if(event.getSource() == outdo_button){
                File file = openChoseWindow(JFileChooser.DIRECTORIES_ONLY,null,null);
                if(file == null)
                    return;
                outdo_textfield.setText(file.getAbsolutePath());
            }else if(event.getSource() == help_button){
                openHelpWindow("一个测试");
            }else if(event.getSource() == import_properties_button){
                FileFilter filter = new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getName().endsWith(".properties");
                    }

                    @Override
                    public String getDescription() {
                        return ".properties";
                    }
                };
                File file = openChoseWindow(JFileChooser.FILES_ONLY,filter,System.getenv("PUBLIC"));
                if(file == null) {
                    return;
                }
                //回显配置文件中的数据到对应的输入框
                //读取操作
                try {
                    readFile(file,outfilepath_textfield,outxml_package_textfield,outlogpath_textfield,
                            daopackage_textfield,outdo_textfield,dopackage_textfield,
                            jdbc_textfield,name_textfield, pass_textfield,
                            table_textfield,do_name_textfield,outtext_textarea);
                } catch (IOException e) {
                    e.printStackTrace();
                    outtext_textarea.append(e+"\n");
                }
            }else if(event.getSource() == clear_button){
                outfilepath_textfield.setText("");
                outxml_package_textfield.setText("");
                outlogpath_textfield.setText("");
                daopackage_textfield.setText("");
                outdo_textfield.setText("");
                dopackage_textfield.setText("");
                jdbc_textfield.setText("");
                name_textfield.setText("");
                pass_textfield.setText("");
                table_textfield.setText("");
                do_name_textfield.setText("");
                outtext_textarea.append("清空完成\n");
            }
        }
    }

    /**
     * 打开选择文件窗口并返回文件
     * @param type
     * @return
     */
    public File openChoseWindow(int type,FileFilter fileFilter,String path){
        JFileChooser jfc=new JFileChooser(path);
        if(fileFilter!=null) {
            jfc.setFileFilter(fileFilter);
        }
        jfc.setFileSelectionMode(type);//选择的文件类型(文件夹or文件)
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        return file;
    }

    /**
     * creat_user: gaojing
     * describe:   弹出效果
     * creat_date: 2019/6/24
     * creat_time: 15:50
     **/
    public void openHelpWindow(String mess){
        JOptionPane.showMessageDialog(null, "点击生成将会在本地生成一个配置文件，可以导入该配置文件进行快速填充输入操作,文件存放在"+System.getenv("PUBLIC")+"目录下", "说明", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * creat_user: gaojing
     * describe:   读取文件
     * creat_date: 2019/6/24
     * creat_time: 16:59
     **/
    public void writeFile(String filePath,String xmlProject,String xmlPackage,String daoProject,String daoPackage,String doProject,String doPackage
            ,String jdbcUrl,String userName,String password,String tableName,String doName) throws IOException {
        Properties properties = new Properties();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        //设置属性
        properties.setProperty(XMLPROJECT, xmlProject);
        properties.setProperty(XMLPACKAGE, xmlPackage);
        properties.setProperty(DAOPROJECT, daoProject);
        properties.setProperty(DAOPACKAGE, daoPackage);
        properties.setProperty(DOPROJECT, doProject);
        properties.setProperty(DOPACKAGE, doPackage);
        properties.setProperty(JDBCURL, jdbcUrl);
        properties.setProperty(JDBCUSENAME, userName);
        properties.setProperty(JDBCPASSWORD, password);
        properties.setProperty(TABLENAME, tableName);
        properties.setProperty(DONAME, doName);
        properties.store(fileOutputStream, "mybaties快速生成配置文件");
    }

    /**
     * creat_user: gaojing
     * describe:   写入文件
     * creat_date: 2019/6/24
     * creat_time: 16:59
     **/
    public void readFile(File file,JTextField outfilepath_textfield,JTextField outxml_package_textfield,JTextField outlogpath_textfield,
                         JTextField daopackage_textfield,JTextField outdo_textfield,JTextField dopackage_textfield,
                         JTextField jdbc_textfield,JTextField name_textfield,JTextField pass_textfield,
                         JTextField table_textfield,JTextField do_name_textfield,JTextArea outtext_textarea) throws IOException {
        outtext_textarea.append("***********开始读取配置文件**********\n");
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file.getCanonicalPath());
        properties.load(fileInputStream);
        outfilepath_textfield.setText(properties.getProperty(XMLPROJECT));
        outxml_package_textfield.setText(properties.getProperty(XMLPACKAGE));
        outlogpath_textfield.setText(properties.getProperty(DAOPROJECT));
        daopackage_textfield.setText(properties.getProperty(DAOPACKAGE));
        outdo_textfield.setText(properties.getProperty(DOPROJECT));
        dopackage_textfield.setText(properties.getProperty(DOPACKAGE));
        jdbc_textfield.setText(properties.getProperty(JDBCURL));
        name_textfield.setText(properties.getProperty(JDBCUSENAME));
        pass_textfield.setText(properties.getProperty(JDBCPASSWORD));
        table_textfield.setText(properties.getProperty(TABLENAME));
        do_name_textfield.setText(properties.getProperty(DONAME));
        outtext_textarea.append("***********读取配置文件结束**********\n");
    }

    public void windowClosed(WindowEvent arg0) {
        System.exit(0);
    }
    public void windowClosing(WindowEvent arg0) {
        System.exit(0);
    }
}