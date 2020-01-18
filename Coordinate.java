public class Coordinate {
    private int value;
    private float feromon;
    private boolean visited;

    public Coordinate(int value) {
        this.value = value;
        this.feromon = -1;
        this.visited = false;
    }

    public Coordinate(int value, float feromon) {
        this.value = value;
        this.feromon = feromon;
        this.visited = false;
    }

    void setValue(int value){
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    double getFeromon() {
        return this.feromon;
    }

    void setFeromon(float feromon){
        this.feromon += feromon;
    }
    
    void vaporateFeromon(float feromon){
        this.feromon *= feromon;
    }
    
    void removeFeromon(float feromon){
        this.feromon /= feromon;
    }
    
    boolean isVisited(){
        return this.visited;
    }
    
    void setVisited(boolean visited){
        this.visited = visited;
    }
}