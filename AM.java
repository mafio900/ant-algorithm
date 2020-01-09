import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;

public class AM
{
    private static final int ROAD = 0;
    private static final int WALL = 1;
    private static final int PATH = 2;
    private static final int START = 3;
    private static final int EXIT = 4;
    private double INITFEROMON = 1;
    private double FEREMONVAPORATERATE = 0.5;
    private double FEREMONTOLEFT = 4;

    public Coordinate[][] maze;
    private Stack<Cell> antPosition = new Stack<Cell>();

    public AM()
    {

    }

    public void setMaze(File maze) throws FileNotFoundException {
        String fileText = "";
        try (Scanner input = new Scanner(maze)) {
            while (input.hasNextLine()) {
                fileText += input.nextLine() + "\n";
            }
        }
        initializeMaze(fileText);
    }

    public void resetMaze(){
        maze = null;
    }

    private void initializeMaze(String text) {
        if (text == null || (text = text.trim()).length() == 0) {
            throw new IllegalArgumentException("pusty dokument!");
        }

        String[] lines = text.split("[\r]?\n");

        maze = new Coordinate[lines.length][lines[0].length()-1];

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (lines[row].charAt(col) == '1')
                    maze[row][col] = new Coordinate(WALL);
                else
                    maze[row][col] = new Coordinate(ROAD, INITFEROMON);
            }
        }
        maze[0][0] = new Coordinate(START, INITFEROMON);
        maze[getHeight()-1][getWidth()-1] = new Coordinate(EXIT, INITFEROMON);
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Coordinate getEntry() {
        return maze[0][0];
    }

    public Coordinate getExit() {
        return maze[getHeight()-1][getWidth()-1];
    }

    public boolean isExit(int x, int y) {
        return x == getHeight()-1 && y == getWidth()-1;
    }

    public boolean isStart(int x, int y) {
        return x == 0 && y == 0;
    }

    public boolean isWall(int row, int col) {
        return maze[row][col].getValue() == WALL;
    }

    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col].getValue() == ROAD) {
                    result.append('0');
                } else if (maze[row][col].getValue() == WALL) {
                    result.append('1');
                } else if (maze[row][col].getValue() == START) {
                    result.append('3');
                } else if (maze[row][col].getValue() == EXIT) {
                    result.append('4');
                } else {
                    result.append('2');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void solve(int iloscIteracji, int iloscMrowek){
        for(int i = 0; i < iloscIteracji; i++){
            for(int j = 0; j < iloscMrowek; j++){

                findPath(0,0, true);
            }
            resetVisited();
        }

    }

    public boolean findPath(int row, int col, boolean canPush){
        if(canPush){
            antPosition.push(new Cell(row,col));
        }
        double suma = 0;
        int i = 0;
        boolean g=false,p=false,d=false,l=false;
        if(!maze[antPosition.peek().row][antPosition.peek().col+1].isVisited() 
            && !isWall(antPosition.peek().row, antPosition.peek().col+1) 
            && isValidLocation(antPosition.peek().row, antPosition.peek().col+1)){
                
            suma += maze[row][col+1].getFeromon();
            i++;
            p = true;
        }
        if(!maze[antPosition.peek().row+1][antPosition.peek().col].isVisited() 
            && !isWall(antPosition.peek().row+1, antPosition.peek().col) 
            && isValidLocation(antPosition.peek().row+1, antPosition.peek().col)){
                
            suma += maze[row+1][col].getFeromon();
            i++;
            d = true;
        }
        if(!maze[antPosition.peek().row-1][antPosition.peek().col].isVisited() 
            && !isWall(antPosition.peek().row-1, antPosition.peek().col) 
            && isValidLocation(antPosition.peek().row-1, antPosition.peek().col)){
                
            suma += maze[row-1][col].getFeromon();
            i++;
            g = true;
        }
        if(!maze[antPosition.peek().row][antPosition.peek().col-1].isVisited() 
            && !isWall(antPosition.peek().row, antPosition.peek().col-1) 
            && isValidLocation(antPosition.peek().row, antPosition.peek().col-1)){
                
            suma += maze[row][col-1].getFeromon();
            i++;
            l = true;
        }
        return true;
    }

    private void resetVisited(){
        for(int row = 0; row < getHeight(); row++){
            for(int col = 0; col < getWidth(); col++){
                maze[row][col].setVisited(false);
            }
        }
    }

    
}
