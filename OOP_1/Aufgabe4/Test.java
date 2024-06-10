import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Test {

    public static void main(String[] args) {

        List<Unit> units = new LinkedList<>();
        Map<Class<?>, Integer> amountMap = new HashMap<>();
        int budget = 100000;
        System.out.println("Choosing Units with Investment Budget "+budget);
        while(budget > 0){
            int rand = random(0,5);
            Unit tmp;
            switch(rand){
                case 0:
                    //Batterie
                    tmp = new Electric_Power_Unit(0,1,2,
                            0.02, 100);
                    break;
                case 1:
                    tmp = new Heating_System();
                    break;
                case 2:
                    tmp = new HeatPump();
                    break;
                case 3:
                    tmp = new PhotovoltaicSystem();
                    break;
                case 4:
                    tmp = new SolarThermalSystem();
                    break;
                case 5:
                    tmp = new WindTurbine();
                    break;
                default:
                    //Nie erreichbar
                    tmp = new SolarThermalSystem();
                    System.out.println("Das unerreichbare wurde erreicht!!");
            }
            int newBudget = budget - tmp.investmentCosts();
            if(newBudget > 0){
                int amount = amountMap.getOrDefault(tmp.getClass(), 0)+1;
                amountMap.put(tmp.getClass(),amount);
                units.add(tmp);
            }
            budget = newBudget;
        }
        System.out.println("I bought "+units.size()+" Units");
        for(Class<?> clazz : amountMap.keySet()){
            System.out.println("    "+clazz.getSimpleName()+" - "+amountMap.get(clazz));
        }

        int runningSum = units.stream().mapToInt(Unit::runningCosts).sum();
        int energyInput = units.stream().mapToInt(Unit::energyInput).sum();
        int energyOutput = units.stream().mapToInt(Unit::energyOutput).sum();

        System.out.println("The systems together cost "+ runningSum+"€ per year to maintain");
        System.out.println("They need "+energyInput+"KWh energy as input");
        System.out.println("And they produce "+energyOutput+"KWh energy");

        if(energyInput < energyOutput){
            System.out.println("This is very efficient!");
        } else {
            System.out.println("We lose energy. NOOOOOOO");
        }



    }


    public static int random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}


