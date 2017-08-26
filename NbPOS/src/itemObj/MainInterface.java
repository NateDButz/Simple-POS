package itemObj;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import driver.SWTtools;
import itemObj.Item;

public class MainInterface {
	
	//Build objects
	public static void buildObj(List<Item> Product, String mode) {
		String csvFile = "/home/nate/eclipse-workspace/NbPOS/src/product.csv";
		String line = "";
		String csvSplitBy = ",";
		int cnt = 0;
		//read from file
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while ((line = br.readLine()) != null) {
				String[] product = line.split(csvSplitBy);
				Product.add(new Item());
				Product.get(cnt).setName(product[0]);
				Product.get(cnt).setNick(product[1]);
				Product.get(cnt).setPrice(Float.parseFloat(product[2]));
				Product.get(cnt).setArea(product[3]);
				Product.get(cnt).setX(Integer.parseInt(product[4]));
				Product.get(cnt).setY(Integer.parseInt(product[5]));				
				
				cnt+=1;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//Was used for testing, 		NEEDS REMOVED
		/*for(Item x : Product){
			System.out.println("Product Name: " + x.getName());
			System.out.println("Product Price: $" + x.getPrice());
			System.out.println("Product Nickname: " + x.getNick());
			System.out.println("Area in Menu: " + x.getArea());
			System.out.println();
		}*/
		
		buildInterface(Product, mode);
	}
	
	//Build POS Interface
	public static void buildInterface(List<Item> Product, String mode) {
		//building shell/display
		Display display = new Display();
		Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText("Simple POS");
		//building groups to manage buttons and text dialog
		Group mainBut = new Group(shell,SWT.None);
		Group readout = new Group(shell,SWT.None);
		
		//set bounds for button groups and text group
		mainBut.setBounds(0, 0, 620, 680);
		readout.setBounds(620,0,330,680);
				
		//set shell size/location
		shell.setSize(900, 680);
		SWTtools.centerWindow(shell);		
		
		//populate buttons
		for(Item x : Product){
		if( x.getArea().equals("Main")) {
			SWTtools.createButton(mainBut, x, mode);
			}
		}
				
		//text 
		Text overview = new Text(readout,SWT.MULTI | SWT.READ_ONLY);
		overview.setBounds(0, 0, 330, 600);
		overview.setEditable(false);
		
		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}		
	}
	
	
	
}
