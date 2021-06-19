import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//Function.java is where all the functions called on Main.java are being called
//Function.java é onde as funções chamadas no Main.java estão sendo construídas
public class Function {

    //Function 1 to list all the taxi stands in Porto Alegre wich are in the csv file
    //Função 1 para listar todos os pontos de taxi da cidade de Porto Alegre contidos no arquivo csv
    public static void AllStands() {
        //Finding the csv file inside the folder
        //Encontrando o arquivo csv dentro da pasta
        Path path = Paths.get("/home/felipe/PontosTaxi/src/pontos_taxi.csv");
        try {
            //Bringing to the program all the information separated by ";" of the csv file
            //Trazendo para o programa todas  informações separadas por ";", do arquivo csv
            Files.lines(path)
                    .skip(1)
                    .map(lin -> lin.split(";"))
                    .map(col -> new Variables(Integer.parseInt(col[1]),col[2],col[3],col[4],col[5],
                            Double.parseDouble(col[6].replaceAll(",", ".")),
                            Double.parseDouble(col[7].replaceAll(",", "."))))
                    .forEach(t -> System.out.println("#######################"
                            + "\nNome: " + t.name
                            + "\nTelefone: " + t.phone
                            + "\nCodigo: " + t.code
                            + "\nLogradouro: " + t.address
                            + "\nNumero: " + t.number
                            + "\nLatitude: " + t.latitude
                            + "\nLongitude: " + t.longitude
                            + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closestTaxiStands(Double lat1,  Double lon1) {
        //Function used to find the taxi stands closest of the geographic coordinates insert by the user
        //Função usada para achar os pontos de taxi mais próximos das coordenadas geográficas inseridas pelo usuário
        Path path = Paths.get("/home/felipe/PontosTaxi/src/pontos_taxi.csv");
        //Storing the coordinates of the user
        //Armazenando as coordenadas do usuário
        //Latitude
        ArrayList<Double> coordinatesLat = new ArrayList<>();
        //Longitude
        ArrayList<Double> coordinatesLong = new ArrayList<>();
        //The haversine formula is an equation important in navigation, giving great-circle distances between
        // two points on a sphere from their longitudes and latitudes.
        //A fórmula de Haversine é uma importante equação de navegação, criando um círculo entre
        //dois pontos, pegando como ponto sua longitude e latitude.
        ArrayList<Double> haversineValues = new ArrayList<>();
        //Variables of the taxi stands
        //Variáveis dos pontos de taxi
        Double cord1 = 0.0;
        Double cord2 = 0.0;
        Double cord3 = 0.0;
        //Searching for the 3 taxi stands closest to the user's location
        //Buscando os 3 pontos de taxi mais próximos da localização do usuário
        Double val1 = 100000.0;
        Double val2 = 100000.0;
        Double val3 = 100000.0;
        //User's coordinates
        //Coordenadas do usuário
        Double latUser = lat1;
        Double lonUser = lon1;
        //Taxi Stands coordinates
        //Coordenadas dos pontos de taxi
        Double latStand1 = 0.0;
        Double lonStand1 = 0.0;
        Double latStand2 = 0.0;
        Double lonStand2 = 0.0;
        Double latStand3 = 0.0;
        Double lonStand3 = 0.0;
        try {
            //Reading all the coordinates of the taxi stands in the csv file
            //Lendo todas as coordenadas dos pontos de taxi no arquivo csv
            Files.lines(path)
                    .skip(1)
                    //Separating all the variables by the ;
                    //Separando as variáveis pelo ;
                    .map(lin -> lin.split(";"))
                    //Each informarion separated by columns
                    //Cada informação separada por colunas
                    .map(col -> new Variables(Integer.parseInt(col[1]),col[2],col[3],col[4],col[5],
                            //Replacing , for the .
                            //Trocando a , pelo .
                            Double.parseDouble(col[6].replaceAll(",", ".")),
                            Double.parseDouble(col[7].replaceAll(",", "."))))
                    .forEach(t ->
                            //ADD longitude
                            coordinatesLat.add(t.longitude)
                    );
            Files.lines(path)
                    .skip(1)
                    .map(lin -> lin.split(";"))
                    .map(col -> new Variables(Integer.parseInt(col[1]), col[2], col[3], col[4], col[5],
                            Double.parseDouble(col[6].replaceAll(",", ".")),
                            Double.parseDouble(col[7].replaceAll(",", "."))))
                    .forEach(t ->
                            //ADD latitude
                            coordinatesLong.add(t.latitude)
                    );
            //Applying the Haversine Form, to find the 3 closest taxi stands
            //Aplicando a fórmula citada acima, para localizar os 3 pontos de taxi com a menor distância
            for(int i = 0; i < coordinatesLat.size(); i++){
                haversineValues.add(HaversineForm.haversine(latUser, lonUser, coordinatesLat.get(i), coordinatesLong.get(i)));
            }
            //Find the closest taxi stand
            //Acha o ponto de taxi mais próximo
            for(Double value : haversineValues) {
                if(value < val1){
                    val1 = value;
                }
            }
            //Find the 2° closest taxi stand
            //Acha o 2° ponto de taxi mais próximo
            for(int i = 0; i < haversineValues.size(); i++) {
                if(haversineValues.get(i) < val2){
                    if(val1 != haversineValues.get(i)){
                        val2 = haversineValues.get(i);
                    }
                }
            }
            //Find the 3° closest taxi stand
            //Acha o 3° ponto de taxi mais próximo
            for(int i = 0; i < haversineValues.size(); i++) {
                if(haversineValues.get(i) < val3){
                    if(val1 != haversineValues.get(i)){
                        if(val2 != haversineValues.get(i)){
                            val3 = haversineValues.get(i);
                        }
                    }
                }
            }
            //Continuation of the Haversine Form of the first
            //Continuação da fórmula de Haversine do primeiro
            for(int i = 0; i < coordinatesLat.size(); i++){
                if(HaversineForm.haversine(latUser, lonUser, coordinatesLat.get(i), coordinatesLong.get(i)) == val1){
                    latStand1 = coordinatesLat.get(i);
                    lonStand1 = coordinatesLong.get(i);
                }
            }
            //Continuation of the Haversine Form of the second
            //Continuação da fórmula de Haversine do segundo
            for(int i = 0; i < coordinatesLat.size(); i++){
                if(HaversineForm.haversine(latUser, lonUser, coordinatesLat.get(i), coordinatesLong.get(i)) == val2){
                    latStand2 = coordinatesLat.get(i);
                    lonStand2 = coordinatesLong.get(i);
                }
            }
            //Continuation of the Haversine Form of the third
            //Continuação da fórmula de Haversine do terceiro
            for(int i = 0; i < coordinatesLat.size(); i++){
                if(HaversineForm.haversine(latUser, lonUser, coordinatesLat.get(i), coordinatesLong.get(i)) == val3){
                    latStand3 = coordinatesLat.get(i);
                    lonStand3 = coordinatesLong.get(i);
                }
            }
            //Accessing again the csv file to transform the geografic coordinates
            // of the taxi stands into string
            //Acessando novamente o arquivo csv para transformar as coordenadas
            // geográficas dos pontos de taxi em string
            String path1 = "/home/felipe/PontosTaxi/src/pontos_taxi.csv";
            String line = "";
            String latStandString1 = String.valueOf(latStand1);
            String latStandString2 = String.valueOf(latStand2);
            String latStandString3 = String.valueOf(latStand3);
            String lonStandString1 = String.valueOf(lonStand1);
            String lonStandString2 = String.valueOf(lonStand2);
            String lonStandString3 = String.valueOf(lonStand3);
            try {
                //Using BufferedReader to read the csv file
                //Utilizando o BufferedReader para a leitura do arquivo csv
                BufferedReader br = new BufferedReader(new FileReader(path1));
                while((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    //Showing for the user the closest taxi stand
                    //Mostrando na tela para o usuário o ponto de taxi mais próximo
                    if(values[6].replaceAll(",", ".").equals(latStandString1)
                            && values[7].replaceAll(",", ".").equals(lonStandString1)){
                        System.out.println("O primeiro ponto mais próximo de sua localização é: ");
                        System.out.println("###################################################");
                        System.out.println("");
                        System.out.println("Código: "+values[1]);
                        System.out.println("Nome: "+values[2]);
                        System.out.println("Telefone: "+values[3]);
                        System.out.println("Endereço: "+values[4]);
                        System.out.println("Número: "+values[5]);
                        System.out.println("Latitude: "+values[6]);
                        System.out.println("Longitude: "+values[7]);
                        System.out.println("###################################################");
                        System.out.println("");
                    }
                    //Showing for the user the 2° closest taxi stand
                    //Mostrando na tela para o usuário o 2° ponto de taxi mais próximo
                    if(values[6].replaceAll(",", ".").equals(latStandString2)
                            && values[7].replaceAll(",", ".").equals(lonStandString2)){
                        System.out.println("O segundo ponto mais próximo de sua localização é: ");
                        System.out.println("###################################################");
                        System.out.println("");
                        System.out.println("Código: "+values[1]);
                        System.out.println("Nome: "+values[2]);
                        System.out.println("Telefone: "+values[3]);
                        System.out.println("Endereço: "+values[4]);
                        System.out.println("Número: "+values[5]);
                        System.out.println("Latitude: "+values[6]);
                        System.out.println("Longitude: "+values[7]);
                        System.out.println("###################################################");
                        System.out.println("");
                    }
                    //Showing for the user the 3° closest taxi stand
                    //Mostrando na tela para o usuário o 3° ponto de taxi mais próximo
                    if(values[6].replaceAll(",", ".").equals(latStandString3)
                            && values[7].replaceAll(",", ".").equals(lonStandString3)){
                        System.out.println("O terceiro ponto mais próximo de sua localização é: ");
                        System.out.println("###################################################");
                        System.out.println("");System.out.println("");
                        System.out.println("Código: "+values[1]);
                        System.out.println("Nome: "+values[2]);
                        System.out.println("Telefone: "+values[3]);
                        System.out.println("Endereço: "+values[4]);
                        System.out.println("Número: "+values[5]);
                        System.out.println("Latitude: "+values[6]);
                        System.out.println("Longitude: "+values[7]);
                        System.out.println("###################################################");
                        System.out.println("");
                    }
                }
                //Catch used to find exceptions
                //Catch usado para achar exceções
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void taxiStandsStreetNames() {
        //Option 4 of the start main, this function will find
        // taxi stands by the street name typed in the program
        //Opção 4 do menu, essa função irá encontrar pontos de
        // taxi conforme a rua digitada no programa
        Path path = Paths.get("/home/felipe/PontosTaxi/src/pontos_taxi.csv");
        //Reading the street name typed by the user
        //Realizando a leitura do que foi digitado pelo usuário
        Scanner sc = new Scanner(System.in);
        try {
            //Converting all letters to uppercase
            //Convertendo todas as letras para maiúsculo
            System.out.println("Digite o nome da rua:");
            String addressUser = sc.nextLine().toUpperCase();
            //Show all the taxi stands in the street typed by the user
            //Mostrando todos os pontos de taxi na rua digitada pelo usuário
            Files.lines(path)
                    .skip(1)
                    .map(lin -> lin.split(";"))
                    .map(col -> new Variables(Integer.parseInt(col[1]),
                            col[2],
                            col[3],
                            col[4],
                            col[5],
                            Double.parseDouble(col[6].replaceAll(",", ".")),
                            Double.parseDouble(col[7].replaceAll(",", "."))))
                    .filter(t -> t.address.contains(addressUser))
                    .forEach(t -> System.out.println("#######################"
                            + "\nCodigo: " + t.code
                            + "\nNome: " + t.name
                            + "\nLogradouro: " + t.address
                            + "\nNumero: " + t.number
                            + "\nTelefone: " + t.phone
                            + "\nLatitude: " + t.latitude
                            + "\nLongitude: " + t.longitude
                            + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
