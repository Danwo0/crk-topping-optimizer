import com.toppingoptimizer.DataManager;
import com.toppingoptimizer.optimizing.Combination;
import com.toppingoptimizer.optimizing.Configuration;
import com.toppingoptimizer.optimizing.Optimizer;
import com.toppingoptimizer.utils.StatType;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DataManager dm = new DataManager();
        dm.load("src/main/java/resources/data.json");

        Configuration cf1 = new Configuration.ConfigurationBuilder(StatType.ATTACK).build();
        Configuration cf2 = new Configuration.ConfigurationBuilder(StatType.COOLDOWN).build();

        List<Configuration> configs = List.of(cf2, cf1);
        Optimizer op = new Optimizer(dm.getInventory());

        Combination combination = op.optimize(configs, false);
        System.out.println(combination.getSummary());
        //dm.save("src/main/java/resources/dataOut.json");
    }
}