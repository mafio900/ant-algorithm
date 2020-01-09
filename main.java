import java.io.File;
import java.util.Scanner;

public class main
{
    public static void main() throws Exception 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj wielkość labiryntu (8):");
        String name = scan.nextLine();
        File labirynt = new File(name+".txt");
        AM mrufka = new AM();
        mrufka.resetMaze();
        mrufka.setMaze(labirynt);
        System.out.println(mrufka);
    }
}
