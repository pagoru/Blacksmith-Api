package net.darkaqua.blacksmith.mod.item;

import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by cout970 on 16/12/2015.
 */
public class BS_Item extends Item {

    protected IItemDefinition definition;

    public BS_Item(IItemDefinition def){
        definition = def;
        definition.onCreate(MCInterface.fromItem(this));
        setUnlocalizedName(def.getUnlocalizedName());
        setCreativeTab(MCInterface.fromCreativeTab(def.getCreativeTab()));
    }

    public IItemDefinition getItemDefinition(){
        return definition;
    }

    public String getUnlocalizedName(ItemStack stack){
        return "item."+definition.getUnlocalizedName(MCInterface.fromItemStack(stack));
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return definition.onItemUse(MCInterface.fromItemStack(stack), MCInterface.toPlayer(playerIn), new WorldRef(MCInterface.fromWorld(worldIn), MCInterface.fromBlockPos(pos)), MCInterface.fromEnumFacing(side), new Vect3d(hitX, hitY, hitZ));
    }

    //TODO
//    @Override
//    public float getStrVsBlock(ItemStack stack, Block block) {
//        return super.getStrVsBlock(stack, block);
//    }
//
//    @Override
//    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
//        return super.onItemRightClick(itemStackIn, worldIn, playerIn);
//    }
//
//    @Override
//    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
//        return super.onItemUseFinish(stack, worldIn, playerIn);
//    }

    @Override
    public int getMetadata(int damage) {
        return super.getMetadata(damage);
    }

    @Override
    public boolean getHasSubtypes() {
        return definition.hasSubtypes();
    }

    @Override
    public int getMaxDamage() {
        return definition.getMaxDamage();
    }
//
//    @Override
//    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
//        return super.hitEntity(stack, target, attacker);
//    }
//
//    @Override
//    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
//        return super.onBlockDestroyed(stack, worldIn, blockIn, pos, playerIn);
//    }
//
//    @Override
//    public boolean canHarvestBlock(Block blockIn) {
//        return super.canHarvestBlock(blockIn);
//    }
//
//    @Override
//    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
//        return super.itemInteractionForEntity(stack, playerIn, target);
//    }
//
    @Override
    public boolean isFull3D() {
        return definition.is3DItem();
    }
//
//    @Override
//    public boolean shouldRotateAroundWhenRendering() {
//        return super.shouldRotateAroundWhenRendering();
//    }
//
//    @Override
//    public Item getContainerItem() {
//        return super.getContainerItem();
//    }
//
//    @Override
//    public int getColorFromItemStack(ItemStack stack, int renderPass) {
//        return super.getColorFromItemStack(stack, renderPass);
//    }
//
//    @Override
//    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
//    }
//
//    @Override
//    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
//        super.onCreated(stack, worldIn, playerIn);
//    }
//
//    @Override
//    public int getMaxItemUseDuration(ItemStack stack) {
//        return super.getMaxItemUseDuration(stack);
//    }
//
//    @Override
//    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
//        super.onPlayerStoppedUsing(stack, worldIn, playerIn, timeLeft);
//    }
//
//    @Override
//    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
//        super.addInformation(stack, playerIn, tooltip, advanced);
//    }
//
//    @Override
//    public boolean isItemTool(ItemStack stack) {
//        return super.isItemTool(stack);
//    }
//
//    @Override
//    public int getItemEnchantability() {
//        return super.getItemEnchantability();
//    }
//
//    @Override
//    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
//        super.getSubItems(itemIn, tab, subItems);
//    }
//
//    @Override
//    public boolean canItemEditBlocks() {
//        return super.canItemEditBlocks();
//    }
//
//    @Override
//    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
//        return super.getIsRepairable(toRepair, repair);
//    }
//
//    @Override
//    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
//        return super.onDroppedByPlayer(item, player);
//    }
//
//    @Override
//    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
//        return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
//    }
//
    @Override
    public int getItemStackLimit(ItemStack stack) {
        return definition.getMaxStackSize(MCInterface.fromItemStack(stack));
    }

//    @Override
//    public boolean isDamaged(ItemStack stack) {
//        return super.isDamaged(stack);
//    }
//
//    @Override
//    public double getDurabilityForDisplay(ItemStack stack) {
//        return super.getDurabilityForDisplay(stack);
//    }
}