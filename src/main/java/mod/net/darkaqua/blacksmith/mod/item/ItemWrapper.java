package net.darkaqua.blacksmith.mod.item;

import net.darkaqua.blacksmith.api.item.IItem;
import net.minecraft.item.Item;

/**
 * Created by cout970 on 08/11/2015.
 */
public class ItemWrapper implements IItem {

    private Item item;

    public ItemWrapper(Item item){
        this.item = item;
    }

    public Item getItem(){
        return item;
    }
}
