package ui;

import static javafx.scene.layout.BorderStrokeStyle.SOLID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameLog extends Pane {

	private ObservableList<Label> logDataList = FXCollections.observableArrayList();
	private ListView<Label> logListView;
	
	public GameLog() {
		
		logListView = new ListView<Label>(logDataList);
		logListView.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.25), 
				CornerRadii.EMPTY, Insets.EMPTY)));
		logListView.setFocusTraversable(false);
		logListView.setPrefSize(300, 200);
		
		logListView.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, SOLID, 
			CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		setMaxSize(310, 210);
		
		getChildren().add(logListView);
	}
	
	public void addData(String message) {
		Label newLabel = new Label(message);
		newLabel.setStyle("-fx-font-weight: bold");
		logDataList.add(newLabel);
		if (logDataList.size() > 6) {
			logDataList.remove(0);
		}
		logListView.scrollTo(newLabel);
	}

	public ListView<Label> getLogListView() {
		return logListView;
	}
	
}
