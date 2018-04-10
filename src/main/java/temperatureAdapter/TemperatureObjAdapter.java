package temperatureAdapter;

public class TemperatureObjAdapter implements TemperatureAdapter {

    Celsius celsius;

    public TemperatureObjAdapter() {
        celsius = new Celsius();
    }

    @Override
    public double getTemperatureInF() {
        return celsiusToFahrenheit(celsius.getTemperature());
    }

    @Override
    public void setTemperatureInF(double temperatureInF) {
        celsius.setTemperature(fahrenheitToCelsius(temperatureInF));
    }

    @Override
    public double getTemperatureInC() {
        return celsius.getTemperature();
    }

    @Override
    public void setTemperatureInC(double temperatureInC) {
        celsius.setTemperature(temperatureInC);
    }


    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
}
