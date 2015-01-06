/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.MeteorCraft.Blocks;

import net.minecraft.world.World;
import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Libraries.IO.ReikaChatHelper;
import Reika.DragonAPI.Libraries.MathSci.ReikaMathLibrary;
import Reika.MeteorCraft.Entity.EntityMeteor;
import Reika.MeteorCraft.Event.EntryEvent;
import Reika.MeteorCraft.Event.ImpactEvent;

public class TileEntityMeteorRadar extends TileEntityMeteorBase {

	@Override
	public void updateEntity(World world, int x, int y, int z, int meta) {

	}

	@Override
	protected String getTEName() {
		return "Meteor Radar";
	}

	@Override
	public long getMinPower() {
		return 16384;
	}

	@Override
	public void onMeteor(EntryEvent e) {
		EntityMeteor m = e.meteor;
		double dd = ReikaMathLibrary.py3d(e.x-xCoord, 0, e.z-zCoord);
		if (this.canPerformActions() && dd <= this.getRange()) {
			if (!worldObj.isRemote)
				ReikaChatHelper.sendChatToAllOnServer("A meteor has been detected above "+m.posX+", "+m.posZ);
		}
	}

	@Override
	public void onImpact(ImpactEvent e) {
		EntityMeteor m = e.meteor;
		double dd = ReikaMathLibrary.py3d(e.x-xCoord, 0, e.z-zCoord);
		if (this.canPerformActions() && dd <= this.getRange()) {
			if (!worldObj.isRemote)
				ReikaChatHelper.sendChatToAllOnServer("A meteor impact has been detected at "+e.x+", "+e.y+", "+e.z);
		}
	}

	public int getRange() {
		return ModList.ROTARYCRAFT.isLoaded() ? (int)(this.getPower()/1024) : 128;
	}

}
