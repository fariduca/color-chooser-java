package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

import java.text.Format;

public class ColorChooserController {

    @FXML
    private TextField redTextField;

    @FXML
    private TextField greenTextField;

    @FXML
    private TextField blueTextField;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private Slider alphaSlider;

    @FXML
    private Rectangle colorRectangle;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    public void initialize() {
        Bindings.bindBidirectional(redTextField.textProperty(),
                redSlider.valueProperty(), new MyIntConverter());
        Bindings.bindBidirectional(greenTextField.textProperty(),
                greenSlider.valueProperty(), new MyIntConverter());
        Bindings.bindBidirectional(blueTextField.textProperty(),
                blueSlider.valueProperty(), new MyIntConverter());
        Bindings.bindBidirectional(alphaTextField.textProperty(),
                alphaSlider.valueProperty(), new MyDoubleConverter());

        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number t1) {
                red = t1.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
            }
        });
        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number t1) {
                green = t1.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
            }
        });
        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number t1) {
                blue = t1.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
            }
        });
        alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                                Number number, Number t1) {
                alpha = t1.doubleValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
            }
        });
    }

    private static class MyIntConverter extends StringConverter<Number> {

        @Override
        public String toString(Number number) {
            return String.format("%.0f", number.doubleValue());
        }

        @Override
        public Number fromString(String s) throws NumberFormatException{
            try {
                int v = Integer.parseInt(s);
                return (v >= 0 && v <= 255) ? v : 125;
            } catch (NumberFormatException e) {
                return 125;
            }
        }
    }

    private static class MyDoubleConverter extends StringConverter<Number> {

        @Override
        public String toString(Number number) {
            return String.format("%.2f", number.doubleValue());
        }

        @Override
        public Number fromString(String s) throws NumberFormatException{
            try {
                double v = Double.parseDouble(s);
                return (v >= 0 && v <= 1.0) ? v : 0.5;
            } catch (NumberFormatException e) {
                return 0.5;
            }
        }
    }
}
