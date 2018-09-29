package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.stream.IntStream;

public class Main extends Application {
    private Scene auswahlScene;
    private Scene selectTopicsScene;
    private Scene testAndTrainScene;
    private String selectedTopic = "";
    private String selectedClass;
    private boolean[] topicIsSelected = {false,false,false,false,false};
    private boolean isTest;
    private String completeCurrentExercise;
    private String taskRequest;
    private String exercise;
    private String solution;
    private String explanation;
    private Exercises exercises = new Exercises();
    private boolean[] correctAnswers = new boolean[15];
    private int correctAnswerPointer = 0;

    @Override
    public void start(Stage primaryStage) {
        //different layouts for different scenes
        //introLayout
        GridPane introLayout = new GridPane();
        ColumnConstraints nameColumn = new ColumnConstraints();
        ColumnConstraints classColumn = new ColumnConstraints();
        ColumnConstraints topicColumn = new ColumnConstraints();
        ColumnConstraints getStartedColumn = new ColumnConstraints();
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        introLayout.getColumnConstraints().addAll(nameColumn,classColumn,topicColumn,getStartedColumn);
        introLayout.getRowConstraints().addAll(row0,row1);
        Label nameLabel = new Label("Name:");
        Label classLabel= new Label("Klasse:");
        Label topicLabel = new Label("Themenbereich:");
        Label disclaimerLabel = new Label("Momentan sind noch nicht alle Klassen und Themen verfügbar!   ");
        Button getStartedButton = new Button("Los geht's: ->");
        TextField nameField = new TextField("");
        ObservableList<String> classes =
                FXCollections.observableArrayList(
                        "5","6","7","8","9","10"
                );
        ObservableList<String> topicsClass5 =
                FXCollections.observableArrayList(
                        "K.5 Thema 1","K.5 Thema 2","K.5 Thema 3","K.5 Thema 4","K.5 Thema 5","K.5 Thema 6"
                );
        ObservableList<String> topicsClass6 =
                FXCollections.observableArrayList(
                        "K.6 Thema 1","K.6 Thema 2","K.6 Thema 3","K.6 Thema 4","K.6 Thema 5","K.6 Thema 6"
                );
        ObservableList<String> topicsClass7 =
                FXCollections.observableArrayList(
                        "Elementares Rechnen","Prozent- und Zinsrechnung","Funktionen","Geometrie","Gleichungen und Terme"
                );
        ObservableList<String> topicsClass8 =
                FXCollections.observableArrayList(
                        "K.8 Thema 1","K.8 Thema 2","K.8 Thema 3","K.8 Thema 4","K.8 Thema 5","K.8 Thema 6"
                );
        ObservableList<String> topicsClass9 =
                FXCollections.observableArrayList(
                        "K.9 Thema 1","K.9 Thema 2","K.9 Thema 3","K.9 Thema 4","K.9 Thema 5","K.9 Thema 6"
                );
        ObservableList<String> topicsClass10 =
                FXCollections.observableArrayList(
                        "K.10 Thema 1","K.10 Thema 2","K.10 Thema 3","K.10 Thema 4","K.10 Thema 5","K.10 Thema 6"
                );
        ComboBox<String> classesDropdown = new ComboBox<>(classes);
        ComboBox<String> topicsDropdown = new ComboBox<>();
        classesDropdown.setOnAction((event -> {
            selectedClass = classesDropdown.getSelectionModel().getSelectedItem();
            switch (selectedClass){
                case "5":
                    topicsDropdown.setItems(topicsClass5);
                    break;
                case "6":
                    topicsDropdown.setItems(topicsClass6);
                    break;
                case "7":
                    topicsDropdown.setItems(topicsClass7);
                    break;
                case "8":
                    topicsDropdown.setItems(topicsClass8);
                    break;
                case "9":
                    topicsDropdown.setItems(topicsClass9);
                    break;
                case "10":
                    topicsDropdown.setItems(topicsClass10);
                    break;
                default: topicsDropdown.setItems(null);
            }
        }));
        introLayout.add(nameLabel,0,0);
        introLayout.add(classLabel,0,1);
        introLayout.add(topicLabel,0,2);
        introLayout.add(disclaimerLabel,0,3);
        introLayout.add(nameField,1,0);
        introLayout.add(classesDropdown,1,1);
        introLayout.add(topicsDropdown,1,2);
        introLayout.add(getStartedButton,1,3);
        getStartedButton.setOnAction(e -> {selectedTopic=topicsDropdown.getSelectionModel().getSelectedItem();
            if (nameField.getText().equals("")||topicsDropdown.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler!");
                alert.setHeaderText("Es sind nicht alle Felder ausgefüllt!");
                alert.setContentText("Bitte füllen sie alle Felder aus.");
                alert.showAndWait();
            }
            else{primaryStage.setTitle("Der Mathe-Vorbereiter [BETA] | Hallo "+nameField.getText());
                primaryStage.setScene(auswahlScene); }
        });
        classesDropdown.setPrefWidth(150);
        topicsDropdown.setPrefWidth(150);

        //auswahllayout
        GridPane auswahlLayout = new GridPane();
        RowConstraints auswahlLayoutRow0 = new RowConstraints();
        RowConstraints auswahlLayoutRow1  = new RowConstraints();
        RowConstraints auswahlLayoutRow2 = new RowConstraints();
        ColumnConstraints auswahlLayoutColumn0 = new ColumnConstraints();
        ColumnConstraints auswahlLayoutColumn1 = new ColumnConstraints();
        auswahlLayout.getRowConstraints().addAll(auswahlLayoutRow0,auswahlLayoutRow1,auswahlLayoutRow2);
        auswahlLayout.getColumnConstraints().addAll(auswahlLayoutColumn0,auswahlLayoutColumn1);
        Label takeTestLabel = new Label("   Mache einen Test und finde deine Schwächen!");
        Label trainAllLabel = new Label("   Trainiere alle Unterthemen!");
        Label trainSpecificLabel = new Label("   Such dir die Unterthemen aus, die du nicht kannst!");
        Button takeTestButton = new Button("Test machen");
        Button trainAllButton = new Button("Alles trainieren");
        Button trainSpecificButton = new Button("Bestimmtes trainieren");
        auswahlLayout.add(takeTestButton,0,0);
        auswahlLayout.add(trainAllButton,0,1);
        auswahlLayout.add(trainSpecificButton,0,2);
        auswahlLayout.add(takeTestLabel,1,0);
        auswahlLayout.add(trainAllLabel,1,1);
        auswahlLayout.add(trainSpecificLabel,1,2);
        takeTestButton.setPrefWidth(200);
        trainAllButton.setPrefWidth(200);
        trainSpecificButton.setPrefWidth(200);
        takeTestButton.setOnAction(event -> {
            for (int i=0;i<topicIsSelected.length;i++){
                topicIsSelected[i]=true;
            }
            isTest=true;
            primaryStage.setScene(testAndTrainScene);
        });
        trainAllButton.setOnAction(event -> {
            for (int i=0;i<topicIsSelected.length;i++){
                topicIsSelected[i]=true;
            }
            isTest = false;
            primaryStage.setScene(testAndTrainScene);
        });

        //Themenbereiche Layout
        GridPane selectTopicsLayout = new GridPane();
        RowConstraints topicLayoutRow0 = new RowConstraints();
        RowConstraints topicLayoutRow1 = new RowConstraints();
        RowConstraints topicLayoutRow2 = new RowConstraints();
        RowConstraints topicLayoutRow3 = new RowConstraints();
        RowConstraints topicLayoutRow4 = new RowConstraints();
        RowConstraints topicLayoutRow5 = new RowConstraints();
        RowConstraints topRow = new RowConstraints();
        ColumnConstraints topicLayoutColumn0 = new ColumnConstraints();
        ColumnConstraints topicLayoutColumn1 = new ColumnConstraints();
        selectTopicsLayout.getRowConstraints().addAll(topRow,topicLayoutRow0,topicLayoutRow1,topicLayoutRow2,topicLayoutRow3,topicLayoutRow4,topicLayoutRow5);
        selectTopicsLayout.getColumnConstraints().addAll(topicLayoutColumn0,topicLayoutColumn1);
        Label topRowLabel = new Label("Die folgenden Themenbereiche \n werden dir empfohlen!");
        topRowLabel.setVisible(false);
        CheckBox subtopicCheckBox0 = new CheckBox();
        CheckBox subtopicCheckBox1 = new CheckBox();
        CheckBox subtopicCheckBox2 = new CheckBox();
        CheckBox subtopicCheckBox3 = new CheckBox();
        CheckBox subtopicCheckBox4 = new CheckBox();
        // dieses Event musste außerhalb der Reihe definiert werden
        trainSpecificButton.setOnAction(event -> {
            switch (selectedTopic){
                case "Prozent- und Zinsrechnung":
                    subtopicCheckBox0.setText("Prozentrechnung");
                    subtopicCheckBox1.setText("Zinsrechnung");
                    subtopicCheckBox2.setVisible(false);
                    subtopicCheckBox3.setVisible(false);
                    subtopicCheckBox4.setVisible(false);
                    break;
                case "Funktionen":
                    subtopicCheckBox0.setText("Termumformungen");
                    break;
                case "Geometrie":
                    subtopicCheckBox0.setText("Termumformungen");
                    break;
                case "Gleichungen und Terme":
                    subtopicCheckBox0.setText("Termumformungen");
                    subtopicCheckBox1.setText("Lineare Gleichungen");
                    subtopicCheckBox2.setText("Lineare Gleichungen - Textaufgaben");
                    subtopicCheckBox3.setVisible(false);
                    subtopicCheckBox4.setVisible(false);
                    break;
            }
            primaryStage.setScene(selectTopicsScene);
        });

        Button backToAuswahlButton = new Button("<- zurück zur Auswahl");
        backToAuswahlButton.setOnAction(event -> primaryStage.setScene(auswahlScene));
        Button startTrainingButton = new Button("Training starten!");
        startTrainingButton.setOnAction(event -> {
            isTest=false;
            topicIsSelected[0] = subtopicCheckBox0.isSelected();
            topicIsSelected[1] = subtopicCheckBox1.isSelected();
            topicIsSelected[2] = subtopicCheckBox2.isSelected();
            topicIsSelected[3] = subtopicCheckBox3.isSelected();
            topicIsSelected[4] = subtopicCheckBox4.isSelected();
            if (!topicIsSelected[0] && !topicIsSelected[1] && !topicIsSelected[2] && !topicIsSelected[3] && !topicIsSelected[4]){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler!");
                alert.setHeaderText("Es ist kein Thema ausgewählt!");
                alert.setContentText("Bitte wählen Sie mindestens ein Thema aus!");
                alert.showAndWait();
            }
            else{primaryStage.setScene(testAndTrainScene); }
        });
        selectTopicsLayout.add(subtopicCheckBox0,0,0);
        selectTopicsLayout.add(subtopicCheckBox1,0,1);
        selectTopicsLayout.add(subtopicCheckBox2,0,2);
        selectTopicsLayout.add(subtopicCheckBox3,0,3);
        selectTopicsLayout.add(subtopicCheckBox4,0,4);
        selectTopicsLayout.add(backToAuswahlButton,0,5);
        selectTopicsLayout.add(startTrainingButton,1,5);

        //testAndTrainLayout - Das Layout zum Lösen von Aufgaben ist für Test und Training gleich gleich
        GridPane testAndTrainLayout = new GridPane();
        RowConstraints testAndTrainLayoutRow0 = new RowConstraints();
        RowConstraints testAndTrainLayoutRow1 = new RowConstraints();
        RowConstraints testAndTrainLayoutRow2 = new RowConstraints();
        RowConstraints testAndTrainLayoutRow3 = new RowConstraints();
        RowConstraints testAndTrainLayoutRow4 = new RowConstraints();
        ColumnConstraints testAndTrainLayoutColumn0 = new ColumnConstraints();
        ColumnConstraints testAndTrainLayoutColumn1 = new ColumnConstraints();
        testAndTrainLayout.getRowConstraints().addAll(testAndTrainLayoutRow0,testAndTrainLayoutRow1,testAndTrainLayoutRow2,testAndTrainLayoutRow3,testAndTrainLayoutRow4);
        testAndTrainLayout.getColumnConstraints().addAll(testAndTrainLayoutColumn0,testAndTrainLayoutColumn1);
        Label taskRequestLabel = new Label();
        taskRequestLabel.setMaxWidth(150);
        Label exerciseLabel = new Label("Aufgabe:  ");
        Label studentSolutionLabel = new Label("Ergebnis:  ");
        Label rightAnswerLabel = new Label("Diese Lösung ist richtig!");
        Label wrongAnswerLabel = new Label("Diese Lösung leider falsch.");
        TextField exerciseTextField = new TextField("");
        Tooltip exerciseTooltip = new Tooltip();
        exerciseTooltip.setStyle("-fx-font-size: 20;");
        Duration showDuration = new Duration(999999999);
        exerciseTooltip.setShowDuration(showDuration);
        exerciseTextField.setTooltip(exerciseTooltip);
        exerciseTextField.setEditable(false);
        TextField studentSolutionTextField = new TextField();
        Button solveButton =  new Button("Lösung prüfen");
        Button nextExerciseButton = new Button("nächste Aufgabe");
        Button showRightAnswerButton = new Button("Lösung mit Erklärung anzeigen");
        Button startExercisingButton = new Button("Beginnen!");
        solveButton.setVisible(false);
        rightAnswerLabel.setVisible(false);
        wrongAnswerLabel.setVisible(false);
        nextExerciseButton.setVisible(false);
        showRightAnswerButton.setVisible(false);
        testAndTrainLayout.add(taskRequestLabel,0,0);
        testAndTrainLayout.add(exerciseLabel,0,1);
        testAndTrainLayout.add(exerciseTextField,1,1);
        testAndTrainLayout.add(studentSolutionLabel,0,2);
        testAndTrainLayout.add(studentSolutionTextField,1,2);
        testAndTrainLayout.add(startExercisingButton,1,3);
        testAndTrainLayout.add(solveButton,1,3);
        testAndTrainLayout.add(rightAnswerLabel,0,3);
        testAndTrainLayout.add(wrongAnswerLabel,0,3);
        testAndTrainLayout.add(nextExerciseButton,1,4);
        testAndTrainLayout.add(showRightAnswerButton,0,4);
        startExercisingButton.setOnAction(event -> {
            completeCurrentExercise=getNextExercise();
            String[] innerParts = completeCurrentExercise.split("%");
            taskRequest = innerParts[0];
            exercise = innerParts[1];
            solution = innerParts[2];
            explanation = innerParts[3];
            solveButton.setVisible(true);
            startExercisingButton.setVisible(false);
            taskRequestLabel.setText(taskRequest);
            exerciseTextField.setText(exercise);
            exerciseTooltip.setText(exercise);

            studentSolutionTextField.setText("");
        });
        solveButton.setOnAction(event -> {
            boolean correct = isCorrect(studentSolutionTextField.getText());
            if (correct){
                rightAnswerLabel.setVisible(true);
                if (isTest){
                    correctAnswers[correctAnswerPointer]=true;
                    correctAnswerPointer++;
                }
            }
            else {
                wrongAnswerLabel.setVisible(true);
                showRightAnswerButton.setVisible(true);
                if (isTest){
                    correctAnswers[correctAnswerPointer]=false;
                    correctAnswerPointer++;
                }
            }
            solveButton.setVisible(false);
            nextExerciseButton.setVisible(true);
        });
        nextExerciseButton.setOnAction(event -> {
            completeCurrentExercise=getNextExercise();
            if (completeCurrentExercise.equals("done")){
                int subtopic0check=0;
                int subtopic1check=0;
                int subtopic2check=0;
                int subtopic3check=0;
                int subtopic4check=0;
                correctAnswerPointer=0;
                for (int i=0;i<5;i++){
                    if (correctAnswers[i]){subtopic0check++;}
                }
                if ((subtopic0check)<3){subtopicCheckBox0.setSelected(true);}
                for (int i=5;i<10;i++){
                    if (correctAnswers[i]){subtopic1check++;}
                }
                if ((subtopic1check)<3){subtopicCheckBox1.setSelected(true);}
                for (int i=10;i<15;i++){
                    if (correctAnswers[i]){subtopic2check++;}
                }
                if ((subtopic2check)<3){subtopicCheckBox2.setSelected(true);}
                //Code für den Fall, dass mehr als 3 Unterthemen existieren wurde aus Platz- und Prototypgründen gespart
                topRowLabel.setVisible(true);
                subtopicCheckBox0.setText("Termumformungen");
                subtopicCheckBox1.setText("Lineare Gleichungen");
                subtopicCheckBox2.setText("Lineare Gleichungen - Textaufgaben");
                subtopicCheckBox3.setVisible(false);
                subtopicCheckBox4.setVisible(false);
                IntStream.range(0, topicIsSelected.length).forEach(i -> topicIsSelected[i] = false);
                solveButton.setVisible(false);
                rightAnswerLabel.setVisible(false);
                wrongAnswerLabel.setVisible(false);
                showRightAnswerButton.setVisible(false);
                nextExerciseButton.setVisible(false);
                taskRequestLabel.setText("");
                exerciseTextField.setText("");
                exerciseTooltip.setText("");
                studentSolutionTextField.setText("");
                startExercisingButton.setVisible(true);
                primaryStage.setScene(selectTopicsScene);
            }
            else{
                String[] innerParts = completeCurrentExercise.split("%");
                taskRequest = innerParts[0];
                exercise = innerParts[1];
                solution = innerParts[2];
                explanation = innerParts[3];
                solveButton.setVisible(true);
                rightAnswerLabel.setVisible(false);
                wrongAnswerLabel.setVisible(false);
                showRightAnswerButton.setVisible(false);
                nextExerciseButton.setVisible(false);
                taskRequestLabel.setText(taskRequest);
                exerciseTextField.setText(exercise);
                exerciseTooltip.setText(exercise);
                studentSolutionTextField.setText("");
            }
        });
        showRightAnswerButton.setOnAction(event -> {
            final Stage rightAnswerDialog = new Stage();
            rightAnswerDialog.initModality(Modality.APPLICATION_MODAL);
            rightAnswerDialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Die richtige Lösung ist " + solution +"\n" +explanation));
            Button closeModalButton = new Button("Okay!");
            dialogVbox.getChildren().add(closeModalButton);
            closeModalButton.setOnAction(event1 -> rightAnswerDialog.close());
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            rightAnswerDialog.setScene(dialogScene);
            rightAnswerDialog.show();
        });
        //creating 4 scenes
        Scene introScene = new Scene(introLayout, 500, 130);
        auswahlScene = new Scene(auswahlLayout,500,130);
        selectTopicsScene = new Scene(selectTopicsLayout,500,130);
        testAndTrainScene = new Scene(testAndTrainLayout,500,130);
        primaryStage.setTitle("Der Mathe-Vorbereiter [BETA]");
        primaryStage.setScene(introScene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    private String getNextExercise(){
        switch (selectedTopic){
            case "Gleichungen und Terme":
                if (isTest){
                    topicIsSelected[0]=true;
                    topicIsSelected[1]=true;
                    topicIsSelected[2]=true;
                    return exercises.getTermeUndGleichungenExercise(topicIsSelected);
                }
                else return exercises.getTermeUndGleichungenExercise(topicIsSelected);
        }
        return "Dieser Fall hätte niemals eintreten können!";
    }
    // checks if the given solution to the exercise is correct
    private boolean isCorrect(String answer){
        solution=solution.trim();
        solution=solution.toLowerCase();
        solution=solution.replaceAll(" ","");
        answer=answer.trim();
        answer=answer.toLowerCase();
        answer=answer.replaceAll(" ","");
        return answer.equals(solution);
    }
    public static void main(String[] args) {
        launch(args);
    }
}