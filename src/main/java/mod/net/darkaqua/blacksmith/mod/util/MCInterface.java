package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.block.IIProperty;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.ILivingEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.network.packet.IDescriptionPacket;
import net.darkaqua.blacksmith.api.render.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.storage.IDataCompound;
import net.darkaqua.blacksmith.api.storage.IDataList;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3i;
import net.darkaqua.blacksmith.api.world.IChunk;
import net.darkaqua.blacksmith.api.world.IIBlockAccess;
import net.darkaqua.blacksmith.api.world.IIChunkProvider;
import net.darkaqua.blacksmith.api.world.IWorld;
import net.darkaqua.blacksmith.mod.block.BlockWrapper;
import net.darkaqua.blacksmith.mod.block.blockstate.IBlockStateWrapper;
import net.darkaqua.blacksmith.mod.block.blockstate.IPropertyWrapper;
import net.darkaqua.blacksmith.mod.creativetab.CreativeTabWrapper;
import net.darkaqua.blacksmith.mod.entity.EntityPlayerWrapper;
import net.darkaqua.blacksmith.mod.entity.EntityWrapper;
import net.darkaqua.blacksmith.mod.inventory.ItemStackWrapper;
import net.darkaqua.blacksmith.mod.inventory.SidedInventoryWrapper;
import net.darkaqua.blacksmith.mod.inventory.SimpleInventoryWrapper;
import net.darkaqua.blacksmith.mod.item.ItemBlockWrapper;
import net.darkaqua.blacksmith.mod.item.ItemWrapper;
import net.darkaqua.blacksmith.mod.network.packet.DescriptionPacketWrapper;
import net.darkaqua.blacksmith.mod.render.gui.FontRendererWrapper;
import net.darkaqua.blacksmith.mod.storage.NBTTagCompoundWrapper;
import net.darkaqua.blacksmith.mod.storage.NBTTagListWrapper;
import net.darkaqua.blacksmith.mod.tileentity.TileEntityWrapper;
import net.darkaqua.blacksmith.mod.world.ChunkWrapper;
import net.darkaqua.blacksmith.mod.world.IBlockAccessWrapper;
import net.darkaqua.blacksmith.mod.world.IChunkProviderWrapper;
import net.darkaqua.blacksmith.mod.world.WorldWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;


public class MCInterface {

    public static IIBlockAccess fromBlockAccess(IBlockAccess world) {
        if (world == null) return null;
        return new IBlockAccessWrapper(world);
    }

    public static IBlockAccess toBlockAccess(IIBlockAccess world) {
        if (world instanceof IBlockAccessWrapper)
            return ((IBlockAccessWrapper) world).getIBlockAccess();
        return null;
    }

    public static Vect3i fromBlockPos(BlockPos pos) {
        return new Vect3i(pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos toBlockPos(Vect3i pos) {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }

    public static IWorld fromWorld(World world) {
        if (world == null) return null;
        return new WorldWrapper(world);
    }

    public static World toWorld(IWorld world) {
        if (world instanceof WorldWrapper)
            return ((WorldWrapper) world).getWorld();
        return null;
    }

    public static Direction fromEnumFacing(EnumFacing side) {
        if (side == null) return null;
        return Direction.getDirection(side.getIndex());
    }

    public static EnumFacing toEnumFacing(Direction side) {
        if (side == null) return null;
        return EnumFacing.VALUES[side.ordinal()];
    }

    public static Cube fromAxisAlignedBB(AxisAlignedBB aabb) {
        if (aabb == null) return null;
        return new Cube(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
    }

    public static AxisAlignedBB toAxisAlignedBB(Cube cube) {
        if (cube == null) return null;
        return AxisAlignedBB.fromBounds(cube.minX(), cube.minY(), cube.minZ(), cube.maxX(), cube.maxY(),
                cube.maxZ());
    }

    public static IEntity fromEntity(Entity entity) {
        if (entity == null) return null;
        return new EntityWrapper(entity);
    }

    public static Entity toEntity(IEntity entity) {
        if (entity instanceof EntityWrapper)
            return ((EntityWrapper) entity).getEntity();
        return null;
    }

    public static IItemStack fromItemStack(ItemStack stack) {
        if (stack == null) return null;
        return new ItemStackWrapper(stack);
    }

    public static ItemStack toItemStack(IItemStack stack) {
        if (stack instanceof ItemStackWrapper)
            return ((ItemStackWrapper) stack).getItemStack();
        return null;
    }

    public static IItem fromItem(Item item) {
        if (item == null) return null;
        if (item instanceof ItemBlock){
            return new ItemBlockWrapper((ItemBlock) item);
        }
        return new ItemWrapper(item);
    }

    public static Item toItem(IItem item) {
        if (item instanceof ItemWrapper)
            return ((ItemWrapper) item).getItem();
        return null;
    }

    public static IBlockVariant fromIBlockState(IBlockState state) {
        if (state == null) return null;
        return new IBlockStateWrapper(state);
    }

    public static IBlockState toIBlockState(IBlockVariant state) {
        if (state instanceof IBlockStateWrapper)
            return ((IBlockStateWrapper) state).getIBlockState();
        return null;
    }

    public static IBlock fromBlock(Block block) {
        if (block == null) return null;
        return new BlockWrapper(block);
    }

    public static Block toBlock(IBlock block) {
        if (block instanceof BlockWrapper)
            return ((BlockWrapper) block).getBlock();
        return null;
    }

    public static ICreativeTab fromCreativeTab(CreativeTabs tab) {
        if (tab == null) return null;
        return new CreativeTabWrapper(tab);
    }

    public static CreativeTabs fromCreativeTab(ICreativeTab tab) {
        if (tab instanceof CreativeTabWrapper) {
            return ((CreativeTabWrapper) tab).getCreativeTab();
        }
        return null;
    }

    public static IDataCompound fromNBTCompound(NBTTagCompound tag) {
        if (tag == null)
            return null;
        return new NBTTagCompoundWrapper(tag);
    }

    public static NBTTagCompound toNBTCompound(IDataCompound tag) {
        if (tag instanceof NBTTagCompoundWrapper) {
            return ((NBTTagCompoundWrapper) tag).getNBTTagCompound();
        }
        return null;
    }

    public static ITileEntity fromTileEntity(TileEntity tile) {
        if (tile == null) return null;
        return new TileEntityWrapper(tile);
    }

    public static TileEntity toTileEntity(ITileEntity tile) {
        if (tile instanceof TileEntityWrapper)
            return ((TileEntityWrapper) tile).getTileEntity();
        return null;
    }

    public static IDescriptionPacket toDescriptionPacket(S35PacketUpdateTileEntity pack) {
        if (pack == null) return null;
        return new DescriptionPacketWrapper(pack);
    }

    public static S35PacketUpdateTileEntity fromDescriptionPacket(IDescriptionPacket pack) {
        if (pack instanceof DescriptionPacketWrapper)
            return ((DescriptionPacketWrapper) pack).getPacket();
        return null;
    }

    public static IPlayer toPlayer(EntityPlayer player) {
        if (player == null) return null;
        return new EntityPlayerWrapper(player);
    }

    public static EntityPlayer fromPlayer(IPlayer player) {
        if (player instanceof EntityPlayerWrapper)
            return ((EntityPlayerWrapper) player).getPlayer();
        return null;
    }

    public static IIProperty fromIProperty(IProperty prop) {
        if (prop == null) return null;
        return new IPropertyWrapper(prop);
    }

    public static IProperty toIProperty(IIProperty prop) {
        if (prop instanceof IPropertyWrapper)
            return ((IPropertyWrapper) prop).getIProperty();
        return null;
    }

    public static IFontRenderer fromFontRenderer(FontRenderer fontRenderer) {
        if (fontRenderer == null) {
            return null;
        }
        return new FontRendererWrapper(fontRenderer);
    }

    public static IIChunkProvider fromIChunkProvider(IChunkProvider chunkProvider) {
        if (chunkProvider == null) {
            return null;
        }
        return new IChunkProviderWrapper(chunkProvider);
    }

    public static IChunkProvider toIChunkProvider(IIChunkProvider prov) {
        if (prov instanceof IChunkProviderWrapper) {
            return ((IChunkProviderWrapper) prov).getChunkProvider();
        }
        return null;
    }

    public static IChunk fromChunk(Chunk chunk) {
        if (chunk == null) {
            return null;
        }
        return new ChunkWrapper(chunk);
    }

    public static Chunk toChunk(IChunk chunk) {
        if (chunk instanceof ChunkWrapper) {
            return ((ChunkWrapper) chunk).getChunk();
        }
        return null;
    }

    public static ILivingEntity toLivingEntity(EntityLivingBase entity) {
        //TODO
        return null;
    }

    public static EntityLivingBase fromLivingEntity(ILivingEntity entity) {
        //TODO
        return null;
    }

    public static ItemCameraTransforms.TransformType toCamaraTransform(RenderPlace place) {
        switch (place) {

            case NONE:
                return ItemCameraTransforms.TransformType.NONE;
            case THIRD_PERSON:
            case THIRD_PERSON_RIGHT_HAND:
            case THIRD_PERSON_LEFT_HAND:
                return ItemCameraTransforms.TransformType.THIRD_PERSON;
            case FIRST_PERSON:
            case FIRST_PERSON_RIGHT_HAND:
            case FIST_PERSON_LEFT_HAND:
                return ItemCameraTransforms.TransformType.FIRST_PERSON;
            case GUI:
                return ItemCameraTransforms.TransformType.GUI;
            case HEAD:
                return ItemCameraTransforms.TransformType.HEAD;
            case GROUND:
                return ItemCameraTransforms.TransformType.GROUND;
            case FIXED:
                return ItemCameraTransforms.TransformType.FIXED;
        }
        return ItemCameraTransforms.TransformType.NONE;
    }

    public static ResourceReference fromResourceLocation(ResourceLocation loc) {
        if (loc == null) return null;
        return new ResourceReference(loc.getResourceDomain(), loc.getResourcePath());
    }

    public static ResourceLocation toResourceLocation(ResourceReference loc) {
        if (loc == null) return null;
        return new ResourceLocation(loc.getDomain(), loc.getPath());
    }

    public static IDataList fromNBTList(NBTTagList nbt) {
        if(nbt == null) return null;
        return new NBTTagListWrapper(nbt);
    }

    public static NBTTagList toNBTTagList(IDataList nbt){
        if (nbt instanceof NBTTagListWrapper){
            return ((NBTTagListWrapper) nbt).getNBTTagList();
        }
        return null;
    }

    public static IInventoryHandler fromInventory(IInventory inv) {
        if (inv == null)return null;
        if (inv instanceof ISidedInventory){
            return new SidedInventoryWrapper((ISidedInventory) inv);
        }
        return new SimpleInventoryWrapper(inv);
    }
}
