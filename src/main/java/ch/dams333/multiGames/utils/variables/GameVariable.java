package ch.dams333.multiGames.utils.variables;

public class GameVariable {

    private String name;
    private Object value;


    public GameVariable(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getIntValue(){
        return Integer.parseInt(String.valueOf(value));
    }

    public float getFloatValue(){
        return Float.parseFloat(String.valueOf(value));
    }

    public double getDoubleValue(){
        return Double.parseDouble(String.valueOf(value));
    }

    public boolean getBooleanValue(){
        return Boolean.parseBoolean(String.valueOf(value));
    }

    public String getStringValue(){
        return String.valueOf(value);
    }

    
}
