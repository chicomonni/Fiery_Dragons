package Objects;

import Interfaces.DragonInterface;
import Interfaces.DragonType;

public class DragonCard implements DragonInterface {

    private final DragonType Type;
    private int Value;
    private String cardString;

    public DragonCard(DragonType type, int value) {
        Type = type;
        Value = value;
        cardString = getASCII();
        cardString = cardString.replace("xx", String.format("%2s", Value));
    }

    public String getCardback() {
        return "┌─────────┐\n" +
                "│┌───────┐│\n" +
                "││xx     ││\n" +
                "││       ││\n" +
                "││   ◊   ││\n" +
                "││  / \\  ││\n" +
                "││ ( ● ) ││\n" +
                "││{ )∙( }││\n" +
                "││{ )∙( }││\n" +
                "││ ( ● ) ││\n" +
                "││  \\ /  ││\n" +
                "││   ◊   ││\n" +
                "││       ││\n" +
                "││     xx││\n" +
                "│└───────┘│\n" +
                "└─────────┘";
    }

    public String getCardString() {
        return cardString;
    }

    @Override
    public String getASCII() {
        switch (Type) {
            case BAT:
                return "┌─────────┐\n" +
                        "│┌───────┐│\n" +
                        "││xx     ││\n" +
                        "││       ││\n" +
                        "││       ││\n" +
                        "││       ││\n" +
                        "│__     __│\n" +
                        "│)_\\   /_(│\n" +
                        "│ '.\\‼/.` │\n" +
                        "││  '(`  ││\n" +
                        "││   \"   ││\n" +
                        "││       ││\n" +
                        "││       ││\n" +
                        "││     xx││\n" +
                        "│└───────┘│\n" +
                        "└─────────┘";
            case BABY_DRAGON:
                return "┌─────────┐\n" +
                        "│┌─────   │\n" +
                        "││xx )  ( │\n" +
                        "││  (\\__/)│\n" +
                        "││  /` (/ │\n" +
                        "││ |● ●/)││\n" +
                        "││ / / /|││\n" +
                        "││o o_/ ,││\n" +
                        "││(^(  ; ││\n" +
                        "││ (_(/  ││\n" +
                        "│  ,     ││\n" +
                        "│,//(    ││\n" +
                        "│( ' )   ││\n" +
                        "│ )    xx││\n" +
                        "│  ──────┘│\n" +
                        "└─────────┘";
            case SPIDER:
                return "┌─────────┐\n" +
                        "│┌───────┐│\n" +
                        "││xx     ││\n" +
                        "││       ││\n" +
                        "││  / \\  ││\n" +
                        "││ // \\\\ ││\n" +
                        "││ \\\\●// ││\n" +
                        "││/// \\\\\\││\n" +
                        "││││   ││││\n" +
                        "││││\\_/││││\n" +
                        "││`│   │'││\n" +
                        "││ `   ' ││\n" +
                        "││       ││\n" +
                        "││     xx││\n" +
                        "│└───────┘│\n" +
                        "└─────────┘";
            case SALAMANDER:
                return "┌─────────┐\n" +
                        "│┌───────┐│\n" +
                        "││xx     ││\n" +
                        "││       ││\n" +
                        "││  .-_  ││\n" +
                        "││ ' ● ‾; │\n" +
                        "││:.-_ ,-<│\n" +
                        "││`. (    │\n" +
                        "││  \\ \\  ││\n" +
                        "││ .-\\ \\ ││\n" +
                        "││( __. .││\n" +
                        "││\\_ _ .'││\n" +
                        "││       ││\n" +
                        "││     xx││\n" +
                        "│└───────┘│\n" +
                        "└─────────┘";
            case DRAGON_PIRATE:
                return "┌─────────┐\n" +
                        "│┌───────┐│\n" +
                        "││xx     ││\n" +
                        "││       ││\n" +
                        "││  .-.  ││\n" +
                        "││ /   \\ ││\n" +
                        "││ _   _ ││\n" +
                        "││|█   █|││\n" +
                        "││└‾ ^ ‾┘││\n" +
                        "││ )   ( ││\n" +
                        "││  uuu  ││\n" +
                        "││  └─┘  ││\n" +
                        "││       ││\n" +
                        "││     xx││\n" +
                        "│└───────┘│\n" +
                        "└─────────┘";
            default:
                return null;
        }
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }
}
