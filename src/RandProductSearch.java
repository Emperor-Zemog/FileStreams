import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RandProductSearch extends JFrame {
    public RandProductSearch(){
        super("Invoice Manager");
        // background elements
        ArrayList<Product> redHawk= new ArrayList<Product>();

        //UI elements
        JPanel buttonPanel = new JPanel();
        JPanel invoiceTextPanel = new JPanel();
        JPanel renderPanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel linePanel = new JPanel();
        JButton clear = new JButton("Clear Options");


        JButton quit = new JButton("Quit");
        JButton render = new JButton("Search Product List");
        JLabel lineLType = new JLabel("Partial Product Name");

        JTextArea proName = new JTextArea("", 1, 10);
        JTextArea proDescrip = new JTextArea("", 1, 10);
        JTextArea proID = new JTextArea("", 1, 10);
        JTextArea proCost = new JTextArea("", 1, 10);

        JLabel addLTitle = new JLabel("Name of Business");
        JLabel addLStreet = new JLabel("Street Address");
        JLabel addLCity = new JLabel("City, State, Zip");
        JTextArea addITitle = new JTextArea("", 1, 10);
        JTextArea addIStreet = new JTextArea("", 1, 10);
        JTextArea addICity = new JTextArea("", 1, 10);

        JRadioButton textRender = new JRadioButton("Render to Textbox");

        textRender.setSelected(true);
        JRadioButton consoleRender = new JRadioButton("Render to Console");
        ButtonGroup renderGroup = new ButtonGroup();
        renderGroup.add(textRender);
        renderGroup.add(consoleRender);
        GridBagConstraints c = new GridBagConstraints();
        JTextArea ta = new JTextArea("", 15, 50); // Text area
        ta.setLineWrap(true);
        JScrollPane sbrText = new JScrollPane(ta); // Scroll pane for text area
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //action listener

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(new JFrame(),"Sure? You want to exit?", "Quit Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    System.exit(0);

                }else if (result == JOptionPane.NO_OPTION){

                }else {

                }
            }
        });

        render.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomAccessFile raf = null;
                String outVal="";
                ArrayList<String> sList = new ArrayList<String>();
                ArrayList<Product> products = new ArrayList<Product>();
                try {
                    raf = new RandomAccessFile("ProductTestData.txt", "r");
                    int fileLength= (int) raf.length();

                    byte[] bytes = new byte[fileLength];
                    raf.read(bytes);
                    raf.close();
                    outVal= new String(bytes);
                    System.out.println(outVal);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String compiledList="";
                sList=splitSubstrings(outVal);
                for(int x=0;x < sList.size();x=x+4){
                    products.add(new Product(sList.get(x).trim(),sList.get(x+1).trim(),sList.get(x+2).trim(),Double.parseDouble(sList.get(x+3))));

                }
                for(int y=0; y< products.size();y++){
                    compiledList+= products.get(y).getRecord();
                }
                String lowDoc = compiledList.toLowerCase();
                String val= proName.getText().toLowerCase();
                ArrayList<String> aList = new ArrayList<String>();


                aList = splitSubs(lowDoc);
                ArrayList<String> filDoc = (ArrayList<String>) sList.stream().filter(c -> c.contains(val)).collect(Collectors.toList());
                for(int x =0; x<filDoc.size();x++) {
                    writeText(ta, filDoc.get(x));
                }




            }
        });

        //formatting the buttonPanel
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Actions", TitledBorder.LEFT, TitledBorder.TOP));
        c.weightx = 1;

        c.ipady = 0;

        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;

        buttonPanel.add(quit,c);
        c.ipady = 0;
        c.gridx = 2;
        c.gridy = 1;

        buttonPanel.add(render,c);

        // formatting invoiceTextPanel
        invoiceTextPanel.setLayout(new GridBagLayout());
        invoiceTextPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Product List Render", TitledBorder.LEFT, TitledBorder.TOP));
        c.weightx = 1;

        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;
        invoiceTextPanel.add(sbrText, c);
        //formatting the linePanel
        linePanel.setLayout(new GridBagLayout());
        linePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Product Item List", TitledBorder.LEFT, TitledBorder.TOP));
        c.weightx = 1;
        c.ipady = 0;
        c.gridwidth=1;
        c.gridheight=1;
        c.weighty=0;
        c.anchor = GridBagConstraints.WEST;
        c.ipadx= 10;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 10;
        linePanel.add(lineLType,c);


        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 0;
        c.ipadx =0;
        linePanel.add(proName,c);


        //Formatting renderPanel


        // Formatting addressPanel
        addressPanel.setLayout(new GridBagLayout());
        addressPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Address Info", TitledBorder.LEFT, TitledBorder.TOP));

        c.anchor = GridBagConstraints.WEST;
        c.gridwidth=1;
        c.ipadx= 10;
        c.gridx = 0;
        c.gridy = 0;
        addressPanel.add(addLTitle,c);
        c.gridx = 0;
        c.gridy = 1;
        addressPanel.add(addITitle,c);
        c.gridx = 0;
        c.gridy = 2;
        addressPanel.add(addLStreet,c);
        c.gridx = 0;
        c.gridy = 3;
        addressPanel.add(addIStreet,c);
        c.gridx = 0;
        c.gridy = 4;
        addressPanel.add(addLCity,c);
        c.gridx = 0;
        c.gridy = 5;
        addressPanel.add(addICity,c);

        c.ipadx= 0;



        //adding UI Elements
        setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty=1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(buttonPanel, c);
        c.gridx = 0;
        c.gridy = 2;
        add(invoiceTextPanel, c);
        c.gridx = 0;
        c.gridy = 1;
        add(linePanel, c);
        c.gridwidth = 1;
        // c.gridx = 0;
        // c.gridy = 0;
        // add(addressPanel, c);
        c.gridx = 1;
        c.gridy = 0;
        add(renderPanel, c);



        // formatting the frame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // center frame in screen

        setSize((int) (screenWidth / 1.25), (int) (screenHeight / 1.25));

        setLocation((int) (screenWidth / 9.5), (int) (screenHeight / 9));

        // Render the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.show();



    }
    public static ArrayList<String> splitSubstrings(String s)
    {


        int i, j;


        int stringLength = s.length();


        ArrayList<String> subStringList = new ArrayList<String>();

        i=0;
        j=0;
        while(i<stringLength){
            if(s.charAt(i)==','){
                subStringList.add(s.substring(j,i));
                j=i+1;
            } else if(s.charAt(i)=='\n'){
                subStringList.add(s.substring(j,i-1));
                j=i+1;

            }else if (i==stringLength-1) {
                subStringList.add(s.substring(j,i));
            }
            i++;
        }


        return subStringList;
    }
    public static ArrayList<String> splitSubs(String s)
    {


        int i, j;


        int stringLength = s.length();


        ArrayList<String> subStringList = new ArrayList<String>();

        i=0;
        j=0;
        while(i<stringLength){
            if(s.charAt(i)=='\n'){
                subStringList.add(s.substring(j,i));
                j=i+1;
            }else if (i==stringLength-1) {
                subStringList.add(s.substring(j,i));
            }
            i++;
        }


        return subStringList;
    }
    public void writeText(JTextArea ta, String outText){
        int past = ta.getText().length();
        if(past==0) {
            ta.setText(outText);
        }else {
            ta.setText(ta.getText()+"\n"+outText);

        }

    }
}
