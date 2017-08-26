package driver;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import itemObj.*;
import layoutSetup.SetupInterface;

public class startPOS {

	public static void main(String[] args) {
		List<Item> Product = new ArrayList<Item>();
		Display display = new Display();
		
		Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText("Login");
		shell.setLayout(new RowLayout());
		
		Group selector = new Group(shell,SWT.NONE);
		selector.setBounds(0, 0, 150, 00);
						
		Group buttons = new Group(shell, SWT.NONE);
		buttons.setBounds(0, 43, 150, 40);
				
		//Configure dropdown selection
		Combo select = new Combo(selector,SWT.DROP_DOWN | SWT.READ_ONLY);
		select.setBounds(0, 0, 150, 40);
		select.add("Start");
		select.add("Configure");
		select.add("Configure New");
		select.setTouchEnabled(false);
		select.select(0);
		
		//Start button configuration
		Button login = new Button(buttons, SWT.PUSH);
		login.setText("Start");
		login.setBounds(0,0,75,40);
		Listener startListener = new Listener() {
			public void handleEvent(Event event) {
				if(select.getText().equals("Start")) {
					String selection = select.getText();
					display.dispose();
					System.out.println("Start main POS was selected");
					itemObj.MainInterface.buildObj(Product, selection);	
				}
				else {
					System.out.println("Start Configuration of POS was selected");
					String selection = select.getText();
					display.dispose();
					layoutSetup.SetupInterface.buildObj(Product, selection);
				}
				
			}
		};
		login.addListener(SWT.Selection, startListener);
		
		
		
		//Exit Button Configuration
		Button exit = new Button(buttons, SWT.PUSH);
		exit.setText("Exit");
		exit.setBounds(75, 0, 75, 40);
		Listener exitListener = new Listener() {
			public void handleEvent(Event event) {
				System.out.print("Exit button Pressed.");
				display.dispose();
			}
		};		
		exit.addListener(SWT.Selection, exitListener);
		
		
		shell.setBounds(70, 70, 157, 120);
		SWTtools.centerWindow(shell);
		
		
		shell.open();
		
		while(!shell.isDisposed()) {
			
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		
		//itemObj.MainInterface.buildObj(Product);		
		

	}

}
