package nana7mimod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class Base extends AbstractPower {
    private void init(String id, String name, AbstractCreature owner, int amount, PowerType type) {
        this.ID = id;
        this.name = name;
        this.owner = owner;
        this.amount = amount;
        this.type = type;
        updateDescription();
    }

    public static String convertUpperCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                // 检查前一个字符是否也是大写字母
                if (i > 0 && Character.isUpperCase(input.charAt(i - 1))) {
                    // 前一个字符是大写，当前字符直接变小写
                    result.append(Character.toLowerCase(currentChar));
                } else {
                    // 前一个字符不是大写，添加下划线+小写
                    result.append('_').append(Character.toLowerCase(currentChar));
                }
            } else {
                // 不是大写字母，保持不变
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public Base(String id, String name, AbstractCreature owner, int amount, PowerType type) {
        init(id, name, owner, amount, type);
        loadRegion(id);
    }

    public Base(String id, String name, AbstractCreature owner, int amount, PowerType type, String region) {
        init(id, name, owner, amount, type);
        super.loadRegion(region);
    }

    public void loadRegion(String id) {
        String fileName = convertUpperCase(ModHelper.unwrap(id).replace("Power", "")).substring(1);
        region48 = AbstractPowerPatch.powerAtlas.findRegion("48/" + fileName);
        region128 = AbstractPowerPatch.powerAtlas.findRegion("128/" + fileName);
    }
}
