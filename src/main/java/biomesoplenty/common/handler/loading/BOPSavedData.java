package biomesoplenty.common.handler.loading;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class BOPSavedData extends WorldSavedData
{
    public static final String DATA_IDENTIFIER = "biomesoplenty";
    
    public int lastLoadVersion;
    
    /**The version a chunk is assumed to be if it is already generated and does not possess a version tag**/
    public int defaultGeneratedVersion;
    
    public BOPSavedData(String identifier) 
    {
        super(identifier);
        
        this.lastLoadVersion = BlockSwapHandler.CURRENT_LOAD_VERSION;
        this.defaultGeneratedVersion = BlockSwapHandler.CURRENT_LOAD_VERSION;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) 
    {
        this.lastLoadVersion = nbt.getInteger("LastLoadVersion");
        this.defaultGeneratedVersion = nbt.getInteger("DefaultGeneratedVersion");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) 
    {
        nbt.setInteger("LastLoadVersion", this.lastLoadVersion);
        nbt.setInteger("DefaultGeneratedVersion", this.defaultGeneratedVersion);
    }
}
