import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Model.Movement;
import Model.Stock;

public class Main {
    
    public static void main(String[] args) {

        Main main = new Main(); 
        Stock stk = new Stock(); 

        stk.setId(main.generateId());




    }

    public void getInput(Stock stk){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Veuillez renseigner le stock initial : ");
            stk.setStkInit(new BigDecimal(sc.nextLine()));


            System.out.print("Veuillez renseigner la liste des sorties (séparées par un ';'): ");
            List<String> outputList =  new ArrayList<String>(Arrays.asList(sc.nextLine().split(";")));

            List<Movement> movList = new ArrayList<>(); 

            for (int i =0; i < outputList.size(); i++){
                Movement movement = new Movement(generateId(), new BigDecimal(outputList.get(i)), i); 
                movList.add(movement); 
            }
            stk.setMovList(movList);

            System.out.print("Veuillez renseigner le nombre de jour mini de couverture : ");
            stk.setCouvMin(Integer.parseInt(sc.nextLine()));

            System.out.print("Veuillez renseigner le nombre de jour cible à couvrir : ");
            stk.setCouvCible(Integer.parseInt(sc.nextLine()));
        }       
    }

    public String generateId(){
        String id = UUID.randomUUID().toString();
        return id; 
    }
}
