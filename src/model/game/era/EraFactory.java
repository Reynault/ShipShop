package model.game.era;

public class EraFactory {

    public static Era modernEra;
    public static Era xviEra;

    public static Era getModernEra(){
        if(modernEra == null){
            modernEra = new Modern();
        }
        return modernEra;
    }

    public static Era getXVIEra(){
        if(xviEra == null){
            xviEra = new XVI();
        }
        return xviEra;
    }
}
