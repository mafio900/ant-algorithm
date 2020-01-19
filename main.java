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
        
        //(początkowa ilość feromonów, mnożnik wyparowania zawsze musi być mniejszy niż 1, ilość feromonu zostawionego przez mrówkę, włącz/wyłącz cofanie)
        AM mrufka = new AM(8, 0.5f, 4, true);
        
        mrufka.resetMaze();
        mrufka.setMaze(labirynt); // podaj klase File jak o argument z labiryntem
        mrufka.solve(10,50); // (ilość iteracji, ilość mrówek)
        
        System.out.println("\nIlość kroków: "+mrufka.iloscKrokow);
    }
}
