package net.darkaqua.blacksmith.api.block.methods;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.variants.IBlockData;
import net.darkaqua.blacksmith.api.entity.IEntity;
import net.darkaqua.blacksmith.api.entity.ILivingEntity;
import net.darkaqua.blacksmith.api.entity.IPlayer;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 12/12/2015.
 */
public interface BlockMethod {

    interface OnHarvested extends BlockMethod {
        void onHarvested(WorldRef ref, IBlockData variant, IPlayer player);
    }

    interface OnPlacedBy extends BlockMethod {
        void onPlacedBy(WorldRef ref, IBlockData state, ILivingEntity placer, IItemStack stack);
    }

    interface OnRemovedByPlayer extends BlockMethod {
        boolean onRemovedByPlayer(WorldRef ref, IPlayer player, boolean willHarvest);
    }

    interface OnAdded extends BlockMethod {
        void onAdded(WorldRef ref, IBlockData fromBlockState);
    }

    interface OnBreaks extends BlockMethod {
        void onBreaks(WorldRef ref, IBlockData state);
    }

    interface OnNeighborChange extends BlockMethod {
        void onNeighborBlockChange(WorldRef ref, IBlockData state, IBlock neighbor);
    }

    interface OnActivated extends BlockMethod {
        boolean onActivated(WorldRef ref, IBlockData state, IPlayer player, Direction side, Vect3d vector3d);
    }

    interface OnEntityCollided extends BlockMethod {
        void onEntityCollided(WorldRef ref, IEntity entity);
    }

    interface OnPlaced extends BlockMethod {
        IBlockData onPlaced(WorldRef ref, Direction side, ILivingEntity entity, Vect3d hit, int metadata);
    }

    interface OnClicked extends BlockMethod {
        void onClicked(WorldRef ref, IPlayer player);
    }

    interface AllBlockMethods extends OnHarvested, OnPlacedBy,
            OnRemovedByPlayer, OnAdded, OnBreaks, OnNeighborChange,
            OnActivated, OnEntityCollided, OnPlaced, OnClicked {
    }
}
