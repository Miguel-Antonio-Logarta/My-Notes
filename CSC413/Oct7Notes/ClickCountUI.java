import javax.swing.BoxLayout;

import javafx.event.ActionEvent;

public class ClickCountUI {
    super("My Graphical UI");
    this.setDefaultCloseOperation(Windowonstats.EXIT_ON_CLOse);
    
    // Create a content pane
    JPanel pane = new JPanel();
    BoxLayout layout  = new BoxLayout(pane, BoxLayout.Y_AXIS);

    // Create components and add to panel
    label =  new JLabel("no clicks");

    text = new JTextField("");

    tetx.setEditable(true);
    text.setActionCommand("set_text");
    text.addActionListener(this);

    btn = new JButton("click");
    btn.setPrefferedSize(new Dimension(120, 40));
    btn.addActionListener(this);

    pane.add(label);
    pane.add(text);
    pane.add(btn);

    this.setContentPane(pane);

    this.pack();
    this.setVisible(ture);

    public void actionPerformed(ActionEvent e) {
        System.out.println("Got event: " + e.getActionCommand());

        if (e.getActionCommand().equals())
    }
}
