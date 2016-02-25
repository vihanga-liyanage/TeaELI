package classes;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

class AutoSuggest {

    Vector<String> v = new Stack<String>();
    private boolean hide_flag = false;
    JTextField tx;

    public void setAutoSuggest(final JComboBox Search, ResultSet rst) {

        Search.removeAllItems();
        try {
            rst.first();
            if (Search.getItemCount() == 0) {
                Search.addItem("");
                do {
                    Search.addItem(rst.getString(1));
                    v.addElement(rst.getString(1));
                    Search.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent ie) {
                            if (ie.getStateChange() == ItemEvent.SELECTED) {
                                Search.getSelectedIndex();
                            }
                        }
                    });
                } while (rst.next());
            } else {
                Search.addItem("");
            }
        } catch (SQLException e) {
            
        }

        tx = (JTextField) Search.getEditor().getEditorComponent();
        tx.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String text = tx.getText();
                        if (text.length() == 0) {
                            Search.hidePopup();
                            setModel(new DefaultComboBoxModel(v), "", Search);
                        } else {
                            DefaultComboBoxModel m = getSuggestedModel(v, text);
                            if (m.getSize() == 0) {
                                Search.hidePopup();
                            } else {
                                setModel(m, text, Search);
                                Search.showPopup();
                            }
                        }
                    }
                });
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                String txt = tx.getText();
                int code = ke.getKeyCode();
                if (code == KeyEvent.VK_F2) {
                    //mhp.txtCash.requestFocusInWindow();
                    //spi.txtCash.requestFocusInWindow();
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ENTER) {
                    for (int i = 0; i < v.size(); i++) {
                        String str = (String) v.elementAt(i);
                        if(txt.equals("")){
                            //mhp.amount.requestFocusInWindow();
                            //spi.amount.requestFocusInWindow();
                            return;
                        }else if (str.toLowerCase().startsWith(txt)) {
                            tx.setText(str);
                            //mhp.amount.requestFocusInWindow();
                            //spi.amount.requestFocusInWindow();
                            return;

                        } else if (str.equals(tx.getText())) {
                            //mhp.amount.requestFocusInWindow();
                            //spi.amount.requestFocusInWindow();
                            return;
                        }
                    }
                }
            }

        });
    }

    private void setModel(DefaultComboBoxModel mdl, String str, JComboBox Search) {
        Search.setModel(mdl);
        tx.setText(str);
    }

    private DefaultComboBoxModel getSuggestedModel(List<String> list, String txt) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            if (s.toLowerCase().startsWith(txt)) {
                m.addElement(s);
            }
        }
        return m;
    }

}
