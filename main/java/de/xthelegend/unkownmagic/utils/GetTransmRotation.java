package de.xthelegend.unkownmagic.utils;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

//@author("xTheLegend")

public class GetTransmRotation {
	public static boolean isBlockRotatedThreeByThree(World world, Block MiddleBlock, Block outsideBlock, int x, int y, int z, int location)
	{
		int j = (int) y + (int) location;
		if(world.getBlock(x, j , z) == outsideBlock 
				
				&& world.getBlock(x + 1, y, z + 1) == MiddleBlock // 1-1
				
						&& world.getBlock(x, y, z + 1) == MiddleBlock
						
								&& world.getBlock(x, y, z - 1) == MiddleBlock
								
										&& world.getBlock(x - 1, y, z + 1) == MiddleBlock
										
												&& world.getBlock(x - 1, y, z) == MiddleBlock
												
														&& world.getBlock(x + 1, y, z) == MiddleBlock
														
																&& world.getBlock(x - 1, y, z - 1) == MiddleBlock
																
																		&& world.getBlock(x + 1, y, z - 1) == MiddleBlock)
		{

			return true;
		}
		
		
		return false;
	}
	
	public static int isStrickCraftingTrue(Block StickBlock, World world, int x, int y, int z)
	{
		if(world.getBlock(x, y, z) == StickBlock )
		{
			if(world.getBlock(x - 1, y, z) == StickBlock)
			{
				return 1;
			}
			 if(world.getBlock(x + 1, y, z) == StickBlock)
			{
				return 2;
			}
			 if(world.getBlock(x, y, z - 1) == StickBlock)
			{
				return 3;
			}
			 if(world.getBlock(x, y, z + 1) == StickBlock)
				{
					return 4;
				}
			 else return 0;
		}
		 
		return 0;
	}
	public Block[] xPos = new Block[]{};
	public Block[] zPos = new Block[]{};
	
	public Block[] yPos = new Block[]{};
	
	for(int x = 0; x < 16; x++)
	{
		
	}
	
}
