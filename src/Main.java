import java.util.Scanner;
//maun.java used to show the start main and call the functins to road the program
//Main.java utilizado para mostrar o menu inicial e chamar as funções necessárias para executar o programa
public class Main {
    public static void startMain() {
        //Default number for Latitude and Longitude.
        //Valores iniciais para Latitude e Longitude.
        double latitude = 0.0;
        double longitude = 0.0;
        for (int i = 0; i < 1; ) {
            //Starting main
            //Iniciando o Menu
            System.out.println("###########MENU###########");
            System.out.println("1 - Listar todos os pontos de táxi:");
            System.out.println("2 - Informar minha localização:");
            System.out.println("3 - Encontrar pontos próximos:");
            System.out.println("4 - Buscar pontos por logradouro:");
            System.out.println("5 - Terminar o programa.");
            System.out.println("#############################");
            System.out.println();
            //User input
            //Entrada do usuário
            System.out.println("Escolha uma das opções: ");
            Scanner sc = new Scanner(System.in);
            //Reading the user input
            //Lendo a entrada do usuário
            int reader = sc.nextInt();
            switch (reader) {
                case 1:
                    //Option 1: list all the TAXI STANDS
                    //Opção 1: listar todos os PONTOS DE TAXI
                    System.out.println("Todos os Pontos de Taxi da cidade de PORTO ALEGRE:");
                    //Function to show all the taxi stadns of the city.
                    //Função para chamar todos os pontos de Taxi da cidade.
                    Function.AllStands();
                    break;
                case 2:
                    //Option 2: Request the user's location
                    //Opção 2: Solicitar a localização do usuário
                    System.out.println("Informar minha localização: ");
                    System.out.print("Digite sua latitude utilizando (,): ");
                    latitude = sc.nextDouble();
                    System.out.print("Digite sua longitude utilizando (,): ");
                    longitude = sc.nextDouble();
                    //User located saved in the program
                    //Localização salva no programa
                    System.out.println("Localização armazenada.");
                    break;
                case 3:
                    //Finding nearest taxi stands accordin to the informed location
                    //Encontrando pontos de taxi mais próximos, conforme a localização informada
                    if (latitude == 0.0 && longitude == 0.0) {
                        //If the user didn't inform the location
                        //Se o usuário não informou a sua localização
                        System.out.println("Latitude e Longitude não informadas:");
                        System.out.println("No menu, escolha a opção 2 e informe sua localização.");
                    } else {
                        //Running the function to find the nearest taxi stands
                        //Executando a função para encontrar os pontos de taxi mais próximos
                        Function.closestTaxiStands(latitude, longitude);
                    }
                    break;
                case 4:
                    //Searching for taxi stands by the streets names
                    //Buscando pontos de taxi pelo nome da rua
                    Function.taxiStandsStreetNames();
                    break;
                case 5:
                    //Ending the program
                    //Encerrando o programa
                    System.out.println("Obrigado por utilizar nossos serviços.");
                    System.out.println("Continue utilizando TAXIS.");
                    i = 1;
                    break;
                default:
                    //Invalid input
                    //Entrada inválida
                    System.out.println("Número inválido, tente novamente.");
                    break;
            }
        }
    }
}