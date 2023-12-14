package me.restonic4.fading_realms.item;

import me.restonic4.restapi.holder.RestItem;
import me.restonic4.restapi.item.ItemRegistry;
import me.restonic4.restapi.util.CustomItemProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Rarity;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class ItemManager {
    public static RestItem DivineKey = ItemRegistry.CreateCustom(
            MOD_ID,
            "divine_key",
            () -> new FlintAndSteelItem(
                    new CustomItemProperties().tab(CreativeModeTabs.TOOLS_AND_UTILITIES).rarity(Rarity.EPIC).build()
            )
    );

    public static void register() {
        ItemRegistry.Register(MOD_ID);
    }
}
