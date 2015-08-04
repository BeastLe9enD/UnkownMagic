package de.xthelegend.unkownmagic.common.items;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import de.xthelegend.unkownmagic.MainMod;
import de.xthelegend.unkownmagic.reference.Reference;
import de.xthelegend.unkownmagic.utils.GetTransmRotation;

public class ItemTransmutationCrystal extends UMItem{

	public ItemTransmutationCrystal(String unlocalName) {
		super(unlocalName);
		this.setMaxDamage(1000);
	}
	
	@Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }
	
	@Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        ItemStack movedStack = itemStack.copy();

        movedStack.setItemDamage(movedStack.getItemDamage() + 1);
        movedStack.stackSize = 1;

        return movedStack;
    }
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int metadata, float playerX, float playerY, float playerZ)
    {
		
		if(GetTransmRotation.isBlockRotatedThreeByThree(world,Blocks.air, Blocks.air, x, y, z, 0))
		{
			stack.damageItem(Reference.TRANSMUTATION_DURABILITY_USE, player);
		}
		int stickD = GetTransmRotation.isStrickCraftingTrue((Block) Blocks.log, world, x, y, z);
		if(stickD != 0 && stickD != -0)
		{
			Block air = (Block)Blocks.air;
			int zero = 0, one = 1;
			EntityItem entityItem = new EntityItem(world);
			entityItem.setEntityItemStack(new ItemStack(MainMod.essenceStick,one,zero));
			entityItem.setPosition(x, y + 0.75D, z);
			world.spawnEntityInWorld(entityItem);
			stack.damageItem(Reference.TRANSMUTATION_DURABILITY_USE, player);
			world.setBlock(x,y,z,air);
			if(stickD==1)
				world.setBlock(x - 1,y,z, air);
			if(stickD==2)
				world.setBlock(x + 1,y,z, air);
			if(stickD==3)
				world.setBlock(x,y,z - 1, air);
			if(stickD==4)
				world.setBlock(x,y,z + 1, air);
		}
        return false;
    }
	

}
