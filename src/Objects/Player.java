package Objects;

import Interfaces.DragonInterface;
import Interfaces.DragonType;

public class Player implements DragonInterface {

    private String Name;
    private DragonType Type;
    private int position;

    public Player(String name, DragonType type) {
        Name = name;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public DragonType getType() {
        return Type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getASCII() {
        switch (Type) {
            case BAT:
                return "┌─────────┐\n" +
                        "│         │\n" +
                        "│         │\n" +
                        "│         │\n" +
                        "│         │\n" +
                        "│__     __│\n" +
                        "│)_\\   /_(│\n" +
                        "│ '.\\‼/.` │\n" +
                        "│   '(`   │\n" +
                        "│    \"    │\n" +
                        "│         │\n" +
                        "│         │\n" +
                        "│         │\n" +
                        "└─────────┘";
            case BABY_DRAGON:
                return "┌─────────┐\n" +
                        "│    )  ( │\n" +
                        "│   (\\__/)│\n" +
                        "│   /` (/ │\n" +
                        "│  |● ●/) │\n" +
                        "│  / / /| │\n" +
                        "│ o o_/ , │\n" +
                        "│ (^(  ;  │\n" +
                        "│  (_(/   │\n" +
                        "│  ,      │\n" +
                        "│,//(     │\n" +
                        "│( ' )    │\n" +
                        "│ )       │\n" +
                        "└─────────┘";
            case SPIDER:
                return "";
            case SALAMANDER:
                return "";
            default:
                return null;
        }
    }
}
