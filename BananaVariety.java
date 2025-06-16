package Project;

public class BananaVariety{
    private int id;
    private String name;
    private String season;
    private int growth_duration;
    private double minsoilph;
    private double maxsoilph;

    BananaVariety(int id, String name, String season, int growth_duration, double minsoilph, double maxsoilph) {
        this.id = id;
        this.name = name;
        this.season = season;
        this.growth_duration = growth_duration;
        this.minsoilph = minsoilph;
        this.maxsoilph = maxsoilph;
    }

    BananaVariety(String name, String season, int growth_duration, double minsoilph, double maxsoilph){
        this.name = name;
        this.season = season;
        this.growth_duration = growth_duration;
        this.minsoilph = minsoilph;
        this.maxsoilph = maxsoilph;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getGrowthDuration() { return growth_duration; }
    public String getSeason() { return season; }
    public double getMinSoilPH() { return minsoilph; }
    public double getMaxSoilPH() { return maxsoilph; }

    public String toString() {
        return "[" + id + "]" + " | Name: " + name + " | Season: " + season + " | Days: " + growth_duration +
                " | Min pH: " + minsoilph + " | Max pH: " + maxsoilph;
    }
}
