/**
 * Copyright (C), 2015-2019, 南京云融金融信息服务有限公司
 * 文件名: Swing
 * 作者:   gaojing
 * 创建时间:     2019/6/19 14:00
 * 描述:
 * 历史修改:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 〈一句话功能简述〉<br> 
 * @author gaojing
 * @create 2019/6/19
 * @version 1.0
 */
public class Swing {

    private JPanel 工作区;
    private JTree tree1;
    private JList list1;

    public Swing() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(1111);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}