package Module8;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class JavaFXTask1 extends Application{
    Random random = new Random();
    Pane root = new Pane();
    private List<Rectangle> rectangles2 = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Random random = new Random();

        final Button singleThread = new Button("Single Thread");
        final Button multyThreads = new Button("Multy Threads");

        singleThread.setTranslateX(10);
        singleThread.setTranslateY(10);
        multyThreads.setTranslateX(100);
        multyThreads.setTranslateY(10);

        root.getChildren().addAll(singleThread,multyThreads);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);

        Thread thread = new Thread(() -> {
            createRectangles();
        });
        multyThreads.setOnMouseClicked(event -> {
            thread.start();
        });

        singleThread.setOnMouseClicked(event -> {
            createRectangles2();
        });
    }

    public void createRectangle(){
        Rectangle rectangle = new Rectangle(random.nextInt(50),random.nextInt(20),getColor());

        rectangle.setTranslateX(random.nextInt(100));
        rectangle.setTranslateY(random.nextInt(100));

        Thread thread = new Thread(()->{
            while(true) {
                final double x = rectangle.getTranslateX() + random.nextInt(5);
                final double y = rectangle.getTranslateY() + random.nextInt(10);

                Platform.runLater(() -> {
                    rectangle.setTranslateX(x);
                    rectangle.setTranslateY(y);
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } });


        Platform.runLater(() -> {
            root.getChildren().addAll(rectangle);
            thread.start();
        });
    }

    private void createRectangles2(){
        int count = random.nextInt(8) + 3;
        for (int i = 0; i < count; i++){
            rectangles2.add(createRectangle2());
        }
    }

    private Rectangle createRectangle2(){
        Rectangle rectangle = new Rectangle(random.nextInt(50),random.nextInt(20),getColor());

        rectangle.setTranslateX(random.nextInt(100));
        rectangle.setTranslateY(random.nextInt(100));

        Thread thread = new Thread(()->{
            while(true) {
                final double x = rectangle.getTranslateX() + random.nextInt(5);
                final double y = rectangle.getTranslateY() + random.nextInt(10);

                Platform.runLater(() -> {
                    rectangle.setTranslateX(x);
                    rectangle.setTranslateY(y);
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } });


        Platform.runLater(() -> {
            root.getChildren().addAll(rectangle);
            thread.start();
        });

        return rectangle;

    }


    private Color getColor(){
        Color color = Color.color(random.nextDouble(),
                random.nextDouble(),
                random.nextDouble(),
                0.7f);
        return color;
    }


    public void createRectangles(){
        Rectangle[] rectangles2 = new Rectangle[random.nextInt(8)+3];
        for (Rectangle rectangles: rectangles2){
            createRectangle();

        }
    }

}
