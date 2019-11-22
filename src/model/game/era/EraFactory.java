package model.game.era;

public class EraFactory {

    public static Era modernEra;
    public static Era xviEra;

    public static Era GetModernEra(){
        if(modernEra == null){
            modernEra = new ModernEra();
        }
        return modernEra;
    }

    public static Era getXVIEra(){
        if(xviEra == null){
            xviEra = new XVIEra();
        }
        return xviEra;
    }
}
