package com.example.task14.controller;

import com.example.task14.model.ActionChain;
import com.example.task14.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView view;
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnStart;
    @FXML
    private TextField Name;
    @FXML
    private TextField Count;

    public Player player1;
    public ActionChain action;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onPlay(ActionEvent actionEvent) {
        if(!init()) return;//проверка ликвидности
        File file = new File("C:\\Users\\TDKpo\\Desktop\\Учёба\\2 Семестр 3 курс\\Технология программирования\\Task 14\\src\\main\\java\\com\\example\\task14\\controller\\мешочек.png");
        // --> file:/C:/MyImages/myphoto.jpg
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image image = new Image(localUrl);
        view.setImage(image);//загрузка автомата
        action=new ActionChain();//запуск механизма розыгрыша
    }

    public boolean init(){
        if(! player1.pay(1)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Средств на счете недостаточно, еще монетку плисс!");
            alert.show();
            action=null;
            File file = new File("C:\\Users\\TDKpo\\Desktop\\Учёба\\2 Семестр 3 курс\\Технология программирования\\Task 14\\src\\main\\java\\com\\example\\task14\\controller\\монетки.png");
            // --> file:/C:/MyImages/myphoto.jpg
            String localUrl = null;
            try {
                localUrl = file.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Image image = new Image(localUrl);
            view.setImage(image);
            return false;
        }
        else return true;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("C:\\Users\\TDKpo\\Desktop\\Учёба\\2 Семестр 3 курс\\Технология программирования\\Task 14\\src\\main\\java\\com\\example\\task14\\controller\\монетки.png");
        // --> file:/C:/MyImages/myphoto.jpg
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image image = new Image(localUrl);
        view.setImage(image);
        view.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (action == null) return;//если цепочка обработки отсутствует
            if (action.process()) init();//продолжить играть и проверить наличия монетки у игрока
            else action = null;//завершить игру, сделав обработку недоступной
        });
    }

    public void onStart(ActionEvent actionEvent) {
        if(!init()) return;//проверка ликвидности
        player1 = new Player(Name.getText(),Integer.parseInt (Count.getText().trim()));
        player1.pay(1);
        MotionBlur motionBlur = new MotionBlur();
        motionBlur.setRadius(20);
        motionBlur.setAngle(150.0);
        btnStart.setEffect(motionBlur);
    }
}
