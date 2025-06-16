package Project;

import java.time.*;
import java.util.*;

public class HarvesterSchedule {
    public LocalDate calculateHarvestDate(LocalDate plantingDate, String banana_variety) {
        DatabaseManager db = new DatabaseManager();
        List<BananaVariety> variety = db.fetchAllVariety();
        int durationInDays = 0;

        for(BananaVariety i : variety){
            if(i.getName().equals(banana_variety)){
                durationInDays = i.getGrowthDuration();
            }
        }

        return plantingDate.plusDays(durationInDays);
    }
}