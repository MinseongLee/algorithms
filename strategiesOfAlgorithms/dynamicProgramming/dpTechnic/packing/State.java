package strategiesOfAlgorithms.dynamicProgramming.dpTechnic.packing;

public class State {
    String name;
    int volume;
    int want;

    public State(String name, int volume, int want) {
        this.name = name;
        this.volume = volume;
        this.want = want;
    }
}
