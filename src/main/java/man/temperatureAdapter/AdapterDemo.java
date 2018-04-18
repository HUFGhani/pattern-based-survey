package man.temperatureAdapter;

public class AdapterDemo {

    public static void main(String[] args) {

        // class adapter
        System.out.println("object adapter test");
        TemperatureAdapter tempInfo = new TemperatureObjAdapter();
        testTempInfo(tempInfo);


    }

    public static void testTempInfo(TemperatureAdapter tempInfo) {
        tempInfo.setTemperatureInC(0);
        System.out.println("temp in C:" + tempInfo.getTemperatureInC());
        System.out.println("temp in F:" + tempInfo.getTemperatureInF());

        tempInfo.setTemperatureInF(85);
        System.out.println("temp in C:" + tempInfo.getTemperatureInC());
        System.out.println("temp in F:" + tempInfo.getTemperatureInF());
    }

}