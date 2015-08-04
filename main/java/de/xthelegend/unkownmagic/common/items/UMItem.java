package de.xthelegend.unkownmagic.common.items;

import de.xthelegend.unkownmagic.MainMod;
import de.xthelegend.unkownmagic.reference.Reference;
import net.minecraft.item.Item;

public class UMItem extends Item{
	public UMItem(String unlocalName)
	{
		this.setUnlocalizedName(unlocalName);
		this.setTextureName(Reference.MODID + ":" + unlocalName);
		setCreativeTab(MainMod.tabUnkownMagic);
	}
}
