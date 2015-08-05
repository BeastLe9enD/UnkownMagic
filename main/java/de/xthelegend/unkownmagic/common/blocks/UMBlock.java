package de.xthelegend.unkownmagic.common.blocks;

import de.xthelegend.unkownmagic.MainMod;
import de.xthelegend.unkownmagic.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class UMBlock extends Block 
{

	public UMBlock(Material material, String unlocalName, float/*double*/ hardness) {
		super(material);
		// TODO Auto-generated constructor stub
		this.setBlockName(unlocalName);
		this.setBlockTextureName(Reference.MODID + ":" + unlocalName);
		setCreativeTab(MainMod.tabUnkownMagic);
		setHardness(hardness);
		setResistance(hardness);
	}
	
}