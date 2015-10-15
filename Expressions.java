import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class Expressions extends JFrame{
	private JTextField enter;
	private JEditorPane contents;
	private JMenuBar jb;
	private JMenu file, help, edit;
	private JMenuItem go, exit, about, refresh, back, forward;
	private String s, st, ab;
	private int i, j;

	public Expressions(){
		super("Expressions Web Browser");

		Container c = getContentPane();
		
		st = "";

		enter = new JTextField("http://");
		jb = new JMenuBar();
		
		file = new JMenu("File");
		help = new JMenu("Help");
		edit = new JMenu("Edit");
		
		go = new JMenuItem("Go");
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");
		refresh = new JMenuItem("Refresh");
		back = new JMenuItem("Back");
		forward = new JMenuItem("Forward");

		jb.add(file);
		jb.add(edit);
		jb.add(help);

		file.add(go);
		file.add(exit);

		edit.add(refresh);
		edit.add(back);
		edit.add(forward);
		
		help.add(about);
		
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				s = enter.getText();
				getThePage( s );
			}
		});
		
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				s = enter.getText();
				getThePage( s );
			}
		});
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				s = enter.getText();
				
				i = s.lastIndexOf('/');
				
				if(i != -1) st = s.substring(0 , i);
				else st = s;
				
				getThePage( st );
			}
		});
		
		forward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(s != null) getThePage( s );
			}
		});
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ab = "A experimental web browser\nas a course work of\nNetworking lab\n"+
						"Humaira Sharmin, 04101020\n"+
						"Mahbubur Rahman, 04101007\n"+
						"Abdullah Al Zakir Hossain, 04101009\n"+
						"\nExpressions\nCSE, UAP\n";
				JOptionPane.showMessageDialog(null, ""+ab);
			}
		});

		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getThePage( e.getActionCommand() );
			}
		});

		c.add( enter, BorderLayout.NORTH );

		contents = new JEditorPane();

		contents.setEditable( false );

		contents.addHyperlinkListener(new HyperlinkListener(){
			public void hyperlinkUpdate(HyperlinkEvent e){
				if ( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ) getThePage(e.getURL().toString());
			}
		});

		c.add( new JScrollPane( contents ), BorderLayout.CENTER );

		setJMenuBar(jb);
		setSize( 700, 500 );
		setVisible(true);
	}

	private void getThePage(String location){
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		try{
			contents.setPage( location );
			enter.setText( location );
		}
		catch(IOException io){
			JOptionPane.showMessageDialog(this, "Error retrieving specified URL", "Bad URL", JOptionPane.ERROR_MESSAGE );
		}

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public static void main(String args[]){
		Expressions app = new Expressions();
		app.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit( 0 );
			}
		});
	}
}
