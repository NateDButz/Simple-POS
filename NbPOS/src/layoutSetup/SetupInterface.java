package layoutSetup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Event;
import driver.SWTtools;
import itemObj.Item;

public class SetupInterface {
	
	public static void buildObj(List<Item> Default, String mode) {
		String csvFile = "/home/nate/eclipse-workspace/NbPOS/src/product.csv";
		if(mode.equals("Configure New")) {
			csvFile = "/home/nate/eclipse-workspace/NbPOS/src/default.csv";
		}
			
		mode = "Configure";
		
		String line = "";
		String csvSplitBy = ",";
		int cnt = 0;
		//read from file
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while ((line = br.readLine()) != null) {
				String[] def = line.split(csvSplitBy);
				Default.add(new Item());
				Default.get(cnt).setName(def[0]);
				Default.get(cnt).setNick(def[1]);
				Default.get(cnt).setPrice(Float.parseFloat(def[2]));
				Default.get(cnt).setArea(def[3]);
				Default.get(cnt).setX(Integer.parseInt(def[4]));
				Default.get(cnt).setY(Integer.parseInt(def[5]));				
				
				cnt+=1;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
				
		setupInterface(Default,mode);
	}
	
	
	
	public static void setupInterface(List<Item> Default, String mode) {
		
		//setup display/shell
		Display display = new Display();
		Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText("Setup Simple POS");
		Group mainBut = new Group(shell,SWT.None);
		Group readout = new Group(shell,SWT.None);
		
		//set bounds for button groups and text group
		mainBut.setBounds(0, 0, 620, 680);
		readout.setBounds(620,0,330,680);
				
		//set shell size/location
		shell.setSize(900, 680);
		SWTtools.centerWindow(shell);		
		
		//populate buttons
		for(Item x : Default){
			if( x.getArea().equals("Main")) {
				SWTtools.createButton(mainBut, x, mode);
			}
		}
		
		//text 
		Text overview = new Text(readout,SWT.MULTI | SWT.READ_ONLY);
		overview.setBounds(0, 0, 330, 600);
		overview.setEditable(false);
		
		//Save and Cancel Buttons
		Button save = new Button(mainBut,SWT.PUSH);
		Button cancel = new Button(mainBut,SWT.PUSH);
		save.setText("Save");
		cancel.setText("Cancel");
		save.setBounds(225, 615, 100, 30);
		cancel.setBounds(335, 615, 100, 30);
		
		//Button Listeners
		Listener saveListener = new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Save button pressed");
				driver.SWTtools.saveAll(Default);
				//shell.dispose();
			}
		};
		Listener exitListener = new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Exit button Pressed.");
				shell.dispose();
			}
		};
		save.addListener(SWT.Selection, saveListener);
		cancel.addListener(SWT.Selection, exitListener);
		
		
		
		//launch shell
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			
			}		
		}
	}
	

	
}
