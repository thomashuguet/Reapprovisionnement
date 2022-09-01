import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import Model.Movement;
import Model.Product;
import Model.Stock;

public class Main {

    public int maxJours = 30; 
    
    public static void main(String[] args) {

        Main main = new Main(); 
        /*Stock stk = new Stock();
        stk.setId(main.generateId());
        main.getInput(stk);
        main.calculStockProjeteSansAppro(stk);*/

        Stock stk = main.getDonnée(); 

        Map<Integer, BigDecimal> approByDayMap = main.CalculPropoApprovisionnement(stk); 
        approByDayMap.forEach((key, value) -> System.out.println("Jour " + key + " : " + "Qty a appro : " + value));

        main.calculStockProjeteAvecAppro(stk, approByDayMap); 
    }

    @Deprecated
    public void getInput(Stock stk){
        try (Scanner sc = new Scanner(System.in)) {

            Product prod = new Product(generateId(), "DEFAULT"); 

            System.out.print("Veuillez renseigner le stock initial : ");
            stk.setStkInit(new BigDecimal(sc.nextLine()));


            System.out.print("Veuillez renseigner la liste des sorties (séparées par un ';'): ");
            List<String> outputList =  new ArrayList<String>(Arrays.asList(sc.nextLine().split(";")));

            List<Movement> movList = new ArrayList<>(); 

            for (int i =0; i < outputList.size(); i++){
                Movement movement = new Movement(generateId(), prod, new BigDecimal(outputList.get(i)), i); 
                movList.add(movement); 
            }
            stk.setMovList(movList);

            System.out.print("Veuillez renseigner le nombre de jour mini de couverture : ");
            stk.setCouvMin(Integer.parseInt(sc.nextLine()));

            System.out.print("Veuillez renseigner le nombre de jour cible à couvrir : ");
            stk.setCouvCible(Integer.parseInt(sc.nextLine()));
        }       
    }

    public Stock getDonnée (){

        Product product = new Product(generateId(), "DEFAULT"); 

        BigDecimal stkInit = BigDecimal.valueOf(1200); 
        var couvMin = 5; 
        var couvCible = 3; 

        double[] outpuList = {79.23,93.49,76.29,93.53,68.24,112.35,41.81,112.15,88.45,62.80,104.44,102.57,87.02,17.30,85.03,3.29,85.49,15.22,11.88,87.94,80.14,70.43,17.67,86.04,27.12,14.14,2.01,37.63,8.45,31.56};

        List<Movement> movList = new ArrayList<>(); 
        
        for (int i = 0; i < outpuList.length; i++){
            Movement movement = new Movement(generateId(), product, BigDecimal.valueOf(outpuList[i]), i); 
            movList.add(movement); 
        }

        Stock stk = new Stock(generateId(), product, stkInit, couvMin, couvCible, movList); 

        return stk; 
    }

    public void calculStockProjeteSansAppro(Stock stk){
        System.out.println("Stock initial : " + stk.getStkInit()); 
        BigDecimal 𝑠𝑡𝑘𝑃𝑟𝑜𝑗 = stk.getStkInit();
        for (int i = 0 ; i <= maxJours; i++){  
            if (i < stk.getMovList().size()){
                //Movement movement = stk.getMovList().get(i); 
                𝑠𝑡𝑘𝑃𝑟𝑜𝑗 = 𝑠𝑡𝑘𝑃𝑟𝑜𝑗.subtract(stk.getMovList().get(i).getQty());
            }
            System.out.println("Jour " + i  + " : " + 𝑠𝑡𝑘𝑃𝑟𝑜𝑗);
        }
    }

    public Map<Integer, BigDecimal> CalculPropoApprovisionnement(Stock stk){
        Map<Integer, BigDecimal> approDayQtyMap = new HashMap<>(); 

        BigDecimal stkProj = stk.getStkInit(); 
        BigDecimal stkCouvMin = BigDecimal.ZERO; 
        BigDecimal stkCouvCible = BigDecimal.ZERO; 

        List<BigDecimal> outputList = stk.getMovList().stream()
                                        .map(Movement::getQty)
                                        .collect(Collectors.toList()); 

        for(Movement movement : stk.getMovList()){
            stkProj = stkProj.subtract(movement.getQty());     

            int dayCouvMin = (movement.getDay() + stk.getCouvMin() < maxJours) 
                                    ? movement.getDay() + stk.getCouvMin() 
                                    : maxJours; 
            int dayCouvCible = (movement.getDay() + stk.getCouvCible() < maxJours) 
                                    ? movement.getDay() + stk.getCouvCible() 
                                    : maxJours; 

            stkCouvMin = outputList.subList(movement.getDay(), dayCouvMin)
                                .stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add); 
            
            stkCouvCible = outputList.subList(movement.getDay(), dayCouvCible)
                                .stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add);             

            if (stkProj.compareTo(stkCouvMin) <= 0){
                approDayQtyMap.put(movement.getDay(), stkCouvCible); 
                stkProj = stkProj.add(stkCouvCible); 
            }
        }

        return approDayQtyMap; 
    }

    private void calculStockProjeteAvecAppro(Stock stk, Map<Integer, BigDecimal> approByDayMap) {
        System.out.println("Stock initial : " + stk.getStkInit()); 
        BigDecimal 𝑠𝑡𝑘𝑃𝑟𝑜𝑗 = stk.getStkInit();
        for (int i = 0 ; i < maxJours; i++){  
            if (i < stk.getMovList().size()){
                //Movement movement = stk.getMovList().get(i); 
                if (approByDayMap.get(i) != null){
                    𝑠𝑡𝑘𝑃𝑟𝑜𝑗 = 𝑠𝑡𝑘𝑃𝑟𝑜𝑗.add(approByDayMap.get(i)); 
                }
                𝑠𝑡𝑘𝑃𝑟𝑜𝑗 = 𝑠𝑡𝑘𝑃𝑟𝑜𝑗.subtract(stk.getMovList().get(i).getQty());
            }
            System.out.println("Jour " + i + " : " + 𝑠𝑡𝑘𝑃𝑟𝑜𝑗);
        }
    }

    public String generateId(){
        String id = UUID.randomUUID().toString();
        return id; 
    }
}
