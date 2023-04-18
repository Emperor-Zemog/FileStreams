import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RandProductMaker extends JFrame {
    public RandProductMaker(){
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
        JButton save = new JButton("Save List");
        JButton additionalLine = new JButton("Add Line");
        JButton quit = new JButton("Quit");
        JButton render = new JButton("Render Product List");
        JLabel lineLType = new JLabel("Product Name");
        JLabel lineLUnitPrice = new JLabel("Product Description");
        JLabel lineLQuantity = new JLabel("Product ID");
        JLabel lineLCost = new JLabel("Product Cost");
        JLabel proCounter = new JLabel("Amount of Products in Que: 0");
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
        save.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath= "ProductTestData.txt";
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile(filePath, "rw");

                    for(int x=0; x < redHawk.size(); x++){
                        raf.write(redHawk.get(x).toCSVDataRecord().getBytes());


                    }

                    raf.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });
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
        additionalLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= proName.getText();
                String description = proDescrip.getText();
                String id = proID.getText();

                double cost= Double.parseDouble(proCost.getText());
                redHawk.add(new Product(id,name,description,cost));
                proCounter.setText("Amount of Products in Que: "+redHawk.size());
                proName.setText("");
                proDescrip.setText("");
                proID.setText("");
                proCost.setText("");
                System.out.println("Line Item #"+redHawk.size());

            }
        });
        render.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount=0;
                String msg="";
                msg+= String.format("%-15s %-15s %-25s %-20s\n","ID#","Name","Description","Cost");

                msg+="=====================================================================\n";


                for(int x =0; x<redHawk.size();x++){

                    msg+=String.format("%-15s %-15s %-25s %-20s\n",redHawk.get(x).getID(),redHawk.get(x).getName(),redHawk.get(x).getDescription(),redHawk.get(x).getCost());
                }




                msg+="\n \n";




                msg+="=====================================================================\n";
                if(textRender.isSelected()==true){

                    ta.setText(msg);
                }else{
                    System.out.print(msg);
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

        buttonPanel.add(save,c);

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
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 10;
        linePanel.add(lineLQuantity,c);
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 10;
        linePanel.add(lineLUnitPrice,c);
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        linePanel.add(lineLCost,c);
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 10;
        linePanel.add(proCounter,c);
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 0;
        c.ipadx =0;
        linePanel.add(proName,c);
        c.gridx = 1;
        c.gridy = 1;
        linePanel.add(proDescrip,c);
        c.gridx = 1;
        c.gridy = 2;
        linePanel.add(proID,c);
        c.gridx = 1;
        c.gridy = 3;
        linePanel.add(proCost,c);
        c.gridx = 1;
        c.gridy = 4;
        linePanel.add(additionalLine,c);
        c.ipadx= 0;
        c.ipady=0;
        c.weighty=1;

        //Formatting renderPanel
        renderPanel.setLayout(new GridBagLayout());
        renderPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Render Options", TitledBorder.LEFT, TitledBorder.TOP));
        c.gridwidth=1;

        c.gridwidth=2;
        c.gridx = 0;
        c.gridy = 1;
        renderPanel.add(textRender,c);
        c.gridx = 0;
        c.gridy = 2;
        renderPanel.add(consoleRender,c);
        c.gridx = 0;
        c.gridy = 3;
        renderPanel.add(render,c);

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
}
