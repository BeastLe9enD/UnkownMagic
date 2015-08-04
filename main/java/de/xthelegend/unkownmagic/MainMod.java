package de.xthelegend.unkownmagic;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.xthelegend.unkownmagic.common.items.ItemEssenceSeed;
import de.xthelegend.unkownmagic.common.items.ItemTransmutationCrystal;
import de.xthelegend.unkownmagic.proxy.CommonProxy;
import de.xthelegend.unkownmagic.reference.Reference;

@Mod(modid = Reference.MODID , version = Reference.VERSION, name = Reference.NAME, modLanguage = "java")

public class MainMod {
	@Instance(value = Reference.MODID)
	public static MainMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static CommonProxy proxy;

	public static CreativeTabs tabUnkownMagic = new CreativeTabs("tabUnkownMagic") {
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return essenceLeaf;
	    }
	};
	
	public static Item essenceLeaf = new Item().setUnlocalizedName("essenceLeaf").setTextureName(Reference.MODID + ":" + "essence_leaf").setCreativeTab(tabUnkownMagic);
	public static Item essenceStick = new Item().setUnlocalizedName("essenceStick").setTextureName(Reference.MODID + ":" + "essence_stick").setCreativeTab(tabUnkownMagic);
	public static Item transmutationCrystal = new ItemTransmutationCrystal("trans_crystal");
	public static Item essenceDust = new Item().setUnlocalizedName("essence_dust").setTextureName(Reference.MODID + ":" + "essence_dust").setCreativeTab(tabUnkownMagic);
	
	
	public static Block BlockEssenceCrop = new de.xthelegend.unkownmagic.common.blocks.BlockEssenceCrop();
	
	public static Item essenceSeeds = new ItemEssenceSeed(BlockEssenceCrop,Blocks.farmland);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.addGrassSeed(new ItemStack(essenceLeaf),1);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		GameRegistry.registerItem(essenceLeaf,essenceLeaf.getUnlocalizedName());
		GameRegistry.registerItem(essenceStick,essenceStick.getUnlocalizedName());
		GameRegistry.registerItem(transmutationCrystal,transmutationCrystal.getUnlocalizedName());
		GameRegistry.registerItem(essenceDust,essenceDust.getUnlocalizedName());
		
		
		GameRegistry.registerBlock(BlockEssenceCrop,BlockEssenceCrop.getUnlocalizedName());
		GameRegistry.registerItem(essenceSeeds,essenceSeeds.getUnlocalizedName());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
