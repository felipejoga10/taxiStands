
import java.util.InputMismatchException;

//Hello, my name is Felipe Bavaresco Zanatta, I'm student of analysis and systems development.
//It is a pleasure, be selected to this part.
//I hope you like the program, and call me back for a interview.

//Olá, meu nome é Felipe Bavaresco Zanatta, sou estudante de análise e desenvolvimento de sistemas.
//É um prazer fazer parte dessa seleção.
//Espero que gostem do programa, e me chamem para uma entrevista.

//AppTaxiStands.java => used to call the Start Main and treat through try and catch wrong entries by the users.
//AppTaxiStands.java => utilizado para chamar o menu inicial e tratar por meio do try e catch erros de entrada do usuário.
public class AppTaxiStands {

    public static void main(String[] args) {
        try {
            //Start main
            //Menu inicial
            Main.startMain();
        } catch (InputMismatchException e){
            //Wrong entry
            //Entrada errada
            System.out.println("Favor inserir um número válido.");
            System.out.println("Latitude e Longitude somente com ( , ).");
            System.out.println("Tente novamente!");
            Main.startMain();

        }
    }
}
