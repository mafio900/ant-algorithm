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
        AM mrufka = new AM(8, 0.5f, 4); //(początkowa ilość feromonów, mnożnik wyparowania zawsze musi być mniejszy niż 1, ilość feromonu zostawionego przez mrówkę)
        mrufka.resetMaze();
        mrufka.setMaze(labirynt); // podaj klase File jak o argument z labiryntem
        mrufka.solve(10,10); // (ilość iteracji, ilość mrówek)
    }
}
