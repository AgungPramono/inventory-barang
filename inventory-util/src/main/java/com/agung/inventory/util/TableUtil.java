/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.inventory.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.search.AbstractSearchable;

/**
 *
 * @author agung
 */
public class TableUtil {
    
    /**
     * gainsboro
     */
    public static final  Color TABLE_COLOR_FOREGROUND = new Color(220,220,220);
    /**
     * white
     */
    public static final  Color TABLE_COLOR_BACKGROUND = new Color(255,255,255);
    /**
     * dodger blue
     */
    public static final  Color ROW_SELECTED_COLOR = new Color(30,144,255);

    public static void initColumn(JTable jTable1, JScrollPane scrollPane) {
        JTableHeader header = jTable1.getTableHeader();
        int rowCount = jTable1.getRowCount();

        Enumeration columns = jTable1.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) jTable1.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(jTable1, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) jTable1.getCellRenderer(row, col).getTableCellRendererComponent(jTable1,
                        jTable1.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // this line is very important
            width += 50;
            column.setWidth(width + jTable1.getIntercellSpacing().width);
        }

        JTable rownomber = new RowNumberTable(jTable1);
        scrollPane.setRowHeaderView(rownomber);
    }
    
    public static void formatTable(JXTable table){
         table.setHighlighters(HighlighterFactory.createAlternateStriping(TABLE_COLOR_BACKGROUND,
                TABLE_COLOR_FOREGROUND
        ));
        table.setRowHeight(20);
        table.setColumnControlVisible(true);
        table.setSelectionBackground(ROW_SELECTED_COLOR);
        table.setGridColor(Color.LIGHT_GRAY);
        table.putClientProperty(AbstractSearchable.MATCH_HIGHLIGHTER, Boolean.TRUE);
        table.setBackground(table.getBackground());
        table.setRolloverEnabled(true);
    }
    
}

class RowNumberTable extends JTable
        implements ChangeListener, PropertyChangeListener, TableModelListener {

    private JTable main;

    public RowNumberTable(JTable table) {
        main = table;
        main.addPropertyChangeListener(this);
        main.getModel().addTableModelListener(this);

        setFocusable(false);
        setAutoCreateColumnsFromModel(false);
        setSelectionModel(main.getSelectionModel());

        TableColumn column = new TableColumn();
        column.setHeaderValue(" ");
        addColumn(column);
        column.setCellRenderer(new RowNumberRenderer());

        getColumnModel().getColumn(0).setPreferredWidth(50);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Component c = getParent();

        //  Keep scrolling of the row table in sync with the main table.
        if (c instanceof JViewport) {
            JViewport viewport = (JViewport) c;
            viewport.addChangeListener(this);
        }
    }

    /*
	 *  Delegate method to main table
     */
    @Override
    public int getRowCount() {
        return main.getRowCount();
    }

    @Override
    public int getRowHeight(int row) {
        int rowHeight = main.getRowHeight(row);

        if (rowHeight != super.getRowHeight(row)) {
            super.setRowHeight(row, rowHeight);
        }

        return rowHeight;
    }

    /*
	 *  No model is being used for this table so just use the row number
	 *  as the value of the cell.
     */
    @Override
    public Object getValueAt(int row, int column) {
        return Integer.toString(row + 1);
    }

    /*
	 *  Don't edit data in the main TableModel by mistake
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /*
	 *  Do nothing since the table ignores the model
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
    }
//
//  Implement the ChangeListener
//

    @Override
    public void stateChanged(ChangeEvent e) {
        //  Keep the scrolling of the row table in sync with main table

        JViewport viewport = (JViewport) e.getSource();
        JScrollPane scrollPane = (JScrollPane) viewport.getParent();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }
//
//  Implement the PropertyChangeListener
//

    public void propertyChange(PropertyChangeEvent e) {
        //  Keep the row table in sync with the main table

        if ("selectionModel".equals(e.getPropertyName())) {
            setSelectionModel(main.getSelectionModel());
        }

        if ("rowHeight".equals(e.getPropertyName())) {
            repaint();
        }

        if ("model".equals(e.getPropertyName())) {
            main.getModel().addTableModelListener(this);
            revalidate();
        }
    }

//
//  Implement the TableModelListener
//
    @Override
    public void tableChanged(TableModelEvent e) {
        revalidate();
    }

    /*
	 *  Attempt to mimic the table header renderer
     */
    private static class RowNumberRenderer extends DefaultTableCellRenderer {

        public RowNumberRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();

                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(header.getBackground());
                    setFont(header.getFont());
                }
            }

            if (isSelected) {
                setFont(getFont().deriveFont(Font.BOLD));
            }

            setText((value == null) ? "" : value.toString());
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));

            return this;
        }
    }
}
