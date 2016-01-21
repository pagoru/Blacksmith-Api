package net.darkaqua.blacksmith.mod.item;

import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 16/12/2015.
 */
public class BS_Item extends Item {

    protected IItemDefinition definition;

    public BS_Item(IItemDefinition def) {
        definition = def;
        definition.onCreate(MCInterface.fromItem(this));
        setUnlocalizedName(def.getUnlocalizedName());
        setCreativeTab(MCInterface.fromCreativeTab(def.getCreativeTab()));
    }

    public IItemDefinition getItemDefinition() {
        return definition;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + definition.getUnlocalizedName(MCInterface.fromItemStack(stack));
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
                             float hitX, float hitY, float hitZ) {
        return definition.onItemUse(MCInterface.fromItemStack(stack), MCInterface.toPlayer(playerIn),
                new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)),
                MCInterface.fromEnumFacing(side), new Vect3d(hitX, hitY, hitZ));
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block) {
        return definition.getStrengthVsBlock(MCInterface.fromItemStack(stack),
                MCInterface.fromIBlockState(block.getDefaultState()));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        return MCInterface.toItemStack(definition.onItemRightClick(MCInterface.fromItemStack(itemStackIn),
                MCInterface.fromWorld(worldIn), MCInterface.toPlayer(playerIn)));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        return super.onItemUseFinish(stack, worldIn, playerIn);
    }

    @Override
    public boolean getHasSubtypes() {
        return definition.hasSubtypes();
    }

    @Override
    public int getMaxDamage() {
        return definition.getMaxDamage();
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return definition.hitEntity(MCInterface.fromItemStack(stack),MCInterface.toLivingEntity(target), MCInterface.toLivingEntity(attacker));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
        return definition.onBlockDestroyed(MCInterface.fromItemStack(stack), MCInterface.toWorldRef(worldIn, pos), MCInterface.toLivingEntity(playerIn));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
        return definition.canInteractWithEntity(MCInterface.fromItemStack(stack), MCInterface.toPlayer(playerIn), MCInterface.toLivingEntity(target));
    }

    @Override
    public boolean isFull3D() {
        return definition.is3DItem();
    }

    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return definition.shouldRotateWhenRenderingInHand();
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return definition.getColorFromItemStack(MCInterface.fromItemStack(stack), renderPass).toInt();
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        definition.onUpdate(MCInterface.fromItemStack(stack), MCInterface.fromWorld(worldIn), MCInterface.fromEntity(entityIn), itemSlot, isSelected);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        definition.onCreated(MCInterface.fromItemStack(stack), MCInterface.fromWorld(worldIn), MCInterface.toPlayer(playerIn));
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return definition.getItemUseMaxDuration(MCInterface.fromItemStack(stack));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
        definition.onPlayerStoppedUsing(MCInterface.fromItemStack(stack), MCInterface.fromWorld(worldIn), MCInterface.toPlayer(playerIn), timeLeft);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        definition.addInformation(MCInterface.fromItemStack(stack), MCInterface.toPlayer(playerIn), tooltip, advanced);
    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return definition.isTool(MCInterface.fromItemStack(stack));
    }

    @Override
    public boolean canItemEditBlocks() {
        return super.canItemEditBlocks();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return super.getIsRepairable(toRepair, repair);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
    }
//
    @Override
    public int getItemStackLimit(ItemStack stack) {
        return definition.getMaxStackSize(MCInterface.fromItemStack(stack));
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return definition.isDamaged(MCInterface.fromItemStack(stack));
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return definition.getDurabilityBarValue(MCInterface.fromItemStack(stack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems){
        List<IItemStack> sub = new LinkedList<>();
        definition.getSubItems(MCInterface.fromItem(itemIn), MCInterface.fromCreativeTab(tab), sub);
        sub.stream().map(MCInterface::toItemStack).forEach(subItems::add);
    }

    @Override
    public int getMaxDamage(ItemStack stack){
        return definition.getMaxDamage(MCInterface.fromItemStack(stack));
    }
}