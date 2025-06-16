package Project;

import java.util.*;

public class SoilCondition {
    private double inputPH;

    public SoilCondition(double inputPH) {
        this.inputPH = inputPH;
    }

    public List<BananaVariety> getRecommendedVarieties() {
        DatabaseManager db = new DatabaseManager();
        return db.recommended_varieites(inputPH);
    }
}

