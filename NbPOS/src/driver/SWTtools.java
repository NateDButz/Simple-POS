package driver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Event;
import java.util.List;

import itemObj.Item;

public class SWTtools {

		//Create Buttons for Items
		public static void createButton(Group mainG,Item X, String mode) {
			int xoffset = 85;
			int yoffset = 85;
			
						
			Button XX = new Button(mainG, SWT.PUSH );
			XX.setData(X);
			XX.setText(X.getNick());
			//XX.pack();
			XX.setBounds(((X.getX()*xoffset)-xoffset+15), ((X.getY()*yoffset)-yoffset+15),xoffset,yoffset);
			
			//Create Listener for configuration
			Listener buttonList = new Listener() {	
				@Override
				public void handleEvent(Event event) {
					if(mode.equals("Configure")) {
						System.out.println("Button pressed for "+ X.getX() + ", " +  X.getY());
						configButton(X, mode, mainG, XX);
						
					}else {
						System.out.println("Button pressed for "+ X.getName() +" - "+ X.getX() + ", " +  X.getY());
					}
					
				}
			};
			XX.addListener(SWT.Selection, buttonList);
			
			
		}
		
		//Configure buttons
		public static void configButton( Item X, String mode, Group mainG, Button XX) {
			Shell shell = new Shell( SWT.CLOSE | SWT.TITLE | SWT.MIN);
			shell.setText("Setup "+ X.getName() + ", Loc: " + X.getX() + ", " + X.getY());
			
			//Labels
			Label name = new Label(shell, SWT.NONE);
			Label nick = new Label(shell, SWT.NONE);
			Label price = new Label(shell, SWT.NONE);
			name.setText("Product Name: ");
			nick.setText("Button Label: ");
			price.setText("Product Price:");
			
			//Label Bounds
			name.setLocation(5, 7);
			nick.setLocation(5, 32);
			price.setLocation(5, 57);
			name.pack();
			nick.pack();
			price.pack();
			
			
			//Text Boxes
			Text tName = new Text(shell, SWT.None);
			Text tNick = new Text(shell, SWT.None);
			Text tPrice = new Text(shell, SWT.None);
			//TextBox Bounds
			tName.setBounds(110, 5, 130, 22);
			tNick.setBounds(110,30,130,22);
			tPrice.setBounds(110,55,130,22);
						
			//Listeners
			Listener saveListener = new Listener() {
				public void handleEvent(Event event) {
					System.out.println("Save button pressed");
					saveConfig(X, tName.getText(), tNick.getText(), tPrice.getText());
					XX.dispose();
					createButton(mainG, X, mode);
					shell.dispose();
				}
			};
			Listener exitListener = new Listener() {
				public void handleEvent(Event event) {
					System.out.println("Exit button Pressed.");
					shell.dispose();
				}
			};
			
			
			//Buttons
			Button save = new Button(shell,SWT.PUSH);
			Button cancel = new Button(shell, SWT.PUSH);
			save.setText("Save");
			cancel.setText("Cancel");
			save.addListener(SWT.Selection, saveListener);
			cancel.addListener(SWT.Selection, exitListener);
			
			//Button Bounds
			save.setBounds(5, 80, 100, 30);
			cancel.setBounds(115, 80, 100, 30);
			
			//Setup Shell
			shell.setBounds(0,0,250,150);
			centerWindow(shell);
			shell.open();			
		}
		
		//Write new Config to Object
		public static void saveConfig(Item X, String name, String nick, String price) {
			X.setName(name);
			X.setNick(nick);
			X.setPrice(Float.parseFloat(price));
			
			
		}
		
		//Save new config
		public static void saveAll(List<Item> it) {
			
		}
		
		//Center Shell
		public static void centerWindow(Shell shell) {
			Rectangle bounds = shell.getDisplay().getBounds();
			Point p = shell.getSize();
			
			int nLeft = (bounds.width - p.x)/2;
			int nTop = (bounds.height - p.y)/2;
			
			shell.setBounds(nLeft, nTop, p.x, p.y);
		}
		
}
