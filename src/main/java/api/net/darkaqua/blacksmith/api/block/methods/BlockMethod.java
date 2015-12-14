package net.darkaqua.blacksmith.api.block.methods;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockVariant;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.WorldRef;

import javax.vecmath.Vector3d;

/**
 * Created by cout970 on 12/12/2015.
 */
public interface BlockMethod {

    interface OnBlockActivate extends BlockMethod {
        void onActivate();
    }

    interface OnHarvested extends BlockMethod {
        void onBlockHarvested(WorldRef ref, IBlockVariant variant, IPlayer player);
    }

    interface OnPlacedBy extends BlockMethod {
        void onBlockPlacedBy(WorldRef ref, IBlockVariant state, IPlayer placer, IItemStack stack);
    }

    interface OnRemovedByPlayer extends BlockMethod{
        boolean removedByPlayer(WorldRef ref, IPlayer player, boolean willHarvest);
    }

    interface OnAdded extends BlockMethod {
        void onBlockAdded(WorldRef ref, IBlockVariant fromBlockState);
    }

    interface OnBreaks extends BlockMethod {
        void onBlockBreaks(WorldRef ref, IBlockVariant state);
    }

    interface OnNeighborChange extends BlockMethod {
        void onNeighborBlockChange(WorldRef ref, IBlockVariant state, IBlock neighbor);
    }

    interface OnActivated extends BlockMethod {
        boolean onBlockActivated(WorldRef ref, IBlockVariant state, IPlayer player, Direction side, Vector3d vector3d);
    }

    interface OnEntityCollided extends BlockMethod {
        void onEntityCollidedWithBlock(WorldRef ref, IEntity entity);
    }

    interface OnPlaced extends BlockMethod {
        IBlockVariant onBlockPlaced(WorldRef ref, Direction side, IPlayer entity, Vector3d hit, int metadata);
    }

    interface OnClicked extends BlockMethod {
        void onBlockClicked(WorldRef ref, IPlayer player);
    }

    interface AllBlockMethods extends OnBlockActivate, OnHarvested, OnPlacedBy,
            OnRemovedByPlayer, OnAdded, OnBreaks, OnNeighborChange,
            OnActivated, OnEntityCollided, OnPlaced, OnClicked {}
}
