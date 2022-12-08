package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller implements Initializable {

	@FXML
    private TextField textFieldB;

    @FXML
    private Button clearButton1;

    @FXML
    private TextField textFieldG;

    @FXML
    private Button rectangleButton1;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Text textG1;

    @FXML
    private TextField textFieldG1;

    @FXML
    private Tab editorTab;

    @FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button clearButton;

    @FXML
    private Tab helpTab;

    @FXML
    private Button redoButton;

    @FXML
    private Text textR;

    @FXML
    private ToolBar functionBar1;

    @FXML
    private MenuItem menuLoad;

    @FXML
    private Slider lineSlider;

    @FXML
    private Tooltip tooltipClear;

    @FXML
    private Slider lineSlider1;

    @FXML
    private Button lineButton1;

    @FXML
    private ToolBar functionBar;

    @FXML
    private MenuItem menuExit;

    @FXML
    private MenuItem menuNew;

    @FXML
    private Text textB1;

    @FXML
    private TextField textFieldB1;

    @FXML
    private Text lineThickness;

    @FXML
    private ToolBar functionBarTwo1;

    @FXML
    private Text textB;

    @FXML
    private Text textG;

    @FXML
    private ToolBar functionBarTwo;

    @FXML
    private Button lineButton;

    @FXML
    private Menu menu;

    @FXML
    private Text lineThickness1;

    @FXML
    private TextArea shortcutTextField;

    @FXML
    private RadioButton radioColor1;

    @FXML
    private Tab editorTab1;

    @FXML
    private TextField textFieldR;

    @FXML
    private Text textR1;

    @FXML
    private TextField textFieldR1;

    @FXML
    private Button undoButton1;

    @FXML
    private Button rectangleButton;

    @FXML
    private RadioButton radioColor;

    @FXML
    private Button undoButton;

    @FXML
    private MenuItem menuSave;

    @FXML
    private Button selectButton;
    
    @FXML
    private Canvas canvas = new Canvas();
    
    private double lineStartX, lineStartY, lineEndX, lineEndY;
    
    private double rectangleStartX, rectangleStartY, rectangleEndX, rectangleEndY;
    private GraphicsContext gc;
    
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    
    private Stack<String> history = new Stack<>();
    
    private EventHandler<MouseEvent> lineHandler = new EventHandler<>() {
		@Override public void handle(MouseEvent event) {
			lineEndX = event.getX();
		    lineEndY = event.getY();
		    
		    // Draw a line from the starting point to the ending point
		    gc.strokeLine(lineStartX, lineStartY, lineEndX, lineEndY);
			history.push(String.format("LINE %f %f %f %f", lineStartX, lineStartY, lineEndX, lineEndY));
			System.out.println(history);
		}
	};
	
	private EventHandler<MouseEvent> rectangleHandler = new EventHandler<>() {
		@Override public void handle(MouseEvent event) {
			rectangleEndX = event.getX();
    		rectangleEndY = event.getY();

            // Calculate the width and height of the rectangle
            double width = rectangleEndX - rectangleStartX;
            double height = rectangleEndY - rectangleStartY;

            // Draw a rectangle from the starting point to the ending point
            
            gc.fillRect(rectangleStartX, rectangleStartY, width, height);
            history.push(String.format("RECTANGLE %f %f %f %f", rectangleStartX, rectangleStartY, width, height));
            System.out.println(history);
		}
	};
    
    @FXML
    void lineSelect() {
    	
    	//Removing the handler for rectangle so we don't make multiple handles at once
    	canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, rectangleHandler);
    	
    	gc.setStroke(Color.BLACK);
    	
    	gc.setFill(Color.TRANSPARENT);
    
    	if(radioColor.isSelected()) {
    		System.out.print("pls");
    		if(!textFieldR.getText().trim().equals("") && !textFieldG.getText().trim().equals("") && !textFieldB.getText().trim().equals("")) {
    			red = Integer.parseInt(textFieldR.getText());
    	    	green = Integer.parseInt(textFieldG.getText());
    	    	blue = Integer.parseInt(textFieldB.getText());
    	    	
    	    	Color color = Color.rgb(red, green, blue);

    	    	gc.setStroke(color);
    		}
    	}
    	
    	gc.setLineWidth(3);
    	
    	canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            lineStartX = mouseEvent.getX();
            lineStartY = mouseEvent.getY();
        });
    	
    	
  		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, lineHandler);
  		
    	
    }
    
    @FXML
    void rectangleSelect() {
    	//Removing the handler for line so we don't make multiple handles at once

    	canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, lineHandler);
    	
    	gc.setFill(Color.BLACK);
    	
    	if(radioColor.isSelected()) {
    		System.out.print("pls");
    		if(!textFieldR.getText().trim().equals("") && !textFieldG.getText().trim().equals("") && !textFieldB.getText().trim().equals("")) {
    			red = Integer.parseInt(textFieldR.getText());
    	    	green = Integer.parseInt(textFieldG.getText());
    	    	blue = Integer.parseInt(textFieldB.getText());
    	    	
    	    	System.out.println(red);
    	    	System.out.println(green);
    	    	System.out.println(blue);
    	    	
    	    	Color color = Color.rgb(red, green, blue);

    	    	gc.setFill(color);
    		}
    	}
    	
    	canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
    		rectangleStartX = mouseEvent.getX();
    		rectangleStartY = mouseEvent.getY();
        });
    	
    	canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, rectangleHandler);
    	
    }
    
    @FXML
    void selectObject(ActionEvent event) {
    	
    }

    
    // UNDO changing colors if color is selected. Add parameters for colors in history when saving
    @FXML
    void undoAction(ActionEvent event) {
    	if (history.size() > 0) {
            history.pop();
        }

        // Clear the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Redraw all the operations in the history
        for (String operation : history) {
            String[] parts = operation.split(" ");
            if (parts[0].equals("LINE")) {
                double startX = Double.parseDouble(parts[1]);
                double startY = Double.parseDouble(parts[2]);
                double endX = Double.parseDouble(parts[3]);
                double endY = Double.parseDouble(parts[4]);
                System.out.println(history);
                gc.strokeLine(startX, startY, endX, endY);
            } else if(parts[0].equals("RECTANGLE")) {
            	double startX = Double.parseDouble(parts[1]);
                double startY = Double.parseDouble(parts[2]);
                double width = Double.parseDouble(parts[3]);
                double height = Double.parseDouble(parts[4]);
                
                gc.fillRect(startX, startY, width, height);
            }
        }
    }

    @FXML
    void redoAction(ActionEvent event) {

    }

    @FXML
    void clearEditor(ActionEvent event) {
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	while(!history.empty()) {
    		history.pop();
    	}
    }

    @FXML
    void setColor(ActionEvent event) {

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gc = canvas.getGraphicsContext2D();
	}

}
