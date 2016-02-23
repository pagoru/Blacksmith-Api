package net.darkaqua.blacksmith.mod.common.util;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockAttribute;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.client.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockDataHandler;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.ILivingEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.fluid.IFluid;
import net.darkaqua.blacksmith.api.common.fluid.IFluidStack;
import net.darkaqua.blacksmith.api.common.gui.IFontRenderer;
import net.darkaqua.blacksmith.api.common.gui.ISlotDefinition;
import net.darkaqua.blacksmith.api.common.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.common.inventory.IInventoryHandler;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.recipe.ICraftingGrid;
import net.darkaqua.blacksmith.api.client.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.client.sound.ISoundEffect;
import net.darkaqua.blacksmith.api.common.storage.IDataCompound;
import net.darkaqua.blacksmith.api.common.storage.IDataList;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.util.*;
import net.darkaqua.blacksmith.api.common.util.raytrace.Cube;
import net.darkaqua.blacksmith.api.common.util.raytrace.RayTraceResult;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3i;
import net.darkaqua.blacksmith.api.common.world.*;
import net.darkaqua.blacksmith.mod.common.block.BlockWrapper;
import net.darkaqua.blacksmith.mod.common.block.MaterialWrapper;
import net.darkaqua.blacksmith.mod.common.block.blockdata.*;
import net.darkaqua.blacksmith.mod.client.creativetab.CreativeTabWrapper;
import net.darkaqua.blacksmith.mod.common.entity.BS_EntityFactory;
import net.darkaqua.blacksmith.mod.common.entity.EntityLivingWrapper;
import net.darkaqua.blacksmith.mod.common.entity.EntityPlayerWrapper;
import net.darkaqua.blacksmith.mod.common.entity.EntityWrapper;
import net.darkaqua.blacksmith.mod.common.fluid.FluidStackWrapper;
import net.darkaqua.blacksmith.mod.common.fluid.FluidWrapper;
import net.darkaqua.blacksmith.mod.common.gui.BS_Slot;
import net.darkaqua.blacksmith.mod.common.intermod.CapabilityWrapper;
import net.darkaqua.blacksmith.mod.common.intermod.IStorageWrapper;
import net.darkaqua.blacksmith.mod.common.intermod.StorageHandlerWrapper;
import net.darkaqua.blacksmith.mod.common.inventory.InventoryHandlerWrapper;
import net.darkaqua.blacksmith.mod.common.inventory.ItemStackWrapper;
import net.darkaqua.blacksmith.mod.common.inventory.SimpleInventoryWrapper;
import net.darkaqua.blacksmith.mod.common.item.ItemBlockWrapper;
import net.darkaqua.blacksmith.mod.common.item.ItemWrapper;
import net.darkaqua.blacksmith.mod.common.recipe.InventoryCraftingWrapper;
import net.darkaqua.blacksmith.mod.common.gui.FontRendererWrapper;
import net.darkaqua.blacksmith.mod.client.sound.SoundWrapper;
import net.darkaqua.blacksmith.mod.common.storage.NBTTagCompoundWrapper;
import net.darkaqua.blacksmith.mod.common.storage.NBTTagListWrapper;
import net.darkaqua.blacksmith.mod.common.tileentity.TileEntityWrapper;
import net.darkaqua.blacksmith.mod.common.world.ChunkWrapper;
import net.darkaqua.blacksmith.mod.common.world.IBlockAccessWrapper;
import net.darkaqua.blacksmith.mod.common.world.IChunkProviderWrapper;
import net.darkaqua.blacksmith.mod.common.world.WorldWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;


public class MCInterface {

    public static IWorldAccess fromBlockAccess(IBlockAccess world) {
        if (world == null) return null;
        return new IBlockAccessWrapper(world);
    }

    public static IBlockAccess toBlockAccess(IWorldAccess world) {
        if (world instanceof IBlockAccessWrapper)
            return ((IBlockAccessWrapper) world).getWorldAccess();
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
        return BS_EntityFactory.fromEntity(entity);
    }

    public static Entity toEntity(IEntity entity) {
        if (entity instanceof EntityWrapper)
            return ((EntityWrapper) entity).getMcEntity();
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
        if (item instanceof ItemBlock) {
            return new ItemBlockWrapper((ItemBlock) item);
        }
        return new ItemWrapper(item);
    }

    public static Item toItem(IItem item) {
        if (item instanceof ItemWrapper)
            return ((ItemWrapper) item).getItem();
        return null;
    }

    public static IBlockData fromBlockState(IBlockState state) {
        if (state == null) return null;
        return new IBlockStateWrapper(state);
    }

    public static IBlockState toBlockState(IBlockData state) {
        if (state instanceof IBlockStateWrapper)
            return ((IBlockStateWrapper) state).getBlockState();
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

    public static IPlayer toPlayer(EntityPlayer player) {
        if (player == null) return null;
        return new EntityPlayerWrapper(player);
    }

    public static EntityPlayer fromPlayer(IPlayer player) {
        if (player instanceof EntityPlayerWrapper)
            return ((EntityPlayerWrapper) player).getPlayer();
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
        if (entity == null) return null;
        return new EntityLivingWrapper(entity);
    }

    public static EntityLivingBase fromLivingEntity(ILivingEntity entity) {
        if (entity instanceof EntityLivingWrapper) {
            return ((EntityLivingWrapper) entity).getLivingEntity();
        }
        return null;
    }

    public static ItemCameraTransforms.TransformType toCameraTransform(RenderPlace place) {
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
        if (nbt == null) return null;
        return new NBTTagListWrapper(nbt);
    }

    public static NBTTagList toNBTTagList(IDataList nbt) {
        if (nbt instanceof NBTTagListWrapper) {
            return ((NBTTagListWrapper) nbt).getNBTTagList();
        }
        return null;
    }

    public static IInventoryHandler fromInventory(IInventory inv) {
        if (inv == null) return null;
        return new SimpleInventoryWrapper(inv);
    }

    public static IFluidStack fromFluidStack(FluidStack stack) {
        if (stack == null) return null;
        return new FluidStackWrapper(stack);
    }

    public static FluidStack toFluidStack(IFluidStack stack) {
        if (stack instanceof FluidStackWrapper) {
            return ((FluidStackWrapper) stack).getFluidStack();
        }
        return null;
    }

    public static IFluid fromFluid(Fluid fluid) {
        if (fluid == null) return null;
        return new FluidWrapper(fluid);
    }

    public static Fluid toFluid(IFluid fluid) {
        if (fluid instanceof FluidWrapper) {
            return ((FluidWrapper) fluid).getFluid();
        }
        return null;
    }

    public static ICraftingGrid fromInventoryCrafting(InventoryCrafting inv) {
        if (inv == null) return null;
        return new InventoryCraftingWrapper(inv);
    }

    public static InventoryCrafting toInventoryCrafting(ICraftingGrid grid) {
        if (grid instanceof InventoryCraftingWrapper) {
            return ((InventoryCraftingWrapper) grid).getInventory();
        }
        return null;
    }

    public static Side toSide(GameSide gameSide) {
        if (gameSide == null) return null;
        return Side.values()[gameSide.ordinal()];
    }

    public static Slot toSlot(ISlotDefinition slot) {
        if (slot == null) return null;
        return new BS_Slot(slot);
    }

    public static ISlotDefinition fromSlot(Slot slot) {
        if (slot instanceof BS_Slot) {
            return ((BS_Slot) slot).getDefinition();
        }
        return null;
    }

    public static IInventory toInventory(IInventoryHandler inv) {
        if (inv == null) return null;
        return new InventoryHandlerWrapper(inv);
    }

    public static IBlockMaterial toMaterial(Material mat) {
        if (mat == null) return null;
        return new MaterialWrapper(mat);
    }

    public static Material toMaterial(IBlockMaterial mat) {
        if (mat instanceof MaterialWrapper) {
            return ((MaterialWrapper) mat).getMaterial();
        }
        return null;
    }

    public static Vect3d fromVec3(Vec3 vec) {
        if (vec == null) return null;
        return new Vect3d(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public static Vec3 toVec3(Vect3d vec) {
        if (vec == null) return null;
        return new Vec3(vec.getX(), vec.getY(), vec.getZ());
    }

    public static <T extends Comparable<T>> IProperty<T> fromBlockAttribute(IBlockAttribute<T> attr) {
        if (attr instanceof BlockAttribute) {
            return ((BlockAttribute<T>) attr).getProperty();
        }
        if (attr instanceof BlockAttributeWrapper){
            return ((BlockAttributeWrapper<T>) attr).getProperty();
        }
        return null;
    }

    public static <T extends Comparable<T>> IBlockAttribute<T> toBlockAttribute(IProperty<T> p) {
        if (p == null) return null;
        if (p instanceof BlockAttribute.Property){
            return ((BlockAttribute.Property<T>) p).getAttribute();
        }
        return new BlockAttributeWrapper<>(p);
    }

    public static IBlockDataHandler toBlockDataHandler(BlockState gen) {
        if (gen == null) return null;
        return new BlockStateWrapper(gen);
    }

    public static BlockState fromBlockDataHandler(IBlockDataHandler gen) {
        if (gen instanceof BlockStateWrapper) {
            return ((BlockStateWrapper) gen).getBlockState();
        }
        return null;
    }

    public static IInterfaceIdentifier fromCapability(Capability<?> cap) {
        if (cap == null) return null;
        return new CapabilityWrapper(cap);
    }

    public static Capability<?> toCapability(IInterfaceIdentifier id) {
        if (id instanceof CapabilityWrapper) {
            return ((CapabilityWrapper) id).getCapability();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Capability.IStorage<T> fromStorageHandler(IInterfaceIdentifier.IStorageHandler storage) {
        if (storage instanceof IStorageWrapper) {
            return (Capability.IStorage<T>) ((IStorageWrapper) storage).getStorage();
        }
        return new StorageHandlerWrapper(storage);
    }

    public static IInterfaceIdentifier.IStorageHandler toStorageHandler(Capability.IStorage<?> storage) {
        if (storage instanceof StorageHandlerWrapper) {
            return ((StorageHandlerWrapper) storage).getHandler();
        }
        return new IStorageWrapper(storage);
    }

    public static WorldRef toWorldRef(World worldIn, BlockPos pos) {
        return new WorldRef(fromWorld(worldIn), fromBlockPos(pos));
    }

    public static RayTraceResult fromMOP(MovingObjectPosition mop) {
        if (mop == null) return null;
        RayTraceResult res = null;
        switch (mop.typeOfHit) {
            case MISS:
                res = new RayTraceResult(MCInterface.fromVec3(mop.hitVec));
                break;
            case BLOCK:
                res = new RayTraceResult(MCInterface.fromVec3(mop.hitVec), MCInterface.fromEnumFacing(mop.sideHit),
                        MCInterface.fromBlockPos(mop.getBlockPos()));
                break;
            case ENTITY:
                res = new RayTraceResult(MCInterface.fromVec3(mop.hitVec), MCInterface.fromEntity(mop.entityHit));
                break;
        }
        res.setExtraData(mop.hitInfo);
        return res;
    }

    public static MovingObjectPosition toMOP(RayTraceResult res) {
        if (res == null) return null;
        MovingObjectPosition mop = null;
        switch (res.getType()) {
            case MISS:
                mop = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, MCInterface.toVec3(res.getHit()), MCInterface.toEnumFacing(res.getSide()), MCInterface.toBlockPos(res.getPosition()));
                break;
            case BLOCK:
                mop = new MovingObjectPosition(MCInterface.toVec3(res.getHit()), MCInterface.toEnumFacing(res.getSide()), MCInterface.toBlockPos(res.getPosition()));
                break;
            case ENTITY:
                mop = new MovingObjectPosition(MCInterface.toEntity(res.getEntity()), MCInterface.toVec3(res.getHit()));
                break;
        }
        mop.hitInfo = res.getExtraData();
        return mop;
    }

    public static ISound toSound(ISoundEffect sound) {
        if (sound instanceof SoundWrapper) {
            return ((SoundWrapper) sound).getSound();
        }
        return null;
    }

    public static ISoundEffect fromSound(ISound sound) {
        if (sound == null) return null;
        return new SoundWrapper(sound);

    }
}
