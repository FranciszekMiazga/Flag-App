package zad1;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryTable {
    Flags flags = new Flags();
    private JTable countryTable;
    String countriesFileName;
    List<String> listName = new ArrayList<>();
    List<String> listCapitals = new ArrayList<>();
    List<Integer> listPopulation = new ArrayList<>();
    List<String> listHeaders = new ArrayList<>();


    public CountryTable(String countriesFileName) {
        this.countriesFileName = countriesFileName;

    }

    public JTable create() {
        regexFile();

        return countryTable;
    }

    public void regexFile() {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader(countriesFileName);

            int wrt;
            while ((wrt = fr.read()) != -1) {
                sb.append((char) wrt);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("(.+)[ \t](.+)[ \t].+[ \t](.+)");
        Matcher matcher = pattern.matcher(sb);

        int counter = 0;


        while (matcher.find()) {
            String name = matcher.group(1);
            String captials = matcher.group(2);
            String population = matcher.group(3);

            if (counter > 0) {
                int popInt = 0;
                try {
                    popInt = Integer.parseInt(population);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: 76 " + nfe.getMessage());
                }
                listPopulation.add(popInt);
                listName.add(name);
                listCapitals.add(captials);

            } else {
                listHeaders.add(name);
                listHeaders.add(captials);
                listHeaders.add(population);
                listHeaders.add("Flague");
                listHeaders.add("Remarks");

            }
            counter++;
        }
        doTable();


    }

    public void doTable() {

        int n = listHeaders.size();
        int k = listName.size();
        Object[][] data = new Object[k][n];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = listName.get(i);
                        break;
                    case 1:
                        data[i][j] = listCapitals.get(i);
                        break;
                    case 2:
                        data[i][j] = listPopulation.get(i);
                        break;
                }
                if (j == 3) {
                    doFlag(i,j,data);
                }

            }
        }

        DefaultTableModel model = new DefaultTableModel(data, listHeaders.toArray());
        countryTable = new JTable(model) {
            boolean[] canEdit = new boolean[]{
                    false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            public Class getColumnClass(int column) {
                if(column==3){
                    return Icon.class;
                }else if(column==2){
                    return Integer.class;
                }else
                    return Object.class;
            }

        };
        countryTable.setRowHeight(countryTable.getRowHeight() + 20);
        countryTable.getColumnModel().getColumn(0).setPreferredWidth(150);


        model.addTableModelListener(new TableModelListener() {
            int counter = 0;

            public void tableChanged(TableModelEvent e) {

                if (counter == 0) {
                    counter++;
                    int popInt2 = 0;
                    int myRow = e.getFirstRow();
                    int myColumn = e.getColumn();
                    try {
                        int editedV = (int) countryTable.getModel().getValueAt(myRow, myColumn);

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        countryTable.setValueAt(date, myRow, myColumn + 2);

                    } catch (NumberFormatException nfe) {
                        System.out.println("NumberFormatException: 162 " + nfe.getMessage());
                    }
                    if (popInt2 > 20000) {
                        int finalPopInt = popInt2;
                        int wrt = 0;
                        doColor(myRow,myColumn,data);

                    }
                    countryTable.getColumnModel().getColumn(4).setPreferredWidth(250);


                }
                if (countryTable.isEditing()) {
                    counter = 0;
                }
            }

        });
        doColor(0,2,data);
    }
    public void doColor(int myRow,int myColumn,Object [][]data){
        countryTable.getColumn("Population").setCellRenderer(
                new DefaultTableCellRenderer(){
                    Color orgColor=Color.black;
                    public Component
                    getTableCellRendererComponent(JTable table,
                                                  Object value, boolean isSelected,
                                                  boolean hasFocus, int row, int column) {
                        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.
                                getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                        int val=(int) value;

                        if(val>20000){
                            renderer.setForeground(Color.RED);
                        }else
                            renderer.setForeground(orgColor);

                        return renderer;
                    }

                }
        );
    }
    public void doFlag(int i,int j,Object[][] data){

        switch (i) {
            case 0:
                data[i][j] = flags.getPolishFlag();
                break;
            case 1:
                data[i][j] = flags.getCzechFlag();
                break;
            case 2:
                data[i][j] = flags.getSpainFlag();
                break;
            case 3:
                data[i][j] = flags.getGermanyFlag();
                break;
            case 4:
                data[i][j] = flags.getUkFlag();
                break;
            case 5:
                data[i][j] = flags.getBrasilFlag();
                break;
            case 6:
                data[i][j] = flags.getJapanFlag();
                break;
            case 7:
                data[i][j] = flags.getFranceFlag();
                break;
            case 8:
                data[i][j] = flags.getPortugalFlag();
                break;
            case 9:
                data[i][j] = flags.getChinaFlag();
                break;
            case 10:
                data[i][j] = flags.getRpaFlag();
                break;
            default:
                if (i % 3 == 0) {
                    data[i][j] = flags.getFranceFlag();
                } else if (i % 2 == 0) {
                    data[i][j] = flags.getBrasilFlag();
                } else
                    data[i][j] = flags.getRpaFlag();


        }
    }
}




